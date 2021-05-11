package com.manager.system.config;

import java.util.*;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @classname: CodeGenerator
 * @description: mybatisplus代码生成器
 * @author: hannibal
 **/
public class CodeGenerator {

    /**
     * 代码生成器的配置常量
     */
    private static final String outPutDir = "/src/main/java";
    private static final String dataName = "root";
    private static final String dataPwd = "root";
    private static final String dataUrl = "jdbc:mysql://192.168.57.97:3306/manager?serverTimezone=GMT%2B8&useSSL=FALSE&useUnicode=true&characterEncoding=utf-8&autoReconnect=true&allowMultiQueries=true";
    private static final String driverName = "com.mysql.cj.jdbc.Driver";
    private static final String parentPackage = "com.manager.system";
    private static final String mapperName = "mapper";
    private static final String serviceName = "service";
    private static final String implName = "service.impl";
    private static final String pojoName = "entity";
    private static final String controllerName = "controller";
    private static final String xmlName = "mapper";

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (!StringUtils.isBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        // 当前工程路径
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + outPutDir);
        gc.setDateType(DateType.ONLY_DATE);
        gc.setAuthor("hannibal");
        gc.setOpen(false);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        // 覆盖生成的文件
        gc.setFileOverride(true);
        gc.setServiceName("%sService");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(dataUrl);
        dsc.setDriverName(driverName);
        dsc.setUsername(dataName);
        dsc.setPassword(dataPwd);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(parentPackage)
                .setMapper(mapperName)
                .setEntity(pojoName)
                .setService(serviceName)
                .setController(controllerName)
                .setServiceImpl(implName)
                .setXml(xmlName);
        mpg.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityTableFieldAnnotationEnable(true);
        strategy.setEntityLombokModel(true);
        strategy.setTablePrefix("tb_");
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        //默认生成全部
        /*        strategy.setExclude(null);*/
        strategy.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(strategy);
        mpg.execute();
    }

}

