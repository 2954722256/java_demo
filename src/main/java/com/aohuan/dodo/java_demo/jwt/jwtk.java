package com.aohuan.dodo.java_demo.jwt;

import java.security.Key;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

public class Jwtk {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Jwtk jw = new Jwtk();
		jw.do01Build();
	}

	
	void do01Build(){
		Key key = MacProvider.generateKey();

		String compactJws = Jwts.builder()
		  .setSubject("Joe")
		  .signWith(SignatureAlgorithm.HS512, key)
		  .compact();
		
		System.out.println(compactJws);
		System.out.println(Jwts.parser().setSigningKey(key).parseClaimsJws(compactJws).getBody());
		System.out.println(Jwts.parser().setSigningKey(key).parseClaimsJws(compactJws).getBody().getSubject());
		System.out.println(Jwts.parser().setSigningKey(key).parseClaimsJws(compactJws).getHeader());
		System.out.println(Jwts.parser().setSigningKey(key).parseClaimsJws(compactJws).getSignature());
	}
}
