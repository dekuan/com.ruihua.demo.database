package com.ruihua.demo.database.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties( "app" )
public class AppProperties
{
	private String timezone;

	AppProperties()
	{
		//
		//	initialize the default values
		//
		this.timezone	= "Asia/Shanghai";
	}


	public String getTimezone()
	{
		return timezone;
	}
	public void setTimezone( String timezone )
	{
		this.timezone = timezone;
	}
}