package homework;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class HomeWork {

    public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法

        // 1.future task
//        FutureTask<Integer> futureTask = new FutureTask<>(() -> sum());
//        new Thread(futureTask).start();
//        int result = futureTask.get();

        // 2.thread pool future
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        Future<Integer> future = executorService.submit(() -> sum());
//        int result = future.get();

        // 3.completableFuture
//        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> sum());
//        int result = integerCompletableFuture.get();

        // 4.countdownlatch
//        CountDownLatch latch = new CountDownLatch(1);
//        CountDownLatchTask countDownLatchTask = new CountDownLatchTask(0, latch);
//        new Thread(countDownLatchTask).start();
//        latch.await();
//        int result  = countDownLatchTask.getResult();

        // 5.join
        final AtomicInteger atomicInteger = new AtomicInteger();
        Thread t1 = new Thread(()->{
            atomicInteger.set(sum());
        });
        t1.start();
        t1.join();
        int result = atomicInteger.get();
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        // 然后退出main线程
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }

    static class CountDownLatchTask implements Runnable{
        private int result;
        private CountDownLatch latch;

        public CountDownLatchTask(int result, CountDownLatch latch){
            this.result =result;
            this.latch = latch;
        }

        public int getResult(){
            return result;
        }
        @Override
        public void run() {
            result = sum();
            latch.countDown();
        }
    }
}
