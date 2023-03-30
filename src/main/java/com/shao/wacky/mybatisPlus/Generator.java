//package com.shao.wacky.mybatisPlus;
//
//import com.baomidou.mybatisplus.core.AutoGenerator;
//import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
//import com.baomidou.mybatisplus.generator.config.GlobalConfig;
//import com.baomidou.mybatisplus.generator.config.PackageConfig;
//import com.baomidou.mybatisplus.generator.config.StrategyConfig;
//import com.baomidou.mybatisplus.generator.config.rules.DbType;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
//
///**
// * 自动生成代码
// */
//public class Generator {
//
//    /**
//     * @param serviceNameStartWithI
//     * @param packageName   包名
//     * @param author  作者
//     * @param database  数据库名
//     * @param tableNames 表名
//     */
//    private static void generateByTables(boolean serviceNameStartWithI, String packageName, String author, String database, String... tableNames) {
//        GlobalConfig config = new GlobalConfig();
//        String dbUrl = "jdbc:mysql://127.0.0.1:3306/" + database + "?useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false";
//        DataSourceConfig dataSourceConfig = new DataSourceConfig();
//        dataSourceConfig.setDbType(DbType.MYSQL)
//                .setUrl(dbUrl)
//                .setUsername("shao")
//                .setPassword("123456")
//                .setDriverName("com.mysql.cj.jdbc.Driver");
//        StrategyConfig strategyConfig = new StrategyConfig();
//        strategyConfig
//                .setCapitalMode(true)
//                .setEntityLombokModel(false)
//                .setDbColumnUnderline(true)
//                .setNaming(NamingStrategy.underline_to_camel)
////              .setSuperMapperClass("cn.saytime.mapper.BaseMapper")
//                .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组
//        config.setActiveRecord(false)
//                .setAuthor(author)
//                .setOutputDir("/Users/shaoyou/workspace/code")
//                .setFileOverride(true)
//                .setEnableCache(false);
//        if (!serviceNameStartWithI) {
//            config.setServiceName("%sService");
//        }
//        new AutoGenerator().setGlobalConfig(config)
//                .setDataSource(dataSourceConfig)
//                .setStrategy(strategyConfig)
//                .setPackageInfo(
//                        new PackageConfig()
//                                .setParent(packageName)
//                                //.setController("web")
//                                //.setEntity("model")
//                                .setMapper("mapper")
//                                .setService("service")
//                                .setServiceImpl("service.impl")
//                                .setXml("mybatis.mappers")
//                ).execute();
//    }
//
//
//    public static void main(String[] args) {
//        String packageName = "com.shao.wacky";
//        boolean serviceNameStartWithI = false;//auth -> UserService, 设置成true: auth -> IUserService
//        generateByTables(false, packageName, "wacky", "shao", "user");
//        System.out.println("completed...");
//    }
//}
