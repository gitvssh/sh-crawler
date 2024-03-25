# Table of contents

- [sh-crawler](#-📁-sh-crawler)
- [프로젝트 목표 및 주요 기능](#-프로젝트-목표-및-주요기능)
- [사용 기술 및 프레임워크](#-사용-기술-및-프레임워크)
- [환경 설정 및 설치](#-환경 설정 및 설치)
- [주요 기능 및 구현 방식](#-주요-기능-및-구현-방식)
- [테스트 및 배포](#-테스트-및-배포)
- [추가 정보](#-추가-정보)
- [프로젝트 진행 과정에서 겪은 어려움 및 해결 과정](#-프로젝트-진행-과정에서-겪은-어려움-및-해결-과정)
- [프로젝트 개선 방향 및 향후 계획](#-프로젝트-개선-방향-및-향후-계획)
- [Contributing](#-contributing)
- [License](#-license)
- [Author](#-author)

# 📁 sh-crawler

이 프로젝트는 관심있는 웹 사이트를 등록하여 주기적으로 크롤링하여 데이터를 수집하는 프로젝트입니다.

## 🎯 프로젝트 목표 및 주요기능

인터넷에 존재하는 다양한 정보의 가치가 날이 갈수록 높아지고 있습니다. 
<br>
이러한 정보들을 효과적으로 수집하고 관리하여 가치를 활용하기 위해서 사용자가 원하는 웹 사이트를 등록하고 주기적으로 크롤링하여 데이터를 수집하는 서비스를 제공합니다.
<br>
프로젝트의 주요 목표는 다음과 같습니다.

### 수집
- 사용자가 원하는 웹 사이트를 등록하고 주기적으로 크롤링하여 데이터를 수집합니다.
- 사용자는 수집한 데이터를 조회하고 관리할 수 있습니다.
- 사용자는 수집한 데이터를 다운로드할 수 있습니다.
- 사용자는 수집한 데이터를 삭제할 수 있습니다.
- 사용자는 수집한 데이터를 수정할 수 있습니다.

### 분석
- 수집한 데이터의 통계 정보를 제공합니다.
- 수집한 데이터를 시각화하여 사용자에게 제공합니다.

### 가공
- 수집한 데이터를 활용하기 쉽도록 사용자가 원하는 형태로 가공합니다.

### API
- 수집한 데이터 및 가공한 데이터를 다른 서비스에 API 형태로 제공할 수 있습니다.

## 🧱 사용 기술 및 프레임워크

다음과 같은 기술로 이 프로젝트를 구현합니다.

### Backend
- Java 17
- Spring Boot 3.0.3
- Spring Data JPA
- Spring Data MongoDB
- Spring Security
- Spring Batch

- lombok
### Persistence
- H2
- MongoDB
- MySQL

### tool
- selenium

# 🛠️ 환경 설정 및 설치
## 설치
프로젝트 실행을 위해선 다음과 같은 환경 구성이 필요합니다.

- application.yml 설정
- MongoDb 설치
- MySQL 설치
- 웹드라이버 설치

### 개발 환경

#### application.yml
```
spring:
  config:
    activate:
      on-profile: local
  jpa:
    database: mysql
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQLDialect
        show_sql: true
        format_sql: true
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: <사용자 MySQL URL>
    username: root
    password: 1234
  data:
    mongodb:
      uri: <사용자 MongoDB URI>
      database: shsystem
  batch:
    jdbc:
      initialize-schema: always
    job:
      name: ${job.name:NONE}
      enabled: true


jasypt:
  encryptor:
    bean: jasyptStringEncryptor
    password: ${JASYPT_PASSWORD}

webdriver:
  path: <사용자 웹드라이버 경로>

logging:
  file:
    name: <사용자 로그 경로>
  logback:
    rollingpolicy:
      max-history: 31
```

#### MongoDB(Docker)
```
docker pull mongo

docker run -d --name mongodb -v <사용자경로>mongo:/data/db   \
-e MONGO_INITDB_ROOT_USERNAME=<루트사용자>   \
-e MONGO_INITDB_ROOT_PASSWORD=<password>   \
-p 27017:27017 mongo
```

#### MySQL(Docker)
```
docker pull mysql

docker run -d --name mysql -v <사용자경로>mysql:/var/lib/mysql   \
-e MYSQL_ROOT_PASSWORD=<password>   \
-e MYSQL_DATABASE=shsystem   \
-p 3306:3306 mysql

```

## 프로젝트 실행 및 사용 방법

### 설치
1. 프로젝트를 클론합니다.
2. application.yml 설정을 완료합니다.
3. MongoDB를 설치합니다.
4. MySQL을 설치합니다.
5. 웹드라이버를 설치합니다.
6. 프로젝트를 실행합니다.
### 사용(현재 개발 진행 중입니다)
7. crawling_source 테이블에 클로링 대상 웹 사이트를 등록합니다.
8. shcrawler.util.parser 클래스에 웹 사이트 파싱 로직을 작성합니다.

# ⚙️ 주요 기능 및 구현 방식
## ETL
- 크롤링 원본 소스는 형태가 다양할 수 있기 때문에 MongoDB를 사용하여 데이터를 저장하였습니다.
- 웹사이트 url 변경, 인터넷 연결 불가 등의 이유로 크롤링이 실패할 수 있기 때문에 크롤링 실패 시에는 크롤링 실패 이력을 저장하였습니다.
## 배치 및 스케쥴링
- 크롤링은 Spring Batch를 사용하여 주기적으로 실행하도록 구현하였습니다.
- 주기적인 실행 외에도 사용자가 원할 때 수동으로 크롤링을 실행할 수 있도록 web api를 구현하였습니다.
## 확장성
- 여러가지 형식의 데이터를 수집하기 위하여 파서를 사용하여 데이터를 가공하였습니다.
- 확장 가능한 구현을 위하여 CrawlingParser의 인터페이스를 정의하고 구현체를 사용하여 데이터를 가공하였습니다.
- 후에 수집과 가공 배치작업을 메세징 시스템을 사용하여 scale-out을 지원하도록 작업을 분리하여 구현하였습니다.
- 추후 크롤링 데이터가 늘어날 경우, SpringBatch의 청크 기능을 사용하여 크롤링을 병렬로 실행할 수 있습니다.
# ✅ 테스트 및 배포

개발 진행 중입니다.

# ℹ️ 추가 정보
## 📈 개발 예정 순서
- 크롤링 기능 구현 (완료)
- 데이터 가공 기능 구현 (현재 작업중)
- 리팩토링
  - Parser 확장을 위한 디자인 패턴 적용
- AWS 배포, 운영
- 모니터링 및 로깅 구현
- 외부 API 구현
  - OAuth2.0 인증서버 구현
- 관리자 페이지 구현
## 🤝 Contributing

If you want to contribute to this project, you can:
<br>

- Open an issue to report a bug or suggest a new feature.
- Fork the repository, make your changes, and submit a pull request.
  <br>
  이 프로젝트에 기여하고 싶으시다면 아래의 방법을 이용해주세요.
  <br>
  <br>

- Issue를 열어 버그를 리포트하거나 새로운 기능을 제안해주세요.
- 리포지토리를 Fork하고 pull reqest을 보내주세요.

## 📝 License

This repository follows the MIT License.
<br>
<br>
이 저장소는 MIT 라이센스를 따릅니다.

## 🧑‍💻 Author

ID : <a href="https://github.com/gitvssh" target="_new">gitvssh</a>
<br>
:email: EMAIL : gmavsks@gmail.com