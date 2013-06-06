package framewise.rest.client.host;

/**
 * 웹서비스 호출 대상(Host) 인스턴스 정보를 구성 지원
 * 
 * @author chanwook
 * 
 */
public interface WebServiceHostConfigResolver {

	/**
	 * 웹서비스의 타겟 호출 정보 초기화 함수
	 * 
	 * @return
	 * @throws Exception
	 */
	WebServiceHostConfig loadHostConfig() throws Exception;

}
