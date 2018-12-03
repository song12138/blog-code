/**
 * Copyright (c)2018, Laoyuan.me.  All Rights Reserved.
 */
package me.laoyuan.rabbitmq.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import me.laoyuan.rabbitmq.config.RabbitMQConfig;

/**
 * 消息接收消费者
 * 
 * @author laoyuan.me
 *
 */
@Service
public class LogReceiver {

	/**
	 * 日志队列，收取所有消息
	 * 
	 */
	@RabbitListener(queues = RabbitMQConfig.QUEUE_NAME_LOG)
	public void receivLog(String msg) {
		System.out.println("记录消息日志：" + msg);
	}
}
