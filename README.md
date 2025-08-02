# Boundesu Words SDK

[![Version](https://img.shields.io/badge/version-1.0.0-blue.svg)](https://github.com/boundesu/boundesu-words)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](LICENSE)
[![Java](https://img.shields.io/badge/java-8%2B-orange.svg)](https://www.oracle.com/java/)

> 自主研发的文档处理SDK，专注于HTML/XML到DOCX文档的高质量转换

## 📖 项目简介

Boundesu Words SDK 是一个功能强大的Java文档处理工具包，提供了HTML和XML内容到Microsoft Word
DOCX格式的高质量转换功能。该SDK采用模块化设计，支持多种转换方式，并提供了丰富的API接口。

### ✨ 核心特性

- 🔄 **多格式转换**: 支持HTML、XML到DOCX的无缝转换
- 🏗️ **模块化架构**: 清晰的模块分离，便于维护和扩展
- 🎨 **丰富样式**: 支持文本格式、表格、列表等多种文档元素
- ⚡ **高性能**: 内置性能监控，优化转换效率
- 🛡️ **异常处理**: 完善的异常处理机制，提供详细错误信息
- 📝 **易于使用**: 简洁的API设计，支持链式调用

## 🏗️ 项目架构

```
boundesu-words/
├── boundesu-words-all/      # 聚合模块，提供统一入口
├── boundesu-words-core/     # 核心功能模块
├── boundesu-words-html/     # HTML转换模块
├── boundesu-words-xml/      # XML转换模块
├── boundesu-words-common/   # 公共工具模块
└── pom.xml                  # 主项目配置
```

### 模块说明

| 模块                        | 功能描述                    |
|---------------------------|-------------------------|
| **boundesu-words-all**    | 聚合所有功能的统一入口，提供最简单的使用方式  |
| **boundesu-words-core**   | 核心转换引擎，包含文档创建器和高级生成器    |
| **boundesu-words-html**   | HTML解析和转换功能，支持丰富的HTML标签 |
| **boundesu-words-xml**    | XML解析和转换功能，支持结构化文档生成    |
| **boundesu-words-common** | 公共工具类、常量定义和异常处理         |

## 🚀 快速开始

### 环境要求

- Java 8 或更高版本
- Maven 3.6 或更高版本

### 安装依赖

```xml

<dependency>
    <groupId>com.boundesu</groupId>
    <artifactId>boundesu-words-all</artifactId>
    <version>1.0.0</version>
</dependency>
```

### 基础使用

#### 1. 简单的HTML转换

```java
import com.boundesu.words.BoundesuWordsAll;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class Example {
    public static void main(String[] args) {
        // 创建SDK实例
        BoundesuWordsAll sdk = new BoundesuWordsAll();

        try {
            // HTML内容转换
            String htmlContent = "<h1>标题</h1><p>这是一个段落。</p>";
            XWPFDocument document = sdk.htmlToDocx(htmlContent);

            // 保存文档
            sdk.saveToFile(document, new File("output.docx"));

            System.out.println("转换完成！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

#### 2. XML转换示例

```java
// XML内容转换
String xmlContent = "<document><title>XML标题</title><paragraph>这是XML段落。</paragraph></document>";
XWPFDocument document = sdk.xmlToDocx(xmlContent);
sdk.

saveToFile(document, new File("xml_output.docx"));
```

#### 3. 文件转换

```java
// 自动识别文件类型并转换
File inputFile = new File("input.html");
File outputFile = new File("output.docx");
sdk.

convert(inputFile, outputFile);
```

### 高级功能

#### 使用高级文档生成器

```java
import com.boundesu.words.BoundesuWordsSDK;
import com.boundesu.words.core.advanced.AdvancedDocumentGenerator;

// 创建高级文档生成器
AdvancedDocumentGenerator generator = BoundesuWordsSDK.createAdvancedGenerator();

        // 生成技术文档
        Map<String, String> sections = new HashMap<>();
sections.

        put("概述","这是项目概述内容...");
sections.

        put("技术架构","这是技术架构说明...");

BoundesuWordsSDK.Utils.

        createTechnicalDocument(
    "项目技术文档",
            "开发团队",
            "本文档介绍了项目的技术实现...",
    sections,
    "总结内容",
    Paths.get("technical_doc.docx")
);
```

#### 设置页边距

```java
import com.boundesu.words.html.converter.HtmlToDocxConverter;

// 创建页边距设置
HtmlToDocxConverter.PageMargins margins = new HtmlToDocxConverter.PageMargins(
        1440, // 上边距 (1英寸 = 1440 twips)
        1440, // 下边距
        1440, // 左边距
        1440  // 右边距
);

        // 转换时应用页边距
        XWPFDocument document = sdk.getHtmlConverter().convertHtmlToDocx(htmlContent, margins);
```

## 📚 API 文档

### 主要类说明

#### BoundesuWordsAll

聚合所有功能的主入口类，提供最简单的使用方式。

**主要方法：**

- `htmlToDocx(String htmlContent)` - HTML内容转换
- `xmlToDocx(String xmlContent)` - XML内容转换
- `convertToDocx(File inputFile)` - 自动识别文件类型转换
- `convert(File inputFile, File outputFile)` - 一键转换并保存

#### BoundesuWordsSDK

提供更多高级功能的SDK入口类。

**主要方法：**

- `createDocumentCreator(String type)` - 创建文档创建器
- `createAdvancedGenerator()` - 创建高级文档生成器
- `convertHtmlToDocx(String htmlContent, Path outputPath)` - HTML转换并保存

#### HtmlToDocxConverter

专门的HTML转换器，支持更多自定义选项。

**支持的HTML标签：**

- 标题标签：`<h1>` - `<h6>`
- 段落标签：`<p>`
- 文本格式：`<b>`, `<strong>`, `<i>`, `<em>`
- 列表标签：`<ul>`, `<ol>`, `<li>`
- 表格标签：`<table>`, `<tr>`, `<td>`, `<th>`

#### XmlToDocxConverter

专门的XML转换器，支持结构化文档生成。

**支持的XML元素：**

- `<heading level="1-6">` - 标题
- `<paragraph>` - 段落
- `<table>` - 表格
- `<list type="ordered|unordered">` - 列表

## 🔧 配置选项

### 文档属性配置

```java
// 设置文档属性
AdvancedDocumentGenerator.DocumentConfig config = new AdvancedDocumentGenerator.DocumentConfig();
config.

setGenerateToc(true);           // 生成目录
config.

setGenerateCoverPage(true);     // 生成封面
config.

setTocTitle("目录");            // 目录标题
config.

setCompany("公司名称");         // 公司名称

AdvancedDocumentGenerator generator = BoundesuWordsSDK.createAdvancedGenerator();
generator.

setConfig(config);
```

### 性能监控

```java
import com.boundesu.words.common.util.PerformanceMonitor;

// 监控文档创建性能
PerformanceMonitor.OperationContext context = BoundesuWordsSDK.createPerformanceMonitor();
try{
        // 执行文档转换操作
        XWPFDocument document = sdk.htmlToDocx(htmlContent);
}finally{
        context.

        end();
    System.out.

        println("转换耗时: "+context.getDuration() +"ms");
        }
```

## 🧪 测试

运行所有测试：

```bash
mvn clean test
```

运行特定模块测试：

```bash
mvn test -pl boundesu-words-core
```

## 📦 构建

编译项目：

```bash
mvn clean compile
```

打包项目：

```bash
mvn clean package
```

安装到本地仓库：

```bash
mvn clean install
```

## 🤝 贡献指南

我们欢迎任何形式的贡献！请遵循以下步骤：

1. Fork 本仓库
2. 创建特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 开启 Pull Request

### 开发规范

- 遵循Java编码规范
- 添加适当的单元测试
- 更新相关文档
- 确保所有测试通过

## 📄 许可证

本项目采用 MIT 许可证 - 查看 [LICENSE](LICENSE) 文件了解详情。

## 🙏 致谢

感谢以下开源项目的支持：

- [Apache POI](https://poi.apache.org/) - Microsoft Office文档处理
- [Jsoup](https://jsoup.org/) - HTML解析和处理
- [SLF4J](http://www.slf4j.org/) - 日志门面
- [Logback](http://logback.qos.ch/) - 日志实现

## 📞 联系我们

- 项目主页: [GitHub](https://github.com/boundesu/boundesu-words)
- 问题反馈: [Issues](https://github.com/boundesu/boundesu-words/issues)
- 邮箱: boundesu@example.com

---

**Boundesu Words SDK** - 让文档转换变得简单高效！ 🚀