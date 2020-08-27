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

# Issue: wsl로 ubuntu 개발 환경 만들기

## 상황: 기존의 듀얼 부팅을 wsl로 변경

<br/>

## 알게된 부분 정리:

- wsl 쓰는 이유
- wsl을 위한 업데이트, ubuntu 설치
- wsl과 docker를 위한 기본 설치
- ubuntu 기본 설정
- windows terminal 설정

<br/>

## wsl 쓰는 이유

  - 개발을 진행하다 보면 때때로 Windows 환경을 지원하지 않는 경우가 종종 있고 이를 위해 ubuntu 더블 부팅으로 사용했었는데 메모리만 차지하기 때문에 굳이 그럴 필요없이 wsl을 사용한다.

## 개념: wsl2을 위한 업데이트, ubuntu 설치

<br/>
  https://docs.microsoft.com/ko-kr/windows/wsl/install-win10
  https://www.44bits.io/ko/post/wsl2-install-and-basic-usage
  https://wnsgml972.github.io/setting/2019/05/07/wsl/
  https://jootc.com/p/201901132508
  https://webdir.tistory.com/542

  1. Windows 10 실행, 버전 2004로 업데이트, 빌드 19041 이상. => 상당히 오래 걸린다. 미리미리 업데이트 하는게..
  2. dism.exe /online /enable-feature /featurename:Microsoft-Windows-Subsystem-Linux /all /norestart
    - "Linux용 Windows 하위 시스템" 옵션 기능을 사용
    - gui에서도 가능하지만 커맨드라인에서 하는게 편한다.
  3. wsl --set-default-version 2 
    - wsl의 버전을 2로 기본 설정
  4. Linux 배포 설치
    - 나는 18.04를 기본으로 했다. 20.04는 gui로 사용했을 때 에러가 좀 발생했어서 그나마 최근이고 그나마 안정적인 18.04로..
    - 리셋을 위해서는 제어판 -> 앱 및 기능 -> 고급옵션 -> 초기화, 제거 둘 중 사용
    - 설치가 완료되면 실행을 누르고 설치가 다 되면 이름, 비밀번호를 입력하면 완료! 굉장히 쉽다.
  5. wsl -l -v 명령어를 통해 wsl2 버전이 적용되었는지 확인
  6. wsl --help 명령어로 다른 옵션도 살펴볼 수 있다.

에러
        가상 디스크 시스템 제한으로 인해 요청한 작업을 완료할 수 없습니다. 가상 하드 디스크 파일은 압축이 풀려 있는 상태이고 암호화되지 않아야 하며 스파스가 아니어야 합니다.

https://docs.microsoft.com/ko-kr/windows/wsl/install-win10#troubleshooting-installation 해당 단계를 따라서 
해결


<br/>
<br/>
<br/>

        참조:

<br/>

## 개념:  wsl과 docker를 위한 기본 설치

<br/>

  1. [Docker Desktop for Windows](https://hub.docker.com/editions/community/docker-ce-desktop-windows) - Docker Hub을 설치
  2. 설정으로 들어가서 General 탭에서 Use the WSL2 based engine 옵션 체크
  3. Resource -> WSL Integration 페이지로 이동해서 설정을 확인 -> 이 단계를 안하면 에러난다.

에러

        Got permission denied while trying to connect to the Docker daemon socket...

https://arclab.tistory.com/247

        sudo usermod -a -G docker $USER 
        sudo reboot


<br/>
<br/>
<br/>

        참조:

<br/>

<br/>

## 개념: ubuntu 기본 설정

<br/>
 
https://luckyyowu.tistory.com/409
https://medium.com/harrythegreat/oh-my-zsh-iterm2%EB%A1%9C-%ED%84%B0%EB%AF%B8%EB%84%90%EC%9D%84-%EB%8D%94-%EA%B0%95%EB%A0%A5%ED%95%98%EA%B2%8C-a105f2c01bec

  - sudo apt-get update, sudo apt full-upgrade로 저장소 업데이트
  - apt와 apt-get 차이는 apt는 프로그레스바를 보여주며 apt-get, apt-cache에서 필요한 기능들을 하나의 명령어로 사용할 수 있다.

  - 한글표시와 한국어 Locale 설정
    - sudo apt -y install language-pack-ko
    - sudo locale-gen ko_KR.UTF-8
    - sudo apt -y install fonts-unfonts-core fonts-unfonts-extra fonts-baekmuk fonts-nanum fonts-nanum-coding fonts-nanum-extra

  - wsl 파일이 위치한 경로
    - 18.04 C:\Users\%USERNAME%\AppData\Local\Packages\CanonicalGroupLimited.UbuntuonWindows_79rhkp1fndgsc\LocalState\rootfs\
    - 해당 경로를 열거나 편집, 사용해서는 안된다.
  
  - apt 소스 저장소를 카카오 미러 서버로 변경
    - sudo sed -i 's/kr.archive.ubuntu.com/mirror.kakao.com/g' /etc/apt/sources.list
  - git, vim, curl 설치
    - sudo apt install vim git curl 
  
  - zsh과 oh-my-zsh 설치, 그리고 플러그인 적용
    - sudo apt install zsh -y && chsh -s `which zsh`
    - chsh 명령어 실행시 password 를 확인합니다.
    - curl -L https://raw.github.com/robbyrussell/oh-my-zsh/master/tools/install.sh | sh
    - chsh 명령어로 기본 쉘을 변경해도 로그아웃 후 재 로그인 전까지 Terminal 앱에서는 bash 쉘이 기본
    - vi ~/.zshrc 가서 ZSH_THEME="agnoster"로 변경을 추천
  - zsh-autosuggestions: 명령어 history 기반으로 입력할 명령어를 추천
    - git clone git://github.com/zsh-users/zsh-autosuggestions $ZSH_CUSTOM/plugins/zsh-autosuggestions --depth=1
  - zsh-syntax-highlighting: 명령어에 하이라이팅
    - git clone https://github.com/zsh-users/zsh-syntax-highlighting.git ${ZSH_CUSTOM:-~/.oh-my-zsh/custom}/plugins/zsh-syntax-highlighting --depth=1
  - zsh-better-npm-completion: Node.js를 사용하여 개발할 때 npm script 자동완성을 지원
    - git clone https://github.com/lukechilds/zsh-better-npm-completion ~/.oh-my-zsh/custom/plugins/zsh-better-npm-completion --depth=1
  - zshrc에 적용
      plugins=(
          git
          zsh-autosuggestions
          zsh-syntax-highlighting
          zsh-better-npm-completion
      )

  <br/>
<br/>
<br/>

        참조:

<br/>

<br/>

## 개념: windows terminal 설정

<br/>
https://github.com/microsoft/terminal/issues/2743
https://medium.com/@callback.insanity/windows-terminal-changing-the-default-shell-c4f5987c31
https://medium.com/@slmeng/how-to-install-powerline-fonts-in-windows-b2eedecace58

  1. 기본 directory 설정
    - "startingDirectory": "//wsl$/Ubuntu-20.04/home/Your Ubuntu Username" 처럼 우분투 디렉토리 내부에 설정하는게 일반적이다.
    - 나 같은 경우는 c드라이브가 아닌 다른 드라이브에 작업들을 저장해놓으려고 했다.
    - "startingDirectory": "E:dailyscat/18.04"와 같이 작성
  2. 탭 네임 설정
    - "suppressApplicationTitle": true,
  3. 새 탭의 기본 쉘 설정
    - "defaultProfile": "{c6eaf9f4-32a7-5fdc-b5cf-066e8a4b1e40}",
  4. 폰트설정 
    - windows에 글꼴을 설치하고 해당하는 이름만 터미널의 설정에서 fontFace: ~~~ 로 주면 적용된다. 
    - 글꼴설치는 시작버튼을 오른쪽 클릭 후 메뉴에서 실행 -> %windir%\fonts -> 원하는 ttf 파일을 붙여넣기 하면 된다.

conemu와 windows terminal 둘다 사용해봤지만... 
conemu는 bridge를 적용해서 사용하는 부분에서 일단 에러가 나고 테마가 적용되도 기본 터미널 쓰는 것만 못하다는 생각이 많이 든다.
그냥 기본 windows terminal에 powerline을 적용해서 사용하는게 가장 속편함..

#### WSL Ubuntu에서 Powerline 설정

https://docs.microsoft.com/ko-kr/windows/terminal/tutorials/powerline-setup#set-up-powerline-in-wsl-ubuntu
공식 문서에서는 powerline-go를 사용하라고 되어 있지만.. 일단 [12이상의 go](https://github.com/kubeedge/kubeedge/issues/981)에서만 정상적으로 처리되며 
zsh 명령어를 못찾는 에러가 있다.


ps. 해당 이슈 해결은 사설 패키지 저장소를 추가하면 1.10 버전보다 높은 golang을 받게 되므로써 해결된다.
https://yoonbh2714.blogspot.com/2019/11/ubuntu-apt-golang-113.html

```
# apt 패키지로 golang 을 설치하면 1.10 이 최신이라고 나온다.
sudo apt install golang

# Ubuntu 16.04 LTS, 18.04 LTS or 19.04 에서는
# 다음 사설 패키지 저장소를 추가하면 최신 버전인 v1.13 을 설치 할 수 있다.
sudo add-apt-repository ppa:longsleep/golang-backports
sudo apt install golang
```

[powerline-shall](https://github.com/b-ryan/powerline-shell#zsh) 원본은 pip를 활용해서 하기 때문에 python 설치가 필요하다.
해당 설치 후에  [에러](https://github.com/powerline/powerline/issues/187)가 날 수 있는데 이때 기존의 `export PATH=$HOME/bin:/usr/local/bin:$PATH`를  `export PATH=$HOME/.local/bin:$PATH`로 바꿔주면 해결이 됐다. 

powerline-shall의 테마를 활용하기위해

1. .zshrc에 해당 내용을 넣는다.
      function powerline_precmd() {
                  PS1="$(powerline-shell --shell zsh $?)"
      }

      function install_powerline_precmd() {
        for s in "${precmd_functions[@]}"; do
          if [ "$s" = "powerline_precmd" ]; then
            return
          fi
        done
        precmd_functions+=(powerline_precmd)
      }

      if [ "$TERM" != "linux" ]; then
        install_powerline_precmd
      fi

2. config 파일을 생성하고 테마를 결정한다.

- `mkdir -p ~/.config/powerline-shell`
- 해당 디렉토리로 가서 `powerline-shell --generate-config`
- vi 해당 디렉토리
- 
      {
      "segments": [
        "virtual_env",
        "username",
        "hostname",
        "ssh",
        "cwd",
        "git",
        "hg",
        "jobs",
        "root"
      ],
      "theme": "~/.local/lib/python2.7/site-packages/powerline_shell/themes/basic.py"
      // 해당 부분은 본인이 설치한 영역을 찾아 들어가야 한다. 해당 부분에서 에러나서 절대경로로 잡아주니 해결
      }
- basic을 기존에 있는 다른 테마들로 변경해도 된다.

그럼 가독성 좋은 디렉토리배열과 ci를 구성해볼 수 있다

즉 내 zsh 쉘은 oh my zsh과 powerline shall을 혼합하여 
plugin은 plugin 대로 쓰고 테마는 또 테마대로 이쁘게 쓰게 되었다.

뿌듯하다.

<br/>
<br/>
<br/>

        참조:

<br/>

그밖의 설정에 도움이 된 사이트

- ubuntu  apt-get 명령어 정리
  - https://luckeex.tistory.com/290

- 프론트엔드 개발 위한 wsl 환경 구성
```
윈도우10 Frontend 개발환경 세팅(1). 요즘 프론트엔드 개발 공부에 푹 빠져서 웹개발에 최적화 된 개발환경… | by boystyou82 | Medium
https://medium.com/@boystyou82/%EC%9C%88%EB%8F%84%EC%9A%B010-frontend-%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD-%EC%84%B8%ED%8C%85-1-425d042b9bf

윈도우10 Frontend 개발환경 세팅(2). 지난 글에서는 윈도우10에서 네이티브 리눅스와 유사한 환경을 제공해주는… | by boystyou82 | Medium
https://medium.com/@boystyou82/%EC%9C%88%EB%8F%84%EC%9A%B010-frontend-%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD-%EC%84%B8%ED%8C%85-2-d82b47136e63

윈도우10 Frontend 개발환경 세팅(3). 지난 글까지 wsl설치, 터미널의 색상 및 폰트를 변경하는 법을… | by boystyou82 | Medium
https://medium.com/@boystyou82/%EC%9C%88%EB%8F%84%EC%9A%B010-frontend-%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD-%EC%84%B8%ED%8C%85-3-badc8f520373
```

- conemu 활용(포스팅 내용 자체는 좋지만.. 파워라인을 사용하는게 개인적으로 좋아보인다.)
 - https://rldnddl87.github.io/devops/wsl2_setup/

- linux,unxi 툴에 대한 팁 정리 사이트-> 정말 좋다 꾸준히 참고할 듯
 - https://www.lesstif.com/lpt/solarized-dark-light-theme-42074154.html
