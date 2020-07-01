package com.camel.camel_boot_ex;

import javax.validation.constraints.NotEmpty;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

// application.properties의 key value 값이 많을경우 한 클래스 내 묶을수 있다.
@Component
@Data
@ConfigurationProperties("name")
@Validated
public class NameProperties {
	
	@NotEmpty
	private String name;
	
	private int age;
	
	private String fullName;
	
	/*
	 * @DurationUnit(ChronoUnit.SECONDS) private Duration sessionTimeout =
	 * Duration.buildBySeconds(30);
	 */

}
