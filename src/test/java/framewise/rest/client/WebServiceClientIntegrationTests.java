package framewise.rest.client;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import framewise.rest.client.Product;
import framewise.rest.client.test.JettyWebServer;
import static org.hamcrest.CoreMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("testcontext.xml")
public class WebServiceClientIntegrationTests {

	@Autowired
	ProductWebServiceApi api;

	@BeforeClass
	public static void onSetUp() throws InterruptedException {
//		JettyWebServer webServer = new JettyWebServer(8090, "/config/web.xml", "/");
//		webServer.run();
	}

	@Test
	public void simpleGetWithParam() throws Exception {
		Product p = api.getProduct("p100");
		assertThat(p, notNullValue());
		assertThat(p.getCode(), is("p100"));
	}
}
