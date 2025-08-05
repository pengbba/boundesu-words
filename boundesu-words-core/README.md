# Boundesu Words Core - 核心类架构

## 概述

Boundesu Words Core 已从工具类架构重构为核心类架构，更好地模拟 Aspose Words API 的设计模式，提供更直观和强大的文档处理能力。

## 核心类结构

### 1. 文档核心类

#### Document
- **位置**: `com.boundesu.words.core.Document`
- **功能**: 表示 Word 文档的核心类
- **主要方法**:
  - `Document()` - 创建新文档
  - `Document(String fileName)` - 从文件加载文档
  - `Document(String fileName, LoadOptions loadOptions)` - 使用加载选项从文件加载
  - `save(String fileName)` - 保存文档
  - `save(String fileName, SaveOptions saveOptions)` - 使用保存选项保存文档

#### DocumentBuilder
- **位置**: `com.boundesu.words.core.builder.DocumentBuilder`
- **功能**: 用于构建和编辑文档内容
- **主要功能**:
  - 文本写入和格式设置
  - 字体格式控制
  - 段落格式设置
  - 页面设置
  - 插入表格、图片、超链接等

### 2. 选项类

#### LoadOptions
- **位置**: `com.boundesu.words.core.options.LoadOptions`
- **功能**: 文档加载选项基类
- **支持格式**: DOC, DOCX, HTML, RTF, TXT 等

#### HtmlLoadOptions
- **位置**: `com.boundesu.words.core.options.HtmlLoadOptions`
- **功能**: HTML 文档加载专用选项
- **特性**: Web 请求超时、块导入模式等

#### SaveOptions
- **位置**: `com.boundesu.words.core.options.SaveOptions`
- **功能**: 文档保存选项抽象基类

#### DocxSaveOptions
- **位置**: `com.boundesu.words.core.options.DocxSaveOptions`
- **功能**: DOCX 格式保存选项
- **特性**: 压缩级别、密码保护等

#### PdfSaveOptions
- **位置**: `com.boundesu.words.core.options.PdfSaveOptions`
- **功能**: PDF 格式保存选项
- **特性**: PDF 合规性、字体嵌入、图像压缩等

### 3. 主入口类

#### BoundesuWords
- **位置**: `com.boundesu.words.core.BoundesuWords`
- **功能**: SDK 主入口，提供便捷的 API 方法
- **主要方法**:
  - `createDocument()` - 创建新文档
  - `loadDocument(String fileName)` - 加载文档
  - `createDocumentBuilder()` - 创建文档构建器
  - `saveDocument()` - 保存文档

## ✨ 主要功能

- **多格式支持**: 支持 DOCX、PDF、HTML、RTF 等多种文档格式
- **高性能转换**: 基于 Apache POI 的高效文档处理引擎
- **灵活配置**: 提供丰富的配置选项，满足不同场景需求
- **易于集成**: 简洁的 API 设计，快速集成到现有项目

## 🚀 快速开始

### 添加依赖

```xml
<dependency>
    <groupId>com.boundesu</groupId>
    <artifactId>boundesu-words-core</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

### 基本使用

```java
// 创建 BoundesuWords 实例
BoundesuWords boundesuWords = new BoundesuWords();

// 获取版本信息
String version = boundesuWords.getVersion();
System.out.println("Boundesu Words 版本: " + version);

// 保存文档到文件
boundesuWords.saveToFile(document, "output.docx");
```

## 使用示例

### 基本文档创建

```java
// 创建 SDK 实例
BoundesuWords sdk = new BoundesuWords();

// 创建新文档
Document doc = sdk.createDocument();
DocumentBuilder builder = sdk.createDocumentBuilder(doc);

// 设置字体格式
builder.getFont().setName("Arial");
builder.getFont().setSize(12);
builder.getFont().setBold(true);

// 写入文本
builder.writeln("Hello, Boundesu Words!");

// 保存文档
sdk.saveDocument(doc, "output.docx");
```

### 使用加载和保存选项

```java
// HTML 加载选项
HtmlLoadOptions htmlOptions = new HtmlLoadOptions();
htmlOptions.setWebRequestTimeout(5000);

// 加载 HTML 文档
Document doc = sdk.loadDocument("input.html", htmlOptions);

// PDF 保存选项
PdfSaveOptions pdfOptions = new PdfSaveOptions();
pdfOptions.setCompliance(PdfSaveOptions.PdfCompliance.PDF_A_1B);
pdfOptions.setEmbedFullFonts(true);

// 保存为 PDF
sdk.saveDocument(doc, "output.pdf", pdfOptions);
```

### 高级格式设置

```java
DocumentBuilder builder = sdk.createDocumentBuilder();

// 段落格式
builder.getParagraphFormat().setAlignment(DocumentBuilder.ParagraphAlignment.CENTER);
builder.getParagraphFormat().setSpaceBefore(10);
builder.getParagraphFormat().setSpaceAfter(10);

// 字体格式
builder.getFont().setUnderline(DocumentBuilder.UnderlineType.SINGLE);
builder.getFont().setHighlightColor(Color.YELLOW);
builder.getFont().setSubscript(true);

// 页面设置
builder.getPageSetup().setPaperSize(DocumentBuilder.PaperSize.A4);
builder.getPageSetup().setOrientation(DocumentBuilder.Orientation.PORTRAIT);
```

## 架构优势

### 1. API 兼容性
- 严格按照 Aspose Words API 设计模式
- 平滑的代码迁移体验
- 熟悉的方法命名和参数结构

### 2. 类型安全
- 使用枚举类型替代字符串常量
- 编译时类型检查
- 更好的 IDE 支持和代码提示

### 3. 功能完整性
- 涵盖文档创建、加载、编辑、保存全流程
- 支持多种文档格式
- 丰富的格式设置选项

### 4. 扩展性
- 模块化设计
- 清晰的继承关系
- 便于添加新功能和格式支持

## 迁移指南

### 从工具类迁移到核心类

**旧方式 (工具类)**:
```java
DocumentBuilderUtils.Font font = new DocumentBuilderUtils.Font();
DocumentBuilderUtils utils = new DocumentBuilderUtils();
```

**新方式 (核心类)**:
```java
Document doc = new Document();
DocumentBuilder builder = new DocumentBuilder(doc.getInternalDocument());
DocumentBuilder.Font font = builder.getFont();
```

### 主要变化
1. **类位置变更**: 从 `util` 包移动到对应的功能包
2. **实例化方式**: 不再是静态工具类，而是面向对象的核心类
3. **方法调用**: 通过实例方法而非静态方法调用
4. **包结构**: 更清晰的包组织结构

## 🏗️ 核心组件

### 主入口类

#### BoundesuWords

核心SDK入口类，提供最常用的转换功能：

```java
// 创建SDK实例
BoundesuWords sdk = new BoundesuWords();

// HTML转换
XWPFDocument doc1 = sdk.htmlToDocx(htmlContent);
XWPFDocument doc2 = sdk.htmlToDocx(htmlFile);

// XML转换
XWPFDocument doc3 = sdk.xmlToDocx(xmlContent);
XWPFDocument doc4 = sdk.xmlToDocx(xmlFile);

// 自动识别文件类型转换
XWPFDocument doc5 = sdk.convertToDocx(inputFile);

// 一键转换并保存
sdk.

convert(inputFile, outputFile);
sdk.

convert("input.html","output.docx");
```

#### BoundesuWordsService

核心服务类，实现具体的转换逻辑：

```java
BoundesuWordsService service = new BoundesuWordsService();

// 获取SDK信息
Map<String, String> info = BoundesuWordsService.getSDKInfo();
String version = BoundesuWordsService.getVersion();

// 转换操作
XWPFDocument document = service.convertHtmlToDocx(htmlContent);
service.

saveDocxToFile(document, outputFile);
```

### 文档创建器

#### DocumentCreator 接口

统一的文档创建器接口：

```java
public interface DocumentCreator {
    DocumentCreator setTitle(String title);

    DocumentCreator setAuthor(String author);

    DocumentCreator addParagraph(String text);

    DocumentCreator addHeading(String text, int level);

    DocumentCreator setHeader(String headerText);

    DocumentCreator setFooter(String footerText);

    void createDocument(Path outputPath) throws IOException;

    byte[] createDocumentAsBytes() throws IOException;
}
```

#### DocumentCreatorFactory

文档创建器工厂，支持三种创建方式：

```java
// 直接使用POI创建
DocumentCreator poiCreator = DocumentCreatorFactory.createDocumentCreator("poi");

// 通过HTML转换创建
DocumentCreator htmlCreator = DocumentCreatorFactory.createDocumentCreator("html");

// 通过XML转换创建
DocumentCreator xmlCreator = DocumentCreatorFactory.createDocumentCreator("xml");

// 使用枚举类型
DocumentCreator creator = DocumentCreatorFactory.createDocumentCreator(
        DocumentCreatorFactory.CreatorType.DIRECT_POI
);
```

#### 具体实现类

1. **PoiDirectDocxCreator**: 直接使用Apache POI创建DOCX
2. **HtmlToDocxCreator**: 通过HTML转换创建DOCX
3. **XmlBasedDocxCreator**: 通过XML转换创建DOCX

### 高级文档生成器

#### AdvancedDocumentGenerator

支持复杂文档结构的高级生成器：

```java
// 创建高级生成器
AdvancedDocumentGenerator generator = new AdvancedDocumentGenerator();

// 配置文档属性
AdvancedDocumentGenerator.DocumentConfig config = new AdvancedDocumentGenerator.DocumentConfig();
config.

setGenerateToc(true);           // 生成目录
config.

setGenerateCoverPage(true);     // 生成封面
config.

setTocTitle("目录");            // 目录标题
config.

setCompany("公司名称");         // 公司名称
generator.

setConfig(config);

// 生成技术文档
Map<String, String> sections = new HashMap<>();
sections.

put("概述","项目概述内容...");
sections.

put("架构设计","架构设计说明...");

generator.

generateTechnicalDocument(
    "技术文档标题",
            "作者",
            "文档介绍",
    sections,
    "总结"
);

// 创建文档
generator.

createDocument(Paths.get("technical_doc.docx"));
```

## 🚀 使用示例

### 基础转换示例

```java
import com.boundesu.words.core.BoundesuWords;
import com.boundesu.words.common.exception.BoundesuWordsException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class CoreExample {
    public static void main(String[] args) {
        BoundesuWords sdk = new BoundesuWords();

        try {
            // HTML内容转换
            String htmlContent = "<h1>标题</h1><p>段落内容</p>";
            XWPFDocument document = sdk.htmlToDocx(htmlContent);
            sdk.saveToFile(document, "output.docx");

            // 文件转换
            sdk.convert("input.html", "output.docx");

            System.out.println("转换完成！");

        } catch (BoundesuWordsException e) {
            System.err.println("转换失败: " + e.getMessage());
        }
    }
}
```

### 使用文档创建器

```java
import com.boundesu.words.core.creator.DocumentCreator;
import com.boundesu.words.core.creator.DocumentCreatorFactory;

public class CreatorExample {
    public static void main(String[] args) throws IOException {
        // 创建POI文档创建器
        DocumentCreator creator = DocumentCreatorFactory
                .createDocumentCreator(DocumentCreatorFactory.CreatorType.DIRECT_POI);

        // 构建文档
        creator.setTitle("我的文档")
                .setAuthor("作者姓名")
                .addHeading("第一章", 1)
                .addParagraph("这是第一章的内容...")
                .addHeading("第二章", 1)
                .addParagraph("这是第二章的内容...")
                .setHeader("文档页头")
                .setFooter("文档页脚");

        // 创建文档
        creator.createDocument(Paths.get("my_document.docx"));

        // 或获取字节数组
        byte[] documentBytes = creator.createDocumentAsBytes();
    }
}
```

### 高级文档生成

```java
import com.boundesu.words.core.advanced.AdvancedDocumentGenerator;

public class AdvancedExample {
    public static void main(String[] args) throws IOException {
        AdvancedDocumentGenerator generator = new AdvancedDocumentGenerator();

        // 配置文档
        AdvancedDocumentGenerator.DocumentConfig config =
                new AdvancedDocumentGenerator.DocumentConfig();
        config.setGenerateToc(true);
        config.setGenerateCoverPage(true);
        config.setCompany("我的公司");
        config.setDepartment("技术部");
        generator.setConfig(config);

        // 生成报告文档
        Map<String, String> chapters = new HashMap<>();
        chapters.put("执行摘要", "本季度业绩总结...");
        chapters.put("详细分析", "详细的数据分析...");
        chapters.put("未来规划", "下季度工作计划...");

        generator.generateReportDocument(
                "季度业务报告",
                "业务分析团队",
                "本报告分析了本季度的业务表现",
                chapters
        );

        generator.createDocument(Paths.get("quarterly_report.docx"));
    }
}
```

### 性能监控集成

```java
import com.boundesu.words.common.util.PerformanceMonitor;

public class PerformanceExample {
    public static void main(String[] args) {
        BoundesuWords sdk = new BoundesuWords();

        // 监控转换性能
        PerformanceMonitor.OperationContext context =
                PerformanceMonitor.startOperation("HTML to DOCX Conversion");

        try {
            XWPFDocument document = sdk.htmlToDocx(htmlContent);
            sdk.saveToFile(document, "output.docx");
        } catch (BoundesuWordsException e) {
            e.printStackTrace();
        } finally {
            context.end();
            System.out.println("转换耗时: " + context.getDuration() + "ms");
        }
    }
}
```

## 🔧 配置选项

### 支持的文件格式

- **输入格式**: HTML (.html), XML (.xml)
- **输出格式**: DOCX (.docx)

### 文档创建器类型

| 类型              | 描述          | 适用场景       |
|-----------------|-------------|------------|
| DIRECT_POI      | 直接使用POI API | 需要精确控制文档结构 |
| HTML_CONVERSION | 通过HTML转换    | 有现成的HTML内容 |
| XML_CONVERSION  | 通过XML转换     | 需要结构化文档生成  |

### 高级生成器配置

```java
DocumentConfig config = new DocumentConfig();
config.

setGenerateToc(true);           // 是否生成目录
config.

setGenerateCoverPage(true);     // 是否生成封面
config.

setGenerateFooter(true);        // 是否生成页脚
config.

setGenerateHeader(true);        // 是否生成页头
config.

setTocTitle("目录");            // 目录标题
config.

setDateFormat("yyyy年MM月dd日"); // 日期格式
config.

setCompany("公司名称");         // 公司名称
config.

setDepartment("部门名称");      // 部门名称
```

## 📦 依赖关系

### 模块依赖

- **boundesu-words-common**: 基础工具和异常处理
- **boundesu-words-html**: HTML转换功能
- **boundesu-words-xml**: XML转换功能

### Maven 依赖

```xml

<dependency>
    <groupId>com.boundesu</groupId>
    <artifactId>boundesu-words-core</artifactId>
    <version>1.0.0</version>
</dependency>
```

### 外部依赖

- Apache POI: Microsoft Office文档处理
- SLF4J: 日志门面

## 🧪 测试

运行模块测试：

```bash
mvn test -pl boundesu-words-core
```

运行特定测试类：

```bash
mvn test -pl boundesu-words-core -Dtest=BoundesuWordsTest
```

## 🔍 故障排除

### 常见问题

1. **文件格式不支持**
   ```
   BoundesuWordsException[UNSUPPORTED_FORMAT]: 不支持的文件格式
   ```
   解决方案：检查输入文件扩展名，确保是 .html 或 .xml

2. **文件不存在**
   ```
   BoundesuWordsException[FILE_NOT_FOUND]: 文件不存在
   ```
   解决方案：检查文件路径是否正确，文件是否存在

3. **内存不足**
   ```
   OutOfMemoryError: Java heap space
   ```
   解决方案：增加JVM堆内存大小 `-Xmx2g`

### 调试技巧

```java
// 启用详细日志
System.setProperty("org.slf4j.simpleLogger.defaultLogLevel","debug");

// 获取SDK信息
Map<String, String> info = BoundesuWords.getSDKInfo();
info.

forEach((key, value) ->System.out.

println(key +": "+value));
```

## 📄 许可证

本模块采用 MIT 许可证，详见项目根目录的 LICENSE 文件。

---

**Boundesu Words Core** - 强大的文档转换核心引擎！ 🚀