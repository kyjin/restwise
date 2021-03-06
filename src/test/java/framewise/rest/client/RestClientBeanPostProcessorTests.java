package framewise.rest.client;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import framewise.rest.client.Product;


@ContextConfiguration("testcontext.xml")
public class RestClientBeanPostProcessorTests extends AbstractJUnit4SpringContextTests {

	@Test
	public void createClientBean() throws Exception {
		assertThat(applicationContext.getBean(ProductWebServiceApi.class), notNullValue());
	}

	@Test
	public void simpleCall() throws Exception {
		ProductWebServiceApi api = applicationContext.getBean(ProductWebServiceApi.class);
		Product p = api.getProduct("prd100");

		assertThat(p, notNullValue());
	}
}
