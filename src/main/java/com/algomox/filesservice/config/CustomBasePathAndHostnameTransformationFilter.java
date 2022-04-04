package com.algomox.filesservice.config;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.servers.Server;
import springfox.documentation.oas.web.OpenApiTransformationContext;
import springfox.documentation.oas.web.WebMvcOpenApiTransformationFilter;
import springfox.documentation.spi.DocumentationType;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 1000)
public class CustomBasePathAndHostnameTransformationFilter implements WebMvcOpenApiTransformationFilter {

   	@Override
	public boolean supports(DocumentationType delimiter) {
		// TODO Auto-generated method stub
		return delimiter == DocumentationType.OAS_30;
	}
	@Override
	public OpenAPI transform(OpenApiTransformationContext<HttpServletRequest> context) {
		// TODO Auto-generated method stub
		
		OpenAPI openApi = context.getSpecification();
		context.request().ifPresent(servletRequest -> {
			// For Deployed
//			String basePath  = baseUrl;
			
			// For Local
			String basePath ="";
			final Server server = openApi.getServers().get(0);
			openApi.setServers(Collections
					.singletonList(new Server().url(server.getUrl() + basePath).description(server.getDescription())));
			final Paths paths = new Paths();
			openApi.getPaths().forEach((key, value) -> paths.addPathItem(key.replaceAll(basePath, ""), value));
			openApi.setPaths(paths);
		});
		return openApi;
	}
}