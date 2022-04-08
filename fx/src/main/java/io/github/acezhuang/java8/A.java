package io.github.acezhuang.java8;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Builder
@Setter
@Getter
public class A {
    private int age;
    private String name;

    public void test(){
        log.info("test");
    }
}
