package com.feibai.study.es.unit;

import com.feibai.study.es.unit.model.User;
import com.feibai.study.es.utils.EsUtils;
import lombok.Data;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class EsUtilsTest {
  private static RestHighLevelClient client = EsUtils.getEsClient();

  @Test
  public void testCreateIndex() throws IOException {
    EsUtils.createIndex(client, "java_api");
  }


  @Test
  public void test_addDoc() throws IOException {
    User user = new User();
    user.setName("zhangsan");
    user.setAge(30);
    user.setSex("男");
    EsUtils.addDoc(client, user);
  }


  @Test
  public void test_updateDoc() {

  }


}
