package conc01;

public class ThreadCount {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getThreadGroup().getParent());
        Thread.currentThread().getThreadGroup().getParent().list();
    }
}
