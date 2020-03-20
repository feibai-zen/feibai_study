package com.feibai.study.demos.multithread.foundation.webdownloader;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

public class WebDownloader {

	public void download(String url, String name) {
		try {
			System.out.println(name);
			FileUtils.copyURLToFile(new URL(url), new File(name));
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.out.println("不合法的url.");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("图片下载失败.");
		}
	}

}
