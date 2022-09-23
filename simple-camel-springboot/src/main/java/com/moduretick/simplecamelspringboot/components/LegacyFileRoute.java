package com.moduretick.simplecamelspringboot.components;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LegacyFileRoute extends RouteBuilder{
	
	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void configure() throws Exception {
		from("file:src/data/input?fileName=inputFile.txt")
		.routeId("legacyFileMoveRouteId")
		.process(exchange ->{
			String fileData = exchange.getIn().getBody(String.class);
			logger.info("This is the read fileData: " + fileData);
		})
		.to("file:src/data/output?fileName=outputFile.txt");
		
	}

}
