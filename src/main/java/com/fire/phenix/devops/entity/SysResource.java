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
@Table(value = "sys_resource")
public class SysResource implements Serializable {

    @Id(keyType = KeyType.Auto)
    private Long id;

    private Timestamp createTime;

    private String name;

    private String url;

    private String description;

    private Long categoryId;

    private String method;

}
