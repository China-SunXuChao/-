package com.bdqn.access.controller;

import com.bdqn.access.service.RoleService;
import com.teach.entity.access.Role;
import com.teach.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/role")
@CrossOrigin
public class RoleController {

    @Autowired(required = false)
    private RoleService roleService;


    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public Result list(@RequestBody Map<String,Object> map){
        return roleService.list(map);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Result saveRole(@RequestBody Role role){
        return roleService.saveRole(role);
    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public Result updateRole(@RequestBody Role role){
        return  roleService.updateRole(role);
    }

    @RequestMapping(value = "/getRoles",method = RequestMethod.GET)
    public Result getRoles(){
        return roleService.getRoles();
    }


}
