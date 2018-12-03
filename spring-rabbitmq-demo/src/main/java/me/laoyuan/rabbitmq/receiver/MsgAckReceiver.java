/**
 * Copyright (c)2018, Laoyuan.me.  All Rights Reserved.
 */
package me.laoyuan.rabbitmq.receiver;

import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.rabbitmq.client.Channel;

import me.laoyuan.rabbitmq.config.RabbitMQConfig;

/**
 * 带消息确认的ACK消费者
 * 
 * @author laoyuan.me
 *
 */
@Service
public class MsgAckReceiver {
	/**
	 * 消息队列，只接收消息内容
	 * 
	 */
	@RabbitListener(queues = RabbitMQConfig.QUEUE_NAME_MSG)
	public void receivMsg(int msg, String lmsg, Message message, Channel channel) {
		System.out.println("收到消息：" + msg + "," + lmsg);

		// 确认消息已经消费成功
		try {
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);// 确认成功
			//schannel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);// 确认失败
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
