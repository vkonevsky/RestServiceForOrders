package com.vlko.client_vaadin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {
    private Long id;
    private String description;
    private Status status;
}
