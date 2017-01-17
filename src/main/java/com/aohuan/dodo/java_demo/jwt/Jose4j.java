package com.aohuan.dodo.java_demo.jwt;

import java.security.Key;

import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.keys.AesKey;
import org.jose4j.lang.ByteUtil;
import org.jose4j.lang.JoseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Jose4j {
	private static Logger logger = LoggerFactory.getLogger(Jose4j.class);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		do01HelloJwe();
	}

	private static void do01HelloJwe() {
		// TODO Auto-generated method stub
		Key key = new AesKey(ByteUtil.randomBytes(16));
		JsonWebEncryption jwe = new JsonWebEncryption();
		jwe.setPayload("Hello World!");
		jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.A128KW);
		jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);
		jwe.setKey(key);
		try {
			String serializedJwe = jwe.getCompactSerialization();
			
			logger.info("Serialized Encrypted JWE: " + serializedJwe);
			logger.warn("Serialized Encrypted JWE: " + serializedJwe);
			System.out.println("Serialized Encrypted JWE: " + serializedJwe);
			jwe = new JsonWebEncryption();
			jwe.setKey(key);
			jwe.setCompactSerialization(serializedJwe);
			System.out.println("Payload: " + jwe.getPayload());
		} catch (JoseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
