package br.com.branas.glstore.application.services;

import br.com.branas.glstore.application.entities.DiscountCoupon;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

@Service
public class DiscountCouponService {

    public boolean checkCouponIsValid(DiscountCoupon coupon){
        return Objects.nonNull(coupon) && !coupon.getExpirationDate().isAfter(LocalDate.now());
    }
}
