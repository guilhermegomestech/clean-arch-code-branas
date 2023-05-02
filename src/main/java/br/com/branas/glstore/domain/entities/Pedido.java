package br.com.branas.glstore.domain.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;

    private String decricaoPedido;

    private String nomeCliente;

    private String enderecoCliente;

    private String cpfCliente;

    private BigDecimal valorTotalPedido;
    private Integer quantidade;

    private BigDecimal porcentagemDesconto;
    @Transient
    private boolean possuiCupom;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Produto> listaProdutos;

    // getters and setters

    public Long getIdPedido() {
        return idPedido;
    }

    public String getDecricaoPedido() {
        return decricaoPedido;
    }

    public void setDecricaoPedido(String decricaoPedido) {
        this.decricaoPedido = decricaoPedido;
    }


    public Integer getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public List<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(List<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getEnderecoCliente() {
        return enderecoCliente;
    }

    public void setEnderecoCliente(String enderecoCliente) {
        this.enderecoCliente = enderecoCliente;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public BigDecimal getValorTotalPedido() {
        return valorTotalPedido;
    }

    public void setValorTotalPedido(BigDecimal valorTotalPedido) {
        this.valorTotalPedido = valorTotalPedido;
    }

    public BigDecimal getPorcentagemDesconto() {
        return porcentagemDesconto;
    }

    public void setPorcentagemDesconto(BigDecimal porcentagemDesconto) {
        this.porcentagemDesconto = porcentagemDesconto;
    }

    public boolean isPossuiCupom() {
        return possuiCupom;
    }

    public void setPossuiCupom(boolean possuiCupom) {
        this.possuiCupom = possuiCupom;
    }
}