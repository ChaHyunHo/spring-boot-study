package com.camel.camel_boot_ex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component
@Order(1)
@Log4j2
public class RunnerSample implements ApplicationRunner {
	
	private Logger logger = LoggerFactory.getLogger(RunnerSample.class);
	
	@Autowired
	NameProperties nameProperties;
	
	@Autowired
	private String hello;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("=====================");
		log.info(nameProperties.getName());
		log.info(nameProperties.getFullName());
		log.info(nameProperties.getAge());
		/* log.info(nameProperties.getSessionTimeout()); */
		log.info("=====================");
		
		log.info("=====================");
		log.info("프로파일 테스트");
		log.info(hello);
		log.info("=====================");
		
		
		System.out.println("====================== logger 테스트");
		logger.debug(nameProperties.getName());
		logger.debug(nameProperties.getFullName());
		System.out.println("=====================================");
	}
}
