package conc02.lock;

public class ReentrantReadWriteLockDemo {

    public static void main(String[] args) {

        final Count2 count = new Count2();

        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    count.get();
                }
            }).start();
        }

        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                count.put();
            }).start();
        }
    }
}
