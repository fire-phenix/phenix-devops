package com.fire.phenix.devops.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.fire.phenix.devops.entity.SysResource;
import com.fire.phenix.devops.mapper.SysResourceMapper;
import com.fire.phenix.devops.service.ISysResourceService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  服务层实现。
 *
 * @author fire-phenix
 * @since 2023-11-02
 */
@Service
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements ISysResourceService {

    @Override
    public List<SysResource> findResourcesByAccountId(Long accountId) {
        return mapper.findResourcesByAccountId(accountId);
    }
}
