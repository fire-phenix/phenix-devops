package com.fire.phenix.devops.mapper;

import com.fire.phenix.devops.entity.SysAccount;
import com.mybatisflex.core.BaseMapper;
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

    /**
     * 修改用户信息
     * @param account 用户
     * @return
     */
    Integer updateAccountById(@Param("account") SysAccount account);
}
