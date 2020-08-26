package com.ruihua.demo.database.model.rest.company;


import com.ruihua.demo.database.exceptions.InvalidDeException;
import com.ruihua.demo.database.model.rest.RestInfoBaseResponse;

public class RestCompanyInfo extends RestInfoBaseResponse
{
	private String companyName;
	private String companyAddress;
	private String companyContacts;
	private String companyDesc;


	public RestCompanyInfo()
	{
		this.companyName	= null;
		this.companyAddress	= null;
		this.companyContacts	= null;
		this.companyDesc	= null;
	}



	public String getCompanyName()
	{
		return companyName;
	}
	public RestCompanyInfo setCompanyName(String companyName )
	{
		this.companyName = companyName;
		return this;
	}


	public String getCompanyAddress()
	{
		return companyAddress;
	}
	public RestCompanyInfo setCompanyAddress(String companyAddress )
	{
		this.companyAddress = companyAddress;
		return this;
	}


	public String getCompanyContacts()
	{
		return companyContacts;
	}
	public RestCompanyInfo setCompanyContacts(String companyContacts )
	{
		this.companyContacts = companyContacts;
		return this;
	}


	public String getCompanyDesc()
	{
		return companyDesc;
	}
	public RestCompanyInfo setCompanyDesc(String companyDesc )
	{
		this.companyDesc = companyDesc;
		return this;
	}
}
