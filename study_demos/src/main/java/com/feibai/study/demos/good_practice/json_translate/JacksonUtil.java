package com.feibai.study.demos.good_practice.json_translate;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feibai.study.demos.beans.AccountBean;

import java.io.IOException;
import java.util.*;

public class JacksonUtil {

  public static void main(String[] args) {
//    new JacksonUtil().test01();
    new JacksonUtil().test06();
  }

  /**
   * Java对象转换成JSON
   */
  public void test01() {
    AccountBean bean = new AccountBean();
    bean.setAddress("china-Guangzhou");
    bean.setEmail("hoojo_@126.com");
    bean.setId(1);
    bean.setName("hoojo");
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      JsonGenerator jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(System.out, JsonEncoding.UTF8);
      System.out.println("jsonGenerator");
      // writeObject可以转换java对象，eg:JavaBean/Map/List/Array等
      jsonGenerator.writeObject(bean);
      System.out.println();

      // writeValue具有和writeObject相同的功能
      System.out.println("ObjectMapper");
      objectMapper.writeValue(System.out, bean);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * 将Map集合转换成Json字符串
   */
  public void test02() {
    AccountBean bean1 = new AccountBean();
    bean1.setAddress("china-Suzhou");
    bean1.setEmail("zhangsan@126.com");
    bean1.setId(1);
    bean1.setName("zhangsan");

    AccountBean bean2 = new AccountBean();
    bean2.setAddress("china-Shanghai");
    bean2.setEmail("lisi@126.com");
    bean2.setId(2);
    bean2.setName("lisi");

    Map<String, Object> map = new HashMap<String, Object>();
    map.put("name1", bean1.getName());
    map.put("account1", bean1);
    map.put("name2", bean2.getName());
    map.put("account2", bean2);

    try {
      ObjectMapper objectMapper = new ObjectMapper();
      JsonGenerator jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(System.out, JsonEncoding.UTF8);
      System.out.println("jsonGenerator");
      jsonGenerator.writeObject(map);
      System.out.println();

      System.out.println("ObjectMapper");
      objectMapper.writeValue(System.out, map);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * 将List集合转换成json
   */
  public void test03() {
    AccountBean bean1 = new AccountBean();
    bean1.setAddress("china-Suzhou");
    bean1.setEmail("zhangsan@126.com");
    bean1.setId(1);
    bean1.setName("zhangsan");

    AccountBean bean2 = new AccountBean();
    bean2.setAddress("china-Shanghai");
    bean2.setEmail("lisi@126.com");
    bean2.setId(2);
    bean2.setName("lisi");

    List<AccountBean> list = new ArrayList<AccountBean>();
    list.add(bean1);
    list.add(bean2);

    try {
      ObjectMapper objectMapper = new ObjectMapper();
      JsonGenerator jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(System.out, JsonEncoding.UTF8);
      System.out.println("jsonGenerator");
      jsonGenerator.writeObject(list);
      System.out.println();

      System.out.println("ObjectMapper");
      objectMapper.writeValue(System.out, list);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  public static void test04() {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      JsonGenerator jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(System.out, JsonEncoding.UTF8);
      String[] arr = {"a", "b", "c"};
      System.out.println("jsonGenerator");
      String str = "hello world jackson!";
      // byte
      jsonGenerator.writeBinary(str.getBytes());
      // boolean
      jsonGenerator.writeBoolean(true);
      // null
      jsonGenerator.writeNull();
      // float
      jsonGenerator.writeNumber(2.2f);
      // char
      jsonGenerator.writeRaw("c");
      // String
      jsonGenerator.writeRaw(str, 5, 10);
      // String
      jsonGenerator.writeRawValue(str, 5, 5);
      // String
      jsonGenerator.writeString(str);
//      jsonGenerator.writeTree(JsonNodeFactory.instance.POJONode(str));
      System.out.println();
      // Object
      jsonGenerator.writeStartObject();// {
      jsonGenerator.writeObjectFieldStart("user");// user:{
      jsonGenerator.writeStringField("name", "jackson");// name:jackson
      jsonGenerator.writeBooleanField("sex", true);// sex:true
      jsonGenerator.writeNumberField("age", 22);// age:22
      jsonGenerator.writeEndObject();// }
      jsonGenerator.writeArrayFieldStart("infos");// infos:[
      jsonGenerator.writeNumber(22);// 22
      jsonGenerator.writeString("this is array");// this is array
      jsonGenerator.writeEndArray();// ]
      jsonGenerator.writeEndObject();// }
      AccountBean bean = new AccountBean();
      bean.setAddress("address");
      bean.setEmail("email");
      bean.setId(1);
      bean.setName("haha");
      // complex Object
      jsonGenerator.writeStartObject();// {
      jsonGenerator.writeObjectField("user", bean);// user:{bean}
      jsonGenerator.writeObjectField("infos", arr);// infos:[array]
      jsonGenerator.writeEndObject();// }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * json转换为Java Bean
   */
  public void test05() {
    String json = "{\"address\":\"address\",\"name\":\"haha\",\"id\":1,\"email\":\"email\"}";
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      AccountBean acc = objectMapper.readValue(json, AccountBean.class);
      System.out.println(acc.getName());
      System.out.println(acc);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * json字符串转换成List<Map>集合
   */
  public void test06() {
    String json = "[{\"address\": \"address2\",\"name\":\"haha2\",\"id\":2,\"email\":\"email2\"},"
            + "{\"address\":\"address\",\"name\":\"haha\",\"id\":1,\"email\":\"email\"}]";
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      List<Map<String, Object>> list = objectMapper.readValue(json, List.class);
      System.out.println(list.size());
      for (int i = 0; i < list.size(); i++) {
        Map<String, Object> map = list.get(i);
        Set<String> set = map.keySet();
        for (Iterator<String> it = set.iterator(); it.hasNext(); ) {
          String key = it.next();
          System.out.println(key + ":" + map.get(key));
        }
        System.out.println();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Json字符串转换成Array数组
   */
  public void test07() {
    String json = "[{\"address\": \"address2\",\"name\":\"haha2\",\"id\":2,\"email\":\"email2\"},"
            + "{\"address\":\"address\",\"name\":\"haha\",\"id\":1,\"email\":\"email\"}]";
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      AccountBean[] arr = objectMapper.readValue(json, AccountBean[].class);
      System.out.println(arr.length);
      for (int i = 0; i < arr.length; i++) {
        System.out.println(arr[i]);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  @SuppressWarnings("unchecked")
  public void test08() {
    String json = "{\"success\":true,\"A\":{\"address\": \"address2\",\"name\":\"haha2\",\"id\":2,\"email\":\"email2\"},"
            + "\"B\":{\"address\":\"address\",\"name\":\"haha\",\"id\":1,\"email\":\"email\"}}";
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      Map<String, Map<String, Object>> maps = objectMapper.readValue(json, Map.class);
      System.out.println(maps.size());
      Set<String> key = maps.keySet();
      Iterator<String> iter = key.iterator();
      while (iter.hasNext()) {
        String field = iter.next();
        System.out.println(field + ":" + maps.get(field));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
