/**
 * Copyright (c)2018, Laoyuan.me.  All Rights Reserved.
 */
package me.laoyuan.sample;

/**
 * 类锁与对象锁
 * 
 * @author laoyuan.me
 *
 */
public class ClassVsObjectLock {
	public synchronized void objectLock() {
		System.out.println("对象方法开始执行");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("对象方法结束执行");
	}

	public static synchronized void classLockA() {
		System.out.println("静态方法A开始执行");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("静态方法A结束执行");
	}
	public static synchronized void classLockB() {
		System.out.println("静态方法B开始执行");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("静态方法B结束执行");
	}
	public static void main(String[] args) {
		// 先测试类锁会不会阻塞对象锁
//		new Thread() {
//			@Override
//			public void run() {
//				ClassVsObjectLock.classLock();
//			};
//		}.start();
//		new Thread() {
//			@Override
//			public void run() {
//				new ClassVsObjectLock().objectLock();
//			};
//		}.start();
		// 再测试对象锁会不会阻塞类锁
		new Thread() {
			@Override
			public void run() {
				new ClassVsObjectLock().objectLock();
			};
		}.start();
		new Thread() {
			@Override
			public void run() {
				ClassVsObjectLock.classLockA();
			};
		}.start();
		new Thread() {
			@Override
			public void run() {
				ClassVsObjectLock.classLockB();
			};
		}.start();
	}
}
