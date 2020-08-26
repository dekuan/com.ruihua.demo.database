package com.ruihua.demo.database.services.RepoServices;

import com.ruihua.demo.database.entities.mysql.CompanyTable;
import com.ruihua.demo.database.exceptions.HttpBadRequestException;
import com.ruihua.demo.database.exceptions.HttpForbiddenException;
import com.ruihua.demo.database.exceptions.HttpNotFoundException;
import com.ruihua.demo.database.exceptions.InvalidDeException;
import com.ruihua.demo.database.model.rest.RestResponse;
import com.ruihua.demo.database.model.rest.company.RestCompanyInfo;
import com.ruihua.demo.database.model.rest.company.RestPostCompanyForm;
import com.ruihua.demo.database.model.rest.company.RestPostCompanyResponse;
import com.ruihua.demo.database.repositories.mysql.CompanyTableRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ruihua.demo.database.common.EnumCommonStatus;
import ruihua.demo.database.common.EnumRpcCode;

import javax.annotation.Resource;
import javax.transaction.Transactional;


@Service
public class CompanyRepoService implements CompanyRepo
{
	private static final Logger logger = LoggerFactory.getLogger( CompanyRepoService.class );

	@Resource
	CompanyTableRepository companyRepo;



	public RestResponse<RestCompanyInfo> getCompanyInfo
		(
			String mid
		) throws InvalidDeException
	{
		if ( StringUtils.isBlank( mid ) )
		{
			throw new HttpBadRequestException( "invalid.mid" );
		}

		RestResponse<RestCompanyInfo> res = new RestResponse<RestCompanyInfo>();
		CompanyTable companyTable = companyRepo.findFirstByMid( mid );
		if ( null == companyTable )
		{
			throw new HttpNotFoundException( "not-found" );
		}
		if ( 0 == companyTable.getEnabled() )
		{
			throw new HttpForbiddenException( "disabled" );
		}

		//	...
		RestCompanyInfo restCompanyInfo = new RestCompanyInfo();
		restCompanyInfo.setMid( companyTable.getMid() );
		restCompanyInfo.setCompanyMid( companyTable.getMid() );
		restCompanyInfo.setCompanyName( companyTable.getCompanyName() );
		restCompanyInfo.setCompanyAddress( companyTable.getCompanyAddress() );
		restCompanyInfo.setCompanyContacts( companyTable.getCompanyContacts() );
		restCompanyInfo.setCompanyDesc( companyTable.getCompanyDesc() );
		restCompanyInfo.setCreatedAt( companyTable.getCreatedAt() );
		restCompanyInfo.setUpdatedAt( companyTable.getUpdatedAt() );
		res.setCode( EnumRpcCode.RPC_SUCCESSFUL );
		res.setBody( restCompanyInfo );

		return res;
	}


	/**
	 *	create company
	 *
	 *	@param	restPostCompanyForm	-
	 *	@return	RestResponse<RestPostCompanyResponse>
	 *	@throws InvalidDeException	-
	 */
	@Transactional( rollbackOn = Exception.class )
	public RestResponse<RestPostCompanyResponse> createCompany
		(
			RestPostCompanyForm restPostCompanyForm
		) throws InvalidDeException
	{
		//	...
		RestResponse<RestPostCompanyResponse> res = new RestResponse<RestPostCompanyResponse>();
		res.setCode( EnumRpcCode.RPC_FAILED );

		//
		//	create new company
		//
		CompanyTable companyNew = companyRepo.saveAndFlush( new CompanyTable
			(
				restPostCompanyForm.getCompanyName(),
				restPostCompanyForm.getCompanyAddress(),
				restPostCompanyForm.getCompanyContacts(),
				restPostCompanyForm.getCompanyDesc()
			) );
		if ( StringUtils.isBlank( companyNew.getMid() ) )
		{
			throw new InvalidDeException( "failed.create-company" );
		}

		//	...
		res.setCode( EnumRpcCode.RPC_SUCCESSFUL );
		res.setError( "" );
		res.setBody( (RestPostCompanyResponse)( new RestPostCompanyResponse().setMid( companyNew.getMid() ) ) );

		return res;
	}


	/**
	 * 	create or update company
	 *
	 *	@param	restPostCompanyForm	-
	 *	@return	RestResponse<RestPostCompanyResponse>
	 *	@throws InvalidDeException	-
	 */
	@Transactional( rollbackOn = Exception.class )
	public RestResponse<RestPostCompanyResponse> createOrUpdateCompany
		(
			RestPostCompanyForm restPostCompanyForm
		) throws InvalidDeException
	{
		if ( null == restPostCompanyForm )
		{
			throw new HttpBadRequestException( "invalid.form" );
		}
		if ( StringUtils.isEmpty( restPostCompanyForm.getMid() ) )
		{
			//
			//	empty mid,
			//	try to create
			//
			return this.createCompany( restPostCompanyForm );
		}

		//	...
		RestResponse<RestPostCompanyResponse> res = new RestResponse<RestPostCompanyResponse>();
		res.setCode( EnumRpcCode.RPC_FAILED );

		CompanyTable companyFind = companyRepo.findFirstByStatusAndMid
			(
				EnumCommonStatus.OKAY_VALUE,
				restPostCompanyForm.getMid()
			);
		int effect = companyRepo.updateCompanyInfo
			(
				companyFind.getMid(),
				restPostCompanyForm.getCompanyName(),
				(
					StringUtils.isNotBlank( restPostCompanyForm.getCompanyAddress() ) ?
					restPostCompanyForm.getCompanyAddress() : companyFind.getCompanyAddress()
				),
				restPostCompanyForm.getCompanyContacts(),
				(
					StringUtils.isNotBlank( restPostCompanyForm.getCompanyDesc() ) ?
					restPostCompanyForm.getCompanyDesc() : companyFind.getCompanyDesc()
				)
			);
		if ( effect > 0 )
		{
			res.setCode( EnumRpcCode.RPC_SUCCESSFUL );
			res.setError( "" );
			res.setBody( (RestPostCompanyResponse)( new RestPostCompanyResponse().setMid( companyFind.getMid() ) ) );
		}
		else
		{
			res.setCode( EnumRpcCode.RPC_FAILED );
			res.setError( "failed.update" );
		}

		return res;
	}
}