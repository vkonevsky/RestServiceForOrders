package com.vlko.server.rest;

import com.vlko.server.dto.UserDto;
import com.vlko.server.model.User;
import com.vlko.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/users/")
public class UserRestControllerV1 {
    private final UserService userService;

    @Autowired
    public UserRestControllerV1(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserDto result = UserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<UserDto>> getUserById() {
        List<User> users = userService.getAll();

        if (users == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<UserDto> result = new ArrayList<>();

        users.forEach(user -> {
            result.add(UserDto.fromUser(user));
        });

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity register(@RequestBody User user) {
        UserDto response = UserDto.fromUser(userService.register(user));
        return ResponseEntity.ok(response);
    }
}
