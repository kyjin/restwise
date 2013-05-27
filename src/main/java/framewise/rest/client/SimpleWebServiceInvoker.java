package framewise.rest.client;

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

	@Override
	public Object invoke(WebServiceHost host, WebServiceOperation operation, MethodInvocation invocation) {
		String url = composeUrl(host, operation);
		String queryString = composeQueryString(operation, invocation);

		return null;
	}

	protected String composeQueryString(WebServiceOperation operation, MethodInvocation invocation) {

		return "";
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
