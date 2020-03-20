package com.feibai.study.demos.multithread.foundation.webdownloader;

public class TDownloader extends Thread {

	private String url;
	private String name;

	public TDownloader(String url, String name) {
		this.url = url;
		this.name = name;

	}

	@Override
	public void run() {

		WebDownloader webDownloader = new WebDownloader();
		webDownloader.download(this.url, this.name);
	}

	public static void main(String[] args) {
		TDownloader td1 = new TDownloader("http://p1.pstatp.com/large/403c00037462ae2eee13", "spl.jpg");
		TDownloader td2 = new TDownloader("http://p1.pstatp.com/large/403c00037462ae2eee13", "spl.jpg");
		TDownloader td3 = new TDownloader(
				"http://5b0988e595225.cdn.sohucs.com/images/20170830/d8b57e0dce0d4fa29bd5ef014be663d5.jpeg",
				"success.jpg");

		td1.start();
		td2.start();
		td3.start();

	}

}
