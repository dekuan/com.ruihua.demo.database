package com.ruihua.demo.database.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties( "grpc" )
public class GRpcProperties
{
	private boolean enabled;
	private String host;
	private int port;

	GRpcProperties()
	{
		//
		//	initialize the default values
		//
		this.enabled	= true;
		this.host	= "127.0.0.1";
		this.port	= 1124;
	}


	public boolean isEnabled()
	{
		return enabled;
	}
	public void setEnabled( boolean enabled )
	{
		this.enabled = enabled;
	}


	public String getHost()
	{
		return host;
	}
	public void setHost( String host )
	{
		this.host = host;
	}


	public int getPort()
	{
		return port;
	}
	public void setPort( int port )
	{
		this.port = port;
	}
}