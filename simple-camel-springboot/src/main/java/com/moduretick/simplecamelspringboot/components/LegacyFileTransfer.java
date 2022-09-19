package com.moduretick.simplecamelspringboot.components;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class LegacyFileTransfer extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("file:src/data/input?fileName=inputFile.txt")
		.routeId("legacyFileMoveRouteId")
		.to("file:src/data/output?fileName=outputFile.txt");
		
	}

}
