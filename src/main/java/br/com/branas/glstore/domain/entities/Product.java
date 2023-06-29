package br.com.branas.glstore.domain.entities;

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
}
