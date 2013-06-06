package framewise.rest.client;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import framewise.rest.client.host.WebServiceHostConfig;
import framewise.rest.client.host.WebServiceHostConfigResolver;
import framewise.rest.client.meta.Webservice;

/**
 * 웹서비스 클라인언트 API를 인식해서 빈으로 등록해주는 {@link BeanFactoryPostProcessor} 구현체
 * 
 * @author chanwook
 * 
 */
public class RestClientBeanFactoryPostProcessor extends ApplicationObjectSupport implements BeanFactoryPostProcessor {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private String basePackage;

	private WebServiceHostConfigResolver hostConfigResolver;

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}

	public void setHostConfigResolver(WebServiceHostConfigResolver hostConfigResolver) {
		this.hostConfigResolver = hostConfigResolver;
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

		// Create Host information
		WebServiceHostConfig hostConfig = null;
		try {
			hostConfig = hostConfigResolver.loadHostConfig();

			createAndRegisterBean(beanFactory, candidateComponents, hostConfig);
		} catch (Exception e) {
			throw new RuntimeException("웹서비스 호스트 정보를 구성하는 중 예외가 발생했습니다", e);
		}

	}

	private void createAndRegisterBean(ConfigurableListableBeanFactory beanFactory, Set<BeanDefinition> candidateComponents,
			WebServiceHostConfig hostConfig) {
		for (BeanDefinition beanDefinition : candidateComponents) {
			Object proxyObject = createProxiedBeanObject(beanDefinition, beanFactory, hostConfig);
			beanFactory.registerSingleton(beanDefinition.getBeanClassName(), proxyObject);
		}
	}

	/**
	 * {@link ProxyFactory}를 사용해 실제 빈(bean)으로 등록되는 인터페이스의 객체를 생성함
	 * 
	 * @param beanDefinition
	 * @param beanFactory
	 * @param hostConfig
	 * @return
	 */
	protected Object createProxiedBeanObject(BeanDefinition beanDefinition, ConfigurableListableBeanFactory beanFactory,
			WebServiceHostConfig hostConfig) {
		ProxyFactory factory = new ProxyFactory();
		RestClientProxyObject proxyObject = new RestClientProxyObject();
		factory.setTarget(proxyObject);
		Class<?> targetInterface = resolveTargetInterface(beanDefinition.getBeanClassName(), beanFactory);
		factory.setInterfaces(new Class[] { targetInterface });

		String targetKey = resolveTargetKey(targetInterface);
		RestClientOperationMethodInterceptor interceptor = new RestClientOperationMethodInterceptor(hostConfig, targetKey);
		factory.addAdvice(interceptor);

		Object beanObject = factory.getProxy();
		if (logger.isDebugEnabled()) {
			logger.debug("Create proxy bean object to " + beanDefinition.getBeanClassName());
		}
		return beanObject;
	}

	private String resolveTargetKey(Class<?> targetInterface) {
		Webservice webservice = targetInterface.getAnnotation(Webservice.class);
		String target = webservice.target();
		if (StringUtils.hasText(target)) {
			return target;
		} else {
			// 호스트 타겟명이 지정되어 있지 않다면 인터페이스 명을 사용한다
			target = StringUtils.replace(targetInterface.getName(), "WebServiceApi", "");
		}
		return target;
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
