package conc02.lock;

public class ReentrantLockDemo {

    public static void main(String[] args) {
        final Count count = new Count();



        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                count.put();
            }).start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                count.get();
            }).start();
        }
    }
}
