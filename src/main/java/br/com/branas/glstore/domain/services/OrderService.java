package br.com.branas.glstore.domain.services;

import br.com.branas.glstore.domain.entities.Order;
import br.com.branas.glstore.domain.entities.Product;
import br.com.branas.glstore.exceptions.OrderException;
import br.com.branas.glstore.infrastructure.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order salvarPedido(Order order) {
        if(!validateCpf(order.getClientCpf())){
            throw new OrderException("Cpf is invalid");
        }

        order.setQuantity(order.getListProducts().size());
        order.setOrderGrossValue(order.getListProducts().stream().map(Product::getProductPrice).reduce(BigDecimal.ZERO, BigDecimal::add));

        if(order.isExistsCoupon()){
            order.setOrderGrossValue(calcularDescontoPedido(order.getOrderGrossValue(), order.getDiscountPercentage()));
        }
        return this.orderRepository.save(order);
    }

    public List<Order> getAllPedidos() {
        return this.orderRepository.findAll();
    }

    public BigDecimal calcularDescontoPedido(BigDecimal valorTotalPedido, BigDecimal valorDesconto) {
        return valorTotalPedido.subtract(valorTotalPedido.multiply(valorDesconto).divide(new BigDecimal(100)));
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
