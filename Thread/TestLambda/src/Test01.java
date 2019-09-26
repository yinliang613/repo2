public class Test01 {
    public static void main(String[] args) {
        //使用匿名内部类的方式创建线程
        new Thread(new Runnable(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"创建线程");
            }
        }).start();
        //使用Lambda表达式创建线程
        new Thread(()-> System.out.println(Thread.currentThread().getName()+"创建线程")).start();
    }
}
