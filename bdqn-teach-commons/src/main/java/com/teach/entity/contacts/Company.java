package com.teach.entity.contacts;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("company")
public class Company implements Serializable {

    @TableId(type = IdType.ID_WORKER_STR)
    private String cId;

    private String cName;


}
