package com.znv.mall;

import org.apache.kafka.common.metrics.stats.Count;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @Auther: yf
 * @Date: 2019/10/25
 * @Description:
 */
@SpringBootTest
public class Test1 {

    @Test
    public void test1() throws InterruptedException {
        int count = 1000;
        CountDownLatch countDownLatch1 = new CountDownLatch(count);
        CountDownLatch countDownLatch2 = new CountDownLatch(count);
        SafeCounter safeCounter = new SafeCounter(countDownLatch1,countDownLatch2);
        for (int i=0;i<count;i++) {
            new Thread(safeCounter).start();
//            System.out.println("countDown！！");
            countDownLatch1.countDown();
        }
        countDownLatch2.await();
        System.out.println(SafeCounter.count);

    }
}
class SafeCounter implements Runnable{
    CountDownLatch countDownLatch1;
    CountDownLatch countDownLatch2;
    public static int count = 0;

    SafeCounter(CountDownLatch c1, CountDownLatch c2) {
        countDownLatch1 = c1;
        countDownLatch2 = c2;
    }

    @Override
    public  void run() {
        try {
//            System.out.println("await！！");
            countDownLatch1.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println("开始冲刺！！");
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        count++;
        countDownLatch2.countDown();
    }
}