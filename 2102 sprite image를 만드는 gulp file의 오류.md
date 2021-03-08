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

# Issue: 2102 스프라이트 이미지가 완전하게 만들어지지 않는 오류

## 상황: 스프라이트 이미지를 빌드하는 서버에서 간헐적으로 이미지가 중간에 짤려서 완성되는 이슈

## 생각해낸 방안:

- jenkins 스크립트의 에러
- sprite plugin 만든 원작자에게 물음

## 방안: jenkins 스크립트의 에러(실패)

<br/>

1. 이전 빌드에서 만들어진 디렉토리 삭제
고쳐진듯 싶었으나 안됨.
`rm -rf ${WORKSPACE}/dist`

2. 파이프로 만들어진 파일이름 찾는 커맨드를 변경
jenkins 빌드 로그를 유심히 보니 파이프의 순서가 shall script와 다르게 작동하는 부분이 있어서 이를 고쳐봤지만 안됨.

jenkins shell에서 pipe 문법을 사용하는것은 안티패턴

<br/>
<br/>
<br/>

        참조:

<br/>

## 방안: sprite plugin 만든 원작자에게 물음

<br/>

        return folders.map((folder) => {
            return new Promise((resolve) => {
            gulp.src(path.join(paths.sprite_png_src, folder, '*@2x.png'))
                .pipe(gulpSort())
                .pipe(spritesmith(options.spritesmith({folder, paths})))
                .pipe(gulp.dest(paths.img_src))
                .on('end',resolve);
            });
        })

위 코드를 node에서 실행했을 때 folders.map에서 promise들로 이루어진 배열이 리턴 됐을 때 해당 프로미스 객체들이 전부 done 되었을 때 task가 끝나는게 보장되지 않아서 수정이 필요할 것 같다는 말이 핵심이다.


<br/>
<br/>
<br/>

        참조:
        https://github.com/twolfson/gulp.spritesmith/issues/154

<br/>
