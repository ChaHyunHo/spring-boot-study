package com.camel.camel_boot_ex;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.log4j.Log4j2;

@RunWith(SpringRunner.class)
// @TestPropertySource(properties = "name.fullname=testProPertySource") // 가장 우선순위 프로퍼티
@SpringBootTest(properties ="name.fullname=springBootTest2" )
@Log4j2
class CamelBootEx1ApplicationTests {
	
	@Autowired
	private Environment environment;

	@Test
	void propertiesTest() {
		log.info("============ TEST");
		assertThat(environment.getProperty("name.fullname"))
						.isEqualTo("springBootTest2");
	}
}
