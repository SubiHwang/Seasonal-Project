## PJT#4: 아두이노의 이해와 실습

### 📋 학습 목표

- 아두이노의 구조와 브레드 보드 이해
- 초음파 센서 작동 코드 작성
  <br>

---

### 코드

- TRIG 핀으로 초음파 발생
- ECHO 핀으로 반사파 수신
- 왕복 시간으로 거리 계산 (거리 = 시간 × 음속 ÷ 2)
- 시리얼 모니터로 거리값 출력

```cpp
// 초음파 센서 핀 설정
const int TRIG = 9;  // 트리거 핀
const int ECHO = 8;  // 에코 핀

void setup() {
 Serial.begin(9600);
 pinMode(TRIG, OUTPUT);
 pinMode(ECHO, INPUT);
}

void loop() {
 // 트리거 신호 발생
 digitalWrite(TRIG, LOW);
 delayMicroseconds(2);
 digitalWrite(TRIG, HIGH);
 delayMicroseconds(10);
 digitalWrite(TRIG, LOW);

 // 거리 계산
 long duration = pulseIn(ECHO, HIGH);
 float distance = duration * 0.034 / 2;  // cm 단위로 변환

 // 결과 출력
 Serial.print("Distance: ");
 Serial.print(distance);
 Serial.println(" cm");

 delay(500);  // 0.5초 간격으로 측정
}
```
