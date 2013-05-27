package framewise.rest.client.host;

import java.io.Serializable;
import java.util.Map;

/**
 * 웹서비스 호스트 정보 관리를 위한 도메인 모델
 * 
 * @author chanwook
 * 
 */
public interface WebServiceHostConfig extends Serializable {

	WebServiceHostConfig addEnvironment(String envId, WebServiceEnvironment webServiceEnvironment);

	Map<String, WebServiceEnvironment> getEnvMap();

	/**
	 * .get..(), .get..()을 통해서 네비게이션하는 것이 아니라 파라미터를 동시에 받아서
	 * {@link WebServiceHost}를 반환하는 편의 메서드
	 * 
	 * @param envKey
	 * @param hostName
	 * @return
	 */
	WebServiceHost getHostConfig(String envKey, String hostName);

}
