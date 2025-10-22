# Spring Boot App Demo

这是第1章的基础示例，演示了一个最简单的 Spring Boot Web 应用。

## 项目说明

本示例从原书的 Spring Boot 2.6.3 + Java 17 升级到 Spring Boot 3.4.1 + Java 21，展示了：

### 核心特性
- ✅ 零 XML 配置
- ✅ 内嵌 Tomcat 服务器
- ✅ 自动配置 Web MVC
- ✅ 独立运行（java -jar）
- ✅ 开发者友好的默认配置

### 技术栈
- Spring Boot 3.4.1
- Java 21
- Maven 3.9.9
- JUnit 5

## 项目结构

```
spring-boot-app-demo/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/manning/sbip/ch01/
│   │   │       ├── SpringBootAppDemoApplication.java  # 主入口
│   │   │       └── HelloController.java               # REST 控制器
│   │   └── resources/
│   │       └── application.properties                 # 配置文件
│   └── test/
│       └── java/
│           └── com/manning/sbip/ch01/
│               └── SpringBootAppDemoApplicationTests.java  # 测试
├── pom.xml                                             # Maven 配置
└── README.md                                           # 本文件
```

## 运行方式

### 1. 使用 Maven Wrapper（推荐）

```bash
# 开发模式运行
./mvnw spring-boot:run

# 或在 Windows 上
mvnw.cmd spring-boot:run
```

### 2. 构建并运行 JAR

```bash
# 构建
./mvnw clean package

# 运行
java -jar target/spring-boot-app-demo-0.0.1-SNAPSHOT.jar
```

### 3. 从项目根目录运行

```bash
cd samples/ch01/spring-boot-app-demo
./mvnw spring-boot:run
```

## 测试应用

启动应用后，访问以下 URL：

### 主页
```bash
curl http://localhost:8080/
# 返回: Welcome to Spring Boot Application!
```

### Hello 端点
```bash
curl http://localhost:8080/hello
# 返回: Hello, Spring Boot 3.4 with Java 21!
```

## 运行测试

```bash
./mvnw test
```

测试用例包括：
- 应用上下文加载测试
- REST 端点集成测试

## 关键代码解析

### 主入口类

```java
@SpringBootApplication
public class SpringBootAppDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootAppDemoApplication.class, args);
    }
}
```

`@SpringBootApplication` 是一个组合注解，包含：
- `@SpringBootConfiguration` - 标识为配置类
- `@EnableAutoConfiguration` - 启用自动配置
- `@ComponentScan` - 启用组件扫描

### REST 控制器

```java
@RestController
public class HelloController {
    @GetMapping("/")
    public String home() {
        return "Welcome to Spring Boot Application!";
    }
}
```

`@RestController` = `@Controller` + `@ResponseBody`，自动将返回值序列化为 JSON/文本。

## 升级说明

### 从 Spring Boot 2.x 到 3.x 的变化

1. **Java 版本要求**
   - 最低要求：Java 17
   - 推荐使用：Java 21（LTS）

2. **Jakarta EE 命名空间**
   - `javax.*` → `jakarta.*`
   - 例如：`javax.servlet.*` → `jakarta.servlet.*`

3. **依赖更新**
   - Spring Boot 2.6.3 → 3.4.1
   - Maven 3.6.3 → 3.9.9

4. **新特性**
   - 原生镜像支持（GraalVM）
   - 改进的可观测性
   - 虚拟线程支持（Java 21）

## 常见问题

### Q: 如何修改端口？

在 `application.properties` 中添加：
```properties
server.port=8081
```

### Q: 如何启用调试日志？

```properties
logging.level.root=DEBUG
```

### Q: 需要什么 JDK 版本？

- 最低：JDK 17
- 推荐：JDK 21（本项目使用）

### Q: 启动失败怎么办？

1. 检查 JDK 版本：`java -version`
2. 检查端口占用：`lsof -i :8080` (Mac/Linux) 或 `netstat -ano | findstr :8080` (Windows)
3. 查看详细日志：添加 `--debug` 参数

## 学习要点

通过这个示例，你将学习到：

1. **零配置启动** - 不需要 web.xml 或其他 XML 配置文件
2. **约定优于配置** - Spring Boot 提供合理的默认配置
3. **内嵌服务器** - 无需安装 Tomcat 等应用服务器
4. **独立运行** - 打包成 JAR 即可直接运行
5. **自动配置** - Spring Boot 自动配置必要的组件

## 下一步

- 了解自动配置原理（第4章）
- 添加数据库访问（第3章）
- 实现 RESTful API（第7章）
- 添加安全防护（第5章）

## 相关资源

- [Spring Boot 官方文档](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Spring Boot 3.4 发布说明](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.4-Release-Notes)
- [Java 21 新特性](https://openjdk.org/projects/jdk/21/)
