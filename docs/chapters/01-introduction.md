---
title: 第1章 简介
original: "Chapter 01: Booting Spring Boot"
samples:
  - path: /samples/ch01/web-api
    desc: 起步示例（Web API）
---

# 第1章 简介

本项目将在 docs 中提供本书的中文翻译（学习笔记风格），并在 `samples/` 下按章节提供与原书结构对应的可运行样例。

- 示例入口（第1章）：`samples/ch01`，其中包含 `web-api` 子模块作为起步示例。
- 后续章节：`samples/ch02` ... `samples/ch10`，以及 `samples/appendix01`、`samples/appendix02`。

> 说明：本项目对齐 Spring Boot 3.4.x 与 Java 21，部分实现会在不改变原意的前提下使用最新写法。

## 快速开始

1. 安装依赖并启动文档站点：
   - `pnpm install`
   - `pnpm run docs:dev`
2. 运行第1章示例（需要已安装 Maven 与 JDK 21）：
   - 在 `samples` 目录执行：`mvn -q -DskipTests spring-boot:run -pl ch01/web-api`

## 目录

- [第1章 简介](/chapters/01-introduction)
  - 示例：samples/ch01/web-api
- 第2章 常用任务（待补充）
- 第3章 数据访问（待补充）
- 第4章 自动配置与监控（待补充）
- 第5章 应用安全（待补充）
- 第6章 进阶安全（待补充）
- 第7章 REST 服务（待补充）
- 第8章 响应式开发（待补充）
- 第9章 部署（待补充）
- 第10章 Kotlin/Native/GraphQL（待补充）
- 附录A 生成与构建（待补充）
- 附录B Spring MVC 与 Thymeleaf（待补充）
