package com.ruihua.demo.database.model;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.ruihua.demo.database.exceptions.InvalidDeException;
import org.apache.commons.lang3.StringUtils;


public class GsonBase
{
	protected String error;

	public GsonBase()
	{
		this.error	= null;
	}
	public GsonBase( String error )
	{
		this.error	= error;
	}


	public String getError()
	{
		return error;
	}
	public void setError( String error )
	{
		this.error = error;
	}

	/**
	 *	parse json string to Object
	 *
	 *	@param	jsonString	string
	 *	@return	MessageUserJoin
	 */
	public static GsonBase fromJson( String jsonString )
	{
		try
		{
			if ( StringUtils.isBlank( jsonString ) )
			{
				throw new InvalidDeException( "invalid.jsonString" );
			}

			Gson gson = new Gson();
			return gson.fromJson( jsonString, GsonBase.class );
		}
		catch ( JsonSyntaxException | IllegalArgumentException | InvalidDeException e )
		{
			e.printStackTrace();
			return new GsonBase( e.getMessage() );
		}
	}

	public String toJson() throws InvalidDeException
	{
		try
		{
			Gson gson = new Gson();
			return gson.toJson( this );
		}
		catch ( JsonSyntaxException | IllegalArgumentException e )
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String toString()
	{
		try
		{
			return this.toJson();
		}
		catch (InvalidDeException e)
		{
			e.printStackTrace();
			return null;
		}
	}
}