# 🚀 Boundesu Words SDK 高级功能指南

<div align="center">

![Version](https://img.shields.io/badge/version-1.0.0-blue.svg)
![Java](https://img.shields.io/badge/Java-17+-orange.svg)
![License](https://img.shields.io/badge/license-MIT-green.svg)
![Status](https://img.shields.io/badge/status-stable-brightgreen.svg)

**专业级文档自动化生成解决方案**

[🏠 返回主页](README.md) | [📚 基础指南](README.md) | [🤝 贡献指南](CONTRIBUTING.md) | [📝 更新日志](CHANGELOG.md)

</div>

---

## 📋 目录

- [概述](#-概述)
- [核心组件](#️-核心组件)
- [快速开始](#-快速开始)
- [高级文档生成器](#-高级文档生成器)
- [文档模板生成器](#-文档模板生成器)
- [演示程序](#-演示程序)
- [功能特色](#-功能特色)
- [高级配置](#-高级配置)
- [测试和验证](#-测试和验证)
- [最佳实践](#-最佳实践)
- [扩展开发](#-扩展开发)
- [性能优化](#-性能优化)
- [故障排除](#-故障排除)
- [技术支持](#-技术支持)

## 🎯 概述

Boundesu Words SDK 高级功能模块是一个强大的文档自动化生成解决方案，专为企业级应用设计。它提供了丰富的文档创建功能，包括自动生成封面页、目录、各种类型的专业文档模板等，帮助开发者快速构建高质量的文档处理应用。

### 🌟 核心优势

- **🤖 高度自动化**: 一键生成专业文档，无需手动排版
- **📋 丰富模板**: 内置多种行业标准文档模板
- **🎨 专业样式**: 自动应用专业的文档格式和样式
- **🌏 中文友好**: 完美支持中文内容和格式
- **⚡ 高性能**: 优化的内存使用和处理速度
- **🔧 易于集成**: 简洁的API设计，支持链式调用

### 📊 功能矩阵

| 功能类别 | 功能项 | 状态 | 描述 |
|---------|--------|------|------|
| **文档结构** | 封面页生成 | ✅ | 自动生成专业封面页 |
| | 目录生成 | ✅ | 基于章节结构自动生成目录 |
| | 页眉页脚 | ✅ | 自定义页眉页脚设置 |
| | 页码管理 | ✅ | 自动页码和页面管理 |
| **文档类型** | 技术文档 | ✅ | 技术方案、架构设计文档 |
| | 项目计划 | ✅ | 项目计划和进度管理文档 |
| | 会议纪要 | ✅ | 会议记录和行动项跟踪 |
| | API文档 | ✅ | 接口文档和使用说明 |
| | 用户手册 | ✅ | 产品使用指南和帮助文档 |
| | 测试报告 | ✅ | 测试结果和质量报告 |
| **高级功能** | 章节管理 | ✅ | 多级章节和标题管理 |
| | 样式定制 | ✅ | 字体、颜色、间距等样式设置 |
| | 数据绑定 | ✅ | 动态数据填充和模板渲染 |
| | 批量生成 | ✅ | 批量文档生成和处理 |

## 🏗️ 核心组件

### 架构概览

```
┌─────────────────────────────────────────────────────────────┐
│                    Boundesu Words SDK                       │
│                     高级功能模块                              │
├─────────────────────────────────────────────────────────────┤
│  ┌─────────────────────┐  ┌─────────────────────────────────┐ │
│  │ AdvancedDocument    │  │ DocumentTemplate                │ │
│  │ Generator           │  │ Generator                       │ │
│  │                     │  │                                 │ │
│  │ • 封面页生成         │  │ • 项目计划模板                   │ │
│  │ • 目录生成          │  │ • API文档模板                   │ │
│  │ • 章节管理          │  │ • 用户手册模板                   │ │
│  │ • 页眉页脚          │  │ • 测试报告模板                   │ │
│  │ • 文档属性          │  │ • 会议纪要模板                   │ │
│  └─────────────────────┘  └─────────────────────────────────┘ │
├─────────────────────────────────────────────────────────────┤
│                    基础文档创建层                              │
│  ┌─────────────────────────────────────────────────────────┐ │
│  │           DocumentCreator 接口                          │ │
│  │  ┌─────────────┐ ┌─────────────┐ ┌─────────────────────┐ │ │
│  │  │ POI Direct  │ │ HTML to     │ │ XML to DOCX         │ │ │
│  │  │ Creator     │ │ DOCX        │ │ Creator             │ │ │
│  │  └─────────────┘ └─────────────┘ └─────────────────────┘ │ │
│  └─────────────────────────────────────────────────────────┘ │
├─────────────────────────────────────────────────────────────┤
│                     Apache POI 层                           │
│  ┌─────────────────────────────────────────────────────────┐ │
│  │              Apache POI 5.4.1                          │ │
│  │         (XWPF, HWPF, HSSF, XSSF)                       │ │
│  └─────────────────────────────────────────────────────────┘ │
└─────────────────────────────────────────────────────────────┘
```

### 1. AdvancedDocumentGenerator - 高级文档生成器

**位置**: `src/main/java/com/boundesu/words/sdk/advanced/AdvancedDocumentGenerator.java`

这是核心的高级文档生成器，提供了丰富的文档创建功能。

#### 🔧 主要功能

- ✅ **自动生成封面页** - 专业的文档封面，支持标题、作者、日期、公司信息
- ✅ **自动生成目录** - 基于章节结构的目录，支持多级标题和页码
- ✅ **章节管理** - 多级章节和标题管理，支持自动编号
- ✅ **页眉页脚设置** - 自定义页眉页脚，支持页码、日期、文档信息
- ✅ **文档属性配置** - 标题、作者、主题、关键词等元数据管理
- ✅ **样式管理** - 字体、颜色、间距等样式的统一管理

#### 📝 类结构

```java
public class AdvancedDocumentGenerator {
    
    // 文档基本信息
    private String title;
    private String author;
    private String subject;
    private String company;
    private LocalDate date;
    
    // 样式配置
    private String fontFamily = "微软雅黑";
    private int fontSize = 12;
    private double lineSpacing = 1.15;
    
    // 文档结构
    private List<Chapter> chapters;
    private boolean includeCoverPage = true;
    private boolean includeTableOfContents = true;
    
    // 核心方法
    public AdvancedDocumentGenerator setTitle(String title);
    public AdvancedDocumentGenerator setAuthor(String author);
    public AdvancedDocumentGenerator generateCoverPage();
    public AdvancedDocumentGenerator generateTableOfContents();
    public AdvancedDocumentGenerator addChapter(String title, String content);
    public void createDocument(Path outputPath) throws IOException;
}
```

### 2. DocumentTemplateGenerator - 文档模板生成器

**位置**: `src/main/java/com/boundesu/words/sdk/advanced/DocumentTemplateGenerator.java`

提供预定义的专业文档模板，快速生成标准化文档。

#### 🎨 可用模板

| 模板类型 | 方法名 | 适用场景 | 特色功能 |
|---------|--------|----------|----------|
| 项目计划 | `generateProjectPlan()` | 项目管理 | 时间线、里程碑、交付物 |
| API文档 | `generateApiDocument()` | 接口文档 | 端点列表、参数说明、示例 |
| 用户手册 | `generateUserManual()` | 产品文档 | 功能介绍、操作步骤、FAQ |
| 测试报告 | `generateTestReport()` | 质量保证 | 测试结果、问题跟踪、统计 |
| 会议纪要 | `generateMeetingMinutes()` | 会议管理 | 议程、参与者、行动项 |
| 技术方案 | `generateTechnicalDocument()` | 技术文档 | 架构设计、实现方案、总结 |

## 🚀 快速开始

### 环境准备

确保您的项目已经包含了Boundesu Words SDK的依赖：

```xml
<dependency>
    <groupId>com.boundesu</groupId>
    <artifactId>boundesu-words-jdk17</artifactId>
    <version>1.0.0</version>
</dependency>
```

### 第一个高级文档

```java
import com.boundesu.words.sdk.advanced.AdvancedDocumentGenerator;
import java.nio.file.Paths;

public class QuickStartExample {
    public static void main(String[] args) throws Exception {
        // 创建高级文档生成器
        AdvancedDocumentGenerator generator = new AdvancedDocumentGenerator();
        
        // 设置文档基本信息
        generator.setTitle("我的第一个高级文档")
                 .setAuthor("开发者")
                 .setSubject("快速入门示例")
                 .setCompany("我的公司");
        
        // 生成文档结构
        generator.generateCoverPage()                    // 生成封面页
                 .generateTableOfContents()              // 生成目录
                 .addChapter("概述", "这是文档的概述部分...")
                 .addChapter("详细内容", "这里是详细的内容...")
                 .addChapter("总结", "文档的总结部分...");
        
        // 生成文档
        generator.createDocument(Paths.get("my-first-advanced-document.docx"));
        
        System.out.println("文档生成成功！");
    }
}
```

### 运行结果

生成的文档将包含：
1. **专业封面页** - 包含标题、作者、公司、日期
2. **自动目录** - 基于章节结构生成，包含页码
3. **章节内容** - 格式化的章节内容，自动编号
4. **页眉页脚** - 包含文档信息和页码

## 📖 高级文档生成器

### 基本使用方法

#### 1. 创建和配置

```java
// 创建生成器实例
AdvancedDocumentGenerator generator = new AdvancedDocumentGenerator();

// 设置文档基本信息
generator.setTitle("技术方案文档")
         .setAuthor("技术团队")
         .setSubject("系统架构设计")
         .setCompany("科技有限公司")
         .setDate(LocalDate.now());

// 设置文档样式
generator.setFontFamily("微软雅黑")
         .setFontSize(12)
         .setLineSpacing(1.15);
```

#### 2. 生成文档结构

```java
// 生成封面页
generator.generateCoverPage();

// 生成目录（会自动根据章节生成）
generator.generateTableOfContents();

// 添加章节内容
generator.addChapter("系统概述", "本章介绍系统的整体架构和设计理念...");
generator.addChapter("技术选型", "本章详细说明技术栈的选择和原因...");
generator.addSubChapter("前端技术", "前端采用React + TypeScript技术栈...");
generator.addSubChapter("后端技术", "后端采用Spring Boot + MySQL技术栈...");

// 设置页眉页脚
generator.setHeader("技术方案文档")
         .setFooter("版权所有 © 2024 科技有限公司");
```

#### 3. 生成文档

```java
// 生成到文件
generator.createDocument(Paths.get("technical-document.docx"));

// 或者生成到字节数组（用于Web应用）
byte[] documentBytes = generator.createDocumentAsBytes();
```

### 专业文档类型

#### 1. 技术文档生成

```java
// 准备章节内容
Map<String, String> sections = new LinkedHashMap<>();
sections.put("系统架构", """
    ## 整体架构
    
    系统采用微服务架构，主要包括以下几个核心服务：
    
    ### 用户服务
    - 用户注册、登录、权限管理
    - 基于JWT的身份认证
    - 支持多种登录方式
    
    ### 业务服务
    - 核心业务逻辑处理
    - 数据持久化
    - 业务规则引擎
    
    ### 网关服务
    - 统一入口和路由
    - 限流和熔断
    - 监控和日志
    """);

sections.put("技术选型", """
    ## 技术栈选择
    
    ### 后端技术
    - **框架**: Spring Boot 2.7.18
    - **数据库**: MySQL 8.0 + Redis 6.2
    - **消息队列**: RabbitMQ 3.9
    - **搜索引擎**: Elasticsearch 7.17
    
    ### 前端技术
    - **框架**: React 18 + TypeScript
    - **状态管理**: Redux Toolkit
    - **UI组件**: Ant Design
    - **构建工具**: Vite
    
    ### 运维技术
    - **容器化**: Docker + Kubernetes
    - **监控**: Prometheus + Grafana
    - **日志**: ELK Stack
    - **CI/CD**: Jenkins + GitLab
    """);

sections.put("实现方案", """
    ## 详细实现
    
    ### 数据库设计
    - 采用分库分表策略
    - 读写分离架构
    - 数据备份和恢复机制
    
    ### 缓存策略
    - 多级缓存架构
    - 缓存一致性保证
    - 缓存穿透和雪崩防护
    
    ### 安全方案
    - HTTPS全站加密
    - SQL注入防护
    - XSS和CSRF防护
    - 敏感数据加密存储
    """);

// 生成技术文档
generator.generateTechnicalDocument(
    "微服务架构设计方案",
    "架构师团队",
    "本文档详细描述了系统的微服务架构设计方案，包括技术选型、实现方案和部署策略。",
    sections,
    "通过本方案，我们能够构建一个高性能、高可用、可扩展的微服务系统，满足业务快速发展的需求。"
);
```

#### 2. 会议纪要生成

```java
// 会议基本信息
String meetingTitle = "项目周会";
String meetingDate = "2024-01-15";
String meetingLocation = "会议室A / 腾讯会议";

// 参会人员
List<String> attendees = Arrays.asList(
    "张三 - 项目经理",
    "李四 - 技术负责人", 
    "王五 - 前端开发",
    "赵六 - 后端开发",
    "钱七 - 测试工程师",
    "孙八 - 产品经理"
);

// 会议议程
List<String> agenda = Arrays.asList(
    "1. 上周工作总结",
    "2. 本周工作计划",
    "3. 技术难点讨论",
    "4. 项目风险评估",
    "5. 下阶段规划"
);

// 行动项
List<String> actionItems = Arrays.asList(
    "完成用户模块API设计文档 - 李四 - 本周三前 - 高优先级",
    "搭建前端开发环境和脚手架 - 王五 - 本周五前 - 中优先级",
    "完成数据库表结构设计 - 赵六 - 下周一前 - 高优先级",
    "编写测试用例和测试计划 - 钱七 - 下周三前 - 中优先级",
    "更新产品需求文档 - 孙八 - 本周四前 - 高优先级"
);

// 会议讨论要点
Map<String, String> discussions = new LinkedHashMap<>();
discussions.put("技术架构讨论", """
    经过讨论，确定采用微服务架构：
    - 服务拆分策略：按业务领域拆分
    - 通信方式：HTTP REST + 消息队列
    - 数据一致性：最终一致性 + 补偿机制
    - 监控方案：链路追踪 + 指标监控
    """);

discussions.put("项目进度", """
    当前进度总体正常：
    - 需求分析：已完成 100%
    - 架构设计：已完成 80%，预计本周完成
    - 开发环境：已完成 60%，预计下周完成
    - 详细设计：计划下周开始
    """);

discussions.put("风险识别", """
    识别到以下风险点：
    - 第三方API稳定性风险 - 需要准备备用方案
    - 团队成员技能差异 - 安排技术分享和培训
    - 需求变更风险 - 加强与产品的沟通
    """);

// 生成会议纪要
generator.generateMeetingMinutes(
    meetingTitle,
    meetingDate,
    meetingLocation,
    attendees,
    agenda,
    actionItems,
    discussions
);
```

#### 3. 项目报告生成

```java
// 项目数据
Map<String, Object> projectData = new HashMap<>();
projectData.put("项目名称", "电商平台开发项目");
projectData.put("项目经理", "张三");
projectData.put("开始日期", "2024-01-01");
projectData.put("计划结束日期", "2024-06-30");
projectData.put("当前进度", "35%");
projectData.put("预算总额", "500万元");
projectData.put("已用预算", "175万元");
projectData.put("团队规模", "12人");

// 关键指标
Map<String, String> metrics = new LinkedHashMap<>();
metrics.put("代码提交次数", "1,247次");
metrics.put("代码覆盖率", "85%");
metrics.put("Bug修复率", "95%");
metrics.put("用户故事完成", "45/128个");
metrics.put("API接口完成", "23/56个");
metrics.put("测试用例通过率", "92%");

// 项目洞察
List<String> insights = Arrays.asList(
    "项目整体进度符合预期，关键里程碑按时完成",
    "团队协作效率高，代码质量保持在较高水平",
    "用户反馈积极，产品方向得到验证",
    "技术债务控制良好，架构设计合理",
    "需要关注第三方集成的稳定性问题",
    "建议增加自动化测试覆盖率"
);

// 风险和建议
Map<String, String> risksAndRecommendations = new LinkedHashMap<>();
risksAndRecommendations.put("技术风险", """
    - 第三方支付接口稳定性风险
    - 大促期间系统性能压力
    建议：准备备用支付方案，进行压力测试
    """);

risksAndRecommendations.put("进度风险", """
    - 部分功能复杂度超出预期
    - 测试环境搭建延期
    建议：调整功能优先级，并行搭建测试环境
    """);

risksAndRecommendations.put("资源风险", """
    - 关键开发人员可能离职
    - 设计资源紧张
    建议：知识分享和备份，外包部分设计工作
    """);

// 生成项目报告
generator.generateProjectReport(
    "电商平台项目月度报告",
    "项目管理办公室",
    "2024年1月",
    projectData,
    metrics,
    insights,
    risksAndRecommendations
);
```

## 🎨 文档模板生成器

### 模板概览

DocumentTemplateGenerator 提供了多种预定义的专业文档模板，每个模板都经过精心设计，符合行业标准和最佳实践。

### 1. 项目计划文档模板

```java
DocumentTemplateGenerator templateGen = new DocumentTemplateGenerator();

// 项目基本信息
String projectName = "电商平台开发项目";
String projectManager = "张三";
String startDate = "2024-01-01";
String endDate = "2024-06-30";

// 项目阶段
List<ProjectPhase> phases = Arrays.asList(
    new ProjectPhase("需求分析阶段", "2024-01-01", "2024-01-14", Arrays.asList(
        "业务需求调研",
        "用户故事编写",
        "需求评审会议",
        "需求规格说明书"
    )),
    new ProjectPhase("设计阶段", "2024-01-15", "2024-02-05", Arrays.asList(
        "系统架构设计",
        "数据库设计",
        "UI/UX设计",
        "技术方案评审"
    )),
    new ProjectPhase("开发阶段", "2024-02-06", "2024-04-30", Arrays.asList(
        "后端API开发",
        "前端页面开发",
        "第三方集成",
        "代码审查"
    )),
    new ProjectPhase("测试阶段", "2024-05-01", "2024-05-31", Arrays.asList(
        "单元测试",
        "集成测试",
        "用户验收测试",
        "性能测试"
    )),
    new ProjectPhase("部署阶段", "2024-06-01", "2024-06-30", Arrays.asList(
        "生产环境部署",
        "数据迁移",
        "上线验证",
        "项目交付"
    ))
);

// 交付物清单
List<String> deliverables = Arrays.asList(
    "需求规格说明书",
    "系统架构设计文档",
    "数据库设计文档",
    "API接口文档",
    "前端设计稿",
    "源代码和技术文档",
    "测试计划和测试报告",
    "部署指南和运维手册",
    "用户操作手册",
    "项目总结报告"
);

// 风险管理
Map<String, String> risks = new LinkedHashMap<>();
risks.put("技术风险", "新技术栈学习成本 - 安排技术培训和分享");
risks.put("进度风险", "需求变更频繁 - 建立变更控制流程");
risks.put("资源风险", "关键人员离职 - 知识分享和备份计划");
risks.put("质量风险", "测试时间不足 - 提前介入测试，自动化测试");

// 生成项目计划文档
templateGen.generateProjectPlan(
    projectName,
    projectManager,
    startDate,
    endDate,
    phases,
    deliverables,
    risks
);
```

### 2. API文档模板

```java
// API基本信息
String apiName = "用户管理API";
String apiVersion = "v1.0";
String baseUrl = "https://api.example.com";
String description = "提供用户注册、登录、信息管理等功能的RESTful API";

// API端点定义
List<ApiEndpoint> endpoints = Arrays.asList(
    new ApiEndpoint(
        "GET", 
        "/api/v1/users", 
        "获取用户列表",
        "分页获取系统中的用户列表，支持按条件筛选",
        Arrays.asList(
            new Parameter("page", "int", "页码，从1开始", "1"),
            new Parameter("size", "int", "每页大小，最大100", "20"),
            new Parameter("keyword", "string", "搜索关键词", "")
        ),
        """
        {
          "code": 200,
          "message": "success",
          "data": {
            "total": 100,
            "page": 1,
            "size": 20,
            "users": [
              {
                "id": 1,
                "username": "john_doe",
                "email": "john@example.com",
                "created_at": "2024-01-01T00:00:00Z"
              }
            ]
          }
        }
        """
    ),
    
    new ApiEndpoint(
        "POST", 
        "/api/v1/users", 
        "创建新用户",
        "创建一个新的用户账户",
        Arrays.asList(
            new Parameter("username", "string", "用户名，3-20个字符", "john_doe"),
            new Parameter("email", "string", "邮箱地址", "john@example.com"),
            new Parameter("password", "string", "密码，至少8位", "password123")
        ),
        """
        {
          "code": 201,
          "message": "用户创建成功",
          "data": {
            "id": 123,
            "username": "john_doe",
            "email": "john@example.com",
            "created_at": "2024-01-01T00:00:00Z"
          }
        }
        """
    ),
    
    new ApiEndpoint(
        "PUT", 
        "/api/v1/users/{id}", 
        "更新用户信息",
        "更新指定用户的信息",
        Arrays.asList(
            new Parameter("id", "int", "用户ID", "123"),
            new Parameter("email", "string", "新邮箱地址", "newemail@example.com"),
            new Parameter("profile", "object", "用户资料", "{}")
        ),
        """
        {
          "code": 200,
          "message": "用户信息更新成功",
          "data": {
            "id": 123,
            "username": "john_doe",
            "email": "newemail@example.com",
            "updated_at": "2024-01-01T12:00:00Z"
          }
        }
        """
    )
);

// 错误码说明
Map<String, String> errorCodes = new LinkedHashMap<>();
errorCodes.put("400", "请求参数错误");
errorCodes.put("401", "未授权访问");
errorCodes.put("403", "权限不足");
errorCodes.put("404", "资源不存在");
errorCodes.put("409", "资源冲突");
errorCodes.put("422", "数据验证失败");
errorCodes.put("500", "服务器内部错误");

// 认证说明
String authDescription = """
API使用JWT Bearer Token进行身份认证。

### 获取Token
```bash
curl -X POST https://api.example.com/auth/login \\
  -H "Content-Type: application/json" \\
  -d '{"username": "your_username", "password": "your_password"}'
```

### 使用Token
```bash
curl -X GET https://api.example.com/api/v1/users \\
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

Token有效期为24小时，过期后需要重新获取。
""";

// 生成API文档
templateGen.generateApiDocument(
    apiName,
    "后端开发团队",
    apiVersion,
    baseUrl,
    description,
    endpoints,
    errorCodes,
    authDescription
);
```

### 3. 用户手册模板

```java
// 产品基本信息
String productName = "智能办公平台";
String version = "v2.0";
String description = "一站式智能办公解决方案，提供文档管理、项目协作、即时通讯等功能";

// 功能特性
List<Feature> features = Arrays.asList(
    new Feature(
        "文档管理",
        "在线文档编辑、版本控制、协同编辑",
        Arrays.asList(
            "创建和编辑文档",
            "实时协同编辑",
            "版本历史查看",
            "文档分享和权限管理",
            "模板库使用"
        )
    ),
    new Feature(
        "项目管理",
        "敏捷项目管理、任务跟踪、进度监控",
        Arrays.asList(
            "创建项目和任务",
            "设置任务优先级和截止日期",
            "分配任务给团队成员",
            "查看项目进度和统计",
            "生成项目报告"
        )
    ),
    new Feature(
        "团队协作",
        "即时通讯、视频会议、文件共享",
        Arrays.asList(
            "发送即时消息",
            "创建群组聊天",
            "发起视频会议",
            "共享文件和屏幕",
            "会议录制和回放"
        )
    )
);

// 常见问题
List<FAQ> faqs = Arrays.asList(
    new FAQ(
        "如何重置密码？",
        """
        1. 在登录页面点击"忘记密码"链接
        2. 输入您的邮箱地址
        3. 检查邮箱中的重置密码邮件
        4. 点击邮件中的重置链接
        5. 设置新密码并确认
        
        注意：重置链接有效期为30分钟。
        """
    ),
    new FAQ(
        "如何邀请团队成员？",
        """
        1. 进入团队管理页面
        2. 点击"邀请成员"按钮
        3. 输入成员邮箱地址
        4. 选择成员角色和权限
        5. 发送邀请邮件
        
        被邀请人会收到邀请邮件，点击链接即可加入团队。
        """
    ),
    new FAQ(
        "如何设置文档权限？",
        """
        1. 打开要设置权限的文档
        2. 点击右上角的"分享"按钮
        3. 选择分享范围（公开/团队/指定人员）
        4. 设置权限级别（查看/编辑/管理）
        5. 点击"保存"确认设置
        
        权限设置会立即生效。
        """
    )
);

// 故障排除
List<Troubleshooting> troubleshooting = Arrays.asList(
    new Troubleshooting(
        "无法登录系统",
        Arrays.asList(
            "检查用户名和密码是否正确",
            "确认账户是否被锁定或禁用",
            "清除浏览器缓存和Cookie",
            "尝试使用其他浏览器",
            "联系管理员重置账户"
        )
    ),
    new Troubleshooting(
        "文档保存失败",
        Arrays.asList(
            "检查网络连接是否正常",
            "确认是否有文档编辑权限",
            "检查文档是否被其他人锁定",
            "刷新页面重试",
            "联系技术支持"
        )
    ),
    new Troubleshooting(
        "视频会议无法连接",
        Arrays.asList(
            "检查摄像头和麦克风权限",
            "确认网络带宽是否充足",
            "更新浏览器到最新版本",
            "关闭其他占用带宽的应用",
            "尝试重新加入会议"
        )
    )
);

// 生成用户手册
templateGen.generateUserManual(
    productName,
    "产品团队",
    version,
    description,
    features,
    faqs,
    troubleshooting
);
```

### 4. 测试报告模板

```java
// 测试基本信息
String testProject = "电商平台测试";
String testVersion = "v1.0";
String testPeriod = "2024-01-15 至 2024-01-30";

// 测试结果统计
Map<String, TestResult> testResults = new LinkedHashMap<>();
testResults.put("功能测试", new TestResult(
    "功能测试",
    150,  // 总用例数
    143,  // 通过数
    5,    // 失败数
    2,    // 阻塞数
    95.3, // 通过率
    "核心功能测试完成，主要功能正常"
));

testResults.put("接口测试", new TestResult(
    "接口测试",
    89,
    87,
    2,
    0,
    97.8,
    "API接口测试通过，性能符合预期"
));

testResults.put("性能测试", new TestResult(
    "性能测试",
    25,
    23,
    2,
    0,
    92.0,
    "系统性能基本满足要求，部分接口需优化"
));

testResults.put("安全测试", new TestResult(
    "安全测试",
    35,
    33,
    2,
    0,
    94.3,
    "安全测试通过，发现2个中等风险问题已修复"
));

testResults.put("兼容性测试", new TestResult(
    "兼容性测试",
    40,
    38,
    2,
    0,
    95.0,
    "主流浏览器兼容性良好"
));

// 发现的问题
List<TestIssue> issues = Arrays.asList(
    new TestIssue(
        "BUG-001",
        "登录页面在IE11下样式异常",
        "中",
        "已修复",
        "前端样式兼容性问题，已调整CSS",
        "张三",
        "2024-01-20"
    ),
    new TestIssue(
        "BUG-002", 
        "大文件上传超时",
        "高",
        "已修复",
        "增加了分片上传和断点续传功能",
        "李四",
        "2024-01-25"
    ),
    new TestIssue(
        "BUG-003",
        "移动端适配问题",
        "中",
        "已修复", 
        "优化了移动端响应式布局",
        "王五",
        "2024-01-28"
    ),
    new TestIssue(
        "BUG-004",
        "搜索功能性能问题",
        "中",
        "进行中",
        "正在优化搜索算法和索引",
        "赵六",
        "预计2024-02-05"
    )
);

// 测试环境信息
Map<String, String> testEnvironment = new LinkedHashMap<>();
testEnvironment.put("操作系统", "Windows 10, macOS 12, Ubuntu 20.04");
testEnvironment.put("浏览器", "Chrome 120+, Firefox 115+, Safari 16+, Edge 120+");
testEnvironment.put("移动设备", "iOS 15+, Android 10+");
testEnvironment.put("服务器", "CentOS 7.9, 8核16GB内存");
testEnvironment.put("数据库", "MySQL 8.0.32");
testEnvironment.put("测试工具", "Selenium, JMeter, Postman, SonarQube");

// 测试结论和建议
String conclusion = """
## 测试结论

经过全面的测试，电商平台v1.0版本整体质量良好，主要功能正常，性能基本满足业务需求。

### 主要成果
- 功能测试通过率95.3%，核心业务流程稳定
- 接口测试通过率97.8%，API性能优秀
- 安全测试发现的问题已全部修复
- 兼容性测试覆盖主流浏览器和设备

### 存在问题
- 搜索功能在大数据量下性能有待优化
- 部分边界场景的错误处理需要完善
- 移动端用户体验还有提升空间

### 建议
1. 优化搜索算法，提升查询性能
2. 完善异常处理机制，提高系统稳定性
3. 持续优化移动端用户体验
4. 建立自动化测试体系，提高测试效率
""";

// 生成测试报告
templateGen.generateTestReport(
    testProject,
    "测试团队",
    testVersion,
    testPeriod,
    testResults,
    issues,
    testEnvironment,
    conclusion
);
```

## 🎬 演示程序

### AdvancedDocumentDemo

**位置**: `src/test/java/com/boundesu/words/sdk/demo/AdvancedDocumentDemo.java`

这个演示程序展示了所有高级功能的使用方法，是学习和理解SDK功能的最佳起点。

#### 🚀 运行演示

```bash
# 方法1: 使用Maven运行
mvn exec:java -Dexec.mainClass="com.boundesu.words.sdk.demo.AdvancedDocumentDemo"

# 方法2: 作为测试运行
mvn test -Dtest=AdvancedDocumentDemo

# 方法3: 在IDE中直接运行
# 找到AdvancedDocumentDemo类，右键选择"Run"
```

#### 📁 生成的文档

演示程序会在 `demo-output/` 目录下生成以下专业文档：

| 文档名称 | 文件大小 | 描述 | 特色功能 |
|---------|---------|------|----------|
| **技术报告.docx** | ~45KB | 完整的技术文档示例 | 封面页、目录、多级章节 |
| **会议纪要.docx** | ~25KB | 专业的会议记录 | 参会人员、议程、行动项 |
| **项目计划.docx** | ~35KB | 详细的项目计划文档 | 时间线、里程碑、风险管理 |
| **API文档.docx** | ~30KB | 标准的API文档 | 接口列表、参数说明、示例 |
| **用户手册.docx** | ~40KB | 用户操作指南 | 功能介绍、FAQ、故障排除 |
| **测试报告.docx** | ~32KB | 测试结果报告 | 测试统计、问题跟踪、结论 |

#### 📋 演示内容概览

```java
public class AdvancedDocumentDemo {
    
    public static void main(String[] args) {
        System.out.println("🚀 Boundesu Words SDK 高级功能演示");
        System.out.println("=====================================");
        
        try {
            // 创建输出目录
            createOutputDirectory();
            
            // 演示1: 技术文档生成
            demonstrateTechnicalDocument();
            
            // 演示2: 会议纪要生成
            demonstrateMeetingMinutes();
            
            // 演示3: 项目计划生成
            demonstrateProjectPlan();
            
            // 演示4: API文档生成
            demonstrateApiDocument();
            
            // 演示5: 用户手册生成
            demonstrateUserManual();
            
            // 演示6: 测试报告生成
            demonstrateTestReport();
            
            System.out.println("✅ 所有演示完成！请查看 demo-output/ 目录");
            
        } catch (Exception e) {
            System.err.println("❌ 演示过程中发生错误: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
```

## ✨ 功能特色

### 1. 🤖 高度自动化

#### 一键生成专业文档
```java
// 只需几行代码即可生成完整的专业文档
AdvancedDocumentGenerator generator = new AdvancedDocumentGenerator();
generator.setTitle("项目报告")
         .setAuthor("项目团队")
         .generateCoverPage()           // 自动生成封面页
         .generateTableOfContents()     // 自动生成目录
         .addChapter("概述", content1)   // 添加章节
         .addChapter("详情", content2)
         .createDocument(outputPath);   // 生成文档
```

#### 智能格式化
- **自动样式应用**: 标题、正文、列表等自动应用专业样式
- **智能分页**: 自动处理分页和页面布局
- **目录生成**: 基于章节结构自动生成目录和页码
- **页眉页脚**: 自动添加页眉页脚和页码

### 2. 📋 丰富的模板库

#### 内置专业模板
| 模板类型 | 适用场景 | 核心特性 |
|---------|----------|----------|
| 技术文档 | 架构设计、技术方案 | 结构化章节、代码示例、图表支持 |
| 项目计划 | 项目管理、进度跟踪 | 时间线、里程碑、资源分配 |
| 会议纪要 | 会议记录、决策跟踪 | 参会人员、议程、行动项 |
| API文档 | 接口文档、开发指南 | 端点列表、参数说明、示例代码 |
| 用户手册 | 产品说明、操作指南 | 功能介绍、步骤说明、FAQ |
| 测试报告 | 质量保证、测试结果 | 测试统计、问题跟踪、结论建议 |

#### 模板定制化
```java
// 可以基于现有模板进行定制
DocumentTemplateGenerator template = new DocumentTemplateGenerator();
template.setCompanyLogo("path/to/logo.png")
        .setCompanyInfo("公司名称", "地址", "联系方式")
        .setDocumentStyle("corporate")  // 企业风格
        .setColorScheme("blue");        // 蓝色主题
```

### 3. 🌏 中文友好设计

#### 完美中文支持
- **字体优化**: 默认使用微软雅黑等中文友好字体
- **排版优化**: 针对中文文档的行距、段距优化
- **日期格式**: 支持中文日期格式（如：2024年1月15日）
- **数字格式**: 支持中文数字格式和货币格式

#### 本地化特性
```java
// 中文日期和格式支持
generator.setDateFormat("yyyy年MM月dd日")
         .setNumberFormat("中文")
         .setPageNumberFormat("第 {0} 页 共 {1} 页");
```

### 4. ⚡ 高性能设计

#### 内存优化
- **流式处理**: 大文档采用流式处理，避免内存溢出
- **对象复用**: 重用文档对象，减少内存分配
- **延迟加载**: 按需加载文档内容，提高启动速度

#### 性能指标
| 文档类型 | 文档大小 | 生成时间 | 内存使用 |
|---------|---------|----------|----------|
| 简单文档 | < 10页 | < 500ms | < 50MB |
| 中等文档 | 10-50页 | < 2s | < 100MB |
| 大型文档 | 50-200页 | < 10s | < 200MB |
| 超大文档 | > 200页 | < 30s | < 500MB |

```java
// 性能优化示例
generator.setPerformanceMode(true)          // 启用性能模式
         .setMemoryOptimization(true)        // 启用内存优化
         .setBatchSize(100);                 // 设置批处理大小
```

### 5. 🔧 易于集成

#### 简洁的API设计
```java
// 链式调用，代码简洁易读
generator.setTitle("文档标题")
         .setAuthor("作者")
         .addChapter("章节1", "内容1")
         .addChapter("章节2", "内容2")
         .createDocument(path);
```

#### 多种输出方式
```java
// 输出到文件
generator.createDocument(Paths.get("document.docx"));

// 输出到字节数组（适用于Web应用）
byte[] bytes = generator.createDocumentAsBytes();

// 输出到输出流
generator.createDocument(outputStream);

// 输出到临时文件
Path tempFile = generator.createTemporaryDocument();
```

#### Spring Boot集成
```java
@Service
public class DocumentService {
    
    @Autowired
    private AdvancedDocumentGenerator documentGenerator;
    
    public byte[] generateReport(ReportData data) {
        return documentGenerator
            .setTitle(data.getTitle())
            .setAuthor(data.getAuthor())
            .generateReport(data)
            .createDocumentAsBytes();
    }
}
```

## 🔧 高级配置

### 文档样式定制

#### 基础样式设置
```java
AdvancedDocumentGenerator generator = new AdvancedDocumentGenerator();

// 字体设置
generator.setFontFamily("微软雅黑")           // 主字体
         .setTitleFontFamily("黑体")          // 标题字体
         .setCodeFontFamily("Consolas")       // 代码字体
         .setFontSize(12)                     // 正文字号
         .setTitleFontSize(16);               // 标题字号

// 颜色设置
generator.setPrimaryColor("#1890ff")          // 主色调
         .setSecondaryColor("#52c41a")        // 辅助色
         .setTextColor("#262626")             // 文本颜色
         .setLinkColor("#1890ff");            // 链接颜色

// 间距设置
generator.setLineSpacing(1.15)               // 行距
         .setParagraphSpacing(6)              // 段距
         .setChapterSpacing(12);              // 章节间距
```

#### 预定义样式主题
```java
// 使用预定义主题
generator.setDocumentStyle("professional");  // 专业风格
generator.setDocumentStyle("modern");        // 现代风格
generator.setDocumentStyle("classic");       // 经典风格
generator.setDocumentStyle("minimal");       // 简约风格

// 自定义主题
DocumentStyle customStyle = DocumentStyle.builder()
    .fontFamily("微软雅黑")
    .primaryColor("#1890ff")
    .headerStyle(HeaderStyle.MODERN)
    .footerStyle(FooterStyle.SIMPLE)
    .build();
generator.setDocumentStyle(customStyle);
```

### 页面设置

#### 页面布局
```java
// 页面大小和方向
generator.setPageSize(PageSize.A4)           // A4纸张
         .setPageOrientation(Orientation.PORTRAIT); // 纵向

// 页边距设置（单位：厘米）
generator.setPageMargins(
    2.5,  // 上边距
    2.0,  // 下边距
    2.0,  // 左边距
    2.0   // 右边距
);

// 页眉页脚设置
generator.setHeaderHeight(1.5)               // 页眉高度
         .setFooterHeight(1.0)               // 页脚高度
         .setHeaderMargin(0.5)               // 页眉边距
         .setFooterMargin(0.5);              // 页脚边距
```

#### 页眉页脚定制
```java
// 简单页眉页脚
generator.setHeader("文档标题")
         .setFooter("第 {page} 页 共 {total} 页");

// 复杂页眉页脚
HeaderFooterConfig headerConfig = HeaderFooterConfig.builder()
    .leftText("公司名称")
    .centerText("文档标题")
    .rightText("机密文件")
    .includeDate(true)
    .dateFormat("yyyy年MM月dd日")
    .build();

generator.setHeaderConfig(headerConfig);

FooterFooterConfig footerConfig = FooterFooterConfig.builder()
    .leftText("版权所有 © 2024")
    .centerText("")
    .rightText("第 {page} 页")
    .includeTotalPages(true)
    .build();

generator.setFooterConfig(footerConfig);
```

### 目录设置

#### 目录样式配置
```java
// 目录基本设置
generator.setTocTitle("目录")                 // 目录标题
         .setTocDepth(3)                     // 目录深度（1-6级）
         .setIncludePageNumbers(true)        // 包含页码
         .setTocPageBreak(true);             // 目录后分页

// 目录样式
TocStyle tocStyle = TocStyle.builder()
    .level1Style("标题1样式")
    .level2Style("标题2样式")
    .level3Style("标题3样式")
    .dotLeader(true)                        // 点线引导
    .rightAlign(true)                       // 页码右对齐
    .build();

generator.setTocStyle(tocStyle);
```

#### 自定义目录格式
```java
// 自定义目录项格式
generator.setTocFormat(1, "第{number}章 {title}")  // 一级标题格式
         .setTocFormat(2, "{number} {title}")      // 二级标题格式
         .setTocFormat(3, "{number} {title}");     // 三级标题格式

// 目录编号设置
generator.setChapterNumbering(true)          // 启用章节编号
         .setNumberingStyle("1.1.1")         // 编号样式
         .setNumberingSeparator(".");         // 编号分隔符
```

### 高级功能配置

#### 水印设置
```java
// 文字水印
generator.setTextWatermark("机密文件")
         .setWatermarkOpacity(0.3)
         .setWatermarkAngle(45)
         .setWatermarkColor("#cccccc");

// 图片水印
generator.setImageWatermark("path/to/watermark.png")
         .setWatermarkOpacity(0.2)
         .setWatermarkPosition(WatermarkPosition.CENTER);
```

#### 文档保护
```java
// 文档密码保护
generator.setDocumentPassword("password123")
         .setReadOnlyPassword("readonly456");

// 编辑限制
generator.setEditingRestrictions(
    EditingRestriction.COMMENTS_ONLY,  // 仅允许评论
    EditingRestriction.TRACKED_CHANGES // 跟踪修改
);
```

#### 元数据设置
```java
// 文档属性
generator.setTitle("文档标题")
         .setAuthor("作者姓名")
         .setSubject("文档主题")
         .setKeywords("关键词1,关键词2,关键词3")
         .setDescription("文档描述")
         .setCategory("文档分类")
         .setCompany("公司名称")
         .setManager("管理者");

// 自定义属性
generator.addCustomProperty("项目编号", "PRJ-2024-001")
         .addCustomProperty("版本号", "v1.0.0")
         .addCustomProperty("审核状态", "已审核");
```

## 🧪 测试和验证

### 测试套件概览

项目包含完整的测试套件，确保所有功能的稳定性和可靠性：

```
src/test/java/com/boundesu/words/sdk/
├── advanced/
│   ├── AdvancedDocumentGeneratorTest.java    # 高级文档生成器测试 (11个测试用例)
│   └── DocumentTemplateGeneratorTest.java    # 文档模板生成器测试 (8个测试用例)
├── demo/
│   └── AdvancedDocumentDemo.java             # 功能演示程序
└── integration/
    └── AdvancedFeaturesIntegrationTest.java  # 集成测试 (5个测试用例)
```

### 单元测试

#### AdvancedDocumentGeneratorTest
```java
@TestMethodOrder(OrderAnnotation.class)
public class AdvancedDocumentGeneratorTest {
    
    @Test
    @Order(1)
    @DisplayName("应该成功创建基本文档")
    public void shouldCreateBasicDocument() {
        // 测试基本文档创建功能
    }
    
    @Test
    @Order(2)
    @DisplayName("应该生成专业的封面页")
    public void shouldGenerateProfessionalCoverPage() {
        // 测试封面页生成
    }
    
    @Test
    @Order(3)
    @DisplayName("应该自动生成目录")
    public void shouldGenerateTableOfContents() {
        // 测试目录生成
    }
    
    @Test
    @Order(4)
    @DisplayName("应该支持多级章节管理")
    public void shouldSupportMultiLevelChapters() {
        // 测试章节管理
    }
    
    @Test
    @Order(5)
    @DisplayName("应该生成技术文档")
    public void shouldGenerateTechnicalDocument() {
        // 测试技术文档生成
    }
    
    @Test
    @Order(6)
    @DisplayName("应该生成会议纪要")
    public void shouldGenerateMeetingMinutes() {
        // 测试会议纪要生成
    }
    
    @Test
    @Order(7)
    @DisplayName("应该生成项目报告")
    public void shouldGenerateProjectReport() {
        // 测试项目报告生成
    }
    
    @Test
    @Order(8)
    @DisplayName("应该支持自定义样式")
    public void shouldSupportCustomStyles() {
        // 测试样式定制
    }
    
    @Test