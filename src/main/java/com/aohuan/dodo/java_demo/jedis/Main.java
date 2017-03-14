package com.aohuan.dodo.java_demo.jedis;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main m = new Main();
		m.doRedis01();
	}
	
	public static ArrayList<String> strList = new ArrayList<String>();
	{
		for(int i= 97; i<=122; i++){
			strList.add((char)i+"");
		}
	}

	public void doRedis01(){
		JedisApi ja = new JedisApi();
		ja.getPool();
		for(int i=0; i<strList.size();i++){
			ja.set((strList.get(i)+"Key").getBytes(), (strList.get(i)+"Value").getBytes());
		}
//		ja.set("aaKey".getBytes(), "aaValue".getBytes());
		for(int i=0; i<strList.size();i++){
			System.out.println(new String(ja.get((strList.get(i)+"Key").getBytes())));
		}
//		System.out.println(new String(ja.get("aaKey".getBytes())));
	}
	
	
}
