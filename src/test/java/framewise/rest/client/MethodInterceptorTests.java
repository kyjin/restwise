package framewise.rest.client;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.ReflectionUtils;

public class MethodInterceptorTests {

	@Test
	public void callBasicGetOperation() throws Throwable {
		// given
		RestClientOperationMethodInterceptor interceptor = new RestClientOperationMethodInterceptor();
		MethodInvocation invocation = mock(MethodInvocation.class);

		when(invocation.getMethod()).thenReturn(ReflectionUtils.findMethod(ProductWebServiceApi.class, "getProduct"));

		// when
		Object result = interceptor.invoke(invocation);

		// then
		assertThat(result, nullValue());
	}

	@Test
	public void currentNotSupportCoCOperation() throws Throwable {
		// given
		RestClientOperationMethodInterceptor interceptor = new RestClientOperationMethodInterceptor();
		MethodInvocation invocation = mock(MethodInvocation.class);

		when(invocation.getMethod()).thenReturn(ReflectionUtils.findMethod(ProductWebServiceApi.class, "notMapping"));

		try {
			interceptor.invoke(invocation);
			fail();
		} catch (UnsupportedOperationException e) {
		}
	}
}
