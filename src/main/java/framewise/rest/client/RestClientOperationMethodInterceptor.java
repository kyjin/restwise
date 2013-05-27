package framewise.rest.client;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.core.env.Environment;

import framewise.rest.client.host.WebServiceHost;
import framewise.rest.client.host.WebServiceHostConfig;
import framewise.rest.client.meta.WebServiceMapping;
import framewise.rest.client.model.WebServiceOperation;

/**
 * 실제 프록시 객체의 메서드 호출 시에 요청을 가로채어 처리하는 객체
 * 
 * @author chanwook
 * 
 */
public class RestClientOperationMethodInterceptor implements MethodInterceptor {

	private static final String ENVIRONMENT_VM_ARGUMENT_KEY = "_runenv";
	private RestClientTemplate clientTemplate = new RestClientTemplate();
	private WebServiceHostConfig hostConfig;

	private WebServiceInvoker invoker = new SimpleWebServiceInvoker();

	private Environment environment;

	/**
	 * 기본 생성자
	 */
	public RestClientOperationMethodInterceptor() {
	}

	/**
	 * WebServiceHostConfig를 파라미터로 받는 생성자
	 * 
	 * @param hostConfig
	 */
	public RestClientOperationMethodInterceptor(WebServiceHostConfig hostConfig) {
		this.hostConfig = hostConfig;
	}

	public void setClientTemplate(RestClientTemplate clientTemplate) {
		this.clientTemplate = clientTemplate;
	}

	public RestClientTemplate getClientTemplate() {
		return clientTemplate;
	}

	public void setHostConfig(WebServiceHostConfig hostConfig) {
		this.hostConfig = hostConfig;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		// First, resolve to target method information
		WebServiceOperation operation = resolveOperationInformation(invocation.getMethod());

		WebServiceHost host = hostConfig.getHostConfig(getRunningEnvironmentKey(), resolveHostName(invocation));

		Object result = invoker.invoke(host, operation, invocation);
		return result;
	}

	protected String resolveHostName(MethodInvocation invocation) {

		return null;
	}

	private String getRunningEnvironmentKey() {
		return environment.getProperty(ENVIRONMENT_VM_ARGUMENT_KEY);
	}

	/**
	 * 웹서비스 연산 실행을 위해 {@link Method}를 분석해 호출 정보를 찾아낸다
	 * 
	 * @param method
	 * @return
	 */
	protected WebServiceOperation resolveOperationInformation(Method method) {
		WebServiceMapping mapping = method.getAnnotation(WebServiceMapping.class);
		if (mapping == null) {
			throw new UnsupportedOperationException("현재는 @WebServiceMapping을 반드시 사용해야 합니다. 향후 Convention으로 지원 예정..");
		}
		WebServiceOperation operation = new WebServiceOperation();
		operation.setOperationPath(mapping.url());
		operation.setMethod(mapping.method());
		return operation;
	}

}
