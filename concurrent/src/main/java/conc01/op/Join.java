package conc01.op;

public class Join {

    public static void main(String[] args) {
        Object oo = new Object();
        MyThread t1 = new MyThread("t1");
//        oo = t1;
        t1.setOo(oo);
        t1.start();

        synchronized (oo) {
            for (int i = 0; i < 100; i++) {
                if (i == 20) {
                    try {
//                        oo.wait(0);
                        t1.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + "--" + i);
            }
        }
    }
}

class MyThread extends Thread {

    private String name;
    private Object oo;

    public void setOo(Object oo) {
        this.oo = oo;
    }

    public MyThread(String name) {
        this.name = name;
    }

    public void run() {
        synchronized (oo) {
            for (int i = 0; i < 100; i++) {
                System.out.println(name + i);
            }
        }
    }
}
