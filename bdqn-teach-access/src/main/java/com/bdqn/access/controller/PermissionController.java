package com.bdqn.access.controller;

import com.bdqn.access.service.PermissionService;
import com.teach.entity.access.Permission;
import com.teach.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permission")
@CrossOrigin
public class PermissionController {

    @Autowired(required = false)
    private PermissionService permissionService;

    @RequestMapping("/all")
    public Result all(){return permissionService.all();}

    @RequestMapping(value = "/remove/{id}",method = RequestMethod.DELETE)
    public Result removePermission(@PathVariable("id")String id){
        return permissionService.removePermission(id);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Result savePermission(@RequestBody Permission permission){
        return permissionService.savePermission(permission);
    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public Result updatePermission(@RequestBody Permission permission){
        return permissionService.updatePermission(permission);
    }


}
