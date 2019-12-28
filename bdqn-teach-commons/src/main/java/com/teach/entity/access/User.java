package com.teach.entity.access;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pe_user")
@Getter
@Setter
public class User implements Serializable {

    @Id
    private String id;

    private String username;

    private String password;

    private String nickName;

    private String level; //saasAdmin：saas管理员具备所有权限  coAdmin：企业管理（创建租户企业的时候添加）user：普通用户（需要分配角色）

    private String telephone;

    private String email;

    private String sex;
    private String residenceAddress; //户籍所在地

    private String nowAddress; //现居住址
    private String type;    //老师1,学生2
    private String idCard;
    private Date birthday;
    private String education;   //学历
    private String graduationSchool; //毕业院校
    private String major;		//专业
    private String emergencyContact; //紧急联络人
    private String relation; //紧急联络人关系
    private String status;  //是否禁用该账号

    @Transient
    private String roleIds; //在页面新增,修改用户时,传递过来的角色ids字符串,用于绑定关系

    /**
     *  JsonIgnore
     *     : 忽略json转化
     */
    //@JsonIgnore
    @ManyToMany
    @JoinTable(name="pe_user_role",joinColumns={@JoinColumn(name="user_id",referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="role_id",referencedColumnName="id")}
    )
    private Set<Role> roles = new HashSet<Role>();//用户与角色   多对多

}
