package com.lnsoft.futuretask;

import java.util.concurrent.*;

/**
 * 手动实现FutureTask：
 * 我们的futureTask要做的两件事：
 * （1）执行线程，把Callable通过线程提交
 * （2）在get()方法的时候进行阻塞
 * </p>
 * Created By Chr on 2019/1/30/0030.
 */
public class ChrFutureTask<V> implements Runnable, Future<V> {

    Callable<V> callable;//封装业务逻辑
    V result = null;//执行结果

    public ChrFutureTask(Callable<V> callable) {
        this.callable = callable;
    }

    //执行线程方法
    @Override
    public void run() {
        try {
            //当线程池执行submit的方法的时候，就是线程run方法执行的时候，就是去调用线程对应的执行方法call方法
            result = callable.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //如果线程执行成功之后，就唤醒get的等待，让其返回结果
        synchronized (this){
            this.notifyAll();//唤醒get方法，使其得以返回结果
        }
    }

    //阻塞方法
    @Override
    public V get() throws InterruptedException, ExecutionException {
        if (result != null) {
            return result;
        }
        //result==null,阻塞，等待结果
        synchronized (this) {
            this.wait();
        }
        return result;//返回结果
    }


    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }


    @Override
    public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }
}
