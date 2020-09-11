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
/**
 * 1.MetaspaceSize
 * 初始化的Metaspace大小，控制元空间发生GC的阈值。GC后，动态增加或降低MetaspaceSize。在默认情况下，这个值大小根据不同的平台在12M到20M浮动。使用Java -XX:+PrintFlagsInitial命令查看本机的初始化参数
 * <p>
 * 2.MaxMetaspaceSize
 * 限制Metaspace增长的上限，防止因为某些情况导致Metaspace无限的使用本地内存，影响到其他程序。在本机上该参数的默认值为4294967295B（大约4096MB）。
 * <p>
 * 3.MinMetaspaceFreeRatio
 * 当进行过Metaspace GC之后，会计算当前Metaspace的空闲空间比，如果空闲比小于这个参数（即实际非空闲占比过大，内存不够用），那么虚拟机将增长Metaspace的大小。默认值为40，也就是40%。设置该参数可以控制Metaspace的增长
 * 的速度，太小的值会导致Metaspace增长的缓慢，Metaspace的使用逐渐趋于饱和，可能会影响之后类的加载。而太大的值会导致Metaspace增长的过快，浪费内存。
 * <p>
 * 4.MaxMetasaceFreeRatio
 * 当进行过Metaspace GC之后， 会计算当前Metaspace的空闲空间比，如果空闲比大于这个参数，那么虚拟机会释放Metaspace的部分空间。默认值为70，也就是70%。
 * <p>
 * 5.MaxMetaspaceExpansion
 * Metaspace增长时的最大幅度。在本机上该参数的默认值为5452592B（大约为5MB）。
 * <p>
 * 6.MinMetaspaceExpansion
 * Metaspace增长时的最小幅度。在本机上该参数的默认值为340784B（大约330KB为）。
 */
