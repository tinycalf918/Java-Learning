package conc02.lock;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

public class TestFair {

    public static volatile int race = 0;
    public static ReentrantLock lock = new ReentrantLock(true);
    public static void increase(){
        lock.lock();
        race++;
        lock.unlock();
    }

    private static final int THREADS_COUNT = 20;

    public static void main(String[] args) {
        int count = Thread.activeCount();
        long now = System.currentTimeMillis();
        System.out.println(count);
        AtomicReference<Thread> sign = new AtomicReference<>();
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 100000; j++) {
                        increase();
                    }
                    System.out.println(race);
                }
            });
            threads[i].start();
        }

        while(Thread.activeCount() > count){
            Thread.yield();
        }

        System.out.println(lock.getClass().getName() + " ts = " + (System.currentTimeMillis() - now));
    }
}
