package com.ruihua.demo.database.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;


@ResponseStatus( value = HttpStatus.INTERNAL_SERVER_ERROR )
public class HttpInternalServerErrorException extends ResponseStatusException
{
	final static HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

	private final String desc;


	public HttpInternalServerErrorException()
	{
		this( status.getReasonPhrase(), (String)null );
	}

	public HttpInternalServerErrorException( String reason )
	{
		this( reason, (String)null );
	}
	public HttpInternalServerErrorException( String reason, String desc )
	{
		super( status, reason );
		this.desc = desc;
	}


	public String getDesc()
	{
		return desc;
	}
}