package com.example.judge.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
 문자열을 파일로 변환해주는 클래스
 */
public class StringToFileConverter {

	public static File convertStringToFile(String content) throws IOException {

		System.out.println("String을 파일 변환 코드로 바꾸는 작업 코드 실행");

		String basePath = "/Users/hsb/Desktop/ssafy-github/Seasonal-Project/judge";
		String fileName = "Solution.java";
		String fullPath = basePath + File.separator + fileName;

		Path path = Paths.get(fullPath); //파일 경로 생성
		File file = path.toFile();

		//파일을 열고 사용한 후에는 반드시 close를 해줘야 하기 때문이다.
		//Java 7부터 도입된  "try-with-resources" : try 블록이 끝나면 자동으로 close()가 호출된다.
		// try() 괄호 안에는 AutoCloseable 인터페이스를 구현한 객체만 올 수 있습니다
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
			bw.write(content);
			bw.write("\n");
		}

		return file;

	}

}
