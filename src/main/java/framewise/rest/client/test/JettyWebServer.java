package framewise.rest.client.test;

import java.net.URL;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

/**
 * 주로 테스트 목적으로 사용하는 Jetty 웹서버를 편하게 기동시키기 위한 래퍼 클래스
 * 
 * @author chanwook
 * 
 */
//FIXME SPRING과 연계해서 동작하지가 않음..테스트 필요 
public class JettyWebServer {

	private final Logger logger = LoggerFactory.getLogger(JettyWebServer.class);

	private Server server;

	public JettyWebServer(int port, String webXmlFilePath, String contextName) {
		// TODO 컨피그 속성화
		server = new Server(port);
		// server.setGracefulShutdown(5000);
		// server.setStopAtShutdown(true);

		URL domainLocation = ProtectDomainDummy.class.getProtectionDomain().getCodeSource().getLocation();

		WebAppContext webContext = new WebAppContext(domainLocation.toExternalForm(), contextName);
		webContext.setDescriptor(domainLocation.toExternalForm() + webXmlFilePath);
		webContext.setServer(server);
		webContext.setTempDirectory(new FileSystemResource("target/webserver").getFile());
		server.setHandler(webContext);
	}

	public void run() {
		SimpleAsyncTaskExecutor taskExecutor = new SimpleAsyncTaskExecutor();
		taskExecutor.execute(new Runnable() {
			@Override
			public void run() {
				logger.info(">>>>> Running Jetty Webserver");
				try {
					server.start();
					server.join();
				} catch (Throwable e) {
					throw new RuntimeException("thrown exception in jetty webserver running!", e);
				}
			}
		});
	}

	/**
	 * Stop Jetty WebServer
	 */
	public void stop() {
		try {
			if (server.isRunning()) {
				server.stop();
			} else {
				throw new RuntimeException("Jetty WebServer is not running state!");
			}
		} catch (Exception e) {
			throw new RuntimeException("thrown exception in jetty webserver stop!", e);
		}
	}

	// TODO 다른 함수도 추가 지원..

	public static class ProtectDomainDummy {

	}

}
