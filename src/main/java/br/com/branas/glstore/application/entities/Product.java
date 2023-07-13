package br.com.branas.glstore.application.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "produto")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;
    private String productDescription;
    private BigDecimal productPrice;
    private Double productWidth;
    private Double productHeight;
    private Double productLength;
    private Double productWeight;

    public Product() {
    }

    public Product(String productDescription, BigDecimal productPrice) {
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String decricaoProduto) {
        this.productDescription = decricaoProduto;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal precoProduto) {
        this.productPrice = precoProduto;
    }

    public Double getProductWidth() {
        return productWidth;
    }

    public void setProductWidth(Double productWidth) {
        this.productWidth = productWidth;
    }

    public Double getProductHeight() {
        return productHeight;
    }

    public void setProductHeight(Double productHeight) {
        this.productHeight = productHeight;
    }

    public Double getProductLength() {
        return productLength;
    }

    public void setProductLength(Double productLength) {
        this.productLength = productLength;
    }

    public Double getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(Double productWeight) {
        this.productWeight = productWeight;
    }
}
