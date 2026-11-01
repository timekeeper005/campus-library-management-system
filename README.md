# 校园图书借阅管理系统

这是一个轻量版的 Spring Boot 学习项目，聚合了 Spring MVC、MyBatis、Spring Security 和数据库交互，满足校园图书借阅管理的核心功能：

- 图书查询
- 借阅与归还
- 用户权限管理（学生 / 管理员）
- 操作日志记录

## 技术栈

- Java 17
- Spring Boot 3.2.5
- Spring MVC
- MyBatis
- Spring Security
- H2 Database（轻量本地开发数据库）

## 运行方式

1. 确认已安装 JDK 17 和 Maven
2. 在项目根目录执行：

```bash
mvn clean package
java -jar target/campus-library-management-system-1.0.0.jar
```

3. 启动后访问：

- API: http://localhost:8080/api/books
- H2 Control Console: http://localhost:8080/h2-console

## 默认账号

- 管理员：`admin` / `admin123`
- 学生：`student` / `student123`

## 接口说明

### 图书查询

- GET `/api/books?keyword=java`
- GET `/api/books/{id}`

### 借阅

- POST `/api/borrow/{bookId}`

### 归还

- POST `/api/return/{borrowRecordId}`

### 我的借阅记录

- GET `/api/borrow/my`

### 管理员添加图书

- POST `/api/admin/books`

### 管理员更新图书

- PUT `/api/admin/books/{id}`

### 操作日志

- GET `/api/logs`

## 数据库说明

项目使用 H2 内存数据库，启动后会自动执行：

- `schema.sql`
- `data.sql`

这样可快速演示完整功能，适合学习和课程作业。 

## 适用场景

- 学习 Java 后端整合技术
- Spring Boot + MyBatis + Security 的入门项目
- 课程作业或 Demo 演示
- 作为后续扩展成完整图书馆管理系统的基础
