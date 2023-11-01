package com.fire.phenix.devops.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

/**
 *  表定义层。
 *
 * @author fire-phenix
 * @since 2023-11-02
 */
public class SysMenuTableDef extends TableDef {

    /**
     * 
     */
    public static final SysMenuTableDef SYS_MENU = new SysMenuTableDef();

    
    public final QueryColumn ID = new QueryColumn(this, "id");

    
    public final QueryColumn ICON = new QueryColumn(this, "icon");

    
    public final QueryColumn NAME = new QueryColumn(this, "name");

    
    public final QueryColumn SORT = new QueryColumn(this, "sort");

    
    public final QueryColumn LEVEL = new QueryColumn(this, "level");

    
    public final QueryColumn TITLE = new QueryColumn(this, "title");

    
    public final QueryColumn HIDDEN = new QueryColumn(this, "hidden");

    
    public final QueryColumn PARENT_ID = new QueryColumn(this, "parent_id");

    
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, PARENT_ID, CREATE_TIME, TITLE, LEVEL, SORT, NAME, ICON, HIDDEN};

    public SysMenuTableDef() {
        super("", "sys_menu");
    }

}
