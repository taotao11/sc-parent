package com.cloud.ctl.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.ctl.service.SuperService;
import com.cloud.exception.RRException;
import com.cloud.utils.constant.BaseConstant;
import com.cloud.utils.constant.PageConstant;
import com.cloud.utils.generator.id.SnowflakeIdWorker;
import com.cloud.utils.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;

import static com.cloud.utils.constant.BaseConstant.CREATE_TIME_KEY;
import static com.cloud.utils.constant.BaseConstant.DeleteStatus.IS_DELETE;
import static com.cloud.utils.constant.BaseConstant.DeleteStatus.IS_NOT_DELETE;
import static com.cloud.utils.constant.BaseConstant.ID_KEY;
import static com.cloud.utils.constant.BaseConstant.UPDATE_TIME_KEY;
import static com.cloud.utils.constant.ResultMsgConsant.*;
import static com.cloud.utils.constant.ResultMsgConsant.OperatorWays.ADD_MSG;
import static com.cloud.utils.constant.ResultMsgConsant.OperatorWays.DELETE_MSG;
import static com.cloud.utils.constant.ResultMsgConsant.OperatorWays.UPDATE_MSG;
import static com.cloud.utils.result.CommonReturnMsgUtil.getCommonReturnMsg;


@Slf4j
@Api(value = "后台接口")
public class SuperController <BaseServiceImpl extends SuperService,T, PK extends Serializable> {
    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    @Autowired
    protected BaseServiceImpl baseServiceImpl;

    /**
     * 分页查询，无查询参数，如需要动态查询，则自己重写该方法
     * @param pageNum 页码
     * @param pageSize 每页记录数
     * @return R
     */
    @GetMapping
    @ResponseBody
    @ApiOperation(value = "分页查询",position = 1)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType="Integer", name = "pageNum", value = "页码"),
            @ApiImplicitParam(paramType = "query", dataType="Integer", name = "pageSize", value = "每页条数")})
    @Transactional
    public R listByPage(Integer pageNum, Integer pageSize, Class<T> clazz){
        Page<T> page = setPage(pageNum,pageSize);
        //查询(不含删除状态),如果需要自己添加更多条件，则重写getWrapper方法即可
        page = listByPageConditionReturnPage(page,getWrapper(clazz));
        return R.ok()
                .put(Page_Result_Datas_Key,page.getRecords())
                .put(Page_Result_Total_Key,page.getTotal());
    }

    /**
     * 单条记录查询
     * @param id 主键
     * @return R
     */
    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "单条记录查询",position = 2)
    @ApiImplicitParam(paramType = "path", name = "id", value = "id", required = true)
    public R get(@PathVariable PK id){
        T entity = (T) baseServiceImpl.selectById(id);
        return R.ok().put(Info_Result_Key,entity);
    }

    /**
     * 新增某条记录
     * @param entity 实体
     * @return R
     */
    @PostMapping
    @ResponseBody
    @ApiOperation(value = "新增",position = 3)
    @Transactional
    public R add(@Valid @RequestBody T entity){
        //使用全局唯一性id，这里做异常处理是因为，可能有些关系表是没有id字段的
        entity = settingSpecFieldForEntity(entity,ID_KEY, SnowflakeIdWorker.getInstance().nextId());
        //创建时间填充
        Date date = new Date();
        entity = settingSpecFieldForEntity(entity,CREATE_TIME_KEY,date);
        //修改时间和删除状态的填充,传入date，使新增时新增修改时间一致
        entity = setEntityAddAndUpdate(entity,date);

        boolean b = baseServiceImpl.insert(entity);
        return getCommonReturnMsg(b,ADD_MSG);
    }


    /**
     * 修改某条记录
     * @param entity 实体
     * @return R
     */
    @PutMapping(value = "/{id}")
    @ResponseBody
    @ApiOperation(value = "修改",position = 4)
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "path",name = "id", value = "id", required = true)
    })
    @Transactional
    public R update(@RequestBody T entity){
        //填充公共字段（如果存在），
        entity = setEntityAddAndUpdate(entity);
        boolean b = baseServiceImpl.updateById(entity);
        return getCommonReturnMsg(b,UPDATE_MSG);
    }

    /**
     * 根据单个Id进行删除
     * @param id 主键
     * @return R
     */
    @DeleteMapping(value = "/{id}")
    @ResponseBody
    @ApiOperation(value = "单条删除",position = 5)
    @ApiImplicitParam(paramType = "path",name = "id", value = "id", required = true)
    @Transactional
    public R delete(@PathVariable PK id){
        T entity = (T) baseServiceImpl.selectById(id);
        //填充公共字段（如果存在），设置为删除状态
        entity = setEntityAddAndUpdate(entity,true);
        boolean b = baseServiceImpl.updateById(entity);
        //boolean b = baseServiceImpl.deleteById(id);
        return getCommonReturnMsg(b,DELETE_MSG);
    }

    /**
     * 批量删除数据
     * @param ids id集合
     * @return R
     */
    @DeleteMapping(value = "/batch")
    @ResponseBody
    @ApiOperation(value = "批量删除",position = 6)
    @Transactional
    public R delete(@RequestBody List<PK> ids){
        List<T> list = new ArrayList<>();
        ids.forEach(id->{
            T entity = (T) baseServiceImpl.selectById(id);
            //填充公共字段（如果存在），设置为删除状态
            entity = setEntityAddAndUpdate(entity,true);
            list.add(entity);
        });
        boolean b = baseServiceImpl.updateBatchById(list);
        //boolean b = baseServiceImpl.deleteBatchIds(ids);
        return getCommonReturnMsg(b,DELETE_MSG);
    }


    /**
     * 查询(非删除状态的)所有数据
     * @return R
     */
    @GetMapping(value = "/all")
    @ResponseBody
    @ApiOperation(value = "所有记录",position = 7)
    @Transactional
    public R all(Class<T> clazz){
        //如果需要排除删除逻辑状态的数据，请根据注释部分重写
        //EntityWrapper<T> wrapper = new EntityWrapper<>();
        //wrapper.and(DELETE_STATUS_PREFIX+DB_DELETE);
        return R.ok().put(List_Result_Key,getAllRecords(clazz,getWrapper(clazz)));
    }

    /**
     * 查询所有
     * @param clazz
     * @return
     */
    protected List<T> getAllRecords(Class<T> clazz,EntityWrapper<T> wrapper){
        wrapper = wrapper==null?getWrapper(clazz):wrapper;
        List<T> list = baseServiceImpl.selectList(wrapper);
        return list;
    }


    /**
     * 填充entity公共字段
     * @param entity 实体
     * @param <T> 实体泛型
     * @return R
     */
    private <T> T setEntityAddAndUpdate(T entity,boolean isDelete,Date... dates){
        if (entity == null) throw new RRException("传入的信息有误:id错误/实体为空");
        //填充公共字段（如果存在），
        //修改日期
        Date date = (dates==null || dates.length==0)?new Date():dates[0];

        entity = settingSpecFieldForEntity(entity,UPDATE_TIME_KEY,date);
        //是否为删除状态
        entity = isDelete?settingSpecFieldForEntity(entity,DELETE_STATUS_KEY,IS_DELETE.getValue())
                    :settingSpecFieldForEntity(entity,DELETE_STATUS_KEY,IS_NOT_DELETE.getValue());

        return entity;
    }

    /**
     * 默认是非删除状态
     * @param entity 实体
     * @param <T> 实体泛型
     * @return
     */
    public  <T> T setEntityAddAndUpdate(T entity,Date... dates){
        return setEntityAddAndUpdate(entity,false,dates);
    }

    /**
     * 动态条件查询，返回Page
     * @param page 分页类
     * @param wrapper 查询条件
     * @param <T> 实体泛型
     * @return R
     */
    protected <T>Page<T> listByPageConditionReturnPage(Page<T> page, EntityWrapper<T> wrapper){
        //查询
        page = baseServiceImpl.selectPage(page,wrapper==null?new EntityWrapper<T>():wrapper);
        return page;
    }

    /**
     * 设置特定的字段到实体类中，这个将不影响业务逻辑的实现(如果该字段不存在的话)
     * @param entity 实体
     * @param fieldName 字段名
     * @param value 字段值
     * @param <T> 实体泛型
     * @return R
     */
    protected <T>T settingSpecFieldForEntity(T entity,String fieldName,Object value){
        //空处理
        if (StringUtils.isBlank(fieldName) || value==null) return entity;
        try {
            Class<T> clazz = (Class<T>) entity.getClass();
            Field idField = clazz.getDeclaredField(fieldName);
            //设置更新时间
            if (idField!=null){
                //反射中要对属性值进行更改 必须设置 idField.setAccessible(true)
                //开启对私有字段进行设置值
                idField.setAccessible(true);
                /* 改变 属性对应的 值 */
                idField.set(entity, value);
            }
        } catch (Exception e) {
            //e.printStackTrace();
            log.info("字段"+fieldName+"在实体"+entity.getClass().getName()+"中不存在或者值得类型不一致，则字段将设置无效....");
        }
        return entity;
    }

    /**
     * 判断字段上是否存在
     * @param clazz 类
     * @param fieldName 字段名
     * @return
     */
    protected boolean fieldIsExist(Class<T> clazz,String fieldName){
        boolean b = false;
        //空处理
        if (StringUtils.isBlank(fieldName)) b = false;
        try {
            //反射 field 为类中的属性
            //clazz.getDeclaredField(fieldName); 得到一个属性 没有则为空
            Field idField = clazz.getDeclaredField(fieldName);
            //设置更新时间
            if (idField!=null){
                b = true;
            }
        } catch (Exception e) {
            b = false;
        }
        return b;
    }

    /**
     * 设置条件查询上，排除删除状态
     * @return EntityWrapper
     */
    protected EntityWrapper<T> getWrapper(Class<T> clazz){
        EntityWrapper<T> wrapper = new EntityWrapper<>();
        if (fieldIsExist(clazz,DELETE_STATUS_KEY)){
            //wrapper.and(DELETE_STATUS_KEY+DELETE_STATUS_SIGN+BaseConstant.DeleteStatus.IS_DELETE.getValue());
            //为正常状态
            wrapper.eq(DELETE_STATUS_KEY,IS_NOT_DELETE.getValue());
        }
        //如果id存在，进行分组，然后再帅选，排除删除
        if (fieldIsExist(clazz,ID_KEY)){
            //先分组
            wrapper.groupBy(BaseConstant.ID_KEY);
            //对分组的结果进行筛选,因为既有or又有and的时候，and的条件在or条件生效之时可能会不生效
            wrapper.having(BaseConstant.DELETE_STATUS_KEY+" = "+IS_NOT_DELETE.getValue());
        }
        return wrapper;
    }



    /**
     * 获取所有请求参数，封装为map对象
     * @return map
     */
    public Map<String,Object> getParameterMap(){
        Enumeration<String> enumeration = request.getParameterNames();
        Map<String,Object> parameterMap = new HashMap<String,Object>();
        while (enumeration.hasMoreElements()){
            String key = enumeration.nextElement();
            String value = request.getParameter(key);
            parameterMap.put(key,value);
        }
        return parameterMap;
    }

    /**
     * 移除分页相关参数
     * @return map
     */
    public Map<String,Object> removePageParameterInMap(){
        Map<String, Object> params = getParameterMap();
       if (params.get(PageConstant.PAGE_NUM_KEY)!=null){
           params.remove(PageConstant.PAGE_NUM_KEY);
       }
        if (params.get(PageConstant.PAGE_SIZE_KEY)!=null){
            params.remove(PageConstant.PAGE_SIZE_KEY);
        }
        return params;
    }


    /**
     * 根据名字获取参数的值
     * @param key 参数的键
     * @return object
     */
    public Object getParameterByKey(String key){
        Map<String, Object> map = getParameterMap();
        if (StringUtils.isNotBlank(key)){
            return map.get(key);
        }
        return null;
    }

    /**
     * 设置分页参数
     * @param pageNum 页码
     * @param pageSize 每页记录数
     * @param <T> 泛型
     * @return page
     */
    protected <T>Page<T> setPage(Integer pageNum,Integer pageSize){
        pageNum = pageNum == null? PageConstant.PAGE_NUM:pageNum;
        pageSize = pageSize == null? PageConstant.PAGE_SIZE:pageSize;
        return new Page<>(pageNum,pageSize);
    }


    /**
     * 将HttpServletRequest和response封装到ServletWebRequest中
     * @return ServletWebRequest
     */
    public ServletWebRequest getServletWebRequest(){
        return new ServletWebRequest(request,response);
    }
}
