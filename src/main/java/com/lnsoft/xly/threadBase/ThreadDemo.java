package com.lnsoft.xly.threadBase;

/**
 * 如何中断线程
 * <p>
 * Created by Chryl on 2019/8/22.
 */
public class ThreadDemo {
    private static class DemoThread extends Thread {
        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println(name + ": [isInterrupted] :" + isInterrupted());
//            while (!isInterrupted()) {//实例方法
            while (!Thread.interrupted()) {//静态方法
                System.out.println(name + ": is running");
                System.out.println(name + ": [isInterrupted] :" + isInterrupted());
            }
            /**
             * 实例方法打印的为true
             * 静态方法打印的为false
             */
            System.out.println(name + ": [isInterrupted] :" + isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DemoThread demoThread = new DemoThread();
        demoThread.start();
        demoThread.sleep(10);
        demoThread.interrupt();
    }
}
