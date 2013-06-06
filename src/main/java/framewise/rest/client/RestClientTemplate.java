package framewise.rest.client;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * 스프링의 {@link RestTemplate}을 기초로 웹서비스 호출 연산을 지원하는 템플릿 클래스
 * 
 * @author chanwook
 * 
 */
public class RestClientTemplate {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private RestTemplate restTemplate = new RestTemplate();

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	/**
	 * GET HTTP 메서드를 사용해 HTTP 요청을 처리함
	 * 
	 * @param url
	 * @param urlVariables
	 * @param responseType
	 * @return
	 */
	public <T> T get(String url, HashMap<String, Object> urlVariables, Class<T> responseType) {
		if (logger.isDebugEnabled()) {
			logger.debug("WebService Request[URL: {}, Param: {}, ResponseType: {}]", new Object[] { url, urlVariables, responseType });
		}
		// TODO 선행처리..
		ResponseEntity<T> responseEntity = restTemplate.getForEntity(url, responseType, urlVariables);
		// TODO 후행처리..
		return responseEntity.getBody();
	}

}
