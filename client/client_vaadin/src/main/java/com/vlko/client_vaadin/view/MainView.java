package com.vlko.client_vaadin.view;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route("")
public class MainView extends VerticalLayout {

    public MainView() {
        Text text = new Text("Please use /users or /orders for get data");
        add(text);
    }
}
