# Boundesu Words All

> Boundesu Words SDK 统一入口模块

## 📖 模块简介

boundesu-words-all 是 Boundesu Words SDK 的统一入口模块，聚合了所有核心功能，为用户提供最简单、最直接的 API 接口。该模块整合了
HTML 转换、XML 转换、文档创建等所有功能，是使用 SDK 的推荐入口点。

## ✨ 主要功能

- 🎯 **统一 API**: 提供简洁统一的接口，无需了解内部模块结构
- 🔄 **全功能转换**: 支持 HTML、XML 到 DOCX 的完整转换功能
- 🚀 **一键操作**: 提供最简化的一键转换方法
- 📊 **高级功能**: 集成高级文档生成和性能监控
- 🛠️ **工具集成**: 整合所有实用工具和辅助功能
- 📝 **示例丰富**: 提供完整的使用示例和最佳实践
- 🎨 **灵活配置**: 支持各种自定义配置和选项
- 📈 **性能优化**: 内置性能监控和优化建议

## 🏗️ 核心组件

### BoundesuWordsAll

主要的统一 API 类，聚合所有转换功能：

```java
// 创建 SDK 实例
BoundesuWordsAll sdk = new BoundesuWordsAll();

// HTML 转换
XWPFDocument htmlDoc = sdk.convertHtmlToDocx(htmlContent);
XWPFDocument htmlFileDoc = sdk.convertHtmlToDocx(new File("input.html"));

// XML 转换
XWPFDocument xmlDoc = sdk.convertXmlToDocx(xmlContent);
XWPFDocument xmlFileDoc = sdk.convertXmlToDocx(new File("input.xml"));

// 自动识别格式转换
XWPFDocument autoDoc = sdk.convertToDocx("input.html"); // 自动识别为 HTML
XWPFDocument autoDoc2 = sdk.convertToDocx("input.xml"); // 自动识别为 XML

// 保存文档
sdk.

saveDocument(htmlDoc, "output.docx");
```

### BoundesuWordsSDK

高级 SDK 类，提供更多功能和配置选项：

```java
// 创建高级 SDK 实例
BoundesuWordsSDK sdk = new BoundesuWordsSDK();

// 配置 SDK
sdk.

setDefaultOutputDirectory("./output");
sdk.

setPerformanceMonitoringEnabled(true);
sdk.

setValidationEnabled(true);

// 批量转换
List<String> inputFiles = Arrays.asList("file1.html", "file2.xml", "file3.html");
List<ConversionResult> results = sdk.batchConvert(inputFiles);

// 高级文档生成
AdvancedDocumentConfig config = new AdvancedDocumentConfig();
config.

setGenerateToc(true);
config.

setIncludeCoverPage(true);

XWPFDocument advancedDoc = sdk.generateAdvancedDocument(content, config);

// 性能分析
PerformanceReport report = sdk.getPerformanceReport();
System.out.

println("总转换次数: "+report.getTotalConversions());
        System.out.

println("平均转换时间: "+report.getAverageConversionTime() +"ms");
```

### 示例应用类

#### BoundesuWordsExample

基础使用示例：

```java
public class BoundesuWordsExample {
    public static void main(String[] args) {
        // 运行所有示例
        BoundesuWordsExample.runAllExamples();

        // 运行特定示例
        BoundesuWordsExample.runHtmlExample();
        BoundesuWordsExample.runXmlExample();
        BoundesuWordsExample.runAdvancedExample();
    }
}
```

#### SDKExampleApp

完整的 SDK 演示应用：

```java
public class SDKExampleApp {
    public static void main(String[] args) {
        // 启动演示应用
        SDKExampleApp app = new SDKExampleApp();
        app.runInteractiveDemo();
    }
}
```

## 🚀 快速开始

### 最简单的使用方式

```java
import com.boundesu.words.all.BoundesuWordsAll;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.IOException;

public class QuickStart {
    public static void main(String[] args) throws IOException {
        // 1. 创建 SDK 实例
        BoundesuWordsAll sdk = new BoundesuWordsAll();

        // 2. HTML 转换
        String htmlContent = "<h1>Hello World</h1><p>这是我的第一个文档！</p>";
        XWPFDocument document = sdk.convertHtmlToDocx(htmlContent);

        // 3. 保存文档
        sdk.saveDocument(document, "my_first_document.docx");

        // 4. 关闭文档
        document.close();

        System.out.println("文档创建成功！");
    }
}
```

### 一键转换

```java
import com.boundesu.words.all.BoundesuWordsAll;

public class OneClickConversion {
    public static void main(String[] args) {
        BoundesuWordsAll sdk = new BoundesuWordsAll();

        try {
            // 一键转换 - 自动识别文件类型
            sdk.convertFile("input.html", "output.docx");
            sdk.convertFile("data.xml", "report.docx");

            // 批量一键转换
            String[] inputFiles = {"file1.html", "file2.xml", "file3.html"};
            String outputDir = "./converted/";
            sdk.batchConvertFiles(inputFiles, outputDir);

            System.out.println("所有文件转换完成！");

        } catch (Exception e) {
            System.err.println("转换失败: " + e.getMessage());
        }
    }
}
```

## 📚 详细使用示例

### HTML 转换示例

```java
import com.boundesu.words.all.BoundesuWordsAll;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.IOException;

public class HtmlConversionExample {
    public static void main(String[] args) throws IOException {
        BoundesuWordsAll sdk = new BoundesuWordsAll();

        // 示例 1: 简单 HTML 转换
        String simpleHtml = """
                <h1>项目报告</h1>
                <h2>概述</h2>
                <p>本项目已成功完成所有预定目标。</p>
                <ul>
                    <li>功能开发 ✓</li>
                    <li>测试验证 ✓</li>
                    <li>文档编写 ✓</li>
                </ul>
                """;

        XWPFDocument doc1 = sdk.convertHtmlToDocx(simpleHtml);
        sdk.saveDocument(doc1, "simple_report.docx");
        doc1.close();

        // 示例 2: 复杂 HTML 转换
        String complexHtml = """
                <!DOCTYPE html>
                <html>
                <head>
                    <title>技术文档</title>
                    <style>
                        .highlight { background-color: yellow; }
                        .important { color: red; font-weight: bold; }
                    </style>
                </head>
                <body>
                    <h1>API 文档</h1>
                
                    <h2>接口列表</h2>
                    <table border="1">
                        <tr>
                            <th>接口名称</th>
                            <th>方法</th>
                            <th>描述</th>
                        </tr>
                        <tr>
                            <td>/api/convert</td>
                            <td>POST</td>
                            <td class="important">文档转换接口</td>
                        </tr>
                        <tr>
                            <td>/api/status</td>
                            <td>GET</td>
                            <td>状态查询接口</td>
                        </tr>
                    </table>
                
                    <h2>使用说明</h2>
                    <p>请参考以下<span class="highlight">重要说明</span>：</p>
                    <ol>
                        <li>确保请求格式正确</li>
                        <li>检查认证信息</li>
                        <li>处理响应结果</li>
                    </ol>
                </body>
                </html>
                """;

        XWPFDocument doc2 = sdk.convertHtmlToDocx(complexHtml);
        sdk.saveDocument(doc2, "api_documentation.docx");
        doc2.close();

        // 示例 3: 从文件转换
        File htmlFile = new File("template.html");
        if (htmlFile.exists()) {
            XWPFDocument doc3 = sdk.convertHtmlToDocx(htmlFile);
            sdk.saveDocument(doc3, "from_file.docx");
            doc3.close();
        }

        System.out.println("HTML 转换示例完成！");
    }
}
```

### XML 转换示例

```java
import com.boundesu.words.all.BoundesuWordsAll;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.IOException;

public class XmlConversionExample {
    public static void main(String[] args) throws IOException {
        BoundesuWordsAll sdk = new BoundesuWordsAll();

        // 示例 1: 结构化数据转换
        String dataXml = """
                <?xml version="1.0" encoding="UTF-8"?>
                <report>
                    <metadata>
                        <title>销售报告</title>
                        <author>销售部</author>
                        <date>2024-01-15</date>
                    </metadata>
                
                    <summary>
                        <heading>执行摘要</heading>
                        <content>本季度销售业绩超出预期，同比增长25%。</content>
                    </summary>
                
                    <sections>
                        <section id="sales">
                            <heading level="1">销售数据</heading>
                            <paragraph>详细的销售数据分析如下：</paragraph>
                
                            <table>
                                <row>
                                    <cell>产品</cell>
                                    <cell>销量</cell>
                                    <cell>收入</cell>
                                </row>
                                <row>
                                    <cell>产品 A</cell>
                                    <cell>1000</cell>
                                    <cell>50万</cell>
                                </row>
                                <row>
                                    <cell>产品 B</cell>
                                    <cell>800</cell>
                                    <cell>40万</cell>
                                </row>
                            </table>
                        </section>
                
                        <section id="analysis">
                            <heading level="1">趋势分析</heading>
                            <paragraph>市场趋势分析显示：</paragraph>
                            <list type="unordered">
                                <item>移动端销售增长显著</item>
                                <item>新客户获取率提升</item>
                                <item>客户满意度持续改善</item>
                            </list>
                        </section>
                    </sections>
                
                    <conclusion>
                        <heading>结论</heading>
                        <content>建议继续加大市场投入，扩大产品线。</content>
                    </conclusion>
                </report>
                """;

        XWPFDocument doc1 = sdk.convertXmlToDocx(dataXml);
        sdk.saveDocument(doc1, "sales_report.docx");
        doc1.close();

        // 示例 2: 配置文件转换
        String configXml = """
                <?xml version="1.0" encoding="UTF-8"?>
                <configuration>
                    <title>系统配置文档</title>
                
                    <database>
                        <heading level="1">数据库配置</heading>
                        <parameter name="host">localhost</parameter>
                        <parameter name="port">3306</parameter>
                        <parameter name="database">myapp</parameter>
                        <parameter name="username">admin</parameter>
                    </database>
                
                    <server>
                        <heading level="1">服务器配置</heading>
                        <parameter name="port">8080</parameter>
                        <parameter name="max_connections">1000</parameter>
                        <parameter name="timeout">30</parameter>
                    </server>
                
                    <features>
                        <heading level="1">功能开关</heading>
                        <feature name="cache_enabled" value="true"/>
                        <feature name="debug_mode" value="false"/>
                        <feature name="logging_level" value="INFO"/>
                    </features>
                </configuration>
                """;

        XWPFDocument doc2 = sdk.convertXmlToDocx(configXml);
        sdk.saveDocument(doc2, "system_config.docx");
        doc2.close();

        System.out.println("XML 转换示例完成！");
    }
}
```

### 高级功能示例

```java
import com.boundesu.words.all.BoundesuWordsSDK;
import com.boundesu.words.all.BoundesuWordsSDK.*;

import java.util.*;

public class AdvancedFeaturesExample {
    public static void main(String[] args) {
        BoundesuWordsSDK sdk = new BoundesuWordsSDK();

        // 配置 SDK
        sdk.setDefaultOutputDirectory("./advanced_output");
        sdk.setPerformanceMonitoringEnabled(true);
        sdk.setValidationEnabled(true);

        try {
            // 示例 1: 批量转换
            List<String> inputFiles = Arrays.asList(
                    "report1.html",
                    "data.xml",
                    "summary.html"
            );

            List<ConversionResult> results = sdk.batchConvert(inputFiles);

            for (ConversionResult result : results) {
                if (result.isSuccess()) {
                    System.out.println("✓ " + result.getInputFile() + " -> " + result.getOutputFile());
                } else {
                    System.err.println("✗ " + result.getInputFile() + ": " + result.getError());
                }
            }

            // 示例 2: 高级文档生成
            AdvancedDocumentConfig config = new AdvancedDocumentConfig();
            config.setGenerateToc(true);
            config.setIncludeCoverPage(true);
            config.setIncludePageNumbers(true);
            config.setDocumentTitle("高级技术文档");
            config.setAuthor("技术团队");
            config.setCompany("我的公司");

            Map<String, String> sections = new HashMap<>();
            sections.put("概述", "这是一个高级文档生成的示例。");
            sections.put("技术架构", "详细的技术架构说明...");
            sections.put("API 文档", "完整的 API 接口文档...");
            sections.put("部署指南", "系统部署和配置指南...");

            XWPFDocument advancedDoc = sdk.generateAdvancedDocument(sections, config);
            sdk.saveDocument(advancedDoc, "advanced_technical_doc.docx");
            advancedDoc.close();

            // 示例 3: 性能监控
            PerformanceReport report = sdk.getPerformanceReport();
            System.out.println("\n=== 性能报告 ===");
            System.out.println("总转换次数: " + report.getTotalConversions());
            System.out.println("成功转换: " + report.getSuccessfulConversions());
            System.out.println("失败转换: " + report.getFailedConversions());
            System.out.println("平均转换时间: " + report.getAverageConversionTime() + "ms");
            System.out.println("最快转换时间: " + report.getFastestConversionTime() + "ms");
            System.out.println("最慢转换时间: " + report.getSlowestConversionTime() + "ms");

            // 示例 4: 文档验证
            ValidationResult validation = sdk.validateDocument("test.html");
            if (validation.isValid()) {
                System.out.println("文档验证通过");
            } else {
                System.out.println("文档验证失败: " + validation.getErrors());
            }

            // 示例 5: 自定义转换选项
            ConversionOptions options = new ConversionOptions();
            options.setPageMargins(720); // 0.5英寸边距
            options.setDefaultFontFamily("宋体");
            options.setDefaultFontSize(12);
            options.setIncludeMetadata(true);

            XWPFDocument customDoc = sdk.convertWithOptions("custom.html", options);
            sdk.saveDocument(customDoc, "custom_formatted.docx");
            customDoc.close();

        } catch (Exception e) {
            System.err.println("高级功能示例执行失败: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("高级功能示例完成！");
    }
}
```

### 实际应用场景示例

```java
import com.boundesu.words.all.BoundesuWordsAll;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RealWorldExample {

    // 场景 1: 自动化报告生成
    public static void generateDailyReport() {
        BoundesuWordsAll sdk = new BoundesuWordsAll();

        String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        String reportHtml = """
                <h1>日报 - %s</h1>
                <h2>今日完成工作</h2>
                <ul>
                    <li>完成用户注册功能开发</li>
                    <li>修复登录页面 Bug</li>
                    <li>编写 API 文档</li>
                </ul>
                
                <h2>明日计划</h2>
                <ol>
                    <li>实现密码重置功能</li>
                    <li>优化数据库查询性能</li>
                    <li>进行单元测试</li>
                </ol>
                
                <h2>遇到的问题</h2>
                <p>数据库连接偶尔超时，需要进一步调查。</p>
                """.formatted(today);

        try {
            XWPFDocument report = sdk.convertHtmlToDocx(reportHtml);
            sdk.saveDocument(report, "daily_report_" + today + ".docx");
            report.close();
            System.out.println("日报生成成功: daily_report_" + today + ".docx");
        } catch (Exception e) {
            System.err.println("日报生成失败: " + e.getMessage());
        }
    }

    // 场景 2: 批量文档处理
    public static void processBatchDocuments() {
        BoundesuWordsAll sdk = new BoundesuWordsAll();

        File inputDir = new File("./input_documents");
        File outputDir = new File("./converted_documents");

        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        File[] files = inputDir.listFiles((dir, name) ->
                name.endsWith(".html") || name.endsWith(".xml"));

        if (files != null) {
            for (File file : files) {
                try {
                    String baseName = file.getName().replaceAll("\\.(html|xml)$", "");
                    String outputPath = new File(outputDir, baseName + ".docx").getPath();

                    sdk.convertFile(file.getPath(), outputPath);
                    System.out.println("转换完成: " + file.getName() + " -> " + baseName + ".docx");

                } catch (Exception e) {
                    System.err.println("转换失败: " + file.getName() + " - " + e.getMessage());
                }
            }
        }
    }

    // 场景 3: 动态内容生成
    public static void generateDynamicDocument(Map<String, Object> data) {
        BoundesuWordsAll sdk = new BoundesuWordsAll();

        // 构建动态 HTML 内容
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<h1>").append(data.get("title")).append("</h1>");
        htmlBuilder.append("<p>生成时间: ").append(LocalDateTime.now()).append("</p>");

        // 添加数据表格
        if (data.containsKey("tableData")) {
            @SuppressWarnings("unchecked")
            List<Map<String, String>> tableData = (List<Map<String, String>>) data.get("tableData");

            htmlBuilder.append("<h2>数据表格</h2>");
            htmlBuilder.append("<table border='1'>");

            // 表头
            if (!tableData.isEmpty()) {
                htmlBuilder.append("<tr>");
                for (String key : tableData.get(0).keySet()) {
                    htmlBuilder.append("<th>").append(key).append("</th>");
                }
                htmlBuilder.append("</tr>");

                // 数据行
                for (Map<String, String> row : tableData) {
                    htmlBuilder.append("<tr>");
                    for (String value : row.values()) {
                        htmlBuilder.append("<td>").append(value).append("</td>");
                    }
                    htmlBuilder.append("</tr>");
                }
            }

            htmlBuilder.append("</table>");
        }

        try {
            XWPFDocument document = sdk.convertHtmlToDocx(htmlBuilder.toString());
            String filename = "dynamic_" + System.currentTimeMillis() + ".docx";
            sdk.saveDocument(document, filename);
            document.close();
            System.out.println("动态文档生成成功: " + filename);
        } catch (Exception e) {
            System.err.println("动态文档生成失败: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("=== 实际应用场景示例 ===");

        // 生成日报
        generateDailyReport();

        // 批量处理文档
        processBatchDocuments();

        // 生成动态文档
        Map<String, Object> sampleData = new HashMap<>();
        sampleData.put("title", "销售数据报告");

        List<Map<String, String>> tableData = Arrays.asList(
                Map.of("产品", "产品A", "销量", "100", "收入", "10000"),
                Map.of("产品", "产品B", "销量", "80", "收入", "8000"),
                Map.of("产品", "产品C", "销量", "120", "收入", "12000")
        );
        sampleData.put("tableData", tableData);

        generateDynamicDocument(sampleData);

        System.out.println("所有示例执行完成！");
    }
}
```

## ⚙️ 配置和选项

### SDK 配置

```java
// 基础配置
BoundesuWordsSDK sdk = new BoundesuWordsSDK();
sdk.

setDefaultOutputDirectory("./output");           // 默认输出目录
sdk.

setPerformanceMonitoringEnabled(true);          // 启用性能监控
sdk.

setValidationEnabled(true);                     // 启用文档验证
sdk.

setAutoCreateDirectories(true);                 // 自动创建目录

// 转换选项
ConversionOptions options = new ConversionOptions();
options.

setPageMargins(720);                        // 页边距 (twips)
options.

setDefaultFontFamily("宋体");               // 默认字体
options.

setDefaultFontSize(12);                     // 默认字号
options.

setIncludeMetadata(true);                   // 包含元数据
options.

setPreserveFormatting(true);                // 保持格式

// 高级文档配置
AdvancedDocumentConfig advConfig = new AdvancedDocumentConfig();
advConfig.

setGenerateToc(true);                     // 生成目录
advConfig.

setIncludeCoverPage(true);                // 包含封面
advConfig.

setIncludePageNumbers(true);              // 包含页码
advConfig.

setDocumentTitle("我的文档");             // 文档标题
advConfig.

setAuthor("作者姓名");                    // 作者
advConfig.

setCompany("公司名称");                   // 公司
```

### 性能优化配置

```java
// 性能优化设置
PerformanceConfig perfConfig = new PerformanceConfig();
perfConfig.

setEnableCaching(true);                  // 启用缓存
perfConfig.

setMaxCacheSize(100);                    // 最大缓存大小
perfConfig.

setThreadPoolSize(4);                    // 线程池大小
perfConfig.

setTimeoutSeconds(30);                   // 超时时间

sdk.

setPerformanceConfig(perfConfig);
```

## 📦 依赖关系

### 模块依赖

- **boundesu-words-core**: 核心转换引擎
- **boundesu-words-html**: HTML 转换功能
- **boundesu-words-xml**: XML 转换功能
- **boundesu-words-common**: 基础工具和异常处理

### Maven 依赖

```xml
<!-- 只需要添加这一个依赖，会自动引入所有相关模块 -->
<dependency>
    <groupId>com.boundesu</groupId>
    <artifactId>boundesu-words-all</artifactId>
    <version>1.0.0</version>
</dependency>
```

### 外部依赖

- **Apache POI**: Microsoft Office 文档处理
- **Jsoup**: HTML 解析和操作
- **SLF4J + Logback**: 日志处理
- **TestNG**: 单元测试框架

## 🧪 测试

运行所有测试：

```bash
mvn test -pl boundesu-words-all
```

运行特定测试类：

```bash
mvn test -pl boundesu-words-all -Dtest=BoundesuWordsSDKTest
```

运行示例应用：

```bash
# 运行基础示例
java -cp target/classes com.boundesu.words.all.example.BoundesuWordsExample

# 运行 SDK 示例应用
java -cp target/classes com.boundesu.words.all.example.SDKExampleApp
```

## 🔍 故障排除

### 常见问题

1. **模块依赖问题**
   ```
   ClassNotFoundException: com.boundesu.words.core.BoundesuWords
   ```
   解决方案：确保所有依赖模块都在 classpath 中

2. **文件路径问题**
   ```
   FileNotFoundException: 找不到输入文件
   ```
   解决方案：检查文件路径是否正确，使用绝对路径或确保相对路径正确

3. **内存不足**
   ```
   OutOfMemoryError: Java heap space
   ```
   解决方案：增加 JVM 内存 `-Xmx2g` 或使用批量处理

4. **权限问题**
   ```
   AccessDeniedException: 无法写入输出文件
   ```
   解决方案：检查输出目录的写入权限

### 调试技巧

```java
// 启用详细日志
System.setProperty("org.slf4j.simpleLogger.defaultLogLevel","debug");

// 检查 SDK 状态
BoundesuWordsSDK sdk = new BoundesuWordsSDK();
SDKStatus status = sdk.getStatus();
System.out.

println("SDK 版本: "+status.getVersion());
        System.out.

println("可用模块: "+status.getAvailableModules());
        System.out.

println("内存使用: "+status.getMemoryUsage());

// 验证环境
EnvironmentCheck check = sdk.checkEnvironment();
if(!check.

isValid()){
        System.err.

println("环境检查失败: "+check.getIssues());
        }
```

### 性能优化建议

1. **批量处理**: 对于多个文件，使用批量转换方法
2. **缓存利用**: 启用 SDK 缓存以提高重复转换的性能
3. **内存管理**: 及时关闭文档对象释放内存
4. **异步处理**: 对于大型文件，考虑使用异步转换

```java
// 性能优化示例
public class PerformanceOptimization {
    private static final BoundesuWordsSDK SDK = new BoundesuWordsSDK();

    static {
        // 配置性能优化
        PerformanceConfig config = new PerformanceConfig();
        config.setEnableCaching(true);
        config.setMaxCacheSize(50);
        SDK.setPerformanceConfig(config);
    }

    public void optimizedBatchConversion(List<String> files) {
        // 使用批量转换
        List<ConversionResult> results = SDK.batchConvert(files);

        // 处理结果
        results.parallelStream().forEach(result -> {
            if (result.isSuccess()) {
                System.out.println("✓ " + result.getInputFile());
            } else {
                System.err.println("✗ " + result.getInputFile() + ": " + result.getError());
            }
        });
    }
}
```

## 📄 许可证

本模块采用 MIT 许可证，详见项目根目录的 LICENSE 文件。

## 🤝 贡献

欢迎贡献代码！请参考项目根目录的贡献指南。

## 📞 支持

如有问题或建议，请通过以下方式联系：

- 📧 邮箱: support@boundesu.com
- 🐛 问题反馈: [GitHub Issues](https://github.com/boundesu/boundesu-words/issues)
- 📖 文档: [在线文档](https://docs.boundesu.com/words)

---

**Boundesu Words All** - 您的文档转换一站式解决方案！ 🚀

> 让文档转换变得简单、高效、可靠！