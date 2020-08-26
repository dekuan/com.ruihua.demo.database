package com.ruihua.demo.database.model.rest;


public class RestPostBaseResponse
{
	private String mid;

	public RestPostBaseResponse()
	{
		this.mid = null;
	}


	public String getMid()
	{
		return mid;
	}
	public RestPostBaseResponse setMid( String mid )
	{
		this.mid = mid;
		return this;
	}
}
