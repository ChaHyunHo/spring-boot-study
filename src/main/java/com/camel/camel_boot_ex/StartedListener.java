package com.camel.camel_boot_ex;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

public class StartedListener implements ApplicationListener<ApplicationStartedEvent> { // 어플리케이션 이벤트가 끝난 시점에서 실행

	@Override
	public void onApplicationEvent(ApplicationStartedEvent event) {
		System.out.println("============================");
		System.out.println("Application is started");
		System.out.println("============================");
	}

}
