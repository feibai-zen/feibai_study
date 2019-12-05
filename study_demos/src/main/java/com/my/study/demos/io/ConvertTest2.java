package com.my.study.demos.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;

/**
 * 
 * 转换流：InputStreamReader OutputStreamWriter 1.以字符流的形式操作字节流（纯文本的） 2.指定字符集
 * 
 * @author leeyuanlong
 *
 */
public class ConvertTest2 {

	public static void main(String[] args) {
		// 操作网络流 下载百度源代码

		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(new URL("http://www.baidu.com").openStream(), "UTF-8"));
				BufferedWriter writer = new BufferedWriter(
						new OutputStreamWriter(new FileOutputStream("baidu.html"), "UTF-8"));) {
			String msg;
			while ((msg = reader.readLine()) != null) {
				writer.write(msg);
				writer.newLine();
			}
			writer.flush();

		} catch (Exception e) {
			System.out.println("操作异常");
		}
	}

}
