package com.lnsoft.xly.threadBase;

/**
 * 为什么没有中断线程:因为抛出了InterruptedException中断异常时,中断标志位也会有true变为false
 * <p>
 * Created by Chryl on 2019/8/22.
 */
public class ThreadDemo2 {
    private static class DemoThread2 extends Thread {

        public DemoThread2(String name) {
            super(name);
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println(name + ": [isInterrupted] :" + isInterrupted());
            while (!isInterrupted()) {//实例方法

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println(name + ":" + isInterrupted());
                    /**
                     *出现中断异常,再手动调用中断:自行中断
                     */
                    interrupt();
                    e.printStackTrace();
                }
                System.out.println(name + ": is running");
//                System.out.println(name + ": [isInterrupted] :" + isInterrupted());
            }
            System.out.println(name + ": [isInterrupted] :" + isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DemoThread2 demoThread2 = new DemoThread2("myThread");
        demoThread2.start();
        demoThread2.sleep(450);
        demoThread2.interrupt();

    }
}
