package com.example.judge.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class JudgeController {

	@PostMapping("/greeting") //Body로 오는 것은 Post 방식.
	public Greeting greeting (@RequestBody Greeting greeting) {
		//HTTP 요청의 body는 한 번만 읽을 수 있기 때문에 하나의 매개변수만 가능하다.

		//객체가 자바 객체로 변환된 후에 다시 json 객체로 바꿔지는 형태이다.
		return new Greeting(greeting.user().substring(4), greeting.answer());
	}

}
