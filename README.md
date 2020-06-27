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
	
resources/META-INF/spring.factories

org.springframework.boot.autoconfigure.EnableAutoConfiguration=\   (EnableAutoConfiguration켜져 있으면) 이 키에해당하는 밑에 값을 빈으로 설정
 com.camel.HolomanConfiguration
 
 
 
 
HolomanConfiguration Class 
 
@Configuration
@EnableConfigurationProperties(HolomanProperties.class)
public class HolomanConfiguration {
	
	@Bean
	@ConditionalOnMissingBean // 사용하는 쪽에서 해당 빈이있다면 그 빈으로 사용되게 하는 어노테이션
	public Holoman holoman(HolomanProperties properties) {
		Holoman holoman = new Holoman();
		holoman.setHowLong(properties.getHowLong());
		holoman.setName(properties.getName());
		return holoman;
	}

}

```

