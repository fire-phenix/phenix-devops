package com.fire.phenix.devops.lang;

import com.alibaba.fastjson2.annotation.JSONField;
import com.mybatisflex.core.paginate.Page;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author fire-phenix
 * @since 2023-11-02
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IPage<T> {
    @JSONField(ordinal = 0)
    @Parameter(description = "当前页码")
    private long page;

    /**
     * 当前页的数量
     */
    @JSONField(ordinal = 2)
    @Parameter(description = "每页数据量大小")
    private long size;

    /**
     * 总页数
     */
    @JSONField(ordinal = 3)
    private long pages;

    /**
     * 总记录数
     */
    @JSONField(ordinal = 4)
    @Parameter(description = "总的数据量")
    private long total;

    /**
     * 结果列表
     */
    @JSONField(ordinal = 5)
    @Parameter(description = "数据")
    private List<T> rows;

    public IPage(Page<T> page){
        this.page = page.getPageNumber();
        this.size = page.getPageSize();
        this.pages = page.getTotalPage();
        this.total = page.getTotalRow();
        this.rows = page.getRecords();
    }
}
