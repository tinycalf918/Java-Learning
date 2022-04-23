package com.example.dtx02.service;

import com.example.dtx02.entity.Order;

public interface OrderService {

    void insertOrder(Order order);

    void addOrderBatch();
}
