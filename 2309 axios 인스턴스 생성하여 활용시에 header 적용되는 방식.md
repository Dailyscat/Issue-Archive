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

# Issue: axios 인스턴스 생성하여 활용시에 header 적용되는 방식

## 상황:
trpc 미들웨어에서 browser에서 온 요청을 받아서 header 정보를 다시 Set 해주는 로직이 있었는데 이때 browser의 모든 헤더가 받아져서 이슈가 있었음. 이를 확인하다 어떻게 header에 내용이 적용되는지 확인

<br/>

## 알게된 부분 정리:

- axios에서 header 적용 포인트 정리

<br/>

## 개념: axios에서 header 적용 포인트 정리

<br/>
  1. content-length 계산
    헤더에 content-length가 없으면 아래 로직을 통해서 file의 타입에 따라 set된다.
    ```
    // support for spec compliant FormData objects
    if (utils.isSpecCompliantForm(data)) {
      const userBoundary = headers.getContentType(/boundary=([-_\w\d]{10,70})/i);

      data = formDataToStream(data, (formHeaders) => {
        headers.set(formHeaders);
      }, {
        tag: `axios-${VERSION}-boundary`,
        boundary: userBoundary && userBoundary[1] || undefined
      });
      // support for https://www.npmjs.com/package/form-data api
    } else if (utils.isFormData(data) && utils.isFunction(data.getHeaders)) {
      headers.set(data.getHeaders());

      if (!headers.hasContentLength()) {
        try {
          const knownLength = await util.promisify(data.getLength).call(data);
          Number.isFinite(knownLength) && knownLength >= 0 && headers.setContentLength(knownLength);
          /*eslint no-empty:0*/
        } catch (e) {
        }
      }
    } else if (utils.isBlob(data)) {
      data.size && headers.setContentType(data.type || 'application/octet-stream');
      headers.setContentLength(data.size || 0);
      data = stream.Readable.from(readBlob(data));
    } else if (data && !utils.isStream(data)) {
      if (Buffer.isBuffer(data)) {
        // Nothing to do...
      } else if (utils.isArrayBuffer(data)) {
        data = Buffer.from(new Uint8Array(data));
      } else if (utils.isString(data)) {
        data = Buffer.from(data, 'utf-8');
      } else {
        return reject(new AxiosError(
          'Data after transformation must be a string, an ArrayBuffer, a Buffer, or a Stream',
          AxiosError.ERR_BAD_REQUEST,
          config
        ));
      }

      // Add Content-Length header if data exists
      headers.setContentLength(data.length, false);
    ```
    https://github.com/axios/axios/blob/4c89f25196525e90a6e75eda9cb31ae0a2e18acd/lib/adapters/http.js#L284
    
    2. setHeader 함수를 통해서 이미 config에 header 설정이 있는 부분을 피한다.
    ```
        function setHeader(_value, _header, _rewrite) {
      const lHeader = normalizeHeader(_header);

      if (!lHeader) {
        throw new Error('header name must be a non-empty string');
      }

      const key = utils.findKey(self, lHeader);

      if(!key || self[key] === undefined || _rewrite === true || (_rewrite === undefined && self[key] !== false)) {
        self[key || _header] = normalizeValue(_value);
      }
    }

    const setHeaders = (headers, _rewrite) =>
      utils.forEach(headers, (_value, _header) => setHeader(_value, _header, _rewrite));

    if (utils.isPlainObject(header) || header instanceof this.constructor) {
      setHeaders(header, valueOrRewrite)
    } else if(utils.isString(header) && (header = header.trim()) && !isValidHeaderName(header)) {
      setHeaders(parseHeaders(header), valueOrRewrite);
    } else {
      header != null && setHeader(valueOrRewrite, header, rewrite);
    }

    return this;
  }
    ```
    https://github.com/axios/axios/blob/4c89f25196525e90a6e75eda9cb31ae0a2e18acd/lib/core/AxiosHeaders.js#L102
    https://github.com/axios/axios/blob/4c89f25196525e90a6e75eda9cb31ae0a2e18acd/lib/helpers/parseHeaders.js#L8

    https://stackoverflow.com/questions/59954053/axios-set-content-length-manually-nodejs
<br/>
<br/>
<br/>

        참조:

<br/>
