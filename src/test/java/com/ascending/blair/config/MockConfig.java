package com.ascending.blair.config;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.ascending.blair.service.StorageService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MockConfig {

    @Bean
    @Primary
    public StorageService initStorageService(){

        final AmazonS3 client = Mockito.mock(AmazonS3.class);
        StorageService storageService = new StorageService(client);
        storageService.setBucket("ats_unit");
        return storageService;
    }
}
