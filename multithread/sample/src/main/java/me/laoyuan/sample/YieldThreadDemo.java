/**
 * Copyright (c)2018, Laoyuan.me.  All Rights Reserved.
 */
package me.laoyuan.sample;

/**
 * 让出资源给其他线程执行
 * 
 * @author laoyuan.me
 *
 */
public class YieldThreadDemo {
	/**
	 * 计数线程
	 * 
	 * @author laoyuan.me
	 *
	 */
	public static class CountThread implements Runnable {
		int count = 0;

		public void run() {
			long startTime = System.currentTimeMillis();
			for (int i = 0; i < 5000000; i++) {
				count++;
				// 线程让出CPU资源
				Thread.yield();
			}
			long endTime = System.currentTimeMillis();
			System.out.println("计数执行完总耗时：" + (endTime - startTime));
		}
	}

	public static void main(String[] args) {
		new Thread(new CountThread()).start();
	}
}
