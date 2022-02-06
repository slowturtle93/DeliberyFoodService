# DeliberyFoodService

"음식점 정보부터 음식 배달까지"<br>
배달의 민족을 모티브로 만든 음식배달 플랫폼 API 서버 토이 프로젝트입니다.

  
# 월 2,000만명 이상이 사용하는 배달의 민족은 어떻게 만들어진 것일까?

대규모 트래픽을 어떻게 처리하고 있을까?<br>
대용량 데이터를 어떻게 다루고 있을까?<br>
음식점과 사용자의 배송지의 정보에 대한 거리는 어떻게 처리하고 있을까?<br><br>
이러한 궁금증들을 통해서 직접 배달의민족 서버를 구현해보는 프로젝트를 진행하게 되었습니다.

# 어떠한 기능들을 구현 해야하는 하는지 고민하였습니다.

* AOP를 통해 로그인 관리 기능
* email 및 naver Sens 를 통해 비밀번호 찾기 본인인증 기능
* FCM PUSH 발송을 위해 device Token 정보를 Redis로 관리하여 필요에 의해 FCM PUSH 발송 기능
* QueryDsl 을 활용하여 사용자의 위치로 부터 특정 반경 내 가게 조회 기능
* Retrofit 을 활용하여 Kakao, Toss Pay API 결제 프로세스 기능 
* 결제 완료 등 다양한 상황에서 Apach Kafka 파이프 라인 생성 후 producer, consumer를 통해 message 전달
* 라이더의 실시간 위치 제공 기능

다양한 기술 스택을 통해 많은 요구사항을 구현하기 위해 노력하였습니다.
  
# 단순 기능만 구현하지 않고 유지보수에 <br> 이점을 가질수 있도록 고민하였습니다.
  
* 실제 배달의 민족이 대규모 트래픽을 장애없이 어떻게 처리하고 있는지
* 사용자의 배송지 기준으로 음식점을 노출하는 서비스는 어떻게 구현하였는지
* 실시간 배송 정보 위치 서비스는 어떻게 구현하였는지
* 유지보수성을 위한 객체지향적 설계는 어떻게 이루어져야 하는지
* 냄새나는 코드를 제거해서 읽기 좋은 코드를 만들기 위해서는 어떻게해야 하는지
 
  대용량 트래픽에도 장애없이 동작할 수 있도록 성능과 유지보수성을 고려한 서비스를 만들기 위해서, 읽기 좋은 코드 객체지향적 설계를 위해 노력하였습니다.

# 프로젝트 구조

![프로젝트구조](https://user-images.githubusercontent.com/80434153/138026837-50824929-d785-4eca-969f-1ff1e2c54761.png)

# 프로젝트 사용기술

![사용기술](https://user-images.githubusercontent.com/80434153/152683886-96b83778-3562-4e8e-9fd7-d180a0975ba1.png)


# 프로젝트 ERD

![foodDelivery_ERD](https://user-images.githubusercontent.com/80434153/152683024-5104b4d4-4de4-4309-914c-3adecd96abbf.png)


