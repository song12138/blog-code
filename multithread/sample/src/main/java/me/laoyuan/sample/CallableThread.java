/**
 * Copyright (c)2018, Laoyuan.me.  All Rights Reserved.
 */
package me.laoyuan.sample;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 带返回值的线程
 * 
 * @author laoyuan.me
 *
 */
public class CallableThread implements Callable<Integer> {

	public Integer call() throws Exception {
		int num = new Random().nextInt(100);
		// throw new Exception("执行失败");// 可以被捕获的错误
		return num;
	}

	public static void main(String[] args) {
		FutureTask<Integer> future = new FutureTask<Integer>(new CallableThread());
		new Thread(future).start();
		try {
			Thread.sleep(5000);// 可能做一些事情
			System.out.println(future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}
