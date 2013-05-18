package framewise.rest.client;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import framewise.rest.client.meta.Webservice;

/**
 * {@link Webservice}로 선언된 인터페이스를 식별하기 위한
 * {@link ClassPathScanningCandidateComponentProvider} 확장 클래스. 인터페이스 타입을 식별하지
 * 않도록 되어 있는 로직을 변경함.
 * 
 * @author chanwook
 * 
 */
public class RestClientSupportClassPathScanningCandidateComponentProvider extends
		ClassPathScanningCandidateComponentProvider {

	public RestClientSupportClassPathScanningCandidateComponentProvider() {
		super(false);
		addIncludeFilter(new AnnotationTypeFilter(Webservice.class, true, true));
	}

	@Override
	protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
		return beanDefinition.getMetadata().isIndependent();
	}

}
