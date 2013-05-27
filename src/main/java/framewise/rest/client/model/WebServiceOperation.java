package framewise.rest.client.model;

import org.springframework.http.HttpMethod;

/**
 * 호출 대상 웹서비스 연산에 대한 정보를 관리
 * 
 * @author chanwook
 * 
 */
public class WebServiceOperation {

	private String operationPath;

	private HttpMethod method;

	public WebServiceOperation() {
	}

	public WebServiceOperation(String operationPath, HttpMethod method) {
		super();
		this.operationPath = operationPath;
		this.method = method;
	}

	public void setOperationPath(String operationPath) {
		this.operationPath = operationPath;
	}

	public String getOperationPath() {
		return operationPath;
	}

	public void setMethod(HttpMethod method) {
		this.method = method;
	}

	public HttpMethod getMethod() {
		return method;
	}
}
