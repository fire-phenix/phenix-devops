package com.fire.phenix.devops.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fire.phenix.devops.entity.SysResource;

import java.util.List;

/**
 *  服务层。
 *
 * @author fire-phenix
 * @since 2023-11-02
 */
public interface ISysResourceService extends IService<SysResource> {
    List<SysResource> findResourcesByAccountId(Long accountId);
}
