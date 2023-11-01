package com.fire.phenix.devops.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

/**
 *  表定义层。
 *
 * @author fire-phenix
 * @since 2023-11-02
 */
public class SysAccountTableDef extends TableDef {

    /**
     * 
     */
    public static final SysAccountTableDef SYS_ACCOUNT = new SysAccountTableDef();

    
    public final QueryColumn ID = new QueryColumn(this, "id");

    
    public final QueryColumn ICON = new QueryColumn(this, "icon");

    
    public final QueryColumn TYPE = new QueryColumn(this, "type");

    
    public final QueryColumn EMAIL = new QueryColumn(this, "email");

    
    public final QueryColumn LOCKED = new QueryColumn(this, "locked");

    
    public final QueryColumn REMARK = new QueryColumn(this, "remark");

    
    public final QueryColumn ENABLED = new QueryColumn(this, "enabled");

    
    public final QueryColumn PASSWORD = new QueryColumn(this, "password");

    
    public final QueryColumn REAL_NAME = new QueryColumn(this, "real_name");

    
    public final QueryColumn USERNAME = new QueryColumn(this, "username");

    
    public final QueryColumn LOGIN_TIME = new QueryColumn(this, "login_time");

    
    public final QueryColumn CREATED_TIME = new QueryColumn(this, "created_time");

    
    public final QueryColumn UPDATED_TIME = new QueryColumn(this, "updated_time");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, USERNAME, PASSWORD, ICON, EMAIL, TYPE, REAL_NAME, REMARK, CREATED_TIME, UPDATED_TIME, LOGIN_TIME, ENABLED, LOCKED};

    public SysAccountTableDef() {
        super("", "sys_account");
    }

}
