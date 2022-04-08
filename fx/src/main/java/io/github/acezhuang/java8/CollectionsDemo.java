package io.github.acezhuang.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionsDemo {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(4,2,3,5,1,2,2,7,6);
        Collections.sort(list);
        print(list);
        Collections.reverse(list);
        print(list);
        Collections.shuffle(list);
        print(list);

        int frequency = Collections.frequency(list, 2);
        System.out.println(frequency);
        Collections.fill(list, 1);
        print(list);

        List<Integer> integers = Collections.singletonList(1);
        System.out.println(integers);
    }

    private static void print(List<Integer> list){
//        System.out.println(String.join(":", list.stream().map(i -> i.toString()).collect(Collectors.toList())));
        System.out.println(list.stream().map(i -> i.toString()).collect(Collectors.joining(",")));
    }
}
