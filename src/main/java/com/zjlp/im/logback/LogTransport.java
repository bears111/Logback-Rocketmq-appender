package com.zjlp.im.logback;

import java.io.Serializable;

import ch.qos.logback.classic.Level;

/**
 * Log传输对象
 * 
 * @author sunJie 2017年3月16日 上午11:15:54
 * @reviewer
 */
public class LogTransport  implements Serializable{
	  
	/**  
	* @fields serialVersionUID
	*/  
	  
	
	private static final long serialVersionUID = 7716313553904523198L;
	/**
	 * 源目标
	 */
	private String source;
	/**
	 * 主机
	 */
	private String host;
	/**
	 * 标签
	 */
	private String tags;
	/**
	 * 日志主体
	 */
	private String message;
	/**
	 * 产生log的时间
	 */
	private String creattime;
	/**
	 * logger类
	 */
	private String logger;
	/**
	 * 日志的级别
	 */
	private String level;
	/**
	 * 线程名
	 */
	private String thread;
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public String getCreattime() {
		return creattime;
	}
	public void setCreattime(String creattime) {
		this.creattime = creattime;
	}
	public String getLogger() {
		return logger;
	}
	public void setLogger(String logger) {
		this.logger = logger;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getThread() {
		return thread;
	}
	public void setThread(String thread) {
		this.thread = thread;
	}
	
	

}
