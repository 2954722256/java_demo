package com.aohuan.dodo.java_demo.jedis;

import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisApi {
	private static JedisPool jedisPool;
	
	private static final String REDIS_HOST = "127.0.0.1";
	private static final int REDIS_PORT = 6379;
	
	// 0 - never expire
	private int expire = 0;
	
	//timeout for jedis try to connect to redis server, not expire time! In milliseconds
	private int timeout = 0;
//	private String password = "";
	
	public static JedisPool getPool(){
		if(jedisPool == null){
			JedisPoolConfig config = new JedisPoolConfig();  
            //控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；  
            //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。  
//            config.setMaxActive(500);  
            config.setMaxTotal(500);
            //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。  
            config.setMaxIdle(5);  
            //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；  
//            config.setMaxWait(1000 * 100);
            config.setMaxWaitMillis(1000 * 100);
            //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；  
            config.setTestOnBorrow(true);  
            jedisPool = new JedisPool(config, REDIS_HOST, REDIS_PORT); 
		}
		return jedisPool;
	}
	
	
	/**
	 * get value from redis
	 * @param key
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public byte[] get(byte[] key){
		byte[] value = null;
		Jedis jedis = jedisPool.getResource();
		try{
			value = jedis.get(key);
		}finally{
			jedisPool.returnResource(jedis);
		}
		return value;
	}
	
//	/**
//	 * set 
//	 * @param key
//	 * @param value
//	 * @return
//	 */
//	@SuppressWarnings("deprecation")
//	public byte[] set(String key,byte[] value){
//		Jedis jedis = jedisPool.getResource();
//		try{
//			jedis.set(key,value);
//			jedis.set`
//			if(this.expire != 0){
//				jedis.expire(key, this.expire);
//		 	}
//		}finally{
//			jedisPool.returnResource(jedis);
//		}
//		return value;
//	}
	
	/**
	 * set 
	 * @param key
	 * @param value
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public byte[] set(byte[] key,byte[] value){
		Jedis jedis = jedisPool.getResource();
		try{
			jedis.set(key,value);
			if(this.expire != 0){
				jedis.expire(key, this.expire);
		 	}
		}finally{
			jedisPool.returnResource(jedis);
		}
		return value;
	}
	
	/**
	 * set 
	 * @param key
	 * @param value
	 * @param expire
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public byte[] set(byte[] key,byte[] value,int expire){
		Jedis jedis = jedisPool.getResource();
		try{
			jedis.set(key,value);
			if(expire != 0){
				jedis.expire(key, expire);
		 	}
		}finally{
			jedisPool.returnResource(jedis);
		}
		return value;
	}
	
	/**
	 * del
	 * @param key
	 */
	@SuppressWarnings("deprecation")
	public void del(byte[] key){
		Jedis jedis = jedisPool.getResource();
		try{
			jedis.del(key);
		}finally{
			jedisPool.returnResource(jedis);
		}
	}
	
	/**
	 * flush
	 */
	@SuppressWarnings("deprecation")
	public void flushDB(){
		Jedis jedis = jedisPool.getResource();
		try{
			jedis.flushDB();
		}finally{
			jedisPool.returnResource(jedis);
		}
	}
	
	/**
	 * size
	 */
	@SuppressWarnings("deprecation")
	public Long dbSize(){
		Long dbSize = 0L;
		Jedis jedis = jedisPool.getResource();
		try{
			dbSize = jedis.dbSize();
		}finally{
			jedisPool.returnResource(jedis);
		}
		return dbSize;
	}

	/**
	 * keys
	 * @param regex
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public Set<byte[]> keys(String pattern){
		Set<byte[]> keys = null;
		Jedis jedis = jedisPool.getResource();
		try{
			keys = jedis.keys(pattern.getBytes());
		}finally{
			jedisPool.returnResource(jedis);
		}
		return keys;
	}
	
	
	
	
}
