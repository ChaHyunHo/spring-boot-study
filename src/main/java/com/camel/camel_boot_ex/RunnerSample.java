package com.camel.camel_boot_ex;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class RunnerSample implements ApplicationRunner {
	
	@Value("${name.fullname}")
	private String name;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("=====================");
		System.out.println("풀네임");
		System.out.println(name);
		System.out.println("=====================");
	}
}
