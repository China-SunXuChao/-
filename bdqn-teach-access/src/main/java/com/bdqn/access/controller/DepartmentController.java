package com.bdqn.access.controller;

import com.bdqn.access.service.DepartmentService;
import com.teach.entity.access.Department;
import com.teach.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
@CrossOrigin
public class DepartmentController {

    @Autowired(required = false)
    private DepartmentService departmentService;

    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public Result findAll(){
        return departmentService.findAll();
    }

    @RequestMapping(value = "/remove/{id}",method = RequestMethod.DELETE)
    public Result removeDepartment(@PathVariable("id")String id){
        return departmentService.removeDepartment(id);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Result saveDepartment(@RequestBody Department department){
        return departmentService.saveDepartment(department);
    }


    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public Result updateDepartment(@RequestBody Department department){
        return departmentService.updateDepartment(department);
    }
}
