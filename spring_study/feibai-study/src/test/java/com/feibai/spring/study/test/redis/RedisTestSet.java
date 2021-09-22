package com.feibai.spring.study.test.redis;

import com.feibai.spring.study.FeibaiStudyStartup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Spring Zset测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FeibaiStudyStartup.class)
public class RedisTestSet {

  @Autowired
  @Qualifier("redisTemplate1")
  private RedisTemplate<String, String> redisTemplate;

  @Test
  public void test_ismember() {
    String key = "spring.boot.redis.test.set";
    String name = "liyuanlong";
    Boolean res = redisTemplate.opsForSet().isMember(key, name);
  }

  /**
   * 删除member
   */
  @Test
  public void test_remove() {
    redisTemplate.opsForSet().remove("sKey", String.valueOf("member"));
  }

  /**
   * 1、add(Kkey,V... values)
   * 向变量中批量添加值
   */
  public void test_add() {
    redisTemplate.opsForSet().add("setValue", "A", "B", "C", "B", "D", "E", "F");
  }

  /**
   * 2、members(Kkey)
   * 获取变量中的值。
   */
  public void test_members() {
    Set set = redisTemplate.opsForSet().members("setValue");
    System.out.println("通过members(K key)方法获取变量中的元素值:" + set);
  }

  /**
   * 3、size(Kkey)
   * 获取变量中值的个数。
   */
  public void test_size() {
    long setLength = redisTemplate.opsForSet().size("setValue");
    System.out.println("通过size(K key)方法获取变量中元素值的长度:" + setLength);
  }

  /**
   * 4、randomMember(Kkey)
   * 随机获取变量中的元素
   */

  public void test_randomMember() {
    Object randomMember = redisTemplate.opsForSet().randomMember("setValue");
    System.out.println("通过randomMember(K key)方法随机获取变量中的元素:" + randomMember);
  }

  /**
   * 5 、randomMembers(Kkey, long count)
   * 随机获取变量中指定个数的元素
   */

  public void test_randomMembers() {
    List randomMembers = redisTemplate.opsForSet().randomMembers("setValue", 2);
    System.out.println("通过randomMembers(K key, long count)方法随机获取变量中指定个数的元素:" + randomMembers);
  }

  /**
   * 6、isMember(Kkey, Objecto)检查给定的元素是否在变量中。
   */
  public void test_isMember() {

    boolean isMember = redisTemplate.opsForSet().isMember("setValue", "A");
    System.out.println("通过isMember(K key, Object o)方法检查给定的元素是否在变量中:" + isMember);
  }

  /**
   * 7、
   * move(Kkey, Vvalue, KdestKey)
   * 转移变量的元素值到目的变量。
   */
  public void test_move() {
    boolean isMove = redisTemplate.opsForSet().move("setValue", "A", "destSetValue");
    if (isMove) {
      Set set = redisTemplate.opsForSet().members("setValue");
      System.out.print("通过move(K key, V value, K destKey)方法转移变量的元素值到目的变量后的剩余元素:" + set);
      set = redisTemplate.opsForSet().members("destSetValue");
      System.out.println(",目的变量中的元素值:" + set);
    }
  }

  /**
   * pop(Kkey)
   * 弹出变量中的元素。
   */
  public void test_pop() {
    Object popValue = redisTemplate.opsForSet().pop("setValue");
    System.out.print("通过pop(K key)方法弹出变量中的元素:" + popValue);
    Set set = redisTemplate.opsForSet().members("setValue");
    System.out.println(",剩余元素:" + set);
  }

  /**
   * 9、
   * remove(Kkey, Object... values)
   * 批量移除变量中的元素。
   */

  public void test_remove_new() {
    long removeCount = redisTemplate.opsForSet().remove("setValue", "E", "F", "G");
    System.out.print("通过remove(K key, Object... values)方法移除变量中的元素个数:" + removeCount);
    Set set = redisTemplate.opsForSet().members("setValue");

    System.out.println(",剩余元素:" + set);
  }

  /**
   * 10、 scan(Kkey, ScanOptionsoptio ns)
   * 匹配获取键值对，ScanOptions.NONE为获取全部键值对；ScanOptions.scanOptions().match("C"). build()匹配获取键位map1的键值对,不能模糊匹配。
   */
  public void test_scan() {
    //Cursor<Object> cursor = redisTemplate.opsForSet().scan("setValue", ScanOptions.NONE); 

    Cursor cursor = redisTemplate.opsForSet().scan("setValue", ScanOptions.scanOptions().match("C").count(100).build());

    while (cursor.hasNext()) {
      Object object = cursor.next();
      System.out.println("通过scan(K key, ScanOptions options)方法获取匹配的值:" + object);
    }
  }

  /**
   * 11、difference(Kkey, Collection<K> otherKeys)通过集合求差值。
   */
  public void test_difference_collection() {
    List list = new ArrayList();
    list.add("destSetValue");
    Set differenceSet = redisTemplate.opsForSet().difference("setValue", list);
    System.out.println("通过difference(K key, Collection<K> otherKeys)方法获取变量中与给定集合中变量不一样的值:" + differenceSet);
  }

  /**
   * 12、difference(Kkey, KotherKey)
   * 通过给定的key求2个set变量的差值。
   */
  public void test_difference() {
    Set differenceSet = redisTemplate.opsForSet().difference("setValue", "destSetValue");
    System.out.println("通过difference(K key, Collection<K> otherKeys)方法获取变量中与给定变量不一样的值:" + differenceSet);
  }


  /**
   * 13、differenceAndStore(Kkey, KotherKey, KdestKey)
   * 将求出来的差值元素保存。
   */
  public void test_differenceAndStore() {
    redisTemplate.opsForSet().differenceAndStore("setValue", "destSetValue", "storeSetValue");
    Set set = redisTemplate.opsForSet().members("storeSetValue");
    System.out.println("通过differenceAndStore(K key, K otherKey, K destKey)方法将求出来的差值元素保存:" + set);
  }

  /**
   * 14、differenceAndStore(Kkey, Collection<K> otherKeys, KdestKey)
   * 将求出来的差值元素保存。
   */
  public void test_differenceAndStore_middle_list() {
    List list = new ArrayList();
    redisTemplate.opsForSet().differenceAndStore("setValue", list, "storeSetValue");
    Set set = redisTemplate.opsForSet().members("storeSetValue");
    System.out.println("通过differenceAndStore(K key, Collection<K> otherKeys, K destKey)方法将求出来的差值元素保存:" + set);
  }

  /**
   * 15、distinctRandomMembers(Kkey, long count)
   * 获取去重的随机元素。
   */
  public void test_distinctRandomMembers() {
    Set set = redisTemplate.opsForSet().distinctRandomMembers("setValue", 2);
    System.out.println("通过distinctRandomMembers(K key, long count)方法获取去重的随机元素:" + set);
  }

  /**
   * 16、intersect(Kkey, KotherKey)
   * 获取2个变量中的交集。
   */
  public void test_intersect() {
    Set set = redisTemplate.opsForSet().intersect("setValue", "destSetValue");
    System.out.println("通过intersect(K key, K otherKey)方法获取交集元素:" + set);

  }

  /**
   * 17、intersect(Kkey, Collection<K> otherKeys)
   * 获取多个变量之间的交集。
   */
  public void test_intersect_collection() {
    List list = new ArrayList();

    Set set = redisTemplate.opsForSet().intersect("setValue", list);
    System.out.println("通过intersect(K key, Collection<K> otherKeys)方法获取交集元素:" + set);
  }

  /**
   * 18、 intersectAndStore(Kkey, KotherKey, KdestKey)
   * 获取2个变量交集后保存到最后一个参数上。
   */
  public void test_intersectAndStore_new() {
    redisTemplate.opsForSet().intersectAndStore("setValue", "destSetValue", "intersectValue");
    Set set = redisTemplate.opsForSet().members("intersectValue");
    System.out.println("通过intersectAndStore(K key, K otherKey, K destKey)方法将求出来的交集元素保存:" + set);

  }

  /**
   * 19、intersectAndStore(Kkey, Collection<K> otherKeys, KdestKey)
   * 获取多个变量的交集并保存到最后一个参数上。
   */
  public void test_intersectAndStore() {
    List list = new ArrayList();
    redisTemplate.opsForSet().intersectAndStore("setValue", list, "intersectListValue");
    Set set = redisTemplate.opsForSet().members("intersectListValue");
    System.out.println("通过intersectAndStore(K key, Collection<K> otherKeys, K destKey)方法将求出来的交集元素保存:" + set);
  }

  /**
   * 20、union(Kkey, KotherKey)
   * 获取2个变量的合集。
   */
  public void test_union_otherKey() {
    Set set = redisTemplate.opsForSet().union("setValue", "destSetValue");
    System.out.println("通过union(K key, K otherKey)方法获取2个变量的合集元素:" + set);
  }

  /**
   * 21、union(Kkey, Collection<K> otherKeys)
   * 获取多个变量的合集。
   */
  public void test_union_collection() {
    List list = new ArrayList();
    Set set = redisTemplate.opsForSet().union("setValue", list);
    System.out.println("通过union(K key, Collection<K> otherKeys)方法获取多个变量的合集元素:" + set);
  }

  /**
   * 22、unionAndStore(Kkey, KotherKey, KdestKey)
   * 获取2个变量合集后保存到最后一个参数上。
   */
  public void test_unionAndStore_new() {
    redisTemplate.opsForSet().unionAndStore("setValue", "destSetValue", "unionValue");
    Set set = redisTemplate.opsForSet().members("unionValue");
    System.out.println("通过unionAndStore(K key, K otherKey, K destKey)方法将求出来的交集元素保存:" + set);
  }

  /**
   * 23、unionAndStore(Kkey, Collection<K> otherKeys, KdestKey)
   * 获取多个变量的合集并保存到最后一个参数上。
   */
  public void test_unionAndStore() {
    List list = new ArrayList();
    redisTemplate.opsForSet().unionAndStore("setValue", list, "unionListValue");
    Set set = redisTemplate.opsForSet().members("unionListValue");
    System.out.println("通过unionAndStore(K key, Collection<K> otherKeys, K destKey)方法将求出来的交集元素保存:" + set);
  }

}
