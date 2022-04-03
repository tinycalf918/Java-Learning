package io.github.acezhuang.fx;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student{


    private String id;
    private String name;


    public static Student create(String id, String name){
        return new Student(id,name);
    }

    public void print() {
        System.out.println(this.toString());
    }

}
