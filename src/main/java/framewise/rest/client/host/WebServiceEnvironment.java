package framewise.rest.client.host;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chanwook
 * 
 */
public class WebServiceEnvironment extends ArrayList<WebServiceHost> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8859973692158841533L;

	public List<WebServiceHost> getHostList() {
		return this;
	}

	public WebServiceEnvironment addHost(WebServiceHost host) {
		this.add(host);
		return this;
	}

	/**
	 * 
	 * @param name
	 * @return name에 해당하는 {@link WebServiceHost}가 없을 경우에는 null 반환
	 */
	public WebServiceHost getHost(String name) {
		if (name == null || name.length() < 1) {
			throw new IllegalArgumentException("HOST의 이름은 키로 사용되므로, null이거나 비어있을 수 없습니다!");
		}
		for (WebServiceHost host : this) {
			if (host.getName().equals(name)) {
				return host;
			}
		}
		return null;
	}
}
