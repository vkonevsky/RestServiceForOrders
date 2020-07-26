package com.vlko.server.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vlko.server.model.Order;
import com.vlko.server.model.Status;
import com.vlko.server.model.User;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDto {
    private Long id;
    private UserDto user;
    private String description;
    private Status status;
    private Date updated;
    private Date created;

    public Order toOrder(){
        Order order = new Order();
        order.setId(id);
        order.setUser(user.toUser());
        order.setDescription(description);
        order.setStatus(status);
        order.setUpdated(updated);
        order.setCreated(created);
        return order;
    }

    public static OrderDto fromOrder(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setUser(UserDto.fromUser(order.getUser()));
        orderDto.setDescription(order.getDescription());
        orderDto.setStatus(order.getStatus());
        orderDto.setCreated(order.getCreated());
        orderDto.setUpdated(order.getUpdated());
        return orderDto;
    }

}
