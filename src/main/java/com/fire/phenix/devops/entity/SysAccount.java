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
@Table(value = "sys_account")
public class SysAccount implements Serializable {

    @Id(keyType = KeyType.Auto)
    private Long id;

    private String username;

    private String password;

    private String icon;

    private String email;

    private Integer type;

    private String realName;

    private String remark;

    private Timestamp createdTime;

    private Timestamp updatedTime;

    private Timestamp loginTime;

    private Integer enabled;

    private Integer locked;

}
