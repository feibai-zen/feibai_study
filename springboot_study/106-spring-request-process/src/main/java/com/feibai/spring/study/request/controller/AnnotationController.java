package com.feibai.spring.study.request.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AnnotationController {

  @GetMapping("/register/{id}/{username}")
  public Map<String, Object> registerUser(@PathVariable("id") Integer id,
                                          @PathVariable("username") String name,
                                          @PathVariable Map<String, String> pathVars,
                                          @RequestHeader("User-Agent") String userAgent,
                                          @RequestHeader Map<String, String> header,
                                          @RequestParam("age") Integer age,
                                          @RequestParam("interest") List<String> interest,
                                          @RequestParam Map<String, String> params,
                                          @CookieValue("_ga") String _ga,
                                          @CookieValue("_ga") Cookie cookie) {
    Map<String, Object> retMap = new HashMap<>();
    retMap.put("id", id);
    retMap.put("userName", name);
    retMap.put("pathVars", pathVars);
    retMap.put("userAgent", userAgent);
    retMap.put("header", header);
    retMap.put("age", age);
    retMap.put("interest", interest);
    retMap.put("params", params);
    retMap.put("_ga", _ga);
    retMap.put("cookie", cookie);

    return retMap;
  }

  @PostMapping("/update")
  public Map<String, Object> updateInfo(@RequestBody String newInfo) {
    Map<String, Object> map = new HashMap<>();
    map.put("info", newInfo);
    return map;
  }

  @GetMapping("/register/{id}")
  public Map<String, Object> carsSell(@MatrixVariable("name") Integer name,
                                      @MatrixVariable("interest") List<String> interest,
                                      @PathVariable("id") String id) {
    Map<String, Object> map = new HashMap<>();

    map.put("name", name);
    map.put("interest", interest);
    map.put("id", id);
    return map;
  }

  // /boss/1;age=20/2;age=10

  @GetMapping("/boss/{bossId}/{empId}")
  public Map<String, Object> boss(@MatrixVariable(value = "age", pathVar = "bossId") Integer bossAge,
                                  @MatrixVariable(value = "age", pathVar = "empId") Integer empAge) {
    Map<String, Object> map = new HashMap<>();

    map.put("bossAge", bossAge);
    map.put("empAge", empAge);
    return map;
  }

}