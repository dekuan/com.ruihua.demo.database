package com.ruihua.demo.database.model.rest;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.ruihua.demo.database.exceptions.InvalidDeException;
import org.apache.commons.lang3.StringUtils;
import ruihua.demo.database.common.EnumRpcCode;


/**
 *	REST Base Response
 */
public class BaseResponse
{
	final static public String CURRENT_VERSION	= "1.0";

	protected String version;
	protected String sequence;
	protected String error;

	protected EnumRpcCode code;
	protected String message;


	public BaseResponse()
	{
		this.version	= CURRENT_VERSION;
		this.sequence	= null;
		this.error	= null;

		this.code	= EnumRpcCode.RPC_UNKNOWN;
		this.message	= null;
	}
	public BaseResponse( String error )
	{
		this.setError( error );
	}
	public BaseResponse( EnumRpcCode code, String message ) throws InvalidDeException
	{
		this.setVersion( CURRENT_VERSION );
		this.setSequence( null );
		this.setError( null );

		this.setCode( code );
		this.setMessage( message );
	}
	public BaseResponse( String sequence, EnumRpcCode code, String message ) throws InvalidDeException
	{
		this.setVersion( CURRENT_VERSION );
		this.setSequence( sequence );
		this.setError( null );

		this.setCode( code );
		this.setMessage( message );
	}
	public BaseResponse( String version, String sequence, EnumRpcCode code, String message ) throws InvalidDeException
	{
		this.setVersion( version );
		this.setSequence( sequence );
		this.setError( null );

		this.setCode( code );
		this.setMessage( message );
	}


	public String getVersion()
	{
		return this.version;
	}
	public BaseResponse setVersion( String version ) throws InvalidDeException
	{
		if ( StringUtils.isBlank( version ) )
		{
			throw new InvalidDeException( "invalid.version" );
		}

		this.version = version;
		return this;
	}


	public String getSequence()
	{
		return sequence;
	}
	public BaseResponse setSequence( String sequence )
	{
		this.sequence = sequence;
		return this;
	}


	public String getError()
	{
		return this.error;
	}
	public BaseResponse setError( String error )
	{
		this.error = error;
		return this;
	}


	public EnumRpcCode getCode()
	{
		return this.code;
	}
	public BaseResponse setCode( EnumRpcCode code ) throws InvalidDeException
	{
		if ( null == code || null == EnumRpcCode.forNumber( code.getNumber() ) )
		{
			throw new InvalidDeException( "invalid.code" );
		}

		this.code = code;
		return this;
	}


	public String getMessage()
	{
		return message;
	}
	public BaseResponse setMessage( String message )
	{
		this.message = message;
		return this;
	}


	/**
	 *	parse json string to Message Object
	 *
	 *	@param	sJsonString	string
	 *	@return	Message
	 */
	public static BaseResponse fromJson( String sJsonString )
	{
		try
		{
			if ( null == sJsonString || sJsonString.isEmpty() )
			{
				throw new InvalidDeException( "invalid.sJsonString" );
			}

			Gson gson = new Gson();
			return gson.fromJson( sJsonString, BaseResponse.class );
		}
		catch ( JsonSyntaxException | IllegalArgumentException | InvalidDeException e )
		{
			return new BaseResponse( e.getMessage() );
		}
	}

	public String toJson()
	{
		try
		{
			if ( null == this.code ||
				null == EnumRpcCode.forNumber( this.code.getNumber() ) )
			{
				throw new InvalidDeException( "invalid.code" );
			}

			Gson gson = new Gson();
			return gson.toJson( this );
		}
		catch ( JsonSyntaxException | IllegalArgumentException | InvalidDeException e )
		{
			return null;
		}
	}

	@Override
	public String toString()
	{
		return this.toJson();
	}
}