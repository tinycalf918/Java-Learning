package conc01;

public class Runner2 implements Runnable{

    @Override
    public void run() {
        for(int i=0; i<100; i++){
            System.out.println("进入Runner2," + i);
        }

        boolean result = Thread.currentThread().isInterrupted();
        boolean result1 = Thread.interrupted();
        boolean result2 = Thread.currentThread().isInterrupted();
        System.out.println("Runner2.run result ===>" + result);
        System.out.println("Runner2.run result1 ===>" + result1);
        System.out.println("Runner2.run result3 ===>" + result2);
    }
}
