package com.fire.phenix.devops.service.impl;

import com.fire.phenix.devops.entity.SysAccount;
import com.fire.phenix.devops.mapper.SysAccountMapper;
import com.fire.phenix.devops.service.ISysAccountService;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author fire-phenix
 * @since 2023-11-02
 */
@Service
public class SysAccountServiceImpl extends ServiceImpl<SysAccountMapper, SysAccount> implements ISysAccountService {

}
