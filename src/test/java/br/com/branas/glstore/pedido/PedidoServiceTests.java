package br.com.branas.glstore.pedido;

import br.com.branas.glstore.domain.services.PedidoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(MockitoExtension.class)
public class PedidoServiceTests {

    @InjectMocks
    private PedidoService pedidoService;
    @Test
    public void testarCpfInvalido() throws Exception {
        assertFalse(pedidoService.validarCpf("000.531.410-50"));
    }
    @Test
    public void testarCpfValido() {
        assertTrue(pedidoService.validarCpf("407.302.170-27"));
    }


    //TODO: CRIAR TESTE VALIDACAO CUPOM
}
