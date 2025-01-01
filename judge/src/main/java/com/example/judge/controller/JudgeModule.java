package com.example.judge.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JudgeModule {
	public static int judge(File compiledFile) throws IOException {
		ProcessBuilder pb = new ProcessBuilder("java", "Solution");
		pb.directory(compiledFile.getParentFile());
		Process process = pb.start();

		String BASE_PATH = "/Users/hsb/Desktop/싸피/judge/src/main/resources/src/";

		// 입력 파일과 출력 파일 준비
		BufferedReader inputBr = new BufferedReader(new FileReader(BASE_PATH + "PJT19_input.txt"));
		BufferedReader outputBr = new BufferedReader(new FileReader(BASE_PATH + "PJT19_output.txt"));
		BufferedWriter processInput = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
		BufferedReader processOutput = new BufferedReader(new InputStreamReader(process.getInputStream()));

		// 입력 데이터를 프로세스에 전달
		String inputLine;
		while ((inputLine = inputBr.readLine()) != null) {
			processInput.write(inputLine);
			processInput.newLine();
		}
		processInput.flush();
		processInput.close(); // 입력 완료 후 반드시 닫아줌

		// 실제 출력값 수집
		List<String> actualOutput = new ArrayList<>();
		String outputLine;
		while ((outputLine = processOutput.readLine()) != null) {
			actualOutput.add(outputLine.trim());
		}

		// 예상 출력값 수집
		List<String> expectedOutput = new ArrayList<>();
		while ((outputLine = outputBr.readLine()) != null) {
			expectedOutput.add(outputLine.trim());
		}

		// 결과 비교
		if (actualOutput.size() != expectedOutput.size()) {
			System.out.println("출력 길이가 다릅니다.");
			System.out.println("예상 출력: " + expectedOutput);
			System.out.println("실제 출력: " + actualOutput);
			return 1;
		}

		for (int i = 0; i < expectedOutput.size(); i++) {
			if (!actualOutput.get(i).equals(expectedOutput.get(i))) {
				System.out.println("출력이 다릅니다.");
				System.out.println("라인 " + (i + 1));
				System.out.println("예상: " + expectedOutput.get(i));
				System.out.println("실제: " + actualOutput.get(i));
				return 1;
			}
		}

		// 모든 스트림 닫기
		inputBr.close();
		outputBr.close();
		processOutput.close();

		return 0;
	}
}