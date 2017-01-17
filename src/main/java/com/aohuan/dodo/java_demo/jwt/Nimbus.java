package com.aohuan.dodo.java_demo.jwt;

import java.security.SecureRandom;
import java.text.ParseException;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;

public class Nimbus {

	public static void main(String[] args){
		Nimbus ni = new Nimbus();
		ni.do01Hello();
		
	}
	
	void do01Hello(){
		// Generate random 256-bit (32-byte) shared secret
		SecureRandom random = new SecureRandom();
		byte[] sharedSecret = new byte[32];
		random.nextBytes(sharedSecret);

		// Create HMAC signer		
		try {
			JWSSigner signer = new MACSigner(sharedSecret);
			// Prepare JWS object with "Hello, world!" payload
			JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.HS256), new Payload("Hello, world!"));

			// Apply the HMAC
			jwsObject.sign(signer);

			// To serialize to compact form, produces something like
			// eyJhbGciOiJIUzI1NiJ9.SGVsbG8sIHdvcmxkIQ.onO9Ihudz3WkiauDO2Uhyuz0Y18UASXlSc1eS0NkWyA
			String s = jwsObject.serialize();

			// To parse the JWS and verify it, e.g. on client-side
			jwsObject = JWSObject.parse(s);

			JWSVerifier verifier = new MACVerifier(sharedSecret);
			
			System.out.println("is true? : " + jwsObject.verify(verifier));
			
			System.out.println("getPayload : " + jwsObject.getPayload().toString());
			
		} catch (KeyLengthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JOSEException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

//		assertTrue(jwsObject.verify(verifier));
//
//		assertEquals("Hello, world!", jwsObject.getPayload().toString());
	}
}
