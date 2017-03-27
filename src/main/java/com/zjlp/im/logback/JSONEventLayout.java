package com.zjlp.im.logback;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.LayoutBase;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 * @author sunJie 2017年3月15日 下午5:08:55
 * @reviewer
 */
public class JSONEventLayout extends LayoutBase<ILoggingEvent> {
	Logger logger = LoggerFactory.getLogger(JSONEventLayout.class);

	String source;
	String sourceHost;
	String tags;
	String type;

	public JSONEventLayout() {
		try {
			setSourceHost(InetAddress.getLocalHost().getHostName());

		} catch (UnknownHostException e) {
			logger.error("【JSONEventLayout】:异常UnknownHostException :", e);
		}
	}

	@Override
	public void start() {
		super.start();
	}

	public synchronized String doLayout(ILoggingEvent event) {
		return null;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSourceHost() {
		return sourceHost;
	}

	public void setSourceHost(String sourceHost) {
		this.sourceHost = sourceHost;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
