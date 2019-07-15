package com.znv.mall.consumer.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @Auther: yf
 * @Date: 2019-7-15
 * @Description:
 */
public class MallOrderDemo {

    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public int stock = 10;

    private void createOrder() {
        threadLocal.set(stock--);;
        System.out.println(Thread.currentThread().getName()+"创建订单后库存："+threadLocal.get());
    }

    private void pay() {
        System.out.println(Thread.currentThread().getName()+"订单支付后库存："+threadLocal.get());
    }

    public void count(){
        createOrder();
        pay();
    }

    public static void main(String[] args) {
        MallOrderDemo mallOrderDemo = new MallOrderDemo();

        int count = 10;
        CountDownLatch cdl = new CountDownLatch(count);
        for (int i =0; i<count;i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        cdl.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mallOrderDemo.count();
                }
            }).start();
            cdl.countDown();
        }
    }
}
