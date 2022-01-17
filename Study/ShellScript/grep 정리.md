# grep 정리

egrep, fgrep, rgrep 등이 있지만 현재 grep의 옵션을 통해 다 대체되었다.

```
// i 옵션은 대소문자 구분하지 않고 패턴을 검색하라는 의미
// UUID, uuid를 /etc/fstab 파일에서 검색
grep -i 'uuid' /etc/fstab

// e 옵션은 검색하고자 하는 패턴이 하나 이상일 경우 사용
// nova.conf에서 대괄호[]로 시작되는 설정 섹션, virt_type으로 시작되는 환경 설정 항목 검색
// alnum은 알파벳이나 숫자로 이루어진 문자열
grep -i -e "^\[[[:alnum:]]*\]" /etc/nova/nova.conf
// 대괄호가 앞뒤에 있고 중간이 알파벳이나 숫자로 이루어진 문자열이나 virt_type으로 시작하는 문자열 검색
grep -i -e "^\[[[:alnum:]]\]" -e "^virt_type" /etc/nova/nova.conf

// f 옵션은 파일에 저장된 패턴을 이용하여 검색 가능
// -f 옵션은 -e 옵션과 같이 여러 개의 패턴을 검색할 경우 패턴이 저장된 파일을 이용하는 것과 같다.
echo "^\[[[:alnum:]]\]" > pattern1.txt
grep -i -f pattern1.txt /etc/nova/nova.conf
// 여러개의 패턴 파일의 적용도 가능
echo "^virt_type" > pattern2.txt
grep -i -f pattern.txt -f pattern2.txt /etc/nova/nova.conf

// 특정 파일 내용에서 grep으로 원하는 라인만 터미널에 보이도록
cat /etc/nova/nova.conf | grep -i '^\[Default'
```

## grep의 다양한 옵션

```
// 패턴 문법 관련 옵션

// 확장 정규 표현식에 해당하는 패턴을 검색할 경우 사용
-E --extended-regexp
ex. ?는 앞에 있는 단어 하나가 일치하거나 일치하지 않을 때도 검색이 되도록하는 확장 정규표현식
grep 'q[[:lower:]]*\??' expression.txt // 의도대로 되지 않음
grep -E 'q[[:lower:]]*\??' expression.txt // 의도대로 작동

// 여러 줄로 되어 잇는 문자열 검색할 경우 사용
-F --fixed-strings
grep -F '# DATE
# Author
# Descr' expression.txt

// 기본 정규 표현식에 해당하는 패턴 검색할 때 사용, 기본값으로 보통 생략
-G --basic-regexp

// Perl 방식의 정규 표현식에 해당하는 패턴을 검색할 때 사용되는 옵션.다른 옵션에 비해 잘 사용 X
-P --perl-regexp
```

```
// 매칭 제어 관련 옵션

// Error나 warning 이 포함된 로그를 찾을 때
-e, --regexp=
grep -e 'mail' --regexp 'mail' 'phone expression.txt

// 자주 사용하는 패턴을 파일에 저장하여 사용할 때
-f, --file
grep -f file1.xtx --file file2.txt expression.txt

// 대소문자 구분 없이 문자열 검색 필요할 때
-i, --ignore
grep -i 'expression' expression.txt

// 패턴에 해당하는 라인을 제외한 나머지 라인 찾기
// 주석을 제외하는 용도로 주로 사용
// 아래는 #으로 시작하는 라인, 공백으로 시작하는라인 빼고 보겠다와 같다.
cat expression.txt | grep -v "^#" | grep -v "^$"

// 검색하고자 하는 패턴과 완벽하게 일치하는 단어가 있는 라인만 출력
// -w 옵션없이 검색했을 때
grep 'expression' expression.txt
출력: expression
출력: expressions

// -w 옵션으로 검색했을 때 
grep -w 'expression' expression.txt
출력: expression

// 검색하고자 하는 패턴과 라인 전체가 일치할 경우에만 해당 라인을 출력
// 일부일치는 출력결과 없음
grep -x 'Help" expression.txt
// 전부일치하면 출력결과 있음
grep -x '# Help' expression.txt
# help

// -y 옵션과 -i 옵션은 같다.
```

```
// 출력 제어 관련 옵션

// 검색한 패턴과 일치하는 단어가 몇개나 있는지 확인할 때
grep -c 'expression' expression.txt

// 패턴에 해당하는 문자열을 지정한 색으로 표시할 때
// GREP_COLOR 이라는 환경변수를 함께 사용해야한다.
// "1;31" 굵게
// "4;31" 밑줄
// "5;31" 반짝거리게
// "7;31" 음영이 있는 문자 보여줌
// "1;31" 빨간색
// "1;32" 연두색
// "1;33" 노란색
// "1;35" 연보라
GREP_COLOR="1;32" grep --color 'expression' expression.txt

// 검색하고자 하는 패턴이 존재하지 않는 파일 목록 조회
// -L, --files-without-match
// 패턴이 포함되지 않은 파일 목록 검색
grep -L "express" ./*

// -l, --files-with-match
// 패턴이 포함된 파일 검색
grep -l "express" ./*

// -m, --max-count=라인 수
// 패턴 검색 결과를 특정 라인 수만큼 출력
grep -m 5 "^\[[[:lower:]]*\]" /etc/nova/nova.conf

// -o, --only-matching
// 패턴과 일치하느 ㄴ단어만 출력할 경우 사용하느 ㄴ옵션
grep -o "express[[:lower:]]*" expression.txt
expression
expressions

// -q, --quiet, --silent
// 패턴 검색 결과를 보여주지 않는다
// 왜쓰지?

// -s, --no-message
// grep에 존재하지 않는 파일 명이나 디렉터리를 입력했을 때 에러메세지 띄우지 않음
// 보통 쉘 스크립트에서 에러 결과를 변수로 저장해 처리할 경우 유용

grep "help" express.txt
grep: express.txt: No shuc file or directory
grep -s "help" express.txt

```

```
// 출력라인 제어 옵션

// -b, --byte-offset 
// 검색 패턴이 포함된 라인의 바이트 수를 라인 제일 앞부분에 함께 보여준다.
// 153:# description: regur~~
// 291: thisis ~~~

// -H, --with-filename
// 해당하는 패턴이 있는 파일명을 앞에 보여준다.
grep 'express' expression.txt
//# description
grep -H 'express' expression.txt
//expression.txt:#  description

// -h, --no-filename 옵션을 사용하는 경우
// 리눅스는 기본적으로 파일 명을 라인앞에 보여준다.
// 이때 파일명을 숨기고 싶을 대 사용하는 옵션

grep 'express' ./*
expression.txt:#~~

grep -h 'express' ./*
#~~
```

```
// --label=LABEL
// 검색한 파일 정보 앞에 라벨을 붙일 때 사용
ls -l | grep --label=file -H express 
```

```
// -n, --line-number
// 해당 파일에서 찾은 라인의 번호 앞에 보여준다.
grep -n "question" expression.txt
23:
24:

// -T, --initial-tab
// 탭을 이용해 라인번호와 문자열 사이 간격 조정, 가독성을 위함
grep -T -n 'question' expression.txt

// -u --unix-byte-offset
// 유닉스 스타일의 바이트 수를 라인 앞에 보여줌

// -Z, --null
// 파일 목록 검색할 때 여러줄로 검색된 파일 목록의 뉴라인을 제거해 한줄로 출력
// 검색된 파일명을 for문과 같은 제어문의 인자값으로 사용하기 좋다.
cp expression.txt. test.txt
grep -Z -l 'express' ./*
```

```
// -A 라인 수, --after-context=라인 수
// 패턴이 포함된 라인 후 선언한 라인 만큼 보여줌. 마지막으로 검색된 라인 아래 2줄
grep -A 2 'question' expresison.txt

// -B 라인 수, --before-context=라인 수
// 패턴이 포함 된 라인 전에 선언한 라인 수에 해당하는 라인만큼 앞에 라인 추가하여 보여줌
grep -B 2 'question' expression.txt

// -C 라인 수, --contxt=라인 수
// 패턴이 검색 된 라인 위와 아래를 설정한 라인 수만큼 더 보여준다.
// 처음으로 검색된 패턴, 마지막으로 검색된 패턴 위아래로.
grep -C 2 'expression' expression.txt

// --group-separator=그룹구분
// -A,-B,-C 와 함께 사용
// 검색한 문자열이 서로 다른 줄에서 검색되면 --group-separator과 함께 보여짐
grep -A 1 --group-separator="=======" '# [[:alpha:]]' expression.txt

// --no-group-separator
// 그룹구분 기호 사용하지 않음
// 기본적으로 --group-separator를 사용하지 않으면 --가 기본적인 구분자로 사용된다. 이를 위해 사용
```

파일 및 디렉터리 관련 옵션

```


```
