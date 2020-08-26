package com.ruihua.demo.database.entities.redis;


import com.ruihua.demo.database.exceptions.InvalidDeException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;


/**
 * 	https://github.com/piomin/sample-spring-redis/blob/fea2e5a9013409cb69ab2deca22588c423bb4388/src/main/java/pl/piomin/services/redis/model/Customer.java
 *
 */
@RedisHash( "RedisToken" )
public class RedisToken implements Serializable
{
	private static final Logger logger = LoggerFactory.getLogger( RedisToken.class );

	@Id
	private String user;		//	u_mid

	private String token;		//	tok_string
	private long expire;		//	tok_expire in seconds, unix timestamp

	//	will disappeared in seconds from Redis
	//	default value is 90 days
	@Transient
	private long timeToLive	= 7776000;


	public RedisToken( String user, String token, long expire ) throws InvalidDeException
	{
		if ( StringUtils.isBlank( token ) )
		{
			throw new InvalidDeException( "invalid.token" );
		}

		this.setUser( user );
		this.setToken( token );
		this.setExpire( expire );
	}


	@TimeToLive
	public long getTimeToLive()
	{
		return timeToLive;
	}
	public void setTimeToLive( long timeToLive )
	{
		if ( timeToLive > 0 )
		{
			this.timeToLive = timeToLive;
		}
	}


	/**
	 * 	check if all data is valid and,
	 * 	the user match perfectly
	 *	@param	user	-
	 *	@return	boolean
	 */
	public boolean isValid( String user )
	{
		return StringUtils.isNotBlank( user ) &&
			this.isValid() &&
			this.user.equals( user );
	}

	/**
	 * 	check if all data is valid and,
	 * 	both user and token match perfectly
	 *	@param	user	-
	 *	@param	token	-
	 *	@return	boolean
	 */
	public boolean isValid( String user, String token )
	{
		return StringUtils.isNotBlank( user ) &&
			! StringUtils.isBlank( token ) &&
			this.isValid() &&
			this.user.equals( user ) &&
			this.token.equals( token );
	}

	/**
	 *	check if all data is valid
	 *
	 *	@return	boolean
	 */
	public boolean isValid()
	{
		return ! StringUtils.isBlank( this.token ) &&
			StringUtils.isNotBlank( this.user ) &&
			! RedisToken.isExpired( this.expire );
	}


	public String getUser()
	{
		return this.user;
	}
	public void setUser( String user ) throws InvalidDeException
	{
		if ( StringUtils.isBlank( user ) )
		{
			throw new InvalidDeException( "invalid.user" );
		}

		this.user = user;
	}

	public String getToken()
	{
		return this.token;
	}
	public void setToken( String token )
	{
		this.token = token;
	}


	public long getExpire()
	{
		return this.expire;
	}
	public void setExpire( long expire ) throws InvalidDeException
	{
		if ( RedisToken.isExpired( expire ) )
		{
			throw new InvalidDeException( "invalid.expire" );
		}

		this.expire = expire;
	}
	public void setExpire( LocalDateTime ldtDate ) throws InvalidDeException
	{
		this.setExpire( RedisToken.toExpireInSeconds( ldtDate ) );
	}


	/**
	 *	convert unix timestamp in seconds to LocalDateTime
	 *	@param	expire		-
	 *	@return	LocalDateTime
	 */
	public static LocalDateTime ofExpireInSeconds( long expire )
	{
		if ( expire <= 0 )
		{
			return null;
		}

		//
		//	Inside of that Instant is a count of nanoseconds-from-epoch.
		//	But we do not really care.
		//
		Instant instant		= Instant.now();
		ZoneId zoneId		= ZoneId.of( "Asia/Shanghai" );
		ZoneOffset zoneOffset	= zoneId.getRules().getOffset( instant );

		return LocalDateTime.ofEpochSecond( expire, 0, zoneOffset );
	}

	/**
	 * 	convert LocalDateTime to unix timestamp in seconds
	 *	@param	ldtDate		-
	 *	@return	long
	 */
	public static long toExpireInSeconds( LocalDateTime ldtDate )
	{
		if ( null == ldtDate )
		{
			return 0;
		}

		return ldtDate.atZone( ZoneId.of( "Asia/Shanghai" ) ).toInstant().toEpochMilli() / 1000L;
	}

	/**
	 *	check if expire has expired
	 *
	 *	@param	lnExpire	Long	- in seconds, unix timestamp
	 *	@return	boolean
	 */
	public static boolean isExpired( long lnExpire )
	{
		return lnExpire <= 0 ||
			( lnExpire - Instant.now().getEpochSecond() ) <= 0;
	}
}