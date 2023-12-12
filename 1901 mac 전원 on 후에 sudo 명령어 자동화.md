<!--
author: Dailyscat
purpose: issue arrange
rules:
 (1) 헤더와 문단사이
    <br/>
    <br/>
 (2) 코드가 작성되는 부분은 >로 정리
 (3) 참조는 해당 내용 바로 아래
    <br/>
    <br/>
 (4) 명령어는 bold
 (5) 방안은 ## 안의 과정은 ###
-->

# Issue: mac 전원 on 후에 sudo 명령어 자동화

<br/>

## 개념:

<br/>
```
Bash 쉘을 사용하는 경우:
터미널(Terminal)을 엽니다.
~/.bash_profile 또는 ~/.bashrc 파일을 편집기로 엽니다. (예: nano ~/.bash_profile)
파일의 마지막에 실행하고 싶은 명령을 추가합니다. (예: echo "Welcome back!")
파일을 저장하고 종료합니다.
변경사항을 적용하기 위해 쉘을 재시작하거나 source ~/.bash_profile 또는 source ~/.bashrc를 실행합니다.
Zsh 쉘을 사용하는 경우 (macOS Catalina 이상의 기본 쉘):
터미널을 엽니다.
~/.zshrc 파일을 편집합니다. (예: nano ~/.zshrc)
파일에 로그인 시 실행할 명령을 추가합니다.
파일을 저장하고 종료합니다.
변경사항을 적용하려면 source ~/.zshrc를 실행하거나 새 터미널 세션을 시작합니다.
```

```
1. 무비밀번호 sudo 설정 (권장되지 않음)
특정 사용자에 대해 특정 명령을 무비밀번호로 sudo를 할 수 있도록 /etc/sudoers 파일에서 설정할 수 있습니다.

bash
코드 복사

# 터미널에서 visudo를 사용하여 /etc/sudoers 파일을 안전하게 편집합니다.
sudo visudo
파일에서 다음과 같은 구문을 추가합니다.

bash
코드 복사

username ALL=(ALL) NOPASSWD:ALL
여기서 username을 해당 사용자 이름으로 바꾸면 됩니다. 이렇게 설정하면 모든 명령을 비밀번호 입력 없이 sudo로 실행할 수 있게 됩니다. 그러나 이 방법은 시스템 보안에 매우 큰 구멍을 만들기 때문에 권장되지 않습니다.
```


```
2. 맥오에스 비밀번호 관리자 사용
macOS의 키체인을 사용하여 스크립트에서 안전하게 비밀번호를 불러올 수 있는 방법이 있습니다. 키체인에 비밀번호를 저장하고 아래와 같이 AppleScript와 security 명령어를 사용하여 비밀번호를 불러올 수 있습니다.

~/.zshrc 파일에 다음의 AppleScript 예제를 삽입합니다.

bash
코드 복사

# ~/.zshrc

# 비밀번호 찾아 변수에 저장
password=$(security find-generic-password -w -s "sudo_password" -a username)

# 비밀번호가 저장된 변수를 이용해 echo 로 sudo 비밀번호를 입력
echo $password | sudo -S [임의의 명령]
예를 들면, sudo가 필요한 명령을 실행하기 위해 키체인에서 비밀번호를 가져옵니다. -S 옵션은 sudo의 표준 입력을 통해 비밀번호를 받게 합니다.

이 방법 역시 시스템의 보안 위험이 될 수 있기 때문에, 가능한 다른 방법을 고려해야 합니다.

보안상의 이유로 이 방법들은 신중히 고려하고 사용해야 합니다. 가능한 한 sudo가 필요하지 않은 명령, 스크립트 또는 작업으로 대체하는 것이 바람직합니다.
```

```
echo 'your_password' | sudo -S abc
```

```
# Nginx가 실행 중인지 확인합니다.
if pgrep nginx >/dev/null 2>&1; then
  echo "Nginx is running."
  # Nginx가 실행 중일 때 실행할 명령어를 여기에 추가합니다.
  # 예: echo "Running a backup script"
else
  echo "Nginx is not running."
  # Nginx가 실행 중이지 않을 때 실행할 명령어를 여기에 추가합니다.
  # 예: sudo service nginx start
fi

pgrep 명령은 시스템에서 실행 중인 프로세스 중 nginx라는 이름을 찾고, 찾으면 0을 반환
```


<br/>
<br/>
<br/>

        참조:

<br/>
