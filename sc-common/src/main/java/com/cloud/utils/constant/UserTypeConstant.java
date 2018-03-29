package com.cloud.utils.constant;

public enum  UserTypeConstant {
    Ogr_User(0),       //普通用户
    Org_User_Child_User(1),     //用户的用户
    Manager_User(2);        //运维用户
    private int value;
    UserTypeConstant(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }

}
