package com.camel.camel_boot_ex;

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
	
	@Autowired
	NameProperties nameProperties;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("=====================");
		log.info(nameProperties.getName());
		log.info(nameProperties.getFullName());
		log.info(nameProperties.getAge());
		log.info(nameProperties.getSessionTimeout());
		log.info("=====================");
	}
}
