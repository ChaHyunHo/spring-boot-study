package com.camel.camel_boot_ex;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandSample implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		System.out.println("===============================");
		System.out.println("!!CommandLineRunner sample");
		Arrays.stream(args).forEach(System.out::println);
		System.out.println("===============================");
	}
	
}
