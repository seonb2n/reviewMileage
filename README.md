#사용자 마일리지 서버
--
#### 구동 서버
<p>구동 되는 서버는 mySQL Database 와 연동되어야 한다. <br>
mySql db 를 localhost 의 3306 포트에 구동시키고, 사용자 아이디를 root 비밀번호를 tiger 로 설정해야 yml 파일과 호환이 된다.</p>

#### 테스트
<p>기능의 테스트는 test directory 에 있는 facadeTest 를 통해 완료했다.
<br> api 테스트는 http-test directory 에 있는 mileage-api.http 에 있는 테스트를 통해 완료했다.</p>

#### 구현
+ DDD 기반으로 Layer를 나눠서 구현했다.
+ JPA hibernate 를 사용해서 DB 와 통신한다.
+ 외부에 Entity 가 그대로 노출되지 않도록 Info 타입의 객체로 변환하여 내보낸다.
+ 마찮가지로, 사용자의 요청에 대해서는 Command 객체로 변환하여 사용한다.
+ 모든 응답 결과는 CommonResponse 로 wrapping 되고 내부 로직 오류나, 에러 발생 시에는 결과를 알려준다.
+ api 와 통신 간에는 dto 에 정의되어 있는 형태로 request 와 response 가 가능하다.