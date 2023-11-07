package com.fire.phenix.devops.controller;

import com.fire.phenix.devops.entity.SysAccount;
import com.fire.phenix.devops.lang.IPage;
import com.fire.phenix.devops.service.ISysAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 控制层。
 *
 * @author fire-phenix
 * @since 2023-11-02
 */
@RestController
@RequestMapping("/sysAccount")
public class SysAccountController {

    @Resource
    private ISysAccountService accountService;

    /**
     * 添加。
     *
     * @param account 账户信息
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("/save")
    public Long save(@RequestBody SysAccount account) {
        return accountService.insertAccount(account);
    }

    /**
     * 根据主键删除。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("/remove/{id}")
    public boolean remove(@PathVariable Long id) {
        return accountService.deleteAccount(id);
    }

    /**
     * 根据主键更新。
     *
     * @param account 账户信息
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("/update/{id}")
    public boolean update(@PathVariable Long id, @RequestBody SysAccount account) {
        return accountService.updateAccount(id, account);
    }

    /**
     * 分页查询所有。
     *
     * @return 所有数据
     */
    @GetMapping("")
    @Operation(summary = "分页查询所有角色信息")
    @Parameters({
            @Parameter(name = "num", description = "页码"),
            @Parameter(name = "size", description = "每页大小"),
            @Parameter(name = "condition", description = "查询条件")
    })
    public IPage<SysAccount> list(
            @RequestParam(value = "num", required = false, defaultValue = "1") Integer num,
            @RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
            @RequestParam(value = "condition", required = false) String condition
    ) {
        return accountService.findAllAccounts(num, size, condition);
    }
}
