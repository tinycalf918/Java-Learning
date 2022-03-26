package conc01;

public class DaemonThread {

    public static void main(String[] args) throws InterruptedException {

        Runnable task = () -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("当前线程为" + Thread.currentThread().getName());
        };

        Thread thread = new Thread(task);
        thread.setName("task1");
        thread.setDaemon(false);
        thread.start();

//        Thread.sleep(5000);
    }
}
