package com.camel.camel_boot_ex.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
	
	@Autowired
	private SampleService sampleService;
	
	@GetMapping("/bye")
	public String bye() {
		return "bye"+ " " + sampleService.getName();
	}
	
	
	@GetMapping("/hi")
	public String hihello() {
		return "hi"+ " " + sampleService.getName();
	}
}
