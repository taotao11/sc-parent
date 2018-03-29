package com.cloud.utils.validator;

/**
 * 验证不通过的消息
 */
public class ValidatorFailMsg {
    public static final String Not_Blank = "不能为空";
    public static final String Less_Then_Pre = "不小于";
    public static final String More_Then_Pre = "不超过";
    public static final String Is_Date = "只能为日期类型";
    //OrgManager实体的验证信息
    public static final String account_msg = "账号";
    public static final String password_msg = "密码";


    //OrgOrganization 实体验证
    public static final String fullname_msg = "组织全称";
    public static final String shortname_msg = "组织简称";
    public static final String telphone_msg = "联系电话";
    public static final String address_msg = "通讯地址";
    public static final String sortting_msg = "排序码";
    public static final String name_msg = "名称";
    public static final String parent_org_msg = "上级组织，没有就填0";
    public static final String parent_dept_msg = "上级部门，没有就填0";


    public static String getMsg(String field,String value){
        return String.format(field,value);
    }


}
