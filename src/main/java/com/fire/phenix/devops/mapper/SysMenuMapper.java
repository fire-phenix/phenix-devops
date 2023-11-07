package com.fire.phenix.devops.mapper;

import com.mybatisflex.core.BaseMapper;
import com.fire.phenix.devops.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 *  映射层。
 *
 * @author fire-phenix
 * @since 2023-11-02
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     * 修改菜单项部分字段信息
     * @param id ID主键
     * @param menu 菜单项信息
     * @return 修改的条目数
     */
    int updatePartFieldById(@Param("id") Long id, @Param("menu") SysMenu menu);
}
