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
		if (envKey == null || hostName == null) {
			throw new IllegalArgumentException("웹서비스 호스트 설정을 조회하는 데 파라미터(envKey or hostName)가 비어있습니다. 값을 다시 확인해주세요");
		}
		WebServiceEnvironment env = envMap.get(envKey);
		if (env != null) {
			WebServiceHost host = env.getHost(hostName);
			return host;
		}
		return null;
	}
}
