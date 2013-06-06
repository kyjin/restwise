package framewise.rest.client.meta;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.http.HttpMethod;

/**
 * 웹서비스 API의 상세 정보를 선언할 때 사용하는 애노테이션 클래스
 * 
 * @author chanwook
 * 
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebServiceMapping {

	String url();

	HttpMethod method();

	String description();

}
