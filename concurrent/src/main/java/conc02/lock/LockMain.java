package conc02.lock;

public class LockMain {

    public static void main(String[] args) {
        Count3 count3 = new Count3();
        ThreadA threadA = new ThreadA(count3);
        threadA.setName("A");
        threadA.start();

        ThreadB threadB = new ThreadB(count3);
        threadB.setName("B");
        threadB.start();
    }
}
