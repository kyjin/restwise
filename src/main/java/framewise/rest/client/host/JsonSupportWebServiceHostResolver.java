package framewise.rest.client.host;

import java.io.File;
import java.io.IOException;

import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON 파일로 웹서비스 호스트 정보를 관리하는 경우에 사용하는 리졸버 구현 클래스
 * 
 * @author chanwook
 * 
 */
public class JsonSupportWebServiceHostResolver implements WebServiceHostResolver {

	public static final String DEFAULT_JSON_HOST_CONIFG_PATH = "/config/webservice-config.json";

	private ObjectMapper objectMapper = new ObjectMapper();
	private String hostConfigPath = DEFAULT_JSON_HOST_CONIFG_PATH;

	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public void setHostConfigPath(String hostConfigPath) {
		this.hostConfigPath = hostConfigPath;
	}

	@Override
	public WebServiceHostConfig loadHostConfig() throws Exception {
		WebServiceHostConfig config = objectMapper.readValue(getJsonConfigFile(), JsonSupportWebServiceHostConfig.class);
		return config;
	}

	protected File getJsonConfigFile() throws IOException {
		ClassPathResource resource = new ClassPathResource(hostConfigPath);
		return resource.getFile();
	}

}
