package indi.dsb.hardware.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DruidDataSourceConfig {
	@Value("${db.url}")
	private String url;
	@Value("${db.user}")
	private String username;
	@Value("${db.password}")
	private String password;
	@Value("${db.driverClass}")
	private String driverClassName;
	@Value("${db.maxActive}")
	private Integer maxActive;
	@Value("${db.initialSize}")
	private Integer initialSize;
	@Value("${db.maxWait}")
	private Long maxWait;
	@Value("${db.minIdle}")
	private Integer minIdle;
	@Value("${db.timeBetweenEviction}")
	private Long timeBetweenEvictionRunsMillis;
	@Value("${db.minEvictableIdle}")
	private Long minEvictableIdleTimeMillis;
	@Value("${db.testWhileIdle}")
	private Boolean testWhileIdle=false;
	@Value("${db.testOnBorrow}")
	private Boolean testOnBorrow=false;
	@Value("${db.testOnReturn}")
	private Boolean testOnReturn=true;

	@Bean(name = "dataSource", destroyMethod = "close")
	public DataSource dataSource() {
		if (StringUtils.isEmpty(url) ||
				StringUtils.isEmpty(username) ||
				StringUtils.isEmpty(password) ||
				StringUtils.isEmpty(driverClassName))
			throw new NullPointerException("DataSource config cannot be null!");

		DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
		dataSource.setMaxActive(maxActive);
		dataSource.setMinIdle(minIdle);
		dataSource.setInitialSize(initialSize);
		dataSource.setMaxWait(maxWait);
		dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		dataSource.setTestOnBorrow(testOnBorrow);
		dataSource.setTestOnReturn(testOnReturn);
		dataSource.setTestWhileIdle(testWhileIdle);
        return dataSource;
	}
	
	@Bean(name = "transactionManager")
	public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("dataSource") DataSource ds) {
		return new DataSourceTransactionManager(ds);
	} 
	

	@Bean(name = "sqlSessionFactoryBeanName")
	public SqlSessionFactoryBean sqlSessionFactoryBean(@Qualifier("dataSource") DataSource ds) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(ds);
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("reasonable", "true");  
		properties.setProperty("supportMethodsArguments", "true");  
		properties.setProperty("returnPageInfo", "check");  
		properties.setProperty("params", "count=countSql");
		properties.setProperty("dialect", "mysql");
		pageHelper.setProperties(properties);
		sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper});  
		return sqlSessionFactoryBean;
	}
}
