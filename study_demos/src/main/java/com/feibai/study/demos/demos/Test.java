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
    List<Long> uids = Arrays.asList(10037L, 10040L, 45971L, 331179L, 12410L, 57794L, 329246L, 329247L, 329248L,
            203503L, 203502L, 203504L, 203459L, 203460L, 203461L, 81946L, 20930L, 80469L, 10039L, 202838L,
            80755L, 202847L, 202863L, 202861L, 202862L, 202855L, 202859L, 205428L, 201229L, 80428L, 81775L,
            321316L, 80798L, 329244L, 82055L, 315036L, 329242L, 202864L, 202866L, 202821L, 202823L, 202825L,
            205362L, 314658L, 202818L, 202869L, 205385L, 320138L, 321314L, 320135L, 320464L, 81855L, 333576L,
            317808L, 321071L, 314606L, 317806L, 310075L, 317807L, 318772L, 322736L, 322855L, 309399L, 329249L,
            80447L, 80451L, 80455L, 80466L, 80477L, 80483L, 57698L, 315716L, 316292L, 315721L, 201818L,
            202836L, 317165L, 202839L, 202842L, 315285L, 202843L, 315296L, 202844L, 319244L, 315350L, 81953L,
            315471L, 315472L, 315806L, 319292L, 319293L, 315913L, 315930L, 320132L, 320133L, 320134L,
            323030L, 305674L, 323038L, 305676L, 323027L, 331564L, 330490L, 331806L, 323575L, 12339L,
            200548L, 202848L, 202849L, 82027L, 333577L, 81954L, 82152L, 333572L, 333573L, 333580L, 333579L,
            82153L, 82154L, 82155L, 82159L, 82161L, 203501L, 332877L, 6L, 7L, 10L, 12L, 13L, 14L, 17L, 20L, 1L, 18L,
            10L, 11L, 12L, 13L, 14L, 15L, 16L, 17L, 18L, 19L, 20L, 21L, 22L, 23L, 24L, 25L, 26L, 27L, 28L, 29L,
            30L, 31L, 32L, 33L, 34L, 35L, 36L, 37L, 38L, 39L, 40L, 41L, 42L, 43L, 44L, 45L, 46L, 47L, 48L, 49L, 50L,
            51L, 52L, 53L, 54L, 55L, 56L, 57L, 329243L, 329245L
    );

    String url = "";
    int i = 1;
    for (Long uid : uids) {
      i++;
      String data = "{\"baikalId\":288,\"roam\":{\"ANCHOR_UID\":" + uid + "L,\"BUYER_ID\":" + uid + "L,\"INNER_VALUE\":" + i + "L,\"QUANTITY\":1L,\"GIFT_ID" +
              "\":573L}}";
      String result = post(data, url);
      System.out.println(result);
    }
  }

  public static String post(String json, String url) {
    String result = "";
    HttpPost post = new HttpPost(url);
    try {
      CloseableHttpClient httpClient = HttpClients.createDefault();

      post.setHeader("Content-Type", "application/json;charset=utf-8");
      post.addHeader("Authorization", "Basic YWRtaW46");
      StringEntity postingString = new StringEntity(json, "utf-8");
      post.setEntity(postingString);
      HttpResponse response = httpClient.execute(post);

      InputStream in = response.getEntity().getContent();
      BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));
      StringBuilder strber = new StringBuilder();
      String line = null;
      while ((line = br.readLine()) != null) {
        strber.append(line + '\n');
      }
      br.close();
      in.close();
      result = strber.toString();
      if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
        result = "服务器异常";
      }
    } catch (Exception e) {
      System.out.println("请求异常");
      throw new RuntimeException(e);
    } finally {
      post.abort();
    }
    return result;
  }
}