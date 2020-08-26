package com.ruihua.demo.database.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class InvalidDeException extends Exception
{
	private static final Logger logger = LoggerFactory.getLogger( InvalidDeException.class );

	public InvalidDeException( String sMessage )
	{
		super( sMessage );
	}
}