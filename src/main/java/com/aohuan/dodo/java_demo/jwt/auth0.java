package com.aohuan.dodo.java_demo.jwt;

import java.io.UnsupportedEncodingException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAKey;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;

public class auth0 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		auth0 au = new auth0();
		// au.do01DecodeToken();
//		au.do02CreatAndSignToken();
//		au.do022CreatAndSignToken();	//XXXX
		au.do031VerifyTokenHS();
//		au.do032VerifyTokenRSA();
	}

	void do01DecodeToken() {
		String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXUyJ9.eyJpc3MiOiJhdXRoMCJ9.AbIJTDMFc7yUa5MhvcP03nJPyCPzZtQcGEp-zWfOkEE";
		try {
			JWT jwt = JWT.decode(token);
			System.out.println("in");
		} catch (JWTDecodeException exception) {
			// Invalid token
			System.out.println("out");
		}
	}

	void do02CreatAndSignToken() {
		try {
			String token = JWT.create().withIssuer("auth0").sign(Algorithm.HMAC256("secret"));
			System.out.println("in : " + token);
		} catch (JWTCreationException exception) {
			// Invalid Signing configuration / Couldn't convert Claims.
			System.out.println("out");
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			System.out.println("out");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			System.out.println("out");
			e.printStackTrace();
		}
	}

	void do022CreatAndSignToken() {
		PrivateKey key = new PrivateKey() {
			private static final long serialVersionUID = 1L;

			public String getAlgorithm() {
				// TODO Auto-generated method stub
				return null;
			}

			public String getFormat() {
				// TODO Auto-generated method stub
				return null;
			}

			public byte[] getEncoded() {
				// TODO Auto-generated method stub
				return null;
			}

		};// Get the key instance
		try {
			String token = JWT.create().withIssuer("auth0").sign(Algorithm.RSA256((RSAKey) key));
			System.out.println("in : " + token);
		} catch (JWTCreationException exception) {
			// Invalid Signing configuration / Couldn't convert Claims.
			System.out.println("out");
		}
	}
	
	void do031VerifyTokenHS(){
		String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXUyJ9.eyJpc3MiOiJhdXRoMCJ9.AbIJTDMFc7yUa5MhvcP03nJPyCPzZtQcGEp-zWfOkEE";
		try {
		    JWTVerifier verifier = JWT.require(Algorithm.HMAC256("secret"))
		        .withIssuer("auth0")
		        .build(); //Reusable verifier instance
		    JWT jwt = (JWT) verifier.verify(token);
		    System.out.println(jwt.getToken());
		    System.out.println(jwt.getContentType());
		    System.out.println(jwt.getId());
		    
		    System.out.println(jwt.getSignature());
		    System.out.println(JWT.decode(jwt.getToken()).getSubject());
		} catch (JWTVerificationException exception){
		    //Invalid signature/claims
			exception.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void do032VerifyTokenRSA(){
		String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXUyJ9.eyJpc3MiOiJhdXRoMCJ9.AbIJTDMFc7yUa5MhvcP03nJPyCPzZtQcGEp-zWfOkEE";
		PublicKey key = new PublicKey(){
			private static final long serialVersionUID = 1L;

			public String getAlgorithm() {
				// TODO Auto-generated method stub
				return null;
			}

			public String getFormat() {
				// TODO Auto-generated method stub
				return null;
			}

			public byte[] getEncoded() {
				// TODO Auto-generated method stub
				return null;
			}};// Get the key instance
		try {
		    JWTVerifier verifier = JWT.require(Algorithm.RSA256((RSAKey) key))
		        .withIssuer("auth0")
		        .build(); //Reusable verifier instance
		    JWT jwt = (JWT) verifier.verify(token);
		} catch (JWTVerificationException exception){
		    //Invalid signature/claims
		}
	}

}
