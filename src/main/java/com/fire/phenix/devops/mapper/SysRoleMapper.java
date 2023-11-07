package com.fire.phenix.devops.mapper;

import com.mybatisflex.core.BaseMapper;
import com.fire.phenix.devops.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 映射层。
 *
 * @author fire-phenix
 * @since 2023-11-02
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 更新部分角色信息字段
     *
     * @param id   主键ID
     * @param role 角色信息
     * @return 更新条目
     */
    int updatePartFieldById(@Param("id") Long id, @Param("role") SysRole role);
}
