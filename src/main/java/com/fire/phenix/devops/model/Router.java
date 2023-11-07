package com.fire.phenix.devops.model;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fire.phenix.devops.entity.SysMenu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @author fire-phenix
 * @since 2023-11-07
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Router implements Serializable {
    private static final long serialVersionUID = -7741958692564536459L;

    private Long id;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String path;
    @JsonIgnore
    private String redirect;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String name;

    private Meta meta;

    private Boolean iframe;
    @JsonIgnore
    private String component;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Router> children;

    public Router(SysMenu menu) {
        Meta meta1 = new Meta();
        meta1.title = StrUtil.isNotBlank(menu.getName()) ? menu.getName() : "";
        meta1.icon = StrUtil.isNotBlank(menu.getIcon()) ? menu.getIcon() : "";
        this.id = menu.getId();
        this.path = StrUtil.isNotBlank(menu.getUrl()) ? menu.getUrl() : "";
        this.redirect = StrUtil.isNotBlank(menu.getRedirect()) ? menu.getRedirect() : "";
        this.name = StrUtil.isNotBlank(menu.getName()) ? menu.getName() : "";
        this.meta = meta1;
        this.iframe = StrUtil.isNotBlank(menu.getRedirect());
        this.component = Objects.equals(RouterType.DIR.getValue(), menu.getType()) ? "Layout" : menu.getUrl();
    }

    @Data
    public static class Meta {
        private String title;
        private String icon;
    }
}
