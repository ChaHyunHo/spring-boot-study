package com.camel.camel_boot_ex;

import java.io.PrintStream;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// @SpringBootConfiguration
// @Configuration
// @ComponentScan
// @EnableAutoConfiguration
@SpringBootApplication
@RestController
public class CamelBootEx1Application {
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello Spring";
	}
	
	public static void main(String[] args) {
		//SpringApplication application = new SpringApplication(CamelBootEx1Application.class);
		// application.setWebApplicationType(WebApplicationType.NONE);
		// application.setBannerMode(Banner.Mode.OFF); 배너모드 끄기
		// application.run(args);
		// SpringApplication.run(CamelBootEx1Application.class, args);
		
		// 빌더로도 할 수 있음.
		new SpringApplicationBuilder()
					.listeners(new StartingListener())
					.listeners(new StartedListener())  // 어플리케이션 이벤트가 끝난 시점에서 실행
					.sources(CamelBootEx1Application.class)
					.banner(new Banner() {
						@Override
						public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
							out.println("============================");
							out.println("banner customizing");
							out.println("============================");
						}
					})
					.run(args);
		
		// 배너 커스터 마이징(banner.txt가 존재하면 우선순위에서 밀림)
		/*
		application.setBanner(new Banner() {
			@Override
			public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
				out.println("============================");
				out.println("banner customizing");
				out.println("============================");
			}
		});
		*/

		
		
		
		
			
		/*
		  스프링 부트는 서버가 아니다.
		  1. 톰캣 객체를 생성
		  2. 포트 설정
		  3. 톰캣 컨택스트 추가
		  4. 서블릿 만들기
		  5. 톰캣에 서블릿 추가
		  6. 컨텍스트 서블릿 맵핑
		  7. 톰캣 실행 및 대기 
		  
		  이 모든 과정 보다 상세히 또 유연하게 설정하고 실행해주는게 스프링 부트 자동 설정 
			○ ServletWebServerFactoryAutoConfiguration (서블릿 웹 서버 생성) 
			■ TomcatServletWebServerFactoryCustomizer (서버 커스터마이징) 
			○ DispatcherServletAutoConfiguration 
			■ 서블릿 만들고 등록 

		  Tomcat tomcat = new Tomcat(); tomcat.setPort(8080);
		  
		  String docBase = Files.createTempDirectory("tomcat-basedir").toString();
		  
		  System.out.println(docBase);
		  
		  Context context = tomcat.addContext("/", docBase);
		  
		  HttpServlet servlet = new HttpServlet() {
		  
			@Override
			protected void doGet(HttpServletRequest req, HttpServletResponse resp)
					throws ServletException, IOException {
				PrintWriter writer = resp.getWriter();
				writer.println("<head><title>");
				writer.println("Hey, tomcat");
				writer.println("<title></head>");
				writer.println("<body><h1>Hello Tomcat</h1></body>");
				}
			};
		  
		  String servletName = "helloSevlet"; 
		  tomcat.addServlet("/", servletName, servlet); 
		  context.addServletMappingDecoded("/hello", servletName);
		  
		  tomcat.getConnector();
		  tomcat.start(); 
		  tomcat.getServer().await();
		  
		 */
	}
	
	// https를 설정하게되면 하나의 컨넥터를 https기 사용하므로 http를 따로 설정해줘야 둘다 사용할 수 있다.
	/*
	@Bean
	public ServletWebServerFactory serverFactory() {
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
		tomcat.addAdditionalTomcatConnectors(createStandardConnector());
		return tomcat;
	}
	
	private  Connector createStandardConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setPort(8080);
		return connector;
	}
	*/

	/*
	@Bean
	public Holoman holoman() {
		Holoman holoman = new Holoman();
		holoman.setName("CHAMYM");
		holoman.setHowLong(10);
		return holoman;
	}
	*/
	
	
	/*
	 * 현재 상황은 직접만든 메이븐 패키징을 이 프로젝트에 주입하여 
	 * 해당 라이브러리에 빈을 설정하고 어플리케이션을 설정했다. 
	 * 하지만 이프로젝트에 똑같은 빈설정(holoman)을 하게 되면 어떻게 될까에 대해 학습하는 내용이다.
	 * 스프링 부트가 2.2로 올라가면서 빈 오버라이딩을 막아서 익셉션발생
	 * 그래서 영상에서 설명하는 빈이 덮어 쓰여지는 현상이 벌어지질 않음 
	 * `spring.main.allow-bean-definition-overriding=true` 
	 * 설정을 추가해서 빈 오버라이딩을 허용하시면 수업 영상과 동일하게 
	 * 실습을 진행할 수 있긴하지만 그냥 영상만 참고 하셔도 좋습니다.
	 * 
	 * 빈 오버라이딩을 허용하는방법은 
	 * 패키징 했던 메이븐프로젝트 해당빈에  @ConditionalOnMissingBean 어노테이션을 사용하게되면
	 * 현재 이프로젝트에 설정된 똑같은 빈으로 덮어 씌여지게 된다. 
	 * */

}
