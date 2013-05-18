package framewise.rest.client.meta;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * 웹서비스의 클라이언트에게 노출하는 메타 정보를 정의하는 애노테이션
 * 
 * @author chanwook
 * 
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Webservice {

	/**
	 * 호출 대상 Host 정보
	 * 
	 * @return
	 */
	String target();

	/**
	 * 웹서비스 클라이언트의 빈 이름 지정. 별도로 지정하지 않는 경우에는 규칙에 따라 클래스명의 첫 글자를 소문자로 변경한 이름을
	 * 사용한다
	 * 
	 * @return
	 */
	String name();

}
