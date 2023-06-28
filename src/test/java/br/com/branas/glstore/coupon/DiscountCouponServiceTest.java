package br.com.branas.glstore.coupon;

import br.com.branas.glstore.domain.entities.DiscountCoupon;
import br.com.branas.glstore.domain.services.DiscountCouponService;
import br.com.branas.glstore.domain.services.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class DiscountCouponServiceTest {

    @InjectMocks
    private DiscountCouponService discountCouponService;

    @Test
    public void testCouponExpired() throws Exception {
        DiscountCoupon coupon = new DiscountCoupon();
        coupon.setCouponCode("TESTE_123");
        coupon.setExpirationDate(LocalDate.now().plusDays(1l));

        assertFalse(discountCouponService.checkCouponIsValid(coupon), "Coupon valid");
    }

    @Test
    public void testCouponValid() throws Exception {
        DiscountCoupon coupon = new DiscountCoupon();
        coupon.setCouponCode("TESTE_123");
        coupon.setExpirationDate(LocalDate.now().plusDays(-1l));

        assertTrue(discountCouponService.checkCouponIsValid(coupon), "Coupon invalid");
    }
}
