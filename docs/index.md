---
title: Spring Boot in Practice 中文学习笔记
---

<img src="/assets/spring-boot-in-practice.png" alt="Spring Boot in Practice" style="float:left; margin-right:24px; height:140px;" />

# Spring Boot in Practice 中文学习笔记

> 基于 Somnath Musib 著作，聚焦 Spring Boot 3.4.x + Java 21 的实战技巧与最佳实践。

本项目整理了《Spring Boot in Practice》的核心内容与代码样例，适合希望系统掌握 Spring Boot 的开发者、架构师与团队。

## 关于本书

本项目基于 Somnath Musib 编写的《Spring Boot in Practice》，由 Manning Publications 出版。原书系统介绍了 Spring Boot 的核心功能、实用技巧和最佳实践，适合希望深入理解并高效开发 Spring Boot 应用的 Java 开发者。

### 适用人群

本书适合以下读者：
- 有一定 Java 基础，想系统学习 Spring Boot 的开发者
- 希望掌握 Spring Boot 实战技巧和最佳实践的工程师
- 需要快速查阅 Spring Boot 典型用法的架构师和团队
- 对微服务、响应式开发、云原生部署等主题感兴趣的技术人员

### 全书结构与章节简介

本书共 10 章和 2 个附录，内容涵盖 Spring Boot 的方方面面：

- **第1章 启动 Spring Boot**：介绍 Spring Boot 的核心理念、快速入门和第一个 Web 应用。
- **第2章 常用任务**：讲解配置、日志、Profile、命令行参数等日常开发必备技能。
- **第3章 数据访问**：涵盖 Spring Data JPA、事务管理、数据库迁移等数据层实战。
- **第4章 自动配置与监控**：深入自动配置原理，介绍 Actuator 监控与自定义端点。
- **第5章 应用安全**：带你理解 Spring Security 基础，保护 Web 应用安全。
- **第6章 进阶安全**：进阶讲解认证、授权、OAuth2、JWT 等高级安全话题。
- **第7章 REST 服务**：构建 RESTful API，处理序列化、异常、文档化等问题。
- **第8章 响应式开发**：介绍 WebFlux、响应式流、RSocket 等现代响应式技术。
- **第9章 部署**：讲解容器化、Kubernetes、云平台部署等生产环境实战。
- **第10章 Kotlin/Native/GraphQL**：探索 Kotlin、GraalVM Native Image、GraphQL 等新技术在 Spring Boot 中的应用。
- **附录A 生成与构建**：项目脚手架、构建工具、依赖管理等补充说明。
- **附录B Spring MVC 与 Thymeleaf**：传统 MVC 与模板引擎实用技巧。

### 如何使用本项目

本仓库以学习笔记和重述风格整理原书内容，并结合 Spring Boot 3.4.x 与 Java 21 进行现代化代码实现。每章配有可运行的代码样例，便于读者边学边练。

建议阅读顺序：
1. 先通读第1章，了解 Spring Boot 的整体设计与快速上手方法。
2. 根据实际需求，选择感兴趣的章节深入学习。
3. 结合 `samples/` 目录下的代码样例，动手实践每个知识点。

## 快速开始

### 阅读文档

直接浏览左侧目录或[从第1章开始](/chapters/01-introduction)。

### 运行样例代码

示例代码位于 `samples/` 目录下，按章节组织。详见各章节说明。

```bash
# 启动文档站点
pnpm install
pnpm run docs:dev

# 运行第1章示例
cd samples
mvn spring-boot:run -pl ch01/spring-boot-app-demo
```
