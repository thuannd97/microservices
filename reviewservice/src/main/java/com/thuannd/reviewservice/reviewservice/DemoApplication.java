package com.thuannd.reviewservice.reviewservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.thuannd")
public class DemoApplication {

	public static final Logger LOG = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(DemoApplication.class, args);

		String datasourceUrl = ctx.getEnvironment().getProperty("spring.datasource.url");
		LOG.info("Connected to MySQL url {}: ", datasourceUrl );
	}

}
