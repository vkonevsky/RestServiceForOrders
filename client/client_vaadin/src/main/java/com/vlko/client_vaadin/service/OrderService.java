package com.vlko.client_vaadin.service;

import com.vlko.client_vaadin.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAll(String username, String password);
    List<Order> getAll(String token);
}
