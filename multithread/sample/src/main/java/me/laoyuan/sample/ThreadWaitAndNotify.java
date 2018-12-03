/**
 * Copyright (c)2018, Laoyuan.me.  All Rights Reserved.
 */
package me.laoyuan.sample;

/**
 * 线程的等待与通知
 * 
 * @author laoyuan.me
 *
 */
public class ThreadWaitAndNotify implements Runnable {

	protected Object lock;

	public ThreadWaitAndNotify(Object lock) {
		super();
		this.lock = lock;
	}

	public void run() {
		synchronized (lock) {
			process();
		}

	}

	/**
	 * 被运行方法同步调用方法
	 */
	public void process() {
		// 空实现
	}

	public static void main(String[] args) {
		Object newLock = new Object();
		// 等待通知线程
		new Thread(new ThreadWaitAndNotify(newLock) {
			@Override
			public void process() {
				System.out.println("执行开始等待通知");
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("得到通知后执行结束");
			}
		}).start();
		// 等待5秒再开启通知线程
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		// 发出通知线程
		new Thread(new ThreadWaitAndNotify(newLock) {
			@Override
			public void process() {
				System.out.println("开始发放通知");
				lock.notify();
				try {
					//测试通知后耗时
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
				}
				System.out.println("通知发放结束");
			}
		}).start();
	}
}
