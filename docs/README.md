# 로또

## 기능 목록

### 기능

- [x] 로또 구입 금액을 입력받는다. 출력: `구입금액을 입력해 주세요.`
- [ ] 구입 금액에 맞는 개수의 로또를 발행하고 출력한다.
    - [x] 금액에 맞게 로또를 발행하고 저장한다.
    - [x] 발행된 로또의 개수를 출력한다. 출력: `n개를 구매하였습니다.`
    - [x] 발행된 로또 번호를 오름차순으로 정렬하여 출력한다. `[1, 2, 3, 4, 5, 6]`
- [x] 당첨 번호를 6개를 콤마로 구분하여 입력받는다.
- [x] 보너스 번호 한 개를 입력받는다.
- [x] 당첨 통계를 출력한다. 출력: `당첨 통계\n---`
    - [x] 각 당첨에 해당하는 로또의 개수와 총 상금을 계산한다.
    - [x] 3개가 일치하는 로또의 개수를 출력한다. 출력: `3개 일치 (5,000원) - n개`
    - [x] 4개가 일치하는 로또의 개수를 출력한다. 출력: `4개 일치 (50,000원) - n개`
    - [x] 5개가 일치하는 로또의 개수를 출력한다. 출력: `5개 일치 (1,500,000원) - n개`
    - [x] 5개가 일치하고 보너스 번호와 일치하는 로또의 개수를 출력한다. 출력: `5개 일치, 보너스 볼 일치 (30,000,000원) - n개`
    - [x] 6개가 일치하는 로또의 개수를 출력한다. 출력: `6개 일치 (2,000,000,000원) - n개`
    - [x] 총 수익률을 출력한다. 출력: `총 수익률은 n%입니다.`

### 검증

> 사용자 입력 검증에 실패하면 `IllegalArgumentException`을 발생시키고, "[ERROR]"로 시작하는 에러 메시지를 출력 후 그 부분부터 입력을 다시 받는다.

- **로또 구입**
    - [x] 로또 구입 금액은 숫자이어야 한다.
    - [x] 로또 구입 금액은 1,000원으로 나누어 떨어져야 한다.
    - [x] 로또 구입 금액은 1,000원 이상이어야 한다.(1개 이상 구매해야 한다.)

- **당첨 번호**
    - [x] 번호는 숫자이어야 한다.
    - [x] 당첨 번호는 모두 중복되지 않아야 한다.
    - [x] 당첨 번호는 6개이어야 한다.
    - [x] 보너스 번호는 당첨 번호에 존재하지 않는 숫자이어야 한다.
    - [x] 모든 번호는 1 이상 45 이하이어야 한다.

### 제약 조건

- indent(인덴트, 들여쓰기) depth를 3이 넘지 않도록 구현한다. 2까지만 허용한다.
    - 예를 들어 `while`문 안에 `if`문이 있으면 들여쓰기는 2이다.
    - 힌트: indent(인덴트, 들여쓰기) depth를 줄이는 좋은 방법은 함수(또는 메서드)를 분리하면 된다.
- 3항 연산자를 쓰지 않는다.
- 함수(또는 메서드)의 길이가 15라인을 넘어가지 않도록 구현한다.
    - 함수(또는 메서드)가 한 가지 일만 잘 하도록 구현한다.
- `else` 예약어를 쓰지 않는다.
    - 힌트: `if` 조건절에서 값을 `return`하는 방식으로 구현하면 `else`를 사용하지 않아도 된다.
    - `else`를 쓰지 말라고 하니 `switch/case`로 구현하는 경우가 있는데 `switch/case`도 허용하지 않는다.
- Java Enum을 적용한다.
- 도메인 로직에 단위 테스트를 구현해야 한다. 단, UI(`System.out`, `System.in`, `Scanner`) 로직은 제외한다.
    - JUnit 5와 AssertJ를 이용하여 본인이 정리한 기능 목록이 정상 동작함을 테스트 코드로 확인한다.
    - 핵심 로직을 구현하는 코드와 UI를 담당하는 로직을 분리해 구현한다.
