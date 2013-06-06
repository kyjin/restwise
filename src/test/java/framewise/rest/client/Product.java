package framewise.rest.client;

import java.io.Serializable;

public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6701556298547859650L;
	
	private String code;

	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(String code) {
		super();
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
