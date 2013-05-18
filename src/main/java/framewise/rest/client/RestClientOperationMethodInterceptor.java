package framewise.rest.client;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 실제 프록시 객체의 메서드 호출 시에 요청을 가로채어 처리하는 객체
 * 
 * @author chanwook
 * 
 */
public class RestClientOperationMethodInterceptor implements MethodInterceptor {

	private RestClientTemplate clientTemplate = new RestClientTemplate();

	public RestClientOperationMethodInterceptor() {
	}

	public void setClientTemplate(RestClientTemplate clientTemplate) {
		this.clientTemplate = clientTemplate;
	}

	public RestClientTemplate getClientTemplate() {
		return clientTemplate;
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		// 요청 정보를 분석해서 REST 클라이언트 요청을 처리해야 함
		return null;
	}

}
