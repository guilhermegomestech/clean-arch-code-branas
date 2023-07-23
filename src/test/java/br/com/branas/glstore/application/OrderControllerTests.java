package br.com.branas.glstore.application;

import br.com.branas.glstore.domain.entities.DiscountCoupon;
import br.com.branas.glstore.domain.entities.Order;
import br.com.branas.glstore.domain.entities.Product;
import br.com.branas.glstore.domain.services.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
@ExtendWith(MockitoExtension.class)
public class OrderControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    public void testReturnAllOrdersOK() throws Exception {
        given(orderService.getAllPedidos()).willReturn(Arrays.asList(new Order()));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/order")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testReturnAllOrderNoContent() throws Exception {
        given(orderService.getAllPedidos()).willReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/order")).andDo(print()).andExpect(status().isNoContent());
    }

    @Test
    public void testCreateOrder() throws Exception {
        Order order = new Order();
        order.setOrderDescription("pedido de cliente externo");
        order.setClientName("Maria Silva");
        order.setAddressClient("Rua Vinho, 72 - Oliveira");
        order.setClientCpf("407.302.170-27");
        DiscountCoupon coupon = new DiscountCoupon("COUP_10", new BigDecimal(10), LocalDate.now());
        order.setDiscountCoupon(coupon);
        Product one = new Product();
        one.setProductDescription("Batedeira");
        one.setProductPrice(new BigDecimal(250));
        Product two = new Product();
        two.setProductDescription("Fogão");
        two.setProductPrice(new BigDecimal(560));
        order.setListProducts(Arrays.asList(one, two));

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = objectWriter.writeValueAsString(order);

        given(orderService.validateCpf("000.531.410-50")).willReturn(false);
        when(orderService.validateCpf("000.531.410-50")).thenReturn(false);
        mockMvc.perform(post("/api/v1/order").contentType(APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andExpect(status().isOk());

    }

    @Test
    public void testFindOrderBySerialNumber() throws Exception {
        Long serialNumber = 202300000001l;
        given(orderService.getOrderBySerialNumberOrder(serialNumber)).willReturn(new Order());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/order/{serialNumber}", serialNumber.toString())).andDo(print()).andExpect(status().isOk());
    }
    @Test
    public void testNoFindOrderBySerialNumber() throws Exception {
        Long serialNumber = 0l;
        given(orderService.getOrderBySerialNumberOrder(serialNumber)).willReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/order/{serialNumber}", 0l)).andDo(print()).andExpect(status().isNotFound());
    }
    //TODO: CRIAR TESTE INCLUSAO PEDIDO;

}
