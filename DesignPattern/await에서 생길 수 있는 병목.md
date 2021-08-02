# await에서 생길 수 있는 병목

[await의 함정, 숨은 병목을 찾자](https://jaeheon.kr/161?category=823252)를 읽고 정리

```
const book = await read(param); //100ms
const shop = await readSomething(param) // 500ms
const {key} = await readThat(param); //300ms
const result = await checkVlidKey(key); // 900ms
```

위 코드는 2,700ms를 기다려야 한다.

```
const [book, show, { key }] = await Promise.all([read(param), readSomething(param),readThat(param)]);
const result = await checkValidKey(key);
```

위 코드는 800ms가 감소된 1,900ms만 기다리면 된다.

```
const [book, show, { key }] = await Promise.all([read(param), readSomething(param),checkValidKey( await readThat(param).key)]);
```

위 코드는 700ms 가 감소된 1200ms면 작업이 끝난다.

async, await은 코드를 동기식으로 작성하기 위해 사용한다. 위의 작업 시간이 감소 되는 과정을 위해서는 이 같은 동기식 코드 작성에 연관성을 생각하여 작성하도록 해야한다.

```
const run1 = read(param);
const run2 = readSomething(param)
const run3 = readThat(param);

const book = await run1;
const shop = await run2;
const { key } = await run3;

const result await  checkValidKey(key);
```

위의 코드는 3번째 라인까지 비동기로 호출을 하고 5번째에서 await을 통해 미리 비동기로 요청한 호출의 리턴 값을 받은 후 다시 후속작업(비동기 요청)을 필요로 하는 코드이다.

후속작업으로 인해 await이 비효율적으로 한번 더 기다리게 되는 코드로 작성되었다.

```
const run1 = read(param);
const run2 = readSomething(param)
const run3 = readThat(param);

const { key } = await run3;
const run4 = checkValidKey(key);

const book = await run1;
const shop = await run2;
const result = await run4;

const result await checkValidKey(key);
```

위 코드는 이전의 요청과 await을 동시에 작업하는 부분으로 인해 대기 시간을 더 잡아먹는 부분을
후속작업이 필요한 요청에 대해 미리 비동기 요청 후 작업을 끝낸다. 그리고 리턴된 프로미스를 최 상단에서 요청한 비동기 프로미스들과 함께 await으로 동기작성한다. book의 결과를 리턴하는 시간동안 shop과 result또한 비동기로 요청을 받아오고 있는 상태에 있으므로 쓸데없는 대기 시간을 줄일 수 있다.

즉 후속작업이 필요한 부분에 대해서는 비동기로 요청하여 await을 사용하는 동기적 코드 작성에서의 가독성과 대기시간의 감소를 얻는 기법이다.
