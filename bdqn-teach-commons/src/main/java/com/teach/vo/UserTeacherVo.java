package com.teach.vo;

import com.teach.entity.access.Role;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserTeacherVo implements Serializable {

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

    private String roleIds; //在页面新增,修改用户时,传递过来的角色ids字符串,用于绑定关系

    private Set<Role> roles = new HashSet<Role>();//用户与角色   多对多

    private String jobTitle;// 职位名称
    private Date entryTime;// 入职日期
    private String hasMember;// 是否转正 1转正 0未转正
    private Integer jobNumber;// 工龄
    private String laborContract;// 是否办理劳动合同
    private Date contractTime;//	劳动合同签订日期
    private Integer contractNumber;// 续签天数
    private String hasSocialSecurity;// 是否有社保 1有 2无
    private String hasLeave;//	是否离职 1在职 2离职
    private String departmentId;

}
