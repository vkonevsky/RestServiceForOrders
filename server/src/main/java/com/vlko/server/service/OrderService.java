package com.vlko.server.service;

import com.vlko.server.model.Order;
import com.vlko.server.model.User;

import java.util.List;

public interface OrderService {

    Order register(Order order);

    Order update(Order order);

    List<Order> getAll();

    Order findById(Long id);

    void delete(Long id);
}
