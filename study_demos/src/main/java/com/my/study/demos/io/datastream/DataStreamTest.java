package com.my.study.demos.io.datastream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * 数据流 1.写出后读取 2.读取的顺序与写出保持一致
 * 
 * @author leeyuanlong
 *
 *         DataOutputStream DataInputStream
 */
public class DataStreamTest {

	public static void main(String[] args) throws IOException {
		// 写出
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(baos));
		// 操作数据类型+数据
		dos.writeUTF("编程辛酸泪");
		dos.writeInt(18);
		dos.writeBoolean(false);
		dos.writeChar('a');
		dos.flush();

		// 读取
		byte[] datas = baos.toByteArray();
		DataInputStream dis = new DataInputStream(new BufferedInputStream(new ByteArrayInputStream(datas)));

		String msg = dis.readUTF();
		int age = dis.readInt();
		boolean flag = dis.readBoolean();
		char ch = dis.readChar();
		System.out.println(msg);
	}

}
