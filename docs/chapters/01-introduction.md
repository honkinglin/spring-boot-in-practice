---
title: 第1章 启动 Spring Boot
original: "Chapter 01: Booting Spring Boot"
samples:
  - path: /samples/ch01/web-api
    desc: 起步示例（Web API）
---

# 第1章 启动 Spring Boot

本章将介绍 Spring Boot 的核心理念、设计哲学和快速入门方法。你将了解 Spring Boot 如何简化 Spring 应用开发，以及如何创建第一个可运行的 Web 应用。

## 本章内容

- Spring Boot 简介与核心特性
- 为什么选择 Spring Boot
- Spring Boot 的约定优于配置（Convention over Configuration）
- 创建第一个 Spring Boot Web 应用
- 理解 Spring Boot 的启动流程

## 配套示例

本章配套示例位于 `samples/ch01/web-api`，演示了一个最简单的 Spring Boot Web API 应用。

运行示例：
```bash
cd samples
mvn spring-boot:run -pl ch01/web-api
```

访问 http://localhost:8080/hello 查看运行效果。

---

## 1.1 认识 Spring Boot（Introducing Spring Boot）

在本节中，我们会带你快速了解 **Spring Boot 框架**，并回答一些常见问题，比如：
- 为什么需要 Spring Boot？
- 它解决了什么问题？
- 它有哪些核心特性？
- 以及 Spring Boot 框架主要由哪些部分组成。


### 1.1.1 为什么选择 Spring Boot？（Why Spring Boot?）

在正式开始之前，先问自己一个问题：**我们为什么要学 Spring Boot？**

要回答这个问题，得先了解 Spring Boot 诞生时想要解决的痛点。


#### Spring Framework 的起点

Spring Framework 的初衷是为了**简化 Java 企业级应用开发（Java Enterprise Application）**。

由于它的应用开发方式简单、灵活，很快在企业开发中流行起来。

但随着项目规模和复杂度的提升，**Spring 应用的配置与管理成本也随之上升**，于是进一步简化 Spring 的需求变得迫切。


#### 传统 Spring 的痛点

Spring 虽然极大地帮助开发者专注于业务逻辑本身，但在 Spring Boot 出现之前，仍然存在大量「框架层面的繁琐工作」需要开发者自己处理。

比如，使用 Spring 开发 Web 应用时常见的挑战包括：

* 需要理解 **Servlet** 以及 `web.xml` 部署描述文件的写法
* 熟悉 **WAR**、**EAR** 打包结构和相关配置
* 了解应用服务器的部署机制（例如域、端口、线程池、数据源等）
* 面对复杂的类加载机制、监控、管理和日志配置

简而言之，配置过多、启动复杂。

假如你只是想写个业务逻辑、打个包、执行一下，为什么还得研究半天 XML 和容器配置？


#### Spring Boot 的出现

于是，Spring Boot 横空出世。

它就像一位魔法师，帮你把那些复杂配置都藏在幕后。

开发者只需关注业务逻辑，不再需要编写 `web.xml`、也无需手动部署到应用服务器上。

Spring Boot 默认的自动配置几乎能满足大多数场景。


### 1.1.2 什么是 Spring Boot？（What is Spring Boot?）

**Spring Boot** 于 **2014 年 4 月** 首次发布，目标是**简化 Java Web 应用的开发流程**。

它的核心理念是：

> “让开发者专注于业务逻辑，而不是框架配置。”

Spring Boot 在传统 Spring 框架之上又加了一层封装，形成一个**独立、生产级（production-ready）**的应用开发平台。

开发者不需要编写大量模板代码（boilerplate）和复杂配置，就能快速启动项目。

Spring Boot 是一个「有自己主见（opinionated）」的框架 —— 它为你决定了合理的默认配置，让你几乎可以**开箱即用（out of the box）**。

当然，如果需要自定义，也完全可以覆盖默认设置。


#### Spring Boot 在生态中的位置

可以把 Spring Boot 想象成一个夹心层（intermediate layer），它位于 **开发者（你）** 与 **Spring Framework** 之间：

![图 1.1](../assets/1-1.png)

Spring Boot 通过默认配置和自动化机制，屏蔽了 Spring 的底层复杂性，帮助开发者更轻松地上手 Spring 生态。


### 1.1.3 Spring Boot 的核心特性（Spring Boot core features）

Spring Boot 之所以流行，是因为它有以下几个突出的核心特性：


#### 🚀 快速启动（Fast Bootstrapping）

Spring Boot 最大的目标之一就是让你能**快速启动一个 Spring 应用**。

在传统方式下，要启动一个 Web 项目，通常要经历这些步骤：

1. 用 Maven 或 Gradle 创建一个带有 Spring MVC 依赖的项目
2. 配置 Spring 的 `DispatcherServlet`
3. 将应用打包为 WAR 文件
4. 部署到一个应用服务器（例如 Tomcat）

而使用 Spring Boot，只需指定依赖，运行 `main()` 方法即可启动，Tomcat 已自动内嵌。

无需额外配置或外部容器。


#### ⚙️ 自动配置（Autoconfiguration）

Spring Boot 会根据你项目中存在的依赖自动完成配置。

例如，如果它检测到 classpath 中存在 H2 数据库的驱动 JAR，它就会自动配置对应的数据源。

不再需要写繁琐的 XML。


#### 🧭 有主见的默认配置（Opinionated）

Spring Boot 为不同类型的应用提供了默认的「起点配置（starter dependencies）」。

比如，如果你在 `pom.xml` 里添加了 `spring-boot-starter-web`，它会自动引入开发 Web 应用所需的全部依赖（如 `spring-web`、`spring-webmvc` 等）。


#### 🧱 独立运行（Standalone）

Spring Boot 应用内嵌了 Web 服务器（如 Tomcat、Jetty），可以直接打包成 **可执行 JAR**，通过 `java -jar` 命令启动，无需部署到外部服务器。这也让 Spring Boot 天然适合容器化和云原生（cloud-native）部署。


#### 🧩 生产可用（Production-ready）

Spring Boot 内置了多种生产环境可用的功能，例如健康检查（health check）、线程转储（thread dump）、运行指标（metrics）等。

这些都可以通过 **Actuator** 模块轻松实现。


### 1.1.4 Spring Boot 组件（Spring Boot components）

Spring Boot 由多个组件组成，每个组件负责不同的功能领域。

其中一些是核心组件，几乎在所有 Spring Boot 项目中都会使用。

![图 1.2](../assets/1-2.png)

主要组件如下：

#### 🔸 `spring-boot`

Spring Boot 的核心模块，提供应用的基础支持。

它包含 `SpringApplication` 类，用于创建独立运行的 Spring Boot 应用。

此外，它还支持嵌入式 Web 服务器（例如 Tomcat）和外部化配置（如数据库配置）。


#### 🔸 `spring-boot-autoconfigure`

为 Spring Boot 提供自动配置功能。

Spring Boot 会根据 classpath 中的依赖自动推测（guess）所需的 bean 配置。

如果检测到用户自定义配置，自动配置会智能地让位（back off）。


#### 🔸 `spring-boot-starters`

预打包的依赖集合，方便开发者快速引入常用依赖。

例如 `spring-boot-starter-web` 会自动包含所有 Web 开发所需的依赖。


#### 🔸 `spring-boot-cli`

命令行工具，可直接运行 Groovy 脚本并监控文件变更，自动重启应用。

它能快速原型化（prototype）一个 Spring 应用，而不需要 Maven/Gradle 管理依赖。


#### 🔸 `spring-boot-actuator`

用于监控和管理应用的模块，提供一组内置的监控端点（如 `/actuator/health`、`/actuator/metrics`）。

你也可以自定义端点并控制访问权限。


#### 🔸 `spring-boot-actuator-autoconfigure`

自动配置 Actuator 模块，例如当 classpath 中存在 `Micrometer` 依赖时，会自动配置 `MetricsEndpoint`。


#### 🔸 `spring-boot-test` 与 `spring-boot-test-autoconfigure`

用于编写和自动配置 Spring Boot 的测试用例，支持注解和测试工具集成。


#### 🔸 `spring-boot-loader`

负责将 Spring Boot 应用打包为可执行 JAR，包含所有依赖和嵌入式服务器。

通常与 Maven/Gradle 插件一起使用，不单独操作。


#### 🔸 `spring-boot-devtools`

开发辅助工具包，用于提升开发体验。

支持热更新（hot reload）、自动刷新浏览器（LiveReload）等功能，大大提高开发效率。


## 1.2 第一个 Spring Boot 应用

在这一节里，我们会讨论本书中使用的代码示例，以及我们将用到的相关技术。

我们会聊到构建系统（build system）、编程语言，以及本书中所使用的数据库。

除此之外，还会介绍一下 **Lombok** —— 它可以通过简单的注解，帮我们减少 POJO 类（Plain Old Java Object）的样板代码。

### 1.2.1 Maven vs. Gradle

Spring Boot 支持使用 **Apache Maven**（[https://maven.apache.org/）或](https://maven.apache.org/）或) **Gradle**（[https://gradle.org/）来创建项目。](https://gradle.org/）来创建项目。)

在 **Spring Initializr**（[https://start.spring.io/）](https://start.spring.io/）) 工具中，你可以自由选择要用哪种构建系统来生成项目。

在本书的示例中，我们主要使用 **Maven**，因为大多数读者对 Maven 更熟悉。

不过如果你是 Gradle 用户，也不用担心——把示例项目迁移到 Gradle 基本是无缝的。


### 1.2.2 Java vs. Kotlin

在 Spring Boot 项目中，你可以选择使用 **Java** 或 **Kotlin**（[https://kotlinlang.org/）](https://kotlinlang.org/）) 作为编程语言。
从 Spring Framework 5.0 开始，官方就内置了对 Kotlin 的支持，而且 Spring 团队也一直在持续增强 Kotlin 的生态。
举个例子，在 **Spring Security 5.3** 中，他们甚至为 Spring Security 的 DSL（领域特定语言）提供了 Kotlin 版本。

如果想了解更多关于 Spring 对 Kotlin 的支持，可以参考官方文档链接：[http://mng.bz/Bxw8。](http://mng.bz/Bxw8。)

在本书的示例中，我们主要还是使用 **Java** 来讲解。
不过在第 10 章中，我们也会介绍 Spring Framework（特别是 Spring Boot）中一些主要的 **Kotlin 特性**。


### 1.2.3 数据库支持（Database support）

书中有不少示例需要用到数据库操作来演示相关概念。
Spring Boot 本身对多种 SQL 和 NoSQL 数据库都提供了支持。

为了方便演示和测试，本书的示例主要使用 **H2 内存数据库（H2 in-memory SQL database）**。
当然，也会有少量示例使用其他数据库（少数例外情况会特别说明）。


### 1.2.4 Lombok

**Lombok**（[https://projectlombok.org/）](https://projectlombok.org/）) 是一个非常实用的 Java 库，它可以通过注解自动生成 **构造器、Getter、Setter、toString** 等方法。
换句话说，只要在普通的 Java POJO 类上加上几个注解，就能省掉大量重复代码。

比如：

* 给类的成员变量生成 getter 方法，只需要加上 `@Getter` 注解；
* 同理，`@Setter`、`@ToString` 也都是类似的用法。

我们在本书的示例代码中会广泛使用 Lombok。

如果你不想依赖第三方库（比如出于可移植性或兼容性考虑），也可以手动编写 getter、setter、构造函数。
示例代码在两种情况下都能正常运行。


::: tip

Record（Java 14 新特性）

从 **Java 14** 开始，语言层面引入了 **record** 概念。
`record` 是一种**不可变（immutable）**的数据类，只需要声明字段的类型和名称，编译器就会自动生成常见方法，比如 `equals()`、`hashCode()` 和 `toString()`。

除此之外，编译器还会自动为每个字段生成：

* `private final` 成员变量
* 对应的 getter 方法
* `public` 构造函数

如果你不想依赖像 Lombok 这样的第三方库，也可以直接使用 Java 原生的 `record`。

定义示例如下：

```java
public record Course(int id, String name, String description, int rating) {}
```

编译器会自动生成所有字段对应的构造函数，以及与字段名相同的 getter 方法（例如 `id()`, `name()` 等），并同时实现 `equals()` 和 `hashCode()`。

更多关于 Java record 的资料，可以参考链接：[http://mng.bz/donO。](http://mng.bz/donO。)

:::


## 1.3 理解自动配置

（待补充：Spring Boot 自动配置的工作原理）

## 小结

（待补充：本章要点总结）
