package com.vlko.server.rest;

import com.vlko.server.dto.OrderDto;
import com.vlko.server.dto.UserDto;
import com.vlko.server.model.Order;
import com.vlko.server.model.User;
import com.vlko.server.service.OrderService;
import com.vlko.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/admin/")
public class AdminRestControllerV1 {

    private final OrderService orderService;
    private final UserService userService;

    @Autowired
    public AdminRestControllerV1(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @DeleteMapping(value = "user/{id}")
    public ResponseEntity removeUserById(@PathVariable(name = "id") Long id) {
        userService.delete(id);
        return ResponseEntity.ok("SUCCESS");
    }

    @DeleteMapping(value = "order/{id}")
    public ResponseEntity removeOrderById(@PathVariable(name = "id") Long id) {
        orderService.delete(id);
        return ResponseEntity.ok("SUCCESS");
    }

    @PostMapping("order")
    public ResponseEntity updateOrder(@RequestBody OrderDto orderDto) {
        Order order = orderDto.toOrder();
        OrderDto response = OrderDto.fromOrder(orderService.update(order));
        return ResponseEntity.ok(response);
    }

    @PostMapping("user")
    public ResponseEntity updateOrder(@RequestBody UserDto userDto) {
        User user = userDto.toUser();
        UserDto response = UserDto.fromUser(userService.update(user));
        return ResponseEntity.ok(response);
    }
}
