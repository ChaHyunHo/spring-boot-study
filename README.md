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

<a href="https://github.com/ChaHyunHo/spring-boot-study/blob/0a63af1ee68ec2e438e41426cc2d9e2fded684db/src/main/resources/application.properties#L1">프로젝트 프로퍼티 설정

