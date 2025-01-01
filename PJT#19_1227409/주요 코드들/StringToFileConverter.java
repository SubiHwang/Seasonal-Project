package com.example.judge.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
 문자열을 파일로 변환해주는 클래스
 */
public class StringToFileConverter {

	public static File convertStringToFile(String content, String filePath) throws IOException {


		Path path = Paths.get(filePath); //파일 경로 생성
		Files.createDirectories(path.getParent()); //디렉토리 생성

		File file = path.toFile();

		//파일을 열고 사용한 후에는 반드시 close를 해줘야 하기 때문이다.
		//Java 7부터 도입된  "try-with-resources" : try 블록이 끝나면 자동으로 close()가 호출된다.
		// try() 괄호 안에는 AutoCloseable 인터페이스를 구현한 객체만 올 수 있습니다
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
			bw.write(content);
		}

		return file;

	}

}
