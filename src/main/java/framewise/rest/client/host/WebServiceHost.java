package framewise.rest.client.host;

import java.io.Serializable;

/**
 * @author chanwook
 * 
 */
public class WebServiceHost implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8376369020999888020L;

	private String name;

	private String ip;

	private int port;

	private String context;

	public WebServiceHost() {
	}

	public WebServiceHost(String name, String ip, int port, String context) {
		super();
		this.name = name;
		this.ip = ip;
		this.port = port;
		this.context = context;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

}
