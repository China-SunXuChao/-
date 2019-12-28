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
@TableName("type")
public class Type implements Serializable {

    @TableId(type = IdType.ID_WORKER_STR)
    private String tId;

    private String typeName;

}
