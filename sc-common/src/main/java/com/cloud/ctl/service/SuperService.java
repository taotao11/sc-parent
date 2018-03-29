package com.cloud.ctl.service;

import com.baomidou.mybatisplus.service.IService;
import com.cloud.utils.page.PageResult;


import java.util.Map;

public interface SuperService<T> extends IService<T> {
    <F>PageResult<F> customSelectByPage(Class<F> clazz, Integer page, Integer size, Map<String, Object> params);
}
