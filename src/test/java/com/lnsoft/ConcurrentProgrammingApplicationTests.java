package com.lnsoft;

import com.lnsoft.service.InfoServiceThread;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConcurrentProgrammingApplicationTests {


    private static final int USER_NUMS = 200;
    private static CountDownLatch countDownLatch = new CountDownLatch(USER_NUMS);

    @Autowired
    private InfoServiceThread infoServiceThread;

    /**
     * 测试并发
     */
    @Test
    public void contextLoads() {
        for (int x = 0; x < USER_NUMS; x++) {
            new Thread((Runnable) new RequestRequest()).start();
            countDownLatch.countDown();
        }

    }

    public class RequestRequest implements Callable {

        @Override
        public Object call() {

            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            infoServiceThread.getInfoByThread();
            return null;
        }
    }
}

