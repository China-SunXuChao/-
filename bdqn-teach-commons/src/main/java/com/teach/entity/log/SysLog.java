package com.teach.entity.log;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_log")
public class SysLog implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String username; //操作人

    private String operation; //操作描述

    private Long time;     //耗时(毫秒)

    private String method;  //执行方法

    private String params;  //方法参数

    private String ip;     // 操作人的IP地址

    private Date createTime; //操作时间

}
