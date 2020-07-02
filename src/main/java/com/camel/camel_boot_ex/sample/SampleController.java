package com.camel.camel_boot_ex.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
	
	Logger logger = LoggerFactory.getLogger(SampleController.class);
	
	@Autowired
	private SampleService sampleService;
	
	@GetMapping("/bye")
	public String bye() {
		return "bye"+ " " + sampleService.getName();
	}
	
	@GetMapping("/hi")
	public String hihello() {
		logger.info("test0");
		System.out.println("test1");
		System.out.println("test2");
		return "hi"+ " " + sampleService.getName();
	}
}
