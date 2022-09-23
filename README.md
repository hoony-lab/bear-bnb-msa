# 숙소예약 서비스 (BearBnB)

---
## 👫 Team
| 팀  |   성명   | 직급 | 소속             | 담당 |
| :-: | :------: | :--: | :---------------: | :----- |
|  1  |  안소영 | 과장 | 방산운영1팀      | 개발 |
|     | 🎖김지훈  | 사원 | SharedService1팀  | 개발, 운영 |
|     |  박진곤  | 대리 | 디지털에셋그룹   | 운영 |

---

## 1. 분석설계
  - 호스트가 객실(Room)을 등록/수정/삭제한다.
  - 고객이 객실(Room)을 예약한다.
  - 예약과 동시에 결제가 진행된다.
  - 고객이 예약을 취소할 수 있다.
  - 객실에 후기(review)를 남길 수 있다.
  - 객실에 대한 정보를 한 화면(dashboard)에서 확인 할 수 있다.  
  ![image](https://user-images.githubusercontent.com/61038710/191873690-ac5e4a08-5933-428e-91e8-2bad1a79306a.png)

---

## 2. SAGA Pattern (Pub / Sub)
  - kafka를 이용하여 4개의 마이크로서비스로 연동한다.
  + 객실(Room) 
    + ![image](https://user-images.githubusercontent.com/61038710/191680079-6090ee97-3594-49a1-86d8-f28c59d3e148.png)
  + 예약(Reservation)
    + ![image](https://user-images.githubusercontent.com/61038710/191679496-7403a11f-ad6a-4fd3-b07b-11b49675fdf7.png)
  + 결제(Payment)
    + ![image](https://user-images.githubusercontent.com/61038710/191679905-5eb4f20d-b986-4344-a158-d50adfb15c06.png)
  + 대시보드(DashBoard)
    + ![image](https://user-images.githubusercontent.com/61038710/191679985-85294156-b080-41bb-a8bf-ab325b2f5c9a.png)

    

- Kafka 구현
- https://sarc.io/index.php/development/2128-saga-pattern  
Saga Pattern은 마이크로 서비스에서 데이터 일관성을 관리하는 방법입니다.
각 서비스는 로컬 트랜잭션을 가지고 있으며, 해당 서비스 데이터를 업데이트하며 메시지 또는 이벤트를 발행해서, 다음 단계 트랜잭션을 호출하게 됩니다.
만약, 해당 프로세스가 실패하게 되면 데이터 정합성을 맞추기 위해 이전 트랜잭션에 대해 보상 트랜잭션을 실행합니다.
NoSQL 같이 분산 트랜잭션 처리를 지원하지 않거나, 각기 다른 서비스에서 다른 DB 밴더사를 이용할 경우에도 Saga Pattenrn을 이용해서 데이터 일관성을 보장 받을 수 있습니다.

---

## 3. CQRS Pattern (Command Query Responsibility Segregation)
- Room 등록 및 수정된 데이터가 조회되는 모습이다.
- ![image](https://user-images.githubusercontent.com/44036052/191693434-c8098af4-2df0-49af-82ae-54698b1be5ff.png)
- ![image](https://user-images.githubusercontent.com/44036052/191693680-c86f7edb-40bc-4e40-890a-fd5353054692.png)
- ![image](https://user-images.githubusercontent.com/44036052/191693812-56832582-4ff9-49b3-bd31-e08733b9cf0a.png)
- ![image](https://user-images.githubusercontent.com/44036052/191693918-64fdc629-5b5d-4af7-b23a-30a5b71bb0a0.png)

- Dashboard 구현
- 명령 / 조회 책임 분리
- Define a view database, which is a read-only by subscribing

---

## 4. Correlation / Compensation(Unique Key)
  - 객실에 대한 리뷰 등록 및 삭제 시 방(Room)의 리뷰 수(ReviewCnt)가 갱신된다.
  - 예약 취소 시 결제 취소도 자동으로 수행된다
  - 결제 후 예약 확정 시 객실에 대한 상태값도 갱신된다.
![image](https://user-images.githubusercontent.com/61038710/191683750-bc1633f9-fccd-48aa-bcad-c78dee528e11.png)
![image](https://user-images.githubusercontent.com/61038710/191683495-f1fc6417-91bb-4097-95bb-6f6ccbb6eb76.png)

- 유니크키, FooCancelled 구현
- 데이터 일관성 처리를 위해 전달하는 key = Correlation-key
- 트랜젝션 실패 시 데이터의 일관성 유지를 위해 Rollback 처리 가능 = Compensation
- 데이터 일관성과 무결성 유지 !
- 상관관계 / 보상

---

## 5. Request / Response (Feign Client / Sync.Async)
- `Payment` 서비스가 정상적으로 작동하지 않아도 `PaymentServiceFallback`이 예상 실패 로직을 수행한다.  
- ![image](https://user-images.githubusercontent.com/44036052/191689742-f3fa55fe-7b74-4680-9f59-6b5a25b855d5.png)
- ![image](https://user-images.githubusercontent.com/44036052/191689882-d3618edc-ea2e-45f0-99a1-28e047b0199f.png)

- @FeignClient(name ="Foofeign", url="api.github.com/foo", configuration = "Config.class")
- REST 호출을 도와주는 Http Client Binder가 Feign Client 
- JPA는 인터페이스만으로 그 과정을 모두 축소
- Feign을 적용하면 번거로운 RestTemplate과 같은 호출 방식을 인터페이스 하나만으로 축소

---

## 6. Gateway
- gateway를 8088포트로 실행하여 gateway를 통해 각 micro service를 호출한다.
- ![image](https://user-images.githubusercontent.com/61038710/191685577-86b7b303-6bfb-4de6-be9d-cd1a847a0d75.png)
![image](https://user-images.githubusercontent.com/61038710/191685804-3be93128-64d2-4957-b474-f075db7c9123.png)
![image](https://user-images.githubusercontent.com/61038710/191686379-4bce9b5d-104e-485d-9a61-7da4f676a032.png)

- KeyCloak, Spring Gateway 구현
- 라우팅

---

## 7. Deploy / Pipeline 
- AWS CodeDeploy, Jenkins 구현
- CI/CD

---

## 8. Circuit Breaker
- Fallback 로직이 없어서 500에러가 나오는 모습이다.
- ![image](https://user-images.githubusercontent.com/44036052/191691507-50ed7ea8-368e-47c0-87a7-16a32355b2b8.png)
- Fallback 로직을 적용 후 200만 나오는 모습이다.
- ![image](https://user-images.githubusercontent.com/44036052/191692593-966f90cd-5a1f-456e-ba46-8783cd07eed2.png)

- Spring Cloud Feign, Netflix Hystrix
- istio 구현
- 요청이 과도할 경우 CB 를 통하여 장애격리, 장애전파차단

---

## 9. Autoscale(HPA)
Auto Scale-Out 증명
![image](https://user-images.githubusercontent.com/61038710/191877057-c50eb488-ad29-4d81-95b9-39d5f2af9f7b.png)
![image](https://user-images.githubusercontent.com/61038710/191877224-25215551-53ff-4ce2-a1f3-0bce6851cf79.png)
![image](https://user-images.githubusercontent.com/61038710/191877369-79feb764-9566-4e0f-ac91-5d19530359d2.png)
![image](https://user-images.githubusercontent.com/61038710/191878847-6c9207b1-5f1f-4eea-8040-66e39c520f46.png)
![image](https://user-images.githubusercontent.com/61038710/191896508-cbcc8e85-c7eb-4d71-bbb5-25d52c61a006.png)


- K8S, HorizontalPodAutoscaler kind

---

## 10. Self-Healing(Liveness Probe)
- Pod는 정상적으로 작동하지만 내부의 어플리케이션이 반응이 없다면, 컨테이너는 의미가 없다.
- ![image](https://user-images.githubusercontent.com/44036052/191902080-38cc26cc-3660-405e-859a-acff95bdc990.png)
- ![image](https://user-images.githubusercontent.com/44036052/191902266-53874dfc-1c1f-44e4-a1c9-3c9ccbae245a.png)
- ![image](https://user-images.githubusercontent.com/44036052/191902774-0e173ecb-0145-4c7e-829d-d335162049ee.png)

- K8S, livenessProbe (deployment.yml)

---

## 11. Zero-Downtime Deploy(Readiness Probe)
- 클러스터에 배포를 할때 readinessProbe 설정이 없으면 다운타임이 존재 하게 된다. 이는 쿠버네티스에서 Ramped 배포 방식으로 무정지 배포를 시도 하지만, 서비스가 기동하는 시간이 있기 때문에, 기동 시간동안 장애가 발생 할 수 있다.

- 배포시 다운타임의 존재 여부를 확인하기 위하여, siege 라는 부하 테스트 툴을 사용한다.

- ![image](https://user-images.githubusercontent.com/44036052/191896717-28b4e863-ca37-40aa-8b06-50cd62b2a920.png)
- ![image](https://user-images.githubusercontent.com/44036052/191896788-b6422c35-9f55-41ba-89ab-76147d5d6472.png)
- ![image](https://user-images.githubusercontent.com/44036052/191896669-1771dffc-0748-4708-a9ac-6b0fef957ad9.png)

- ![image](https://user-images.githubusercontent.com/44036052/191896746-e8761e02-e0fb-4f85-b1b3-6fca79006679.png)
- ![image](https://user-images.githubusercontent.com/44036052/191896792-754a0e29-7997-4128-bc4b-94bee2d1dd3b.png)
- ![image](https://user-images.githubusercontent.com/44036052/191896767-3394fc2e-8aac-4eb5-8064-abfa7ef74886.png)

- K8S, ReadinessProbe (deployment.yml)
---

12. Config Map / Persistence Volume (X)
- K8S, ConfigMap kind (configmap.yml)

---

13. Polyglot (X)
- MongoDB/MariaDB/MYSQL, java(spring)/python(flask,fastAPI)
- Polyglot Persistence/Programming
- 각 마이크로 서비스들의 구현 목표와 기능 특성에 따른 각자의 기술 Stack 과 저장소 구조를 다양하게 채택하여 설계


---


<img src= "https://t1.daumcdn.net/cfile/tistory/997A00365C79475E04?download">
