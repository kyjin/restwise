package framewise.rest.client;

import org.aopalliance.intercept.MethodInvocation;

import framewise.rest.client.host.WebServiceHost;
import framewise.rest.client.model.WebServiceOperation;

/**
 * {@link RestClientOperationMethodInterceptor}에서 구해진 파라미터를 사용해 실제 웹서비스 호출을 담당하는
 * 인터페이스
 * 
 * @author chanwook
 * 
 */
public interface WebServiceInvoker {

	Object invoke(WebServiceHost host, WebServiceOperation operation, MethodInvocation invocation);

}
