package com.ruihua.demo.database.grpcserver.helpers;

import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ruihua.demo.database.common.CommonResponse;
import ruihua.demo.database.dataService.UpdateDataResponse;


public class HelloUtils
{
	private static final Logger logger = LoggerFactory.getLogger( HelloUtils.class );


	/**
	 *	@param	responseObserver	-
	 *	@param	sError			-
	 */
	public static void responseUpdateDataError(
		StreamObserver<UpdateDataResponse> responseObserver,
		Object oObjectMethod,
		String sError )
	{
		logger.error( "### {}", sError );
		UpdateDataResponse oRes = UpdateDataResponse.newBuilder()
			.setHeader( CommonResponse.newBuilder()
				.setError( sError )
				.build() )
			.build();
		responseObserver.onNext( oRes );
		responseObserver.onCompleted();
	}
}