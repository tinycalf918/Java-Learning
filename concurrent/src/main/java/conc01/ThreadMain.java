package conc01;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadMain {

    public static void main(String[] args) {

        ThreadA threadA = new ThreadA();
        threadA.start();

        System.out.println("这是主线程");
        ThreadB threadB = new ThreadB();
        new Thread(threadB).start();
        System.out.println("这是主线程");
        ThreadC threadC = new ThreadC();
        FutureTask<String> futureTask = new FutureTask<>(threadC);
        new Thread(futureTask).start();
        System.out.println("这是主线程 begin");

        try {
            System.out.println("返回结果：" + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
