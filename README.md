# spring-boot-study 
### * ;의존성 관리 , 자동 설정에 대한 이해는 생략

## 스프링 부트 자동설정 만들기(메이븐 직접 만들어 실습)

#### 자동설정에 원리를 파악하고 이해하기 위해 학습
```
패키징된 메이븐 프로젝트의 주요한 코드(프로젝트를 따로 올리기 번거로워서 코드만 입력)

pom.xml (dependencies, dependencyManagement)

<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-autoconfigure</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-autoconfigure-processor</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
		    <groupId>org.springframework.boot</groupId>
		    <artifactId>spring-boot-configuration-processor</artifactId>
		    <optional>true</optional>
		</dependency>
		
	</dependencies>
	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-dependencies</artifactId>
				<version>2.0.3.RELEASE</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement> 
	
---------------------------------------------------------------------------------------------------------------------------------------------
서비스 제공자
resources/META-INF/spring.factories

org.springframework.boot.autoconfigure.EnableAutoConfiguration=\   (EnableAutoConfiguration켜져 있으면) 이 키에해당하는 밑에 값을 빈으로 설정
 com.camel.HolomanConfiguration
 
--------------------------------------------------------------------------------------------------------------------------------------------- 
 
HolomanConfiguration Class 
 
@Configuration
@EnableConfigurationProperties(HolomanProperties.class)
public class HolomanConfiguration {
	
	@Bean
	@ConditionalOnMissingBean // 사용하는 쪽에서 해당 빈이있다면 그 빈으로 사용되게 하는 어노테이션
	public Holoman holoman(HolomanProperties properties) { // 빈을 사용하게 될때 해당 프로젝트에서 프로퍼티로 설정하면 HolomanProperies클래스에 멤버변수에 입력됨 
		Holoman holoman = new Holoman();
		holoman.setHowLong(properties.getHowLong());
		holoman.setName(properties.getName());
		return holoman;
	}

}

---------------------------------------------------------------------------------------------------------------------------------------------

Holoman Class

public class Holoman {
	
	String name;
	int howLong;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHowLong() {
		return howLong;
	}
	public void setHowLong(int howLong) {
		this.howLong = howLong;
	}
	
	@Override
	public String toString() {
		return "Holoman [name=" + name + ", howLong=" + howLong + "]";
	}
	

}

---------------------------------------------------------------------------------------------------------------------------------------------

@ConfigurationProperties("holoman") // 사용하게될 프로젝트에서 프로퍼티로 활용할 수 있음
public class HolomanProperties {
	String name;
	int howLong;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHowLong() {
		return howLong;
	}
	public void setHowLong(int howLong) {
		this.howLong = howLong;
	}

}

---------------------------------------------------------------------------------------------------------------------------------------------

```
#### 자동설정 사용

```
pom.xml   // 메이븐 프로젝트를 터미널에서 mvn install하여 로컬에 저장
<dependency>
	<groupId>com.camel</groupId>
    <artifactId>spring-boot-configuration</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>

```

#### <a href="https://github.com/ChaHyunHo/spring-boot-study/blob/0a63af1ee68ec2e438e41426cc2d9e2fded684db/src/main/resources/application.properties#L1">프로젝트 프로퍼티 설정


#### <a href="https://github.com/ChaHyunHo/spring-boot-study/blob/59677da9509f81d184c3a5b39109c32ecd35a7d1/src/main/java/com/camel/camel_boot_ex/CamelBootEx1Application.java#L79">메이븐 사용<a>



# 내장 웹 서버 이해하기
	CamelBootEx1Application.java 주석 참조
	스프링 부트 자체가 서버가 아님 

# 프로퍼티 우선 순위
	1. 유저 홈 디렉토리에 있는 spring-boot-dev-tools.properties 
	2. 테스트에 있는 @TestPropertySource 
	3. @SpringBootTest 애노테이션의 properties 애트리뷰트 
	4. 커맨드 라인 아규먼트 5. SPRING_APPLICATION_JSON (환경 변수 또는 시스템 프로티) 에 들어있는 프로퍼티 
	6. ServletConfig 파라미터 
    7. ServletContext 파라미터 
    8. java:comp/env JNDI 애트리뷰트 
    9. System.getProperties() 자바 시스템 프로퍼티 
    10. OS 환경 변수 
    11. RandomValuePropertySource 
    12. JAR 밖에 있는 특정 프로파일용 application properties 
    13. JAR 안에 있는 특정 프로파일용 application properties 
    14. JAR 밖에 있는 application properties 
    15. JAR 안에 있는 application properties 
    16. @PropertySource 
    17. 기본 프로퍼티 (SpringApplication.setDefaultProperties) 
  	
  	application.properties 우선 순위 (높은게 낮은걸 덮어 씁니다.) 
	1. file:./config/ 
	2. file:./ 
	3. classpath:/config/ 
	4. classpath:/ 
	
	랜덤값 설정하기 
	● ${random.*}
	 
	플레이스 홀더 
	● name = hyunho 
	● fullName = ${name} cha
	
	타입-세이프 프로퍼티 @ConfigurationProperties 
	● 여러 프로퍼티를 묶어서 읽어올 수 있음 
	
	● 빈으로 등록해서 다른 빈에 주입할 수 있음 
		○ @EnableConfigurationProperties 
		○ @Component 
		○ @Bean 
		
	● 융통성 있는 바인딩 
		○ context-path (케밥) 
		○ context_path (언드스코어) 
		○ contextPath (캐멀) 
		○ CONTEXTPATH 
		
	● 프로퍼티 타입 컨버전 
		○ @DurationUnit 
		
	● 프로퍼티 값 검증 
		○ @Validated 
		○ JSR-303 (@NotNull, ...) 
		
	● 메타 정보 생성 
	
	● @Value 
		○ SpEL 을 사용할 수 있지만... 
		○ 위에 있는 기능들은 전부 사용 못합니다. 
	
  	  
    
  
	

