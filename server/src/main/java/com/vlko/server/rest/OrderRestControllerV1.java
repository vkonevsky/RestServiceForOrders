package com.vlko.server.rest;

import com.vlko.server.dto.OrderDto;
import com.vlko.server.model.Order;
import com.vlko.server.model.User;
import com.vlko.server.service.OrderService;
import com.vlko.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/v1/orders/")
public class OrderRestControllerV1 {
    private final OrderService orderService;
    private final UserService userService;

    @Autowired
    public OrderRestControllerV1(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable(name = "id") Long id) {
        Order order = orderService.findById(id);

        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        OrderDto result = OrderDto.fromOrder(order);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "user/{id}")
    public ResponseEntity<List<OrderDto>> getOrderForUserById(@PathVariable(name = "id") Long id) {

        User user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Set<Order> orders = user.getOrders();

        if (orders == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<OrderDto> result = new ArrayList<>();

        orders.forEach(order -> {
            result.add(OrderDto.fromOrder(order));
        });

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<OrderDto>> getUserById() {
        List<Order> orders = orderService.getAll();

        if (orders == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<OrderDto> result = new ArrayList<>();

        orders.forEach(order -> {
            result.add(OrderDto.fromOrder(order));
        });

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity register(@RequestBody OrderDto orderDto) {
        Order order = orderDto.toOrder();
        OrderDto response = OrderDto.fromOrder(orderService.register(order));
        return ResponseEntity.ok(response);
    }
}
