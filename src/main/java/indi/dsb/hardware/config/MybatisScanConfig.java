package indi.dsb.hardware.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tk.mybatis.spring.mapper.MapperScannerConfigurer;

@Configuration
public class MybatisScanConfig {
	public static final String BASE_PACKAGE = "indi.dsb.hardware.**.mapper";
	
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setBasePackage(BASE_PACKAGE);
		Properties properties = new Properties();
		properties.setProperty("mappers", "tk.mybatis.mapper.common.Mapper");
		mapperScannerConfigurer.setProperties(properties);
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBeanName");
		return mapperScannerConfigurer;
	}
}
