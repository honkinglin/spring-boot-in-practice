package com.manning.sbip.ch01;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootAppDemoApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void contextLoads() {
		// 验证应用上下文加载成功
	}

	@Test
	void homeEndpointShouldReturnWelcomeMessage() {
		String response = restTemplate.getForObject("http://localhost:" + port + "/", String.class);
		assertThat(response).contains("Welcome to Spring Boot Application!");
	}

	@Test
	void helloEndpointShouldReturnGreeting() {
		String response = restTemplate.getForObject("http://localhost:" + port + "/hello", String.class);
		assertThat(response).contains("Hello, Spring Boot");
	}
}
