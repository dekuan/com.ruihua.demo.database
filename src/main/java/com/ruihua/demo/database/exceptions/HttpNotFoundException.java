package com.ruihua.demo.database.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;


@ResponseStatus( value = HttpStatus.NOT_FOUND )
public class HttpNotFoundException extends ResponseStatusException
{
	final static HttpStatus status = HttpStatus.NOT_FOUND;

	private final String desc;


	public HttpNotFoundException()
	{
		this( status.getReasonPhrase(), (String)null );
	}

	public HttpNotFoundException( String reason )
	{
		this( reason, (String)null );
	}
	public HttpNotFoundException( String reason, String desc )
	{
		super( status, reason );
		this.desc = desc;
	}


	public String getDesc()
	{
		return desc;
	}
}