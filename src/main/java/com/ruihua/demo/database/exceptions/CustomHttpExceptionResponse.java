package com.ruihua.demo.database.exceptions;

import java.time.LocalDateTime;


/**
 * 	https://mkyong.com/spring-boot/spring-rest-error-handling-example/
 */
public class CustomHttpExceptionResponse
{
	//	{
	//		"timestamp": "2020-07-14T01:29:36.369+0000",
	//		"status": 401,
	//		"error": "Unauthorized",
	//		"code": "failed.authorized",
	//		"message": "请先登录系统！"
	//	}
	//
//	@JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss" )
	private LocalDateTime timestamp;

	private int status;

	private String error;
	private String message;
	private String detail;
	private String path;
	private String client;



	public LocalDateTime getTimestamp()
	{
		return timestamp;
	}
	public void setTimestamp( LocalDateTime timestamp )
	{
		this.timestamp = timestamp;
	}

	public int getStatus()
	{
		return status;
	}
	public void setStatus( int status )
	{
		this.status = status;
	}


	public String getError()
	{
		return error;
	}
	public void setError( String error )
	{
		this.error = error;
	}


	public String getMessage()
	{
		return message;
	}
	public void setMessage( String message )
	{
		this.message = message;
	}

	public String getDetail()
	{
		return detail;
	}
	public void setDetail( String detail )
	{
		this.detail = detail;
	}

	public String getPath()
	{
		return path;
	}
	public void setPath( String path )
	{
		this.path = path;
	}


	public String getClient()
	{
		return client;
	}
	public void setClient( String client )
	{
		this.client = client;
	}
}
