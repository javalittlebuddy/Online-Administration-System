package com.ascending.blair.config;

import com.amazonaws.services.s3.AmazonS3;
import com.ascending.blair.service.StorageService;
import com.ascending.blair.service.StorageServiceTest;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
public class MockConfig {


    @Bean
    public AmazonS3 clientS3(){
        final AmazonS3 client = Mockito.mock(AmazonS3.class);
        return client;
    }

    @Bean
    @Profile("unit")
    public StorageService initStorageService(@Autowired AmazonS3 client){
        StorageService storageService = new StorageService(client);
        storageService.setBucket("ats-admin-dev");
        return storageService;
    }
}
