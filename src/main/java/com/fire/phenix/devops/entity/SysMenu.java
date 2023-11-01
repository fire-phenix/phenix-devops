package com.fire.phenix.devops.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  实体类。
 *
 * @author fire-phenix
 * @since 2023-11-02
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "sys_menu")
public class SysMenu implements Serializable {

    @Id(keyType = KeyType.Auto)
    private Long id;

    private Long parentId;

    private Timestamp createTime;

    private String title;

    private Integer level;

    private Integer sort;

    private String name;

    private String icon;

    private Integer hidden;

}
