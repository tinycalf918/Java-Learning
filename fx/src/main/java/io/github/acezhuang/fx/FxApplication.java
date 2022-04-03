package io.github.acezhuang.fx;

import io.github.acezhuang.fx.props.StudentProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FxApplication {

    public static void main(String[] args) {
        SpringApplication.run(FxApplication.class, args);
    }

    @Autowired
    Student student;
    @Autowired
    StudentProperties properties;

    @Bean
    public void print(){
        System.out.println(properties);
    }
}
