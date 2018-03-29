package com.cloud.ctl.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * 公共的父类Mapper
 * @param <T>
 */
public interface SuperMapper<T> extends BaseMapper<T> {
    //自定义分页查询,可以多表查询
    <F> List<F> customSelectByPage(Map<String, Object> params);
}
