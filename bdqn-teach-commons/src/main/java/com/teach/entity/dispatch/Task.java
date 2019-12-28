package com.teach.entity.dispatch;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("propaganda")
public class Task implements Serializable {

    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    private String title;


    private String activities;
    private String picture;

    @TableField("release_time")
    private String releaseTime;
    @TableField("failure_time")
    private String failureTime;

    @TableField("p_content")
    private String pContent;

}
