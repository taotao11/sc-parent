package com.cloud.utils.page;

import java.util.Collections;
import java.util.List;

/**
 * 自定义mybatis 返回类
 * @param <F>
 */
public class PageResult<F> {
    //collection.emptyList() 一个空的 List 不能用add 方法
    private List<F> datas = Collections.emptyList();
    private int totle = 0;

    public PageResult() {
    }

    public PageResult(List<F> datas, int totle) {
        this.datas = datas;
        this.totle = totle;
    }

    public List<F> getDatas() {
        return datas;
    }

    public void setDatas(List<F> datas) {
        this.datas = datas;
    }

    public int getTotle() {
        return totle;
    }

    public void setTotle(int totle) {
        this.totle = totle;
    }

    public static <F>PageResult<F> builder(Class<F> clazz,List<F> datas, int totle){
        return new PageResult<F>(datas,totle);
    }


}
