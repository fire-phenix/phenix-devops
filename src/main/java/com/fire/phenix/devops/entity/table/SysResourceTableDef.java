package com.fire.phenix.devops.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

/**
 *  表定义层。
 *
 * @author fire-phenix
 * @since 2023-11-02
 */
public class SysResourceTableDef extends TableDef {

    /**
     * 
     */
    public static final SysResourceTableDef SYS_RESOURCE = new SysResourceTableDef();

    
    public final QueryColumn ID = new QueryColumn(this, "id");

    
    public final QueryColumn URL = new QueryColumn(this, "url");

    
    public final QueryColumn NAME = new QueryColumn(this, "name");

    
    public final QueryColumn METHOD = new QueryColumn(this, "method");

    
    public final QueryColumn CATEGORY_ID = new QueryColumn(this, "category_id");

    
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    
    public final QueryColumn DESCRIPTION = new QueryColumn(this, "description");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, CREATE_TIME, NAME, URL, DESCRIPTION, CATEGORY_ID, METHOD};

    public SysResourceTableDef() {
        super("", "sys_resource");
    }

}
