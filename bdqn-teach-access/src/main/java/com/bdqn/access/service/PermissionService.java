package com.bdqn.access.service;

import com.bdqn.access.repository.PermissionRepostitoty;
import com.teach.entity.access.Permission;
import com.teach.response.Result;
import com.teach.response.ResultCode;
import com.teach.utils.IdWorker;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PermissionService {

    @Autowired(required = false)
    private PermissionRepostitoty permissionRepostitoty;
    @Autowired(required = false)
    private IdWorker idWorker;
    public Result all() {
        List<Permission> permissions = this.getPermissions("0");
        return new Result(ResultCode.SUCCESS,permissions);
    }
    private List<Permission> getPermissions(String pid) {
        List<Permission> list = new ArrayList<>();
        Specification<Permission> spec = new Specification<Permission>() {
            @Override
            public Predicate toPredicate(Root<Permission> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                list.add(criteriaBuilder.equal(root.get("pid").as(String.class),pid));
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        List<Permission> permissions = permissionRepostitoty.findAll(spec);
        if (permissions!=null && permissions.size()>0){
            for (Permission permission:permissions){
                permission.setChildren(this.getPermissions(permission.getId()));
                list.add(permission);
            }
        }
        return list;
    }

    //删除权限
    public Result removePermission(String id) {
        //start 写了角色之后的补充：查看是否有相应的角色绑定了该权限
        int count = permissionRepostitoty.getRolesByPermissionId(id);
        if (count != 0){
            return new Result(ResultCode.PERMISSION_IN_ROLE_HAS_DATA);
        }
        permissionRepostitoty.deleteById(id);
        return Result.SUCCESS();
    }

    public Result savePermission(Permission permission) {
        //类型为3的只能有一个。所以需要进行判断
        String pid = permission.getPid();
        Integer type =3;
        List<Permission> permissions = permissionRepostitoty.findByPidAndType(pid,type);
        if (permissions!=null && permissions.size() == 1) return  Result.FAIL();
        permission.setId(idWorker.nextId() + "");
        Permission target = permissionRepostitoty.save(permission);
        if (target!=null) return  Result.SUCCESS();
        else return Result.FAIL();
    }

    public Result updatePermission(Permission permission) {
        Permission target = permissionRepostitoty.findById(permission.getId()).get();
        BeanUtils.copyProperties(permission,target);
        permissionRepostitoty.save(target);
        return Result.SUCCESS();
    }

    public List<Permission> findAll(Map map) {
        Specification<Permission> specification = new Specification<Permission>() {
            @Override
            public Predicate toPredicate(Root<Permission> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> list = new ArrayList<>();

                if(!StringUtils.isEmpty(map.get("pid"))) {
                    list.add(criteriaBuilder.equal(root.get("pid").as(String.class),(String)map.get("pid")));
                }


                if(map.get("enVisible") != null){
                    criteriaBuilder.equal(root.get("enVisible").as(String.class),map.get("enVisible").toString());
                }


                //根据类型 type
                if(!StringUtils.isEmpty(map.get("type"))) {
                    String ty = (String) map.get("type");
                    CriteriaBuilder.In<Object> in = criteriaBuilder.in(root.get("type"));
                    if("0".equals(ty)) {
                        in.value(1).value(2);
                    }else{
                        in.value(Integer.parseInt(ty));
                    }
                    list.add(in);
                }

                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };


        List<Permission> list = permissionRepostitoty.findAll(specification);


        return list;
    }
}


















