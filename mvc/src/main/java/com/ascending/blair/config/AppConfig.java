package com.ascending.blair.config;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.ascending.blair.service.StorageService;
import com.ascending.blair.service.jms.MessageSQSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

@Configuration
@ComponentScan(basePackages = "com.ascending.blair",
        excludeFilters = @ComponentScan.Filter(type= FilterType.REGEX,pattern = "com.ascending.blair.api.*"))
public class AppConfig {

    @Autowired
    private Environment environment; // spring originally has this bean you can use

    @Bean(name = "applicationProperties")
    public PropertiesFactoryBean getDbProperties() {
        PropertiesFactoryBean bean = new PropertiesFactoryBean();
        String profile = environment.getActiveProfiles()[0];
        //logger.debug("applicationProperties is "+profile);
        bean.setLocation(new ClassPathResource("META-INF/env/application-"+profile+".properties"));
        return bean;
    }

    @Bean
    @Profile({"dev", "test", "stage", "prod"})
    public StorageService initStorageService(){

        final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
        StorageService storageService = new StorageService(s3);
        storageService.setBucket("ats-admin-dev");
        return storageService;
    }

    @Bean
    @Profile({"dev", "test", "stage", "prod"})
    public MessageSQSService initAmzonSQS(){
        final AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
        MessageSQSService messageSQSService = new MessageSQSService(sqs);
        return messageSQSService;
    }
}
