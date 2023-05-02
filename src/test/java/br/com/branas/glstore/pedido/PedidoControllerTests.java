package br.com.branas.glstore.pedido;

import br.com.branas.glstore.domain.entities.Pedido;
import br.com.branas.glstore.domain.services.PedidoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@SpringBootTest
// (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //cria uma porta de forma randomica
@WebMvcTest
@ExtendWith(MockitoExtension.class)
public class PedidoControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoService pedidoService;

    @Test
    public void testarRetornoGetAllPedidosOK() throws Exception {
        given(pedidoService.getAllPedidos()).willReturn(Arrays.asList(new Pedido()));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/pedido")).andDo(print()).andExpect(status().isOk());
    }
    @Test
    public void testarRetornoGetAllPedidosNoContent() throws Exception {
        given(pedidoService.getAllPedidos()).willReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/pedido")).andDo(print()).andExpect(status().isNoContent());
    }

    //TODO: CRIAR TESTE INCLUSAO PEDIDO;

}
