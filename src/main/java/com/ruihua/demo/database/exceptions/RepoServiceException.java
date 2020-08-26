package com.ruihua.demo.database.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RepoServiceException extends Exception
{
	private static final Logger logger = LoggerFactory.getLogger( RepoServiceException.class );

	public RepoServiceException( String message )
	{
		super( message );
	}
}