/**
 * Copyright (c)2018, Laoyuan.me.  All Rights Reserved.
 */
package me.laoyuan.sample;

import java.io.IOException;
import java.io.InputStream;

/**
 * 自定义类加载器
 * 
 * @author laoyuan.me
 *
 */
public class CustomerClassLoader {

	public static void main(String[] args) {
		ClassLoader cus = new ClassLoader() {
			@Override
			public Class<?> loadClass(String name) throws ClassNotFoundException {
				// 文件名
				String fileName = name + ".class";
				InputStream classStream = getClass().getResourceAsStream(fileName);
				if (classStream == null) {
					return super.loadClass(name);
				}
				try {
					byte[] b = new byte[classStream.available()];
					classStream.read(b);
					return defineClass( b, 0, b.length);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return super.loadClass(name);

			}
		};
		try {
			Object lock = cus.loadClass("ClassVsObjectLock").newInstance();
			System.out.println(lock instanceof ClassVsObjectLock);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}
