package com.cloud.utils.string;

public class StringHelper {
    /**
     * 首字母大写或者小写的函数
     * @param name
     * @return
     */
    public static String captureName(String name,boolean isUpper) {
        //常规思想，效率不高
        //name = name.substring(0, 1).toUpperCase() + name.substring(1);
        // return  name;
        //即进行字母的ascii编码前移，从而转化为大写
        char[] cs=name.toCharArray();
        if (isUpper){
            cs[0]-=32;
        }else {
            cs[0]+=32;
        }
        return String.valueOf(cs);
    }

    /**
     * 将首字母转化为大写
     * @param name
     * @return
     */
    public static String captureName(String name) {
        return captureName(name,true);
    }
}

