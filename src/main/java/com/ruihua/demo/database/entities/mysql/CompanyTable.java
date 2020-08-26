package com.ruihua.demo.database.entities.mysql;

import com.google.common.base.Strings;
import com.ruihua.demo.database.exceptions.InvalidDeException;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;


/**
 * 	Define a named sequence
 * 	https://www.objectdb.com/java/jpa/entity/generated
 */
@Entity
@Table(
	name = "companies",
	uniqueConstraints = {
		@UniqueConstraint( columnNames = { "alt", "company_name" } ),
	}
)
@DynamicUpdate
@DynamicInsert
@Data
public class CompanyTable extends BaseEntity
{
	@Column( name = "company_name", columnDefinition = "VARCHAR(128) NOT NULL DEFAULT ''" )
	private String companyName;

	@Column( name = "company_address", columnDefinition = "VARCHAR(128) NOT NULL DEFAULT ''" )
	private String companyAddress;

	@Column( name = "company_contacts", columnDefinition = "VARCHAR(128) NOT NULL DEFAULT ''" )
	private String companyContacts;

	@Column( name = "company_desc", columnDefinition = "VARCHAR(4096) NOT NULL DEFAULT ''" )
	private String companyDesc;


	public CompanyTable() throws InvalidDeException
	{
		super();

		//	...
		this.companyName	= null;
		this.companyAddress	= null;
		this.companyContacts	= null;
		this.companyDesc	= null;
	}
	public CompanyTable
		(
			String companyName,
			String companyAddress,
			String companyContacts,
			String companyDesc
		) throws InvalidDeException
	{
		this.setMidAsRandomUUID();
		this.setEnabled( 1 );

		this.setCompanyName( companyName );
		this.setCompanyAddress( companyAddress );
		this.setCompanyContacts( companyContacts );
		this.setCompanyDesc( companyDesc );
	}


	public String getCompanyName()
	{
		return companyName;
	}
	public void setCompanyName( String companyName )
	{
		this.companyName = companyName;
	}


	public String getCompanyAddress()
	{
		return companyAddress;
	}
	public void setCompanyAddress( String companyAddress )
	{
		this.companyAddress = companyAddress;
	}


	public String getCompanyContacts()
	{
		return companyContacts;
	}
	public void setCompanyContacts( String companyContacts )
	{
		this.companyContacts = companyContacts;
	}


	public String getCompanyDesc()
	{
		return companyDesc;
	}
	public void setCompanyDesc( String companyDesc )
	{
		this.companyDesc = companyDesc;
	}
}
