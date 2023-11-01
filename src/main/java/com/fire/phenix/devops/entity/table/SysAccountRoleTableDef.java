package com.fire.phenix.devops.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

/**
 *  表定义层。
 *
 * @author fire-phenix
 * @since 2023-11-02
 */
public class SysAccountRoleTableDef extends TableDef {

    /**
     * 
     */
    public static final SysAccountRoleTableDef SYS_ACCOUNT_ROLE = new SysAccountRoleTableDef();

    
    public final QueryColumn ID = new QueryColumn(this, "id");

    
    public final QueryColumn ROLE_ID = new QueryColumn(this, "role_id");

    
    public final QueryColumn ACCOUNT_ID = new QueryColumn(this, "account_id");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, ACCOUNT_ID, ROLE_ID};

    public SysAccountRoleTableDef() {
        super("", "sys_account_role");
    }

}
