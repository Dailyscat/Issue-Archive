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

# Issue: wsl와 intelliJ 개발 환경 구성(적용은 했지만 사용X)

## 상황: 
ubuntu에서 멀티 모니터 지원이 잘 안되는 이유로 wsl을 활용하여 
개발환경을 구성하기로 했고 달려왔지만... 막상 wsl 에서 intellij 사용하면
nativeGL을 지원한대도 굉장히 침침한 느낌이고 gradle 빌드도 이상하게 되는 오류가 있다.
그리고 이러한 오류를 해결하고 안고 간다 해도.. 자잘한 버그들을 해결해 나가면서
이런 환경을 써가는게 생산성 면에서 너무 안좋을 것 같다.
일단 정식적으로 서포트 하기전에는 쓰지 않는걸로 결정!

그래서 node.js 기반 개발 환경은 wsl에서 전적으로 구성하여 활용하고
intellij, spring boot 개발 환경은 일단 windows에서 활용하고 terminal에서만 우분투 활용, docker를 변행하여
개발환경 구성하기로 했다!

<br/>

## 알게된 부분 정리:

- 적용 과정 archive

<br/>

## 개념: 적용 과정 archive

<br/>
  
  - WSL2 사용하도록 설정
  - powershell 열고 `wsl --set-default-version 2`
  - ubuntu, docker 설치, 설정
  - vcXsrv 설치
    - Display number를 0으로 변경. 나머지 기본값 진행
    - Native opengl 활성화
    - Save configuration이 나오면 클릭하고 문서에 config로 저장   
  - vcXsrv 설정
    - 윈도우 + R 이후 shell:startup 실행해서 시작 프로그램 열기
    - 저장한 config를 시작 프로그램에 붙여넣기 (윈도우 시작시 자동실행)
    - 설치 후 reboot 한번 해야한다.

    - 우분투에서.
        - echo 'export DISPLAY=:0' >> ~/.zshrc
        - echo LIBGL_ALWAYS_INDIRECT=1 >> ~/.zshrc

    - windows terminal에서 `Set-ExecutionPolicy -ExecutionPolicy RemoteSigned` 적용

    - 우분투에서

        # curl
        sudo apt update && sudo apt install -y \
            apt-transport-https \
            ca-certificates \
            curl \
            gnupg-agent \
            software-properties-common

        # Add Node.js to sources.list
        curl -sL https://deb.nodesource.com/setup_14.x | sudo -E bash -

        # Add Yarn to sources.list
        curl -sS https://dl.yarnpkg.com/debian/pubkey.gpg | sudo apt-key add -
        echo "deb https://dl.yarnpkg.com/debian/ stable main" | sudo tee /etc/apt/sources.list.d/yarn.list

        # Add Docker to sources.list
        curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
        sudo add-apt-repository \
        "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
        bionic \
        stable"

        # Install tools
        sudo apt update && sudo apt upgrade
        sudo apt install -y \
            containerd.io \
            docker-ce \
            docker-ce-cli \
            fontconfig \
            git \
            make \
            nodejs \
            tig \
            yarn \
            zsh \

        # Add user to docker group
        sudo usermod -aG docker $USER

        # Create .screen folder used by .zshrc
        mkdir ~/.screen && chmod 700 ~/.screen

        gpg --generate-key
        gpg --list-secret-keys

    - git setup!
        # Set username and email for next commands
        email="contact@alex-d.fr"
        username="Alex-D"
        gpgkeyid="8FA78E6580B1222A"

        # Configure Git
        git config --global user.email "${email}"
        git config --global user.name "${username}"
        git config --global user.signingkey "${gpgkeyid}"
        git config --global commit.gpgsign true
        git config --global core.pager /usr/bin/less
        git config --global core.excludesfile ~/.gitignore

        # Generate a new key
        ssh-keygen -t rsa -b 4096 -C "${email}"

        # Start ssh-agent and add the key to it
        eval $(ssh-agent -s)
        ssh-add ~/.ssh/id_rsa

        # Display the public key ready to be copy pasted to GitHub
        cat ~/.ssh/id_rsa.pub

    - 연결과 zsh 쉘 설정

        .aliases.zsh
        ```
        alias ..="cd .."
        alias ...="cd ../.."
        alias ....="cd ../../.."
        alias .....="cd ../../../.."

        alias ls='ls -G'
        if [[ $(uname) != "Darwin" ]]
        then
        alias ls='ls --color=auto'
        fi
        alias lsa='ls -lah'
        alias l='ls -lah'
        alias ll='ls -lh'
        alias la='ls -lAh'

        alias grep="grep --color=auto --exclude-dir={.bzr,CVS,.git,.hg,.svn}"

        alias sf="docker-compose exec php bin/console"
        alias dc="docker-compose exec php composer"

        alias idea="(pkill -f 'java.*idea' || true) && screen -d -m /opt/idea/bin/idea.sh"
        alias wslb="PowerShell.exe 'Start-Process PowerShell -Verb RunAs \"PowerShell -File \$env:USERPROFILE\\wsl2-bridge.ps1\"'"

        ```
        .zshrc
        ```
        # Autoload compaudit
        autoload -U compinit
        compinit

        # Do not want background jobs to be at a lower priority
        unsetopt BG_NICE

        # WSL specific things
        if grep --quiet microsoft /proc/version 2>/dev/null; then
        # Set Windows display for WSL
        export DISPLAY=$(cat /etc/resolv.conf | grep nameserver | awk '{print $2; exit;}')':0.0'
        export LIBGL_ALWAYS_INDIRECT=1
        fi

        # Custom aliases
        [ -f ~/.aliases.zsh ] && source ~/.aliases.zsh

        # Preferred editor for local and remote sessions
        export EDITOR='vim'

        # screen
        export SCREENDIR=$HOME/.screen

        # If you come from bash you might have to change your $PATH.
        export PATH=$HOME/.local/bin:$PATH

        # Path to your oh-my-zsh installation.
        export ZSH=$HOME/.oh-my-zsh

        # Set name of the theme to load --- if set to "random", it will
        # load a random theme each time oh-my-zsh is loaded, in which case,
        # to know which specific one was loaded, run: echo $RANDOM_THEME
        # See https://github.com/ohmyzsh/ohmyzsh/wiki/Themes
        ZSH_THEME="agnoster"

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


        ## History command configuration
        HISTSIZE=5000                 # How many lines of history to keep in memory
        HISTFILE=~/.zsh_history       # Where to save history to disk
        SAVEHIST=5000                 # Number of history entries to save to disk
        setopt extended_history       # record timestamp of command in HISTFILE
        setopt hist_expire_dups_first # delete duplicates first when HISTFILE size exceeds HISTSIZE
        setopt hist_ignore_dups       # ignore duplicated commands history list
        setopt hist_ignore_space      # ignore commands that start with space
        setopt hist_verify            # show command with history expansion to user before running it
        setopt inc_append_history     # add commands to HISTFILE in order of execution
        setopt share_history          # share command history data

        # Set list of themes to pick from when loading at random
        # Setting this variable when ZSH_THEME=random will cause zsh to load
        # a theme from this variable instead of looking in $ZSH/themes/
        # If set to an empty array, this variable will have no effect.
        # ZSH_THEME_RANDOM_CANDIDATES=( "robbyrussell" "agnoster" )

        # Uncomment the following line to use case-sensitive completion.
        # CASE_SENSITIVE="true"

        # Uncomment the following line to use hyphen-insensitive completion.
        # Case-sensitive completion must be off. _ and - will be interchangeable.
        # HYPHEN_INSENSITIVE="true"

        # Uncomment the following line to disable bi-weekly auto-update checks.
        # DISABLE_AUTO_UPDATE="true"

        # Uncomment the following line to automatically update without prompting.
        # DISABLE_UPDATE_PROMPT="true"

        # Uncomment the following line to change how often to auto-update (in days).
        # export UPDATE_ZSH_DAYS=13

        # Uncomment the following line if pasting URLs and other text is messed up.
        # DISABLE_MAGIC_FUNCTIONS="true"

        # Uncomment the following line to disable colors in ls.
        # DISABLE_LS_COLORS="true"

        # Uncomment the following line to disable auto-setting terminal title.
        # DISABLE_AUTO_TITLE="true"

        # Uncomment the following line to enable command auto-correction.
        # ENABLE_CORRECTION="true"

        # Uncomment the following line to display red dots whilst waiting for completion.
        # COMPLETION_WAITING_DOTS="true"

        # Uncomment the following line if you want to disable marking untracked files
        # under VCS as dirty. This makes repository status check for large repositories
        # much, much faster.
        # DISABLE_UNTRACKED_FILES_DIRTY="true"

        # Uncomment the following line if you want to change the command execution time
        # stamp shown in the history command output.
        # You can set one of the optional three formats:
        # "mm/dd/yyyy"|"dd.mm.yyyy"|"yyyy-mm-dd"
        # or set a custom format using the strftime function format specifications,
        # see 'man strftime' for details.
        # HIST_STAMPS="mm/dd/yyyy"

        # Would you like to use another custom folder than $ZSH/custom?
        # ZSH_CUSTOM=/path/to/new-custom-folder

        # Which plugins would you like to load?
        # Standard plugins can be found in $ZSH/plugins/
        # Custom plugins may be added to $ZSH_CUSTOM/plugins/
        # Example format: plugins=(rails git textmate ruby lighthouse)
        # Add wisely, as too many plugins slow down shell startup.
        plugins=(
        git
        zsh-autosuggestions
        zsh-syntax-highlighting
        zsh-better-npm-completion
        )

        source $ZSH/oh-my-zsh.sh

        # User configuration

        # export MANPATH="/usr/local/man:$MANPATH"

        # You may need to manually set your language environment
        # export LANG=en_US.UTF-8

        # Preferred editor for local and remote sessions
        # if [[ -n $SSH_CONNECTION ]]; then
        #   export EDITOR='vim'
        # else
        #   export EDITOR='mvim'
        # fi

        # Compilation flags
        # export ARCHFLAGS="-arch x86_64"

        # Set personal aliases, overriding those provided by oh-my-zsh libs,
        # plugins, and themes. Aliases can be placed here, though oh-my-zsh
        # users are encouraged to define aliases within the ZSH_CUSTOM folder.
        # For a full list of active aliases, run `alias`.
        #
        # Example aliases
        # alias zshconfig="mate ~/.zshrc"
        # alias ohmyzsh="mate ~/.oh-my-zsh"
        export DISPLAY=:0
        LIBGL_ALWAYS_INDIRECT=1

        ```

        .gitignore
        ```
        .idea/
        .DS_Store
        *~

        ```

        intellij 설치

        ```
        sudo mkdir /opt/idea
        # Allow user to run IDEA updates from GUI
        sudo chmod 777 /opt/idea
        curl -L "https://download.jetbrains.com/product?code=IIU&latest&distribution=linux" | tar vxz -C /opt/idea --strip 1
        echo 'alias idea="nohup /opt/idea-IU-193.6494.35/bin/idea.sh > /dev/null 2>&1 &"' >> ~/.zshrc
        ```


        # windows에 추가되야 할 설정
            windowsUserProfile=/mnt/c/Users/$(cmd.exe /c "echo %USERNAME%" 2>/dev/null | tr -d '\r')

            # Avoid too much RAM consumption
            cp ~/dev/dotfiles/.wslconfig ${windowsUserProfile}/.wslconfig

            # Get the hacky network bridge script
            cp ~/dev/dotfiles/wsl2-bridge.ps1 ${windowsUserProfile}/wsl2-bridge.ps1

        # 마무리

            # This is a custom alias, see .aliases.zsh for more details
            wslb
<br/>
<br/>
<br/>

        참조:
        https://github.com/Alex-D/dotfiles
        https://hy.ne.kr/5

<br/>

## 개념:

<br/>
  개념에 대한 내용
<br/>
<br/>
<br/>

        참조:

<br/>
