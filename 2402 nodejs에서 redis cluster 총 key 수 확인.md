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

# Issue: nodejs에서 redis cluster 총 key 수 확인

## 상황:

<br/>

## 알게된 부분 정리:

- client.scan
- client.dbSize

<br/>

## 개념: client.scan

<br/>

```

import { createCluster } from 'redis';

const cluster = createCluster({
  rootNodes: [
    {
      url: 'redis://host:port'
    },
    {
      url: 'redis://host:port'
    },
    {
      url: 'redis://host:port'
    }
  ],
  defaults: {
    username: '', // username 빈값으로 안하면 에러가 발생함.
    password: ''
  }
});

  await cluster.connect();
  let count = 0;
  await Promise.all(
    cluster.masters.map(async (master) => {
      const client = await cluster.nodeClient(master);
      let currentCursor = 0;
      do {
        // eslint-disable-next-line no-await-in-loop
        const { cursor, keys } = await client.scan(currentCursor, {
          MATCH: '*',
          COUNT: 1000
        });
        // 전체대상이면 await client.scan(currentCursor)로만 호출해도 가능.
        currentCursor = cursor;
        count += keys.length;
      } while (currentCursor !== 0);
    })
  );
  await cluster.disconnect();
```

<br/>
<br/>
<br/>

        참조:
        https://github.com/redis/node-redis/issues/2657
        https://redis.io/commands/scan/
        https://github.com/redis/node-redis/blob/redis%404.6.13/docs/clustering.md

<br/>

## 개념: client.dbSize

<br/>

dbSize가 현재 Db의 key 수를 반환해서 굳이 위처럼 KEY SCAN 할 필요가 없음..


```

import { createCluster } from 'redis';

const cluster = createCluster({
  rootNodes: [
    {
      url: 'redis://host:port'
    },
    {
      url: 'redis://host:port'
    },
    {
      url: 'redis://host:port'
    }
  ],
  defaults: {
    username: '', // username 빈값으로 안하면 에러가 발생함.
    password: ''
  }
});

await cluster.connect();
  let total = 0;
  await Promise.all(
    cluster.masters.map(async (master) => {
      const client = await cluster.nodeClient(master);
      const size = await client.dbSize();
      total += size;
    })
  );
await cluster.disconnect();
```

<br/>
<br/>
<br/>

        참조:
        https://redis.io/commands/dbsize/

<br/>
