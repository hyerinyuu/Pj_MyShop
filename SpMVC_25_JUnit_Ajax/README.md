## Run as Server를 실행하여 프로젝트를 Test
* 모든 로직이 갖추어진 상태에서 사용자가 전송한 데이터를 Controller로 받는 것부터 시작하여 Test가 이루어진다.
* 이러한 Test는 아주 
* 프로젝트가 커지면 커질수록 생산성 면에서 매우 불리하게 작용한다.

## JUnit spring-test를 사용한 단위 Test
* servlet-context.xml을 기준이로 서로 의존성 주입이 완료된 MVC 프로젝트를
실제로 작동하는 것처럼 데이터를 주입하고, 리턴된 데이터를 되돌려 받아 Test수행
* 이 방법은 서버를 직접 실행해서 Test하는 것보다는 매우 효율적이다.
* 또한 통합 테스트를 수행할때는 이런한 방법으로 Test를 진행한다.

* 하지만 개발과정에서 클래스단위, 메서드 단위의 테스트에는 다소 부정적인 방법이다.

* Test하는 과정에서 실제 DB의 데이터가 추가, 수정 삭제되어 변형될 수 있고,
* 변형된 데이터를 SELECT하여 결과를 비교하는 것은 Test 실패로 이어져 불필요한 코드가 추가되거나 할 수 있다.

## Mock를 사용한 단위테스트
* Mockito를 사용하는 단위테스트는 한가지의 동작에 중점을 두고 Test하는 것으로
* Controller가 사용자의 Request를 받았을 때 Service에게 잘 요청하고, 그 결과를 잘 받는지 테스트 하는 것.
* Service가 Dao(Repository)에게 CRUD를 요청했을 때 적절한 결과가 나오는지 테스트 하는 것
* 만약 Controller의 method를 Test한다면 service의 요청 method를 Mock(모형)으로 설정하고,
Service는 항상 정상적인 데이터를 return한다는 가정 하에서 Controller의 Test를 수행한다.

* Service의 methgod를 Test한다면, Dao의 method를 Mock으로 설정하고,
Dao는 항상 정상적인 데이터를 return한다는 가정 하에서 Service의 Test를 수행한다.

* 또한 한 Service method에서 다른 비즈니스 로직의 method를 호출하여 동작이 이루어진다면
이들이 실제로 동작이 잘 이루어지고, 데이터들이 잘 이동되는지 Test를 수행한다.