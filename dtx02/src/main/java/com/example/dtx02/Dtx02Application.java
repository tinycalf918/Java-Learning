package com.example.dtx02;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
//@Import(TransactionConfiguration.class)
@MapperScan("com.example.dtx02.mapper")
public class Dtx02Application {

    public static void main(String[] args) {
        SpringApplication.run(Dtx02Application.class, args);
    }

}
