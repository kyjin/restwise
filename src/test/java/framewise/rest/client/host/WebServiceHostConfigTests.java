package framewise.rest.client.host;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import framewise.rest.client.host.JsonSupportWebServiceHostConfig;
import framewise.rest.client.host.WebServiceEnvironment;
import framewise.rest.client.host.WebServiceHost;
import framewise.rest.client.host.WebServiceHostConfig;

public class WebServiceHostConfigTests {

	@Test
	public void createJsonWebServiceConfigFile() throws Exception {
		WebServiceHostConfig json = new JsonSupportWebServiceHostConfig();
		WebServiceEnvironment env = new WebServiceEnvironment();
		WebServiceHost host1 = new WebServiceHost("catalog", "localhost", 8080, "catalog-api");
		WebServiceHost host2 = new WebServiceHost("category", "localhost", 8090, "category-api");

		env.addHost(host1).addHost(host2);
		json.addEnvironment("dev", env);

		ObjectMapper mapper = new ObjectMapper();
		String value = mapper.writeValueAsString(json);

		System.out.println("Confirm generated JSON: " + value);
	}
}
