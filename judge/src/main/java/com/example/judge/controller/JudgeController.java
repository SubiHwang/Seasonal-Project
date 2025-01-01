package com.example.judge.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
@Tag(name = "Judge", description = "코드 저지 API")
public class JudgeController {

	@Operation(summary = "코드 제출", description = "코드를 제출하고 결과를 반환합니다.")
	@PostMapping(value = "/greeting", consumes = MediaType.TEXT_PLAIN_VALUE) //Body로 오는 것은 Post 방식.
	public ResponseEntity<?> greeting(@RequestBody String str) {
		//HTTP 요청의 body는 한 번만 읽을 수 있기 때문에 하나의 매개변수만 가능하다.

		//String str = greeting.answer();
		
		try {

			File file = StringToFileConverter.convertStringToFile(str);
			System.out.println("String을 파일 변환까지는 완료");

			if (JavaCompileUtil.compileJava(file)) {

				System.out.println("컴파일까지는 완료");

				int result = JudgeModule.judge(file);

				System.out.println("성공 0 실패 1 : " + result);

				switch (result) {
					case 0:
						return ResponseEntity.ok().body("코드 성공입니다.");
					default:
						return ResponseEntity.badRequest().body("코드 실패입니다.");
				}

			} else {
				return ResponseEntity.unprocessableEntity().body("컴파일 오류 입니다.");
			}


		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body("파일 처리 중 오류가 발생했습니다.");
		}

	}

}
