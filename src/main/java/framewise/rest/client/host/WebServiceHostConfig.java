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

	/**
	 * envKey(실행환경변수)를 건네지 않으면 초기화 시에 결정된 환경으로 수행 됨
	 * 
	 * @param resolveHostName
	 * @return
	 */
	WebServiceHost getHostConfig(String hostName);

	/**
	 * 실행되는 애플리케이션의 환경변수 값을 설정함
	 * 
	 * @param envKey
	 */
	void setEnvKey(String envKey);

}
