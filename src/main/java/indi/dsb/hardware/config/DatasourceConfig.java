package indi.dsb.hardware.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations = {"classpath:spring/spring-context.xml"})
public class DatasourceConfig {

}
