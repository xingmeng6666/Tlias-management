# Tlias 智能学习辅助系统

一个前后端分离的培训机构管理系统，采用 **Spring Boot + Vue 3** 技术栈开发。

## 项目简介

Tlias 智能学习辅助系统是专为培训机构设计的综合管理平台，提供班级管理、学员管理、部门管理、员工管理、数据统计等核心功能，帮助培训机构实现数字化、智能化管理。

## 项目结构

```
vue-tlias-management/
├── WEB-project/                    # 后端项目 (Spring Boot)
│   ├── tlias-parent/               # 父工程 (Maven 多模块)
│   ├── tlias-pojo/                 # 实体类模块
│   ├── tlias-utils/                # 工具类模块
│   └── tlias-web-management/       # Web 管理模块 (主程序)
│       ├── src/main/java/          # Java 源代码
│       │   ├── controller/         # 控制器层
│       │   ├── service/            # 业务逻辑层
│       │   ├── mapper/             # 数据访问层
│       │   ├── filter/             # 过滤器
│       │   ├── interceptor/        # 拦截器
│       │   ├── aop/                # AOP 切面
│       │   └── config/             # 配置类
│       └── src/main/resources/     # 配置文件
│           ├── application.yml     # 应用配置
│           └── mapper/             # MyBatis 映射文件
│
└── vue-tlias-management/           # 前端项目 (Vue 3)
    ├── src/
    │   ├── api/                    # API 接口定义
    │   ├── assets/                 # 静态资源
    │   ├── components/             # 公共组件
    │   │   ├── AnimatedCharacters.vue  # 动画角色组件
    │   │   ├── EyeBall.vue             # 眼球组件
    │   │   └── Pupil.vue               # 瞳孔组件
    │   ├── router/                 # 路由配置
    │   ├── utils/                  # 工具函数
    │   └── views/                  # 页面视图
    │       ├── index/              # 首页
    │       ├── login/              # 登录页
    │       ├── layout/             # 布局组件
    │       ├── clazz/              # 班级管理
    │       ├── stu/                # 学员管理
    │       ├── dept/               # 部门管理
    │       ├── emp/                # 员工管理
    │       ├── log/                # 日志管理
    │       └── report/             # 数据报表
    │           ├── emp/            # 员工统计
    │           └── stu/            # 学员统计
    ├── index.html
    ├── package.json
    └── vite.config.js
```

## 技术栈

### 后端技术

| 技术 | 说明 |
|------|------|
| Spring Boot 2.x | 应用框架 |
| MyBatis | ORM 框架 |
| MySQL | 关系型数据库 |
| JWT | 身份认证 |
| AOP | 面向切面编程 |
| Maven | 项目管理工具 |

### 前端技术

| 技术 | 说明 |
|------|------|
| Vue 3 | 渐进式 JavaScript 框架 |
| Vite | 构建工具 |
| Vue Router | 路由管理 |
| Pinia | 状态管理 |
| Element Plus | UI 组件库 |
| ECharts | 数据可视化图表 |
| Axios | HTTP 请求库 |

## 功能模块

### 1. 用户认证
- 登录/退出登录
- JWT Token 身份验证
- 动画角色交互登录页面

### 2. 班级管理
- 班级列表查询（支持分页、条件筛选）
- 班级信息新增、编辑、删除
- 班级状态管理

### 3. 学员管理
- 学员列表查询（支持分页、多条件筛选）
- 学员信息新增、编辑、删除
- 批量删除功能
- 违纪处理功能

### 4. 部门管理
- 部门列表查询
- 部门信息新增、编辑、删除

### 5. 员工管理
- 员工列表查询（支持分页、多条件筛选）
- 员工信息新增、编辑、删除
- 员工头像上传
- 批量删除功能

### 6. 日志管理
- 操作日志查询
- 请求参数记录
- 操作耗时统计

### 7. 数据统计报表
- 员工职位分布统计（柱状图 + 饼图）
- 学员班级人数统计（柱状图 + 饼图）
- 学员学历分布统计

## 快速开始

### 环境要求

- JDK 8+
- Maven 3.6+
- Node.js 16+
- MySQL 5.7+ / 8.0

### 后端启动

1. **创建数据库**
   ```sql
   CREATE DATABASE tlias DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   ```

2. **导入数据库脚本**
   根据项目中的 SQL 文件导入表结构和初始数据。

3. **修改数据库配置**
   编辑 `WEB-project/tlias-web-management/src/main/resources/application.yml`：
   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/tlias
       username: your_username
       password: your_password
   ```

4. **启动后端服务**
   ```bash
   cd WEB-project
   mvn spring-boot:run -pl tlias-web-management
   ```
   后端服务默认运行在 `http://localhost:8080`

### 前端启动

1. **安装依赖**
   ```bash
   cd vue-tlias-management
   npm install
   ```

2. **启动开发服务器**
   ```bash
   npm run dev
   ```
   前端服务默认运行在 `http://localhost:5173`

3. **访问系统**
   打开浏览器访问 `http://localhost:5173/login`

## 项目特色

### 动画登录页面
登录页面采用创意性的动画设计，四个可爱的角色会根据用户的输入做出实时反应：
- 输入用户名时：角色眼睛会跟随鼠标移动
- 输入密码时：角色会捂住眼睛偷看
- 登录成功时：角色会开心庆祝，显示彩带效果
- 登录失败时：角色会露出悲伤的表情

### 现代化 UI 设计
- 深色科技风格的 Header
- 响应式侧边栏导航
- 统一的卡片式页面布局
- 流畅的过渡动画效果

### 数据可视化
使用 ECharts 实现丰富的数据统计图表：
- 柱状图展示数据分布
- 饼图展示比例关系
- 支持图表交互和数据下钻

## 项目截图

| 页面 | 截图 |
|------|------|
| 登录页面 | 动画角色交互登录 |
| 首页 | 仪表盘风格首页 |
| 班级管理 | 表格列表 + 搜索筛选 |
| 数据统计 | ECharts 图表展示 |

## 开发团队

- 开发者：xingmeng

## 开源协议

本项目仅供学习交流使用。

## 相关链接

- [Vue 3 文档](https://vuejs.org/)
- [Element Plus 文档](https://element-plus.org/)
- [Spring Boot 文档](https://spring.io/projects/spring-boot)
- [ECharts 文档](https://echarts.apache.org/)
