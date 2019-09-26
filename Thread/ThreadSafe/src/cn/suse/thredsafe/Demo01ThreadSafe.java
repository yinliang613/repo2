package cn.suse.thredsafe;

public class Demo01ThreadSafe {
    public static void main(String[] args) {
        RunnableImpl2 run  = new RunnableImpl2();
        Thread t1 = new Thread(run);
        Thread t2 = new Thread(run);
        Thread t3 = new Thread(run);
        t1.start();
        t2.start();
        t3.start();


    }

}
