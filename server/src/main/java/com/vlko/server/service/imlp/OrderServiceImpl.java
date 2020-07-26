package com.vlko.server.service.imlp;

import com.vlko.server.model.Order;
import com.vlko.server.model.Status;
import com.vlko.server.model.User;
import com.vlko.server.repository.OrderRepository;
import com.vlko.server.repository.UserRepository;
import com.vlko.server.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Order register(Order order) {
        User user = userRepository.findById(order.getUser().getId()).orElse(null);
        if (user == null) {
            log.error("IN register - no user found with parameters: {}", user);
            return null;
        }
        order.setUser(user);
        orderRepository.save(order);
        log.info("IN register - order: {} successfully registered", order);
        return order;
    }

    @Override
    public Order update(Order order) {
        Order orderFromDb = orderRepository.findById(order.getId()).orElse(null);

        if (orderFromDb == null) {
            log.warn("IN update - no order found with parameters: {}", order);
            register(order);
            return null;
        }

        orderFromDb.setDescription(order.getDescription());
        orderFromDb.setUser(order.getUser());
        orderFromDb.setStatus(order.getStatus());

        Order updateOrder = orderRepository.save(orderFromDb);

        log.info("IN update - order: {} successfully updated", updateOrder);

        return updateOrder;
    }

    @Override
    public List<Order> getAll() {
        List<Order> result = orderRepository.findAll();
        log.info("IN getAll - {} orders found", result.size());
        return result;
    }

    @Override
    public Order findById(Long id) {
        Order result = orderRepository.findById(id).orElse(null);

        if (result == null) {
            log.warn("IN findById - no order found by id: {}", id);
            return null;
        }

        log.info("IN findById - order: {} found by id: {}", result);
        return result;
    }

    @Override
    public void delete(Long id) {
        Order order = orderRepository.findById(id).orElse(null);

        if(order == null) {
            log.warn("IN delete - order with id: {} not found", id);
        }

        order.setStatus(Status.DELETED);
        orderRepository.save(order);
        log.info("IN delete - order with id: {} successfully deleted", id);
    }
}
