package com.ruihua.demo.database.config;

import com.ruihua.demo.database.property.RedisProperties;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import redis.clients.jedis.JedisPoolConfig;


@Configuration
@EnableRedisRepositories
public class RedisConfig
{
	private static final Logger logger = LoggerFactory.getLogger( RedisConfig.class );

	@Autowired
	RedisProperties redisProperties;

	private JedisConnectionFactory RedisConfUtils;



	@Bean
	public JedisPoolConfig jedisPoolConfig()
	{
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

		//	最大连接数 100
		jedisPoolConfig.setMaxTotal( redisProperties.getJedis().getPool().getMaxTotal() );

		//	200
		jedisPoolConfig.setMaxIdle( redisProperties.getJedis().getPool().getMaxIdle() );

		//	最小空闲连接数 20
		jedisPoolConfig.setMinIdle( redisProperties.getJedis().getPool().getMinIdle() );

		//	当池内没有可用的连接时，最大等待时间 10000
		jedisPoolConfig.setMaxWaitMillis( redisProperties.getJedis().getPool().getMaxWaitMillis() );

		logger.info( "RedisStandaloneConfiguration jedis.pool.maxTotal: {}", redisProperties.getJedis().getPool().getMaxTotal() );
		logger.info( "RedisStandaloneConfiguration jedis.pool.maxIdle: {}", redisProperties.getJedis().getPool().getMaxIdle() );
		logger.info( "RedisStandaloneConfiguration jedis.pool.minIdle: {}", redisProperties.getJedis().getPool().getMinIdle() );
		logger.info( "RedisStandaloneConfiguration jedis.pool.maxWaitMillis: {}", redisProperties.getJedis().getPool().getMaxWaitMillis() );

		//------其他属性根据需要自行添加-------------
		return jedisPoolConfig;
	}

	@Bean
	JedisConnectionFactory jedisConnectionFactory()
	{
		//
		//	Azure Redis Configuration
		//	https://docs.microsoft.com/en-us/azure/azure-cache-for-redis/cache-java-get-started
		//
		RedisStandaloneConfiguration aloneConfig = new RedisStandaloneConfiguration
			(
				redisProperties.getHost(),	//	"127.0.0.1"
				redisProperties.getPort()	//	6379/6380
			);
		if ( ! StringUtils.isBlank( redisProperties.getPassword() ) )
		{
			aloneConfig.setPassword( redisProperties.getPassword() );
		}
		logger.info(
			"^_^ --- ^_^ ))) RedisStandaloneConfiguration: host={}, port={}, password={}",
			aloneConfig.getHostName(),
			aloneConfig.getPort(),
			aloneConfig.getPassword() );

		//	获得默认的连接池构造器(怎么设计的，为什么不抽象出单独类，供用户使用呢)
		JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jedisPoolConfigBuilder =
			(JedisClientConfiguration.JedisPoolingClientConfigurationBuilder)JedisClientConfiguration.builder();

		//	指定 jedisPoolConifig 来修改默认的连接池构造器（真麻烦，滥用设计模式！）
		jedisPoolConfigBuilder.poolConfig( jedisPoolConfig() );

		//	通过构造器来构造 jedis 客户端配置
		JedisClientConfiguration jedisClientConfig = jedisPoolConfigBuilder.build();

		return new JedisConnectionFactory( aloneConfig, jedisClientConfig );
	}

	@Bean
	public RedisTemplate<String, String> redisTemplate()
	{
		RedisTemplate<String, String> template = new RedisTemplate<>();
		template.setConnectionFactory( jedisConnectionFactory() );

		return template;
	}
}