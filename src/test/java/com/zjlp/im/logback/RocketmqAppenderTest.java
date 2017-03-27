package com.zjlp.im.logback;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;

public class RocketmqAppenderTest {
	@Test
	public void logTest() throws Exception {
		configLogger("/logback.xml");
		Logger logger = LoggerFactory.getLogger(RocketmqAppenderTest.class);
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			logger.info("Test Log #" + (i + 1));
		}
		long end = System.currentTimeMillis()-start;
		System.out.println("耗时:"+(end/100000));
		//耗时:1ms 10000 
		//耗时：1ms 10000
	}

	private void configLogger(String loggerxml) {
		LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

		try {
			JoranConfigurator configurator = new JoranConfigurator();
			configurator.setContext(context);
			context.reset();
			configurator.doConfigure(this.getClass().getResourceAsStream(loggerxml));
		} catch (JoranException je) {

		}
		StatusPrinter.printInCaseOfErrorsOrWarnings(context);
	}
}