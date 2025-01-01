package com.example.judge.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
public class JudgeController {

	@PostMapping("/greeting") //Body로 오는 것은 Post 방식.
	public ResponseEntity<?> greeting(@RequestBody Greeting greeting) {
		//HTTP 요청의 body는 한 번만 읽을 수 있기 때문에 하나의 매개변수만 가능하다.

		String str = greeting.answer();


		try {

			File file = StringToFileConverter.convertStringToFile(str, "/Users/hsb/Desktop/ssafy-github/Seasonal-Project/judge");


			if (JavaCompileUtil.compileJava(file)) {
				//컴파일이 완료되었으면 모든 테케와 답이 맞을 경우 코드 성공 리턴


			} else {
				return ResponseEntity.badRequest().body("컴파일 오류 입니다.");
			}


		} catch (IOException e) {
			e.printStackTrace();
		}

		//객체가 자바 객체로 변환된 후에 다시 json 객체로 바꿔지는 형태이다.
		return ResponseEntity.ok().body("성공입니다.");
	}

}
