package com.ruihua.demo.database.grpcclient;

import com.ruihua.demo.database.property.GRpcProperties;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ruihua.demo.database.dataService.DataServiceGrpc;
import ruihua.demo.database.dataService.UpdateDataRequest;
import ruihua.demo.database.dataService.UpdateDataResponse;

import javax.annotation.PostConstruct;


@Component
public class RpcClientDataService
{
	private static final Logger logger = LoggerFactory.getLogger( RpcClientDataService.class );

	private DataServiceGrpc.DataServiceBlockingStub dataServiceBlockingStub;

	@Autowired
	GRpcProperties gRpcProperties;



	@PostConstruct
	private void init()
	{
		String host = gRpcProperties.getHost();
		int port = gRpcProperties.getPort();
		ManagedChannel managedChannel =
                        ManagedChannelBuilder.forAddress( host, port ).usePlaintext().build();

		dataServiceBlockingStub =
			DataServiceGrpc.newBlockingStub( managedChannel );
	}


	public UpdateDataResponse updateData
		(
			UpdateDataRequest updateDataRequest
		)
	{
		logger.info( ">>> DataService.updateData sending {}", updateDataRequest );
		UpdateDataResponse oResponse = dataServiceBlockingStub.updateData( updateDataRequest );
		logger.info( "<<< DataService.updateData received {}", oResponse );

		return oResponse;
	}
}