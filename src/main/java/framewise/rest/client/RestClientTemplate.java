package framewise.rest.client;

import org.springframework.web.client.RestTemplate;

/**
 * 스프링의 {@link RestTemplate}을 기초로 웹서비스 호출 연산을 지원하는 템플릿 클래스
 * 
 * @author chanwook
 * 
 */
public class RestClientTemplate {

	private RestTemplate restTemplate = new RestTemplate();

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

}
