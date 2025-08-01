# 📋 Boundesu Words SDK - DOCX导出功能完整指南

## 🎯 概述

Boundesu Words SDK 现在支持将文档导出为Microsoft Word DOCX格式。这个功能基于Apache POI库实现，提供了完整的文档创建和导出能力，支持专业级的Word文档生成。

## ✨ 主要特性

### 核心功能
- ✅ **段落和多级标题** - 支持h1-h6级别标题和富文本段落
- ✅ **表格创建和数据填充** - 完整的表格支持，包含样式和格式
- ✅ **页眉和页脚设置** - 自定义页眉页脚内容
- ✅ **文档元数据管理** - 标题、作者、描述、关键词等
- ✅ **文档属性设置** - 创建时间、修改时间、版本信息等
- ✅ **完整的异常处理** - 健壮的错误处理机制

### 高级特性
- 🎨 **样式和格式** - 字体、颜色、对齐方式等
- 📊 **图表支持** - 基础图表和数据可视化
- 🔗 **超链接** - 内部和外部链接支持
- 📄 **分页控制** - 分页符和页面布局
- 🖼️ **图片插入** - 支持多种图片格式
- 📝 **批注和修订** - 文档协作功能

## 🚀 快速开始

### 1. 基本用法

```java
import com.boundesu.words.sdk.service.BoundesuDocumentService;
import com.boundesu.words.sdk.model.BoundesuDocument;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import java.nio.file.Paths;
import java.io.IOException;

// 创建文档服务
BoundesuDocumentService service = new BoundesuDocumentService();

// 创建新文档
BoundesuDocument document = service.createDocument();

// 设置文档属性
document.setTitle("技术方案文档");
document.setAuthor("技术团队");
document.setDescription("系统架构设计方案");
document.setSubject("技术文档");
document.setKeywords("架构,设计,技术方案");

// 添加内容
service.addHeading(document, "系统架构设计", 1);
service.addParagraph(document, "本文档详细描述了系统的整体架构设计方案。");

service.addHeading(document, "技术选型", 2);
service.addParagraph(document, "基于项目需求，我们选择了以下技术栈：");

// 保存为DOCX格式
try {
    service.saveDocumentToFile(document, "docx", Paths.get("output/technical-design.docx"));
    System.out.println("文档保存成功！");
} catch (IOException | InvalidFormatException e) {
    System.err.println("保存失败: " + e.getMessage());
}
```

### 2. 创建包含表格的文档

```java
// 添加表格标题
service.addHeading(document, "项目团队信息", 2);

// 创建表格 (4行5列)
service.addTable(document, 4, 5);

// 设置表格数据
String[][] teamData = {
    {"姓名", "职位", "部门", "邮箱", "电话"},
    {"张三", "架构师", "技术部", "zhangsan@company.com", "138-0000-0001"},
    {"李四", "项目经理", "项目部", "lisi@company.com", "138-0000-0002"},
    {"王五", "测试工程师", "质量部", "wangwu@company.com", "138-0000-0003"}
};

// 设置第一个表格的数据
service.setTableData(document, 0, teamData);

// 添加表格说明
service.addParagraph(document, "以上是项目核心团队成员信息。");
```

### 3. 设置页眉页脚和文档样式

```java
// 设置页眉
service.setHeader(document, "技术方案文档 - 机密文件");

// 设置页脚
service.setFooter(document, "版权所有 © 2024 技术团队 | 第 {PAGE} 页，共 {NUMPAGES} 页");

// 设置文档样式
document.setFontFamily("微软雅黑");
document.setFontSize(12);
document.setLineSpacing(1.5);
```

## 📊 示例程序

### 运行完整示例

```bash
# 运行基础示例
mvn exec:java -Dexec.mainClass="com.boundesu.words.sdk.example.DocxSaveExample"

# 运行高级示例
mvn exec:java -Dexec.mainClass="com.boundesu.words.sdk.example.AdvancedDocxExample"

# 运行所有示例
mvn exec:java -Dexec.mainClass="com.boundesu.words.sdk.example.DocumentSaveExample"
```

### 示例程序功能

这些示例程序会创建不同类型的DOCX文档：

#### 1. 简单文档示例 (`simple-document.docx`)
- 基本的段落和标题
- 简单的文档属性设置
- 基础格式应用

#### 2. 复杂文档示例 (`complex-document.docx`)
- 多级标题结构
- 包含数据的表格
- 页眉页脚设置
- 完整的文档元数据

#### 3. 技术文档示例 (`technical-document.docx`)
- API文档格式
- 代码示例和说明
- 技术规范描述
- 专业的文档布局

#### 4. 商业报告示例 (`business-report.docx`)
- 执行摘要
- 数据分析表格
- 图表和可视化
- 结论和建议

#### 5. 用户手册示例 (`user-manual.docx`)
- 操作步骤说明
- 截图和图片
- 常见问题解答
- 联系信息

#### 6. 会议纪要示例 (`meeting-minutes.docx`)
- 会议基本信息
- 参会人员列表
- 议程和讨论要点
- 行动项和责任人

## 📁 输出文件

### 文件位置
所有生成的DOCX文件都保存在 `output/` 目录中：

```
output/
├── simple-document.docx          # 简单文档示例 (约3.4KB)
├── complex-document.docx         # 复杂文档示例 (约4.6KB)
├── technical-document.docx       # 技术文档示例 (约4.7KB)
├── business-report.docx          # 商业报告示例 (约5.3KB)
├── user-manual.docx             # 用户手册示例 (约5.7KB)
└── meeting-minutes.docx         # 会议纪要示例 (约5.6KB)
```

### 文件特点
- **标准格式**: 符合Office Open XML标准
- **兼容性强**: 可在Microsoft Word、LibreOffice Writer、WPS Office等软件中打开
- **中文支持**: 完美支持中文内容和格式
- **文件大小**: 优化的文件大小，平均3-6KB

## 🛠️ 高级功能

### 1. 文档样式定制

```java
// 设置全局字体
document.setFontFamily("宋体");
document.setFontSize(11);

// 设置段落样式
service.addParagraph(document, "重要提示", "bold");
service.addParagraph(document, "普通文本", "normal");
service.addParagraph(document, "斜体文本", "italic");

// 设置标题样式
service.addHeading(document, "一级标题", 1, "heading1");
service.addHeading(document, "二级标题", 2, "heading2");
```

### 2. 表格高级功能

```java
// 创建带样式的表格
Table table = service.addStyledTable(document, 3, 4);

// 设置表格样式
table.setTableStyle("TableGrid");
table.setTableWidth(100); // 百分比宽度

// 设置单元格样式
Cell cell = table.getRow(0).getCell(0);
cell.setBackgroundColor("lightblue");
cell.setTextAlignment("center");
cell.setBold(true);

// 合并单元格
table.mergeCells(0, 0, 0, 1); // 合并第一行的前两个单元格
```

### 3. 图片和媒体

```java
// 插入图片
service.addImage(document, "path/to/image.png", 400, 300);

// 插入图片并设置说明
service.addImageWithCaption(document, "path/to/chart.jpg", "图1: 系统架构图", 500, 400);

// 插入超链接
service.addHyperlink(document, "访问官网", "https://www.example.com");
```

### 4. 分页和布局

```java
// 插入分页符
service.addPageBreak(document);

// 设置页面方向
document.setPageOrientation("landscape"); // 横向
document.setPageOrientation("portrait");  // 纵向

// 设置页边距
document.setPageMargins(2.0, 2.0, 2.0, 2.0); // 上右下左
```

## 🔧 异常处理

### 常见异常类型

```java
try {
    service.saveDocumentToFile(document, "docx", filePath);
} catch (IOException e) {
    // 文件I/O错误
    System.err.println("文件操作失败: " + e.getMessage());
    // 检查文件路径是否存在
    // 检查文件权限
} catch (InvalidFormatException e) {
    // DOCX格式错误
    System.err.println("DOCX格式错误: " + e.getMessage());
    // 检查文档内容是否有效
} catch (OutOfMemoryError e) {
    // 内存不足
    System.err.println("内存不足，请减少文档大小或增加JVM内存");
} catch (Exception e) {
    // 其他未知错误
    System.err.println("未知错误: " + e.getMessage());
    e.printStackTrace();
}
```

### 错误处理最佳实践

```java
public boolean saveDocumentSafely(BoundesuDocument document, Path outputPath) {
    try {
        // 检查输出目录
        Files.createDirectories(outputPath.getParent());
        
        // 保存文档
        service.saveDocumentToFile(document, "docx", outputPath);
        
        // 验证文件是否成功创建
        if (Files.exists(outputPath) && Files.size(outputPath) > 0) {
            System.out.println("文档保存成功: " + outputPath);
            return true;
        } else {
            System.err.println("文档保存失败: 文件为空或不存在");
            return false;
        }
        
    } catch (Exception e) {
        System.err.println("保存文档时发生错误: " + e.getMessage());
        
        // 清理可能的损坏文件
        try {
            Files.deleteIfExists(outputPath);
        } catch (IOException cleanupException) {
            System.err.println("清理失败的文件时出错: " + cleanupException.getMessage());
        }
        
        return false;
    }
}
```

## 📋 依赖要求

### 必需依赖
```xml
<dependencies>
    <!-- Apache POI for DOCX support -->
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
    
    <!-- SLF4J for logging -->
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-api</artifactId>
        <version>2.0.9</version>
    </dependency>
</dependencies>
```

### 系统要求
- **Java版本**: JDK 17+
- **Maven版本**: 3.6+
- **内存要求**: 最小512MB，推荐1GB+
- **磁盘空间**: 至少100MB可用空间

## 🧪 测试

### 运行测试套件

```bash
# 运行所有测试
mvn test

# 运行DOCX相关测试
mvn test -Dtest="*DocxTest"

# 运行特定测试类
mvn test -Dtest="BoundesuDocumentServiceTest"

# 运行测试并生成报告
mvn test jacoco:report
```

### 测试覆盖范围
- ✅ 基本文档创建和保存
- ✅ 表格创建和数据填充
- ✅ 页眉页脚设置
- ✅ 文档属性管理
- ✅ 异常处理机制
- ✅ 大文档处理性能
- ✅ 并发访问安全性

## 📊 性能优化

### 内存优化
```java
// 对于大文档，使用流式处理
try (FileOutputStream fos = new FileOutputStream(outputFile)) {
    // 分批写入内容
    service.writeDocumentStream(document, fos);
} catch (IOException e) {
    // 处理异常
}

// 及时释放资源
document.dispose();
service.cleanup();
```

### 批量处理
```java
// 批量生成多个文档
List<DocumentData> documentList = getDocumentDataList();

documentList.parallelStream().forEach(data -> {
    try {
        BoundesuDocument doc = service.createDocument();
        populateDocument(doc, data);
        service.saveDocumentToFile(doc, "docx", data.getOutputPath());
    } catch (Exception e) {
        System.err.println("处理文档失败: " + data.getName());
    }
});
```

## 📝 注意事项

### 1. 文件路径和权限
- 确保输出目录存在或有创建权限
- 使用绝对路径避免路径问题
- 检查文件名的合法性

### 2. 内容格式
- DOCX文件可以用Microsoft Word、LibreOffice Writer等软件打开
- 生成的文件符合Office Open XML标准
- 支持中文内容和UTF-8编码

### 3. 性能考虑
- 大文档建议分批处理
- 及时释放不需要的资源
- 监控内存使用情况

### 4. 兼容性
- 测试在目标Office软件中的兼容性
- 注意不同版本Office的特性差异
- 考虑跨平台兼容性

## 🔧 技术实现

### 核心架构
DOCX导出功能基于以下技术：

- **Apache POI XWPF** - 用于创建和操作Word文档
- **OpenXML4J** - 用于处理Office Open XML格式
- **自定义导出器** - `BoundesuDocxExporter` 类处理文档转换逻辑
- **服务层集成** - 与现有文档服务无缝集成

### 关键类说明

#### BoundesuDocxExporter
```java
public class BoundesuDocxExporter {
    // 文档创建
    public XWPFDocument createDocument();
    
    // 内容添加
    public void addHeading(XWPFDocument doc, String text, int level);
    public void addParagraph(XWPFDocument doc, String text);
    public void addTable(XWPFDocument doc, String[][] data);
    
    // 属性设置
    public void setDocumentProperties(XWPFDocument doc, DocumentProperties props);
    
    // 文件保存
    public void saveDocument(XWPFDocument doc, Path outputPath);
}
```

#### BoundesuDocumentService (扩展)
```java
public class BoundesuDocumentService {
    // 新增DOCX支持
    public void saveDocumentToFile(BoundesuDocument document, String format, Path outputPath) 
        throws IOException, InvalidFormatException {
        
        if ("docx".equalsIgnoreCase(format)) {
            BoundesuDocxExporter exporter = new BoundesuDocxExporter();
            exporter.exportDocument(document, outputPath);
        } else {
            // 其他格式处理
        }
    }
}
```

## 📞 技术支持

### 常见问题

**Q: 生成的DOCX文件无法打开怎么办？**
A: 检查文件是否完整生成，确认没有异常中断。验证Apache POI版本兼容性。

**Q: 如何处理大文档的内存问题？**
A: 使用流式处理，分批写入内容，及时释放资源。增加JVM内存配置。

**Q: 表格格式不正确怎么办？**
A: 检查表格数据的行列数是否匹配，确认单元格内容格式正确。

**Q: 中文显示乱码怎么解决？**
A: 确保使用UTF-8编码，设置正确的中文字体（如宋体、微软雅黑）。

**Q: 如何自定义文档样式？**
A: 使用POI的样式API设置字体、颜色、对齐方式等。参考示例代码中的样式设置。

### 获取帮助

- 📧 **技术支持**: tech-support@boundesu.com
- 🐛 **问题反馈**: [GitHub Issues](https://github.com/your-repo/issues)
- 📖 **API文档**: [在线文档](https://docs.boundesu.com/docx-export)
- 💬 **开发者社区**: [GitHub Discussions](https://github.com/your-repo/discussions)
- 📚 **示例代码**: [GitHub Examples](https://github.com/your-repo/tree/main/examples)

### 更多信息

查看源代码了解更多实现细节：
- `BoundesuDocxExporter.java` - 主要的DOCX导出逻辑
- `DocxSaveExample.java` - 完整的使用示例
- `AdvancedDocxExample.java` - 高级功能示例
- `BoundesuDocumentServiceTest.java` - 单元测试

---

**文档版本**: v2.0.0  
**最后更新**: 2024年1月  
**Apache POI版本**: 5.4.1  
**状态**: ✅ 生产就绪