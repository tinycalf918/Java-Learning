package com.example.dtx02.mapper;

import com.example.dtx02.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {

    @Insert("insert into t_order(order_id, user_id, status) values(#{orderId}, #{userId}, #{status})")
    void insertOrder(Order order);

}
