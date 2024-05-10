package com.fiap.challenge.tastefood.core.applications.useCases.product;

import com.fiap.challenge.tastefood.core.applications.dtos.Product;
import com.fiap.challenge.tastefood.core.domain.entities.ProductEntity;
import com.fiap.challenge.tastefood.core.domain.exception.OrderException;
import com.fiap.challenge.tastefood.core.domain.mapper.ProductMapper;
import com.fiap.challenge.tastefood.core.domain.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CreateProductUseCase {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public CreateProductUseCase(ProductRepository productRepository,
                                ProductMapper productMapper){
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public Long execute(Product product) throws OrderException {
        if (productRepository.findByDescription(product.getDescription()).isPresent())
            throw new OrderException(String.format("Produto: %s já cadastrado!", product.getDescription()));
        else {
            ProductEntity productEntity = productRepository.save(this.productMapper.toProductEntity(product));
            log.info("Produto cadastrado com sucesso: {}", productEntity.getDescription());
            return productEntity.getId();
        }
    }
}