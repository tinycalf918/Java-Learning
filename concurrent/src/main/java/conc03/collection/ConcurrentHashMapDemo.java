package conc03.collection;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentHashMapDemo {

    public static void main(String[] args) {
        demo1();
    }

    public static void demo1(){
        final ConcurrentHashMap<String, AtomicInteger> count = new ConcurrentHashMap<>();
        CountDownLatch endLatch = new CountDownLatch(2);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                AtomicInteger oldValue;
                for (int i = 0; i < 5; i++) {
                    oldValue = count.get("a");
                    if (null == oldValue) {
                        AtomicInteger zeroValue = new AtomicInteger(0);
                        oldValue = count.putIfAbsent("a", zeroValue);
                        System.out.println(oldValue);
                        if (null == oldValue) {
                            oldValue = zeroValue;
                            System.out.println("2");
                        }
                    }
                    oldValue.incrementAndGet();
                }
//                endLatch.countDown();
            }
        };

        Thread t1 = new Thread(runnable);
        t1.start();
        Thread t2 = new Thread(runnable);
        t2.start();



        try {
            t1.join();
            t2.join();
//            endLatch.await();
            System.out.println(count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
