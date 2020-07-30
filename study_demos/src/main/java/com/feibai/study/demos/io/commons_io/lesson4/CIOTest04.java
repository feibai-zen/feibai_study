package com.feibai.study.demos.io.commons_io.lesson4;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

/**
 * 写出内容
 *
 *
 */
public class CIOTest04 {

	public static void main(String[] args) throws IOException {
		//写出文件
		FileUtils.write(new File("happy.sxt"), "学习是一件伟大的事业\r\n","UTF-8");
		FileUtils.writeStringToFile(new File("happy.sxt"), "学习是一件辛苦的事业\r\n","UTF-8",true);
		FileUtils.writeByteArrayToFile(new File("happy.sxt"), "学习是一件幸福的事业\r\n".getBytes("UTF-8"),true);
		
		//写出列表
		List<String> datas =new ArrayList<String>();
		datas.add("马云");
		datas.add("马化腾");
		datas.add("弼马温");
		
		FileUtils.writeLines(new File("happy.sxt"), datas,"。。。。。",true);
	}

}
