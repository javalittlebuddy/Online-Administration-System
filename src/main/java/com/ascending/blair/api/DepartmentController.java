package com.ascending.blair.api;

import com.ascending.blair.domain.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.ArrayList;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping(value = {"api/departments", "api/department"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class DepartmentController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(method = RequestMethod.GET)
    public List<Department> getDepartmentList(){
        logger.debug("list departments");
        return new ArrayList<Department>();
    }
}
