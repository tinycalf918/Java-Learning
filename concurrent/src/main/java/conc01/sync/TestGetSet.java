package conc01.sync;

public class TestGetSet {
    public static void main(String[] args) throws Exception {

        final SetGet s = new SetGet();
        Thread t = new Thread(){
            @Override
            public void run() {
                try {
                    int i = s.get();
                    System.out.println(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
        long start = System.currentTimeMillis();
        s.set(10);
        System.out.println(" ... " + ( System.currentTimeMillis() - start ));

    }


    public static class SetGet {

        int a = 0;
        public synchronized void set(int v) throws Exception {
            System.out.println(Thread.currentThread().getName() +" setting " +v);
            Thread.sleep(1000);
            a = v;
            System.out.println(Thread.currentThread().getName() +" set " +v);
        }

        public synchronized int get() throws Exception {
            System.out.println(Thread.currentThread().getName() +" getting ");
            Thread.sleep(10000);
            System.out.println(Thread.currentThread().getName() + " get ");
            return a;
        }

    }
}
