package com.vlko.client_vaadin.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vlko.client_vaadin.model.User;
import com.vlko.client_vaadin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route("users")
public class UsersView extends VerticalLayout {
    private Grid<User> grid= new Grid<>(User.class);

    @Autowired
    private UserService userService;
    private String token;


    public UsersView() {

        Dialog dialog = new Dialog();

        TextField username = new TextField("Username");
        PasswordField password = new PasswordField("Password");
        Button button = new Button("Enter", event -> {
            List<User> users = userService.getAll(username.getValue(), password.getValue());
            grid.setItems(users);
            add(grid);
            dialog.close();
        });


        VerticalLayout vertical = new VerticalLayout ();

        vertical.add("Authorization");
        vertical.add(username);
        vertical.add(password);
        vertical.add(button);

        dialog.add(vertical);

        dialog.open();
    }
}
