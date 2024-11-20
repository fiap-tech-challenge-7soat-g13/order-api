package com.fiap.challenge.tastefood.app.adapter.output.persistence.mapper;

import com.fiap.challenge.tastefood.app.adapter.output.persistence.entity.OrderEntity;
import com.fiap.challenge.tastefood.core.domain.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {OrderProductMapper.class})
public interface OrderMapper {

    @Mapping(source = "customer.id", target = "customerId")
    OrderEntity toOrderEntity(Order order);

    @Mapping(source = "customerId", target = "customer.id")
    Order toOrder(OrderEntity orderEntity);

    @Mapping(source = "customerId", target = "customer.id")
    List<Order> toOrder(List<OrderEntity> orderEntities);

}
