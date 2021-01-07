package ru.itis.hateoas.simplehateoasservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.hateoas.simplehateoasservice.models.Product;
import ru.itis.hateoas.simplehateoasservice.repositories.ProductRepository;

@Component
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product updatePrice(Long productId, Integer price) {
        Product product = productRepository.findById(productId)
                .orElseThrow(IllegalArgumentException::new);
        product.updatePrice(price);
        productRepository.save(product);
        return product;
    }
}
