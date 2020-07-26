package com.vlko.client_vaadin.service;

import com.vlko.client_vaadin.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll(String username, String password);
    List<User> getAll(String token);
}
