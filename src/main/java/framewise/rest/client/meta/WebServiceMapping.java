package framewise.rest.client.meta;

import org.springframework.http.HttpMethod;

public @interface WebServiceMapping {

	String url();

	HttpMethod method();

	String description();

}
