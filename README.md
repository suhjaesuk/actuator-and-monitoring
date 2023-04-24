# actuator and monitoring tool
### 학습 목적
서비스를 운영하는 개발자에게는 언제든지 장애가 발생할 수 있습니다. 하지만 이를 잘 대응하는 것은 중요하다 생각했습니다. <br>
개발자가 애플리케이션을 개발할 때 기능 요구사항만 개발하는 것이 아닌, <br>
실제 운영 단계에 올리게 되면 개발자들은 서비스에 문제가 없는지 모니터링하고 지표들을 심어서 감시하는 활동도 업무의 일부입니다. <br>
비록 비 기능적 요소이지만 실무에서 문제없이 장애에 대응할 수 있는 능력을 키우고자 강의를 듣습니다.

### 준비
**build.gradle에 추가**
- `implementation 'org.springframework.boot:spring-boot-starter-actuator'` <br>

**application.yml에 추가** <br>
예) 모든 엑츄에이터 엔드포인트를 Http에 노출 단, env, beans는 제외
```
management:
  endpoints:
    web:
      exposure:
        include: "*"
        exclude: "env,beans"
```        

예) 셧다운 기능 엔드포인트를 Http에 노출
```
management:
  endpoint:
    shutdown:
      enabled:
        true
```

**프로메테우스 설치**
- https://prometheus.io/download/ <br>
실행 후 localhost:9090 접속 확인
