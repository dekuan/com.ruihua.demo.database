package com.ruihua.demo.database.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;


@ResponseStatus( value = HttpStatus.BAD_REQUEST )
public class HttpBadRequestException extends ResponseStatusException
{
	final static HttpStatus status = HttpStatus.BAD_REQUEST;

	private final String desc;


	public HttpBadRequestException()
	{
		this( status.getReasonPhrase(), (String)null );
	}

	public HttpBadRequestException( String reason )
	{
		this( reason, (String)null );
	}
	public HttpBadRequestException( String reason, String desc )
	{
		super( status, reason );
		this.desc = desc;
	}


	public String getDesc()
	{
		return desc;
	}
}