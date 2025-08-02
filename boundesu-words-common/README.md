# Boundesu Words Common

> Boundesu Words SDK 公共工具模块

## 📖 模块简介

boundesu-words-common 是 Boundesu Words SDK 的基础模块，提供了项目中所有模块共用的工具类、常量定义、异常处理等基础设施。

## ✨ 主要功能

- 🔧 **工具类**: 字符串处理、文件操作、文档验证等实用工具
- 📋 **常量定义**: SDK版本信息、文件格式、编码等常量
- 🛡️ **异常处理**: 统一的异常体系和错误处理机制
- 📊 **性能监控**: 文档处理操作的性能监控工具

## 🏗️ 核心组件

### 常量类

#### BoundesuConstants

定义了SDK的基本信息和文件格式常量：

```java
public class BoundesuConstants {
    // SDK版本信息
    public static final String SDK_VERSION = "1.0.0";
    public static final String SDK_NAME = "Boundesu Words SDK";

    // 文件格式常量
    public static final String FORMAT_HTML = "html";
    public static final String FORMAT_XML = "xml";
    public static final String FORMAT_DOCX = "docx";

    // 文件扩展名常量
    public static final String EXT_HTML = ".html";
    public static final String EXT_XML = ".xml";
    public static final String EXT_DOCX = ".docx";
}
```

#### DocxConstants

定义了DOCX文档相关的常量：

```java
public class DocxConstants {
    // 默认样式设置
    public static final String DEFAULT_FONT_FAMILY = "宋体";
    public static final int DEFAULT_FONT_SIZE = 12;

    // 标题级别
    public static final int HEADING_LEVEL_1 = 1;
    public static final int MAX_HEADING_LEVEL = 6;

    // 文档属性默认值
    public static final String DEFAULT_AUTHOR = "Boundesu Words SDK";
}
```

### 工具类

#### StringUtils

字符串处理工具类：

```java
// 检查字符串是否为空或空白
boolean isEmpty = StringUtils.isBlank(text);

// 检查字符串是否不为空
boolean isNotEmpty = StringUtils.isNotBlank(text);
```

#### FileUtils

文件操作工具类：

```java
// 读取文件内容
String content = FileUtils.readFileToString("path/to/file.txt");

// 写入文件
FileUtils.

writeStringToFile("path/to/output.txt",content);

// 检查文件是否存在
boolean exists = FileUtils.exists("path/to/file.txt");

// 创建目录
FileUtils.

createDirectories("path/to/directory");
```

#### DocumentValidator

文档验证工具类：

```java
// 验证文件路径
DocumentValidator.validateFilePath(outputPath);

// 验证标题级别
DocumentValidator.

validateHeadingLevel(level);

// 验证表格大小
DocumentValidator.

validateTableSize(rows, cols);
```

#### PerformanceMonitor

性能监控工具类：

```java
// 创建性能监控上下文
PerformanceMonitor.OperationContext context = PerformanceMonitor.startOperation("Document Creation");
try{

// 执行操作
performDocumentOperation();
}finally{
        context.

end();
    System.out.

println("操作耗时: "+context.getDuration() +"ms");
        }

// 监控操作性能
Result result = PerformanceMonitor.monitor("Operation Name", () -> {
    return performOperation();
});
```

### 异常类

#### BoundesuWordsException

基础异常类：

```java
// 创建异常
throw new BoundesuWordsException("操作失败");

// 带错误码的异常
throw new

BoundesuWordsException("INVALID_INPUT","输入参数无效");

// 带原因的异常
throw new

BoundesuWordsException("FILE_ERROR","文件操作失败",cause);
```

#### 专用异常类

- **DocumentExportException**: 文档导出异常
- **DocumentValidationException**: 文档验证异常

## 🚀 使用示例

### 基本工具使用

```java
import com.boundesu.words.common.util.*;
import com.boundesu.words.common.constants.*;
import com.boundesu.words.common.exception.*;

public class CommonExample {
    public static void main(String[] args) {
        try {
            // 文件操作
            String content = FileUtils.readFileToString("input.txt");

            // 字符串验证
            if (StringUtils.isNotBlank(content)) {
                // 处理内容
                processContent(content);
            }

            // 文档验证
            DocumentValidator.validateFilePath("output.docx");

            // 性能监控
            PerformanceMonitor.OperationContext context =
                    PerformanceMonitor.startOperation("File Processing");
            try {
                // 执行文件处理
                FileUtils.writeStringToFile("output.txt", processedContent);
            } finally {
                context.end();
            }

        } catch (BoundesuWordsException e) {
            System.err.println("错误: " + e.getErrorCode() + " - " + e.getMessage());
        }
    }
}
```

### 文档统计

```java
import com.boundesu.words.common.util.BoundesuDocumentUtils;

// 获取当前时间戳
String timestamp = BoundesuDocumentUtils.getCurrentTimestamp();

        // 清理HTML标签
        String cleanText = BoundesuDocumentUtils.removeHtmlTags(htmlContent);

        // 截取字符串
        String truncated = BoundesuDocumentUtils.truncate(longText, 100);
```

## 📦 依赖关系

本模块是其他所有模块的基础依赖，不依赖任何其他业务模块。

### Maven 依赖

```xml

<dependency>
    <groupId>com.boundesu</groupId>
    <artifactId>boundesu-words-common</artifactId>
    <version>1.0.0</version>
</dependency>
```

## 🔧 配置说明

### 编码设置

默认使用UTF-8编码，可通过常量进行配置：

```java
// 使用默认编码
String content = FileUtils.readFileToString(filePath);

// 指定编码
String content = FileUtils.readFileToString(filePath, BoundesuConstants.ENCODING_GBK);
```

### 性能监控配置

性能监控默认启用，可以通过以下方式自定义：

```java
// 设置操作名称
PerformanceMonitor.OperationContext context =
        PerformanceMonitor.startOperation("Custom Operation");
```

## 🧪 测试

运行模块测试：

```bash
mvn test -pl boundesu-words-common
```

## 📄 许可证

本模块采用 MIT 许可证，详见项目根目录的 LICENSE 文件。

---

**Boundesu Words Common** - 为整个SDK提供坚实的基础支撑！ 🚀