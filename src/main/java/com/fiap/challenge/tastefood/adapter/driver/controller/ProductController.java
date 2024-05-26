package com.fiap.challenge.tastefood.adapter.driver.controller;

import com.fiap.challenge.tastefood.core.application.dto.ProductRequest;
import com.fiap.challenge.tastefood.core.application.dto.ProductResponse;
import com.fiap.challenge.tastefood.core.application.useCase.product.ProductCreateUseCase;
import com.fiap.challenge.tastefood.core.application.useCase.product.ProductListUseCase;
import com.fiap.challenge.tastefood.core.application.useCase.product.ProductRemoveUseCase;
import com.fiap.challenge.tastefood.core.application.useCase.product.ProductUpdateUseCase;
import com.fiap.challenge.tastefood.core.domain.valueObject.ProductCategory;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProductController {

    private final ProductCreateUseCase productCreateUseCase;
    private final ProductUpdateUseCase productUpdateUseCase;
    private final ProductRemoveUseCase productRemoveUseCase;
    private final ProductListUseCase productListUseCase;

    @PostMapping(path = "/product")
    public void create(@RequestBody ProductRequest product) {
        productCreateUseCase.execute(product);
    }

    @PutMapping(path = "/product/{id}")
    public void update(@PathVariable Long id, @RequestBody ProductRequest product) {
        productUpdateUseCase.execute(id, product);
    }

    @DeleteMapping(path = "/product/{id}")
    public void remove(@PathVariable Long id) {
        productRemoveUseCase.execute(id);
    }

    @GetMapping(path = "/product")
    public List<ProductResponse> list(@RequestParam(required = false) ProductCategory category) {
        return productListUseCase.execute(category);
    }

}
