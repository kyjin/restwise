package framewise.rest.client.host;

import java.util.HashMap;
import java.util.Map;

/**
 * JSON 파일 형식을 지원하는 구현체
 * 
 * @author chanwook
 * 
 */
public class JsonSupportWebServiceHostConfig implements WebServiceHostConfig {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2882875196551374820L;

	private Map<String, WebServiceEnvironment> envMap = new HashMap<String, WebServiceEnvironment>();

	/**
	 * 컨피그 초기화 시에 로딩되는 실행 환경 키값. 별도로 키값을 지정해 호스트 정보를 조회하지 않는다면 기본값으로 적용 됨.
	 */
	private String envKey;

	@Override
	public void setEnvKey(String envKey) {
		this.envKey = envKey;
	}

	public void setEnvMap(Map<String, WebServiceEnvironment> envMap) {
		this.envMap = envMap;
	}

	@Override
	public Map<String, WebServiceEnvironment> getEnvMap() {
		return envMap;
	}

	@Override
	public WebServiceHostConfig addEnvironment(String envId, WebServiceEnvironment webServiceEnvironment) {
		this.envMap.put(envId, webServiceEnvironment);
		return this;
	}

	@Override
	public WebServiceHost getHostConfig(String envKey, String hostName) {
		if (envKey == null) {
			throw new IllegalArgumentException("웹서비스 호스트 설정을 조회하는 데 파라미터(envKey)가 비어있습니다. 값을 다시 확인해주세요");
		}
		if (hostName == null) {
			throw new IllegalArgumentException("웹서비스 호스트 설정을 조회하는 데 파라미터(hostName)가 비어있습니다. 값을 다시 확인해주세요");
		}
		WebServiceEnvironment env = envMap.get(envKey);
		if (env != null) {
			WebServiceHost host = env.getHost(hostName);
			return host;
		}
		return null;
	}

	@Override
	public WebServiceHost getHostConfig(String hostName) {
		return this.getHostConfig(envKey, hostName);
	}

}
