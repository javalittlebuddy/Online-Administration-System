package com.ascending.blair.api;

import com.amazonaws.services.s3.AmazonS3;
import com.ascending.blair.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@ResponseBody
@RequestMapping(value = "/api/image")
public class ImageController {

    @Autowired
    StorageService storageService;

    @RequestMapping(method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public Map<String, String> uploadPicture(@RequestParam(value = "pic") MultipartFile picture){
        File file = new File("/Users/blair/Desktop/test.jpg");
        Map<String, String> result = new HashMap<>();

        try
        {
            picture.transferTo(file);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        result.put("s3_url", "sampleURL");
        storageService.putObject("ats-admin-dev","s3_url", file);

        return result;
    }
}

