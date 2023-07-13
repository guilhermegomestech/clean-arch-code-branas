package br.com.branas.glstore.services;

import br.com.branas.glstore.application.entities.Product;
import br.com.branas.glstore.application.services.ProductService;
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

    @Test
    public void testDimensionProductIsNegative(){
        Product productOne = new Product();
        productOne.setProductHeight(-100.0);
        productOne.setProductLength(-100.0);
        productOne.setProductWidth(-100.0);

        assertTrue(productService.productHaveInvalidDimension(productOne), "The dimension is valid");
    }

    @Test
    public void testWeightProductIsNegative(){
        Product productOne = new Product();
        productOne.setProductWeight(-100.0);

        assertTrue(Double.compare(productOne.getProductWeight(), 0) < 0, "The weight is valid");
    }
}
