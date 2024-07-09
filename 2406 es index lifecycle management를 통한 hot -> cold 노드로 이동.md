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

# Issue: es index lifecycle management를 통한 hot -> cold 노드로 이동

## 상황:

<br/>

## 알게된 부분 정리:

- kibana ui

<br/>

## 개념: kibana ui, devtool

<br/>

    index management에서 json editor로 아래 정책과 비슷하게 넣고 추가해도됨.

    이때 es 노드의 box_type의 cold여야 한다.

    kibana ui에서 target index를 정할 수 도 있음.

    ```
        node.attr.box_type: cold
    ```


    ```
    {
    "policy": {
        "phases": {
        "hot": {
            "actions": {
            "rollover": {
                "max_size": "50gb",
                "max_age": "30d"
            }
            }
        },
        "warm": {
            "actions": {
            "forcemerge": {
                "max_num_segments": 1
            },
            "shrink": {
                "number_of_shards": 1
            },
            "allocate": {
                "number_of_replicas": 1
            }
            }
        },
        "cold": {
            "actions": {
            "allocate": {
                "require": {
                "box_type": "cold"
                }
            },
            "freeze": {}
            }
        },
        "delete": {
            "min_age": "90d",
            "actions": {
            "delete": {}
            }
        }
        }
    }
    }
    ```

<br/>
<br/>
<br/>

        참조:
        https://discuss.elastic.co/t/ilm-on-single-node/333997/8
        https://medium.com/@kausarbazla/mastering-elasticsearch-index-management-tips-tricks-c36a26916477

<br/>
