# 小徐同学 新闻大事件项目

> 作者：[小徐同学](https://github.com/Xuyuyu520)

本项目地址 [github](https://github.com/Xuyuyu520/newsback) 原创项目，后端代码开源。

[点击starred](https://github.com/Xuyuyu520/newsback)
可获得该项目从 0 到 1 的完整源码

## 项目简介

[小徐同学](https://github.com/Xuyuyu520)
原创项目，一个帮助大家找到新闻大事件的网站。
## 功能
1. SpringBoot安全控制，登录注册功能
2. 增删改查功能，查找的话包含模糊查找，多条件合并查找。
3. 分页功能
4. 文件的上传、下载功能，图片的上传、图片的展示。
5. 通过excel批量增加，信息的excel导出。-新闻事件

## 本项目适合的同学

以下两个条件满足一个即可：

1. 已经学过基本的前端（HTML + CSS + JS 三件套），想学、在学或已学 Vue 移动端开发
2. 学习过后端开发技术（比如 Java Web）

## 技术选型

### 前端

- Vue 3
- Vant UI 组件库
- Vite 脚手架
- Axios 请求库

### 后端

- Java SpringBoot 2.7.x 框架
- MySQL 数据库
- MyBatis

### 部署

- Serverless 服务
- 云原生容器平台


  UploadController修改：

String parentDir = System.getProperty("user.dir");

FileHandlerConfig修改：
registry.addResourceHandler("/upload/**").addResourceLocations("File:"+System.getProperty("user.dir")+"/upload/");
