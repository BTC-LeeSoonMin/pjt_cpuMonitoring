# Cpu Monitoring Project For 테라인터내셔널

## 프로젝트 소개
<p align="justify">
‘서버 CPU 사용률 모니터링 시스템 구현’<br>
서버의 CPU 사용률을 분, 시, 주, 월 단위로 모니터링하고 이를 데이터베이스에 저장하여 API를 통해 조회할 수 있는 시스템을 구현합니다.
제공된 API 는 FrontEnd 화면에서 기간별 분, 시, 일 단위 그래프로 표현 될 것으로 가정합니다.
</p>


## 프로젝트 요구사항
<details>
  <summary>프로젝트 요구사항 펼치기</summary>
  
* 프로젝트 설정
  * Spring Boot 사용
  * Gradle 사용
  * Java 11 이상 사용
* 데이터베이스 설정
  * H2를 개발 및 테스트에 사용
  * MariaDB를 운용 데이터베이스로 사용
  * JPA 사용
* 기능 요구 사항
  * 데이터 수집 및 저장
    * CPU 사용률 수집: 서버의 CPU 사용률을 분 단위로 수집합니다.
    * 데이터 저장: 수집된 데이터를 데이터베이스에 저장합니다.
  * 데이터 조회 API
    * 분 단위 조회: 지정한 시간 구간의 분 단위 CPU 사용률을 조회합니다.
    * 시 단위 조회: 지정한 날짜의 시  단위 CPU 최소/최대/평균 사용률을 조회합니다.
    * 일 단위 조회: 지정한 날짜 구간의 일  단위 CPU 최소/최대/평균 사용률을 조회합니다.
    * Swagger를 사용하여 API 문서화를 설정하세요.
  * 데이터 제공 기한
    * 분 단위 API : 최근 1주 데이터 제공
    * 시 단위 API : 최근 3달 데이터 제공
    * 일 단위 API : 최근 1년 데이터 제공
* 예외 처리
  * 데이터 수집 실패 시 예외를 처리하고 로그를 남깁니다.
  * API 요청 시 잘못된 파라미터에 대한 예외를 처리합니다.
* 테스트
  * 유닛 테스트: 서비스 계층과 데이터베이스 계층의 유닛 테스트를 작성하세요.
  * 통합 테스트: 컨트롤러 계층의 통합 테스트를 작성하세요.
  
</details>

## Getting Started
### 마리아DB Setting
* 더미 데이터 dump 파일 다운로드 
  * https://drive.google.com/file/d/1CG1eWKtsx5Ekv3wLiSHK0zL0_eOA38j-/view?usp=drive_link
* DB 연결 시 정보 ( 아래 참고 이미지 보시면 좋아요 )
  * username : root
  * pw : 1234
  * port : 3307
* HeidiSQL를 통해 dump import
  * 참고 블로그 : https://marumaru.tistory.com/5
<details>
    <summary>DB 연결 시 정보 참고 이미지</summary>

   * ![image](https://github.com/BTC-LeeSoonMin/pjt_cpuMonitoring/assets/134909905/509452df-f6e0-4192-98fc-1905b98bdf15)
</details>

### IntelliJ Setting
* File - Setting - Build, Exection, Deployment - Build Tools - Gradle
  * Build and run using: IntelliJ IDEA
  * Run tests using: IntelliJ IDEA
* Gradle JVM
  * corretto-20 또는 17
<details>
    <summary>IntelliJ Setting 참고 이미지</summary>

   * ![image](https://github.com/BTC-LeeSoonMin/pjt_cpuMonitoring/assets/134909905/caaf734f-0e58-43b6-b538-f52c3bf7ac93)
</details>

## 프로젝트 구조
<details>
  <summary>파일 구조 펼치기</summary>

  ![image](https://github.com/BTC-LeeSoonMin/pjt_cpuMonitoring/assets/134909905/04caeaac-2d57-4c26-a4ca-6a5ea086f59c)
</details>

## Get 호출 API ( 저는 postman을 이용해서 test 했습니다. )
* 분 단위 조회
  * `http://localhost:8080/api/getMinuteData?startDate=2024-05-26T01:00&endDate=2024-05-26T02:00`
* 시 단위 조회
  * `http://localhost:8080/api/getHourData?startDate=2024-03-26&endDate=2024-05-26`
* 일 단위 조회
  * `http://localhost:8080/api/getDayData?startDate=2023-07-26&endDate=2024-05-26`

<details>
  <summary>Get 호출 API Postman 이미지</summary>

  ![image](https://github.com/BTC-LeeSoonMin/pjt_cpuMonitoring/assets/134909905/830f221a-c1a9-4106-8208-71f47a1533f7)
  ![image](https://github.com/BTC-LeeSoonMin/pjt_cpuMonitoring/assets/134909905/6f407827-3be4-44c9-9404-0132e66cf422)
  ![image](https://github.com/BTC-LeeSoonMin/pjt_cpuMonitoring/assets/134909905/ffa5f430-d8ec-4453-b66d-65a92f3eeb31)
</details>



# 요구사항의 해결 방법
* CPU 사용률 수집 : 서버의 CPU 사용률을 분 단위로 수집합니다.
  * 운영 체제의 관련 정보를 제공하는 OperatingSystemMXBean 인스턴스를 사용했고 분 단위로 수집하기 위해 @Scheduled 어노테이션을 이용하였습니다.
  * CpuMonitoringApplication에 위치시켜 Run시 항시 분 단위로 수집 합니다.
* 데이터 저장: 수집된 데이터를 데이터베이스에 저장합니다.
  * 처음 CPU 사용률 수집을 개발 당시 H2 db를 이용하여 정상적으로 수집됨을 확인한 뒤, mariaDB 환경으로 변경을 하였습니다.
  * 현재는 더미데이터를 제공하기때문에 주석처리 되어 있어 db에 저장되지 않습니다.
* 데이터 조회 API
  * 분 단위 조회: 지정한 시간 구간의 분 단위 CPU 사용률을 조회합니다.
    * 입력 값(원하는 기간): 년월일시분 입력 (ex : startDate=2024-05-26T01:00)
    * `원하는 기간을 입력값`으로 `요청`하면 해당 하는 `분 단위 CPU 사용률을 응답` 합니다.
  * 시 단위 조회(일 단위 조회 포함) : 지정한 날짜의 시또는 일 단위 CPU 최소/최대/평균 사용률을 조회합니다.
    * 입력 값(원하는 기간): 년월일시 입력 (ex : startDate=2024-03-26)
    * JPA 레포지토리에서는 LocalDate 형식이 오류를 발생시켜 LocalDateTime 형식으로 변환하기위해 서비스단에서 변환 로직을 작성했습니다.
    * JPQL을 통해 SQL문을 작성했습니다.
  * Swagger를 사용하여 API 문서화를 설정하세요.
    * 프로젝트가 `Run 상태`에서 `http://localhost:8080/swagger-ui/index.html#/`로 웹 접속하면 확인할 수 있습니다.
<details>
  <summary>Swagger를 사용하여 API 문서화 이미지</summary>

  ![image](https://github.com/BTC-LeeSoonMin/pjt_cpuMonitoring/assets/134909905/e4cc2273-1937-4f25-8513-1884fe20f741)
</details>

* 데이터 제공 기한
  * 분 단위 API : 최근 1주 데이터 제공
    * 서비스단에 구현되어 있으며 startDate 기준 1주일을 `초과`하면 `IllegalArgumentException` 해당 예외를 throw 합니다.
  * 시 단위 API : 최근 3달 데이터 제공
    * 서비스단에 구현되어 있으며 startDate 기준 3달을 `초과`하면 `IllegalArgumentException` 해당 예외를 throw 합니다.
  * 일 단위 API : 최근 1년 데이터 제공
    * 서비스단에 구현되어 있으며 startDate 기준 1년을 `초과`하면 `IllegalArgumentException` 해당 예외를 throw 합니다.
* 예외 처리
  * 데이터 수집 실패 시 예외를 처리하고 로그를 남깁니다.
    * try-catch문으로 예외 발생 시 에러 파악 자세히 할 수 있도록 에러메시지와 StackTrace() 구분자`/`을 사용해서 db error_log 테이블에 저장됩니다.
<details>
  <summary>데이터 수집 실패 예외 처리 로그 이미지</summary>

  ![image](https://github.com/BTC-LeeSoonMin/pjt_cpuMonitoring/assets/134909905/95530599-9fd6-4eb8-96fc-f6f581efb93f)
</details>

* API 요청 시 잘못된 파라미터에 대한 예외를 처리합니다.
  * 서비스 단에서 `IllegalArgumentException` 예외 발생 시 `GlobalExceptionHandler`을 통해 글로벌로 예외 처리 합니다.
  * 현재는 제공 기한 초과 했을 때, 날짜 형식이 틀렸을 때 2가지 경우가 있습니다.
  * 데이터 제공 기한이 초과한 API : `http://localhost:8080/api/getMinuteData?startDate=2024-03-26T01:00&endDate=2024-05-26T02:00`
  * 날짜 형식이 틀린 API : `http://localhost:8080/api/getMinuteData?startDate=202-03-26T01:00&endDate=2024-05-26T02:00`
<details>
  <summary>잘못된 파라미터에 대한 예외 이미지</summary>

  ![image](https://github.com/BTC-LeeSoonMin/pjt_cpuMonitoring/assets/134909905/d4b1af7c-d346-4b81-8e83-48318c802f9c)
  ![image](https://github.com/BTC-LeeSoonMin/pjt_cpuMonitoring/assets/134909905/76b52f38-50cc-4932-89e5-47041fa71e58)
</details>

* 테스트
  * 유닛 테스트: 서비스 계층과 데이터베이스 계층의 유닛 테스트를 작성하세요.
    * Given-When-Then 기법을 알고되었고 적용하고자 노력했습니다. 
    * 서비스 계층
      * 서비스 단에서 이루어지는 로직은 `데이터 제공 기한 예외 처리 부분`과 `LocalDate -> LocalDateTime 변환`하는 로직이 정상적으로 동작하면 통과합니다.
    * 데이터베이스 계층
      * 테스트 데이터를 제공하였으며, 분 단위 같은 경우 `startTime부터 endTime 범위 내에 적합`하다면 통과 / 시,일 단위 같은 경우 `Min/Avg/Max 값이 동일하면 통과`하도록 테스트 로직 구현 했습니다.
  * 통합 테스트: 컨트롤러 계층의 통합 테스트를 작성하세요.
    * `SpringBootTest`은 `프로젝트 내부에 있는 모든 스프링 빈들을 스캔하여 등록하고, 애플리케이션 컨텍스트를 생성하여 테스트를 실행`합니다. 또한 `운영환경과 가장 유사한 테스트가 가능하다는 장점`이 있기때문에 `전체적인 Flow가 제대로 동작하는지 검증`할 수 있습니다.
    * `애플리케이션의 설정과 모든 빈들을 스캔하여 등록하기 때문에 동작 시간이 오래 걸리고 무겁다는 점`또한 중요합니다. 또한 테스트 단위가 크기 때문에 디버깅이 어려울 수 있습니다.

## 과제 후기
먼저, 과제 테스트를 경험할 수 있게 해주신 테라인터내셔널에 감사드립니다. <br><br>
이번 과제가 '실무에 가깝지 않을까?'라는 생각을 했고, 처음 사용하는 기술이 많아 생각보다 어려움을 겪었습니다. <br> 
H2, JPA, jUnit을 이용한 테스트 모두 처음 접하는 기술들이었기에 관련 포스팅들을 많이 찾고 학습하며 진행하였습니다. <br>
이번 과제를 통해 JPA의 기본적인 동작 과정을 익히게 되어 기쁩니다.<br>
아쉬운 점은 개발을 먼저 진행하고 후에 테스트를 작성한 것입니다. <br> 
이유는 테스트 코드 작성이 처음이어서 마감기한을 넘길 것 같아 개발을 먼저 진행한 후 테스트 코드를 작성했습니다. <br>
다음 기회에는 테스트 코드를 먼저 작성한 후 개발을 진행하는 진정한 TDD를 경험할 수 있으면 좋겠습니다.<br><br>
지금까지 테라인터내셔널 과제를 위한 4일간의 폭풍 개발 후기였습니다. 감사합니다.

