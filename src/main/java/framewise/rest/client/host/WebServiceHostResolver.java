package framewise.rest.client.host;

/**
 * 웹서비스 호출 대상(Host) 인스턴스 정보를 구성 지원
 * 
 * @author chanwook
 * 
 */
public interface WebServiceHostResolver {

	WebServiceHostConfig loadHostConfig() throws Exception;

}
