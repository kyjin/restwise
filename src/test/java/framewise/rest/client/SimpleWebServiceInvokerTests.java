package framewise.rest.client;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;

import org.aopalliance.intercept.MethodInvocation;
import org.junit.Test;

import framewise.rest.client.SimpleWebServiceInvoker;
import framewise.rest.client.host.WebServiceHost;
import framewise.rest.client.model.WebServiceOperation;

public class SimpleWebServiceInvokerTests {

	WebServiceHost host = new WebServiceHost("catalog", "10.10.10.10", 8080, "catalog");
	WebServiceOperation operation = new WebServiceOperation("/test", null);

	@Test
	public void composeUrl() throws Exception {
		SimpleWebServiceInvoker invoker = new SimpleWebServiceInvoker();
		String url = invoker.composeUrl(host, operation);

		assertThat(url, is("http://10.10.10.10:8080/catalog/test"));
	}

	@Test
	public void sendWithUrlVariables() throws Exception {
		SimpleWebServiceInvoker invoker = new SimpleWebServiceInvoker();
		MethodInvocation invocation = mock(MethodInvocation.class);
		Product prd = new Product();
		when(invocation.getArguments()).thenReturn(new Object[] { 123, "abc", prd });

		HashMap<String, Object> qsm = invoker.composeQueryStringMap(operation, invocation);
		assertNotNull(qsm);
		assertThat(qsm.size(), is(3));
		assertThat(123, is(qsm.get("arg0")));
		assertThat("abc", is(qsm.get("arg1")));
		assertThat(prd, is(qsm.get("arg2")));

	}

	public static class Product {

	}
}
