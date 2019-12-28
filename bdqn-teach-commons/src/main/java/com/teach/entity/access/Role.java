package com.teach.entity.access;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pe_role")
@Getter
@Setter
public class Role implements Serializable {

    @Id
    private String id;

    private String name;    //角色名

    private String description; //描述

    @Transient
    private String permissionIds;  //在页面修改角色时,传递过来的权限ids字符串,用于绑定关系

    /*@JsonIgnore
    @ManyToMany(mappedBy = "roles")*/


    //@JsonIgnore  //忽略 输出  json格式
    @ManyToMany
    @JoinTable(name="pe_role_permission",
            joinColumns={@JoinColumn(name="role_id",referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="permission_id",referencedColumnName="id")})
    private Set<Permission> permissions = new HashSet<Permission>(0);//角色与模块  多对多

}
