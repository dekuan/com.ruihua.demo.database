package com.ruihua.demo.database.model.rest;

import com.ruihua.demo.database.exceptions.HttpBadRequestException;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;


public class RestInfoBaseResponse
{
	protected String mid;

	protected long   userId;
	protected String userMid;
	protected String companyMid;
	protected String createdAt;
	protected String updatedAt;


	public RestInfoBaseResponse()
	{
		this.mid		= null;
		this.userId		= 0;
		this.userMid		= null;
		this.companyMid		= null;
		this.createdAt		= null;
		this.updatedAt		= null;
	}
	public RestInfoBaseResponse
		(
			String mid,
			long   userId,
			String userMid,
			LocalDateTime createdAt,
			LocalDateTime updatedAt
		) throws HttpBadRequestException
	{
		this.setMid( mid );
		this.setUserId( userId );
		this.setUserMid( userMid );
		this.setCreatedAt( createdAt );
		this.setUpdatedAt( updatedAt );
	}
	public RestInfoBaseResponse
		(
			String mid,
			long   userId,
			String userMid,
			String companyMid,
			LocalDateTime createdAt,
			LocalDateTime updatedAt
		) throws HttpBadRequestException
	{
		this.setMid( mid );
		this.setUserId( userId );
		this.setUserMid( userMid );
		this.setCompanyMid( companyMid );
		this.setCreatedAt( createdAt );
		this.setUpdatedAt( updatedAt );
	}
	public RestInfoBaseResponse
		(
			String mid,
			long   userId,
			String userMid,
			String companyMid,
			String createdAt,
			String updatedAt
		) throws HttpBadRequestException
	{
		this.setMid( mid );
		this.setUserId( userId );
		this.setUserMid( userMid );
		this.setCompanyMid( companyMid );
		this.setCreatedAt( createdAt );
		this.setUpdatedAt( updatedAt );
	}


	public String getMid()
	{
		return mid;
	}
	public RestInfoBaseResponse setMid( String mid )
	{
		this.mid = mid;
		return this;
	}


	public long getUserId()
	{
		return userId;
	}
	public RestInfoBaseResponse setUserId( long userId )
	{
		this.userId = userId;
		return this;
	}


	public String getUserMid()
	{
		return userMid;
	}
	public RestInfoBaseResponse setUserMid( String userMid )
	{
		this.userMid = userMid;
		return this;
	}


	public String getCompanyMid()
	{
		return companyMid;
	}
	public RestInfoBaseResponse setCompanyMid( String companyMid )
	{
		this.companyMid = companyMid;
		return this;
	}


	public String getCreatedAt()
	{
		return createdAt;
	}
	public RestInfoBaseResponse setCreatedAt( LocalDateTime createdAt )
	{
		this.createdAt = ( null != createdAt ? createdAt.toString() : "" );
		return this;
	}
	public RestInfoBaseResponse setCreatedAt( String createdAt )
	{
		this.createdAt = createdAt;
		return this;
	}


	public String getUpdatedAt()
	{
		return updatedAt;
	}
	public RestInfoBaseResponse setUpdatedAt( String updatedAt )
	{
		this.updatedAt = updatedAt;
		return this;
	}
	public RestInfoBaseResponse setUpdatedAt( LocalDateTime updatedAt )
	{
		this.updatedAt = ( null != updatedAt ? updatedAt.toString() : "" );
		return this;
	}
}
