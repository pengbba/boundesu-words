# Boundesu Words XML

> XML 到 DOCX 转换模块

## 📖 模块简介

boundesu-words-xml 是 Boundesu Words SDK 的 XML 转换模块，专门负责将 XML 内容转换为 Microsoft Word DOCX 格式。该模块提供了高效的 XML 解析和转换功能，支持复杂的 XML 结构和优化的转换性能。

## ✨ 主要功能

- 🔄 **XML 转 DOCX**: 高质量的 XML 到 Word 文档转换
- ⚡ **优化转换**: 提供优化的转换器以提升性能
- 🏗️ **结构化转换**: 智能识别 XML 结构并转换为相应的 Word 元素
- 📊 **表格支持**: 完整的 XML 表格数据转换
- 📝 **文本处理**: 支持各种文本格式和样式
- 🔗 **层次结构**: 保持 XML 的层次结构在 Word 中的体现
- 🛠️ **XML 工具**: 提供 XML 操作和格式化工具
- 🎯 **灵活配置**: 支持多种转换选项和自定义设置

## 🏗️ 核心组件

### XmlToDocxConverter
标准的 XML 转换器类：

```java
// 创建转换器
XmlToDocxConverter converter = new XmlToDocxConverter();

// 从字符串转换
XWPFDocument doc1 = converter.convertXmlToDocx(xmlContent);

// 从输入流转换
InputStream xmlStream = new FileInputStream("input.xml");
XWPFDocument doc2 = converter.convertXmlToDocx(xmlStream);

// 从文件转换
File xmlFile = new File("input.xml");
XWPFDocument doc3 = converter.convertXmlToDocx(xmlFile);
```

### OptimizedXmlToDocxConverter
优化的 XML 转换器，提供更好的性能：

```java
// 创建优化转换器
OptimizedXmlToDocxConverter optimizedConverter = new OptimizedXmlToDocxConverter();

// 转换为字节数组（更高效）
byte[] docxBytes1 = optimizedConverter.convertXmlToDocx(xmlContent);
byte[] docxBytes2 = optimizedConverter.convertXmlToDocx(xmlFile);

// 带选项的转换
OptimizedXmlToDocxConverter.ConversionOptions options = 
    new OptimizedXmlToDocxConverter.ConversionOptions();
options.setPreserveWhitespace(true);
options.setGenerateTableOfContents(true);

byte[] docxBytes3 = optimizedConverter.convertXmlToDocx(xmlContent, options);
```

### XmlToDocxCreator
XML 到 DOCX 的创建器，提供更细粒度的控制：

```java
// 创建 XML 创建器
XmlToDocxCreator creator = new XmlToDocxCreator();

// 解析 XML 文档
Document xmlDoc = DocumentBuilderFactory.newInstance()
    .newDocumentBuilder().parse(new File("input.xml"));

// 创建 Word 文档
XWPFDocument wordDoc = new XWPFDocument();

// 处理 XML 元素
Element rootElement = xmlDoc.getDocumentElement();
creator.processElement(rootElement, wordDoc);

// 保存文档
try (FileOutputStream out = new FileOutputStream("output.docx")) {
    wordDoc.write(out);
}
```

### XmlUtils
XML 工具类，提供 XML 操作和格式化功能：

```java
// XML 文档转字符串
Document xmlDoc = // ... 获取 XML 文档
String xmlString = XmlUtils.documentToString(xmlDoc);

// 带格式的 XML 字符串
String prettyXml = XmlUtils.documentToString(xmlDoc, true);

// 格式化 XML 字符串
String formattedXml = XmlUtils.formatXml(rawXmlString);

// 压缩 XML 字符串（移除空白）
String compressedXml = XmlUtils.compressXml(xmlString);
```

## 🚀 使用示例

### 基础 XML 转换

```java
import com.boundesu.words.xml.XmlToDocxConverter;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import java.io.FileOutputStream;
import java.io.IOException;

public class BasicXmlExample {
    public static void main(String[] args) throws IOException {
        // 创建转换器
        XmlToDocxConverter converter = new XmlToDocxConverter();
        
        // XML 内容
        String xmlContent = """
            <?xml version="1.0" encoding="UTF-8"?>
            <document>
                <title>技术文档</title>
                <section>
                    <heading level="1">第一章 概述</heading>
                    <paragraph>这是第一章的内容，介绍了项目的基本概念。</paragraph>
                    <list type="unordered">
                        <item>功能特性 1</item>
                        <item>功能特性 2</item>
                        <item>功能特性 3</item>
                    </list>
                </section>
                <section>
                    <heading level="1">第二章 详细说明</heading>
                    <paragraph>这里提供了详细的技术说明。</paragraph>
                    <table>
                        <row>
                            <cell>功能</cell>
                            <cell>状态</cell>
                            <cell>优先级</cell>
                        </row>
                        <row>
                            <cell>XML 转换</cell>
                            <cell>完成</cell>
                            <cell>高</cell>
                        </row>
                        <row>
                            <cell>HTML 转换</cell>
                            <cell>完成</cell>
                            <cell>高</cell>
                        </row>
                    </table>
                </section>
            </document>
            """;
        
        // 转换为 DOCX
        XWPFDocument document = converter.convertXmlToDocx(xmlContent);
        
        // 保存文档
        try (FileOutputStream out = new FileOutputStream("xml_output.docx")) {
            document.write(out);
        }
        
        document.close();
        System.out.println("XML 转换完成！");
    }
}
```

### 优化转换示例

```java
import com.boundesu.words.xml.OptimizedXmlToDocxConverter;
import java.io.FileOutputStream;
import java.io.IOException;

public class OptimizedXmlExample {
    public static void main(String[] args) throws IOException {
        // 创建优化转换器
        OptimizedXmlToDocxConverter converter = new OptimizedXmlToDocxConverter();
        
        // 配置转换选项
        OptimizedXmlToDocxConverter.ConversionOptions options = 
            new OptimizedXmlToDocxConverter.ConversionOptions();
        options.setPreserveWhitespace(false);      // 不保留空白
        options.setGenerateTableOfContents(true);  // 生成目录
        options.setIncludeMetadata(true);          // 包含元数据
        
        String xmlContent = """
            <?xml version="1.0" encoding="UTF-8"?>
            <report>
                <metadata>
                    <title>季度报告</title>
                    <author>分析团队</author>
                    <date>2024-01-15</date>
                </metadata>
                <summary>
                    <heading>执行摘要</heading>
                    <content>本季度业绩表现良好，各项指标均达到预期目标。</content>
                </summary>
                <details>
                    <section id="financial">
                        <heading>财务分析</heading>
                        <paragraph>收入同比增长15%，利润率保持稳定。</paragraph>
                        <chart type="bar">
                            <data>
                                <item label="Q1" value="100"/>
                                <item label="Q2" value="115"/>
                                <item label="Q3" value="130"/>
                                <item label="Q4" value="145"/>
                            </data>
                        </chart>
                    </section>
                    <section id="market">
                        <heading>市场分析</heading>
                        <paragraph>市场份额稳步提升，客户满意度持续改善。</paragraph>
                    </section>
                </details>
            </report>
            """;
        
        // 执行优化转换
        byte[] docxBytes = converter.convertXmlToDocx(xmlContent, options);
        
        // 保存文档
        try (FileOutputStream out = new FileOutputStream("optimized_report.docx")) {
            out.write(docxBytes);
        }
        
        System.out.println("优化转换完成！文档大小: " + docxBytes.length + " 字节");
    }
}
```

### 从文件转换

```java
import com.boundesu.words.xml.XmlToDocxConverter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileXmlExample {
    public static void main(String[] args) throws IOException {
        XmlToDocxConverter converter = new XmlToDocxConverter();
        
        // 从文件转换
        File xmlFile = new File("input.xml");
        XWPFDocument document = converter.convertXmlToDocx(xmlFile);
        
        // 保存到文件
        try (FileOutputStream out = new FileOutputStream("file_output.docx")) {
            document.write(out);
        }
        
        document.close();
        System.out.println("文件转换完成！");
    }
}
```

### 使用 XML 工具

```java
import com.boundesu.words.xml.XmlUtils;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.InputSource;

public class XmlUtilsExample {
    public static void main(String[] args) throws Exception {
        // 原始 XML 字符串（未格式化）
        String rawXml = "<root><item>value1</item><item>value2</item></root>";
        
        // 格式化 XML
        String formattedXml = XmlUtils.formatXml(rawXml);
        System.out.println("格式化后的 XML:");
        System.out.println(formattedXml);
        
        // 解析为 Document
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(new InputSource(new StringReader(rawXml)));
        
        // Document 转字符串
        String xmlString = XmlUtils.documentToString(doc);
        System.out.println("\nDocument 转字符串:");
        System.out.println(xmlString);
        
        // 带格式的 Document 转字符串
        String prettyXmlString = XmlUtils.documentToString(doc, true);
        System.out.println("\n格式化的 Document 字符串:");
        System.out.println(prettyXmlString);
        
        // 压缩 XML（移除空白）
        String compressedXml = XmlUtils.compressXml(formattedXml);
        System.out.println("\n压缩后的 XML:");
        System.out.println(compressedXml);
    }
}
```

### 自定义 XML 处理

```java
import com.boundesu.words.xml.XmlToDocxCreator;
import org.w3c.dom.*;
import org.apache.poi.xwpf.usermodel.*;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class CustomXmlExample {
    public static void main(String[] args) throws Exception {
        // 解析 XML 文件
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document xmlDoc = factory.newDocumentBuilder().parse(new File("custom.xml"));
        
        // 创建 Word 文档
        XWPFDocument wordDoc = new XWPFDocument();
        
        // 创建自定义处理器
        CustomXmlProcessor processor = new CustomXmlProcessor();
        
        // 处理根元素
        Element root = xmlDoc.getDocumentElement();
        processor.processCustomElement(root, wordDoc);
        
        // 保存文档
        try (FileOutputStream out = new FileOutputStream("custom_output.docx")) {
            wordDoc.write(out);
        }
        
        wordDoc.close();
    }
    
    static class CustomXmlProcessor {
        public void processCustomElement(Element element, XWPFDocument document) {
            String tagName = element.getTagName();
            
            switch (tagName) {
                case "title":
                    // 处理标题
                    XWPFParagraph titlePara = document.createParagraph();
                    titlePara.setStyle("Title");
                    XWPFRun titleRun = titlePara.createRun();
                    titleRun.setText(element.getTextContent());
                    titleRun.setBold(true);
                    titleRun.setFontSize(18);
                    break;
                    
                case "section":
                    // 处理章节
                    processSection(element, document);
                    break;
                    
                case "data-table":
                    // 处理数据表格
                    processDataTable(element, document);
                    break;
                    
                default:
                    // 处理子元素
                    NodeList children = element.getChildNodes();
                    for (int i = 0; i < children.getLength(); i++) {
                        Node child = children.item(i);
                        if (child instanceof Element) {
                            processCustomElement((Element) child, document);
                        }
                    }
                    break;
            }
        }
        
        private void processSection(Element section, XWPFDocument document) {
            // 添加章节标题
            String title = section.getAttribute("title");
            if (!title.isEmpty()) {
                XWPFParagraph para = document.createParagraph();
                para.setStyle("Heading1");
                XWPFRun run = para.createRun();
                run.setText(title);
                run.setBold(true);
            }
            
            // 处理章节内容
            NodeList children = section.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                Node child = children.item(i);
                if (child instanceof Element) {
                    processCustomElement((Element) child, document);
                }
            }
        }
        
        private void processDataTable(Element tableElement, XWPFDocument document) {
            NodeList rows = tableElement.getElementsByTagName("row");
            if (rows.getLength() == 0) return;
            
            // 创建表格
            XWPFTable table = document.createTable();
            
            for (int i = 0; i < rows.getLength(); i++) {
                Element row = (Element) rows.item(i);
                NodeList cells = row.getElementsByTagName("cell");
                
                XWPFTableRow tableRow;
                if (i == 0) {
                    tableRow = table.getRow(0);
                } else {
                    tableRow = table.createRow();
                }
                
                for (int j = 0; j < cells.getLength(); j++) {
                    Element cell = (Element) cells.item(j);
                    XWPFTableCell tableCell;
                    
                    if (j == 0 && i > 0) {
                        tableCell = tableRow.getCell(0);
                    } else if (j > 0) {
                        tableCell = tableRow.addNewTableCell();
                    } else {
                        tableCell = tableRow.getCell(0);
                    }
                    
                    tableCell.setText(cell.getTextContent());
                    
                    // 表头样式
                    if (i == 0) {
                        XWPFParagraph para = tableCell.getParagraphs().get(0);
                        XWPFRun run = para.getRuns().get(0);
                        run.setBold(true);
                    }
                }
            }
        }
    }
}
```

## 🎯 支持的 XML 结构

### 文档结构

| 元素 | 描述 | 转换效果 |
|------|------|----------|
| `<document>` | 文档根元素 | Word 文档 |
| `<title>` | 文档标题 | 文档标题样式 |
| `<section>` | 文档章节 | 章节分组 |
| `<metadata>` | 文档元数据 | 文档属性 |

### 内容元素

| 元素 | 描述 | 转换效果 |
|------|------|----------|
| `<heading>` | 标题 | Word 标题样式 |
| `<paragraph>` | 段落 | 普通段落 |
| `<text>` | 文本内容 | 文本运行 |
| `<content>` | 内容块 | 段落内容 |

### 格式元素

| 元素 | 描述 | 转换效果 |
|------|------|----------|
| `<bold>` | 粗体文本 | 粗体格式 |
| `<italic>` | 斜体文本 | 斜体格式 |
| `<underline>` | 下划线文本 | 下划线格式 |
| `<highlight>` | 高亮文本 | 高亮背景 |

### 列表元素

| 元素 | 描述 | 转换效果 |
|------|------|----------|
| `<list>` | 列表容器 | Word 列表 |
| `<item>` | 列表项 | 列表项目 |
| `<ordered-list>` | 有序列表 | 编号列表 |
| `<unordered-list>` | 无序列表 | 项目符号列表 |

### 表格元素

| 元素 | 描述 | 转换效果 |
|------|------|----------|
| `<table>` | 表格 | Word 表格 |
| `<row>` | 表格行 | 表格行 |
| `<cell>` | 表格单元格 | 表格单元格 |
| `<header-row>` | 表头行 | 粗体表头 |

### 属性支持

| 属性 | 适用元素 | 描述 |
|------|----------|------|
| `level` | `<heading>` | 标题级别 (1-6) |
| `type` | `<list>` | 列表类型 (ordered/unordered) |
| `style` | 多个元素 | 样式类名 |
| `id` | 多个元素 | 元素标识符 |

## ⚙️ 配置选项

### 转换选项

```java
// 创建转换选项
OptimizedXmlToDocxConverter.ConversionOptions options = 
    new OptimizedXmlToDocxConverter.ConversionOptions();

// 基础选项
options.setPreserveWhitespace(true);        // 保留空白字符
options.setGenerateTableOfContents(true);   // 生成目录
options.setIncludeMetadata(true);           // 包含元数据
options.setValidateXml(true);               // 验证 XML 格式

// 样式选项
options.setDefaultFontFamily("宋体");       // 默认字体
options.setDefaultFontSize(12);             // 默认字号
options.setPageMargins(720);                // 页边距 (twips)

// 表格选项
options.setTableBorderStyle("single");      // 表格边框样式
options.setTableHeaderStyle("bold");        // 表头样式

// 使用选项进行转换
byte[] result = converter.convertXmlToDocx(xmlContent, options);
```

### XML 格式化选项

```java
// 格式化选项
boolean prettyPrint = true;     // 美化输出
String indent = "  ";           // 缩进字符
String encoding = "UTF-8";      // 字符编码

// 应用格式化
String formattedXml = XmlUtils.formatXml(rawXml, prettyPrint, indent);
```

## 🔧 高级功能

### 自定义元素处理器

```java
public class CustomElementProcessor extends XmlToDocxCreator {
    
    @Override
    protected void processCustomElement(Element element, XWPFDocument document) {
        String tagName = element.getTagName();
        
        switch (tagName) {
            case "code-block":
                processCodeBlock(element, document);
                break;
            case "image":
                processImage(element, document);
                break;
            case "chart":
                processChart(element, document);
                break;
            default:
                super.processCustomElement(element, document);
                break;
        }
    }
    
    private void processCodeBlock(Element element, XWPFDocument document) {
        XWPFParagraph para = document.createParagraph();
        para.setStyle("Code");
        
        XWPFRun run = para.createRun();
        run.setFontFamily("Consolas");
        run.setFontSize(10);
        run.setText(element.getTextContent());
        
        // 设置背景色
        para.getCTP().addNewPPr().addNewShd().setFill("F5F5F5");
    }
}
```

### 批量转换

```java
public class BatchXmlConverter {
    private final OptimizedXmlToDocxConverter converter;
    
    public BatchXmlConverter() {
        this.converter = new OptimizedXmlToDocxConverter();
    }
    
    public void convertDirectory(String inputDir, String outputDir) throws IOException {
        File input = new File(inputDir);
        File output = new File(outputDir);
        
        if (!output.exists()) {
            output.mkdirs();
        }
        
        File[] xmlFiles = input.listFiles((dir, name) -> name.endsWith(".xml"));
        
        for (File xmlFile : xmlFiles) {
            String baseName = xmlFile.getName().replaceAll("\\.xml$", "");
            File docxFile = new File(output, baseName + ".docx");
            
            try {
                byte[] docxBytes = converter.convertXmlToDocx(xmlFile);
                Files.write(docxFile.toPath(), docxBytes);
                System.out.println("转换完成: " + xmlFile.getName() + " -> " + docxFile.getName());
            } catch (Exception e) {
                System.err.println("转换失败: " + xmlFile.getName() + " - " + e.getMessage());
            }
        }
    }
}
```

### 性能监控

```java
import com.boundesu.words.common.util.PerformanceMonitor;

public class MonitoredXmlConverter {
    public void convertWithMonitoring(String xmlContent) {
        PerformanceMonitor.OperationContext context = 
            PerformanceMonitor.startOperation("XML to DOCX Conversion");
        
        try {
            OptimizedXmlToDocxConverter converter = new OptimizedXmlToDocxConverter();
            byte[] result = converter.convertXmlToDocx(xmlContent);
            
            System.out.println("转换成功，文档大小: " + result.length + " 字节");
        } catch (Exception e) {
            System.err.println("转换失败: " + e.getMessage());
        } finally {
            context.end();
            System.out.println("转换耗时: " + context.getDuration() + "ms");
        }
    }
}
```

## 📦 依赖关系

### 模块依赖

- **boundesu-words-common**: 基础工具和异常处理

### Maven 依赖

```xml
<dependency>
    <groupId>com.boundesu</groupId>
    <artifactId>boundesu-words-xml</artifactId>
    <version>1.0.0</version>
</dependency>
```

### 外部依赖

- **Apache POI**: Microsoft Office 文档处理
- **Java DOM API**: XML 文档解析和操作
- **SLF4J**: 日志门面

## 🧪 测试

运行模块测试：

```bash
mvn test -pl boundesu-words-xml
```

运行特定测试：

```bash
mvn test -pl boundesu-words-xml -Dtest=XmlToDocxConverterTest
```

## 🔍 故障排除

### 常见问题

1. **XML 解析失败**
   ```
   BoundesuWordsException: XML 格式错误
   ```
   解决方案：检查 XML 格式是否正确，确保标签闭合

2. **编码问题**
   ```
   字符显示为乱码
   ```
   解决方案：确保 XML 文件编码为 UTF-8，或在 XML 声明中指定正确编码

3. **内存不足**
   ```
   OutOfMemoryError: Java heap space
   ```
   解决方案：对于大型 XML 文件，使用优化转换器或增加 JVM 内存

4. **元素不支持**
   ```
   某些 XML 元素没有转换
   ```
   解决方案：检查元素名称是否在支持列表中，或实现自定义处理器

### 调试技巧

```java
// 启用详细日志
System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "debug");

// 验证 XML 格式
try {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse(new InputSource(new StringReader(xmlContent)));
    System.out.println("XML 格式正确");
} catch (Exception e) {
    System.err.println("XML 格式错误: " + e.getMessage());
}

// 输出解析的 XML 结构
String formattedXml = XmlUtils.formatXml(xmlContent);
System.out.println("格式化的 XML:");
System.out.println(formattedXml);
```

### 性能优化建议

1. **使用优化转换器**：对于大型文档，优先使用 `OptimizedXmlToDocxConverter`
2. **流式处理**：对于超大 XML 文件，考虑分块处理
3. **缓存转换器**：重复使用转换器实例以减少初始化开销
4. **内存管理**：及时关闭文档对象释放内存

```java
// 性能优化示例
public class OptimizedProcessing {
    private static final OptimizedXmlToDocxConverter CONVERTER = 
        new OptimizedXmlToDocxConverter();
    
    public byte[] convertOptimized(String xmlContent) {
        // 使用静态转换器实例
        return CONVERTER.convertXmlToDocx(xmlContent);
    }
    
    public void processLargeFile(File largeXmlFile) throws IOException {
        // 分块处理大文件
        try (BufferedReader reader = Files.newBufferedReader(largeXmlFile.toPath())) {
            StringBuilder chunk = new StringBuilder();
            String line;
            int lineCount = 0;
            
            while ((line = reader.readLine()) != null) {
                chunk.append(line).append("\n");
                lineCount++;
                
                // 每1000行处理一次
                if (lineCount % 1000 == 0) {
                    processChunk(chunk.toString());
                    chunk.setLength(0);
                }
            }
            
            // 处理剩余内容
            if (chunk.length() > 0) {
                processChunk(chunk.toString());
            }
        }
    }
    
    private void processChunk(String xmlChunk) {
        // 处理 XML 块
    }
}
```

## 📄 许可证

本模块采用 MIT 许可证，详见项目根目录的 LICENSE 文件。

---

**Boundesu Words XML** - 高效的 XML 到 DOCX 转换引擎！ 🚀