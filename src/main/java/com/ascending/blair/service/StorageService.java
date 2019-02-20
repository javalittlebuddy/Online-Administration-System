package com.ascending.blair.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

public class StorageService {

    private AmazonS3 s3;
    private String bucket;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public StorageService(AmazonS3 s3){
        this.s3 = s3;
    }

    public StorageService(){

    }

    public void setBucket(String bucket){
        this.bucket = bucket;
    }

    public void putObject(String S3key, File file){
        s3.putObject(bucket, S3key, file);
    }

    public void putObject(String bucket, String S3key, File file){
        if (file != null){
            s3.putObject(bucket, S3key, file);
        }
    }

    public S3Object getObject(String S3key){
        if (S3key == null){
            return null;
        }
        else {
            return s3.getObject(bucket, S3key);
        }
    }

    public S3Object getObject(String bucket, String S3key) {
        return s3.getObject(bucket, S3key);
    }

    public ObjectListing listObjects(String bucket){
        ObjectListing images = s3.listObjects(bucket);
        //List<S3ObjectSummary> list = images.getObjectSummaries();
        return images;

    }

    public void deleteObject(String bucket, String S3key){
        if (S3key != null){
            s3.deleteObject(bucket, S3key);
        }
    }

    public void uploadObject(String keyName, String filePath, String bucketName){
        logger.debug("Uploading %s to S3 bucket %s...\n", filePath, bucketName);
        final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
        try {
            s3.putObject(bucketName, keyName, new File(filePath));
        } catch (AmazonServiceException e) {
            logger.debug(e.getErrorMessage());
        }
    }


}
