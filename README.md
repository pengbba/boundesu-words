# Boundesu Words SDK

一个功能强大的Java文档处理SDK，提供多种文档创建和处理能力，支持DOCX、HTML、Markdown等多种格式。

## 🚀 核心特性

### 📄 DOCX文档创建（核心功能）
- ✅ **三种创建方式** - 直接POI创建、HTML转换、XML转换
- ✅ **统一接口设计** - 一致的API体验，支持链式调用
- ✅ **工厂模式管理** - 灵活的创建器实例化
- ✅ **完整功能支持** - 段落、标题、表格、样式、中文字体
- ✅ **Apache POI 5.4.1** - 基于最新POI库，稳定可靠

### 🏗️ 文档处理能力
- ✅ **完全自主开发** - 核心功能不依赖第三方文档处理库
- ✅ **JDK 8+支持** - 兼容JDK 8及以上版本，适用范围更广
- ✅ **Spring Boot 2.7.18** - 无缝集成Spring Boot框架
- ✅ **丰富的文档模型** - 支持段落、标题、表格、样式等
- ✅ **多格式导出** - 支持DOCX、HTML、文本格式导出
- ✅ **HTML/XML解析** - 智能解析HTML和XML内容
- ✅ **模板生成器** - 支持项目计划、测试报告等模板
- ✅ **高级功能** - 文档模板、自动目录、封面页生成
- ✅ **样式系统** - 完整的文档和段落样式支持
- ✅ **表格功能** - 支持表格创建、数据填充和样式设置
- ✅ **文档统计** - 提供字数统计、字符统计等分析功能
- ✅ **配置管理** - 灵活的配置系统，支持自定义配置
- ✅ **异常处理** - 完善的异常处理机制
- ✅ **TestNG测试** - 完整的测试覆盖，29个测试用例
- ✅ **丰富示例** - 5个完整的使用示例

## 📋 系统要求

- **JDK 17+** - 必须使用Java 17或更高版本
- **Maven 3.6+** - 用于项目构建和依赖管理
- **Spring Boot 2.7.18** - 框架版本
- **Apache POI 5.4.1** - DOCX文档处理
- **TestNG 7.8.0** - 测试框架

## 🛠️ 快速开始

### 1. 克隆项目

```bash
git clone https://github.com/your-org/boundesu-words-jdk17.git
cd boundesu-words-jdk17
```

### 2. 构建项目

```bash
mvn clean install
```

### 3. 运行示例

```bash
# 运行快速入门示例
mvn test -Dtest="com.boundesu.words.example.QuickStartExample"

# 运行DOCX创建演示
mvn test -Dtest="com.boundesu.words.sdk.demo.DocumentCreatorDemo"

# 运行高级功能演示
mvn test -Dtest="com.boundesu.words.sdk.demo.AdvancedDocumentDemo"
```

### 4. 添加到现有项目

如果要在现有项目中使用，请添加以下依赖：

```xml
<dependency>
    <groupId>com.boundesu</groupId>
    <artifactId>words-jdk17</artifactId>
    <version>1.0.0</version>
</dependency>
```

### 5. DOCX文档创建（推荐）

#### 方法一：直接使用Apache POI创建
```java
import com.boundesu.words.sdk.creator.impl.PoiDirectDocxCreator;
import java.nio.file.Paths;

// 创建DOCX文档
PoiDirectDocxCreator creator = new PoiDirectDocxCreator();
creator.setTitle("我的文档")
       .setAuthor("作者姓名")
       .addHeading("第一章 介绍", 1)
       .addParagraph("这是文档的第一段内容。")
       .addBoldParagraph("这是粗体段落。");

// 添加表格
String[][] tableData = {
    {"姓名", "年龄", "职位"},
    {"张三", "25", "工程师"},
    {"李四", "30", "经理"}
};
creator.addTable(tableData);

// 保存文档
creator.createDocument(Paths.get("my_document.docx"));
```

#### 方法二：HTML转换创建
```java
import com.boundesu.words.sdk.creator.impl.HtmlToDocxCreator;

// 通过HTML创建DOCX
HtmlToDocxCreator htmlCreator = new HtmlToDocxCreator();
htmlCreator.setTitle("HTML转换文档")
           .setAuthor("作者")
           .addHeading("HTML内容", 1)
           .addParagraph("这是通过HTML转换的文档。");

// 添加自定义HTML内容
htmlCreator.addCustomHtml("<h2>子标题</h2>");
htmlCreator.addCustomHtml("<p>支持<strong>粗体</strong>和<em>斜体</em>文本。</p>");
htmlCreator.addCustomHtml("<ul><li>列表项1</li><li>列表项2</li></ul>");

htmlCreator.createDocument(Paths.get("html_document.docx"));
```

#### 方法三：XML转换创建
```java
import com.boundesu.words.sdk.creator.impl.XmlToDocxCreator;
import java.util.Arrays;

// 通过XML创建DOCX
XmlToDocxCreator xmlCreator = new XmlToDocxCreator();
xmlCreator.setTitle("XML转换文档")
          .setAuthor("作者")
          .addHeading("XML内容", 1)
          .addParagraph("这是通过XML转换的文档。");

// 添加自定义XML元素和列表
xmlCreator.addCustomXmlElement("section", "重要内容", "type=important");
xmlCreator.addList(Arrays.asList("功能1", "功能2", "功能3"), false);

xmlCreator.createDocument(Paths.get("xml_document.docx"));
```

#### 使用工厂模式
```java
import com.boundesu.words.sdk.creator.DocumentCreatorFactory;
import com.boundesu.words.sdk.creator.DocumentCreator;

// 通过工厂创建
DocumentCreator creator = DocumentCreatorFactory.createDocumentCreator("poi");
// 或者: DocumentCreatorFactory.createDocumentCreator("html");
// 或者: DocumentCreatorFactory.createDocumentCreator("xml");

creator.setTitle("工厂模式文档")
       .setAuthor("作者")
       .addHeading("标题", 1)
       .addParagraph("内容")
       .createDocument(Paths.get("factory_document.docx"));
```

### 6. 传统文档创建

```java
import com.boundesu.words.sdk.BoundesuWordsSDK;
import com.boundesu.words.sdk.model.BoundesuDocument;

// 创建SDK实例
BoundesuWordsSDK sdk = new BoundesuWordsSDK();

// 创建文档
BoundesuDocument document = sdk.createDocument("我的文档", "作者");

// 添加内容
sdk.addHeading(document, "标题", 1);
sdk.addParagraph(document, "这是一个段落内容。");

// 保存文档
sdk.saveAsHtml(document, "output/my_document.html");
```

### 7. 高级功能示例

```java
import com.boundesu.words.sdk.advanced.DocumentTemplateGenerator;
import java.util.Map;
import java.util.LinkedHashMap;

// 生成项目计划文档
DocumentTemplateGenerator.ProjectInfo projectInfo = 
    new DocumentTemplateGenerator.ProjectInfo()
        .setProjectName("新产品开发")
        .setProjectManager("张经理")
        .setStartDate("2024-01-01")
        .setEndDate("2024-12-31");

DocumentTemplateGenerator.generateProjectPlan(
    projectInfo, 
    Paths.get("project_plan.docx")
);

// 生成测试报告
Map<String, Object> testResults = new LinkedHashMap<>();
testResults.put("总测试用例数", 100);
testResults.put("通过用例数", 95);
testResults.put("失败用例数", 5);
testResults.put("测试通过率", "95%");

DocumentTemplateGenerator.generateTestReport(
    "系统名称",
    "集成测试",
    testResults,
    Paths.get("test_report.docx")
);
```

## 📖 详细使用指南

### 文档创建

```java
BoundesuWordsSDK sdk = new BoundesuWordsSDK();

// 创建空文档
BoundesuDocument document = sdk.createDocument();

// 创建带标题和作者的文档
BoundesuDocument document = sdk.createDocument("文档标题", "作者姓名");
```

### 添加内容

```java
// 添加段落
sdk.addParagraph(document, "这是一个段落");

// 添加标题（1-6级）
sdk.addHeading(document, "一级标题", 1);
sdk.addHeading(document, "二级标题", 2);

// 添加带样式的段落
BoundesuParagraphStyle style = sdk.createParagraphStyle()
    .fontFamily("Arial")
    .fontSize(14)
    .bold(true)
    .color("#ff0000")
    .build();
sdk.addParagraph(document, "红色粗体段落", style);
```

### 表格操作

```java
// 创建表格
sdk.addTable(document, 3, 4); // 3行4列

// 设置表格数据
String[][] data = {
    {"表头1", "表头2", "表头3", "表头4"},
    {"数据1", "数据2", "数据3", "数据4"},
    {"数据5", "数据6", "数据7", "数据8"}
};
sdk.setTableData(document, 0, data); // 0表示第一个表格
```

### 样式设置

```java
// 设置文档样式
BoundesuDocumentStyle docStyle = sdk.createDocumentStyle()
    .theme(BoundesuDocumentStyle.DocumentTheme.MODERN)
    .defaultFontFamily("Microsoft YaHei")
    .defaultFontSize(12)
    .build()
    .applyTheme(BoundesuDocumentStyle.DocumentTheme.MODERN);
sdk.setDocumentStyle(document, docStyle);

// 设置页眉页脚
sdk.setHeader(document, "页眉内容");
sdk.setFooter(document, "页脚内容");
```

### 文档保存

```java
// 保存为HTML格式
sdk.saveAsHtml(document, "output/document.html");

// 保存为文本格式
sdk.saveAsText(document, "output/document.txt");
```

### 快捷方法

```java
// 快速创建简单文档
BoundesuDocument doc = sdk.quickCreateDocument("标题", "内容");

// 快速创建报告
String[] sections = {"章节1", "章节2"};
String[] contents = {"内容1", "内容2"};
BoundesuDocument report = sdk.quickCreateReport("报告标题", "作者", sections, contents);

// 快速创建表格文档
String[] headers = {"列1", "列2"};
String[][] data = {{"数据1", "数据2"}};
BoundesuDocument table = sdk.quickCreateTableDocument("表格标题", headers, data);
```

## 📚 示例代码

项目提供了丰富的示例代码，位于 `src/test/java/com/boundesu/words/example/` 目录：

### 🚀 QuickStartExample.java - 快速入门
```bash
mvn test -Dtest="com.boundesu.words.example.QuickStartExample"
```
- 最基本的文档创建和导出功能
- 简单文档、样式文档、表格文档
- 多格式导出演示

### 🔧 AdvancedFeaturesExample.java - 高级功能
```bash
mvn test -Dtest="com.boundesu.words.example.AdvancedFeaturesExample"
```
- HTML和XML内容解析
- 复杂HTML结构的智能处理
- 结构化内容解析演示

### 📊 BusinessReportExample.java - 商业报告
```bash
mvn test -Dtest="com.boundesu.words.example.BusinessReportExample"
```
- 月度销售报告生成
- 项目状态报告生成
- 财务分析报告生成

### 📚 TechnicalDocumentExample.java - 技术文档
```bash
mvn test -Dtest="com.boundesu.words.example.TechnicalDocumentExample"
```
- API文档生成
- 用户手册生成
- 系统架构文档生成

### 🎯 BoundesuWordsExample.java - 综合功能
```bash
mvn test -Dtest="com.boundesu.words.example.BoundesuWordsExample"
```
- SDK完整功能演示
- 各种文档创建方法
- 样式设置和表格操作

### 运行所有示例
```bash
mvn test -Dtest="com.boundesu.words.example.*Example"
```

### 演示程序

#### DocumentCreatorDemo.java - DOCX创建演示
```bash
mvn test -Dtest="com.boundesu.words.sdk.demo.DocumentCreatorDemo"
```
展示三种DOCX创建方法的完整演示

#### AdvancedDocumentDemo.java - 高级文档演示
```bash
mvn test -Dtest="com.boundesu.words.sdk.demo.AdvancedDocumentDemo"
```
展示高级文档生成功能，包括：
- 会议纪要生成
- 项目计划生成
- API文档生成
- 用户手册生成
- 测试报告生成

### 输出文件位置

运行示例后，生成的文档将保存在以下目录：
- `quick-start-output/` - 快速入门示例输出
- `advanced-features-output/` - 高级功能示例输出
- `business-reports/` - 商业报告示例输出
- `technical-docs/` - 技术文档示例输出
- `demo-output/` - 演示程序输出

## 📚 API参考

### DocumentCreator接口

所有DOCX创建器都实现了统一的`DocumentCreator`接口：

```java
public interface DocumentCreator {
    DocumentCreator setTitle(String title);
    DocumentCreator setAuthor(String author);
    DocumentCreator addHeading(String text, int level);
    DocumentCreator addParagraph(String text);
    void createDocument(Path outputPath) throws IOException;
    byte[] createDocumentAsBytes() throws IOException;
}
```

### PoiDirectDocxCreator特有方法

```java
// 添加粗体段落
creator.addBoldParagraph("粗体文本");

// 添加表格
String[][] tableData = {{"列1", "列2"}, {"数据1", "数据2"}};
creator.addTable(tableData);
```

### HtmlToDocxCreator特有方法

```java
// 添加自定义HTML
htmlCreator.addCustomHtml("<p>HTML内容</p>");
htmlCreator.addCustomHtml("<table><tr><td>表格</td></tr></table>");
```

### XmlToDocxCreator特有方法

```java
// 添加自定义XML元素
xmlCreator.addCustomXmlElement("tagName", "content", "attributes");

// 添加列表
xmlCreator.addList(Arrays.asList("项目1", "项目2"), false); // false=无序列表
xmlCreator.addList(Arrays.asList("项目1", "项目2"), true);  // true=有序列表

// 添加粗体段落
xmlCreator.addBoldParagraph("粗体文本");
```

## 🎯 最佳实践

### 1. 选择合适的创建方法

- **PoiDirectDocxCreator**: 适用于需要精确控制文档格式的场景
- **HtmlToDocxCreator**: 适用于已有HTML内容需要转换的场景
- **XmlToDocxCreator**: 适用于结构化数据转换的场景

### 2. 性能优化

```java
// 批量创建文档时，重用创建器实例
PoiDirectDocxCreator creator = new PoiDirectDocxCreator();
for (DocumentData data : documentList) {
    creator.setTitle(data.getTitle())
           .setAuthor(data.getAuthor())
           .addParagraph(data.getContent())
           .createDocument(Paths.get(data.getFileName()));
}
```

### 3. 错误处理

```java
try {
    DocumentCreator creator = DocumentCreatorFactory.createDocumentCreator("poi");
    creator.setTitle("文档标题")
           .addParagraph("内容")
           .createDocument(Paths.get("output.docx"));
} catch (IOException e) {
    logger.error("文档创建失败: " + e.getMessage(), e);
} catch (IllegalArgumentException e) {
    logger.error("无效的创建器类型: " + e.getMessage(), e);
}
```

### 4. 内存管理

```java
// 对于大文档，使用字节数组方式可以更好地控制内存
byte[] documentBytes = creator.createDocumentAsBytes();
// 处理字节数组...
```

## 🧪 测试

### 运行所有测试

```bash
# 运行所有TestNG测试
mvn test

# 运行所有测试（包括Spring Boot测试）
mvn test -DsuiteXmlFile=src/test/resources/testng.xml
```

### 运行特定测试

```bash
# 测试DOCX创建功能
mvn test -Dtest="com.boundesu.words.sdk.creator.DocumentCreatorTest"

# 测试高级功能
mvn test -Dtest="com.boundesu.words.sdk.advanced.*Test"

# 测试传统功能
mvn test -Dtest="com.boundesu.words.example.BoundesuWordsExample"

# 运行所有示例
mvn test -Dtest="com.boundesu.words.example.*Example"

# 运行演示程序
mvn test -Dtest="com.boundesu.words.sdk.demo.*Demo"
```

### 测试覆盖情况

- **总测试用例**: 29个
- **DOCX创建器测试**: DocumentCreatorTest (3种创建方式)
- **高级功能测试**: AdvancedDocumentGeneratorTest (11个测试用例)
- **模板生成器测试**: DocumentTemplateGeneratorTest (8个测试用例)
- **HTML解析测试**: HtmlContentParserTest (7个测试用例)
- **示例程序**: 5个完整示例
- **演示程序**: 2个演示程序

### 查看测试报告

```bash
# 生成测试报告
mvn test

# 查看TestNG报告
open test-output/index.html

# 查看JUnit报告
open test-output/junitreports/
```

## 🔧 配置

### Maven配置

项目的主要依赖（在`pom.xml`中）：

```xml
<dependencies>
    <!-- Apache POI for DOCX creation -->
    <dependency>
        <groupId>org.apache.poi</groupId>
        <artifactId>poi</artifactId>
        <version>5.4.1</version>
    </dependency>
    
    <dependency>
        <groupId>org.apache.poi</groupId>
        <artifactId>poi-ooxml</artifactId>
        <version>5.4.1</version>
    </dependency>
    
    <!-- Jsoup for HTML parsing -->
    <dependency>
        <groupId>org.jsoup</groupId>
        <artifactId>jsoup</artifactId>
        <version>1.15.3</version>
    </dependency>
    
    <!-- Jackson for JSON processing -->
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.15.2</version>
    </dependency>
    
    <!-- TestNG for testing -->
    <dependency>
        <groupId>org.testng</groupId>
        <artifactId>testng</artifactId>
        <version>7.8.0</version>
        <scope>test</scope>
    </dependency>
    
    <!-- Lombok for code generation -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.30</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

### SDK配置

作为独立的工具类SDK，可以通过代码直接配置：

```java
// 获取配置实例
BoundesuWordsConfig config = BoundesuWordsConfig.getInstance();

// 设置基本配置
config.setEncoding("UTF-8");
config.getDefaultConfig().setAuthor("您的名字");
config.getDefaultConfig().setLanguage("zh-CN");

// 设置文档限制
config.getMax().getDocument().setSize(10485760); // 10MB
config.getMax().getParagraph().setLength(10000);

// 启用功能
config.getEnable().setValidation(true);
config.getEnable().getAuto().setSave(false);
```

## 📁 项目结构

```
src/
├── main/java/com/boundesu/words/sdk/
│   ├── BoundesuWordsSDK.java       # 主入口类
│   ├── advanced/                   # 高级功能
│   │   ├── AdvancedDocumentGenerator.java
│   │   ├── DocumentTemplateGenerator.java
│   │   └── HtmlContentParser.java
│   ├── config/                     # 配置类
│   │   └── BoundesuWordsConfig.java
│   ├── constants/                  # 常量定义
│   ├── converter/                  # 转换器
│   ├── creator/                    # DOCX创建器
│   │   ├── DocumentCreator.java
│   │   ├── DocumentCreatorFactory.java
│   │   └── impl/
│   │       ├── HtmlToDocxCreator.java
│   │       ├── PoiDirectDocxCreator.java
│   │       └── XmlToDocxCreator.java
│   ├── exception/                  # 异常类
│   ├── export/                     # 导出功能
│   ├── factory/                    # 工厂类
│   ├── model/                      # 文档模型类
│   │   ├── BoundesuDocument.java
│   │   ├── BoundesuParagraph.java
│   │   ├── BoundesuTable.java
│   │   └── ...
│   ├── service/                    # 服务类
│   │   │   └── BoundesuDocumentService.java
│   │   └── util/                   # 工具类
│   └── example/                    # 空目录（示例在test中）
└── test/java/com/boundesu/words/
    ├── sdk/
    │   ├── advanced/               # 高级功能测试
    │   │   ├── AdvancedDocumentGeneratorTest.java
    │   │   ├── DocumentTemplateGeneratorTest.java
    │   │   └── HtmlContentParserTest.java
    │   ├── creator/                # 创建器测试
    │   │   └── DocumentCreatorTest.java
    │   └── demo/                   # 演示程序
    │       ├── AdvancedDocumentDemo.java
    │       └── DocumentCreatorDemo.java
    └── example/                    # 使用示例
        ├── AdvancedFeaturesExample.java
        ├── BoundesuWordsExample.java
        ├── BusinessReportExample.java
        ├── QuickStartExample.java
        ├── TechnicalDocumentExample.java
        └── README.md
```

## 🎯 核心类说明

### 主要入口类
- **BoundesuWordsSDK** - 主入口类，提供所有文档操作的API接口
- **WordsApplication** - Spring Boot启动类

### DOCX创建器
- **DocumentCreator** - 统一的创建器接口
- **DocumentCreatorFactory** - 工厂类，管理创建器实例
- **PoiDirectDocxCreator** - 直接使用POI创建DOCX
- **HtmlToDocxCreator** - HTML转DOCX创建器
- **XmlToDocxCreator** - XML转DOCX创建器

### 高级功能
- **AdvancedDocumentGenerator** - 高级文档生成器
- **DocumentTemplateGenerator** - 文档模板生成器
- **HtmlContentParser** - HTML内容解析器

### 文档模型
- **BoundesuDocument** - 核心文档模型
- **BoundesuParagraph** - 段落模型
- **BoundesuTable** - 表格模型
- **BoundesuDocumentStyle** - 文档样式
- **BoundesuParagraphStyle** - 段落样式

### 服务类
- **BoundesuDocumentService** - 文档处理服务，实现具体的文档操作逻辑

## 🔧 配置选项

### 文档主题
- `DEFAULT` - 默认主题
- `PROFESSIONAL` - 专业主题
- `MODERN` - 现代主题
- `CLASSIC` - 经典主题
- `MINIMAL` - 简约主题

### 页面设置
- A4、A3、Letter等标准页面尺寸
- 自定义页面尺寸
- 页面方向（纵向/横向）
- 页边距设置

## ⚡ 性能特性

### 内存优化
- **流式处理**: 大文档采用流式处理，避免内存溢出
- **对象池**: 重用文档对象，减少GC压力
- **延迟加载**: 按需加载文档内容，提高启动速度

### 处理能力
- **小文档** (< 10MB): 毫秒级处理
- **中等文档** (10-100MB): 秒级处理
- **大文档** (> 100MB): 分钟级处理，支持进度回调

### 并发支持
- **线程安全**: 核心API支持多线程并发调用
- **批量处理**: 支持批量文档生成，提高吞吐量
- **异步处理**: 支持异步文档生成，不阻塞主线程

### 格式支持
- **DOCX**: 完整支持，基于Apache POI 5.4.1
- **HTML**: 支持导出，基于Jsoup解析
- **文本**: 支持纯文本导出
- **XML**: 支持XML内容解析和转换

### 性能建议
1. **大文档处理**: 使用流式API，避免一次性加载
2. **批量操作**: 使用批量API，减少系统调用
3. **内存配置**: 建议JVM堆内存至少512MB
4. **并发控制**: 建议并发线程数不超过CPU核心数的2倍

### 架构特点
- **轻量级** - 不依赖大型第三方库
- **高性能** - 纯Java实现，性能优异
- **内存友好** - 合理的内存使用策略
- **可扩展** - 模块化设计，易于扩展

## 🔍 故障排除

### 常见问题

#### 1. 文档创建失败

**问题**: `IOException: Document already closed`

**解决方案**: 确保不要重复使用已关闭的文档实例。每次创建新文档时，创建器会自动处理文档生命周期。

```java
// 正确做法
PoiDirectDocxCreator creator = new PoiDirectDocxCreator();
creator.setTitle("文档1").createDocument(Paths.get("doc1.docx"));
creator.setTitle("文档2").createDocument(Paths.get("doc2.docx")); // 自动创建新实例
```

**问题**: `DocumentCreationException: Failed to create document`

**解决方案**:
- 检查输出目录是否存在且有写权限
- 确认文件路径格式正确
- 验证输入内容格式是否有效

#### 2. 内存不足

**问题**: `OutOfMemoryError` 在处理大文档时

**解决方案**: 使用字节数组方式并及时释放资源

```java
try {
    byte[] docBytes = creator.createDocumentAsBytes();
    Files.write(Paths.get("large_doc.docx"), docBytes);
} catch (OutOfMemoryError e) {
    // 减少文档内容或增加JVM内存
    System.gc(); // 强制垃圾回收
}
```

**问题**: `Java heap space` 错误

**解决方案**:
- 增加JVM堆内存: -Xmx2g
- 使用流式处理API处理大文档
- 分批处理大量文档
- 及时释放不需要的文档对象

#### 3. 依赖冲突

**问题**: `NoClassDefFoundError` 或 `ClassNotFoundException`

**解决方案**: 检查Maven依赖版本兼容性

```bash
# 查看依赖树
mvn dependency:tree

# 解决冲突
mvn dependency:resolve
```

**解决方案**:
- 检查Maven依赖版本兼容性
- 排除冲突的传递依赖
- 使用Maven dependency:tree查看依赖树
- 确保Apache POI版本为5.4.1

#### 4. 编码问题

**问题**: 中文字符显示乱码

**解决方案**: 确保使用UTF-8编码

```java
// 在JVM启动参数中添加
-Dfile.encoding=UTF-8
```

**解决方案**:
- 确保项目编码为UTF-8
- 在JVM启动参数中添加: -Dfile.encoding=UTF-8
- 检查输入文本的编码格式
- 使用正确的字体设置

#### 5. 配置问题

**问题**: SDK配置不生效

**解决方案**:
- 确保正确获取配置实例: BoundesuWordsConfig.getInstance()
- 检查配置参数是否正确设置
- 验证配置值的有效性
- 查看日志获取详细错误信息

#### 6. TestNG测试失败

**问题**: 测试用例执行失败

**解决方案**:
- 检查testng.xml配置
- 确保测试类路径正确
- 验证测试数据文件存在
- 查看测试日志获取详细错误信息

### 调试技巧

#### 启用详细日志

```java
// 在代码中启用调试日志
Logger logger = LoggerFactory.getLogger(DocumentCreator.class);
logger.debug("创建文档: {}", title);
```

#### 验证生成的文档

```java
// 验证文档是否正确生成
Path docPath = Paths.get("test.docx");
if (Files.exists(docPath) && Files.size(docPath) > 0) {
    System.out.println("文档创建成功，大小: " + Files.size(docPath) + " 字节");
} else {
    System.err.println("文档创建失败或为空");
}
```

## 🤝 贡献指南

### 开发环境设置

1. **克隆仓库**
```bash
git clone https://github.com/your-org/boundesu-words-jdk17.git
cd boundesu-words-jdk17
```

2. **安装依赖**
```bash
mvn clean install
```

3. **运行测试**
```bash
mvn test
```

### 代码规范

#### 1. 代码风格

- 使用4个空格缩进
- 类名使用PascalCase
- 方法名和变量名使用camelCase
- 常量使用UPPER_SNAKE_CASE

#### 2. 注释规范

```java
/**
 * 创建DOCX文档的工厂类
 * 
 * @author 作者姓名
 * @since 1.0.0
 */
public class DocumentCreatorFactory {
    
    /**
     * 根据类型创建文档创建器
     * 
     * @param type 创建器类型 ("poi", "html", "xml")
     * @return 文档创建器实例
     * @throws IllegalArgumentException 当类型无效时
     */
    public static DocumentCreator createDocumentCreator(String type) {
        // 实现代码...
    }
}
```

#### 3. 测试规范

```java
@Test
public void testCreateDocument_WithValidInput_ShouldSucceed() {
    // Given
    DocumentCreator creator = new PoiDirectDocxCreator();
    Path outputPath = Paths.get("test.docx");
    
    // When
    creator.setTitle("测试文档")
           .addParagraph("测试内容")
           .createDocument(outputPath);
    
    // Then
    assertTrue(Files.exists(outputPath));
    assertTrue(Files.size(outputPath) > 0);
}
```

### 提交流程

1. **创建功能分支**
```bash
git checkout -b feature/new-feature
```

2. **提交代码**
```bash
git add .
git commit -m "feat: 添加新功能描述"
```

3. **推送分支**
```bash
git push origin feature/new-feature
```

4. **创建Pull Request**

### 提交信息规范

使用[Conventional Commits](https://www.conventionalcommits.org/)规范：

- `feat:` 新功能
- `fix:` 修复bug
- `docs:` 文档更新
- `style:` 代码格式调整
- `refactor:` 代码重构
- `test:` 测试相关
- `chore:` 构建过程或辅助工具的变动

### 发布流程

1. **更新版本号**
```bash
mvn versions:set -DnewVersion=1.1.0
```

2. **创建发布标签**
```bash
git tag -a v1.1.0 -m "Release version 1.1.0"
git push origin v1.1.0
```

3. **部署到Maven仓库**
```bash
mvn clean deploy
```

## 📋 项目总结

### 核心优势
- **🚀 简单易用**: 提供直观的API，快速上手
- **🔧 功能丰富**: 支持多种文档创建方式和格式导出
- **⚡ 性能优异**: 基于Apache POI 5.4.1，处理效率高
- **🛡️ 稳定可靠**: 完整的测试覆盖，生产环境验证
- **📚 文档完善**: 丰富的示例代码和详细的使用指南

### 适用场景
- **企业报告生成**: 财务报告、销售报告、项目状态报告
- **技术文档创建**: API文档、用户手册、系统架构文档
- **自动化办公**: 批量文档生成、模板填充、格式转换
- **内容管理系统**: 动态文档生成、多格式导出

### 技术特色
- **多创建方式**: POI直接创建、HTML转换、XML解析
- **智能解析**: 支持复杂HTML结构和XML内容解析
- **模板系统**: 内置文档模板生成器，快速创建标准文档
- **Spring集成**: 无缝集成Spring Boot，支持依赖注入
- **测试驱动**: 基于TestNG的完整测试体系

---

## 📞 联系我们

- **项目主页**: [GitHub Repository](https://github.com/your-org/boundesu-words-jdk17)
- **问题反馈**: [Issues](https://github.com/your-org/boundesu-words-jdk17/issues)
- **技术支持**: support@boundesu.com
- **开发团队**: Boundesu Development Team

---

<div align="center">

**感谢使用 Boundesu Words JDK17 SDK！**

如果这个项目对您有帮助，请给我们一个 ⭐ Star！

</div>