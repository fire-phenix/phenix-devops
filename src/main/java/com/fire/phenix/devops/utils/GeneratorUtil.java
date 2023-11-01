package com.fire.phenix.devops.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.mybatisflex.codegen.Generator;
import com.mybatisflex.codegen.config.GlobalConfig;

import java.time.LocalDate;

/**
 * @author fire-phenix
 * @since 2023-11-02
 */
public class GeneratorUtil {
    public static void main(String[] args) {
        //配置数据源
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://192.168.73.7:3306/devops?characterEncoding=utf-8");
        dataSource.setUsername("root");
        dataSource.setPassword("Tv75aYT8@");

        //创建配置内容，两种风格都可以。
        GlobalConfig globalConfig = createGlobalConfigUseStyle1();

        //通过 datasource 和 globalConfig 创建代码生成器
        Generator generator = new Generator(dataSource, globalConfig);

        //生成代码
        generator.generate();
    }

    public static GlobalConfig createGlobalConfigUseStyle1() {
        //创建配置内容
        GlobalConfig globalConfig = new GlobalConfig();

        //设置根包
        globalConfig.setBasePackage("com.fire.phenix.devops");

        //设置生成 entity 并启用 Lombok
        globalConfig.setEntityGenerateEnable(true);
        // 开启 Entity 的生成
        // 设置生成 Entity 并启用 Lombok、设置父类
        globalConfig.enableEntity().setWithLombok(true);
        globalConfig.setEntityWithLombok(true);
        globalConfig.setAuthor("fire-phenix");
        globalConfig.setSince(LocalDate.now().toString());

        //设置生成 mapper
        globalConfig.setMapperGenerateEnable(true);
        globalConfig.setServiceGenerateEnable(true);
        globalConfig.getServiceConfig().setClassPrefix("I");
        globalConfig.setServiceImplGenerateEnable(true);
        globalConfig.setControllerGenerateEnable(true);
        globalConfig.setMapperXmlGenerateEnable(true);
        globalConfig.setTableDefGenerateEnable(true);

        return globalConfig;
    }
}
