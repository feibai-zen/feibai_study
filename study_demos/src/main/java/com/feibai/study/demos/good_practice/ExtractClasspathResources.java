package com.feibai.study.demos.good_practice;


import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
 *
 * SpringBoot项目中关于资源文件的操作
 *
 *
 */
public class ExtractClasspathResources {

    /*
     *
     * 将springboot项目中的资源文件解压到jar包所在的文件夹下。目的：项目资源文件过多，读取jar工程中的文件太过于麻烦。
     * */
    public static void extractClasspathResources() throws IOException {

        ClassLoader classLoader = MethodHandles.lookup().getClass().getClassLoader();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(classLoader);

        Resource[] allResources = resolver.getResources("classpath:config/**/*");
        for (Resource rs : allResources) {
            ClassPathResource r = (ClassPathResource) rs;
            String path = r.getPath();
            if (path.endsWith("/")) {
                Files.createDirectories(Paths.get(path));
            } else {
                IOUtils.copy(r.getInputStream(), new FileOutputStream(path));
            }
        }

    }

    /*
     *读取jar包中的资源文件
     *
     * */


}
