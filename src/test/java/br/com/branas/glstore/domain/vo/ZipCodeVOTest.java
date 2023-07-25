package br.com.branas.glstore.domain.vo;

import br.com.branas.glstore.infrastructure.exceptions.OrderException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ZipCodeVOTest {

    private static final String REGEX_ZIPCODE_VALIDATE = "^\\d{5}(-\\d{4})?$";

    @Test
    public void testEqualsZipCode(){
        ZipCodeVO zipCodeOne = new ZipCodeVO("46444-4111", "46949-4441");
        ZipCodeVO zipCodeTwo = new ZipCodeVO("46444-4111", "46949-4441");
        assertTrue(zipCodeOne.equals(zipCodeTwo));
    }

    @Test
    public void testZiCodeIsNotValid(){
        ZipCodeVO zipCodeVO = new ZipCodeVO("46444-4111", "46949-4441");
        assertTrue(zipCodeVO.zipCodeIsNotValid());
    }

    @Test
    public void testZiCodeIsNotValidException(){
        ZipCodeVO zipCodeVO = new ZipCodeVO(null, null);
        assertThrows(OrderException.class, () -> zipCodeVO.zipCodeIsNotValid());
    }

    @Test
    public void testValidateZipCodeHifen(){
        String zipCodeHifen = "15456-7844";
        assertTrue(zipCodeHifen.matches(REGEX_ZIPCODE_VALIDATE));
    }

    @Test
    public void testValidateZipCodeNoHifen(){
        String zipCodeNoHifen = "16469";
        assertTrue(zipCodeNoHifen.matches(REGEX_ZIPCODE_VALIDATE));
    }

}