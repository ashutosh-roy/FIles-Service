package com.algomox.filesservice.config;
import java.util.Collection;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class FilesServiceSwaggerConfig {    
	private Collection<VendorExtension> vendorExtension = Collections.emptyList();
	@Bean
	public Docket api() { 
		return new Docket(DocumentationType.OAS_30)  
				.select()                                  
				.apis(RequestHandlerSelectors.basePackage("com.algomox.filesservice"))             
				.paths(PathSelectors.any())                          
				.build()
				.apiInfo(metaData());                                           
	}

	private ApiInfo metaData() {
       return new ApiInfo("Files Service", "All end points and documentation related to Files Service API ", "1.0", "Terms of service",
               new Contact("", "Enter the Anthem URL", "ashutosh.roy@anthem.com"), "", "",  vendorExtension);
   }
}