package com.ruihua.demo.database;

import org.slf4j.LoggerFactory;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PreDestroy;


@SpringBootApplication
public class RuiHuaDemoDatabaseApplication
{
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger( RuiHuaDemoDatabaseApplication.class );

	public static void main( String[] args )
	{
		logger.info( ")))))) RuiHuaDemoDatabaseApplication is starting" );
		SpringApplication.run( RuiHuaDemoDatabaseApplication.class, args );
		logger.info( ")))))) RuiHuaDemoDatabaseApplication started" );
	}

	@Bean
	public ExitCodeGenerator exitCodeGenerator()
	{
		return () -> 0;
	}

	@PreDestroy
	public void onExit()
	{
		logger.info( "### RuiHuaDemoDatabaseApplication is stopping ###" );

		try
		{
			Thread.sleep(100 );
		}
		catch ( InterruptedException e )
		{
			e.printStackTrace();
			logger.error( "", e );;
		}

		logger.info( "### RuiHuaDemoDatabaseApplication stopped from the lifecycle ###" );
	}
}
