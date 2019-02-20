package com.ascending.blair.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.ascending.blair.config.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.io.File;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")

@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})
public class StorageServiceTest {
  //  @InjectMocks
    @Autowired
    private StorageService storageService;

   // @Mock
   // private AmazonS3 client = Mockito.mock(AmazonS3.class);

    @Autowired
    private AmazonS3 s3;

//    public void setUp(){
//        MockitoAnnotations
//    }

    @Test
    @Transactional
    public void uploadObjectTest(){
        String keyName = "test_uploadObject";
        String filePath = "/Users/blair/Desktop/test.jpg";
        String bucketName = "ats-admin-dev";
        File file = new File(filePath);
        storageService.uploadObject(keyName, filePath, bucketName);
        assertTrue(true);
    }

    @Test
    @Transactional
    public void putObjectTest(){
        String keyName = "test_putObject";
        String filePath = "/Users/blair/Desktop/test.jpg";
        String bucketName = "ats-admin-dev";
        File file = new File(filePath);
        storageService.putObject(bucketName, keyName, file);
        verify(s3, times(1)).putObject(bucketName, keyName, file);
        storageService.putObject("test", "test_key", null);
        verify(s3, times(1)).putObject(bucketName, keyName, file);
    }


}
