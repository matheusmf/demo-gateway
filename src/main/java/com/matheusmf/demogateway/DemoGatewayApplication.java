package com.matheusmf.demogateway;

import java.lang.management.ManagementFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableAutoConfiguration
public class DemoGatewayApplication extends SpringBootServletInitializer {
	private static final Logger logger = LoggerFactory.getLogger(DemoGatewayApplication.class);

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DemoGatewayApplication.class);
	}

	public static void main(String[] args) {
		logger.info("JVM Arguments" + ManagementFactory.getRuntimeMXBean().getInputArguments());
		SpringApplication.run(DemoGatewayApplication.class, args);
	}


}
