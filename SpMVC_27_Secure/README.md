## Security와 관련 용어

#### 접근주체(Principal)
* 보호된 대상에 접근하는 유저(User)

#### 인증(Authenticate)
* 접속하는 유저가 누구인가 확인(로그인 절차)

#### 인가(Autherize)
* 접근한 유저가 어떤 서비스, 어떤 페이지에 접근할 수 잇는 권한이 있는가

#### 권한(Role)
* 인증(Authenticate)된 주체(User)가 어떤 페이지, 기능, 서비스에 접근할 수 있는 권한이 있다 는 것을 보증, 증명

#### 무결성, 보안
* 무결점 : 인가된 사용자에 의해 손상될 수 있는 것들
* 보안 : 인가되지 않은 사용자에 의해 손상될 수 있는 것들

## Spring Security
* Filter를 사용하여 접근하는 사용자의 인증절차와 인가를통해 권한이
있는가를 파악하고, 적절한 조치(되돌릭, Redirect, 사용가능)를 비교적 적은
코드 양으로 처리할 수 있도록 만든 framework

* Spring security는 Session과 쿠키방식을 병행하여 사용한다.

### 유저가 로그인을 시도하면
1. Authentication Filter에서부터 users table까지 접근하여 사용자 정보를 인증하는 절차를 거친다.

2. 인증이 되면 user table, user detail table에서 사용자 정보를 fetch(=select)하여 session에 저장

3. 일반적인 HttpSession은 서버의 활동 영역 메모리에 session을 저장하는데비해
Spring Security는 "SecurityContextHolder"라는 메모리에 저장

4. View로 유저의 정보가 담긴 session을 session Id와 함게 응답으로 전달
>* JSESSIONID라는 쿠키에 SESSION ID를 담아서 보내고

5. 이후 유저가 접근을 하면 JSESSIONID에서 쿠키를 추출하여 사용자 인증을 시도한다.
*  ?JSESSIONID=asjidfjwk 이러한 값이 URL뒤에 따라 붙기도 한다.

6.