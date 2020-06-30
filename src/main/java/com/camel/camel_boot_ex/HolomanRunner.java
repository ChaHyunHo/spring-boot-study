package com.camel.camel_boot_ex;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.camel.Holoman;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class HolomanRunner implements ApplicationRunner {
	private final Holoman holoman;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println(holoman);
	}

}
