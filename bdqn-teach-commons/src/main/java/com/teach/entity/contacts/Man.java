package com.teach.entity.contacts;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("man")
public class Man implements Serializable {
    @TableId(type = IdType.ID_WORKER_STR)
    private String serialid;

    private String manname;
    private String manstation;
    private String manaddress;
    private String mantel;
    private String companyoid;
    private String mantypeid;
    private String operatename;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date operatedatetime;
    private String hot;
    private String userid;

}
