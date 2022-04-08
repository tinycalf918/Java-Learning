package io.github.acezhuang.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class ParallelDemo {
    public static void main(String[] args) {
        testSerialize();
        testParallel();
    }

    private static void testSerialize(){
        List<String> list = new ArrayList<>(5000000);
        for (int i = 0; i < 5000000; i++) {
            list.add(UUID.randomUUID().toString());
        }
        System.out.println("开始排序");
        long start = System.nanoTime();
        list.stream().sorted().count();

        long end = System.nanoTime();

        long time = TimeUnit.MILLISECONDS.toMillis(end - start);
        System.out.println("耗时：" + time);

    }

    private static void testParallel(){
        List<String> list = new ArrayList<>(5000000);
        for (int i = 0; i < 5000000; i++) {
            list.add(UUID.randomUUID().toString());
        }
        System.out.println("开始排序");
        long start = System.nanoTime();
        list.parallelStream().sorted().count();

        long end = System.nanoTime();

        long time = TimeUnit.MILLISECONDS.toMillis(end - start);
        System.out.println("耗时：" + time);

    }
}
