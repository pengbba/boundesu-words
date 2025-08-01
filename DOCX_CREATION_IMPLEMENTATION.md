# 📚 Boundesu Words SDK - DOCX创建功能实现详细指南

## 🎯 功能概述

本文档详细介绍了Boundesu Words SDK中新增的DOCX文档创建功能。该功能提供了多种创建DOCX文档的方式，包括直接POI操作、HTML转换和XML转换，为用户提供了灵活且强大的文档生成解决方案。

## 🏗️ 核心架构

### 1. DocumentCreator接口

`DocumentCreator`是所有文档创建器的核心接口，定义了统一的文档创建规范：

```java
public interface DocumentCreator {
    /**
     * 创建DOCX文档
     * @param content 文档内容
     * @param outputPath 输出路径
     * @param options 创建选项
     * @return 创建结果
     */
    DocumentCreationResult createDocument(String content, Path outputPath, CreationOptions options);
    
    /**
     * 获取支持的内容类型
     * @return 支持的内容类型列表
     */
    List<ContentType> getSupportedContentTypes();
    
    /**
     * 验证内容格式
     * @param content 待验证内容
     * @return 验证结果
     */
    ValidationResult validateContent(String content);
}
```

### 2. DocumentCreatorFactory工厂类

工厂类负责根据不同的创建类型返回相应的创建器实例：

```java
public class DocumentCreatorFactory {
    
    /**
     * 创建文档创建器
     * @param type 创建类型
     * @return 对应的创建器实例
     */
    public static DocumentCreator createDocumentCreator(CreationType type) {
        switch (type) {
            case POI_DIRECT:
                return new PoiDirectDocxCreator();
            case HTML_TO_DOCX:
                return new HtmlToDocxCreator();
            case XML_TO_DOCX:
                return new XmlToDocxCreator();
            default:
                throw new UnsupportedOperationException("不支持的创建类型: " + type);
        }
    }
    
    /**
     * 获取所有支持的创建类型
     * @return 创建类型列表
     */
    public static List<CreationType> getSupportedTypes() {
        return Arrays.asList(CreationType.values());
    }
}
```

### 3. 创建类型枚举

```java
public enum CreationType {
    POI_DIRECT("直接POI操作", "使用Apache POI直接创建DOCX文档"),
    HTML_TO_DOCX("HTML转DOCX", "将HTML内容转换为DOCX文档"),
    XML_TO_DOCX("XML转DOCX", "将XML内容转换为DOCX文档");
    
    private final String displayName;
    private final String description;
    
    CreationType(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
    
    // getter方法...
}
```

## 🔧 具体实现

### 1. PoiDirectDocxCreator - 直接POI操作

这是最基础的DOCX创建方式，直接使用Apache POI API进行文档操作：

#### 核心特性
- **直接控制**: 完全控制文档的每个元素
- **高性能**: 无需中间转换，性能最优
- **灵活性**: 支持所有POI功能
- **复杂度**: 需要熟悉POI API

#### 实现示例
```java
public class PoiDirectDocxCreator implements DocumentCreator {
    
    @Override
    public DocumentCreationResult createDocument(String content, Path outputPath, CreationOptions options) {
        try (XWPFDocument document = new XWPFDocument()) {
            
            // 1. 设置文档属性
            setDocumentProperties(document, options);
            
            // 2. 创建段落和内容
            createParagraphs(document, content, options);
            
            // 3. 添加表格（如果需要）
            if (options.isIncludeTables()) {
                createTables(document, options);
            }
            
            // 4. 添加页眉页脚
            if (options.isIncludeHeaderFooter()) {
                createHeaderFooter(document, options);
            }
            
            // 5. 保存文档
            try (FileOutputStream out = new FileOutputStream(outputPath.toFile())) {
                document.write(out);
            }
            
            return DocumentCreationResult.success(outputPath);
            
        } catch (Exception e) {
            return DocumentCreationResult.failure("创建文档失败: " + e.getMessage(), e);
        }
    }
    
    private void setDocumentProperties(XWPFDocument document, CreationOptions options) {
        POIXMLProperties.CoreProperties coreProps = document.getProperties().getCoreProperties();
        coreProps.setTitle(options.getTitle());
        coreProps.setCreator(options.getAuthor());
        coreProps.setDescription(options.getDescription());
        coreProps.setCreated(Optional.of(new Date()));
    }
    
    private void createParagraphs(XWPFDocument document, String content, CreationOptions options) {
        String[] lines = content.split("\n");
        
        for (String line : lines) {
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            
            // 设置字体和样式
            run.setFontFamily(options.getFontFamily());
            run.setFontSize(options.getFontSize());
            run.setText(line);
            
            // 处理特殊格式
            if (line.startsWith("#")) {
                // 标题格式
                paragraph.setStyle("Heading1");
                run.setBold(true);
            } else if (line.startsWith("##")) {
                // 二级标题
                paragraph.setStyle("Heading2");
                run.setBold(true);
            }
        }
    }
    
    private void createTables(XWPFDocument document, CreationOptions options) {
        if (options.getTableData() != null && !options.getTableData().isEmpty()) {
            XWPFTable table = document.createTable();
            
            // 创建表头
            XWPFTableRow headerRow = table.getRow(0);
            List<String> headers = options.getTableHeaders();
            for (int i = 0; i < headers.size(); i++) {
                if (i == 0) {
                    headerRow.getCell(0).setText(headers.get(i));
                } else {
                    headerRow.addNewTableCell().setText(headers.get(i));
                }
            }
            
            // 添加数据行
            for (List<String> rowData : options.getTableData()) {
                XWPFTableRow dataRow = table.createRow();
                for (int i = 0; i < rowData.size(); i++) {
                    dataRow.getCell(i).setText(rowData.get(i));
                }
            }
        }
    }
}
```

### 2. HtmlToDocxCreator - HTML转DOCX

将HTML内容转换为DOCX文档，支持常见的HTML标签和样式：

#### 核心特性
- **HTML支持**: 支持常见HTML标签（p, h1-h6, table, ul, ol等）
- **样式转换**: 将CSS样式转换为Word格式
- **图片处理**: 支持内嵌图片和外部图片链接
- **表格支持**: 完整的HTML表格转换

#### 实现示例
```java
public class HtmlToDocxCreator implements DocumentCreator {
    
    private final HtmlParser htmlParser;
    private final StyleConverter styleConverter;
    
    public HtmlToDocxCreator() {
        this.htmlParser = new HtmlParser();
        this.styleConverter = new StyleConverter();
    }
    
    @Override
    public DocumentCreationResult createDocument(String htmlContent, Path outputPath, CreationOptions options) {
        try (XWPFDocument document = new XWPFDocument()) {
            
            // 1. 解析HTML内容
            Document htmlDoc = htmlParser.parse(htmlContent);
            
            // 2. 设置文档属性
            setDocumentProperties(document, options);
            
            // 3. 转换HTML元素
            convertHtmlElements(document, htmlDoc.body(), options);
            
            // 4. 保存文档
            try (FileOutputStream out = new FileOutputStream(outputPath.toFile())) {
                document.write(out);
            }
            
            return DocumentCreationResult.success(outputPath);
            
        } catch (Exception e) {
            return DocumentCreationResult.failure("HTML转DOCX失败: " + e.getMessage(), e);
        }
    }
    
    private void convertHtmlElements(XWPFDocument document, Element body, CreationOptions options) {
        for (Element element : body.children()) {
            switch (element.tagName().toLowerCase()) {
                case "h1":
                case "h2":
                case "h3":
                case "h4":
                case "h5":
                case "h6":
                    createHeading(document, element, options);
                    break;
                case "p":
                    createParagraph(document, element, options);
                    break;
                case "table":
                    createTable(document, element, options);
                    break;
                case "ul":
                case "ol":
                    createList(document, element, options);
                    break;
                case "img":
                    createImage(document, element, options);
                    break;
                default:
                    // 处理其他元素或作为普通文本
                    createParagraph(document, element, options);
                    break;
            }
        }
    }
    
    private void createHeading(XWPFDocument document, Element element, CreationOptions options) {
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        
        // 设置标题样式
        String level = element.tagName().substring(1); // h1 -> 1
        paragraph.setStyle("Heading" + level);
        
        run.setText(element.text());
        run.setBold(true);
        
        // 应用CSS样式
        String style = element.attr("style");
        if (!style.isEmpty()) {
            styleConverter.applyStyle(run, style);
        }
    }
    
    private void createTable(XWPFDocument document, Element tableElement, CreationOptions options) {
        XWPFTable table = document.createTable();
        
        // 处理表头
        Element thead = tableElement.selectFirst("thead");
        if (thead != null) {
            Elements headerRows = thead.select("tr");
            for (int i = 0; i < headerRows.size(); i++) {
                XWPFTableRow row = (i == 0) ? table.getRow(0) : table.createRow();
                Elements cells = headerRows.get(i).select("th, td");
                
                for (int j = 0; j < cells.size(); j++) {
                    XWPFTableCell cell = (j == 0) ? row.getCell(0) : row.addNewTableCell();
                    cell.setText(cells.get(j).text());
                    
                    // 设置表头样式
                    XWPFParagraph cellPara = cell.getParagraphs().get(0);
                    XWPFRun cellRun = cellPara.createRun();
                    cellRun.setBold(true);
                }
            }
        }
        
        // 处理表体
        Element tbody = tableElement.selectFirst("tbody");
        Elements dataRows = (tbody != null) ? tbody.select("tr") : tableElement.select("tr");
        
        for (Element row : dataRows) {
            XWPFTableRow tableRow = table.createRow();
            Elements cells = row.select("td");
            
            for (int j = 0; j < cells.size(); j++) {
                XWPFTableCell cell = tableRow.getCell(j);
                if (cell == null) {
                    cell = tableRow.addNewTableCell();
                }
                cell.setText(cells.get(j).text());
            }
        }
    }
}
```

### 3. XmlToDocxCreator - XML转DOCX

将结构化XML内容转换为DOCX文档：

#### 核心特性
- **XML解析**: 支持自定义XML结构
- **元素映射**: 将XML元素映射到Word文档元素
- **属性处理**: 支持XML属性到Word样式的转换
- **嵌套结构**: 处理复杂的嵌套XML结构

#### 实现示例
```java
public class XmlToDocxCreator implements DocumentCreator {
    
    private final XmlParser xmlParser;
    private final ElementMapper elementMapper;
    
    public XmlToDocxCreator() {
        this.xmlParser = new XmlParser();
        this.elementMapper = new ElementMapper();
    }
    
    @Override
    public DocumentCreationResult createDocument(String xmlContent, Path outputPath, CreationOptions options) {
        try (XWPFDocument document = new XWPFDocument()) {
            
            // 1. 解析XML内容
            Document xmlDoc = xmlParser.parse(xmlContent);
            
            // 2. 设置文档属性
            setDocumentProperties(document, options);
            
            // 3. 转换XML元素
            convertXmlElements(document, xmlDoc.getDocumentElement(), options);
            
            // 4. 保存文档
            try (FileOutputStream out = new FileOutputStream(outputPath.toFile())) {
                document.write(out);
            }
            
            return DocumentCreationResult.success(outputPath);
            
        } catch (Exception e) {
            return DocumentCreationResult.failure("XML转DOCX失败: " + e.getMessage(), e);
        }
    }
    
    private void convertXmlElements(XWPFDocument document, org.w3c.dom.Element root, CreationOptions options) {
        NodeList children = root.getChildNodes();
        
        for (int i = 0; i < children.getLength(); i++) {
            Node node = children.item(i);
            
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                processXmlElement(document, element, options);
            }
        }
    }
    
    private void processXmlElement(XWPFDocument document, org.w3c.dom.Element element, CreationOptions options) {
        String tagName = element.getTagName();
        
        switch (tagName) {
            case "title":
                createTitle(document, element, options);
                break;
            case "section":
                createSection(document, element, options);
                break;
            case "paragraph":
                createParagraphFromXml(document, element, options);
                break;
            case "table":
                createTableFromXml(document, element, options);
                break;
            case "list":
                createListFromXml(document, element, options);
                break;
            default:
                // 递归处理子元素
                convertXmlElements(document, element, options);
                break;
        }
    }
    
    private void createTitle(XWPFDocument document, org.w3c.dom.Element element, CreationOptions options) {
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        
        // 获取标题级别
        String level = element.getAttribute("level");
        if (level.isEmpty()) {
            level = "1";
        }
        
        paragraph.setStyle("Heading" + level);
        run.setText(element.getTextContent());
        run.setBold(true);
        
        // 应用XML属性样式
        applyXmlAttributes(run, element);
    }
    
    private void createTableFromXml(XWPFDocument document, org.w3c.dom.Element tableElement, CreationOptions options) {
        XWPFTable table = document.createTable();
        
        // 获取表格行
        NodeList rows = tableElement.getElementsByTagName("row");
        
        for (int i = 0; i < rows.getLength(); i++) {
            org.w3c.dom.Element rowElement = (org.w3c.dom.Element) rows.item(i);
            XWPFTableRow tableRow = (i == 0) ? table.getRow(0) : table.createRow();
            
            // 获取单元格
            NodeList cells = rowElement.getElementsByTagName("cell");
            
            for (int j = 0; j < cells.getLength(); j++) {
                org.w3c.dom.Element cellElement = (org.w3c.dom.Element) cells.item(j);
                XWPFTableCell tableCell = (j == 0) ? tableRow.getCell(0) : tableRow.addNewTableCell();
                
                tableCell.setText(cellElement.getTextContent());
                
                // 应用单元格样式
                String cellStyle = cellElement.getAttribute("style");
                if (!cellStyle.isEmpty()) {
                    applyCellStyle(tableCell, cellStyle);
                }
            }
        }
    }
}
```

## 🔧 创建选项配置

### CreationOptions类

```java
public class CreationOptions {
    // 基本属性
    private String title = "Untitled Document";
    private String author = "Boundesu Words SDK";
    private String description = "";
    
    // 字体设置
    private String fontFamily = "宋体";
    private int fontSize = 12;
    
    // 页面设置
    private PageSize pageSize = PageSize.A4;
    private PageOrientation orientation = PageOrientation.PORTRAIT;
    private Margins margins = new Margins(2.54, 2.54, 2.54, 2.54); // cm
    
    // 内容选项
    private boolean includeHeaderFooter = false;
    private boolean includeTables = true;
    private boolean includeImages = true;
    private boolean includeHyperlinks = true;
    
    // 表格数据
    private List<String> tableHeaders = new ArrayList<>();
    private List<List<String>> tableData = new ArrayList<>();
    
    // 样式选项
    private Map<String, Object> customStyles = new HashMap<>();
    
    // 构造器和getter/setter方法...
    
    public static CreationOptions defaultOptions() {
        return new CreationOptions();
    }
    
    public static CreationOptions withTitle(String title) {
        CreationOptions options = new CreationOptions();
        options.setTitle(title);
        return options;
    }
    
    public CreationOptions font(String family, int size) {
        this.fontFamily = family;
        this.fontSize = size;
        return this;
    }
    
    public CreationOptions page(PageSize size, PageOrientation orientation) {
        this.pageSize = size;
        this.orientation = orientation;
        return this;
    }
}
```

## 📊 使用示例

### 1. 基础使用示例

```java
public class BasicDocxCreationExample {
    
    public static void main(String[] args) {
        // 1. 创建基础文档
        createBasicDocument();
        
        // 2. 从HTML创建文档
        createFromHtml();
        
        // 3. 从XML创建文档
        createFromXml();
    }
    
    private static void createBasicDocument() {
        try {
            // 创建POI直接操作的创建器
            DocumentCreator creator = DocumentCreatorFactory.createDocumentCreator(CreationType.POI_DIRECT);
            
            // 准备内容
            String content = """
                # 文档标题
                这是一个示例文档。
                
                ## 章节1
                这是第一个章节的内容。
                
                ## 章节2
                这是第二个章节的内容。
                """;
            
            // 配置创建选项
            CreationOptions options = CreationOptions.withTitle("示例文档")
                .font("微软雅黑", 12)
                .page(PageSize.A4, PageOrientation.PORTRAIT);
            
            // 创建文档
            Path outputPath = Paths.get("output/basic-document.docx");
            DocumentCreationResult result = creator.createDocument(content, outputPath, options);
            
            if (result.isSuccess()) {
                System.out.println("文档创建成功: " + result.getOutputPath());
            } else {
                System.err.println("文档创建失败: " + result.getErrorMessage());
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void createFromHtml() {
        try {
            DocumentCreator creator = DocumentCreatorFactory.createDocumentCreator(CreationType.HTML_TO_DOCX);
            
            String htmlContent = """
                <html>
                <body>
                    <h1>HTML文档标题</h1>
                    <p>这是一个从HTML转换的文档。</p>
                    
                    <h2>表格示例</h2>
                    <table border="1">
                        <thead>
                            <tr>
                                <th>姓名</th>
                                <th>年龄</th>
                                <th>职业</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>张三</td>
                                <td>25</td>
                                <td>工程师</td>
                            </tr>
                            <tr>
                                <td>李四</td>
                                <td>30</td>
                                <td>设计师</td>
                            </tr>
                        </tbody>
                    </table>
                    
                    <h2>列表示例</h2>
                    <ul>
                        <li>项目1</li>
                        <li>项目2</li>
                        <li>项目3</li>
                    </ul>
                </body>
                </html>
                """;
            
            CreationOptions options = CreationOptions.withTitle("HTML转换文档");
            Path outputPath = Paths.get("output/html-to-docx.docx");
            
            DocumentCreationResult result = creator.createDocument(htmlContent, outputPath, options);
            
            if (result.isSuccess()) {
                System.out.println("HTML转DOCX成功: " + result.getOutputPath());
            } else {
                System.err.println("转换失败: " + result.getErrorMessage());
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void createFromXml() {
        try {
            DocumentCreator creator = DocumentCreatorFactory.createDocumentCreator(CreationType.XML_TO_DOCX);
            
            String xmlContent = """
                <?xml version="1.0" encoding="UTF-8"?>
                <document>
                    <title level="1">XML文档标题</title>
                    
                    <section>
                        <title level="2">第一章节</title>
                        <paragraph>这是第一章节的内容。</paragraph>
                        
                        <table>
                            <row>
                                <cell style="bold">产品</cell>
                                <cell style="bold">价格</cell>
                                <cell style="bold">库存</cell>
                            </row>
                            <row>
                                <cell>笔记本电脑</cell>
                                <cell>5999</cell>
                                <cell>50</cell>
                            </row>
                            <row>
                                <cell>台式机</cell>
                                <cell>3999</cell>
                                <cell>30</cell>
                            </row>
                        </table>
                    </section>
                    
                    <section>
                        <title level="2">第二章节</title>
                        <paragraph>这是第二章节的内容。</paragraph>
                        
                        <list type="ordered">
                            <item>第一项</item>
                            <item>第二项</item>
                            <item>第三项</item>
                        </list>
                    </section>
                </document>
                """;
            
            CreationOptions options = CreationOptions.withTitle("XML转换文档");
            Path outputPath = Paths.get("output/xml-to-docx.docx");
            
            DocumentCreationResult result = creator.createDocument(xmlContent, outputPath, options);
            
            if (result.isSuccess()) {
                System.out.println("XML转DOCX成功: " + result.getOutputPath());
            } else {
                System.err.println("转换失败: " + result.getErrorMessage());
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

### 2. 高级功能示例

```java
public class AdvancedDocxCreationExample {
    
    public static void main(String[] args) {
        // 1. 创建包含表格的文档
        createDocumentWithTables();
        
        // 2. 创建包含页眉页脚的文档
        createDocumentWithHeaderFooter();
        
        // 3. 批量创建文档
        batchCreateDocuments();
    }
    
    private static void createDocumentWithTables() {
        try {
            DocumentCreator creator = DocumentCreatorFactory.createDocumentCreator(CreationType.POI_DIRECT);
            
            // 准备表格数据
            List<String> headers = Arrays.asList("编号", "姓名", "部门", "薪资");
            List<List<String>> data = Arrays.asList(
                Arrays.asList("001", "张三", "技术部", "8000"),
                Arrays.asList("002", "李四", "市场部", "7500"),
                Arrays.asList("003", "王五", "人事部", "6500")
            );
            
            CreationOptions options = CreationOptions.withTitle("员工信息表")
                .font("微软雅黑", 11)
                .includeTables(true)
                .tableHeaders(headers)
                .tableData(data);
            
            String content = """
                # 员工信息管理系统
                
                ## 员工基本信息
                以下是公司员工的基本信息统计表：
                """;
            
            Path outputPath = Paths.get("output/employee-info.docx");
            DocumentCreationResult result = creator.createDocument(content, outputPath, options);
            
            if (result.isSuccess()) {
                System.out.println("包含表格的文档创建成功: " + result.getOutputPath());
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void createDocumentWithHeaderFooter() {
        try {
            DocumentCreator creator = DocumentCreatorFactory.createDocumentCreator(CreationType.POI_DIRECT);
            
            CreationOptions options = CreationOptions.withTitle("正式文档")
                .font("宋体", 12)
                .includeHeaderFooter(true)
                .headerText("Boundesu Words SDK - 官方文档")
                .footerText("第 {PAGE} 页，共 {NUMPAGES} 页");
            
            String content = """
                # 正式文档标题
                
                这是一个包含页眉页脚的正式文档。
                
                ## 第一章 概述
                这里是文档的概述内容...
                
                ## 第二章 详细说明
                这里是详细说明内容...
                
                ## 第三章 总结
                这里是总结内容...
                """;
            
            Path outputPath = Paths.get("output/formal-document.docx");
            DocumentCreationResult result = creator.createDocument(content, outputPath, options);
            
            if (result.isSuccess()) {
                System.out.println("包含页眉页脚的文档创建成功: " + result.getOutputPath());
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void batchCreateDocuments() {
        try {
            List<DocumentTemplate> templates = Arrays.asList(
                new DocumentTemplate("报告1", "这是第一份报告的内容", CreationType.POI_DIRECT),
                new DocumentTemplate("报告2", "<h1>HTML报告</h1><p>这是HTML格式的报告</p>", CreationType.HTML_TO_DOCX),
                new DocumentTemplate("报告3", "<?xml version='1.0'?><document><title>XML报告</title></document>", CreationType.XML_TO_DOCX)
            );
            
            for (int i = 0; i < templates.size(); i++) {
                DocumentTemplate template = templates.get(i);
                DocumentCreator creator = DocumentCreatorFactory.createDocumentCreator(template.getType());
                
                CreationOptions options = CreationOptions.withTitle(template.getTitle());
                Path outputPath = Paths.get("output/batch-" + (i + 1) + ".docx");
                
                DocumentCreationResult result = creator.createDocument(template.getContent(), outputPath, options);
                
                if (result.isSuccess()) {
                    System.out.println("批量创建文档 " + (i + 1) + " 成功: " + result.getOutputPath());
                } else {
                    System.err.println("批量创建文档 " + (i + 1) + " 失败: " + result.getErrorMessage());
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static class DocumentTemplate {
        private final String title;
        private final String content;
        private final CreationType type;
        
        public DocumentTemplate(String title, String content, CreationType type) {
            this.title = title;
            this.content = content;
            this.type = type;
        }
        
        // getter方法...
    }
}
```

## 🧪 测试验证

### 1. 单元测试

```java
@Test
public class DocumentCreatorTest {
    
    @Test
    public void testPoiDirectCreator() {
        DocumentCreator creator = DocumentCreatorFactory.createDocumentCreator(CreationType.POI_DIRECT);
        assertNotNull(creator);
        assertTrue(creator instanceof PoiDirectDocxCreator);
    }
    
    @Test
    public void testHtmlToDocxCreator() {
        DocumentCreator creator = DocumentCreatorFactory.createDocumentCreator(CreationType.HTML_TO_DOCX);
        assertNotNull(creator);
        assertTrue(creator instanceof HtmlToDocxCreator);
    }
    
    @Test
    public void testXmlToDocxCreator() {
        DocumentCreator creator = DocumentCreatorFactory.createDocumentCreator(CreationType.XML_TO_DOCX);
        assertNotNull(creator);
        assertTrue(creator instanceof XmlToDocxCreator);
    }
    
    @Test
    public void testDocumentCreation() throws IOException {
        DocumentCreator creator = DocumentCreatorFactory.createDocumentCreator(CreationType.POI_DIRECT);
        
        String content = "测试文档内容";
        Path outputPath = Files.createTempFile("test", ".docx");
        CreationOptions options = CreationOptions.defaultOptions();
        
        DocumentCreationResult result = creator.createDocument(content, outputPath, options);
        
        assertTrue(result.isSuccess());
        assertTrue(Files.exists(outputPath));
        assertTrue(Files.size(outputPath) > 0);
        
        // 清理
        Files.deleteIfExists(outputPath);
    }
    
    @Test
    public void testHtmlConversion() throws IOException {
        DocumentCreator creator = DocumentCreatorFactory.createDocumentCreator(CreationType.HTML_TO_DOCX);
        
        String htmlContent = "<h1>测试标题</h1><p>测试段落</p>";
        Path outputPath = Files.createTempFile("test-html", ".docx");
        CreationOptions options = CreationOptions.defaultOptions();
        
        DocumentCreationResult result = creator.createDocument(htmlContent, outputPath, options);
        
        assertTrue(result.isSuccess());
        assertTrue(Files.exists(outputPath));
        
        // 清理
        Files.deleteIfExists(outputPath);
    }
    
    @Test
    public void testXmlConversion() throws IOException {
        DocumentCreator creator = DocumentCreatorFactory.createDocumentCreator(CreationType.XML_TO_DOCX);
        
        String xmlContent = "<?xml version='1.0'?><document><title>测试</title><paragraph>内容</paragraph></document>";
        Path outputPath = Files.createTempFile("test-xml", ".docx");
        CreationOptions options = CreationOptions.defaultOptions();
        
        DocumentCreationResult result = creator.createDocument(xmlContent, outputPath, options);
        
        assertTrue(result.isSuccess());
        assertTrue(Files.exists(outputPath));
        
        // 清理
        Files.deleteIfExists(outputPath);
    }
}
```

### 2. 集成测试

```java
@Test
public class DocumentCreationIntegrationTest {
    
    private static final Path OUTPUT_DIR = Paths.get("test-output");
    
    @BeforeAll
    public static void setup() throws IOException {
        Files.createDirectories(OUTPUT_DIR);
    }
    
    @AfterAll
    public static void cleanup() throws IOException {
        // 清理测试文件
        Files.walk(OUTPUT_DIR)
            .sorted(Comparator.reverseOrder())
            .map(Path::toFile)
            .forEach(File::delete);
    }
    
    @Test
    public void testCompleteWorkflow() throws IOException {
        // 测试完整的文档创建工作流
        
        // 1. POI直接创建
        testPoiDirectWorkflow();
        
        // 2. HTML转换
        testHtmlConversionWorkflow();
        
        // 3. XML转换
        testXmlConversionWorkflow();
    }
    
    private void testPoiDirectWorkflow() throws IOException {
        DocumentCreator creator = DocumentCreatorFactory.createDocumentCreator(CreationType.POI_DIRECT);
        
        String content = """
            # 集成测试文档
            这是一个集成测试文档。
            
            ## 功能测试
            测试各种功能是否正常工作。
            """;
        
        CreationOptions options = CreationOptions.withTitle("集成测试")
            .font("微软雅黑", 12)
            .includeHeaderFooter(true);
        
        Path outputPath = OUTPUT_DIR.resolve("integration-poi.docx");
        DocumentCreationResult result = creator.createDocument(content, outputPath, options);
        
        assertTrue(result.isSuccess());
        assertTrue(Files.exists(outputPath));
        assertTrue(Files.size(outputPath) > 1000); // 确保文件有实际内容
    }
    
    private void testHtmlConversionWorkflow() throws IOException {
        DocumentCreator creator = DocumentCreatorFactory.createDocumentCreator(CreationType.HTML_TO_DOCX);
        
        String htmlContent = """
            <html>
            <body>
                <h1>HTML集成测试</h1>
                <p>这是HTML转换的集成测试。</p>
                <table border="1">
                    <tr><th>列1</th><th>列2</th></tr>
                    <tr><td>数据1</td><td>数据2</td></tr>
                </table>
            </body>
            </html>
            """;
        
        CreationOptions options = CreationOptions.withTitle("HTML集成测试");
        Path outputPath = OUTPUT_DIR.resolve("integration-html.docx");
        
        DocumentCreationResult result = creator.createDocument(htmlContent, outputPath, options);
        
        assertTrue(result.isSuccess());
        assertTrue(Files.exists(outputPath));
    }
    
    private void testXmlConversionWorkflow() throws IOException {
        DocumentCreator creator = DocumentCreatorFactory.createDocumentCreator(CreationType.XML_TO_DOCX);
        
        String xmlContent = """
            <?xml version="1.0" encoding="UTF-8"?>
            <document>
                <title level="1">XML集成测试</title>
                <section>
                    <paragraph>这是XML转换的集成测试。</paragraph>
                    <table>
                        <row><cell>列1</cell><cell>列2</cell></row>
                        <row><cell>数据1</cell><cell>数据2</cell></row>
                    </table>
                </section>
            </document>
            """;
        
        CreationOptions options = CreationOptions.withTitle("XML集成测试");
        Path outputPath = OUTPUT_DIR.resolve("integration-xml.docx");
        
        DocumentCreationResult result = creator.createDocument(xmlContent, outputPath, options);
        
        assertTrue(result.isSuccess());
        assertTrue(Files.exists(outputPath));
    }
}
```

## 📈 性能优化

### 1. 内存优化

```java
public class MemoryOptimizedCreator {
    
    // 使用对象池减少对象创建
    private final ObjectPool<XWPFDocument> documentPool;
    private final ObjectPool<StringBuilder> stringBuilderPool;
    
    public MemoryOptimizedCreator() {
        this.documentPool = new GenericObjectPool<>(new DocumentFactory());
        this.stringBuilderPool = new GenericObjectPool<>(new StringBuilderFactory());
    }
    
    public DocumentCreationResult createLargeDocument(String content, Path outputPath, CreationOptions options) {
        XWPFDocument document = null;
        StringBuilder buffer = null;
        
        try {
            document = documentPool.borrowObject();
            buffer = stringBuilderPool.borrowObject();
            
            // 分块处理大文档
            processInChunks(document, content, buffer, options);
            
            // 保存文档
            try (FileOutputStream out = new FileOutputStream(outputPath.toFile())) {
                document.write(out);
            }
            
            return DocumentCreationResult.success(outputPath);
            
        } catch (Exception e) {
            return DocumentCreationResult.failure("创建大文档失败: " + e.getMessage(), e);
        } finally {
            // 归还对象到池中
            if (document != null) {
                try {
                    documentPool.returnObject(document);
                } catch (Exception e) {
                    // 记录日志
                }
            }
            if (buffer != null) {
                try {
                    stringBuilderPool.returnObject(buffer);
                } catch (Exception e) {
                    // 记录日志
                }
            }
        }
    }
    
    private void processInChunks(XWPFDocument document, String content, StringBuilder buffer, CreationOptions options) {
        String[] lines = content.split("\n");
        int chunkSize = 100; // 每次处理100行
        
        for (int i = 0; i < lines.length; i += chunkSize) {
            int end = Math.min(i + chunkSize, lines.length);
            
            // 清空缓冲区
            buffer.setLength(0);
            
            // 构建当前块的内容
            for (int j = i; j < end; j++) {
                buffer.append(lines[j]).append("\n");
            }
            
            // 处理当前块
            processChunk(document, buffer.toString(), options);
            
            // 强制垃圾回收（在处理大文档时）
            if (i % (chunkSize * 10) == 0) {
                System.gc();
            }
        }
    }
}
```

### 2. 并发处理

```java
public class ConcurrentDocumentCreator {
    
    private final ExecutorService executorService;
    private final int threadPoolSize;
    
    public ConcurrentDocumentCreator(int threadPoolSize) {
        this.threadPoolSize = threadPoolSize;
        this.executorService = Executors.newFixedThreadPool(threadPoolSize);
    }
    
    public CompletableFuture<List<DocumentCreationResult>> createDocumentsConcurrently(
            List<DocumentCreationTask> tasks) {
        
        List<CompletableFuture<DocumentCreationResult>> futures = tasks.stream()
            .map(task -> CompletableFuture.supplyAsync(() -> {
                try {
                    DocumentCreator creator = DocumentCreatorFactory.createDocumentCreator(task.getType());
                    return creator.createDocument(task.getContent(), task.getOutputPath(), task.getOptions());
                } catch (Exception e) {
                    return DocumentCreationResult.failure("并发创建失败: " + e.getMessage(), e);
                }
            }, executorService))
            .collect(Collectors.toList());
        
        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
            .thenApply(v -> futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList()));
    }
    
    public void shutdown() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
```

## 🔍 故障排除

### 常见问题及解决方案

#### 1. 内存不足错误
```java
// 问题：处理大文档时出现OutOfMemoryError
// 解决方案：
CreationOptions options = CreationOptions.defaultOptions()
    .memoryOptimized(true)
    .chunkSize(1000)
    .enableGarbageCollection(true);
```

#### 2. 字符编码问题
```java
// 问题：中文字符显示乱码
// 解决方案：
CreationOptions options = CreationOptions.defaultOptions()
    .encoding("UTF-8")
    .fontFamily("微软雅黑"); // 使用支持中文的字体
```

#### 3. 文件锁定问题
```java
// 问题：文件被其他程序占用
// 解决方案：
public DocumentCreationResult createDocumentSafely(String content, Path outputPath, CreationOptions options) {
    // 检查文件是否被占用
    if (isFileLocked(outputPath)) {
        return DocumentCreationResult.failure("文件被占用: " + outputPath);
    }
    
    // 使用临时文件
    Path tempPath = outputPath.getParent().resolve(outputPath.getFileName() + ".tmp");
    
    try {
        DocumentCreationResult result = createDocument(content, tempPath, options);
        if (result.isSuccess()) {
            Files.move(tempPath, outputPath, StandardCopyOption.REPLACE_EXISTING);
            return DocumentCreationResult.success(outputPath);
        }
        return result;
    } finally {
        Files.deleteIfExists(tempPath);
    }
}
```

## 📞 技术支持

### 获取帮助
如果在使用DOCX创建功能时遇到问题，请通过以下方式获取帮助：

- 📧 **技术支持邮箱**: docx-support@boundesu.com
- 🐛 **问题报告**: [GitHub Issues](https://github.com/boundesu/words-sdk/issues)
- 💬 **技术讨论**: [GitHub Discussions](https://github.com/boundesu/words-sdk/discussions)
- 📖 **在线文档**: [官方文档](https://docs.boundesu.com/words-sdk)

### 常见问题FAQ

**Q: 支持哪些HTML标签？**
A: 目前支持h1-h6、p、table、ul、ol、li、img、a等常见标签。

**Q: XML格式有什么要求？**
A: XML必须是格式良好的，建议使用我们提供的XML Schema进行验证。

**Q: 如何处理大文档？**
A: 使用内存优化选项，启用分块处理和垃圾回收。

**Q: 支持哪些字体？**
A: 支持系统安装的所有字体，推荐使用微软雅黑、宋体等中文字体。

---

**文档版本**: v1.2.0  
**最后更新**: 2024年1月  
**适用版本**: Boundesu Words SDK 2.0+