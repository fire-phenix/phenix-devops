package com.fire.phenix.devops.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

/**
 *  表定义层。
 *
 * @author fire-phenix
 * @since 2023-11-02
 */
public class SysRoleTableDef extends TableDef {

    /**
     * 
     */
    public static final SysRoleTableDef SYS_ROLE = new SysRoleTableDef();

    
    public final QueryColumn ID = new QueryColumn(this, "id");

    
    public final QueryColumn NAME = new QueryColumn(this, "name");

    
    public final QueryColumn SORT = new QueryColumn(this, "sort");

    
    public final QueryColumn STATUS = new QueryColumn(this, "status");

    
    public final QueryColumn ADMIN_COUNT = new QueryColumn(this, "admin_count");

    
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    
    public final QueryColumn DESCRIPTION = new QueryColumn(this, "description");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, NAME, DESCRIPTION, ADMIN_COUNT, CREATE_TIME, STATUS, SORT};

    public SysRoleTableDef() {
        super("", "sys_role");
    }

}
