package io.github.acezhuang.java8;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class StreamDemo {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(4, 3, 2, 1, 5, 2, 7,8);
        print(list);

        Optional<Integer> first = list.stream().findFirst();
        print(first);

        System.out.println(first.map(i -> i * 100).orElse(100));

        int sum = list.stream().filter(i -> i < 5).distinct().reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        LinkedHashMap<Integer, Integer> map = list.stream().parallel().collect(Collectors.toMap(a -> a, a -> a + 1, (a, b) -> b, LinkedHashMap::new));
        print(map);

        map.forEach((k, v) -> System.out.println(k + "=" + v));

        List<Integer> list1 = map.entrySet().parallelStream().map(e -> e.getKey() + e.getValue()).collect(Collectors.toList());
        print(list1);

    }

    private static void print(Object obj){
        System.out.println(JSON.toJSONString(obj));
    }

}
