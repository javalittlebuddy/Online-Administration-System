package com.ascending.blair.config;

import com.ascending.blair.config.viewresolver.JsonViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan("com.ascending.blair.api")
//@Import({SwaggerConfig.class})
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /*
        Configure view resolver to provide JSON output using JACKSON library to convert to
        json object.
     */
    @Bean
    public ViewResolver jsonViewResolver(){
        return new JsonViewResolver();
    }

    // Configure contentNegotiatingViewResolver
    @Bean
    public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manger){
        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setContentNegotiationManager(manger);

        List<ViewResolver> viewResolversImpl = new ArrayList<>();
        viewResolversImpl.add(jsonViewResolver());

        resolver.setViewResolvers(viewResolversImpl);
        return resolver;
    }

}
