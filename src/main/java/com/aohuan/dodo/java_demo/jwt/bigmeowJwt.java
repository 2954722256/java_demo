package com.aohuan.dodo.java_demo.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;


public class BigmeowJwt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getToken();
		verifyToken();
	}

	private static void verifyToken() {
		// TODO Auto-generated method stub
//		String token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiIyOTE5Njk0NTIiLCJpYXQiOjE0NjA0MzE4ODk2OTgsImV4dCI6MTQ2MDQzNTQ4OTY5OH0.RAa71BnklRMPyPhYBbxsfJdtXBnXeWevxcXLlwC2PrY";
//		Map<String, Object> result=Jwt.validToken(token);
//
//		String state=(String)result.get("state");
//		switch (TokenState.getTokenState(state)) {
//		case VALID:
//		    //To do somethings
//		    System.out.println("有效token");
//		    break;
//		case EXPIRED:
//		    System.out.println("过期token");
//		    break;
//		case INVALID:
//		    System.out.println("无效的token");
//		    break;
//		}
//
//		System.out.println("返回结果数据是：" +result.toString());
		
		
	}

	private static void getToken() {
		// TODO Auto-generated method stub
		Map<String , Object> payload = new HashMap<String, Object>();
		Date date=new Date();
		payload.put("uid", "291969452");//用户id
		payload.put("iat", date.getTime());//生成时间
		payload.put("ext",date.getTime()+1000*60*60);//过期时间1小时
//		String token = JWT.createToken(payload);
//		String token = JWT.create().withJWTId(payload);
//		System.out.println("token:"+token);
	}

}
