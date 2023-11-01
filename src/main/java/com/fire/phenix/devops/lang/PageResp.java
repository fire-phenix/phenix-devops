package com.fire.phenix.devops.lang;

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
public class PageResp<T> {
    @Parameter(description = "每页数据量大小")
    private long pageSize;
    @Parameter(description = "总的数据量")
    private long total;
    @Parameter(description = "当前页码")
    private long pageNum;
    @Parameter(description = "数据")
    private List<T> list;
}
