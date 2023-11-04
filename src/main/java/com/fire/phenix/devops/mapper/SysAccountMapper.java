package com.fire.phenix.devops.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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

    /**
     * 修改用户信息
     * @param account 用户
     * @return
     */
    Integer updateAccountById(@Param("account") SysAccount account);
}
