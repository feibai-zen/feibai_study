package com.feibai.study.demos.demos.jvm.gc;

import java.io.File;
import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * -XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=80m
 * <p>
 * 总结：
 * JDK8中类加载（方法区的功能）已经不在永久代PerGem中了，而是Metaspace中。可以配合JVisualVM来看，更直观一些。
 */
public class Test02_MetaspaceOOM {

  public static void main(String[] args) {
    try {
      //准备url
      URL url = new File("/Users/xmly/Leeyuanlong/feibai_study/study_demos/src/main/java/com/feibai/study/demos/demos/jvm/gc").toURI().toURL();
      URL[] urls = {url};
      //获取有关类型加载的JMX接口
      ClassLoadingMXBean loadingBean = ManagementFactory.getClassLoadingMXBean();
      //用于缓存类加载器
      List<ClassLoader> classLoaders = new ArrayList<ClassLoader>();
      while (true) {
        //加载类型并缓存类加载器实例
        ClassLoader classLoader = new URLClassLoader(urls);
        classLoaders.add(classLoader);
        classLoader.loadClass("TestJVM");
        //显示数量信息（共加载过的类型数目，当前还有效的类型数目，已经被卸载的类型数目）
        System.out.println("total: " + loadingBean.getTotalLoadedClassCount());
        System.out.println("active: " + loadingBean.getLoadedClassCount());
        System.out.println("unloaded: " + loadingBean.getUnloadedClassCount());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
