package br.com.branas.glstore.domain.vo;

import br.com.branas.glstore.infrastructure.exceptions.OrderException;
import jakarta.persistence.Embeddable;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@Embeddable
public class ZipCodeVO {

    private static final String REGEX_ZIPCODE_VALIDATE = "^\\d{5}(-\\d{4})?$";

    private String codeFrom;
    private String codeTo;

    public ZipCodeVO(){

    }

    public ZipCodeVO(String codeFrom, String codeTo) {
        this.codeFrom = codeFrom;
        this.codeTo = codeTo;
    }

    public boolean zipCodeIsNotValid(){
        if(StringUtils.isEmpty(this.codeFrom) || StringUtils.isEmpty(this.codeTo)){
            throw new OrderException("The ZIP code not provided.");
        }

        return !this.codeFrom.matches(REGEX_ZIPCODE_VALIDATE) || !this.codeTo.matches(REGEX_ZIPCODE_VALIDATE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZipCodeVO zipCodeVO = (ZipCodeVO) o;
        return Objects.equals(codeFrom, zipCodeVO.codeFrom) && Objects.equals(codeTo, zipCodeVO.codeTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeFrom, codeTo);
    }

    public String getCodeFrom() {
        return codeFrom;
    }

    public String getCodeTo() {
        return codeTo;
    }
}
