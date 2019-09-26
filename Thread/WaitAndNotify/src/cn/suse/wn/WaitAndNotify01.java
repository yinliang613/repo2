package cn.suse.wn;

public class WaitAndNotify01 {
    public static void main(String[] args) {
        //创建一个锁对象
        Object obj = new Object();
        //创建一个顾客线程，吃包子
        new Thread(() -> {
            while (true) {
                synchronized (obj) {
                    System.out.println("顾客来了,通知老板做包子");
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("顾客吃到了包子，开心！");
                    System.out.println("---------------------");

                }
            }
        }).start();
        //创建一个老板线程，做包子
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    //花了五秒做包子
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (obj) {
                        System.out.println("老板做好了包子");
                        obj.notify();

                    }
                }
            }
        }.start();

    }
}
