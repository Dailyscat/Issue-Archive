# find 정리

// /etc 에서 chrony.conf 파일 검색
find /etc -name chrony.conf 

// 대상 경로 앞에 두는 옵션
// 심볼릭 파일 검색방법, 디버그 관련, 레벨 관련 옵션
// -L  심볼릭 링크의 원파일의 속성검사
// -perm 파일 권한
find -L /etc -perm 644 -name 'rc.*'

## find의 다양한 표현식

파일을 찾기 위한 방법 = 표현식

찾고자 하는 파일의 속성 정의를 위한 tests
테스트와 테스트의 검색 우선순위를 정의할 수 있는 operators
검색한 파일을 인수로 하여 또 다른 명령어를 실행할 수 있는 actions 
테스트와 함께 쓰이며 테스트의 검색 조건을 변경할 수 잇는 positional options

### TESTS

// -amin n
// 파일의 접근 시간 확인하여 n분에 접근한 파일을 찾는다.
date // 현재시간 확인
find ./ -amin 1 // 1분 내에 접근한 파일 확인
stat amin.txt | grep Access