package br.com.branas.glstore.domain.services;

import br.com.branas.glstore.domain.entities.Pedido;
import br.com.branas.glstore.infrastructure.repositories.PedidoRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido salvarPedido(Pedido pedido) {
        return this.pedidoRepository.save(pedido);
    }

    public List<Pedido> getAllPedidos() {
        return this.pedidoRepository.findAll();
    }

    public BigDecimal calcularDescontoPedido(BigDecimal valorTotalPedido, BigDecimal valorDesconto) {
        return valorTotalPedido.subtract(valorTotalPedido.multiply(valorDesconto).divide(new BigDecimal(100)));
    }

    public boolean validarCpf(String cpf) {
        cpf = removeNonDigits(cpf);
        if (isValidLength(cpf)) return false;
        if (allDigitsTheSame(cpf)) {
            int d1 = 0;
            int d2 = 0;
            for (var nCount = 1; nCount < cpf.length() - 1; nCount++) {
                int digito = parseInt(cpf.substring(nCount - 1, nCount));
                d1 = d1 + (11 - nCount) * digito;
                d2 = d2 + (12 - nCount) * digito;
            }
            int dg2 = 0;
            int rest = (d1 % 11);
            int dg1 = (rest < 2) ? 0 : 11 - rest;
            d2 += 2 * dg1;
            rest = (d2 % 11);
            dg2 = rest < 2 ? 0 : (11 - rest);
            String actualCheckDigit = cpf.substring(cpf.length() - 2, cpf.length());
            String calculatedCheckDigit = String.valueOf(dg1).concat(String.valueOf(dg2));
            return actualCheckDigit.equalsIgnoreCase(calculatedCheckDigit);
        } else {
            return false;
        }
    }

    private String removeNonDigits(String cpf) {
        return cpf.replace(".", "").replace("-", "").replace(" ", "");
    }

    private boolean isValidLength(String cpf) {
        return cpf.length() != 11;
    }

    private boolean allDigitsTheSame(String cpf){
        String[] cpfArray = cpf.split("");
        return !Arrays.stream(cpfArray).allMatch(c -> c.equalsIgnoreCase(cpfArray[0]));
    }
}
