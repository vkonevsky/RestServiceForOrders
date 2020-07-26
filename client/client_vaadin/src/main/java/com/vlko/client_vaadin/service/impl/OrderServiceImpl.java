package com.vlko.client_vaadin.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vlko.client_vaadin.model.Order;
import com.vlko.client_vaadin.service.OrderService;
import com.vlko.client_vaadin.service.utils.ServiceUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private static final String ORDERS_URL = "http://localhost:8075/api/v1/orders/";
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<Order> getAll(String username, String password) {
        String token = ServiceUtils.token(username, password);
        return getUsers(token);
    }

    @Override
    public List<Order> getAll(String token) {
        return getUsers(token);
    }

    private List<Order> getUsers(String token){
        try {
            String response = ServiceUtils.getRequest(token, ORDERS_URL);
            List<Order> result = mapper.readValue(response, new TypeReference<List<Order>>(){});
            return result;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
