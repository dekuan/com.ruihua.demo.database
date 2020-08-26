package com.ruihua.demo.database.grpcserver.data;

import com.ruihua.demo.database.grpcserver.helpers.HelloUtils;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ruihua.demo.database.dataService.DataServiceGrpc;
import ruihua.demo.database.dataService.UpdateDataRequest;
import ruihua.demo.database.dataService.UpdateDataResponse;


@GRpcService
public class GRpcDataServer extends DataServiceGrpc.DataServiceImplBase
{
	private static final Logger logger = LoggerFactory.getLogger( GRpcDataServer.class );

	@Autowired
	RModDataUpdateData rModDataUpdateData;


	@Override
	public void updateData( UpdateDataRequest request, StreamObserver<UpdateDataResponse> responseObserver )
	{
		logger.info( "))) received request {}", request );
		if ( null == request )
		{
			HelloUtils.responseUpdateDataError( responseObserver, new Object(){}, "null.request" );
			return;
		}

		//	...
		rModDataUpdateData.updateData( request, responseObserver );
	}
}