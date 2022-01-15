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

# Issue: yarn berry 관련 정리

## 상황: 마이그레이션을 위해 공부하던 부분 정리

<br/>

## 알게된 부분 정리:

- intellij에서 .cache 에 있는 zip 파일 쓸 때 좋은 플러그인
- yarn은 현재에 정말 npm보다 좋은가? 
- unplugged 디렉토리로 들어가는 패키지의 종류
- zero install을 사용할 때 gitignore 설정
- unplugged에 yarn의 기본 플러그인은 plugin-compat 관련 디펜던시가 꼭 들어간다.

<br/>

## 개념: intellij에서 .cache 에 있는 zip 파일 쓸 때 좋은 플러그인

<br/>
zip 파일 내부 확인가능.

https://plugins.jetbrains.com/plugin/9491-archive-browser
<br/>
<br/>
<br/>

        참조:

<br/>

## 개념: yarn은 현재에 정말 npm보다 좋은가? 

<br/>

  - 전역 캐시를 활용하여 offline mirroring 가능
    - 이는 사라진 패키지로 인한 불상사를 겪지 않게 된다.
  - yarn은 병렬 리소스 사용을 통해 확실히 빠른 속도를 보장한다(설치)
  ![](image/2022-01-07-23-35-01.png)
  
  - Flat mode 설치를 통해 중복 버전 설치를 제한한다.
  
  - using checksum to verify the integrity of every package and the ability to check licenses of your installed packages.

  ```
  If throw (the default), Yarn will throw an exception on yarn install if it detects that a package doesn't match the checksum stored within the lockfile. If update, the lockfile checksum will be updated to match the new value. If ignore, the checksum check will not happen.
        checksumBehavior: "throw"
  ```

  - 




<br/>
<br/>
<br/>

        참조:
        https://medium.com/zero-equals-false/facebooks-yarn-vs-npm-is-yarn-really-better-1890b3ea6515
        https://www.whitesourcesoftware.com/free-developer-tools/blog/npm-vs-yarn-which-should-you-choose/#Comparing_Yarn_vs_npm_similarities_and_differences

<br/>

## 개념: unplugged 디렉토리로 들어가는 패키지의 종류

<br/>
  - postInstall을 사용하는 패키지
    - 주로 후원을 사용하는 패키지가 많음, 하지만 확인 필
  - 패키지 내에 아래 확장자의 파일이 있으면 unplugged로 뺀다. 이는 os에 의존성이 있는 패키지일 수 있기 때문에.
  ```
   const FORCED_EXTRACT_FILETYPES = new Set([ 
   // Windows can't execute exe files inside zip archives 
   `.exe`, 
   // The c/c++ compiler can't read files from zip archives 
   `.h`, `.hh`, `.hpp`, `.c`, `.cc`, `.cpp`, 
   // The java runtime can't read files from zip archives 
   `.java`, `.jar`, 
   // Node opens these through dlopen 
   `.node`, 
 ]); 
  ```

  unplugged에 넣지 않아도 되는 파일은 dependenciesMeta에서 해당 패키지에 built: false처리 하면 된다. 이때 해당 패키지가 빌드가 필요 없는지 꼭 확인해야 한다.

  ```
  "dependenciesMeta": {
"fsevents": {
If false, the package will never be built (deny-list). This behavior is reversed when the enableScripts yarnrc setting is toggled off - when that happens, only packages with built explicitly set to true will be executed (allow-list), and those with built explicitly set to false will simply see their build script warnings downgraded into simple notices.

"built": false,
If true, the build isn't required to succeed for the install to be considered a success, and the dependency may be skipped if its os and cpu fields don't match the current system architecture. It's what the optionalDependencies field compiles down to.

This settings will be applied even when found within a nested manifest, but the highest requirement in the dependency tree will prevail.

"optional": false,
If true, the specified package will be automatically unplugged at install time. This should only be needed for packages that contain scripts in other languages than Javascript (for example nan contains C++ headers).

"unplugged": true,
}
}

  ```
<br/>
<br/>
<br/>

        참조:
        https://github.com/yarnpkg/berry/discussions/3197
        https://yarnpkg.com/configuration/manifest#dependenciesMeta
        https://github.com/zloirock/core-js/issues/758

<br/>

## 개념: zero install을 사용할 때 gitignore 설정

<br/>

  ```
  .yarn/plugins and .yarn/releases contain the Yarn releases used in the current repository (as defined by yarn set version). You will want to keep them versioned (this prevents potential issues if, say, two engineers use different Yarn versions with different features).
.yarn/unplugged should likely always be ignored, since it may contain native builds
.yarn/build-state.yml should likely be ignored as well, as it contains the build infos
If for some reason you version unplugged, it may make sense to keep build-state as well
.yarn/cache may be ignored, but you'll need to run yarn install to regenerate it
Versioning it unlocks what we call Zero-Installs - it's optional, though
.pnp.js (and potentially .pnp.data.json) are in the same boat as the cache. Add it to your repository if you keep your cache in your repository, and ignore it otherwise.
yarn.lock should always be stored within your repository (even if you develop a library)
So to summarize:

If you're using Zero-Installs:

.yarn/unplugged
.yarn/build-state.yml
If you're not using Zero-Installs:

.yarn/*
!.yarn/releases
!.yarn/plugins
.pnp.*
  ```

<br/>
<br/>
<br/>

        참조:
        https://github.com/yarnpkg/berry/issues/454#issuecomment-530312089

<br/>

## 개념: unplugged에 yarn의 기본 플러그인은 plugin-compat 관련 디펜던시가 꼭 들어간다.

<br/>
  
  yarn install이라는 step을 아예 없애고 싶었는데 이 부분은 불가능하다. yarn의 기본 패키지에 fsevents와 같은 os 의존적인 패키지가 존재하기 때문에 yarn install 스텝은 꼭 필요.

<br/>
<br/>
<br/>

        참조:
        https://github.com/yarnpkg/berry/pull/2485/files
        https://github.com/yarnpkg/berry/pull/692

<br/>

## 개념:

<br/>
  개념에 대한 내용
<br/>
<br/>
<br/>

        참조:

<br/>
