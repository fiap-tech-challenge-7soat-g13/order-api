package com.fiap.challenge.tastefood.core.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderProduct {

    private Integer quantity;
    private BigDecimal price;
    private Product product;
    private Order order;

}