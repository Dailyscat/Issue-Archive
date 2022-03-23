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

# Issue: 같은 프로젝트 구조, 같은 maven 세팅인데 mvn compile에서 에러

## 상황: 같은 프로젝트 구조, 같은 프로젝트 세팅, 같은 maven-plugin 인데 자꾸 컴파일 단계에서 에러가 발생

<br/>

## 알게된 부분 정리:

- jenv 삭제
- JAVA_HOME을 변경하여 사용하는 java version 적용

<br/>

## 개념: jenv 삭제

<br/>
  여러 버전의 자바 사용을 위해 jenv를 사용하고 있었는데 혹시 이게 문제인가 싶어서 jenv 삭제. 수동으로 java 버전 적용이 훨씬 오류 디버깅하는데 가시성있다고 생각이들기도했다.

  하지만 해결은 안됐다.

  ```
  Hi, chaps.

I had the same issue, I had a problem with jenv and decided to clearly uninstall it from my macOS Big Sur, I think this would be the same process for other versions of macOS, so I decided to post my approach to this.

Run the brew command to uninstall it: brew uninstall jenv. At this point you should not have the executables, but still have some scripts referencing jenv left on your system.
I am using iTerm2 with zsh, so I had to remove part of the code in my ~/.zshrc file, (you can remove relevant code in the ~/.bash_profile file), the code to be removed is something like this:
# To customize prompt, run `p10k configure` or edit ~/.p10k.zsh.
[[ ! -f ~/.p10k.zsh ]] || source ~/.p10k.zsh
export PATH="$HOME/.jenv/bin:$PATH"
eval "$(jenv init -)"
Remove the .jenv folder: rm -rf .jenv
I hope this is it, this fixed all problems for me and I was able to reinstall in from scratch.
  ```
<br/>
<br/>
<br/>

        참조:
        https://github.com/jenv/jenv/issues/95

<br/>

## 개념: JAVA_HOME을 변경하여 사용하는 java version 적용

<br/>
  java -version을 확인해보니 잘되는 컴퓨터는 기본버전이 8 문제가 있는 컴퓨터는 기본이 14였다. 이 부분에서 문제가 발생,,,,, 인텔리제이 후두려 패도 절대 안되던 이유가 있었다.

  결국 현재쓰는 zshrc에 $JAVA_HOME 환경변수를 변경해서 자바 기본 버전을 바꿔주니 해결됐다. 어떻게 돌아가는지 모르고 그냥 maven 커맨드 쓰던 죄 값을 톡톡히 치뤘다. 하지만 이번기회로 자바 버전 수동으로 어떻게 설정하는지 maven 커맨드 쓸 때 내부에서 자바 기본 버전을 어떻게 가져가는지 좀 알 수 있었다.

  ```
  First run /usr/libexec/java_home -V which will output something like the following:

Matching Java Virtual Machines (3):
1.8.0_05, x86_64:   "Java SE 8" /Library/Java/JavaVirtualMachines/jdk1.8.0_05.jdk/Contents/Home
1.6.0_65-b14-462, x86_64:   "Java SE 6" /System/Library/Java/JavaVirtualMachines/1.6.0.jdk/Contents/Home
1.6.0_65-b14-462, i386: "Java SE 6" /System/Library/Java/JavaVirtualMachines/1.6.0.jdk/Contents/Home

/Library/Java/JavaVirtualMachines/jdk1.8.0_05.jdk/Contents/Home
Pick the version you want to be the default (1.6.0_65-b14-462 for arguments sake) then:

export JAVA_HOME=`/usr/libexec/java_home -v 1.6.0_65-b14-462`
or you can specify just the major version, like:

export JAVA_HOME=`/usr/libexec/java_home -v 1.8`
Now when you run java -version you will see:

java version "1.6.0_65"
Java(TM) SE Runtime Environment (build 1.6.0_65-b14-462-11M4609)
Java HotSpot(TM) 64-Bit Server VM (build 20.65-b04-462, mixed mode)
Add the export JAVA_HOME… line to your shell’s init file.

For Bash (as stated by antonyh):

export JAVA_HOME=$(/usr/libexec/java_home -v 1.8)
For Fish (as stated by ormurin)

set -x JAVA_HOME (/usr/libexec/java_home -d64 -v1.8)
Updating the .zshrc file should work:

nano ~/.zshrc

export JAVA_HOME=$(/usr/libexec/java_home -v 1.8.0)
Press CTRL+X to exit the editor Press Y to save your changes

source ~/.zshrc
echo $JAVA_HOME
java -version
Share
Edit
Follow

  ```


<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/21964709/how-to-set-or-change-the-default-java-jdk-version-on-macos
        https://www.delftstack.com/howto/java/change-java-version-mac/
        https://stackoverflow.com/questions/15826202/where-is-java-installed-on-mac-os-x

<br/>
