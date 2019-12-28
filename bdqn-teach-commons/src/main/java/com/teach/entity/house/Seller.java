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
@TableName("h_seller")
public class Seller implements Serializable {
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;
    @TableField("s_name")
    private String sName;
    @TableField("s_phone")
    private String sPhone;
}
