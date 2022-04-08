package io.github.acezhuang.java8;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class StreamDemo2 {

    public static void main(String[] args) {

        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");

        long emptyCount = strings.stream().filter(string -> string.isEmpty()).count();
        System.out.println(emptyCount);

        List<String> notEmpty = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        System.out.println(notEmpty);

        String join = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(","));
        System.out.println(join);

        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        List<Integer> multiply = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        System.out.println(multiply);

        List<Integer> integers = Arrays.asList(1,2,13,4,15,6,17,8,19);
        IntSummaryStatistics intSummaryStatistics = integers.stream().mapToInt(x -> x).summaryStatistics();
        System.out.println(intSummaryStatistics.getMax());
        System.out.println(intSummaryStatistics.getAverage());
        System.out.println(intSummaryStatistics.getCount());
        System.out.println(intSummaryStatistics.getSum());

        new Random().ints().limit(10).sorted().forEach(System.out::println);

    }
}
