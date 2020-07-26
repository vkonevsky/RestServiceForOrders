package com.vlko.server.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Setter
@Getter
public class Order extends BaseEntity{
    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;

    @Override
    public String toString() {
        return "Order{" +
                "description='" + description + '\'' +
                ", user=" + user +
                '}';
    }
}
