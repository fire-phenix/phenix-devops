package com.fire.phenix.devops.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fire.phenix.devops.entity.SysResource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *  映射层。
 *
 * @author fire-phenix
 * @since 2023-11-02
 */
@Mapper
public interface SysResourceMapper extends BaseMapper<SysResource> {
    /**
     * 根据用户ID查蓄奴资源信息
     * @param accountId 用户ID
     * @return resources
     */
    List<SysResource> findResourcesByAccountId(Long accountId);
}
