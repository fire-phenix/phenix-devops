package com.fire.phenix.devops.mapper;

import com.mybatisflex.core.BaseMapper;
import com.fire.phenix.devops.entity.SysAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 *  映射层。
 *
 * @author fire-phenix
 * @since 2023-11-02
 */
@Mapper
public interface SysAccountMapper extends BaseMapper<SysAccount> {

    int updatePartFieldById(@Param("id") Long id, @Param("account") SysAccount account);
}
