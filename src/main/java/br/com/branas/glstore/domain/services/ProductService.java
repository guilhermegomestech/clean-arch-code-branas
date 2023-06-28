package br.com.branas.glstore.domain.services;

import br.com.branas.glstore.domain.entities.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    public boolean productsNotCanRepeat(List<Product> productList){
       return productList.stream().map(Product::getProductDescription).distinct().count() != productList.size();
    }
}
