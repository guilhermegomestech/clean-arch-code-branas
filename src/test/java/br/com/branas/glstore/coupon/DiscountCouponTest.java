package br.com.branas.glstore.coupon;

import br.com.branas.glstore.domain.entities.DiscountCoupon;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class DiscountCouponTest {

    @Test
    public void testCouponExpired() throws Exception {
        DiscountCoupon coupon = new DiscountCoupon();
        coupon.setCouponCode("TESTE_123");
        coupon.setExpirationDate(LocalDate.now().plusDays(1l));

        assertTrue(coupon.getExpirationDate().isAfter(LocalDate.now()), "Coupon expired");
    }

    @Test
    public void testCouponValid() throws Exception {
        DiscountCoupon coupon = new DiscountCoupon();
        coupon.setCouponCode("TESTE_123");
        coupon.setExpirationDate(LocalDate.now().plusDays(-1l));

        assertFalse(coupon.getExpirationDate().isAfter(LocalDate.now()), "Coupon valid");
    }
}
