# Boundesu Words HTML

> HTML 到 DOCX 转换模块

## 📖 模块简介

boundesu-words-html 是 Boundesu Words SDK 的 HTML 转换模块，专门负责将 HTML 内容转换为 Microsoft Word DOCX 格式。该模块提供了强大的 HTML 解析和转换功能，支持丰富的 HTML 标签和样式。

## ✨ 主要功能

- 🔄 **HTML 转 DOCX**: 高质量的 HTML 到 Word 文档转换
- 🎨 **样式保持**: 保持 HTML 的格式和样式
- 📊 **表格支持**: 完整的 HTML 表格转换
- 🖼️ **图片处理**: 支持内嵌和外链图片
- 📝 **文本格式**: 支持粗体、斜体、下划线等格式
- 🔗 **链接处理**: 保持超链接功能
- 📋 **列表支持**: 有序和无序列表转换
- 🎯 **智能解析**: 自动识别和处理复杂的 HTML 结构

## 🏗️ 核心组件

### HtmlToDocxConverter
主要的 HTML 转换器类：

```java
// 基础转换
HtmlToDocxConverter converter = new HtmlToDocxConverter();

// 从字符串转换
XWPFDocument doc1 = converter.convertHtmlToDocx(htmlContent);

// 从输入流转换
InputStream htmlStream = new FileInputStream("input.html");
XWPFDocument doc2 = converter.convertHtmlToDocx(htmlStream);

// 带页边距设置的转换
XWPFDocument doc3 = converter.convertHtmlToDocx(htmlContent, 720); // 0.5英寸边距
```

### HtmlContentParser
HTML 内容解析器，负责解析 HTML 结构：

```java
HtmlContentParser parser = new HtmlContentParser();

// 解析 HTML 内容
Document htmlDoc = Jsoup.parse(htmlContent);
List<HtmlContentParser.ContentItem> items = parser.parseContent(htmlDoc);

// 遍历解析结果
for (HtmlContentParser.ContentItem item : items) {
    System.out.println("类型: " + item.getType());
    System.out.println("级别: " + item.getLevel());
    System.out.println("内容: " + item.getContent());
}
```

### HtmlUtils
HTML 工具类，提供 HTML 内容清理功能：

```java
// 清理 HTML 内容
String cleanedHtml = HtmlUtils.cleanHtmlContent(rawHtml);

// 移除不安全的标签和属性
String safeHtml = HtmlUtils.sanitizeHtml(userInputHtml);
```

## 🚀 使用示例

### 基础 HTML 转换

```java
import com.boundesu.words.html.HtmlToDocxConverter;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import java.io.FileOutputStream;
import java.io.IOException;

public class BasicHtmlExample {
    public static void main(String[] args) throws IOException {
        // 创建转换器
        HtmlToDocxConverter converter = new HtmlToDocxConverter();
        
        // HTML 内容
        String htmlContent = """
            <h1>文档标题</h1>
            <h2>第一章</h2>
            <p>这是一个<strong>重要</strong>的段落，包含<em>斜体</em>文本。</p>
            <ul>
                <li>列表项 1</li>
                <li>列表项 2</li>
                <li>列表项 3</li>
            </ul>
            <h2>第二章</h2>
            <p>这里有一个链接：<a href="https://example.com">示例网站</a></p>
            """;
        
        // 转换为 DOCX
        XWPFDocument document = converter.convertHtmlToDocx(htmlContent);
        
        // 保存文档
        try (FileOutputStream out = new FileOutputStream("output.docx")) {
            document.write(out);
        }
        
        document.close();
        System.out.println("HTML 转换完成！");
    }
}
```

### 从文件转换

```java
import com.boundesu.words.html.HtmlToDocxConverter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileHtmlExample {
    public static void main(String[] args) throws IOException {
        HtmlToDocxConverter converter = new HtmlToDocxConverter();
        
        // 从文件读取 HTML
        try (FileInputStream htmlInput = new FileInputStream("input.html");
             FileOutputStream docxOutput = new FileOutputStream("output.docx")) {
            
            // 转换
            XWPFDocument document = converter.convertHtmlToDocx(htmlInput);
            
            // 保存
            document.write(docxOutput);
            document.close();
        }
        
        System.out.println("文件转换完成！");
    }
}
```

### 带页边距的转换

```java
import com.boundesu.words.html.HtmlToDocxConverter;

public class MarginExample {
    public static void main(String[] args) throws IOException {
        HtmlToDocxConverter converter = new HtmlToDocxConverter();
        
        String htmlContent = "<h1>标题</h1><p>内容</p>";
        
        // 设置页边距（单位：twips，1英寸 = 1440 twips）
        int marginTwips = 720; // 0.5英寸
        
        XWPFDocument document = converter.convertHtmlToDocx(htmlContent, marginTwips);
        
        // 保存文档
        try (FileOutputStream out = new FileOutputStream("margin_document.docx")) {
            document.write(out);
        }
        
        document.close();
    }
}
```

### 复杂 HTML 转换

```java
import com.boundesu.words.html.HtmlToDocxConverter;

public class ComplexHtmlExample {
    public static void main(String[] args) throws IOException {
        HtmlToDocxConverter converter = new HtmlToDocxConverter();
        
        // 复杂的 HTML 内容
        String complexHtml = """
            <!DOCTYPE html>
            <html>
            <head>
                <title>复杂文档</title>
                <style>
                    .highlight { background-color: yellow; }
                    .important { color: red; font-weight: bold; }
                </style>
            </head>
            <body>
                <h1>技术文档</h1>
                
                <h2>概述</h2>
                <p>这是一个<span class="important">重要</span>的技术文档。</p>
                
                <h2>功能列表</h2>
                <ol>
                    <li>HTML 解析</li>
                    <li>样式转换</li>
                    <li>文档生成</li>
                </ol>
                
                <h2>数据表格</h2>
                <table border="1">
                    <thead>
                        <tr>
                            <th>功能</th>
                            <th>状态</th>
                            <th>优先级</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>HTML 转换</td>
                            <td class="highlight">完成</td>
                            <td>高</td>
                        </tr>
                        <tr>
                            <td>XML 转换</td>
                            <td>进行中</td>
                            <td>中</td>
                        </tr>
                    </tbody>
                </table>
                
                <h2>代码示例</h2>
                <pre><code>
public class Example {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
                </code></pre>
                
                <h2>参考链接</h2>
                <p>更多信息请访问：<a href="https://example.com">官方文档</a></p>
            </body>
            </html>
            """;
        
        // 转换复杂 HTML
        XWPFDocument document = converter.convertHtmlToDocx(complexHtml);
        
        // 保存文档
        try (FileOutputStream out = new FileOutputStream("complex_document.docx")) {
            document.write(out);
        }
        
        document.close();
        System.out.println("复杂 HTML 转换完成！");
    }
}
```

### 使用内容解析器

```java
import com.boundesu.words.html.HtmlContentParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ParserExample {
    public static void main(String[] args) {
        HtmlContentParser parser = new HtmlContentParser();
        
        String htmlContent = """
            <h1>主标题</h1>
            <h2>子标题</h2>
            <p>段落内容</p>
            <h3>三级标题</h3>
            <p>更多内容</p>
            """;
        
        // 解析 HTML
        Document doc = Jsoup.parse(htmlContent);
        List<HtmlContentParser.ContentItem> items = parser.parseContent(doc);
        
        // 输出解析结果
        for (HtmlContentParser.ContentItem item : items) {
            System.out.printf("[%s] 级别%d: %s%n", 
                item.getType(), item.getLevel(), item.getContent());
        }
    }
}
```

## 🎯 支持的 HTML 标签

### 文本格式

| 标签 | 描述 | 转换效果 |
|------|------|----------|
| `<h1>` - `<h6>` | 标题 | Word 标题样式 |
| `<p>` | 段落 | 普通段落 |
| `<strong>`, `<b>` | 粗体 | 粗体文本 |
| `<em>`, `<i>` | 斜体 | 斜体文本 |
| `<u>` | 下划线 | 下划线文本 |
| `<br>` | 换行 | 换行符 |
| `<hr>` | 水平线 | 分隔线 |

### 列表

| 标签 | 描述 | 转换效果 |
|------|------|----------|
| `<ul>` | 无序列表 | 项目符号列表 |
| `<ol>` | 有序列表 | 编号列表 |
| `<li>` | 列表项 | 列表项目 |

### 表格

| 标签 | 描述 | 转换效果 |
|------|------|----------|
| `<table>` | 表格 | Word 表格 |
| `<thead>` | 表头 | 表格标题行 |
| `<tbody>` | 表体 | 表格内容 |
| `<tr>` | 表格行 | 表格行 |
| `<th>` | 表头单元格 | 粗体单元格 |
| `<td>` | 表格单元格 | 普通单元格 |

### 链接和媒体

| 标签 | 描述 | 转换效果 |
|------|------|----------|
| `<a>` | 超链接 | Word 超链接 |
| `<img>` | 图片 | 嵌入图片 |

### 代码

| 标签 | 描述 | 转换效果 |
|------|------|----------|
| `<code>` | 内联代码 | 等宽字体 |
| `<pre>` | 预格式化文本 | 保持格式的文本块 |

### 容器

| 标签 | 描述 | 转换效果 |
|------|------|----------|
| `<div>` | 块级容器 | 段落分组 |
| `<span>` | 内联容器 | 文本分组 |

## ⚙️ 配置选项

### 页边距设置

```java
// 页边距单位：twips (1英寸 = 1440 twips)
int margin_0_5_inch = 720;   // 0.5英寸
int margin_1_inch = 1440;    // 1英寸
int margin_2_cm = 1134;      // 2厘米 (约)

XWPFDocument doc = converter.convertHtmlToDocx(html, margin_1_inch);
```

### HTML 清理选项

```java
// 使用 HtmlUtils 清理 HTML
String cleanHtml = HtmlUtils.cleanHtmlContent(rawHtml);

// 自定义清理规则
String customCleanHtml = HtmlUtils.cleanHtmlContent(rawHtml, true); // 移除脚本
```

## 🔧 高级功能

### 自定义样式映射

```java
// 扩展转换器以支持自定义样式
public class CustomHtmlConverter extends HtmlToDocxConverter {
    
    @Override
    protected void applyCustomStyles(XWPFParagraph paragraph, Element element) {
        // 自定义样式逻辑
        if (element.hasClass("highlight")) {
            // 应用高亮样式
            XWPFRun run = paragraph.createRun();
            run.getCTR().addNewRPr().addNewHighlight().setVal(STHighlightColor.YELLOW);
        }
    }
}
```

### 图片处理

```java
// 处理图片转换
public void handleImages(Document htmlDoc, XWPFDocument docxDoc) {
    Elements images = htmlDoc.select("img");
    for (Element img : images) {
        String src = img.attr("src");
        String alt = img.attr("alt");
        
        // 处理图片插入逻辑
        if (src.startsWith("http")) {
            // 处理网络图片
            downloadAndInsertImage(src, docxDoc);
        } else {
            // 处理本地图片
            insertLocalImage(src, docxDoc);
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
    <artifactId>boundesu-words-html</artifactId>
    <version>1.0.0</version>
</dependency>
```

### 外部依赖

- **Apache POI**: Microsoft Office 文档处理
- **Jsoup**: HTML 解析和操作
- **SLF4J**: 日志门面

## 🧪 测试

运行模块测试：

```bash
mvn test -pl boundesu-words-html
```

运行特定测试：

```bash
mvn test -pl boundesu-words-html -Dtest=HtmlToDocxConverterTest
```

## 🔍 故障排除

### 常见问题

1. **HTML 解析失败**
   ```
   BoundesuWordsException: HTML 解析错误
   ```
   解决方案：检查 HTML 格式是否正确，使用 HtmlUtils 清理内容

2. **图片无法显示**
   ```
   图片在 Word 中显示为占位符
   ```
   解决方案：确保图片路径正确，图片文件存在且可访问

3. **样式丢失**
   ```
   转换后的文档样式与原 HTML 不符
   ```
   解决方案：检查 CSS 样式是否受支持，考虑使用内联样式

4. **表格格式问题**
   ```
   表格边框或对齐方式不正确
   ```
   解决方案：使用标准的 HTML 表格标签和属性

### 调试技巧

```java
// 启用详细日志
System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "debug");

// 输出解析的 HTML 结构
Document doc = Jsoup.parse(htmlContent);
System.out.println("解析后的 HTML:");
System.out.println(doc.html());

// 检查支持的标签
Elements supportedTags = doc.select("h1,h2,h3,h4,h5,h6,p,strong,em,ul,ol,li,table,tr,td,th");
System.out.println("找到 " + supportedTags.size() + " 个支持的标签");
```

### 性能优化

```java
// 对于大型 HTML 文档，考虑分块处理
public void convertLargeHtml(String largeHtml) {
    // 分割 HTML 内容
    List<String> chunks = splitHtmlIntoChunks(largeHtml, 1000); // 每块1000字符
    
    XWPFDocument document = new XWPFDocument();
    HtmlToDocxConverter converter = new HtmlToDocxConverter();
    
    for (String chunk : chunks) {
        // 逐块转换并合并
        XWPFDocument chunkDoc = converter.convertHtmlToDocx(chunk);
        mergeDocuments(document, chunkDoc);
    }
}
```

## 📄 许可证

本模块采用 MIT 许可证，详见项目根目录的 LICENSE 文件。

---

**Boundesu Words HTML** - 强大的 HTML 到 DOCX 转换引擎！ 🚀