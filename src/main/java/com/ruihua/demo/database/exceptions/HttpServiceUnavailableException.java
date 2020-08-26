package com.ruihua.demo.database.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;


@ResponseStatus( value = HttpStatus.SERVICE_UNAVAILABLE )
public final class HttpServiceUnavailableException extends ResponseStatusException
{
	final static HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;

	private final String desc;


	public HttpServiceUnavailableException()
	{
		this( status.getReasonPhrase(), (String)null );
	}

	public HttpServiceUnavailableException( String reason )
	{
		this( reason, (String)null );
	}
	public HttpServiceUnavailableException( String reason, String desc )
	{
		super( status, reason );
		this.desc = desc;
	}


	public String getDesc()
	{
		return desc;
	}
}