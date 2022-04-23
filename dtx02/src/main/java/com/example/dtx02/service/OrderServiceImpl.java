package com.example.dtx02.service;

import com.example.dtx02.entity.Order;
import com.example.dtx02.mapper.OrderMapper;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.apache.shardingsphere.transaction.core.TransactionTypeHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper; //DAO  // Repository


    @Override
    public void insertOrder(Order order) {
        orderMapper.insertOrder(order);
    }

    @Override
    @ShardingTransactionType(TransactionType.XA)
    @Transactional
    public void addOrderBatch() {
        // ds0 可以成功插入
        Order order1 = new Order(10L, 2L, "ok");
        insertOrder(order1);
        // ds1 可以成功插入
        Order order2 = new Order(11L, 1L, "ok");
        insertOrder(order2);
        // ds0 可以成功插入
        Order order3 = new Order(12L, 3L, "ok");
        insertOrder(order3);
        // ds1 这条记录触发主键冲突报错 通过xa可实现回滚
        Order order4 = new Order(9L, 4L, "ok");
        insertOrder(order4);
    }
}
