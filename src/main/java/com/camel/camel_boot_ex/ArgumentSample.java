package com.camel.camel_boot_ex;

import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

@Component
public class ArgumentSample {
	
	public ArgumentSample(ApplicationArguments arguments) {
		System.out.println(arguments);
	}

}
