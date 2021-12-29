# 반복문 for, while

```
for num in 1, 2, 3
do
    echo $num;
done
```

```
// 쉘 스크립트에는 숫자나 문자열 이외에 디렉터리나 파일과 같은 객체를 범위로 사용할 수 있다.

for file in $HOME/*
do
    echo $file;
done
```

```
// 반복문의 범위값을 사용할 때 연속된 숫자를 나열할 경우가 있다.
// {초기값..마지막값} 여기서 ..은 생략의 의미

for num in {1..5}
do 
    echo $num;
done

// 중괄호에 범위를 사용할 땐 증가값을 1이 아닌 그 이상으로 표현도 가능하다.
// {초기값..마지막값..증가값}

for num in {1..10..2}
do
    echo $num;
done
```

```
// 범위를 배열로 사용도 가능하다.
// 이때 배열 선언 시 값 사이에 쉼표(,)를 사용하면 안됨
// 이때 배열 내부의 모든 아이템이 범위일 경우 ${배열[@]}을 사용하여 명시해야한다.
// 이는 위치매개변수 $@를 사용, 파라미터로 넘어오는 모든 매개변수 의미한다.

#!/bin/bash

array=("apple", "banana", "pineapple")

for fruit in ${array[@]}
do
    echo $fruit;
done

#!/bin/bash
for((num=0; num < 3; num++))
do
    echo $num;
done

```

# while문

```
num = 0;

while [$num -lt 3]
do
    echo $num
    num - $((num+1))
done
```

