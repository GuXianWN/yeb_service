package com.guxian;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;


/**
 * @author GuXianWN
 * @date 2022/01/12 16:36
 **/
public class main {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://101.34.229.217:3306/yeb?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai", "yeb", "357681")
                .globalConfig(builder -> {
                    builder.author("baomidou") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
//                            .fileOverride() // 覆盖已生成文件
                            .outputDir("E://win//Desktop//yeb//yeb-server//src//main//java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.guxian") // 设置父包名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "E://win//Desktop//yeb//yeb-server//src//main//resources//mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(Collections.emptyList())// 设置需要生成的表名
                            .addTablePrefix("t_", "c_")// 设置过滤表前缀
                            .entityBuilder().enableLombok().naming(NamingStrategy.underline_to_camel)
                            .mapperBuilder().enableMapperAnnotation();
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
