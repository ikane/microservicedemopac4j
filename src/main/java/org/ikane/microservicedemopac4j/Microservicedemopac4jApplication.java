package org.ikane.microservicedemopac4j;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

@SpringBootApplication
public class Microservicedemopac4jApplication {
	
	static final Logger logger = LoggerFactory.getLogger(Microservicedemopac4jApplication.class);

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Microservicedemopac4jApplication.class, args);
		
		Environment env = context.getEnvironment();
		
		logger.info("\n\thttp://{}:{}{}\n\tProfiles:{}\n\tServerAddress:{}\n", 
				env.getProperty("server.address"), 
				env.getProperty("server.port"), 
				env.getProperty("server.context-path"),
				Arrays.toString(env.getActiveProfiles()),
				env.getProperty("server.address")
				);
	}
}
