/**
 * Copyright (c)2018, Laoyuan.me.  All Rights Reserved.
 */
package me.laoyuan;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * RabbitMQ启动器
 * 
 * @author laoyuan.me
 *
 */
@SpringBootApplication
//开启定时任务，用于定时发送一条消息
@EnableScheduling
public class RabbitMQStarter {
	public static void main(String[] args) {
		new SpringApplicationBuilder(RabbitMQStarter.class).run(args);
	}
}
