package com.cloud.ctl.service.impl;

import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cloud.ctl.mapper.SuperMapper;
import com.cloud.ctl.service.SuperService;
import com.cloud.utils.constant.PageConstant;
import com.cloud.utils.page.PageResult;


import java.util.List;
import java.util.Map;

public class SuperServiceImpl<M extends SuperMapper<T>,T> extends ServiceImpl<M,T> implements SuperService<T> {
    /**
     * 自定义多表分页的实现，使用PageHelper实现
     * @param clazz sql  orm 映射的实体
     * @param page 页码
     * @param size 每页记录数
     * @param params 条件查询的参数
     * @return
     */
    @Override
    public <F> PageResult<F> customSelectByPage(Class<F> clazz, Integer page, Integer size, Map<String, Object> params) {
        //在分页之前计算总数
        int total = baseMapper.customSelectByPage(params).size();

        page = page==null ? PageConstant.PAGE_NUM : page;
        size = size==null ? PageConstant.PAGE_SIZE : size;

        //开启分页
        PageHelper.startPage(page,size);
        //分页查询
        List<F> list = baseMapper.customSelectByPage(params);

        //结果装载
        PageResult<F> result = PageResult.builder(clazz,list, total);
        return result;
    }

}
