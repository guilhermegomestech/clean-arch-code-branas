package br.com.branas.glstore.domain.services;

import br.com.branas.glstore.domain.entities.Product;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    public boolean productsNotCanRepeat(List<Product> productList){
       return productList.stream().map(Product::getProductDescription).distinct().count() != productList.size();
    }

    public boolean productHaveInvalidDimension(Product product){
        return validDimension(product.getProductHeight(), product.getProductLength(), product.getProductWidth());
    }

    private boolean validDimension(Integer...dimensionArray){
        return Arrays.stream(dimensionArray).anyMatch(dimension -> dimension < 0);
    }

    public boolean productHaveInvalidWeight(Product product){
        return Double.compare(product.getProductWeight(), 0) < 0;
    }
}
