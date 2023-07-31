package br.com.branas.glstore.domain.services;

import br.com.branas.glstore.domain.entities.Product;
import br.com.branas.glstore.domain.services.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

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

    @Test
    public void testMinimumFreight(){
        Product productOne = new Product();
        productOne.setProductHeight(20.0);
        productOne.setProductLength(18.0);
        productOne.setProductWidth(15.0);
        productOne.setProductWeight(2.0);

        Product productTwo = new Product();
        productTwo.setProductHeight(30.0);
        productTwo.setProductLength(20.0);
        productTwo.setProductWidth(10.0);
        productTwo.setProductWeight(2.0);

        Double freight = 0.0;
        for (Product product : Arrays.asList(productOne, productTwo)){
            Double volume = product.getProductWidth()/100 * product.getProductHeight()/100 * product.getProductLength()/100;
            Double density = product.getProductWeight()/volume;
            freight = 1000 * volume * (density/100);
            freight += freight * 2;
        }
        System.out.println(freight);
        assertFalse(new BigDecimal(freight).compareTo(BigDecimal.TEN) < 0);
    }

    @Test
    public void testSerialNumberOrder(){
        Long serialNumber = Long.valueOf(String.valueOf(LocalDate.now().getYear()).concat("00000001"));
        assertTrue(serialNumber.compareTo(202300000001l) == 0);
    }

    @Test
    public void testSerialNumberOrderSum(){
        Long serialNumber = Long.valueOf(String.valueOf(LocalDate.now().getYear()).concat(StringUtils.leftPad("0", 8, "0")));
        serialNumber += 1;
        assertTrue(serialNumber.compareTo(202300000001l) == 0);
    }
}
