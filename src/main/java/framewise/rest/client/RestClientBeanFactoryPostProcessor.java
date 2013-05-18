package framewise.rest.client;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.util.ClassUtils;

/**
 * 웹서비스 클라인언트 API를 인식해서 빈으로 등록해주는 {@link BeanFactoryPostProcessor} 구현체
 * 
 * @author chanwook
 * 
 */
public class RestClientBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private String basePackage;

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.config.BeanFactoryPostProcessor#
	 * postProcessBeanFactory
	 * (org.springframework.beans.factory.config.ConfigurableListableBeanFactory
	 * )
	 */
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		RestClientSupportClassPathScanningCandidateComponentProvider provider = new RestClientSupportClassPathScanningCandidateComponentProvider();
		Set<BeanDefinition> candidateComponents = provider.findCandidateComponents(basePackage);

		for (BeanDefinition beanDefinition : candidateComponents) {
			Object proxyObject = createProxiedBeanObject(beanDefinition, beanFactory);
			beanFactory.registerSingleton(beanDefinition.getBeanClassName(), proxyObject);
		}
	}

	/**
	 * {@link ProxyFactory}를 사용해 실제 빈(bean)으로 등록되는 인터페이스의 객체를 생성함
	 * 
	 * @param beanDefinition
	 * @param beanFactory
	 * @return
	 */
	protected Object createProxiedBeanObject(BeanDefinition beanDefinition, ConfigurableListableBeanFactory beanFactory) {
		ProxyFactory factory = new ProxyFactory();
		RestClientProxyObject proxyObject = new RestClientProxyObject();
		factory.setTarget(proxyObject);
		factory.setInterfaces(new Class[] { resolveTargetInterface(beanDefinition.getBeanClassName(), beanFactory) });
		factory.addAdvice(new RestClientOperationMethodInterceptor());

		Object beanObject = factory.getProxy();
		if (logger.isDebugEnabled()) {
			logger.debug("Create proxy bean object(ID: " + beanObject.toString() + ") to "
					+ beanDefinition.getBeanClassName() + ".");
		}
		return beanObject;
	}

	private Class<?> resolveTargetInterface(String beanClassName, ConfigurableListableBeanFactory beanFactory) {
		try {
			Class<?> clientInterface = ClassUtils.forName(beanClassName, beanFactory.getBeanClassLoader());
			return clientInterface;
		} catch (Exception e) {
			throw new RuntimeException("thrown excpetion when initializing rest client object!", e);
		}
	}
}
