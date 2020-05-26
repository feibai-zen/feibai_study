package com.feibai.study.demos.demos.collections;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentMap;

public class GoodPracticeCollection {

  public static void main(String[] args) {

    /**
     * Map
     */
    HashMap hashMap = Maps.newHashMap();
    ConcurrentMap concurrentHashMap = Maps.newConcurrentMap();
    TreeMap treeMap = Maps.newTreeMap();

    /**
     * List
     */
    List list = Lists.newArrayList();
    List copyOnWriteArrayList = Lists.newCopyOnWriteArrayList();

    /**
     * Set
     */
    Sets.newCopyOnWriteArraySet();
    Sets.newConcurrentHashSet();
    Sets.newHashSet();

    List<Integer> testList = Collections.EMPTY_LIST;//返回的List不能再调用add()方法
    Collections.emptyList();
  }

}
