package com.teach.entity.access;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("pe_teacher")
@Table(name = "pe_teacher")
@Entity
@ToString
public class Teacher implements Serializable {

    @Id
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;
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
