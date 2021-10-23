# 군인 커뮤니티 앱(KorArmy)

## 소개
> 군인들을 위한 익명 커뮤니티 어플입니다.
> 간단히 달력에 일정 저장도 가능하고, 현재 군인 계급, 전역 날짜, 월급 등을 표시하여 나타내줍니다.
> Firebase 통한 실시간DB 사용으로 게시판과 Authentication을 이용한 회원가입 및 로그인 제공(+ 이름 변경, 비밀번호 변경)  
> SNS처럼 글을 작성한 시간과 현재시간을 통해 ~~ 전으로 타임스탬프를 표시


### 이미지

<img src="https://user-images.githubusercontent.com/41982054/138401285-f32fccc3-4dfb-433d-8a15-c541e6c25fa2.png" width="200" height="400"> <img src="https://user-images.githubusercontent.com/41982054/138403024-3d9f03a0-25b3-4dd8-9eef-d214c24590c5.png" width="200" height="400"> <img src="https://user-images.githubusercontent.com/41982054/138231194-51790491-2ab6-41b8-83e8-e660e0e3fcdf.png" width="200" height="400"> <img src="https://user-images.githubusercontent.com/41982054/138231211-b5f5b904-d1bf-4a13-a3e1-d1683927225c.png" width="200" height="400"> <img src="https://user-images.githubusercontent.com/41982054/138401073-e20d3cf0-fc8f-4ae6-92e2-53209d704169.png" width="200" height="400"> <img src="https://user-images.githubusercontent.com/41982054/138401080-e487fa38-2e11-4dff-a032-09cba125aab6.png" width="200" height="400">
<img src="https://user-images.githubusercontent.com/41982054/138406498-9c34af04-3761-4c2f-b62d-5692ee701c18.png" width="200" height="400"> <img src="https://user-images.githubusercontent.com/41982054/138231194-51790491-2ab6-41b8-83e8-e660e0e3fcdf.png" width="200" height="400"> <img src="https://user-images.githubusercontent.com/41982054/138401732-d6be0ae0-296f-43e8-b3e4-73252d9d3931.png" width="200" height="400"> 
<img src="https://user-images.githubusercontent.com/41982054/138404860-0c036100-d445-4df0-a0ed-edea12861a12.png" width="200" height="400"> <img src="https://user-images.githubusercontent.com/41982054/138406613-1b556c93-ed3f-49bc-a04c-7a93291d99ff.png" width="200" height="400"> <img src="https://user-images.githubusercontent.com/41982054/138406619-eab1afa9-158f-4f30-8fe5-091268beded4.png" width="200" height="400">
<img src="https://user-images.githubusercontent.com/41982054/138417317-68abf9ca-6853-417a-a6f4-e670d3575f4e.png" width="200" height="400"> <img src="https://user-images.githubusercontent.com/41982054/138417319-ef44de9e-68d7-4f36-9ee7-7e53fe8239d6.png" width="200" height="400">  < 군인 정보를 등록하면 추가 군인 정보를 자동으로 표시

------------

### 정리
이 앱을 만들면서 사용하거나 공부한 내용들을 정리하는 공간

<br>

앱 구성

<img src="https://user-images.githubusercontent.com/41982054/138415330-0e5177b2-fb10-407b-bf2b-51c5b4230f0e.png" width="200" height="400">

* Board : 자유게시판,질문게시판,비밀게시판을 보여주는 Activity, 글을 작성하는 Activity로 구성
* Frag : 메인화면을 네비게이션바를 이용해 화면을 전환할 수 있는 3가지 fragment 구성
* Login : 로그인 Activity와 회원가입 시 군인 정보를 저장하는 Activity, 자동 로그인을 구현할 SharedPreference Activity 등 구성
* Recyclerview로 게시판 정보를 표시 


Shared Preference

- 안드로이드 내부에 해당 데이터를 키-값 쌍으로 저장하는 방식
- 간단한 데이터를 저장할 떄 사용하며 자동 로그인을 구현하기 위해 아이디 저장할 때 사용

Splash Activity

- 어플 실행 시 처음 나오는 인트로 화면으로 어플리케이션의 대표 이미지를 담당하는 부분

Firebase

- 이메일을 활용한 회원가입과 구글API를 이용해 구글로그인을 구현


- 실시간 데이터베이스 사용으로 게시판들의 제목과 내용, 작성자, 작성일을 저장 / 글의 작성자일 경우 글을 수정 및 삭제 버튼이 표시


OpenFileI/O

- 안드로이드 내부 저장장치에 파일을 생성하여 쓰거나 읽는 방식 / CalenderView로 달력을 보여주고 해당 날짜를 클릭 후 텍스트 입력창에 내용을 적고 저장 버튼을 누르면 일정을 저장하거나 수정, 삭제 가능

Visivility

- 안드로이드에서 뷰를 정의할 때 뷰를 나타내거나 숨길 수 있는데, 이때 gone과 invisible을 사용
+ gone
  + View를 보이지 않게 하고 공간도 차지하지 않음
+ invisible
  + View를 보이지 않지만 공간은 차지


[블로그로 가기](https://vnfmadl234.tistory.com/22)
