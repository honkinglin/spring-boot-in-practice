---
title: 第3章 数据访问
original: "Chapter 03: Database Access with Spring Data"
samples:
  - path: /samples/ch03
    desc: 数据访问示例入口（JPA/Flyway/Testcontainers 计划）
---

# 第 3 章 使用 Spring Data 进行数据库访问 (Database access with Spring Data)

### 本章内容涵盖

* 介绍 Spring Data 的概念、使用场景及其主要模块；
* 配置关系型数据库与 NoSQL 数据库（如 MongoDB），并在 Spring Boot 应用中访问数据；
* 启用 Spring Data JPA 以使用关系型数据库管理业务对象；
* 通过多种技术访问关系型数据库，包括 `@NamedQuery`、`@Query`、Criteria API 和 Querydsl。

现代应用程序几乎都需要数据库来存储应用数据，Spring Boot 应用也不例外。
在前两章中，我们学习了 Spring Boot 的基础概念、配置方式和运行机制。
有了这些基础，我们可以继续学习如何让应用与数据库交互。

在本章中，你将学习如何使用 **Spring Data** 与数据库通信。
我们将探索其配置方式、数据初始化、数据访问与对象管理的完整流程，
并体会到在 Spring Boot 中执行数据库操作的便捷性。

## 3.1 认识 Spring Data（Introducing Spring Data）

[Spring Data](https://spring.io/projects/spring-data) 是一个可以让你方便访问各种数据源的框架，
包括关系型数据库与非关系型数据库（如 MongoDB）、MapReduce 数据库、以及云端数据服务等。
Spring Data 的目标是：
提供一种统一、简单、熟悉的编程模型，让开发者在 Spring 体系中轻松实现数据访问。

Spring Data 是一个 “**伞形项目（umbrella project）**”，
它包含多个子项目，每个子项目针对特定类型的数据库。

例如：

* **Spring Data JPA**：专注于关系型数据库（H2、MySQL、PostgreSQL 等）；
* **Spring Data MongoDB**：专注于 MongoDB 数据库。

### Java 持久化 API（Java Persistence API, JPA）

在现代应用中，程序通常需要与数据库通信以存储或读取数据。
而传统的 JDBC 方式要求开发者编写大量模板代码，例如：

* 获取数据库连接；
* 定义 `PreparedStatement`；
* 设置参数；
* 执行查询；
* 管理资源关闭。

这些样板化步骤使得代码冗长且难以维护。

::: tip

**JPA（Java Persistence API）** 解决了这一问题。
它在 Java 对象模型（例如业务实体类）与关系数据库模型（如表结构）之间建立了桥梁，
从而实现了 **对象关系映射（Object-Relational Mapping, ORM）**。

如下图所示：

![图 3-1](../assets/3-1.png)

> 图 3.1 对象关系映射示意图
> 实体（Entity）表示业务对象，持久化提供者（Persistence Provider）负责实现 JPA 规范。

JPA 本身只是一个**规范（specification）**，定义了一套接口、类和注解。
它的职责是规范化 ORM 技术，使得开发者可以轻松、统一地进行数据持久化操作。

常见的 JPA 实现包括：

* **Hibernate**：[https://hibernate.org/orm/](https://hibernate.org/orm/)
* **EclipseLink**：[https://www.eclipse.org/eclipselink/#jpa](https://www.eclipse.org/eclipselink/#jpa)

这些框架提供了对 JPA 规范的具体实现，从而让我们能够在 Spring 应用中轻松地完成数据库持久化与对象操作。

:::
