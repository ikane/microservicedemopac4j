package org.ikane.microservicedemopac4j;

import org.pac4j.core.config.Config;
import org.pac4j.springframework.web.RequiresAuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan(basePackages = "org.pac4j.springframework.web")
public class SecurityConfig extends WebMvcConfigurerAdapter {

	
	@Autowired
    private Config config;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		//registry.addInterceptor(new RequiresAuthenticationInterceptor(config, "CasClient")).addPathPatterns("/*");
		registry.addInterceptor(new RequiresAuthenticationInterceptor(config, "CasClient")).addPathPatterns("/cas/*");
        registry.addInterceptor(new RequiresAuthenticationInterceptor(config)).addPathPatterns("/protected/*");
	}
}
