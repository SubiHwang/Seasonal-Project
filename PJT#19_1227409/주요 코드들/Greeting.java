package com.example.judge.controller;

public record Greeting (String user, String answer){}
//쉽게 말하자면 dto class임.
//record 클래스는 불변(immutable) 데이터 객체를 만드는데 최적화되어 있어 REST API의 응답 모델로 적합하다.
//자동으로 생성자, getter, equals(), hashCode(), toString() 메서드를 제공

//Spring Boot의 웹 스타터에 포함된 Jackson 라이브러리가 자동으로
//* Java 객체 → JSON (직렬화)
//* JSON → Java 객체 (역직렬화) 처리를 해준다.
