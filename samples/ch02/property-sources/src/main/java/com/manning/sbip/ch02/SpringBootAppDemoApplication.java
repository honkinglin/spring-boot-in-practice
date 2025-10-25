package com.manning.sbip.ch02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBootAppDemoApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringBootAppDemoApplication.class);

	public static void main(String[] args) {
		
		ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBootAppDemoApplication.class, args);
		DbConfiguration dbConfiguration = applicationContext.getBean(DbConfiguration.class);
		log.info(dbConfiguration.toString());
	}
}
