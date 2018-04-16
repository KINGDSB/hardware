package indi.dsb.hardware.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MarshallingView;

@Configuration
public class HardwareConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public ContentNegotiatingViewResolver contentNegotiatingViewResolver() {
        ContentNegotiatingViewResolver contentNegotiatingViewResolver = new ContentNegotiatingViewResolver();

        List<View> defaultViews = new ArrayList<>();
        MappingJackson2JsonView mappingJackson2JsonView = new MappingJackson2JsonView();
        mappingJackson2JsonView.setExtractValueFromSingleKeyModel(true);
        MarshallingView marshallingView = new MarshallingView();
        marshallingView.setMarshaller(new XStreamMarshaller());
        defaultViews.add(mappingJackson2JsonView);
        defaultViews.add(marshallingView);
        contentNegotiatingViewResolver.setDefaultViews(defaultViews);
        return contentNegotiatingViewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/static/**")) {
            registry.addResourceHandler("/static/**").addResourceLocations("/static/");
        }
        if (!registry.hasMappingForPattern("/common/**")) {
            registry.addResourceHandler("/common/**").addResourceLocations("/common/");
        }
        if (!registry.hasMappingForPattern("/images/**")) {
            registry.addResourceHandler("/images/**").addResourceLocations("/images/");
        }
        if (!registry.hasMappingForPattern("/js/**")) {
            registry.addResourceHandler("/js/**").addResourceLocations("/js/");
        }
        if (!registry.hasMappingForPattern("/file/**")) {
            registry.addResourceHandler("/file/**").addResourceLocations("/file/");
        }
    }
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController( "/" ).setViewName( "forward:/index" );
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE );
        super.addViewControllers( registry );
    } 
}
