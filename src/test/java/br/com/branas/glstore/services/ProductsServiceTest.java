package br.com.branas.glstore.services;

import br.com.branas.glstore.domain.entities.Product;
import br.com.branas.glstore.domain.services.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class ProductsServiceTest {

    @InjectMocks
    private ProductService productService;

    @Test
    public void testProductsNotCanRepeat(){
        List<Product> productList = new ArrayList<>();

        Product productOne = new Product();
        productOne.setProductDescription("Batedeira");
        productOne.setProductPrice(new BigDecimal(250));

        Product productTwo = new Product();
        productTwo.setProductDescription("Batedeira");
        productTwo.setProductPrice(new BigDecimal(560));

        productList.addAll(Arrays.asList(productOne, productTwo));

        assertTrue(productService.productsNotCanRepeat(productList), "The products list not contains repeated elements.");
    }
}
