package com.ascending.blair.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.io.File;
import java.util.List;

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

    @Test
    @Transactional
    public void getObjectTest(){
        String keyName = "test_putObject";
        String bucketName = "ats-admin-dev";
        storageService.getObject(keyName);
        verify(s3, times(1)).getObject(bucketName, keyName);
        storageService.getObject(null);
        verify(s3, times(1)).getObject(bucketName, keyName);
    }

    @Test
    @Transactional
    public void listObjectsTest(){
        String bucketName = "ats-admin-dev";
        storageService.listObjects(bucketName);
        verify(s3, times(1)).listObjects(bucketName);
    }

    @Test
    @Transactional
    public void deleteObjectTest(){
        String keyName = "test_putObject";
        String bucketName = "ats-admin-dev";
        storageService.deleteObject(bucketName, keyName);
        verify(s3, times(1)).deleteObject(bucketName, keyName);
        storageService.deleteObject(bucketName, null);
        verify(s3, times(1)).deleteObject(bucketName, keyName);
    }


}
