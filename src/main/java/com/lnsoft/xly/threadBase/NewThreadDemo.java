package com.lnsoft.xly.threadBase;


/**
 * 线程运行本质只有两个方法,看Thread源码注解
 * <p>
 * Created by Chryl on 2019/8/22.
 */
public class NewThreadDemo {

    //1
    private static class UserThread extends Thread {
        @Override
        public void run() {
            System.out.println("thread");
        }
    }

    //2
    private static class UserRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("runnable");
        }
    }


    public static void main(String[] args) {
        UserThread userThread = new UserThread();
        userThread.start();
        //第二次start会抛异常
        userThread.start();

        //
        UserRunnable userRunnable = new UserRunnable();
        Thread thread = new Thread(userRunnable);
        thread.start();



    }
}
