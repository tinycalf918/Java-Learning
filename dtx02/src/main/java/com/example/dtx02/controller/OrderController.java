package com.example.dtx02.controller;

import com.example.dtx02.entity.Order;
import com.example.dtx02.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class OrderController {

    @Autowired
    OrderService orderService;
    
    @RequestMapping("/order/add")
    String addOrder() {
        Order order1 = new Order(2L, 2L, "ok");
        orderService.insertOrder(order1);
        Order order2 = new Order(3L, 1L, "ok");
        orderService.insertOrder(order2);
        Order order3 = new Order(4L, 3L, "ok");
        orderService.insertOrder(order3);
        Order order4 = new Order(5L, 4L, "ok");
        orderService.insertOrder(order4);
        return "OK";
    }

    @RequestMapping("/order/addBatch")
    String addOrderBatch() {
        orderService.addOrderBatch();
        return "OK";
    }

}