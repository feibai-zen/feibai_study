package com.feibai.study.demos.hutool;

import cn.hutool.http.HttpRequest;
import com.google.common.collect.Maps;

import com.alibaba.fastjson.JSON;
import java.util.Map;

public class HttpClientTest {

  public void test_01() {
    Map<String, Object> params = Maps.newHashMap();
    params.put("Id", 111);
    HttpRequest.get("url")
        .header("Content-Type", "application/json")
        .header("User", "userName")
        .header("Authorization","authority")
        .body(JSON.toJSONString(params)).timeout(30000).execute();
  }

}