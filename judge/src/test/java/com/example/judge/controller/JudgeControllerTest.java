package com.example.judge.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc //실제 서버를 실행하지 않고도 API 요청을 테스트할 수 있게 해주는 도구인 MockMvc를 자동으로 구성
class JudgeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void greetingTest() throws Exception {
		String jsonRequest = """
				{
				"user":"구미 4반 황수비",
				"answer":"import java.io.BufferedReader;
				               import java.io.IOException;
				               import java.io.InputStreamReader;
				               import java.util.Arrays;
				               import java.util.StringTokenizer;
				               
				               public class Solution {
				               
				               	static int T, D, W, K;
				               	static int[][] map;
				               	static int[][] otherMap;
				               	static int min;
				               	static boolean[] isSelected;
				               
				               	public static void main(String[] args) throws IOException {
				               
				               		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				               		StringTokenizer st;
				               
				               		T = Integer.parseInt(br.readLine());
				               
				               		for (int t = 1; t <= T; t++) {
				               			st = new StringTokenizer(br.readLine());
				               			D = Integer.parseInt(st.nextToken());
				               			W = Integer.parseInt(st.nextToken());
				               			K = Integer.parseInt(st.nextToken());
				               
				               			map = new int[D][W];
				               			otherMap = new int[D][W];
				               			isSelected = new boolean[D];
				               
				               			for (int i = 0; i < D; i++) {
				               				st = new StringTokenizer(br.readLine());
				               				for (int j = 0; j < W; j++) {
				               					map[i][j] = Integer.parseInt(st.nextToken());
				               					otherMap[i][j] = map[i][j];
				               				}
				               			}
				               
				               			//약품의 최소 투입 횟수
				               			min = D;
				               
				               			subSet(0);
				               
				               			System.out.println("#" + t + " " + min);
				               		}
				               	}
				               
				               	private static void subSet(int depth) {
				               
				               		if (depth == D) {
				               			inject(0, 0);
				               			reset();
				               			return;
				               		}
				               
				               		isSelected[depth] = true;
				               		subSet(depth + 1);
				               		isSelected[depth] = false;
				               		subSet(depth + 1);
				               
				               	}
				               
				               	private static void reset() {
				               		for (int i = 0; i < D; i++) {
				               			for (int j = 0; j < W; j++) {
				               				map[i][j] = otherMap[i][j];
				               			}
				               		}
				               	}
				               
				               	private static void inject(int injectionCnt, int idx) { //약물 투입
				               
				               		if (injectionCnt >= min) return; //여기서 약물을 판단한다.
				               
				               		if (idx == D) {
				               			if (isPassed()) min = injectionCnt;
				               			return;
				               		}
				               
				               		if (isSelected[idx]) { //투입하는 애들에 대해서
				               			Arrays.fill(map[idx], 0); //0 주입하고
				               			inject(injectionCnt + 1, idx + 1); //약물 개수 증가
				               
				               			//0주입 다하면 1주입 시작
				               			Arrays.fill(map[idx], 1);
				               			inject(injectionCnt + 1, idx + 1);
				               
				               		} else {
				               			inject(injectionCnt, idx + 1);
				               		}
				               
				               
				               	}
				               
				               	private static boolean isPassed() {
				               
				               		for (int i = 0; i < W; i++) {
				               			int start = 0, end = 1;
				               			int len = 1;
				               			while (end < D) {
				               
				               				if (map[start][i] == map[end][i]) {
				               					end++;
				               				} else {
				               					start = end;
				               					end++;
				               				}
				               
				               				len = Math.max(len, end - start);
				               
				               				if (len >= K) break;
				               
				               			}
				               
				               			if (len < K) return false;
				               
				               		}
				               
				               		return true;
				               
				               	}
				               }
				               
				               "
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
		System.out.println("제출한 코드: " + result.getResponse().getContentAsString());
	}

}