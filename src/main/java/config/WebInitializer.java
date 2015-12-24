package config;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.alibaba.druid.support.http.StatViewServlet;

/**
 * 代替web.xml
 */
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	public void onStartup(ServletContext servletContext) throws ServletException {
		servletContext.log(new StringBuilder(WebInitializer.class.getCanonicalName()).append("正在启动中").toString());

		Dynamic druid = servletContext.addServlet("druid", new StatViewServlet());
		druid.addMapping("/druid/*");

		super.onStartup(servletContext);
	}

	// 设置spring 的配置 除去web部分
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { ApplicationConfig.class };
	}

	// 设置springmvc的配置
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { SpringMVCConfig.class };
	}

	// 拦截匹配的url
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/", "*.json" };
	}

	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return new Filter[] { characterEncodingFilter };
	}

}
