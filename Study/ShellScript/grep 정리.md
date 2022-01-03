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

```