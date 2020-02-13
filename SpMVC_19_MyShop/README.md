#Spring MVC 기반 쇼핑몰 솔루션
### 2020-02-10

## spring security
* security login 구현
(spring security core/web/config/libs dependency는 version은 호환성 문제로 항상 spring frame work version보다 낮은 것을 선택해서 사용해야함)
* spring security에 의해서 기본적으로 login POST, logout POST 기능을 사용할 수 있고,
* security 자체의 암호화 알고리즘으로 web과 서버 사이에서 데이터를 보호해주는 기능을 수행.
* spring security를 사용하여 login, logout 처리를 수행하면 자체적으로
httpSession과 cookie등을 사용해서 안전한 연결처리를 대신 수행한다.



## 관리자 페이지
* 상품정보등록
* 품목정보등록
* 거래처정보등록
* 은행 및 결제 정보등록
* 주문관리

## 사용자 페이지
* 상품검색
* 상품상세보기
* 주문
* 결제
* 배송정보 확인

## Spring security와 Hibernate, Validation