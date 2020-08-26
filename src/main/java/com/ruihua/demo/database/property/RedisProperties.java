package com.ruihua.demo.database.property;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


//#   Redis and Jedis
//:
//    :
//    port: 6379
//
//    # redis连接池配置
//    jedis:
//        pool:
//            max-total: 100      #	最大连接数
//            max-active: 200
//            min-idle: 20        #   最小空闲连接数
//            max-idle: 200
//            max-wait: 10000ms   #   当池内没有可用的连接时，最大等待时间

/**
 *	https://mkyong.com/spring-boot/spring-boot-yaml-example/
 */
@Component
@ConfigurationProperties( "redis" )
public class RedisProperties
{
	private String host;
	private int port;
	private boolean useSsl;
	private String password;
	private final RedisJedisProperties jedis = new RedisJedisProperties();

	RedisProperties()
	{
		//
		//	initialize the default values
		//
		this.host	= "127.0.0.1";
		this.port	= 6379;
		this.useSsl	= false;
		this.password	= "";
	}

	public int getPort()
	{
		return port;
	}
	public void setPort( int port )
	{
		this.port = port;
	}


	public String getHost()
	{
		return host;
	}
	public void setHost( String host )
	{
		this.host = host;
	}


	public boolean isUseSsl()
	{
		return useSsl;
	}
	public void setUseSsl( boolean useSsl )
	{
		this.useSsl = useSsl;
	}


	public String getPassword()
	{
		return password;
	}
	public void setPassword( String password )
	{
		this.password = password;
	}



	public RedisJedisProperties getJedis()
	{
		return this.jedis;
	}



	//	redis.jedis
	public static class RedisJedisProperties
	{
		private final RedisJedisPoolProperties pool = new RedisJedisPoolProperties();

		RedisJedisProperties()
		{
		}

		public RedisJedisPoolProperties getPool()
		{
			return this.pool;
		}

		//	redis.jedis.pool
		public static class RedisJedisPoolProperties
		{
			private int maxTotal;		//	最大连接数
			private int maxActive;
			private int minIdle;		//	最小空闲连接数
			private int maxIdle;		//
			private int maxWaitMillis;	//	毫秒；当池内没有可用的连接时，最大等待时间

			RedisJedisPoolProperties()
			{
				maxTotal	= 100;
				maxActive	= 200;
				minIdle		= 20;
				maxIdle		= 200;
				maxWaitMillis	= 10000;
			}


			public int getMaxTotal()
			{
				return maxTotal;
			}
			public void setMaxTotal( int maxTotal )
			{
				this.maxTotal = maxTotal;
			}


			public int getMaxActive()
			{
				return maxActive;
			}
			public void setMaxActive( int maxActive )
			{
				this.maxActive = maxActive;
			}


			public int getMinIdle()
			{
				return minIdle;
			}
			public void setMinIdle( int minIdle )
			{
				this.minIdle = minIdle;
			}


			public int getMaxIdle()
			{
				return maxIdle;
			}
			public void setMaxIdle( int maxIdle )
			{
				this.maxIdle = maxIdle;
			}


			public int getMaxWaitMillis()
			{
				return maxWaitMillis;
			}
			public void setMaxWaitMillis( int maxWaitMillis )
			{
				this.maxWaitMillis = maxWaitMillis;
			}
		}
	}
}