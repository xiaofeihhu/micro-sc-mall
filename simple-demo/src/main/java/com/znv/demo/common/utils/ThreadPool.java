package com.znv.demo.common.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 线程任务
 * 
 */
public class ThreadPool {

	private ThreadPool() {
	}

	/**
	 * 初始化类
	 */
	public final static ThreadPool task = new ThreadPool();

	/**
	 *
	 * @return ThreadTask引用
	 */
	public static ThreadPool getInstance() {
		return task;
	}

	/**
	 * 线程池
	 */
	ExecutorService executor = Executors.newFixedThreadPool(5);

	ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
	/**
	 * 执行线程
	 *
	 * @param runnable
	 */
	public void execute(Runnable runnable) {
		this.executor.execute(runnable);
	}

	public void executeScheduled(int millSenconds, Runnable runnable){
		this.scheduledExecutorService.schedule(runnable,millSenconds, TimeUnit.MILLISECONDS);
	}
}
