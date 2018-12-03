/**
 * Copyright (c)2018, Laoyuan.me.  All Rights Reserved.
 */
package me.laoyuan.sample;

import java.util.Date;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁与通知
 * 
 * @author laoyuan.me
 *
 */
public class ReentrantLockAndCondition {
	// 公平锁
	private ReentrantLock lock = new ReentrantLock(true);
	private Condition condition = lock.newCondition();

	public void await() {
		try {
			lock.lock();
			//condition.await();
			condition.awaitUntil(new Date());
			System.out.println("等待结束");
		} catch (Exception e) {
			//
		} finally {
			lock.unlock();
		}

	}

	public void signal() {
		try {
			Thread.sleep(4000);
			lock.lock();
			//lock.tryLock();
			condition.signal();
			Thread.sleep(4000);
			System.out.println("通知结束");
		} catch (Exception e) {
			//
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		final ReentrantLockAndCondition rac = new ReentrantLockAndCondition();
		new Thread() {
			@Override
			public void run() {
				rac.await();
			};
		}.start();
		// 当通知结束释放锁后，等待方法才会继续执行
		new Thread() {
			@Override
			public void run() {
				rac.signal();
			};
		}.start();
	}
}
