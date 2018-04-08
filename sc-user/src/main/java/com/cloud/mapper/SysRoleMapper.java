package com.cloud.mapper;

import com.cloud.entity.SysRole;
import com.cloud.ctl.mapper.SuperMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author taotao123
 * @since 2018-04-06
 */
public interface SysRoleMapper extends SuperMapper<SysRole> {
    public List<String> findRolesByUid(long uid);

}
