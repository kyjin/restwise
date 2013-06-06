package framewise.rest.client;

import java.util.HashMap;

import org.aopalliance.intercept.MethodInvocation;

import framewise.rest.client.host.WebServiceHost;
import framewise.rest.client.model.WebServiceOperation;

/**
 * {@link WebServiceInvoker} 기본 구현체
 * 
 * @author chanwook
 * 
 */
public class SimpleWebServiceInvoker implements WebServiceInvoker {

	private static final String DEFAULT_QUERY_STRING_PREFIX = "arg";

	private RestClientTemplate clientTemplate = new RestClientTemplate();
	private String queryStringPrefix = DEFAULT_QUERY_STRING_PREFIX;

	public void setClientTemplate(RestClientTemplate clientTemplate) {
		this.clientTemplate = clientTemplate;
	}

	public void setQueryStringPrefix(String queryStringPrefix) {
		this.queryStringPrefix = queryStringPrefix;
	}

	@Override
	public Object invoke(WebServiceHost host, WebServiceOperation operation, MethodInvocation invocation) {
		String url = composeUrl(host, operation);
		HashMap<String, Object> queryString = composeQueryStringMap(operation, invocation);

		// TODO 리턴하는 CLASS 타입을 직접 지정할 수 있도록 기능 보완
		Object returnValue = clientTemplate.get(url, queryString, invocation.getMethod().getReturnType());
		return returnValue;
	}

	protected HashMap<String, Object> composeQueryStringMap(WebServiceOperation operation, MethodInvocation invocation) {
		HashMap<String, Object> queryStringMap = new HashMap<String, Object>();
		Object[] arguments = invocation.getArguments();
		for (int i = 0; i < arguments.length; i++) {
			queryStringMap.put(queryStringPrefix + i, arguments[i]);
		}
		return queryStringMap;
	}

	protected String composeUrl(WebServiceHost host, WebServiceOperation operation) {
		StringBuilder url = new StringBuilder();
		url.append("http://");
		url.append(host.getIp());
		url.append(":");
		url.append(host.getPort());
		url.append("/");
		url.append(host.getContext());
		if (!operation.getOperationPath().startsWith("/")) {
			url.append("/");
		}
		// TODO path에 들어있는 경로 유형에 따라 처리 지원
		url.append(operation.getOperationPath());

		// TODO URL Template 변수({}로 감싸져 있는) 처리 추가

		return url.toString();
	}

}
