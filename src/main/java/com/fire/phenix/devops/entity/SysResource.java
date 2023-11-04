package com.fire.phenix.devops.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

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
public class SysResource implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private Timestamp createTime;

    private String name;

    private String url;

    private String description;

    private Long categoryId;

    private String method;

}
