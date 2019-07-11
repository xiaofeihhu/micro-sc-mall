package com.znv.mall.producer.business.delay;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class MyDelayedEvent implements Delayed{
    //要执行的任务
    private Task task;

    private Long endTime;

    public MyDelayedEvent(Task task, Long endTime) {
        this.task = task;
        this.endTime = endTime;
    }

    //获取剩余的时间，为0获取负数时取出
    //TimeUnit.NANOSECONDS 毫微妙
    @Override
    public long getDelay(TimeUnit unit) {
//        return unit.convert(endTime,TimeUnit.MILLISECONDS) - unit.convert(System.currentTimeMillis(),TimeUnit.MILLISECONDS);
        return unit.convert(endTime,TimeUnit.NANOSECONDS) - unit.convert(System.currentTimeMillis(),TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if (this == o)
            return 1;
        if (o==null)
            return -1;
        long diff = this.getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
        return diff<0?-1:(diff==0?0:1);
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }
}
