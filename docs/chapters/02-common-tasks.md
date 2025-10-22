---
title: 第2章 常用任务
original: "Chapter 02: Spring Boot Common Tasks"
samples:
  - path: /samples/ch02
    desc: 常用任务示例入口
---

# 第 2 章 — 常见的 Spring Boot 任务（Common Spring Boot Tasks）

## 本章内容（This chapter covers）

- 在 Spring Boot 应用中管理配置  
- 使用 `@ConfigurationProperties` 创建自定义配置  
- 探索 `CommandLineRunner` 接口以执行初始化代码  
- 理解 Spring Boot 默认日志机制并配置 `Log4j2` 日志  
- 在 Spring Boot 应用中使用 Bean Validation 验证用户数据  

到目前为止，我们已经了解了 Spring Boot 的基本概念，以及它通过抽象底层细节来改善应用开发体验的目的。

在本章中，你将进一步加深对这些概念的理解，学习若干关键内容，包括如何管理应用配置以及为应用创建自定义配置。同时，你还将学习使用 Spring Boot 完成一些常见的开发任务——这些任务在构建 Spring Boot 应用时会经常遇到。

## 2.1 管理配置（Managing Configurations）

应用配置管理是任何应用程序的重要组成部分，Spring Boot 应用也不例外。  
根据你的开发与部署方式，你的应用可能需要在多个环境中运行（例如：`dev`、`test`、`staging`、`prod`）。  
在一个组织中，你可能会有多个运行环境：一个用于开发、一个用于测试、一个用于预发布、一个用于生产。  

在这些不同环境下，应用的核心代码通常保持一致，但配置却需要根据环境进行调整。  
例如，数据库配置或安全配置在不同环境中会有所不同。  
随着应用的功能越来越多、依赖越来越复杂，管理配置的难度也会随之上升。

Spring Boot 提供了多种方法，允许你在不修改源码的前提下实现 **外部化配置**。  
这些方法包括使用属性文件（`property files`）、`YAML` 文件、环境变量以及命令行参数等。  

在接下来的小节中，我们将详细介绍这些方式，并演示如何在 Spring Boot 应用中管理配置。  
如果你希望参考完整代码，可以从 GitHub 仓库下载示例项目。

### 2.1.1 使用 SpringApplication 类（Using the SpringApplication Class）

> 💡 **源码位置**  
> 本节示例项目可以[点击这里](https://github.com/honkinglin/spring-boot-in-practice/tree/main/ch02/spring-application)查看  

你可以使用 Spring Boot 提供的 `SpringApplication` 类在应用中定义配置。  
该类提供了一个名为 `setDefaultProperties()` 的方法，接收一个 `java.util.Properties` 或 `java.util.Map<String, Object>` 实例，用于设置应用属性。  

这种方式适合定义一些**一次性配置**，例如你不希望每次都改动的固定参数。

例如，你可以在 `application.properties` 文件中通过 `spring.config.import` 属性导入额外的配置文件：  

```properties
spring.config.import=classpath:additional-application.properties
```

Spring Boot 会从指定路径加载额外的配置文件。  
如果文件不存在，Spring Boot 会抛出 `ConfigDataLocationNotFoundException`。  
不过，你也可以配置 `spring.config.on-not-found` 属性来指定当文件不存在时的行为，例如：

```java
package com.manning.sbip.ch02;

import java.util.Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootAppDemoApplication {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("spring.config.on-not-found", "ignore");

        SpringApplication application =
                new SpringApplication(SpringBootAppDemoApplication.class);
        application.setDefaultProperties(properties);
        application.run(args);
    }
}
```

如上所示，我们通过 `SpringApplication` 实例调用 `setDefaultProperties()`，并在其中设置了 `spring.config.on-not-found=ignore`。  
这使得当配置文件不存在时，应用不会中断启动过程。

### 2.1.2 使用 @PropertySource 注解（Using @PropertySource）

> 💡 **源码位置**
> 本节示例项目可以[点击这里](https://github.com/honkinglin/spring-boot-in-practice/tree/main/ch02/property-sources)查看

在 Spring 的配置类中，可以使用 `@PropertySource` 注解加载指定路径下的属性文件。  
以下示例展示了如何定义一个配置类来加载数据库配置文件：

```java
package com.manning.sbip.ch02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:dbConfig.properties")
public class DbConfiguration {

    @Autowired
    private Environment env;

    @Override
    public String toString() {
        return "Username: " + env.getProperty("user")
                + ", Password: " + env.getProperty("password");
    }
}
```

上面的代码定义了一个 Spring 配置类，并通过 `@PropertySource` 注解加载了 `dbConfig.properties` 文件中的属性。  
文件内容如下：

```properties
# Listing 2.3 dbConfig.properties
user=sa
password=p@ssw0rd
```

在应用主类中，我们可以获取并打印这些配置属性：

```java
package com.manning.sbip.ch02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBootAppDemoApplication {

    private static final Logger log =
            LoggerFactory.getLogger(SpringBootAppDemoApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                SpringApplication.run(SpringBootAppDemoApplication.class, args);

        DbConfiguration dbConfig = context.getBean(DbConfiguration.class);
        log.info(dbConfig.toString());
    }
}
```

运行应用后，控制台会输出从配置文件中读取到的用户名和密码。

::: tip

**关于 @PropertySource 的补充说明**

* `@PropertySource` 注解**不支持 YAML (`.yml` 或 `.yaml`) 文件**。
  如果希望使用 YAML 文件，需要额外编写代码实现加载逻辑。

* 在 **Java 8 及以上版本**，你可以重复使用多个 `@PropertySource` 注解，以加载多个配置文件，例如：

```java
@Configuration
@PropertySource("classpath:dbConfig.properties")
@PropertySource("classpath:redisConfig.properties")
public class DbConfiguration {
    // ...
}
```

此配置会同时加载 `dbConfig.properties` 与 `redisConfig.properties` 文件中的属性。

:::


