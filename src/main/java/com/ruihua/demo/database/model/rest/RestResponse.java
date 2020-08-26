package com.ruihua.demo.database.model.rest;


import com.ruihua.demo.database.exceptions.InvalidDeException;
import ruihua.demo.database.common.EnumRpcCode;



/**
 *	REST Response
 */
public class RestResponse<T> extends BaseResponse
{
	private T body;


	public RestResponse()
	{
		super();
		this.body = null;
	}
	public RestResponse( T body )
	{
		super();
		this.setBody( body );
	}
	public RestResponse( String error )
	{
		super( error );
	}
	public RestResponse( EnumRpcCode code, String message, T body ) throws InvalidDeException
	{
		super( code, message );
		this.setBody( body );
	}
	public RestResponse( String sequence, EnumRpcCode code, String message, T body ) throws InvalidDeException
	{
		super( sequence, code, message );
		this.setBody( body );
	}
	public RestResponse( String version, String sequence, EnumRpcCode code, String message, T body ) throws InvalidDeException
	{
		super( version, sequence, code, message );
		this.setBody( body );
	}

	public T getBody()
	{
		return body;
	}
	public void setBody( T body )
	{
		this.body = body;
	}
}