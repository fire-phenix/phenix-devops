package com.fire.phenix.devops.model;

/**
 * @author fire-phenix
 * @since 2023-11-07
 */
public enum RouterType {
    /**
     * 目录
     */
    DIR(1),
    /**
     * 菜单
     */
    MENU(2),
    /**
     * 按钮
     */
    BUTTON(3);

    private final Integer value;

    RouterType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}