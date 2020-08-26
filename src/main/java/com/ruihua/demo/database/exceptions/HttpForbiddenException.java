package com.ruihua.demo.database.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;


@ResponseStatus( value = HttpStatus.FORBIDDEN )
public final class HttpForbiddenException extends ResponseStatusException
{
	final static HttpStatus status = HttpStatus.FORBIDDEN;

	private final String desc;


	public HttpForbiddenException()
	{
		this( status.getReasonPhrase(), (String)null );
	}

	public HttpForbiddenException( String reason )
	{
		this( reason, (String)null );
	}
	public HttpForbiddenException( String reason, String desc )
	{
		super( status, reason );
		this.desc = desc;
	}


	public String getDesc()
	{
		return desc;
	}
}