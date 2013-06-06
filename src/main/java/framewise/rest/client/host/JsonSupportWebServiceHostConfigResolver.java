package framewise.rest.client.host;

import java.io.File;
import java.io.IOException;

import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON 파일로 웹서비스 호스트 정보를 관리하는 경우에 사용하는 리졸버 구현 클래스
 * 
 * @author chanwook
 * 
 */
public class JsonSupportWebServiceHostConfigResolver extends ApplicationObjectSupport implements WebServiceHostConfigResolver {

	public static final String ENVIRONMENT_VM_ARGUMENT_KEY = "_runenv";

	public static final String DEFAULT_JSON_HOST_CONIFG_PATH = "/config/webservice-config.json";

	private ObjectMapper objectMapper = new ObjectMapper();
	private String hostConfigPath = DEFAULT_JSON_HOST_CONIFG_PATH;

	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public void setHostConfigPath(String hostConfigPath) {
		this.hostConfigPath = hostConfigPath;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see framewise.rest.client.host.WebServiceHostResolver#loadHostConfig()
	 */
	@Override
	public WebServiceHostConfig loadHostConfig() throws Exception {
		WebServiceHostConfig config = objectMapper.readValue(getJsonConfigFile(), JsonSupportWebServiceHostConfig.class);
		config.setEnvKey(getRunningEnvironmentKey());

		return config;
	}

	protected File getJsonConfigFile() throws IOException {
		ClassPathResource resource = new ClassPathResource(hostConfigPath);
		return resource.getFile();
	}

	/**
	 * 애플리케이션이 수행된 환경을 식별할 수 있는 파라미터를 조회함
	 * 
	 * @return
	 */
	protected String getRunningEnvironmentKey() {
		String property = super.getApplicationContext().getEnvironment().getProperty(ENVIRONMENT_VM_ARGUMENT_KEY);
		if (!StringUtils.hasText(property)) {
			throw new IllegalArgumentException("웹서비스 초기화 시에는 반드시 애플리케이션이 실행되는 환경의 VM Argument 값이 필요합니다! Argument name은 기본이 '"
					+ ENVIRONMENT_VM_ARGUMENT_KEY + "' 입니다.");
		}
		return property;
	}
}
