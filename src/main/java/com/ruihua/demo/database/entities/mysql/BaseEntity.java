package com.ruihua.demo.database.entities.mysql;

import com.ruihua.demo.database.exceptions.InvalidDeException;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.*;
import ruihua.demo.database.common.EnumCommonStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;



@TypeDefs({
	@TypeDef( name = "json", typeClass = JsonStringType.class ),
	@TypeDef( name = "jsonb", typeClass = JsonBinaryType.class )
})
@MappedSuperclass
public class BaseEntity implements Serializable
{
	@Id
	@GeneratedValue( strategy= GenerationType.IDENTITY )
	@Column( name = "id", columnDefinition = "BIGINT", updatable = false, nullable = false, unique = true )
	protected BigInteger id;

	@GeneratedValue( generator = "uuid2" )
	@GenericGenerator( name = "uuid2", strategy = "uuid2" )
	@Column( name = "mid", columnDefinition = "CHAR(36) NOT NULL default ''", updatable = false, nullable = false, unique = true )
	protected String mid;

	@Column( name = "company_mid", columnDefinition = "CHAR(36) NOT NULL default ''", updatable = false, nullable = false )
	protected String companyMid;

	//
	//	1	enabled
	//	0	disabled
	@Column( name = "enabled", columnDefinition = "TINYINT UNSIGNED NOT NULL DEFAULT 0" )
	protected int enabled;

	//	version lock
	//	enable optimistic locking version control
//	@Version
//	@Column(name = "version", columnDefinition = "INT DEFAULT 0", nullable = false )
//	private Long version = 0L;

	@Column( name = "status", columnDefinition = "TINYINT UNSIGNED NOT NULL DEFAULT 0" )
	protected int status;

	//	Used with column status to mark the deletion status of the record
	@Column( name = "alt", columnDefinition = "CHAR(36) NOT NULL default ''", nullable = false )
	protected String alt;

	@Column( name = "created_at", columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP", updatable = false )
	@CreationTimestamp
	protected LocalDateTime createdAt;

	@Column( name = "updated_at", columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP" )
	@UpdateTimestamp
	protected LocalDateTime updatedAt;



	public BaseEntity() throws InvalidDeException
	{
		this.id			= null;
		this.mid		= null;

		this.companyMid		= null;
		this.enabled		= 1;

		this.setStatus( EnumCommonStatus.OKAY_VALUE );
		this.alt		= "";
		//this.version		= null;
		this.createdAt		= null;
		this.updatedAt		= null;
	}


	public BigInteger getId()
	{
		return this.id;
	}
	public void setId( BigInteger id )
	{
		this.id = id;
	}


	public String getMid()
	{
		return this.mid;
	}
	public void setMid( String mid ) throws InvalidDeException
	{
		this.mid = mid;
	}
	public void setMidAsRandomUUID() throws InvalidDeException
	{
		this.setMid( UUID.randomUUID().toString() );
	}


	public String getCompanyMid()
	{
		return companyMid;
	}
	public void setCompanyMid( String companyMid ) throws InvalidDeException
	{
		this.companyMid = companyMid;
	}


	public int getEnabled()
	{
		return ( enabled > 0 ? 1 : 0 );
	}
	public void setEnabled( int enabled )
	{
		this.enabled = ( enabled > 0 ? 1 : 0 );
	}


	public int getStatus()
	{
		return this.status;
	}
	public void setStatus( int status ) throws InvalidDeException
	{
		if ( null == EnumCommonStatus.forNumber( status ) )
		{
			throw new InvalidDeException( "invalid.status" );
		}

		this.status = status;
	}


	public String getAlt()
	{
		return alt;
	}
	public void setAlt( String alt ) throws InvalidDeException
	{
		this.alt = alt;
	}


	public LocalDateTime getCreatedAt()
	{
		return this.createdAt;
	}
	public void setCreatedAt( LocalDateTime createdAt )
	{
		this.createdAt = createdAt;
	}


	public LocalDateTime getUpdatedAt()
	{
		return this.updatedAt;
	}
	public void setUpdatedAt( LocalDateTime updatedAt )
	{
		this.updatedAt = updatedAt;
	}
}