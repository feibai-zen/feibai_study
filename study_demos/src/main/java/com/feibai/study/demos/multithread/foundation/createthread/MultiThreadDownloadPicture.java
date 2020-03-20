package com.feibai.study.demos.multithread.foundation.createthread;

public class MultiThreadDownloadPicture implements Runnable {

	private String url;
	private String name;

	public MultiThreadDownloadPicture(String url, String name) {
		this.url = url;
		this.name = name;

	}

	@Override
	public void run() {
		WebDownloader webDownloader = new WebDownloader();
		webDownloader.download(this.url, this.name);
	}

	public static void main(String[] args) {

		MultiThreadDownloadPicture td1 = new MultiThreadDownloadPicture("http://p1.pstatp.com/large/403c00037462ae2eee13", "spl.jpg");
		MultiThreadDownloadPicture td2 = new MultiThreadDownloadPicture("http://p1.pstatp.com/large/403c00037462ae2eee13", "spl.jpg");
		MultiThreadDownloadPicture td3 = new MultiThreadDownloadPicture(
				"http://5b0988e595225.cdn.sohucs.com/images/20170830/d8b57e0dce0d4fa29bd5ef014be663d5.jpeg",
				"success.jpg");

		//记住这里不是调用run()方法。如果调用run方法则是普通的方法调用，将会顺序执行
		new Thread(td1).start();
		new Thread(td2).start();
		new Thread(td3).start();

	}

}
