package com.feibai.study.demos.demos;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.IntStream;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;


public class Test {

  public static void main(String[] args) {
    new Thread(()->{
      while(true){
        try {
          Thread.sleep(1000);
          System.out.println("son thread.");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }

    }).start();
    System.out.println("main");

  }
}