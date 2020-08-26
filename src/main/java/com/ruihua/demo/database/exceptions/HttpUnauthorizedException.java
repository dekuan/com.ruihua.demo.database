package com.ruihua.demo.database.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;


/**
 * 	About @ResponseStatus
 *	https://www.baeldung.com/exception-handling-for-rest-with-spring
 */
@ResponseStatus( value = HttpStatus.UNAUTHORIZED )
public class HttpUnauthorizedException extends ResponseStatusException
{
	final static HttpStatus status = HttpStatus.UNAUTHORIZED;

	private final String desc;


	public HttpUnauthorizedException()
	{
		this( status.getReasonPhrase(), (String)null );
	}

	public HttpUnauthorizedException( String reason )
	{
		this( reason, (String)null );
	}
	public HttpUnauthorizedException( String reason, String desc )
	{
		super( status, reason );
		this.desc = desc;
	}


	public String getDesc()
	{
		return desc;
	}
}