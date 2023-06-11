package br.com.branas.glstore.domain.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "pedido")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrder;

    private String orderDescription;

    private String clientName;

    private String addressClient;

    private String clientCpf;

    private BigDecimal orderGrossValue;
    private Integer quantity;

    private BigDecimal discountPercentage;
    @Transient
    private boolean existsCoupon;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> listProducts;


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
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public boolean isExistsCoupon() {
        return existsCoupon;
    }

    public void setExistsCoupon(boolean existsCoupon) {
        this.existsCoupon = existsCoupon;
    }

    public List<Product> getListProducts() {
        return listProducts;
    }

    public void setListProducts(List<Product> listProducts) {
        this.listProducts = listProducts;
    }
}