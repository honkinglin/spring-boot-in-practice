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

[Spring Data](https://spring.io/projects/spring-data) 是一个可以让你方便访问各种数据源的框架，包括关系型数据库与非关系型数据库（如 MongoDB）、MapReduce 数据库、以及云端数据服务等。

Spring Data 的目标是：  
提供一种统一、简单、熟悉的编程模型，让开发者在 Spring 体系中轻松实现数据访问。

Spring Data 是一个 “**伞形项目（umbrella project）**”，它包含多个子项目，每个子项目针对特定类型的数据库。

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
它在 Java 对象模型（例如业务实体类）与关系数据库模型（如表结构）之间建立了桥梁，从而实现了 **对象关系映射（Object-Relational Mapping, ORM）**。

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

### 3.1.1 为什么选择 Spring Data（Why Spring Data）

Spring Data 的核心主题之一是提供一种一致的编程模型，用于访问各种数据源。  
因此，它提供了一个便捷的 API，使开发者可以为业务领域对象指定元数据（metadata），并确保这些对象能够被持久化到指定的数据存储中。

例如，你可以使用关系型数据库，并通过 **Spring Data JPA** 来管理业务对象。  
你可以在业务对象中添加 JPA 注解，而 Spring Data JPA 会确保这些领域对象被正确地持久化到数据库表中。  
在本章的后续部分中，你将会看到许多这类注解及其在业务对象中的用法。

Spring Data 各模块还以模板（template）类的形式提供 API，这种模式与 Spring 中常见的 `JdbcTemplate` 或 `JmsTemplate` 十分类似。  
例如，如果你使用的是 MongoDB，就可以使用 `MongoTemplate` 来在 MongoDB 数据库中执行各种操作。  
这些模板类提供了多种辅助方法，用于处理存储资源管理和异常转换（exception translation）等常见任务。

::: tip

**Spring templates**

Spring 模板类消除了原本在使用常见 API（例如 JDBC、JMS、JNDI）时所需编写的大量样板代码。  
这些样板代码通常包括：建立数据库连接、创建 `PreparedStatement`、执行查询、处理异常、关闭连接等。  
而 Spring 的模板类封装了这些步骤，让开发者可以专注于核心业务逻辑。

例如，使用 `JdbcTemplate` 时，你只需要提供要执行的 SQL 查询语句，其余的连接、执行、资源关闭等流程都由模板自动完成。

:::

Spring Data 还在所有支持的数据库之上提供了一个**仓库抽象层（repository abstraction layer）**，该抽象层为开发者提供了统一的编程模型。  
这个抽象层包含在 **Spring Data Commons** 模块中，并提供了一系列有用的接口，这些接口可用于执行标准的 CRUD 操作（创建 Create、读取 Read、更新 Update、删除 Delete）以及执行查询。

该抽象层位于 Spring Data 的最上层，同时也是其他所有 Spring Data 模块的基础。

### 3.1.2 Spring Data 模块（Spring Data Modules）

在前一节中，你已经了解了 Spring Data 的作用。  
在本节中，我们将进一步学习 Spring Data 的各个模块。  
你可以参考官方文档获取完整模块列表：
[https://spring.io/projects/spring-data](https://spring.io/projects/spring-data)

::: tip

**Spring Data modules**

Spring Data 是一个为多种主流数据存储系统提供支持的“伞形项目（umbrella project）”。   
表 3.1 总结了其中几个常用模块及其用途。

| 模块名称                                 | 用途                             |
| ------------------------------------ | ------------------------------ |
| **Spring Data Commons**              | 包含所有 Spring Data 项目中使用的基础组件。   |
| **Spring Data JDBC**                 | 提供基于 JDBC 的仓库支持。               |
| **Spring Data JPA**                  | 提供基于 JPA 的仓库支持。                |
| **Spring Data MongoDB**              | 提供基于文档的 MongoDB 数据库支持。         |
| **Spring Data REDIS**                | 提供 Redis 数据存储的支持。              |
| **Spring Data REST**                 | 允许将 Spring Data 仓库暴露为 REST 资源。 |
| **Spring Data for Apache Cassandra** | 提供 Apache Cassandra 数据库支持。     |

你可以参考 [Spring Data Reference](https://spring.io/projects/spring-data) 文档，查看完整的 Spring Data 项目列表。

:::

在所有模块中，**Spring Data Commons** 模块是最重要的一个。  
它由 Spring Data 的基础组件组成，这些组件与具体数据源无关，并被其他 Spring Data 模块复用。

例如：

* Spring Data JPA 模块依赖于 Spring Data Commons；
* Spring Data JPA 的 `JpaRepository` 接口是 Spring Data Commons 模块中 `PagingAndSortingRepository` 接口的子接口；
* 而 `PagingAndSortingRepository` 又继承了 `CrudRepository`；
* 通过这种继承关系，Spring Data Commons 提供了 CRUD、分页与排序的核心功能。

#### 图 3.2 Spring Data 模块结构（Spring Data Modules）

![图 3-2](../assets/3-2.png)

> **图 3.2** Spring Data 模块结构：
> Spring Data Commons 模块为其他子模块提供了基础，
> 每个子模块都面向特定类型的数据库（例如 MySQL、PostgreSQL、MongoDB）。
> `Repository`、`CrudRepository` 和 `PagingAndSortingRepository` 都是由 Commons 模块定义的接口。

Spring Data 的子模块包含针对特定数据库技术（例如 JDBC、JPA）或供应商（例如 MongoDB、Cassandra）的实现。  
这些子模块利用 Spring Data Commons 提供的核心框架特性，从而在不同数据库之间保持统一的编程体验。
