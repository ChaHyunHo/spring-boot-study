package com.camel.camel_boot_ex.sample;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK) // webEnvironment 환경이 이 테스트에 MOCK 환경으로 잡혀있음
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// @AutoConfigureMockMvc
class SampleControllerTest {
	
	/* @AutowiredMockMvc mockMvc; // 내장톰캣 구동 안할시 */
	
	// 직접 내장톰캣 서버에 요청을 보낼때 사용함.
	@Autowired TestRestTemplate testRestTemplate; 
	
	// 컨트롤러 까지만 테스트 하고싶을 경우
	// 애플리케이션 컨텍스트 안에들어있는 SampleService 빈을 mockSampleService로 교체한다.
	@MockBean
	private SampleService mockSampleService;
	
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
		String result = testRestTemplate.getForObject("/bye", String.class);
		assertThat(result).isEqualTo("hellohyunho");
	}
	
	@Test
	public void mockSampleServiceHelloTest() throws Exception {
		when(mockSampleService.getName()).thenReturn("cha");
		
		String result = testRestTemplate.getForObject("/bye", String.class);
		assertThat(result).isEqualTo("hello");
	}

}
