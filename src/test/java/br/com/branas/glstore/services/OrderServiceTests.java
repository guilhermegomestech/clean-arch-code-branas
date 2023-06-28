package br.com.branas.glstore.services;

import br.com.branas.glstore.domain.services.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(MockitoExtension.class)
public class OrderServiceTests {

    @InjectMocks
    private OrderService orderService;

    @Test
    public void testCpfInvalid() {
        assertFalse(orderService.validateCpf("000.531.410-50"));
    }

    @Test
    public void testCpfValid() {
        assertTrue(orderService.validateCpf("407.302.170-27"));
    }

    @Test
    public void testOrderIsNotNegative(){
        assertTrue(orderService.isQuantityProductsIsNegative(-1));
    }
}
