package com.ruihua.demo.database;

import com.ruihua.demo.database.grpcclient.RpcClientDataService;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ruihua.demo.database.common.EnumRpcCode;
import ruihua.demo.database.dataService.UpdateDataRequest;
import ruihua.demo.database.dataService.UpdateDataResponse;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RpcClientDataServiceTest
{
	@Autowired
	private RpcClientDataService rpcClientDataService;



	@BeforeEach
	void setUp()
	{
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	public void testGetGreetingCase1()
	{
		UpdateDataRequest getGreetingRequest =
			UpdateDataRequest.newBuilder()
				.build();

		UpdateDataResponse getGreetingResponse = rpcClientDataService.updateData
			(
				getGreetingRequest
			);
		assertThat( getGreetingResponse ).isNotNull();
		assertThat( getGreetingResponse.getHeader().getCode() ).isEqualTo( EnumRpcCode.RPC_SUCCESSFUL );
		assertThat( getGreetingResponse.getMid() ).isNotNull();
		assertThat( getGreetingResponse.getMid() ).isNotEmpty();
	}
}