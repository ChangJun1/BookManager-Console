# 图书管理系统(控制台版)

基于Mybatis+JUL+Lombok+Maven（数据库+日志+依赖管理）

项目需求：

* 在线录入学生信息和书籍信息
* 查询书籍信息列表
* 查询学生信息列表
* 查询借阅信息列表
* 完整的日志系统

1. 配置数据库
    * 建表，建表语句见origin.sql
    *
    新建java项目，配置maven依赖，新建mybaits-config.xml配置文件，可参考[mybaits入门](https://mybatis.org/mybatis-3/zh/getting-started.html)
    ，连接数据库，创建实体类，配置映射器，测试插入
2. 开发功能