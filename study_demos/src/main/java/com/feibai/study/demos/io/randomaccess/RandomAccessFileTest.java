package com.feibai.study.demos.io.randomaccess;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileTest {

	public static void main(String[] args) throws IOException {

	}

	// 指定起始位置，读取剩余所有内容
	public static void test01() {

		try (RandomAccessFile raf = new RandomAccessFile(new File("testFileOutput"), "r");) {

			raf.seek(2);

			byte[] flush = new byte[1024];
			int len = -1;
			while ((len = raf.read(flush)) != -1) {
				System.out.println(new String(flush, 0, len));
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
