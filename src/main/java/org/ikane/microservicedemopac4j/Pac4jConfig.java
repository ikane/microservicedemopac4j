package org.ikane.microservicedemopac4j;

import org.pac4j.cas.client.CasClient;
import org.pac4j.core.authorization.RequireAnyRoleAuthorizer;
import org.pac4j.core.client.Clients;
import org.pac4j.core.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Pac4jConfig {

	@Bean
	public Config config() {
		
		 // CAS
        //final CasClient casClient = new CasClient("https://casserverpac4j.herokuapp.com/login");
		
		final CasClient casClient = new CasClient();
        casClient.setCasLoginUrl("https://gucibrahimak1.verifone.com:8443/cas/login");
        //casClient.setCasLoginUrl("https://casserverpac4j.herokuapp.com/login");
        
        final Clients clients = new Clients("http://gucibrahimak1.verifone.com:8890/microservicedemopac4j/callback", casClient);
        
        final Config config = new Config(clients);
        config.addAuthorizer("admin", new RequireAnyRoleAuthorizer("ROLE_ADMIN"));
        config.addAuthorizer("reader", new RequireAnyRoleAuthorizer("ROLE_READER"));
        //config.addAuthorizer("custom", new CustomAuthorizer());
        return config;
	}
}
