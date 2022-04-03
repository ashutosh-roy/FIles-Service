package com.algomox.filesservice.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AppMvcInterceptor implements HandlerInterceptor {
	
	private static final Logger LOG = LoggerFactory.getLogger(AppMvcInterceptor.class);
	public static final String TRACE_ID = "TRACE_ID";

	/**
	 * The Constant SERVICE_NAME.
	 */
	public static final String SERVICE_NAME = "SERVICE_NAME";

	/**
	 * The Constant ENVIRONMENT.
	 */
	public static final String ENVIRONMENT = "ENVIRONMENT";
	public static final String TRIGGERED =" api triggered";
	public static final String API_TRIGGER_COMPLETED =" api trigger comepleted";
	/**
	 * The SERVICE_NAME.
	 */
	@Value("${spring.application.name}")
	private String serviceName;

	/**
	 * The ENVIRONMENT.
	 */
	
	@Value("${spring.profiles.active}")
	private String environment;
	
	
	public AppMvcInterceptor() {
		MDC.put("TRACE_ID", UUID.randomUUID().toString());
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String traceID = null;
		if (request.getHeader(TRACE_ID) != null && !request.getHeader(TRACE_ID).isEmpty()) {
			traceID = request.getHeader(TRACE_ID);
		} else {
			traceID = UUID.randomUUID().toString();
		}
		MDC.put(TRACE_ID, traceID);
		MDC.put(ENVIRONMENT, environment);
		MDC.put(SERVICE_NAME, serviceName);
		LOG.info(request.getRequestURI()+TRIGGERED);
		return true;
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception 
	{
		MDC.clear();
		LOG.info(request.getRequestURI()+API_TRIGGER_COMPLETED);
	}

	public String getTranactionId() {
		return null;
	}
}
