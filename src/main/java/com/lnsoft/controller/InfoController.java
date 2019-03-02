package com.lnsoft.controller;

import com.lnsoft.service.InfoServiceThread;
import com.lnsoft.service.InfoServiceTraditional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.Callable;

/**
 * 该类测试传统的调用和并发编程的效率差距：
 * Created By Chr on 2019/1/30/0030.
 */
@Controller
@RequestMapping("/test")
public class InfoController {

    @Autowired
    private InfoServiceTraditional infoServiceTraditional;
    @Autowired
    private InfoServiceThread infoServiceThread;

    /**
     * spring异步请求机制有两种方式：
     * 1.Callable<String>
     * 2.DeferredResult<String>
     *
     * @return
     */
    //异步机制：使用spring提供的异步机制：返回类型+线程+线程池
    //Async Requests
    @ResponseBody
    @RequestMapping("/info3")
    public Callable<String> infoTest3() {

        long startTime = System.currentTimeMillis();
        //主线程
        System.out.println("当前主线程为：" + Thread.currentThread() + " --->主线程开始时间：" + System.currentTimeMillis());
        //子线程
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("当前子线程为：" + Thread.currentThread() + " --->子线程程开始时间：" + System.currentTimeMillis());
                String result = infoServiceThread.getInfoByThread();
                System.out.println("当前子线程为：" + Thread.currentThread() + " --->子线程程结束时间：" + System.currentTimeMillis());
                return result;
            }
        };
        System.out.println("当前主线程为：" + Thread.currentThread() + " --->接口结束时间：" + System.currentTimeMillis());
        System.out.println("接口执行时间：" + (System.currentTimeMillis() - startTime));
//******************************************
        return callable;
    }

    //使用线程Callable+线程池sun+FutureTask
    @ResponseBody
    @RequestMapping("/info2")
    public String infoTest2() {
        long startTime = System.currentTimeMillis();
        System.out.println("接口开始时间：" + startTime);
        String infoByThread = infoServiceThread.getInfoByThread();
        System.out.println("接口结束时间：" + System.currentTimeMillis());
        System.out.println("接口执行时间：" + (System.currentTimeMillis() - startTime));

        return infoByThread;

        //（1）普通线程+线程池
//        接口开始时间：1548830263127
//        {"status":200,"msg":"成功","data":{"id":8,"username":"nancy","password":"etoak","createtime":"2018-10-26 15:06:40","email":"456@qq.com","phone":"15153501309","ustatus":"1"}}
//        {"status":200,"msg":"OK","data":[{"ENAME":"SCOTT","HIREDATE":"1987-04-19 00:00:00","EMPNO":7788,"MGR":7566,"JOB":"ANALYST","DEPTNO":20,"SAL":3000}]}
//        接口结束时间：1548830263286
//        接口执行时间：159
        //（2）sun的futureTask+线程+线程池
//        接口开始时间：1548830272971
//        {"status":200,"msg":"OK","data":[{"ENAME":"SCOTT","HIREDATE":"1987-04-19 00:00:00","EMPNO":7788,"MGR":7566,"JOB":"ANALYST","DEPTNO":20,"SAL":3000}]}
//        接口结束时间：1548830272996
//        接口执行时间：25

        //（3）自己写的futureTask+线程+线程池
//        {"status":200,"msg":"OK","data":[{"ENAME":"SCOTT","HIREDATE":"1987-04-19 00:00:00","EMPNO":7788,"MGR":7566,"JOB":"ANALYST","DEPTNO":20,"SAL":3000}]}
//        {"status":200,"msg":"成功","data":{"id":8,"username":"nancy","password":"etoak","createtime":"2018-10-26 15:06:40","email":"456@qq.com","phone":"15153501309","ustatus":"1"}}
//        接口开始时间：1548830290712
//        接口结束时间：1548830290725
//        接口执行时间：13
    }

    //这是传统的调用接口
    @ResponseBody
    @RequestMapping("/info")
    public String infoTest() {
        long startTime = System.currentTimeMillis();
        System.out.println("接口开始时间：" + startTime);
        String infoByTrad = infoServiceTraditional.getInfoByTrad();
        System.out.println("接口结束时间：" + System.currentTimeMillis());
        System.out.println("接口执行时间：" + (System.currentTimeMillis() - startTime));

        return infoByTrad;
//        接口开始时间：1548817678353
//        {"status":200,"msg":"OK","data":[{"ENAME":"SCOTT","HIREDATE":"1987-04-19 00:00:00","EMPNO":7788,"MGR":7566,"JOB":"ANALYST","DEPTNO":20,"SAL":3000}]}
//        {"status":200,"msg":"成功","data":{"id":8,"username":"nancy","password":"etoak","createtime":"2018-10-26 15:06:40","email":"456@qq.com","phone":"15153501309","ustatus":"1"}}
//        {"msg":"OK","data":[{"ENAME":"SCOTT","HIREDATE":"1987-04-19 00:00:00","EMPNO":7788,"MGR":7566,"JOB":"ANALYST","DEPTNO":20,"SAL":3000}],"status":200}
//        接口结束时间：1548817680460
//        接口执行时间：2107

    }

    //改进
    @ResponseBody
    @RequestMapping("/info4")
    public Callable<String> infoTest4() {
        //子线程
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                return infoServiceThread.getInfoByThread();
            }
        };
    }

}
