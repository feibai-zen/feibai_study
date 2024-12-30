package com.coding.redis;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import redis.clients.jedis.Jedis;

//访问业务程序
public class AccessApplication implements Runnable{

	private int clientID; //代表客户端的ID
	public AccessApplication(int id) {
		this.clientID  = id;
	}
	
	@Override
	public void run() {
		// 调用业务方法
		try {
			System.out.println(clientID + "号客户端是否允许执行操作：" + access());
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public boolean access() throws Exception{
		//创建Redis客户端
		Jedis client = new Jedis("192.168.79.11",6379);
		
		//获取Lua文件
		File luaFile = new File(TestRedisLuaLimit.class.getResource("/").toURI().getPath()+"limit.lua");
		//获取Lua文件中的内容
		String luaScript = FileUtils.readFileToString(luaFile);
		
		//定义限流的Key
		String key = "ip:" + System.currentTimeMillis()/1000; // 每秒生成一个不一样的Key
		
		//定义限流的大小
		String limit = "4"; //每秒只允许3个客户端进行访问。
		
		List<String> keys = new ArrayList<String>();
		keys.add(key);
		
		List<String> args = new ArrayList<String>();
		args.add(limit);
		
		//执行Lua脚本，传入参数
		long result = (long) (client.eval(luaScript, keys, args));
		return result==1;
	}
}



















