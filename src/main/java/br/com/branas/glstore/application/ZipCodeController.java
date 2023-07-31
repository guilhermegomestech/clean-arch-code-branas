package br.com.branas.glstore.application;

import br.com.branas.glstore.domain.entities.ZipCode;
import br.com.branas.glstore.domain.services.ZipCodeService;
import br.com.branas.glstore.infrastructure.exceptions.OrderException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/zipcode")
public class ZipCodeController {

    private ZipCodeService zipCodeService;

    public ZipCodeController(ZipCodeService zipCodeService) {
        this.zipCodeService = zipCodeService;
    }

    @PostMapping()
    public ResponseEntity<?> saveZipCode(@RequestBody ZipCode zipCode){
        try{
            return ResponseEntity.ok(this.zipCodeService.save(zipCode));
        } catch (OrderException oe){
            return ResponseEntity.unprocessableEntity().body(oe.getMessage());
        }
    }

    @PostMapping("/list")
    public ResponseEntity<?> saveZipCode(@RequestBody List<ZipCode> zipCode){
        try{
            return ResponseEntity.ok(this.zipCodeService.save(zipCode));
        } catch (OrderException oe){
            return ResponseEntity.unprocessableEntity().body(oe.getMessage());
        }
    }
}
