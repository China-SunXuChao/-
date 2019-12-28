package com.teach.entity.house;

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
@TableName("h_buyer")
public class Buyer implements Serializable {

    @TableId(type = IdType.ID_WORKER_STR)
    private String id;
    @TableField("b_name")
    private String bName;
    @TableField("b_phone")
    private String bPhone;

}
