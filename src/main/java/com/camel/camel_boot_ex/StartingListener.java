package com.camel.camel_boot_ex;

import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

// @Component 빈으로 등록을 한다고해도 애플리케이션 컨텍스트가 존재하지 않아 실행안됨 (빈으로 등록하는게 의미 없음)
public class StartingListener implements ApplicationListener<ApplicationStartingEvent> { // 애플리케이션 컨텍스트가 만들기도 전에 실행되는 시점이다.

	@Override
	public void onApplicationEvent(ApplicationStartingEvent event) {
		System.out.println("============================");
		System.out.println("Application is starting");
		System.out.println("============================");
		
	}

}
