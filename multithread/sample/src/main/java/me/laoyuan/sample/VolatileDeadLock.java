/**
 * Copyright (c)2018, Laoyuan.me.  All Rights Reserved.
 */
package me.laoyuan.sample;

/**
 * 无Volatile标示，会导致死锁情况,原因：程序执行值来自本地变量
 * 
 * @author laoyuan.me
 *
 */
public class VolatileDeadLock {
	// volatile boolean run = true; //加入volatile关键字对比，发现当被置为false时，程序能快速停止
	boolean run = true;

	public void setRun(boolean run) {
		this.run = run;
	}

	public void runPrint() {
		System.out.println("程序运行开始...");
		while (run == true) {
			// 无任何耗时操作，让程序一直运行才会体现出程序使用的是工作内存
		}
		System.out.println("程序运行结束...");
	}

	public static void main(String[] args) {
		final VolatileDeadLock nvdl = new VolatileDeadLock();
		new Thread() {
			@Override
			public void run() {
				nvdl.runPrint();
			};
		}.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		nvdl.setRun(false);
	}
}
