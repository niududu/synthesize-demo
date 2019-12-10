package com.example.demo1.generator;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: LiuZW
 * @CreateDate: 2019/10/18/018 15:13
 * @Version: 1.0
 */
// 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class MysqlGenerator {

    private static final String dataBaseName = "esc_user";

    private static final String[] tableName = new String[] {"lst_asset"};

    private static final String basePackage = "com.example.demo1";
    /**业务模块包*/
    private static final String modelPackage = "";

    private static final String dir = "E:/Generator/code";

    /** db config*/
    private static final String ipAndPort = "localhost:3306";

    private static final String userName = "root";

    private static final String passWord = "root";

    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(dir);
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        gc.setAuthor("LiuZW");
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        // gc.setMapperName("%sDao");
        // gc.setXmlName("%sDao");
        // gc.setServiceName("MP%sService");
        gc.setServiceImplName("%sService");
        // gc.setControllerName("%sAction");
        mpg.setGlobalConfig(gc);
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        /*
        dsc.setTypeConvert(

                new MySqlTypeConvert() {

            // 自定义数据库表字段类型转换【可选】
            @Override
            public DbColumnType processTypeConvert(String fieldType) {
                System.out.println("转换类型：" + fieldType);
                return super.processTypeConvert(fieldType);
            }
        });*/
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(userName);
        dsc.setPassword(passWord);
        dsc.setUrl("jdbc:mysql://" + ipAndPort + "/" + dataBaseName + "?serverTimezone=GMT&useUnicode=true&characterEncoding=utf8&autoReconnect=true");
        mpg.setDataSource(dsc);
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
        // strategy.setTablePrefix(new String[] { "tlog_", "tsys_" });//
        // 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setInclude(tableName); // 需要生成的表
        // strategy.setExclude(new String[]{"test"}); // 排除生成的表
        // 自定义实体父类
        //strategy.setSuperEntityClass("com.buddha.component.mybatis.PojoModel");
        // 自定义实体，公共字段
        // strategy.setSuperEntityColumns(new String[] { "test_id", "age" });
        // 自定义 mapper 父类
        // strategy.setSuperMapperClass("com.baomidou.demo.TestMapper");
        // 自定义 service 父类
        // strategy.setSuperServiceClass("com.baomidou.demo.TestService");
        // 自定义 service 实现类父类
        // strategy.setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl");
        // 自定义 controller 父类
        //strategy.setSuperControllerClass("com.buddha.controller.WebBaseController");
        strategy.setRestControllerStyle(true); // REST风格
        // 【实体】是否生成字段常量（默认 false）
        // public static final String ID = "test_id";
        // strategy.setEntityColumnConstant(true);
        // 【实体】是否为构建者模型（默认 false）
        // public User setName(String name) {this.name = name; return this;}
        // strategy.setEntityBuliderModel(true);
        mpg.setStrategy(strategy);
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(basePackage);
        pc.setController("controller" + modelPackage);
        pc.setMapper("mapper" + modelPackage);
        pc.setServiceImpl("service" + modelPackage);
        pc.setEntity("entity" + modelPackage);
        //pc.setModuleName("test");
        mpg.setPackageInfo(pc);

        InjectionConfig cfg = new InjectionConfig() {

            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("dataBaseName", dataBaseName);
                map.put("modelPackage", modelPackage);
                map.put("well", "#"); //# 符号
                map.put("dollor", "$"); //$ 符号
                map.put("proxy", "miracle-api"); // 跨域
                this.setMap(map);
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        // 1 调整 service 生成目录
        focList.add(new FileOutConfig("/templates/serviceImpl.java.vm") {

            @Override
            public String outputFile(TableInfo tableInfo) {
                return dir + "/service/" + tableInfo.getEntityName() + "Service.java";
            }
        });
        // 调整entity生成目录
        focList.add(new FileOutConfig("/templates/entity.java.vm") {

            @Override
            public String outputFile(TableInfo tableInfo) {
                return dir + "/entity/" + tableInfo.getEntityName() + ".java";
            }
        });
        // 调整mapper生成目录
        focList.add(new FileOutConfig("/templates/mapper.java.vm") {

            @Override
            public String outputFile(TableInfo tableInfo) {
                return dir + "/mapper/" + tableInfo.getEntityName() + "Mapper.java";
            }
        });
        // 调整xml生成目录
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {

            @Override
            public String outputFile(TableInfo tableInfo) {
                return dir + "/xml/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        // 前端控制器
        focList.add(new FileOutConfig("/templates/controller.java.vm") {

            @Override
            public String outputFile(TableInfo tableInfo) {
                return dir + "/controller/" + tableInfo.getEntityName() + "Controller.java";
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        // 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/templates 下面内容修改，
        // 放置自己项目的 src/main/resources/templates 目录下, 默认名称一下可以不配置，也可以自定义模板名称
        TemplateConfig tc = new TemplateConfig();
        tc.setController(null);
        tc.setEntity(null);
        tc.setMapper(null);
        tc.setXml(null);
        tc.setService(null);
        tc.setServiceImpl(null);
        // 如上任何一个模块如果设置 空 OR Null 将不生成该模块。
        mpg.setTemplate(tc);
        // 执行生成
        mpg.execute();
        // 打印注入设置
        // System.err.println(mpg.getCfg().getMap().get("abc"));
    }
}

