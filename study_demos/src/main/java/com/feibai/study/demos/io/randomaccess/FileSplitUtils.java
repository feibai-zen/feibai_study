package com.feibai.study.demos.io.randomaccess;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class FileSplitUtils {

	// 源头
	private File src;
	// 目的地(文件夹)
	private String destDir;
	// 所有分割后的文件存储路径
	private List<String> destPaths;
	// 每块大小
	private int blockSize;
	// 块数: 多少块
	private int size;

	public FileSplitUtils(String srcPath, String destDir) {
		this(srcPath, destDir, 1024);
	}

	public FileSplitUtils(String srcPath, String destDir, int blockSize) {
		this.src = new File(srcPath);
		this.destDir = destDir;
		this.blockSize = blockSize;
		this.destPaths = new ArrayList<String>();

		// 初始化
		init();
	}

	// 初始化
	private void init() {
		// 总长度
		long len = this.src.length();
		// 块数: 多少块
		this.size = (int) Math.ceil(len * 1.0 / blockSize);
		// 路径
		for (int i = 0; i < size; i++) {
			this.destPaths.add(this.destDir + "/" + i + "-" + this.src.getName());
		}
	}

	/**
	 * 分割 1、计算每一块的起始位置及大小 2、分割
	 * 
	 * @throws IOException
	 */
	public void split() throws IOException {
		// 总长度
		long len = src.length();
		// 起始位置和实际大小
		int beginPos = 0;
		int actualSize = (int) (blockSize > len ? len : blockSize);
		for (int i = 0; i < size; i++) {
			beginPos = i * blockSize;
			if (i == size - 1) { // 最后一块
				actualSize = (int) len;
			} else {
				actualSize = blockSize;
				len -= actualSize; // 剩余量
			}
			splitDetail(i, beginPos, actualSize);
		}
	}

	/**
	 * 指定第i块的起始位置 和实际长度
	 * 
	 * @param i
	 * @param beginPos
	 * @param actualSize
	 * @throws IOException
	 */
	private void splitDetail(int i, int beginPos, int actualSize) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(this.src, "r");
		RandomAccessFile raf2 = new RandomAccessFile(this.destPaths.get(i), "rw");
		// 随机读取
		raf.seek(beginPos);
		// 读取
		// 3、操作 (分段读取)
		byte[] flush = new byte[1024]; // 缓冲容器
		int len = -1; // 接收长度
		while ((len = raf.read(flush)) != -1) {
			if (actualSize > len) { // 获取本次读取的所有内容
				raf2.write(flush, 0, len);
				actualSize -= len;
			} else {
				raf2.write(flush, 0, actualSize);
				break;
			}
		}
		raf2.close();
		raf.close();
	}

	/**
	 * 文件的合并
	 * 
	 * @throws IOException
	 */
	public void merge(String destPath) throws IOException {
		// 输出流
		OutputStream os = new BufferedOutputStream(new FileOutputStream(destPath, true));
		Vector<InputStream> vi = new Vector<InputStream>();
		SequenceInputStream sis = null;
		// 输入流
		for (int i = 0; i < destPaths.size(); i++) {
			vi.add(new BufferedInputStream(new FileInputStream(destPaths.get(i))));
		}
		sis = new SequenceInputStream(vi.elements());
		// 拷贝
		// 3、操作 (分段读取)
		byte[] flush = new byte[1024]; // 缓冲容器
		int len = -1; // 接收长度
		while ((len = sis.read(flush)) != -1) {
			os.write(flush, 0, len); // 分段写出
		}
		os.flush();
		sis.close();
		os.close();
	}

	public static void main(String[] args) throws IOException {
		FileSplitUtils sf = new FileSplitUtils("src/com/sxt/io/SplitFile.java", "dest");
		sf.split();
		sf.merge("aaa.java");
	}
}
