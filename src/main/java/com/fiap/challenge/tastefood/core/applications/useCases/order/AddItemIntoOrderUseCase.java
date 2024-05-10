package com.fiap.challenge.tastefood.core.applications.useCases.order;

import com.fiap.challenge.tastefood.adapter.driven.infra.OrderGateway;
import com.fiap.challenge.tastefood.adapter.driven.infra.ProductGateway;
import com.fiap.challenge.tastefood.core.applications.dtos.Order;
import com.fiap.challenge.tastefood.core.domain.entities.OrderEntity;
import com.fiap.challenge.tastefood.core.domain.entities.ProductEntity;
import com.fiap.challenge.tastefood.core.domain.exception.OrderException;
import com.fiap.challenge.tastefood.core.domain.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Service
public class AddItemIntoOrderUseCase {

    private final OrderGateway gateway;
    private final ProductGateway productGateway;
    private final OrderMapper orderMapper;

    @Autowired
    public AddItemIntoOrderUseCase(OrderGateway orderGateway,
                                   ProductGateway productGateway,
                                   OrderMapper orderMapper) {
        this.gateway = orderGateway;
        this.productGateway = productGateway;
        this.orderMapper = orderMapper;
    }

    public Order execute(Long orderId, Long productId) throws OrderException {
        Optional<ProductEntity> product = productGateway.findById(productId);
        OrderEntity order = gateway.findbyId(orderId);
        if (product.isPresent()) {
            if (order.getProducts() == null)
                order.setProducts(new ArrayList<>());

            order.getProducts().add(product.get());
            return this.orderMapper.fromOrderEntityToOrderDTO(this.gateway.update(order));
        } else {
            throw new OrderException("Produto não existe!");
        }
    }

}