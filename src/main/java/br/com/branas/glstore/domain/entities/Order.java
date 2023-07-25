package br.com.branas.glstore.domain.entities;

import br.com.branas.glstore.domain.vo.ZipCodeVO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedido")
public class Order extends RepresentationModel<Order> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrder;
    private String orderDescription;
    private Long serialNumberOrder;
    private String clientName;
    private String addressClient;
    private String clientCpf;
    private BigDecimal orderGrossValue;
    private Integer quantity;
    private BigDecimal freight;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "codeFrom", column = @Column(name = "code_from")),
            @AttributeOverride(name = "codeTo", column = @Column(name = "code_To"))})
    private ZipCodeVO zipCode;

    @OneToOne(cascade = CascadeType.ALL)
    private DiscountCoupon discountCoupon;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> listProducts = new ArrayList<>();

    public Long getIdOrder() {
        return idOrder;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getAddressClient() {
        return addressClient;
    }

    public void setAddressClient(String addressclient) {
        this.addressClient = addressclient;
    }

    public String getClientCpf() {
        return clientCpf;
    }

    public void setClientCpf(String clientCpf) {
        this.clientCpf = clientCpf;
    }

    public BigDecimal getOrderGrossValue() {
        return orderGrossValue;
    }

    public void setOrderGrossValue(BigDecimal orderGrossValue) {
        this.orderGrossValue = orderGrossValue;
    }

    public Integer getQuantity() {
        return getListProducts().size();
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public DiscountCoupon getDiscountCoupon() {
        return discountCoupon;
    }

    public void setDiscountCoupon(DiscountCoupon discountCoupon) {
        this.discountCoupon = discountCoupon;
    }

    public List<Product> getListProducts() {
        return listProducts;
    }

    public void setListProducts(List<Product> listProducts) {
        this.listProducts = listProducts;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public Long getSerialNumberOrder() {
        return serialNumberOrder;
    }

    public void setSerialNumberOrder(Long serialNumberOrder) {
        this.serialNumberOrder = serialNumberOrder;
    }

    public ZipCodeVO getZipCode() {
        return zipCode;
    }

    public void setZipCode(ZipCodeVO zipCode) {
        this.zipCode = zipCode;
    }
}