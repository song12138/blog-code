/**
 * Copyright (c)2018, Laoyuan.me.  All Rights Reserved.
 */
package me.laoyuan.sample;

/**
 * 方法参数锁，当传入相同的参数对象，则会锁住同一个
 * 
 * @author laoyuan.me
 *
 */
public class MethodArgLock implements Runnable {
	/**
	 * 切换参数
	 */
	private String args;

	public MethodArgLock(String args) {
		super();
		this.args = args;
	}

	/**
	 * 锁住相同参数
	 * 
	 * @param arg
	 *            需要锁住的参数
	 */
	public void lockArg(Object arg) {
		System.out.println("执行开始,参数名称：" + arg);
		synchronized (arg) {
			System.out.println("----》开始锁住参数：" + arg);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		System.out.println("执行结束,参数名称：" + arg);
	}

	public static void main(String[] args) {
		String arg = "AVG";
		new Thread(new MethodArgLock(arg)).start();
		new Thread(new MethodArgLock(arg)).start();
	}

	public void run() {
		this.lockArg(args);

	}
}
