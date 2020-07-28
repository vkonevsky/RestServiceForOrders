package com.vlko.client_vaadin.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vlko.client_vaadin.model.User;
import com.vlko.client_vaadin.service.UserService;
import com.vlko.client_vaadin.service.utils.ServiceUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private static final String USERS_URL = "http://localhost:8070/api/v1/users/";
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<User> getAll(String username, String password) {
        String token = ServiceUtils.token(username, password);
        return getUsers(token);
    }

    @Override
    public List<User> getAll(String token) {
        return getUsers(token);
    }

    private List<User> getUsers(String token){
        try {
            String response = ServiceUtils.getRequest(token, USERS_URL);
            List<User> result = mapper.readValue(response, new TypeReference<List<User>>(){});
            return result;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}