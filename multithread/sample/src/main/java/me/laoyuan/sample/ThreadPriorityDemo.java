/**
 * Copyright (c)2018, Laoyuan.me.  All Rights Reserved.
 */
package me.laoyuan.sample;

import java.util.ArrayList;
import java.util.List;

/**
 * 线程权限演示
 * 
 * @author laoyuan.me
 *
 */
public class ThreadPriorityDemo {
	/**
	 * 权限查看线程
	 * 
	 * @author laoyuan.me
	 *
	 */
	public static class PriorityViewThread implements Runnable {
		public void run() {
			System.out.println(
					"子线程" + Thread.currentThread().getName() + "当前权限级别：" + Thread.currentThread().getPriority());
		}
	}

	public static void main(String[] args) {
		System.out.println("主线程当前权限级别：" + Thread.currentThread().getPriority());
		List<Thread> t10List = new ArrayList<Thread>();
		List<Thread> t1List = new ArrayList<Thread>();
		for (int i = 0; i < 20; i++) {
			Thread threada = new Thread(new PriorityViewThread());
			threada.setName("10级别线程-" + i);
			threada.setPriority(10);
			t10List.add(threada);
			Thread threadb = new Thread(new PriorityViewThread());
			threadb.setName("1级别线程-" + i);
			threadb.setPriority(1);
			t1List.add(threadb);
		}
		for (Thread temp : t1List) {// 低级别线程先开始执行
			temp.start();
		}
		for (Thread temp : t10List) {// 高级别线程后开始执行
			temp.start();
		}
	}
}
