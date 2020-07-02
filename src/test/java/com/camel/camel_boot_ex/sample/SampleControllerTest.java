package com.camel.camel_boot_ex.sample;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.system.OutputCaptureRule;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK) // webEnvironment 환경이 이 테스트에 MOCK 환경으로 잡혀있음

// 스프링 부트 테스트는 springApplication main 쪽을 찾아간다.
// 따라서 어플리케이션의 모든 빈을 스캔하여 테스트를 하게된다.
// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)


// 슬라이스 테스트를 하고싶은경우 ... 각각에 맞게 어노테이션을 사용
// @JsonTest json 슬라이스 테스트 *레퍼런스 참조* 

//@AutoConfigureMockMvc로 주석을 달아 비@WebMvcTest(예: @SpringBootTest)에서 
//MockMvc를 자동으로 구성할 수도 있다. MockMvc를 사용하는 예는 다음과 같다.
// @AutoConfigureMockMvc 자동으로 mock을 이용하여 MVC 테스트를 가능하게 해줌

// 컨트롤러 하나만 테스트 하고 싶을 경우 *SampleController만 등록됨 / 일반적인 컴퍼넌트는 빈으로 등록되지 않는다.
// 따라서 해당 컨트롤러이외에는 MockBean을 이용하여 필요한 것들을 추가해주면된다.
@WebMvcTest(SampleController.class)
public class SampleControllerTest {
	
	
	//테스트 유틸중 하나
	@Rule
	public OutputCaptureRule outputCaptureRule = new OutputCaptureRule();
	
	// 내장톰캣 구동 안할시 
	@Autowired
	MockMvc mockMvc; 
	
	// 직접 내장톰캣 서버에 요청을 보낼때 사용함.
	// @Autowired TestRestTemplate testRestTemplate; 
	
	// 컨트롤러 까지만 테스트 하고싶을 경우
	// 애플리케이션 컨텍스트 안에들어있는 SampleService 빈을 mockSampleService로 교체한다.
	@MockBean
	SampleService mockSampleService;
	
	// 웹플러스를 이용한 비동기 웹 클라이언트 테스트
	// @Autowired WebTestClient webTestClient;
	
	@Test
	@Ignore
	public void hello() throws Exception {
		/*
			mockMvc.perform(get("/hello")).andExpect(status().isOk()).andExpect(content().string("hellohyunho"))
			.andDo(print()); // print()는 모든 결과값을 콘솔로 찍어줌
		*/
	}
	
	@Test
	@Ignore
	public void helloRealServerClientTest() throws Exception {
		// String result = testRestTemplate.getForObject("/bye", String.class);
		// assertThat(result).isEqualTo("hellohyunho");
	}
	
	@Test
	@Ignore
	public void mockSampleServiceHelloTest() throws Exception {
		// when(mockSampleService.getName()).thenReturn("cha");
		
		// String result = testRestTemplate.getForObject("/hi", String.class);
		// assertThat(result).isEqualTo("hi cha");
	}
	
	@Test
	@Ignore
	public void webTestClientSampleServiceTest() throws Exception {
		when(mockSampleService.getName()).thenReturn("cha");
		
		// webTestClient.get().uri("/hi").exchange().expectStatus().isOk().expectBody(String.class).isEqualTo("hi cha");
	}
	
	@Test
	public void sliceControllerTest() throws Exception {
		when(mockSampleService.getName()).thenReturn("cha");
		
		mockMvc.perform(get("/hi"))
			.andExpect(content().string("hi cha")).andDo(print());
		
		assertThat(outputCaptureRule)
				.contains("test1")
				.contains("test2")
				;
	}
	
	
	/*
	 * README.md에 작성하기 귀찮아서 여기에 추가로 정리 
	 * 테스트 유틸이 있는데 종류는 
	 * OutputCaptur
	 * 	해당 빈에 찍힌 로그를 캡쳐한디. 테스트할시 찍힌 로그가 나왔는지 확인할때 사용
	 * TestPropertyValues
	 * TestRestTmplate
	 * ConfigFileApplicationContextInitializer
	 * 
	 */
	

}
