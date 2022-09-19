package com.moduretick.simplecamelspringboot;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.UseAdviceWith;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@CamelSpringBootTest
@SpringBootTest
@UseAdviceWith
public class LegacyFileRouteTest {
	
	@Autowired
	CamelContext context;
	
	@EndpointInject("mock:result")
	protected MockEndpoint mockEndpoint;
	
	@Test 
	public void testFileMove() throws Exception{
		
		// Setup the Mock
		String expectedBody = "This is input File";
		mockEndpoint.expectedBodiesReceived(expectedBody);
		mockEndpoint.expectedMinimumMessageCount(1);
		
		// Tweak the Route Definition		
		AdviceWith.adviceWith(context, "legacyFileMoveRouteId",  routeBuilder ->{
			routeBuilder.weaveByToUri("file:*").replace().to(mockEndpoint);
		});
		
		//Start the Context and Validate is Mock is Asserted
		context.start();
		mockEndpoint.assertIsSatisfied();
	}
	

}