package framewise.rest.client.host;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.Map;

import org.junit.Test;

public class JsonSupportWebServiceHostResolverTests {

	@Test
	public void loadBasicConfig() throws Exception {
		JsonSupportWebServiceHostResolver resolver = new JsonSupportWebServiceHostResolver();
		WebServiceHostConfig config = resolver.loadHostConfig();

		assertThat(config, notNullValue());

		Map<String, WebServiceEnvironment> envMap = config.getEnvMap();
		assertThat(envMap.size(), is(1));

		WebServiceEnvironment env = envMap.get("dev");
		assertThat(env, notNullValue());

		WebServiceHost catalogHost = env.getHost("catalog");
		assertThat(catalogHost, notNullValue());
		
		WebServiceHost categoryHost = env.getHost("category");
		assertThat(categoryHost, notNullValue());

	}
}
