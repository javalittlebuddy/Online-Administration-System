package com.ascending.blair.service;

import com.ascending.blair.config.AppConfig;
import com.ascending.blair.repository.DepartmentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import static junit.framework.TestCase.assertTrue;

@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

//    @Test
//    @Transactional
//    public void findAllUserByDepartmentIdTest(){
//        assertTrue(true);
//    }

}
