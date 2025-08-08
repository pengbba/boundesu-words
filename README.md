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
- 🎯 **动态样式解析**: 智能解析CSS样式，支持边框、颜色、字体等动态设置
- 📊 **表格增强**: 支持复杂表格结构，包括单元格边框、内边距、背景色等
- ⚡ **高性能**: 内置性能监控，优化转换效率
- 🛡️ **异常处理**: 完善的异常处理机制，提供详细错误信息
- 📝 **易于使用**: 简洁的API设计，支持链式调用
- 🔍 **CSS选择器**: 支持组合选择器（如 `th, td`）和复杂CSS规则解析

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
import com.boundesu.words.core.Document;
import com.boundesu.words.core.example.HtmlLoadExample;

public class Example {
    public static void main(String[] args) {
        try {
            // 创建文档实例
            Document doc = new Document();
            
            // 从HTML文件加载
            doc.loadFromHtml("input.html");
            
            // 保存为DOCX
            doc.save("output.docx");
            
            System.out.println("转换完成！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

#### 2. 带样式的HTML转换

```java
// 支持复杂CSS样式的HTML转换
String htmlWithStyles = """
    <style>
        table, th, td { border: 1px solid #ddd; }
        th { background-color: #f2f2f2; }
        .highlight { color: red; font-weight: bold; }
    </style>
    <table>
        <tr><th>标题1</th><th>标题2</th></tr>
        <tr><td>数据1</td><td class="highlight">重要数据</td></tr>
    </table>
    """;
    
Document doc = new Document();
doc.loadFromHtml(htmlWithStyles);
doc.save("styled_output.docx");
```

#### 3. 使用HTML加载选项

```java
import com.boundesu.words.core.HtmlLoadOptions;

// 创建HTML加载选项
HtmlLoadOptions options = new HtmlLoadOptions();
options.setEncoding("UTF-8");
options.setBaseUri("file:///path/to/resources/");

// 使用选项加载HTML
Document doc = new Document("input.html", options);
doc.save("output.docx");
```

#### 4. 批量转换

```java
// 批量转换HTML文件
File[] htmlFiles = new File("input_folder").listFiles((dir, name) -> name.endsWith(".html"));

for (File htmlFile : htmlFiles) {
    Document doc = new Document();
    doc.loadFromHtml(htmlFile.getAbsolutePath());
    
    String outputName = htmlFile.getName().replace(".html", ".docx");
    doc.save("output_folder/" + outputName);
    
    System.out.println("已转换: " + htmlFile.getName());
}
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
- 文本格式：`<b>`, `<strong>`, `<i>`, `<em>`, `<u>`, `<s>`
- 列表标签：`<ul>`, `<ol>`, `<li>`
- 表格标签：`<table>`, `<tr>`, `<td>`, `<th>`, `<thead>`, `<tbody>`
- 链接标签：`<a href="...">`
- 图片标签：`<img src="...">`
- 分割线：`<hr>`
- 换行：`<br>`

**支持的CSS属性：**

- 文本样式：`color`, `font-size`, `font-weight`, `font-style`
- 边框样式：`border`, `border-width`, `border-style`, `border-color`
- 背景样式：`background-color`
- 间距样式：`padding`, `margin`
- 表格样式：`border-collapse`, `text-align`

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

运行示例程序：

```bash
# 编译项目
mvn clean compile

# 运行HTML转换示例
cd boundesu-words-core
java -cp "target/classes;target/dependency/*" com.boundesu.words.core.example.HtmlLoadExample
```

### 测试文件

项目包含以下测试文件：
- `test-input.html` - HTML转换测试文件
- `test-image.jpg` - 图片处理测试文件

## 🐛 故障排除

### 常见问题

**1. 找不到主类错误**
```
错误: 找不到或无法加载主类 com.boundesu.words.examples.HtmlLoadExample
```
解决方案：确保使用正确的包名 `com.boundesu.words.core.example.HtmlLoadExample`

**2. 文件被占用错误**
```
FileNotFoundException: 文件被另一个程序占用
```
解决方案：关闭可能打开目标文件的程序（如Microsoft Word）

**3. CSS样式不生效**
- 确保CSS选择器语法正确
- 检查是否使用了支持的CSS属性
- 验证HTML结构是否正确

**4. 中文乱码问题**
```java
// 设置正确的编码
HtmlLoadOptions options = new HtmlLoadOptions();
options.setEncoding("UTF-8");
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

## 📈 版本历史

### v1.0.0 (当前版本)
- ✅ 基础HTML到DOCX转换功能
- ✅ CSS样式解析和应用
- ✅ 表格边框动态设置
- ✅ 组合CSS选择器支持
- ✅ 完善的异常处理机制
- ✅ 性能监控和日志记录

### 计划中的功能
- 🔄 更多HTML标签支持
- 🎨 高级CSS样式支持
- 📊 图表和图形转换
- 🔗 超链接和书签支持
- 📱 响应式布局转换

## 📞 联系我们

- 项目主页: [GitHub](https://github.com/boundesu/boundesu-words)
- 问题反馈: [Issues](https://github.com/boundesu/boundesu-words/issues)
- 邮箱: support@boundesu.com

## 🌟 Star History

如果这个项目对您有帮助，请给我们一个 ⭐ Star！

---

**Boundesu Words SDK** - 让文档转换变得简单高效！ 🚀

*最后更新: 2024年12月*