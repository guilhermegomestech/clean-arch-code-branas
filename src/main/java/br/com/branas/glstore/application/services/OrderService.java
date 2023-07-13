package br.com.branas.glstore.application.services;

import br.com.branas.glstore.application.entities.Order;
import br.com.branas.glstore.application.entities.Product;
import br.com.branas.glstore.exceptions.OrderException;
import br.com.branas.glstore.infrastructure.repositories.OrderRepository;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.lang.Integer.parseInt;

@Service
public class OrderService {

    private static final String REGEX_ZIPCODE_VALIDATE = "^\\d{5}(-\\d{4})?$";
    private OrderRepository orderRepository;
    private DiscountCouponService discountCouponService;
    private ProductService productService;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductService productService, DiscountCouponService discountCouponService){
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.discountCouponService = discountCouponService;
    }

    public Order salvarPedido(Order order) {
        if(!validateCpf(order.getClientCpf())){
            throw new OrderException("Cpf is invalid");
        }

        if(isQuantityProductsIsNegative(order.getQuantity())){
            throw new OrderException("Products quantity can't is negative");
        }

        if(productService.productsNotCanRepeat(order.getListProducts())){
            throw new OrderException("The products list can't contain repeated elements.");
        }

        if(order.getListProducts().stream().anyMatch(product -> productService.productHaveInvalidDimension(product))){
            //TODO: TO UPDATE IN ORDER TO RETURN THE SPECIFIC PRODUCT
            throw new OrderException("The product dimension is invalid.");
        }

        if(order.getListProducts().stream().anyMatch(product -> productService.productHaveInvalidWeight(product))){
            //TODO: TO UPDATE IN ORDER TO RETURN THE SPECIFIC PRODUCT
            throw new OrderException("The product weight is invalid.");
        }

        order.setOrderGrossValue(order.getListProducts().stream().map(Product::getProductPrice).reduce(BigDecimal.ZERO, BigDecimal::add));

        if(Objects.nonNull(order.getDiscountCoupon())){
            if(discountCouponService.checkCouponIsValid(order.getDiscountCoupon())){
                order.setOrderGrossValue(calcularDescontoPedido(order.getOrderGrossValue(), order.getDiscountCoupon().getDiscountPercentage()));
            }
        }

        if(validateZipCode(order.getZipCodeFrom()) || validateZipCode(order.getZipCodeTo())){
            throw new OrderException("The ZIP code is invalid or not provided.");
        } else {
            order.setFreight(calculateFreight(order.getListProducts(), order.getQuantity()).setScale(2, RoundingMode.DOWN));
            order.setOrderGrossValue(order.getOrderGrossValue().add(applyFreightMinimum(order.getFreight())).setScale(2, RoundingMode.DOWN));
        }


        Long serialNumber = Long.valueOf(String.valueOf(LocalDate.now().getYear()).concat("00000000"));
        if(getLastSerialNumberOrder() != null){
            order.setSerialNumberOrder(getLastSerialNumberOrder() + 1l);
        } else {
            order.setSerialNumberOrder(serialNumber + 1l);
        }

        return this.orderRepository.save(order);
    }

    private BigDecimal calculateFreight(List<Product> productList, Integer quantity){
        Double freight = 0.0;
        for (Product product : productList){
            Double volume = product.getProductWidth()/100 * product.getProductHeight()/100 * product.getProductLength()/100;
            Double density = product.getProductWeight()/volume;
            freight = 1000 * volume * (density/100);
            freight += freight * quantity;
        }

        return new BigDecimal(freight);
    }

    private BigDecimal applyFreightMinimum(BigDecimal freight){
        return freight != null && freight.compareTo(BigDecimal.TEN) < 0 ? BigDecimal.TEN : freight;
    }

    public List<Order> getAllPedidos() {
        return this.orderRepository.findAll();
    }

    private BigDecimal calcularDescontoPedido(BigDecimal valorTotalPedido, BigDecimal valorDesconto) {
        return valorTotalPedido.subtract(valorTotalPedido.multiply(valorDesconto).divide(new BigDecimal(100)));
    }

    public boolean isQuantityProductsIsNegative(Integer quantityProducts){
        return quantityProducts < 0;
    }

    private Long getLastSerialNumberOrder(){
        return orderRepository.getLastSerialNumberOrder();
    }

    //TODO: MOVE NEXT METHODS FOR OTHER CLASS

    private boolean validateZipCode(String zipCode){
        return StringUtils.isEmpty(zipCode) || !zipCode.matches(REGEX_ZIPCODE_VALIDATE);
    }


    public boolean validateCpf(String cpf) {
        cpf = removeNonDigits(cpf);
        if (isValidLength(cpf)) return false;
        if (allDigitsTheSame(cpf)) {
            int dg1 = calculateDigits(cpf, 10);
            int dg2 = calculateDigits(cpf, 11);
            String actualCheckDigit = cpf.substring(cpf.length() - 2, cpf.length());
            String calculatedCheckDigit = String.valueOf(dg1).concat(String.valueOf(dg2));
            return actualCheckDigit.equalsIgnoreCase(calculatedCheckDigit);
        } else {
            return false;
        }
    }

    private int calculateDigits(String cpf, int factor) {
        int total = 0;
        for (String digit : Arrays.stream(cpf.split("")).toList()) {
            if (factor > 1) {
                total += Integer.valueOf(digit) * (factor--);
            }
        }
        int rest = total % 11;
        return (rest < 2 ? 0 : 11 - rest);
    }

    private String removeNonDigits(String cpf) {
        return cpf.replace(".", "").replace("-", "").replace(" ", "");
    }

    private boolean isValidLength(String cpf) {
        return cpf.length() != 11;
    }

    private boolean allDigitsTheSame(String cpf) {
        String[] cpfArray = cpf.split("");
        return !Arrays.stream(cpfArray).allMatch(c -> c.equalsIgnoreCase(cpfArray[0]));
    }
}
