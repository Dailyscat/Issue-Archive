# 조건문 if

```
#!/bin/bash

value1=10
value2=10

if[$value1 = $value2]
then
    echo True
else
    echo False
fi
```

```
#!/bin/bash

value=0

if[$value = 0]
then
    echo True
else
    echo False
fi
```

```
// 연산자 $변수 타입
// -z는 변수에 저장된 값의 길이가 0인지를 비교하여 0이면 true

#!/bin/bash

value=""

if[ -z $value]
then
    echo True
else
    echo False
fi
```

```
// if [조건식] 연산자 [조건식]; then 타입
// AND 나 OR 연산자와 함께 사용할 때

#!/bin/bash

value=5

if[$value -gt 0] && [$value -lt 10]
then
    echo True
else
    echo False
fi
```
