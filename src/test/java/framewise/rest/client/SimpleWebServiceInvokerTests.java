package framewise.rest.client;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import framewise.rest.client.host.WebServiceHost;
import framewise.rest.client.model.WebServiceOperation;

public class SimpleWebServiceInvokerTests {

	@Test
	public void composeUrl() throws Exception {
		SimpleWebServiceInvoker invoker = new SimpleWebServiceInvoker();
		String url = invoker.composeUrl(new WebServiceHost("catalog", "10.10.10.10", 8080, "catalog"), new WebServiceOperation("/test",
				null));

		assertThat(url, is("http://10.10.10.10:8080/catalog/test"));
	}
}
