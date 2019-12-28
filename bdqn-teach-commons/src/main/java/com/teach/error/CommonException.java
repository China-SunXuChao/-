package com.teach.error;

import com.teach.response.ResultCode;
import lombok.Getter;

@Getter
public class CommonException extends Exception {

    private ResultCode resultCode;

    public CommonException(ResultCode resultCode){
        this.resultCode = resultCode;
    }


}
