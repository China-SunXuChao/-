package com.bdqn.access.service;

import com.bdqn.access.repository.PermissionRepostitoty;
import com.bdqn.access.repository.RoleRepository;
import com.teach.entity.access.Permission;
import com.teach.entity.access.Role;
import com.teach.response.PageResult;
import com.teach.response.Result;
import com.teach.response.ResultCode;
import com.teach.utils.IdWorker;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
@Transactional
@SuppressWarnings("all")
public class RoleService {

    @Autowired(required = false)
    private RoleRepository roleRepository;

    @Autowired
    private IdWorker idWorker;

    @Autowired(required = false)
    private PermissionRepostitoty permissionRepostitoty;


    public Result list(Map<String, Object> map) {
        Pageable page = (Pageable) PageRequest.of(Integer.parseInt(map.get("page").toString())-1,Integer.parseInt(map.get("size").toString()));
        Page<Role> result = roleRepository.findAll(page);
        PageResult<Role> pageResult = new PageResult<>(result.getTotalElements(),result.getContent());
        return new Result(ResultCode.SUCCESS,pageResult);
    }


    public Result saveRole(Role role) {
        role.setId(idWorker.nextId() + "");
        Set<Permission> permissions = new HashSet<>();
        for (String permissionId : role.getPermissionIds().split(",")){
            Permission permission =permissionRepostitoty.findById(permissionId).get();
            permissions.add(permission);
        }
        if (permissions != null && permissions.size()>0){
            role.setPermissions(permissions);
            roleRepository.save(role);
            return Result.SUCCESS();
        }else{
            return Result.FAIL();
        }
    }

    public Result updateRole(Role role) {
        Role target = roleRepository.findById(role.getId()).get();
        BeanUtils.copyProperties(role,target);

        //处理role和permissionIds中间表关系数据
        String permissionIds = role.getPermissionIds();

        Set<Permission> permissions = new HashSet<>();
        for (String permissionId : permissionIds.split(",")){
            Permission permission = permissionRepostitoty.findById(permissionId).get();
            permissions.add(permission);
        }

        if (permissions!=null && permissions.size() > 0){
            target.setPermissions(permissions);
            roleRepository.save(target);
            return Result.SUCCESS();
        }else{
            return Result.FAIL();
        }

    }


    public Result getRoles() {
        return new Result(ResultCode.SUCCESS,roleRepository.findAll());
    }
}
