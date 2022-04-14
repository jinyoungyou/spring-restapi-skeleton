# spring-restapi-skeleton

#### Spring Boot - Rest API skeleton 서버 개발 프로젝트


## 사용법
### 빌드
```bash
# 그래들 빌드
./gradlew bootJar
```
### 도커 컨테이너 실행
```bash
# 환경 변수 셋업
cp .env.default .env

# 컨테이너 실행
docker-compose up
```

## 모듈 설명
### todo-api
비지니스 로직 처리
* Todo 도메인 관련 CRUD 처리 (JpaAuditing 적용)
* Spring Security 를 통한 인가 구현 (AuthenticationFilter 적용)
* Authentication 처리 (토큰 발행, 토큰 갱신)


### todo-auth
가입 / 인증 로직 처리
* Account 도메인 처리 (회원 가입, BCryptPasswordEncoder)
* Authentication 처리 (토큰 발행, 토큰 갱신)


### todo-common
프로젝트 공통 로직 처리
* Exception 처리 (Custem Exception, Handler, Response Template)
* Account Domain (Entity, Repository, CustomUserDetailsService)
* Controller DTO (Response Template, Pagination)
* JWT 관리 (jjwt 활용)

### etc.
기타 개발 요소
* 프로젝트 분리 테스트
* docker-compose를 사용한 docker image 빌드

### future-work
향후 보완이 필요한 부분
* 테스트 코드 작성 및 코드 리팩토링
* 주석 추가 및 Spring component 별 매카니즘 확인
* 유저 입력 항목에 대한 validation 강화
* Redis 연동 및 JWT refresh 구현
* Exception 처리 추가
* Front-end 페이지 추가

## 동작 예시
### Account 생성
```http request
POST http://localhost:8082/api/account/
Content-Type: application/json

{
  "email": "testEmail@gmail.com",
  "password": "testPassword"
}
```
```json
{
  "resultCode": "000",
  "message": "success",
  "payload": {
    "id": 2,
    "email": "testEmail@gmail.com",
    "authority": "ROLE_USER"
  }
}
```
### JWT 발급
```http request
POST http://localhost:8082/auth/token
Content-Type: application/json

{
  "email": "testEmail@gmail.com",
  "password": "testPassword"
}
```
```json
{
  "resultCode": "000",
  "message": "success",
  "payload": {
    "grantType": "bearer",
    "accessToken": "eyJhbGciOiJIUzI1NiJ9. ...",
    "refreshToken": "eyJhbGciOiJIUzI1NiJ9. ...",
    "expiresIn": 1649999047155
  }
}
```
### JWT 리프레쉬
```http request
PUT http://localhost:8082/auth/token
Content-Type: application/json

{
  "accessToken": "eyJhbGciOiJIUzI1NiJ9. ...",
  "refreshToken": "eyJhbGciOiJIUzI1NiJ9. ..."
}
```
```json
{
  "resultCode": "000",
  "message": "success",
  "payload": {
    "grantType": "bearer",
    "accessToken": "eyJhbGciOiJIUzI1NiJ9. ...",
    "refreshToken": "eyJhbGciOiJIUzI1NiJ9. ...",
    "expiresIn": 1649999047155
  }
}
```
### 비정상적 JWT 요청
```http request
PUT http://localhost:8082/auth/token
Content-Type: application/json

{
  "accessToken": "eyJhbGciOiJIUzI1NiJ9. abcdefg...",
  "refreshToken": "eyJhbGciOiJIUzI1NiJ9. abcdefg..."
}
```
```json
{
  "status": 400,
  "code": "400",
  "message": "Invalid JWT token / 비정상적인 JWT 토큰입니다."
}
```
### Todo 작성
```http request
POST http://localhost:8081/api/todo
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9. ...

{
    "title": "test",
    "description": "desc",
    "status": 1
}
```
```json
{
  "resultCode": "000",
  "message": "success",
  "payload": {
    "id": 1,
    "title": "test",
    "description": "desc",
    "status": 1,
    "createdAt": "2022-04-14T14:08:58.038472",
    "createdBy": 1,
    "updatedAt": "2022-04-14T14:08:58.038472",
    "updatedBy": 1
  }
}
```
### Todo 리스트
```http request
GET http://localhost:8081/api/todo
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9. ...
```
```json
{
  "resultCode": "000",
  "message": "success",
  "payload": [
    {
      "id": 1,
      "title": "test",
      "description": "dest",
      "status": 1,
      "createdAt": "2022-04-14T14:08:58.038472",
      "createdBy": 1,
      "updatedAt": "2022-04-14T14:08:58.038472",
      "updatedBy": 1
    }
  ],
  "pagination": {
    "page": 0,
    "totalPages": 1,
    "elements": 1,
    "totalElements": 1
  }
}
```
### Todo 수정
```http request
PUT http://localhost:8081/api/todo
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9. ...

{
  "title": "updated test",
  "description": "updated desc",
  "status": 1
}
```
```json
{
  "resultCode": "000",
  "message": "success",
  "payload": {
    "id": 1,
    "title": "updated test",
    "description": "updated desc",
    "status": 1,
    "createdAt": "2022-04-14T14:08:58.038472",
    "createdBy": 1,
    "updatedAt": "2022-04-14T14:11:00.51996",
    "updatedBy": 1
  }
}
```


