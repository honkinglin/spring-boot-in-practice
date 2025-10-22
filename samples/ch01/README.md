# 第1章 启动 Spring Boot

本章示例演示了最基础的 Spring Boot 应用。

## 示例列表

### spring-boot-app-demo

最基础的 Spring Boot Web 应用，演示了：
- Spring Boot 3.4.1 + Java 21
- 自动配置
- 内嵌 Tomcat 服务器
- 简单的 REST 端点

## 运行示例

### 方式一：使用 Maven（在项目根目录）

```bash
cd samples/ch01/spring-boot-app-demo
./mvnw spring-boot:run
```

### 方式二：构建并运行 JAR

```bash
cd samples/ch01/spring-boot-app-demo
./mvnw clean package
java -jar target/spring-boot-app-demo-0.0.1-SNAPSHOT.jar
```

## 访问应用

应用启动后，访问以下端点：

- http://localhost:8080/ - 欢迎页面
- http://localhost:8080/hello - Hello 端点

## 技术栈

- Spring Boot 3.4.1
- Java 21
- Maven
- 内嵌 Tomcat

## 学习要点

1. **零配置启动**：无需 web.xml 或其他 XML 配置
2. **内嵌服务器**：不需要外部应用服务器
3. **自动配置**：Spring Boot 自动配置 Web MVC 组件
4. **快速开发**：添加依赖即可使用相关功能

