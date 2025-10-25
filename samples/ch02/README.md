# 第2章 常用任务

本章示例演示了 Spring Boot 在配置、启动、属性绑定、环境变量、日志与校验等常用场景的用法。

## 示例列表

- Bean Validation 基础（`samples/ch02/bean-validation`）
  - spring-boot-app-start：最小可运行示例，演示 Jakarta Bean Validation 基础约束
  - spring-boot-app-final：完善的校验示例，包含更完整的约束与消息
- Bean Validation 自定义注解（`samples/ch02/bean-validation-custom-annotation`）
  - spring-boot-app-start：定义自定义约束与基础校验流程
  - spring-boot-app-final：完整的自定义约束与 ConstraintValidator 实践
- CommandLineRunner（`samples/ch02/command-line-runner`）
  - start/final：启动即执行命令行任务，演示应用生命周期中的初始化逻辑
- Config Data File（`samples/ch02/config-data-file`）：从 data/ 等位置加载外部化配置
- Configuration Properties（`samples/ch02/configuration-properties`）：使用 `@ConfigurationProperties` 进行类型安全绑定
- OS Env Variables（`samples/ch02/os-env-variables`）：读取操作系统环境变量
- Property Sources（`samples/ch02/property-sources`）：演示多种属性来源及优先级
- Spring Application（`samples/ch02/spring-application`）：常用启动参数与 API 用法
- 默认日志 Default Logging（`samples/ch02/spring-boot-default-logging`）
  - start/final：Spring Boot 默认日志（Logback）配置与输出
- Log4j2（`samples/ch02/spring-boot-logging-with-log4j2`）
  - start/final：切换到 Log4j2 并演示基础配置

## 运行示例

本章所有示例已对齐至：Spring Boot 3.4.1、Java 21、Maven Wrapper 3.9.9（wrapper 3.3.2）。如运行报错，请先确认本机 JDK：`java -version`。

### 方式一：使用 Maven（在项目根目录）

以 Bean Validation 完成版为例：

```bash
cd samples/ch02/bean-validation/spring-boot-app-final
./mvnw spring-boot:run
```

其他示例将路径替换为相应子目录即可（Windows 将 `./mvnw` 改为 `mvnw.cmd`）。

### 方式二：构建并运行 JAR

以 Configuration Properties 为例：

```bash
cd samples/ch02/configuration-properties
./mvnw clean package -DskipTests
java -jar target/*.jar
```

## 访问/验证

- Web 类示例默认端口为 8080；具体端点与返回请查看各模块源码或启动日志。
- Console/Runner/日志类示例主要通过控制台日志与标准输出进行效果验证。

## 技术栈

- Spring Boot 3.4.1
- Java 21
- Maven（Maven Wrapper 3.9.9 / Wrapper 3.3.2）
- Jakarta Bean Validation（在 Boot 3 中使用 `jakarta.validation.*`）

## 学习要点

1. 外部化配置与属性来源：Config Data、Property Sources 的组合与优先级
2. 类型安全配置绑定：`@ConfigurationProperties` 的声明与使用
3. 环境变量与命令行：读取 OS 环境变量、使用 CommandLineRunner 实现启动任务
4. 验证机制：基础约束与自定义注解、`ConstraintValidator` 的实现
5. 日志体系：默认日志（Logback）与 Log4j2 的差异与切换
6. SpringApplication 常用参数与 API：定制应用启动行为
