## PJT#20: Create Next App + React + Typescript + next.js

### 📋 학습 목표

- React의 기본 개념과 철학 학습
- next.js 를 공부하면서 SSR의 중요성 학습
- TypeScript 를 사용하면서 프론트엔드 정적 타입 문법 학습

  <br>

---

#### 1. React란?

Meta(Facebook)d이 개발한 JavaScript UI 라이브러리이다.

공식 문서에 따르면 "사용자 인터페이스를 구축하기 위한 선언적이고 효율적이며 유연한 JavaScript 라이브러리"로 정의된다.

#### 2. SSR(Server-Side Rendering)

서버에서 페이지를 완전히 렌더링하여 클라이언트로 전송하는 기술이다.

장점은 아래와 같다.

1. 성능 최적화

- First Contentful Paint (FCP) 개선
- Time To First Byte (TTFB) 최적화
- 메인 스레드 부하 감소

2. SEO 최적화

- 크롤러에 완성된 HTML 제공
- 메타데이터 동적 생성 가능

#### 3. TypeScript(정적 타입)

JavaScript에 타입 시스템을 추가한 프로그래밍 언어이다.

```js
// 인터페이스 정의 예시
interface User {
  id: number;
  name: string;
  email?: string; // 선택적 프로퍼티
}

// 제네릭 사용 예시
function getFirstElement<T>(arr: T[]): T | undefined {
  return arr[0];
}
```
