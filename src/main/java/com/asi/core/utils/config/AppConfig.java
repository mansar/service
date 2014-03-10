package com.asi.core.utils.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

//@Configuration
//@Profile("default")
//@PropertySource({"classpath:dev-environment.properties","classpath:velocity-import.properties"})
public class AppConfig{
	@Autowired
	private static Environment environment;

	public static String getProertyValue(String key)
	{
		return environment.getProperty(key);
	}
/*	@Bean
	public static PropertySourcesPlaceholderConfigurer properties(){
	   PropertySourcesPlaceholderConfigurer pspc =
	      new PropertySourcesPlaceholderConfigurer();
	   Resource[] resources = new ClassPathResource[ ]
	      { new ClassPathResource( "dev-environment.properties" ), new ClassPathResource( "velocity-import.properties" )};
	  pspc.setLocations( resources );
	  pspc.setIgnoreUnresolvablePlaceholders( true );
	  return pspc;
	}*/	
	
}
