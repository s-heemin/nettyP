해당 프로젝트는 혼자 작업한 netty 서버 코드입니다.

자바 17 버전을 사용했으며 proto3을 사용했습니다.

프로퍼티 세팅에 리소스 데이터들이 존재해야만 서버가 켜집니다.

커뮤니티 관련 컨텐츠는 멀티 스레드의 동시성 이슈를 해결 하기 위하여 이벤트 큐를 구현하여 해결하였습니다

세션,랭킹은 레디스를 이용하여 구현하였습니다
