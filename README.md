# actuator and monitoring tool
### 학습 목적
서비스를 운영하는 개발자에게는 언제든지 장애가 발생할 수 있습니다. 하지만 이를 잘 대응하는 것은 중요하다 생각했습니다. <br>
개발자가 애플리케이션을 개발할 때 기능 요구사항만 개발하는 것이 아닌, <br>
실제 운영 단계에 올리게 되면 개발자들은 서비스에 문제가 없는지 모니터링하고 지표들을 심어서 감시하는 활동도 업무의 일부입니다. <br>
비록 비 기능적 요소이지만 실무에서 문제없이 장애에 대응할 수 있는 능력을 키우고자 강의를 듣습니다.

### 준비
**엑츄에이터 라이브러리 의존 추가**
- `implementation 'org.springframework.boot:spring-boot-starter-actuator'` <br>

**application.yml에 엑츄에이터 설정 추가** <br>
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

다운로드 및 prometheus.exe 실행 후 localhost:9090 접속 확인

![화면 캡처 2023-04-24 135702](https://user-images.githubusercontent.com/110963294/233903473-f65fec28-b764-4c68-bc90-14b3cdb3a883.png)

**마이크로미터 라이브러리 의존 추가**
- `implementation 'io.micrometer:micrometer-registry-prometheus'`
- 스프링부트와 엑츄에이터가 자동으로 마이크로미터 프로메테우스 구현체를 등록해서 동작하도록 설정해줌
- 스프링 실행 후 localhost:8080/actuator/prometheus 접속 

**prometheus 폴더 안에 있는 prometheus.yml에 코드 추가**
```
global:
  scrape_interval: 15s
  evaluation_interval: 15s 

alerting:
  alertmanagers:
    - static_configs:
        - targets:

rule_files:
scrape_configs:
  - job_name: "prometheus"
    static_configs:
      - targets: ["localhost:9090"]
      
# 추가
  - job_name: "spring-actuator" #수집 이름 -> 임의의 이름 사용
    metrics_path: '/actuator/prometheus' # 수집 경로
    scrape_interval: 1s # 수집 주기 -> 운영에서는 10 ~ 60초로 설정 권장 (요청으로 인한 성능저하 발생)
    static_configs: 
      - targets: ['localhost:8080'] #ip, port 지정
```
프로메테우스 재실행 후 localhost:9090/targets 접속 확인

![화면 캡처 2023-04-24 142200](https://user-images.githubusercontent.com/110963294/233906629-1061aa92-2ca1-477d-ab64-8ad25d47a288.png)

**그라파나 설치**
- https://grafana.com/grafana/download?platform=windows
- 설치 후 bin폴더의 grafana-server.exe 실행
- 실행 후 localhost:3000 접속 확인
- admin / admin으로 로그인

![화면 캡처 2023-04-24 152100](https://user-images.githubusercontent.com/110963294/233915103-b4623704-dffd-47d4-b5e1-8e9104eb61ed.png)

**그라파나에 프로메테우스 연결**
- 설정 - Configuration - add data source - Prometheus
- uri에 http://localhost:9090 입력

![화면 캡처 2023-04-24 152708](https://user-images.githubusercontent.com/110963294/233916085-1aa1042f-0113-4c9f-9519-3906c7f9f7ff.png)

