# switch-case문

```
기본적인 switch case

case $변수 in
    조건값1)
    수행문1;;
    조건값2)
    수행문2;;
    조건값3)
    수행문3;;
    *)
    수행문4
esac
```

```
case $1 in
    start)
    echo "Start"
    ;;
    stop)
    echo "Stop"
    ;;
    restart)
    echo "Restart"
    ;;
    help)
    echo "Help"
    ;;
    *)
    echo "Please input sub command"
esac

변수가 있을 시 해당하는 문구 출력
없을 시 *) 아래 문구 출력
```