package conc03.threadlocal;

public class ThreadLocalDemo {

    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>(){
        public Integer initialValue() {
            return 0;
        }
    };

    public ThreadLocal<Integer> getThreadLocal(){
        return seqNum;
    }

    public int getNextNum(){
        seqNum.set(seqNum.get() + 1);
        return seqNum.get();
    }

    public static void main(String[] args) {
        ThreadLocalDemo threadLocalDemo = new ThreadLocalDemo();

        SnThread snThread1 = new SnThread(threadLocalDemo);
        SnThread snThread2 = new SnThread(threadLocalDemo);
        SnThread snThread3 = new SnThread(threadLocalDemo);

        snThread1.start();
        snThread2.start();
        snThread3.start();
    }

    private static class SnThread extends Thread{
        private ThreadLocalDemo sn;

        public SnThread(ThreadLocalDemo sn){
            this.sn = sn;
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                System.out.println("thread[" + Thread.currentThread().getName() + "] ---> sn [" + sn.getNextNum() + "]");
            }
            sn.getThreadLocal().remove();
        }
    }
}
