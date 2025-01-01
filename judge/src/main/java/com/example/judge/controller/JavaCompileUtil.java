package com.example.judge.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class JavaCompileUtil {

	//java.lang 패키지에 있는 ProcessBuilder 클래스 사용
	public static boolean compileJava(File input) throws IOException {
		//ProcessBuilder에서 실행할 명령어("javac")와 인자("Test.java")를 받아둔다.
		ProcessBuilder ps = new ProcessBuilder("javac", input.getAbsolutePath());
		//start 호출 시 시스템에 설치된 javac 컴파일러를 실행시킨다.
		//"javac /Users/hsb/Desktop/ssafy-github/Seasonal-Project/judge/Solution.java" 같은 명령어로 소스가 컴파일 됨
		Process process = ps.start();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
			String line;
			StringBuilder output = new StringBuilder();
			while ((line = br.readLine()) != null) {
				output.append(line).append("\n");

			}

			try {
				int exitCode = process.waitFor(); //waitFor(): 컴파일이 완료될 때까지 대기

				if (exitCode != 0) {
					System.out.println("컴파일 오류");
					return false;
				}

			} catch (InterruptedException e) { //긴 컴파일 작업을 기다리는 동안 사용자가 작업을 취소하려고 할 때 인터럽트 에러 발생
				System.out.println("인터럽트 오류");
				process.destroy(); // 프로세스 강제 종료
			}

		}
		return true;
	}

}