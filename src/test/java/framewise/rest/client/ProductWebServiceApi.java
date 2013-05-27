package framewise.rest.client;

import org.springframework.http.HttpMethod;

import framewise.rest.client.meta.WebServiceMapping;
import framewise.rest.client.meta.Webservice;

@Webservice(name = "catalogApi", target = "catalog")
public interface ProductWebServiceApi {

	@WebServiceMapping(url = "/product/{productCode}", method = HttpMethod.GET, description = "find for Single Product")
	Product getProduct(String productCode);

	void notMapping();
}
