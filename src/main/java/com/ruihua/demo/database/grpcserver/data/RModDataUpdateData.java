package com.ruihua.demo.database.grpcserver.data;

import com.ruihua.demo.database.grpcserver.helpers.HelloUtils;
import com.ruihua.demo.database.model.rest.RestResponse;
import com.ruihua.demo.database.model.rest.company.RestPostCompanyForm;
import com.ruihua.demo.database.model.rest.company.RestPostCompanyResponse;
import com.ruihua.demo.database.services.RepoServices.CompanyRepoService;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ruihua.demo.database.dataService.UpdateDataRequest;
import ruihua.demo.database.dataService.UpdateDataResponse;
import ruihua.demo.database.common.CommonResponse;
import ruihua.demo.database.common.EnumRpcCode;


@Component
public class RModDataUpdateData
{
	private static final Logger logger = LoggerFactory.getLogger( RModDataUpdateData.class );

	@Autowired
	CompanyRepoService companyRepoService;


	public void updateData( UpdateDataRequest request, StreamObserver<UpdateDataResponse> response )
	{
		try
		{
			RestPostCompanyForm restPostCompanyForm = new RestPostCompanyForm();
			restPostCompanyForm.setCompanyName( String.format( "Company(%d)", System.currentTimeMillis() ) );
			restPostCompanyForm.setCompanyDesc( "Company Desc" );
			restPostCompanyForm.setCompanyAddress( "Company Address" );
			restPostCompanyForm.setCompanyContacts( "Company Contacts" );

			RestResponse<RestPostCompanyResponse> createResponse =
				companyRepoService.createCompany( restPostCompanyForm );

			//
			//	response
			//
			UpdateDataResponse oRes = UpdateDataResponse.newBuilder()
				.setHeader( CommonResponse.newBuilder()
					.setCode( EnumRpcCode.RPC_SUCCESSFUL )
					.build() )
				.setSuccess( null != createResponse )
				.build();

			logger.info( "))) successfully, responded {}", oRes );
			response.onNext( oRes );
			response.onCompleted();
		}
//		catch ( InvalidApRpcException e )
//		{
//			e.printStackTrace();
//			logger.error( "### exception: {}", e.getMessage() );
//			InternalUtils.responseGetUserMidError( response, null, e.getMessage() );
//		}
		catch ( Exception e )
		{
			e.printStackTrace();
			HelloUtils.responseUpdateDataError( response, new Object(){}, "exception" );
		}
	}
}
