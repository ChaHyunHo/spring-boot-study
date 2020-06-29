package com.camel.camel_boot_ex;

import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class PortListener implements ApplicationListener<ServletWebServerInitializedEvent>  {

	@Override
	public void onApplicationEvent(ServletWebServerInitializedEvent event) {
		// 웹서버가 초기화가 되면 이벤트 리스너가 콜백 호출된다. 
		ServletWebServerApplicationContext applicationContext =  event.getApplicationContext();
		log.info("ServletWebServerInitializedEvent port number check!! : " + applicationContext.getWebServer().getPort()); 
	}

}
