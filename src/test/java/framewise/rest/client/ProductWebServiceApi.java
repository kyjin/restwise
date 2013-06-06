package framewise.rest.client;

import org.springframework.http.HttpMethod;

import framewise.rest.client.Product;
import framewise.rest.client.meta.WebServiceMapping;
import framewise.rest.client.meta.Webservice;

@Webservice(name = "catalogApi", target = "catalog")
public interface ProductWebServiceApi {

	@WebServiceMapping(url = "/catalog/{arg0}", method = HttpMethod.GET, description = "find for Single Product")
	Product getProduct(String productCode);

	void notMapping();
}
