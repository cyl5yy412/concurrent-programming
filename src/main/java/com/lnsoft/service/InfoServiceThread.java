package com.lnsoft.service;

import com.alibaba.fastjson.JSONObject;
import com.lnsoft.test.TestFastJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

/**
 * 并发编程：
 * 使用线程和线程池：Callable
 * 使用自己手写的FutureTask更快
 * Created By Chr on 2019/1/30/0030.
 */
@Service
public class InfoServiceThread {
    @Autowired
    private TestFastJson testFastJson;

    //线程池
    ExecutorService executorService = Executors.newFixedThreadPool(2);

    public String getInfoByThread() {
        //user信息
        Callable<JSONObject> queryUserInfo = new Callable<JSONObject>() {
            @Override
            public JSONObject call() throws Exception {
                //调用接口
                String infoUser = testFastJson.getUserInfo();
                JSONObject infoUserJSON = JSONObject.parseObject(infoUser);
                return infoUserJSON;
            }
        };
        //emp信息
        Callable<JSONObject> queryEmpInfo = new Callable<JSONObject>() {
            @Override
            public JSONObject call() throws Exception {
                //调用接口
                String infoEmp = testFastJson.getEmpInfo();
                JSONObject infoEmpJSON = JSONObject.parseObject(infoEmp);
                return infoEmpJSON;
            }
        };

        /**
         * 使用sun提供的FutureTask
         */
        FutureTask<JSONObject> infoEmpJsonTask = new FutureTask<JSONObject>(queryEmpInfo);
        FutureTask<JSONObject> infoUserJsonTask = new FutureTask<JSONObject>(queryUserInfo);
        /**
         * 使用自定义的FutureTask速度更快，因为没有乱七八糟的东西了
         */
//        ChrFutureTask<JSONObject> infoEmpJsonTask = new ChrFutureTask<JSONObject>(queryEmpInfo);
//        ChrFutureTask<JSONObject> infoUserJsonTask = new ChrFutureTask<JSONObject>(queryUserInfo);


        //如果使用线程，尽量使用线程池
//        new Thread(infoEmpJsonTask).start();
//        new Thread(infoUserJsonTask).start();
        //new Thread(Runnable).start();

        /**
         * 使用线程池，如果使用线程，就是用线程池：
         * 需要注意的问题：
         *      （1）线程池是的初始化和线程的初始化阶段不同，线程是使用的时候才进行创建对象，线程池则在准备阶段进行创建对象
         *
         *      （2）线程池如果不用的时候会造成资源的浪费，注意优化★★★★★★
         *
         *      （3）执行submit方法的时候，就是对应的线程的执行方法，此处就是调用call方法
         */
        executorService.submit(infoEmpJsonTask);
        executorService.submit(infoUserJsonTask);

        JSONObject result = new JSONObject();
        try {
            //此get方法会发生阻塞，等在主线程的方法return就是等待queryInfo的call方法执行返回结果之后
            //在从线程里get得到数据，不可能直接返回数据
            result.putAll(infoEmpJsonTask.get());//get()阻塞方法
            result.putAll(infoUserJsonTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return result.toString();
    }
}
