package config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "shh.fvckxml.controller")

public class SpringMVCConfig {
	Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 视图控制器
	 */
	@Bean
	public ViewResolver viewResolver() {
		logger.info("ViewResolver");
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	/**
	 * Json处理
	 */
	@Bean
	public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
		FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
		converter.setCharset(Charset.forName("utf-8"));
		converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
		converter.setFeatures(SerializerFeature.QuoteFieldNames);
		RequestMappingHandlerAdapter requestMappingHandlerAdapter = new RequestMappingHandlerAdapter();

		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(converter);
		requestMappingHandlerAdapter.setMessageConverters(messageConverters);
		return requestMappingHandlerAdapter;
	}

}
