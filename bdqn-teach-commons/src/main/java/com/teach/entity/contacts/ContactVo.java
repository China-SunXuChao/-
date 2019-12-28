package com.teach.entity.contacts;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ContactVo implements Serializable {
    private String cId;

    private String cName;

    private String serialID;
    private String manName;
    private String manStation;
    private String manAddress;
    private String manTel;
    private String companyOid;
    private String manTypeId;
    private String operateName;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date operateDateTime;
    private String hot;
    private String userId;

    private String tId;

    private String typeName;


}
