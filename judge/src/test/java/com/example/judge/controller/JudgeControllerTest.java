package com.example.judge.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.net.http.HttpResponse;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc //실제 서버를 실행하지 않고도 API 요청을 테스트할 수 있게 해주는 도구인 MockMvc를 자동으로 구성
class JudgeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void greetingTest() throws Exception{
		String jsonRequest = """
				{
				"user":"구미 4반 황수비",
				"answer":"Hello"
				}
				""";
		MvcResult result = mockMvc.perform(post("/greeting")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(jsonRequest))
				.andReturn();

		//MvcResult만 출력하면 객체의 참조값만 나온다.
		/*
		[요청 시]
		"{"user":"John"}" → HttpRequest@abc → MockMvc@123
		(힙 메모리)      (힙 메모리)        (힙 메모리)

		[응답 시]
		MockMvc@123 →HttpResponse @def → "{"user":"John"}"
		(힙 메모리)    (힙 메모리)        (힙 메모리)
		*/
		System.out.println("실행 결과: " + result.getResponse().getContentAsString());
	}

}