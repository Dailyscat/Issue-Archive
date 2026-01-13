<!-- author: Dailyscat purpose: issue arrange rules: (1) 헤더와 문단사이 <br/> <br/> (2) 코드가 작성되는 부분은 >로 정리 (3) 참조는 해당 내용 바로 아래 <br/> <br/> (4) 명령어는 bold (5) 방안은 ## 안의 과정은 ### -->

Issue:

<br/> <br/>

상황:

<br/> <br/>
OpenSearch Dashboards에서 template_data 인덱스 기준으로 templateConfigId가 특정 값인 문서를 찾고, enable=true 조건까지 같이 걸어서 조회하려고 함.
추가로 lastModifiedDate 기준으로 최신 순 정렬까지 붙이려 했는데, 처음에 Query DSL을 term 하나로 두 조건을 같이 넣는 형태로 작성해서 쿼리가 실패/오동작함.
<br/> <br/>
참조:
<br/> <br/>

OpenSearch/Elasticsearch Query DSL에서 term은 단일 필드 조건만 지원 (복수 조건은 bool 조합 필요)
<br/> <br/>

생각해낸 방안:

<br/> <br/>

조건이 2개 이상이면 bool.filter로 분리해서 걸기

templateConfigId가 매핑에 따라 keyword 서브필드가 필요할 수 있으니 .keyword 케이스도 같이 준비

정렬은 sort로 lastModifiedDate 내림차순 적용
<br/> <br/>
참조:
<br/> <br/>

filter는 스코어링 없이 조건만 필터링해서 성능/의도 측면에서 적합
<br/> <br/>

방안:

<br/> <br/>

1. 두 조건을 bool.filter로 분리해서 조회

<br/> <br/>

GET template_data/\_search
{
"query": {
"bool": {
"filter": [
{ "term": { "templateConfigId": "seller_event" } },
{ "term": { "enable": true } }
]
}
},
"sort": [
{ "lastModifiedDate": { "order": "desc" } }
]
}
<br/> <br/>
참조:
<br/> <br/>

term 여러 개를 AND로 묶으려면 bool.filter/must를 사용해야 함
<br/> <br/>

2. templateConfigId가 text 매핑이면 .keyword로 정확 일치 처리

<br/> <br/>

GET template_data/\_search
{
"query": {
"bool": {
"filter": [
{ "term": { "templateConfigId.keyword": "seller_event" } },
{ "term": { "enable": true } }
]
}
},
"sort": [
{ "lastModifiedDate": { "order": "desc" } }
]
}
<br/> <br/>
참조:
<br/> <br/>

text 필드는 분석(토큰화)되기 때문에 정확 일치는 보통 keyword 필드로 처리
<br/> <br/>

3. 정렬이 이상하거나 실패하면 매핑 확인

<br/> <br/>

GET template_data/\_mapping
<br/> <br/>
참조:
<br/> <br/>

lastModifiedDate가 date 타입이 아니면 정렬이 기대와 다르게 나오거나 에러가 날 수 있음
