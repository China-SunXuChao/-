package com.teach.response;

public enum  ResultCode {


    SUCCESS(true,10000,"恭喜你,操作成功"),
    //权限 ------------------
    PERMISSION_IN_ROLE_HAS_DATA(false,11111,"无法删除权限,因为角色已经绑定了该权限"),
    DEPARTMENT_HAS_USERS(false,15661,"无法删除部门， 部门下还有许多员工！"),
    FAIL(false,10001,"操作失败,请检查你的网络"),
    MOBILEORPASSWORDERROR(false,20001,"用户名或密码错误"),
    UNAUTHORISE(false,10003,"权限不足"),
    UNAUTHENTICATED(false,10002,"您还未登录");


    int code;
    String message;
    boolean success;


    ResultCode(boolean success,int code,String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public int code(){
        return code;
    }

    public String message(){
        return message;
    }

    public boolean success(){
        return success;
    }


}
