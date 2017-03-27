package com.zjlp.im.logback;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import com.zjlp.im.mq.rocketmq.producer.RocketMQProducer;
import com.zjlp.im.mq.rocketmq.producer.config.RocketMQProducerClientConfig;
import com.zjlp.im.mq.rocketmq.producer.impl.RocketMQProducerImpl;

public class RocketmqAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {

	Logger logger = LoggerFactory.getLogger(RocketmqAppender.class);
	private JSONEventLayout jsonlayout = null;
	/*** RocketMQProducer */
	private RocketMQProducer rocketMQProducer = null;

	/*** RocketMQ配置类 */
	private static RocketMQProducerClientConfig msgProducerConfig = null;
	/*** 主机地址 */
	private String namesrvAddr;
	/*** 客户端标识 ip@instanceName */
	private String instanceName;
	/*** 组名 */
	private String groupName;
	/*** 发送消息的topic */
	private String topic;

	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

	public RocketmqAppender() {
		if (jsonlayout == null)
			jsonlayout = new JSONEventLayout();
	}

	@Override
	protected void append(ILoggingEvent event) {

		try {
			if (msgProducerConfig == null) {
				msgProducerConfig = new RocketMQProducerClientConfig();

				msgProducerConfig.setNamesrvAddr(namesrvAddr);
				msgProducerConfig.setGroupName(groupName);
				msgProducerConfig.setInstanceName(instanceName);
			}
			if (this.rocketMQProducer == null) {
				this.rocketMQProducer = new RocketMQProducerImpl(msgProducerConfig);
			}
		} catch (Exception e) {
			logger.error("【rocketMQProducer】:异常 :", e);
		}

		LogTransport logTransport = bulidLogtransport(event);

		synchronized (rocketMQProducer) {
			try {
				this.rocketMQProducer.sendSync(this.topic, logTransport);

			} catch (Exception e) {
				logger.error("【rocketMQProducer】:发送异常 :", e);
			}
		}

	}

	/**
	 * 
	 *
	 * @param event
	 * @return
	 * @author sunJie 2017年3月16日 下午2:47:44
	 * @reviewer
	 */
	private LogTransport bulidLogtransport(ILoggingEvent event) {
		LogTransport logTransport = new LogTransport();
		logTransport.setSource(jsonlayout.getSource());
		logTransport.setHost(jsonlayout.getSourceHost());
		logTransport.setTags(getTags());
		logTransport.setMessage(event.getMessage());
		logTransport.setCreattime(df.format(new Date(event.getTimeStamp())));
		logTransport.setLogger(event.getLoggerName());
		logTransport.setThread(event.getThreadName());
		logTransport.setLevel(event.getLevel().levelStr);
		return logTransport;
	}

	public String getSource() {
		return jsonlayout.getSource();
	}

	public void setSource(String source) {
		jsonlayout.setSource(source);
	}

	public String getTags() {
		return jsonlayout.getTags();
	}

	public void setTags(String tags) {
		jsonlayout.setTags(tags);
	}

	public String getNamesrvAddr() {
		return namesrvAddr;
	}

	public void setNamesrvAddr(String namesrvAddr) {
		this.namesrvAddr = namesrvAddr;
	}

	public String getInstanceName() {
		return instanceName;
	}

	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

}
