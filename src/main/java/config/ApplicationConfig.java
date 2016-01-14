package config;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@ComponentScan(basePackages = "io.github.mylyed.gravy", excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = { Controller.class }) })
@PropertySource({ "classpath:dbconfig.properties" })
@EnableTransactionManagement
public class ApplicationConfig {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	Environment environment;

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		logger.debug("初始化数据源");
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(environment.getProperty("jdbc.driverClassName"));
		dataSource.setUsername(environment.getProperty("jdbc.username"));
		dataSource.setPassword(environment.getProperty("jdbc.password"));
		dataSource.setUrl(environment.getProperty("jdbc.url"));
		try {
			// 监控
			dataSource.setFilters("stat");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		logger.debug("初始化成功， 数据源信息->{}", dataSource);
		return dataSource;
	}

	@Autowired
	@Bean()
	public LocalSessionFactoryBean sessionFactory(DataSource dataSource) throws Exception {
		logger.debug("初始化 -->sessionFactory");
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setPackagesToScan("io.github.mylyed.gravy.entitis");
		Properties properties = new Properties();
		properties.load(getClass().getClassLoader().getResourceAsStream("hibernate.properties"));
		logger.debug(properties.toString());
		sessionFactory.setHibernateProperties(properties);
		logger.debug("初始化 -->sessionFactory -->OK");
		return sessionFactory;
	}

	@Autowired
	@Bean
	public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) throws PropertyVetoException {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
	}

}
