package br.com.branas.glstore.domain.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduto;

    private String decricaoProduto;
    private BigDecimal precoProduto;

    public String getDecricaoProduto() {
        return decricaoProduto;
    }

    public void setDecricaoProduto(String decricaoProduto) {
        this.decricaoProduto = decricaoProduto;
    }

    public BigDecimal getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(BigDecimal precoProduto) {
        this.precoProduto = precoProduto;
    }
}
