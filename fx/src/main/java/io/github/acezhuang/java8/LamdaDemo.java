package io.github.acezhuang.java8;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LamdaDemo {
    public static void main(String[] args) {

        ArrayList<IndexResult> list = new ArrayList<>();
        list.add(new IndexResult("20220101", "111", "111"));
        list.add(new IndexResult("20220102", "111", "111"));
        list.add(new IndexResult("20220103", "111", "111"));
        list.add(new IndexResult("20220103", "111", "222"));
        list.add(new IndexResult("20220101", "222", "222"));
        list.add(new IndexResult("20220102", "222", "222"));
        list.add(new IndexResult("20220103", "222", "222"));
        list.add(new IndexResult("20220101", "333", "333"));
        list.add(new IndexResult("20220102", "333", "333"));


        Map<String, Map<String, IndexResult>> collect = list.stream().
                collect(Collectors.groupingBy(IndexResult::getIndexNo, Collectors.toMap(i -> i.getDate().substring(0, 4), Function.identity(), (x1, x2) -> x1)));

        System.out.println(collect);
    }
}
