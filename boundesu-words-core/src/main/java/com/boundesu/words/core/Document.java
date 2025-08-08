package com.boundesu.words.core;

import com.boundesu.words.common.exception.BoundesuWordsException;
import com.boundesu.words.common.util.StringUtils;
import com.boundesu.words.core.config.DocumentPageConfig;
import com.boundesu.words.core.config.HtmlLoadConfig;
import com.boundesu.words.core.config.XmlLoadConfig;
import com.boundesu.words.core.options.HtmlLoadOptions;
import org.apache.poi.xwpf.usermodel.*;
import org.jsoup.nodes.Element;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * 文档主类
 *
 * @author Boundesu Team
 * @version 1.0.0
 */
public class Document {

    private static final Logger log = LoggerFactory.getLogger(Document.class);

    private XWPFDocument document;
    private String filePath;
    private BuiltInDocumentProperties builtInDocumentProperties;
    private CustomDocumentProperties customDocumentProperties;
    private SectionCollection sections;
    private CompatibilityOptions compatibilityOptions;

    // 配置对象
    private DocumentPageConfig pageConfig;
    private HtmlLoadConfig htmlConfig;
    private XmlLoadConfig xmlConfig;

    /**
     * 内置文档属性类
     * 模拟Aspose Words BuiltInDocumentProperties类
     */
    public static class BuiltInDocumentProperties {
        private XWPFDocument document;

        public BuiltInDocumentProperties(XWPFDocument document) {
            this.document = document;
        }

        public String getTitle() {
            return document.getProperties().getCoreProperties().getTitle();
        }

        public void setTitle(String title) {
            document.getProperties().getCoreProperties().setTitle(title);
        }

        public String getAuthor() {
            return document.getProperties().getCoreProperties().getCreator();
        }

        public void setAuthor(String author) {
            document.getProperties().getCoreProperties().setCreator(author);
        }

        public String getSubject() {
            return document.getProperties().getCoreProperties().getSubject();
        }

        public void setSubject(String subject) {
            document.getProperties().getCoreProperties().setSubjectProperty(subject);
        }

        public String getKeywords() {
            return document.getProperties().getCoreProperties().getKeywords();
        }

        public void setKeywords(String keywords) {
            document.getProperties().getCoreProperties().setKeywords(keywords);
        }

        public String getComments() {
            return document.getProperties().getCoreProperties().getDescription();
        }

        public void setComments(String comments) {
            document.getProperties().getCoreProperties().setDescription(comments);
        }

        public String getCategory() {
            return document.getProperties().getCoreProperties().getCategory();
        }

        public void setCategory(String category) {
            document.getProperties().getCoreProperties().setCategory(category);
        }

        public Date getCreatedTime() {
            return document.getProperties().getCoreProperties().getCreated();
        }

        public void setCreatedTime(Date createdTime) {
            document.getProperties().getCoreProperties().setCreated(Optional.of(createdTime));
        }

        public Date getLastSavedTime() {
            return document.getProperties().getCoreProperties().getModified();
        }

        public void setLastSavedTime(Date lastSavedTime) {
            document.getProperties().getCoreProperties().setModified(Optional.of(lastSavedTime));
        }
    }

    /**
     * 自定义文档属性类
     * 模拟Aspose Words CustomDocumentProperties类
     */
    public static class CustomDocumentProperties {
        private XWPFDocument document;
        private Map<String, Object> customProperties;

        public CustomDocumentProperties(XWPFDocument document) {
            this.document = document;
            this.customProperties = new HashMap<>();
        }

        public void add(String name, Object value) {
            customProperties.put(name, value);
        }

        public Object get(String name) {
            return customProperties.get(name);
        }

        public boolean contains(String name) {
            return customProperties.containsKey(name);
        }

        public void remove(String name) {
            customProperties.remove(name);
        }

        public Set<String> getNames() {
            return customProperties.keySet();
        }

        public int getCount() {
            return customProperties.size();
        }
    }

    /**
     * 节集合类
     * 模拟Aspose Words SectionCollection类
     */
    public static class SectionCollection {
        private XWPFDocument document;
        private List<Section> sections;

        public SectionCollection(XWPFDocument document) {
            this.document = document;
            this.sections = new ArrayList<>();
            // 初始化时至少有一个节
            if (sections.isEmpty()) {
                sections.add(new Section(document));
            }
        }

        public Section get(int index) {
            if (index < 0 || index >= sections.size()) {
                throw new IndexOutOfBoundsException("节索引超出范围: " + index);
            }
            return sections.get(index);
        }

        public int getCount() {
            return sections.size();
        }

        public Section add(Section section) {
            sections.add(section);
            return section;
        }

        public void removeAt(int index) {
            if (index < 0 || index >= sections.size()) {
                throw new IndexOutOfBoundsException("节索引超出范围: " + index);
            }
            sections.remove(index);
        }
    }

    /**
     * 节类
     * 模拟Aspose Words Section类
     */
    public static class Section {
        private XWPFDocument document;
        private PageSetup pageSetup;

        public Section(XWPFDocument document) {
            this.document = document;
            this.pageSetup = new PageSetup();
        }

        public PageSetup getPageSetup() {
            return pageSetup;
        }
    }

    /**
     * 页面设置类
     * 模拟Aspose Words PageSetup类
     */
    public static class PageSetup {
        private double pageWidth = 595.0; // A4宽度（磅）
        private double pageHeight = 842.0; // A4高度（磅）
        private double leftMargin = 72.0; // 1英寸
        private double rightMargin = 72.0;
        private double topMargin = 72.0;
        private double bottomMargin = 72.0;

        public double getLeftMargin() {
            return leftMargin;
        }

        public void setLeftMargin(double leftMargin) {
            this.leftMargin = leftMargin;
        }

        public double getRightMargin() {
            return rightMargin;
        }

        public void setRightMargin(double rightMargin) {
            this.rightMargin = rightMargin;
        }

        public double getTopMargin() {
            return topMargin;
        }

        public void setTopMargin(double topMargin) {
            this.topMargin = topMargin;
        }

        public double getBottomMargin() {
            return bottomMargin;
        }

        public void setBottomMargin(double bottomMargin) {
            this.bottomMargin = bottomMargin;
        }

        public double getPageWidth() {
            return pageWidth;
        }

        public void setPageWidth(double pageWidth) {
            this.pageWidth = pageWidth;
        }

        public double getPageHeight() {
            return pageHeight;
        }

        public void setPageHeight(double pageHeight) {
            this.pageHeight = pageHeight;
        }
    }

    /**
     * 兼容性选项类
     * 模拟Aspose Words CompatibilityOptions类
     */
    public static class CompatibilityOptions {
        private com.boundesu.words.core.options.LoadOptions.MsWordVersion optimizedVersion;

        public void optimizeFor(com.boundesu.words.core.options.LoadOptions.MsWordVersion version) {
            this.optimizedVersion = version;
            log.debug("优化兼容性设置为: {}", version);
        }

        public com.boundesu.words.core.options.LoadOptions.MsWordVersion getOptimizedVersion() {
            return optimizedVersion;
        }
    }

    /**
     * 默认构造函数 - 创建空白文档
     */
    public Document() throws BoundesuWordsException {
        try {
            this.document = new XWPFDocument();
            this.builtInDocumentProperties = new BuiltInDocumentProperties(this.document);
            this.customDocumentProperties = new CustomDocumentProperties(this.document);
            this.sections = new SectionCollection(this.document);
            this.compatibilityOptions = new CompatibilityOptions();

            // 初始化配置对象
            this.pageConfig = new DocumentPageConfig();
            this.htmlConfig = HtmlLoadConfig.createDefault();
            this.xmlConfig = XmlLoadConfig.createDefault();

            log.debug("创建新的空白文档成功");
        } catch (Exception e) {
            log.error("创建文档失败: {}", e.getMessage());
            throw new BoundesuWordsException("文档创建失败: " + e.getMessage(), e);
        }
    }

    /**
     * 从文件路径加载文档
     */
    public Document(String filePath) throws BoundesuWordsException {
        if (StringUtils.isEmpty(filePath)) {
            throw new BoundesuWordsException("文件路径不能为空");
        }

        this.filePath = filePath;

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                throw new BoundesuWordsException("文件不存在: " + filePath);
            }

            try (FileInputStream fis = new FileInputStream(file)) {
                this.document = new XWPFDocument(fis);
            }

            this.builtInDocumentProperties = new BuiltInDocumentProperties(this.document);
            this.customDocumentProperties = new CustomDocumentProperties(this.document);
            this.sections = new SectionCollection(this.document);
            this.compatibilityOptions = new CompatibilityOptions();

            // 初始化配置对象
            this.pageConfig = new DocumentPageConfig();
            this.htmlConfig = HtmlLoadConfig.createDefault();
            this.xmlConfig = XmlLoadConfig.createDefault();

            log.debug("从文件加载文档成功: {}", filePath);
        } catch (Exception e) {
            log.error("加载文档失败: {}", e.getMessage());
            throw new BoundesuWordsException("文档加载失败: " + e.getMessage(), e);
        }
    }

    /**
     * 从输入流创建Document对象
     *
     * @param stream 输入流
     * @throws BoundesuWordsException 如果加载失败
     */
    public Document(InputStream stream) throws BoundesuWordsException {
        try {
            this.document = new XWPFDocument(stream);
            this.builtInDocumentProperties = new BuiltInDocumentProperties(this.document);
            this.customDocumentProperties = new CustomDocumentProperties(this.document);
            this.sections = new SectionCollection(this.document);
            this.compatibilityOptions = new CompatibilityOptions();

            // 初始化配置对象
            this.pageConfig = new DocumentPageConfig();
            this.htmlConfig = HtmlLoadConfig.createDefault();
            this.xmlConfig = XmlLoadConfig.createDefault();

            log.debug("从输入流加载文档成功");
        } catch (IOException e) {
            throw new BoundesuWordsException("加载文档失败: " + e.getMessage(), e);
        }
    }

    /**
     * 从输入流创建Document对象，支持加载选项
     *
     * @param stream      输入流
     * @param loadOptions 加载选项
     * @throws BoundesuWordsException 如果加载失败
     */
    public Document(InputStream stream, com.boundesu.words.core.options.LoadOptions loadOptions) throws BoundesuWordsException {
        try {
            this.document = new XWPFDocument(stream);
            this.builtInDocumentProperties = new BuiltInDocumentProperties(this.document);
            this.customDocumentProperties = new CustomDocumentProperties(this.document);
            this.sections = new SectionCollection(this.document);
            this.compatibilityOptions = new CompatibilityOptions();

            // 应用加载选项
            if (loadOptions != null) {
                applyLoadOptions(loadOptions);
            }

            log.debug("使用加载选项从输入流加载文档成功");
        } catch (IOException e) {
            throw new BoundesuWordsException("加载文档失败: " + e.getMessage(), e);
        }
    }

    /**
     * 从文件路径和加载选项加载文档
     * 支持各种类型的加载选项
     */
    public Document(String fileName, com.boundesu.words.core.options.LoadOptions loadOptions) throws BoundesuWordsException {
        if (StringUtils.isEmpty(fileName)) {
            throw new BoundesuWordsException("文件路径不能为空");
        }

        this.filePath = fileName;

        File file = new File(fileName);
        if (!file.exists()) {
            throw new BoundesuWordsException("文件不存在: " + fileName);
        }

        try (FileInputStream fis = new FileInputStream(file)) {
            this.document = new XWPFDocument(fis);

            this.builtInDocumentProperties = new BuiltInDocumentProperties(this.document);
            this.customDocumentProperties = new CustomDocumentProperties(this.document);
            this.sections = new SectionCollection(this.document);
            this.compatibilityOptions = new CompatibilityOptions();

            // 应用加载选项
            if (loadOptions != null) {
                applyLoadOptions(loadOptions);
            }

            log.debug("使用加载选项从文件加载文档成功: {}", fileName);
        } catch (IOException e) {
            log.error("加载文档失败: {}", e.getMessage());
            throw new BoundesuWordsException("文档加载失败: " + e.getMessage(), e);
        }
    }

    /**
     * 从HTML文件路径和HTML加载选项加载文档
     * 这是专门为HTML文件加载提供的便捷构造函数
     */
    public Document(String htmlFilePath, HtmlLoadOptions htmlLoadOptions) throws BoundesuWordsException {
        if (StringUtils.isEmpty(htmlFilePath)) {
            throw new BoundesuWordsException("HTML文件路径不能为空");
        }

        this.filePath = htmlFilePath;

        try {
            // 创建一个新的空白文档
            this.document = new XWPFDocument();

            // 读取HTML文件内容
            String htmlContent = readHtmlFile(htmlFilePath, htmlLoadOptions);

            // 将HTML内容转换为简单的文本并添加到文档中
            convertHtmlToDocument(htmlContent, htmlLoadOptions);

            this.builtInDocumentProperties = new BuiltInDocumentProperties(this.document);
            this.customDocumentProperties = new CustomDocumentProperties(this.document);
            this.sections = new SectionCollection(this.document);
            this.compatibilityOptions = new CompatibilityOptions();

            // 应用HTML加载选项
            if (htmlLoadOptions != null) {
                applyLoadOptions(htmlLoadOptions);
            }

            log.debug("使用HTML加载选项从文件加载文档成功: {}", htmlFilePath);
        } catch (Exception e) {
            log.error("加载HTML文档失败: {}", e.getMessage());
            throw new BoundesuWordsException("HTML文档加载失败: " + e.getMessage(), e);
        }
    }

    /**
     * 应用加载选项
     *
     * @param loadOptions 加载选项
     */
    private void applyLoadOptions(com.boundesu.words.core.options.LoadOptions loadOptions) {
        if (loadOptions == null) {
            return;
        }

        // 应用编码设置
        if (loadOptions.getEncoding() != null) {
            // 编码设置已在文档加载时应用
            log.debug("应用编码设置: {}", loadOptions.getEncoding());
        }

        // 可以在这里添加其他加载选项的处理逻辑
        log.debug("加载选项应用完成");
    }

    /**
     * 读取HTML文件内容
     */
    private String readHtmlFile(String htmlFilePath, HtmlLoadOptions htmlLoadOptions) throws IOException {
        Path path = Paths.get(htmlFilePath);
        Charset charset = (htmlLoadOptions != null && htmlLoadOptions.getEncoding() != null)
                ? htmlLoadOptions.getEncoding() : Charset.forName("UTF-8");
        return new String(Files.readAllBytes(path), charset);
    }

    /**
     * 将HTML内容转换为Word文档
     * 使用Jsoup解析HTML并保留段落结构和原始顺序
     */
    private void convertHtmlToDocument(String htmlContent, HtmlLoadOptions htmlLoadOptions) {
        try {
            // 使用Jsoup解析HTML，应用编码配置
            Charset encoding = null;
            if (htmlLoadOptions != null) {
                encoding = htmlLoadOptions.getEncoding();
            }
            org.jsoup.nodes.Document htmlDoc;
            if (encoding != null) {
                htmlDoc = org.jsoup.Jsoup.parse(htmlContent, encoding.name());
            } else {
                htmlDoc = org.jsoup.Jsoup.parse(htmlContent);
            }

            // 解析CSS样式（暂时默认启用CSS处理）
            parseCssStyles(htmlDoc);

            // 处理标题（仅处理head中的title）
            org.jsoup.select.Elements titles = htmlDoc.select("title");
            if (!titles.isEmpty()) {
                String title = titles.first().text().trim();
                if (!title.isEmpty()) {
                    XWPFParagraph titleParagraph = document.createParagraph();
                    titleParagraph.setAlignment(org.apache.poi.xwpf.usermodel.ParagraphAlignment.CENTER);
                    XWPFRun titleRun = titleParagraph.createRun();
                    titleRun.setText(title);
                    titleRun.setBold(true);
                    titleRun.setFontSize(16);
                }
            }

            // 获取body元素，按顺序处理所有子元素
            org.jsoup.nodes.Element body = htmlDoc.body();
            if (body != null) {
                processElementsInOrder(body);
            }

        } catch (Exception e) {
            log.error("HTML转换失败: {}", e.getMessage(), e);
            // 如果转换失败，添加原始HTML内容作为文本
            XWPFParagraph fallbackParagraph = document.createParagraph();
            XWPFRun fallbackRun = fallbackParagraph.createRun();
            fallbackRun.setText("[HTML转换失败] " + htmlContent.substring(0, Math.min(500, htmlContent.length())));
        }
    }

    /**
     * 按顺序处理HTML元素，保持原始文档结构
     */
    private void processElementsInOrder(org.jsoup.nodes.Element parentElement) {
        for (org.jsoup.nodes.Node node : parentElement.childNodes()) {
            if (node instanceof org.jsoup.nodes.Element) {
                org.jsoup.nodes.Element element = (org.jsoup.nodes.Element) node;
                String tagName = element.tagName().toLowerCase();

                switch (tagName) {
                    case "h1":
                    case "h2":
                    case "h3":
                    case "h4":
                    case "h5":
                    case "h6":
                        processHeading(element, tagName);
                        break;
                    case "p":
                        processParagraph(element);
                        break;
                    case "ul":
                    case "ol":
                        processList(element);
                        break;
                    case "table":
                        processTable(element);
                        break;
                    case "blockquote":
                        processBlockquote(element);
                        break;
                    case "div":
                    case "section":
                    case "article":
                        // 递归处理容器元素
                        processElementsInOrder(element);
                        break;
                    default:
                        // 对于其他元素，如果包含文本，作为段落处理
                        String text = element.ownText().trim();
                        if (!text.isEmpty()) {
                            XWPFParagraph paragraph = document.createParagraph();
                            XWPFRun run = paragraph.createRun();
                            run.setText(text);
                        }
                        break;
                }
            }
        }
    }

    /**
     * 处理标题元素
     */
    private void processHeading(Element heading, String tagName) {
        String headingText = heading.text().trim();
        if (!headingText.isEmpty()) {
            XWPFParagraph headingParagraph = document.createParagraph();
            XWPFRun headingRun = headingParagraph.createRun();
            headingRun.setText(headingText);
            headingRun.setBold(true);

            // 根据标题级别设置字体大小
            int level = Integer.parseInt(tagName.substring(1));
            headingRun.setFontSize(Math.max(12, 18 - level)); // H1=17, H2=16, ..., H6=12

            // 添加段落间距
            headingParagraph.setSpacingBefore(200);
            headingParagraph.setSpacingAfter(100);
        }
    }

    /**
     * 处理段落元素
     */
    private void processParagraph(org.jsoup.nodes.Element p) {
        XWPFParagraph wordParagraph = document.createParagraph();

        // 处理段落内的所有子节点，包括文本和内联元素
        processInlineElements(p, wordParagraph);
    }

    /**
     * 处理内联元素和文本节点
     */
    private void processInlineElements(org.jsoup.nodes.Element parentElement, XWPFParagraph wordParagraph) {
        for (org.jsoup.nodes.Node node : parentElement.childNodes()) {
            if (node instanceof org.jsoup.nodes.TextNode) {
                // 处理纯文本节点
                org.jsoup.nodes.TextNode textNode = (org.jsoup.nodes.TextNode) node;
                String text = textNode.text().trim();
                if (!text.isEmpty()) {
                    XWPFRun run = wordParagraph.createRun();
                    run.setText(text);
                }
            } else if (node instanceof org.jsoup.nodes.Element) {
                // 处理内联元素
                org.jsoup.nodes.Element element = (org.jsoup.nodes.Element) node;
                processInlineElement(element, wordParagraph);
            }
        }
    }

    /**
     * 处理内联元素（如span、strong、em等）
     */
    private void processInlineElement(org.jsoup.nodes.Element element, XWPFParagraph wordParagraph) {
        String tagName = element.tagName().toLowerCase();
        String text = element.text().trim();

        if (!text.isEmpty()) {
            XWPFRun run = wordParagraph.createRun();
            run.setText(text);

            // 根据标签类型设置样式
            switch (tagName) {
                case "strong":
                case "b":
                    run.setBold(true);
                    break;
                case "em":
                case "i":
                    run.setItalic(true);
                    break;
                case "span":
                    // 处理span元素的样式
                    applySpanStyles(run, element);
                    break;
                default:
                    // 对于其他内联元素，递归处理其子元素
                    processInlineElements(element, wordParagraph);
                    return; // 避免重复处理文本
            }
        }
    }

    /**
     * 应用span元素的样式
     */
    private void applySpanStyles(XWPFRun run, org.jsoup.nodes.Element spanElement) {
        try {
            // 获取class属性
            String className = spanElement.attr("class");

            // 处理背景色
            String backgroundColor = "";

            // 优先从CSS规则中获取背景色
            if (!className.isEmpty()) {
                backgroundColor = getCssProperty(spanElement, "background-color");
            }

            // 如果没有找到CSS规则，检查内联样式
            if (backgroundColor.isEmpty()) {
                String style = spanElement.attr("style");
                backgroundColor = extractCssProperty(style, "background-color");
            }

            // 应用背景色
            if (!backgroundColor.isEmpty()) {
                try {
                    String colorHex = backgroundColor.replace("#", "");
                    if (colorHex.length() == 6) {
                        // 设置文本高亮背景色
                        run.setTextHighlightColor(colorHex.toUpperCase());
                        log.info("成功设置span背景色: {}", backgroundColor);
                    }
                } catch (Exception e) {
                    log.warn("设置span背景色失败: {}", e.getMessage());
                }
            }

            // 处理其他样式属性
            String color = getCssProperty(spanElement, "color");
            if (!color.isEmpty()) {
                String colorHex = color.replace("#", "");
                if (colorHex.length() == 6) {
                    run.setColor(colorHex.toUpperCase());
                }
            }

            String fontWeight = getCssProperty(spanElement, "font-weight");
            if ("bold".equals(fontWeight)) {
                run.setBold(true);
            }

        } catch (Exception e) {
            log.warn("应用span样式时出错: {}", e.getMessage());
        }
    }

    /**
     * 处理列表元素
     */
    private void processList(org.jsoup.nodes.Element list) {
        org.jsoup.select.Elements listItems = list.select("li");
        for (int i = 0; i < listItems.size(); i++) {
            org.jsoup.nodes.Element li = listItems.get(i);
            String itemText = li.text().trim();
            if (!itemText.isEmpty()) {
                XWPFParagraph listParagraph = document.createParagraph();
                XWPFRun listRun = listParagraph.createRun();

                // 添加列表标记
                String prefix = list.tagName().equals("ul") ? "• " : (i + 1) + ". ";
                listRun.setText(prefix + itemText);

                // 设置缩进
                listParagraph.setIndentationLeft(400);
            }
        }
    }

    /**
     * 处理引用块元素
     */
    private void processBlockquote(org.jsoup.nodes.Element blockquote) {
        String quoteText = blockquote.text().trim();
        if (!quoteText.isEmpty()) {
            XWPFParagraph quoteParagraph = document.createParagraph();
            XWPFRun quoteRun = quoteParagraph.createRun();
            quoteRun.setText("\"" + quoteText + "\"");
            quoteRun.setItalic(true);

            // 设置引用样式
            quoteParagraph.setIndentationLeft(400);
        }
    }

    /**
     * 处理表格元素
     */
    private void processTable(org.jsoup.nodes.Element table) {
        try {
            // 创建Word表格
            XWPFTable wordTable = document.createTable();

            // 应用表格样式
            applyTableStyles(wordTable, table);

            // 获取所有行
            org.jsoup.select.Elements rows = table.select("tr");
            boolean isFirstRow = true;

            for (org.jsoup.nodes.Element row : rows) {
                XWPFTableRow wordRow;

                if (isFirstRow && wordTable.getRows().size() > 0) {
                    // 使用已存在的第一行
                    wordRow = wordTable.getRow(0);
                    isFirstRow = false;
                } else {
                    // 创建新行
                    wordRow = wordTable.createRow();
                }

                // 应用行样式
                applyRowStyles(wordRow, row);

                // 获取单元格（th或td）
                org.jsoup.select.Elements cells = row.select("th, td");

                for (int cellIndex = 0; cellIndex < cells.size(); cellIndex++) {
                    org.jsoup.nodes.Element cell = cells.get(cellIndex);
                    String cellText = cell.text().trim();

                    XWPFTableCell wordCell;
                    if (cellIndex < wordRow.getTableCells().size()) {
                        wordCell = wordRow.getCell(cellIndex);
                    } else {
                        wordCell = wordRow.addNewTableCell();
                    }

                    // 应用单元格样式
                    applyCellStyles(wordCell, cell);

                    // 设置单元格内容
                    if (!cellText.isEmpty()) {
                        // 清除现有内容
                        wordCell.removeParagraph(0);
                        XWPFParagraph cellParagraph = wordCell.addParagraph();
                        XWPFRun cellRun = cellParagraph.createRun();
                        cellRun.setText(cellText);

                        // 如果是表头（th），设置加粗
                        if (cell.tagName().equals("th")) {
                            cellRun.setBold(true);
                        }

                        // 应用文本样式
                        applyTextStyles(cellRun, cell);
                    }
                }
            }

            log.debug("成功处理表格，包含 {} 行", rows.size());
        } catch (Exception e) {
            log.warn("处理表格时出错: {}", e.getMessage());
            // 如果表格处理失败，添加表格内容作为普通文本
            String tableText = table.text().trim();
            if (!tableText.isEmpty()) {
                XWPFParagraph fallbackParagraph = document.createParagraph();
                XWPFRun fallbackRun = fallbackParagraph.createRun();
                fallbackRun.setText("[表格内容] " + tableText);
            }
        }
    }

    /**
     * 应用表格样式
     */
    private void applyTableStyles(XWPFTable wordTable, org.jsoup.nodes.Element table) {
        try {
            // 解析width属性（优先使用CSS规则）
            String width = getCssProperty(table, "width");
            if (!width.isEmpty()) {
                // 设置表格宽度（POI中表格宽度设置比较复杂，这里简化处理）
                log.debug("表格宽度: {}", width);
                if ("100%".equals(width)) {
                    // 设置表格为全宽
                    wordTable.setWidth("100%");
                }
            }

            // 解析margin属性
            String margin = getCssProperty(table, "margin");
            if (!margin.isEmpty()) {
                log.debug("表格边距: {}", margin);
            }

            // 解析border-collapse属性
            String borderCollapse = getCssProperty(table, "border-collapse");
            if ("collapse".equals(borderCollapse)) {
                log.debug("表格边框合并: {}", borderCollapse);
                // POI中设置边框合并
            }

            // 设置表格对齐方式
            wordTable.setTableAlignment(TableRowAlign.LEFT);

        } catch (Exception e) {
            log.warn("应用表格样式时出错: {}", e.getMessage());
        }
    }

    /**
     * 应用行样式
     */
    private void applyRowStyles(XWPFTableRow wordRow, org.jsoup.nodes.Element row) {
        try {
            // 解析行高（优先使用CSS规则）
            String height = getCssProperty(row, "height");
            if (!height.isEmpty()) {
                log.debug("行高: {}", height);
                // POI中设置行高需要转换单位
                try {
                    String heightValue = height.replaceAll("[^0-9.]", "");
                    if (!heightValue.isEmpty()) {
                        double heightNum = Double.parseDouble(heightValue);
                        // 转换为磅（points）
                        int heightInPoints = (int) (heightNum * 20); // 1pt = 20 twips
                        wordRow.setHeight(heightInPoints);
                        log.debug("设置行高: {}pt", heightNum);
                    }
                } catch (NumberFormatException e) {
                    log.warn("解析行高失败: {}", height);
                }
            }

            // 解析背景色
            String backgroundColor = getCssProperty(row, "background-color");
            if (!backgroundColor.isEmpty()) {
                log.debug("行背景色: {}", backgroundColor);
                // POI中设置行背景色比较复杂，这里记录日志
            }

        } catch (Exception e) {
            log.warn("应用行样式时出错: {}", e.getMessage());
        }
    }

    /**
     * 应用单元格样式
     */
    private void applyCellStyles(XWPFTableCell wordCell, org.jsoup.nodes.Element cell) {
        try {
            // 设置单元格边框（基于CSS中的border: 1px solid #ddd）
            wordCell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.TOP);

            // 解析边框样式（优先使用CSS规则）
            String border = getCssProperty(cell, "border");

            // 只有当HTML中明确设置了边框样式时才应用边框
            if (!border.isEmpty()) {
                try {
                    // 解析边框属性：width style color
                    String[] borderParts = border.trim().split("\\s+");
                    String borderWidth = "1px";  // 默认宽度
                    String borderStyle = "solid"; // 默认样式
                    String borderColor = "000000"; // 默认颜色

                    // 解析边框各部分
                    for (String part : borderParts) {
                        part = part.trim();
                        if (part.matches("\\d+px")) {
                            // 宽度部分
                            borderWidth = part;
                        } else if (part.matches("solid|dashed|dotted|double|groove|ridge|inset|outset")) {
                            // 样式部分
                            borderStyle = part;
                        } else if (part.startsWith("#")) {
                            // 颜色部分
                            String colorPart = part.substring(1);
                            if (colorPart.length() == 3) {
                                // 3位颜色转换为6位 (如 ddd -> dddddd)
                                borderColor = "" + colorPart.charAt(0) + colorPart.charAt(0) +
                                        colorPart.charAt(1) + colorPart.charAt(1) +
                                        colorPart.charAt(2) + colorPart.charAt(2);
                            } else if (colorPart.length() >= 6) {
                                borderColor = colorPart.substring(0, 6);
                            }
                        } else if (part.matches("[a-zA-Z]+")) {
                            // 可能是颜色名称，这里简单处理几个常见的
                            switch (part.toLowerCase()) {
                                case "black":
                                    borderColor = "000000";
                                    break;
                                case "white":
                                    borderColor = "ffffff";
                                    break;
                                case "red":
                                    borderColor = "ff0000";
                                    break;
                                case "green":
                                    borderColor = "008000";
                                    break;
                                case "blue":
                                    borderColor = "0000ff";
                                    break;
                                case "gray":
                                case "grey":
                                    borderColor = "808080";
                                    break;
                                default:
                                    break;
                            }
                        }
                    }

                    // 使用POI设置单元格边框
                    CTTcPr tcPr = wordCell.getCTTc().getTcPr();
                    if (tcPr == null) {
                        tcPr = wordCell.getCTTc().addNewTcPr();
                    }

                    CTTcBorders borders = tcPr.getTcBorders();
                    if (borders == null) {
                        borders = tcPr.addNewTcBorders();
                    }

                    // 设置四个方向的边框
                    setBorderStyle(borders.addNewTop(), borderColor);
                    setBorderStyle(borders.addNewBottom(), borderColor);
                    setBorderStyle(borders.addNewLeft(), borderColor);
                    setBorderStyle(borders.addNewRight(), borderColor);

                    log.debug("设置单元格边框: {} (宽度: {}, 样式: {}, 颜色: #{})", border, borderWidth, borderStyle, borderColor);
                } catch (Exception e) {
                    log.warn("设置单元格边框失败: {}", e.getMessage());
                }
            }

            // 解析背景色（优先使用CSS规则）
            String backgroundColor = getCssProperty(cell, "background-color");
            if (backgroundColor.isEmpty() && cell.tagName().equals("th")) {
                // 表头默认背景色 #f2f2f2
                backgroundColor = "#f2f2f2";
            }

            if (!backgroundColor.isEmpty()) {
                try {
                    // 设置单元格背景色
                    String colorHex = backgroundColor.replace("#", "");
                    if (colorHex.length() == 6) {
                        log.debug("单元格背景色: {}", backgroundColor);

                        // 使用POI设置单元格背景色
                        CTTcPr tcPr = wordCell.getCTTc().getTcPr();
                        if (tcPr == null) {
                            tcPr = wordCell.getCTTc().addNewTcPr();
                        }

                        CTShd shd = tcPr.getShd();
                        if (shd == null) {
                            shd = tcPr.addNewShd();
                        }

                        shd.setVal(STShd.CLEAR);
                        shd.setColor("auto");
                        shd.setFill(colorHex.toUpperCase());

                        log.info("成功设置单元格背景色: {}", backgroundColor);
                    }
                } catch (Exception e) {
                    log.warn("设置单元格背景色失败: {}", e.getMessage());
                }
            }

            // 解析内边距（padding: 8px）
            String padding = getCssProperty(cell, "padding");
            if (padding.isEmpty()) {
                padding = "8px"; // 默认内边距
            }
            log.debug("单元格内边距: {}", padding);

            // 解析文本对齐（text-align: left）
            String textAlign = getCssProperty(cell, "text-align");
            if (textAlign.isEmpty()) {
                textAlign = "left"; // 默认左对齐
            }

            // 设置段落对齐方式
            if (wordCell.getParagraphs().size() > 0) {
                XWPFParagraph paragraph = wordCell.getParagraphs().get(0);
                switch (textAlign.toLowerCase()) {
                    case "center":
                        paragraph.setAlignment(ParagraphAlignment.CENTER);
                        break;
                    case "right":
                        paragraph.setAlignment(ParagraphAlignment.RIGHT);
                        break;
                    case "left":
                    default:
                        paragraph.setAlignment(ParagraphAlignment.LEFT);
                        break;
                }
            }

        } catch (Exception e) {
            log.warn("应用单元格样式时出错: {}", e.getMessage());
        }
    }

    /**
     * 应用文本样式
     */
    private void applyTextStyles(XWPFRun cellRun, org.jsoup.nodes.Element cell) {
        try {
            // 解析字体颜色（优先使用CSS规则）
            String color = getCssProperty(cell, "color");
            if (!color.isEmpty()) {
                log.debug("文本颜色: {}", color);
                // POI中设置文本颜色
            }

            // 解析字体粗细
            String fontWeight = getCssProperty(cell, "font-weight");
            if ("bold".equals(fontWeight) || cell.tagName().equals("th")) {
                cellRun.setBold(true);
            }

            // 解析字体大小
            String fontSize = getCssProperty(cell, "font-size");
            if (!fontSize.isEmpty()) {
                try {
                    // 提取数字部分
                    String sizeStr = fontSize.replaceAll("[^0-9.]", "");
                    if (!sizeStr.isEmpty()) {
                        double size = Double.parseDouble(sizeStr);
                        cellRun.setFontSize((int) size);
                        log.debug("字体大小: {}pt", size);
                    }
                } catch (NumberFormatException e) {
                    log.warn("解析字体大小失败: {}", fontSize);
                }
            }

        } catch (Exception e) {
            log.warn("应用文本样式时出错: {}", e.getMessage());
        }
    }

    // CSS样式缓存
    private Map<String, Map<String, String>> cssRules = new HashMap<>();

    /**
     * 解析HTML文档中的CSS样式
     */
    private void parseCssStyles(org.jsoup.nodes.Document htmlDoc) {
        try {
            // 解析<style>标签中的CSS
            org.jsoup.select.Elements styleElements = htmlDoc.select("style");
            for (org.jsoup.nodes.Element styleElement : styleElements) {
                String cssContent = styleElement.html();
                parseCssContent(cssContent);
            }

            log.debug("解析到 {} 个CSS规则", cssRules.size());
        } catch (Exception e) {
            log.warn("解析CSS样式时出错: {}", e.getMessage());
        }
    }

    /**
     * 解析CSS内容
     */
    private void parseCssContent(String cssContent) {
        try {
            // 简单的CSS解析器
            // 移除注释
            cssContent = cssContent.replaceAll("/\\*.*?\\*/", "");

            // 按规则分割（以}为分隔符）
            String[] rules = cssContent.split("}");

            for (String rule : rules) {
                rule = rule.trim();
                if (rule.isEmpty()) continue;

                int braceIndex = rule.indexOf("{");
                if (braceIndex > 0) {
                    String selector = rule.substring(0, braceIndex).trim();
                    String declarations = rule.substring(braceIndex + 1).trim();

                    Map<String, String> properties = new HashMap<>();

                    // 解析属性声明
                    String[] declarationArray = declarations.split(";");
                    for (String declaration : declarationArray) {
                        declaration = declaration.trim();
                        if (declaration.isEmpty()) continue;

                        int colonIndex = declaration.indexOf(":");
                        if (colonIndex > 0) {
                            String property = declaration.substring(0, colonIndex).trim();
                            String value = declaration.substring(colonIndex + 1).trim();
                            properties.put(property, value);
                        }
                    }

                    if (!properties.isEmpty()) {
                        cssRules.put(selector, properties);
                        log.debug("解析CSS规则: {} -> {}", selector, properties);
                    }
                }
            }
        } catch (Exception e) {
            log.warn("解析CSS内容时出错: {}", e.getMessage());
        }
    }

    /**
     * 获取元素的CSS样式属性值
     */
    private String getCssProperty(org.jsoup.nodes.Element element, String property) {
        // 首先检查内联样式
        String inlineValue = extractCssProperty(element.attr("style"), property);
        if (!inlineValue.isEmpty()) {
            return inlineValue;
        }

        // 然后检查CSS规则
        String tagName = element.tagName().toLowerCase();
        String className = element.attr("class");
        String id = element.attr("id");

        // 按优先级检查CSS规则：ID > Class > Tag
        if (!id.isEmpty()) {
            String idSelector = "#" + id;
            Map<String, String> idRules = cssRules.get(idSelector);
            if (idRules != null && idRules.containsKey(property)) {
                return idRules.get(property);
            }
        }

        if (!className.isEmpty()) {
            String[] classes = className.split("\\s+");
            for (String cls : classes) {
                String classSelector = "." + cls;
                Map<String, String> classRules = cssRules.get(classSelector);
                if (classRules != null && classRules.containsKey(property)) {
                    return classRules.get(property);
                }
            }
        }

        // 检查标签选择器
        Map<String, String> tagRules = cssRules.get(tagName);
        if (tagRules != null && tagRules.containsKey(property)) {
            return tagRules.get(property);
        }

        // 检查组合选择器（如 "th, td"）
        for (Map.Entry<String, Map<String, String>> entry : cssRules.entrySet()) {
            String selector = entry.getKey();
            Map<String, String> rules = entry.getValue();

            // 检查是否为逗号分隔的选择器组合
            if (selector.contains(",") && rules.containsKey(property)) {
                String[] selectors = selector.split(",");
                for (String singleSelector : selectors) {
                    singleSelector = singleSelector.trim();
                    if (singleSelector.equals(tagName)) {
                        return rules.get(property);
                    }
                }
            }
        }

        return "";
    }

    /**
     * 从CSS样式字符串中提取指定属性的值
     */
    private String extractCssProperty(String cssStyle, String property) {
        if (cssStyle == null || cssStyle.isEmpty() || property == null) {
            return "";
        }

        try {
            // 将CSS样式按分号分割
            String[] declarations = cssStyle.split(";");
            for (String declaration : declarations) {
                String[] parts = declaration.split(":");
                if (parts.length == 2) {
                    String prop = parts[0].trim();
                    String value = parts[1].trim();
                    if (property.equalsIgnoreCase(prop)) {
                        return value;
                    }
                }
            }
        } catch (Exception e) {
            log.warn("解析CSS属性时出错: {}", e.getMessage());
        }

        return "";
    }

    /**
     * 获取内置文档属性
     * 模拟 Aspose Words Document.getBuiltInDocumentProperties() 方法
     */
    public BuiltInDocumentProperties getBuiltInDocumentProperties() {
        return builtInDocumentProperties;
    }

    /**
     * 获取自定义文档属性
     * 模拟 Aspose Words Document.getCustomDocumentProperties() 方法
     */
    public CustomDocumentProperties getCustomDocumentProperties() {
        return customDocumentProperties;
    }

    /**
     * 获取节集合
     * 模拟 Aspose Words Document.getSections() 方法
     */
    public SectionCollection getSections() {
        return sections;
    }

    /**
     * 获取文档文本
     * 模拟 Aspose Words Document.getText() 方法
     */
    public String getText() throws BoundesuWordsException {
        try {
            StringBuilder text = new StringBuilder();
            for (XWPFParagraph paragraph : document.getParagraphs()) {
                text.append(paragraph.getText()).append("\n");
            }
            return text.toString();
        } catch (Exception e) {
            log.error("获取文档文本失败: {}", e.getMessage());
            throw new BoundesuWordsException("获取文档文本失败: " + e.getMessage(), e);
        }
    }

    /**
     * 获取兼容性选项
     * 模拟 Aspose Words Document.getCompatibilityOptions() 方法
     */
    public CompatibilityOptions getCompatibilityOptions() {
        return compatibilityOptions;
    }

    /**
     * 获取页面配置
     */
    public DocumentPageConfig getPageConfig() {
        return pageConfig;
    }

    /**
     * 设置页面配置
     */
    public void setPageConfig(DocumentPageConfig pageConfig) {
        this.pageConfig = pageConfig;
    }

    /**
     * 获取HTML加载配置
     */
    public HtmlLoadConfig getHtmlConfig() {
        return htmlConfig;
    }

    /**
     * 设置HTML加载配置
     */
    public void setHtmlConfig(HtmlLoadConfig htmlConfig) {
        this.htmlConfig = htmlConfig;
    }

    /**
     * 获取XML加载配置
     */
    public XmlLoadConfig getXmlConfig() {
        return xmlConfig;
    }

    /**
     * 设置XML加载配置
     */
    public void setXmlConfig(XmlLoadConfig xmlConfig) {
        this.xmlConfig = xmlConfig;
    }

    /**
     * 保存文档到指定路径
     * 模拟 Aspose Words Document.save(String) 方法
     */
    public void save(String filePath) throws BoundesuWordsException {
        if (StringUtils.isEmpty(filePath)) {
            throw new BoundesuWordsException("保存路径不能为空");
        }

        try {
            // 确保目录存在
            File file = new File(filePath);
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            try (FileOutputStream fos = new FileOutputStream(file)) {
                document.write(fos);
            }

            log.info("文档保存成功: {}", filePath);
        } catch (Exception e) {
            log.error("保存文档失败: {}", e.getMessage());
            throw new BoundesuWordsException("文档保存失败: " + e.getMessage(), e);
        }
    }

    /**
     * 保存文档到指定路径和格式
     * 模拟 Aspose Words Document.save(String, SaveOptions) 方法
     */
    public void save(String filePath, com.boundesu.words.core.options.SaveOptions saveOptions) throws BoundesuWordsException {
        // 目前简化实现，忽略SaveOptions
        save(filePath);

        if (saveOptions != null) {
            log.debug("应用保存选项: {}", saveOptions.getClass().getSimpleName());
        }
    }

    /**
     * 保存文档到输出流
     * 模拟 Aspose Words Document.save(OutputStream, SaveOptions) 方法
     */
    public void save(OutputStream outputStream, com.boundesu.words.core.options.SaveOptions saveOptions) throws BoundesuWordsException {
        try {
            document.write(outputStream);
            log.debug("文档保存到输出流成功");
        } catch (Exception e) {
            log.error("保存文档到输出流失败: {}", e.getMessage());
            throw new BoundesuWordsException("文档保存失败: " + e.getMessage(), e);
        }
    }

    /**
     * 关闭文档
     * 模拟 Aspose Words Document.close() 方法
     */
    public void close() throws BoundesuWordsException {
        try {
            if (document != null) {
                document.close();
                log.debug("文档关闭成功");
            }
        } catch (Exception e) {
            log.error("关闭文档失败: {}", e.getMessage());
            throw new BoundesuWordsException("文档关闭失败: " + e.getMessage(), e);
        }
    }

    /**
     * 获取底层POI文档对象
     */
    public XWPFDocument getXWPFDocument() {
        return document;
    }

    /**
     * 获取文档的内部XWPFDocument对象（兼容旧API）
     *
     * @return XWPFDocument对象
     */
    public XWPFDocument getInternalDocument() {
        return this.document;
    }


    /**
     * 应用HTML加载选项
     */
    private void applyHtmlLoadOptions(com.boundesu.words.core.options.HtmlLoadOptions htmlOptions) {
        // 应用HTML特定的加载选项
        log.debug("应用HTML加载选项:");
        log.debug("  - WebRequestTimeout: {} ms", htmlOptions.getWebRequestTimeout());
        log.debug("  - BlockImportMode: {}", htmlOptions.getBlockImportMode());
        log.debug("  - PreferredControlType: {}", htmlOptions.getPreferredControlType());
        log.debug("  - SupportVml: {}", htmlOptions.getSupportVml());
        log.debug("  - ConvertSvgToEmf: {}", htmlOptions.getConvertSvgToEmf());
        log.debug("  - IgnoreNoscriptElements: {}", htmlOptions.getIgnoreNoscriptElements());
        log.debug("  - SupportFontFaceRules: {}", htmlOptions.getSupportFontFaceRules());
        log.debug("  - Encoding: {}", htmlOptions.getEncoding());
        log.debug("  - MsWordVersion: {}", htmlOptions.getMsWordVersion());

        // 注意：由于使用POI而不是真正的HTML解析器，
        // 这些选项主要用于日志记录和未来扩展
        // 实际的HTML到DOCX转换需要专门的HTML解析库
    }

    // ==================== 静态工具方法 ====================

    /**
     * 获取文档文本内容
     * 静态工具方法，用于兼容原有的DocumentUtils API
     */
    public static String getDocumentText(XWPFDocument document) throws BoundesuWordsException {
        if (document == null) {
            throw new BoundesuWordsException("文档不能为空");
        }

        try {
            StringBuilder text = new StringBuilder();
            for (XWPFParagraph paragraph : document.getParagraphs()) {
                text.append(paragraph.getText()).append("\n");
            }
            return text.toString();
        } catch (Exception e) {
            log.error("获取文档文本失败: {}", e.getMessage());
            throw new BoundesuWordsException("获取文档文本失败: " + e.getMessage(), e);
        }
    }

    /**
     * 合并文档
     * 静态工具方法，用于兼容原有的DocumentUtils API
     */
    public static void mergeDocuments(XWPFDocument targetDocument, List<XWPFDocument> sourceDocuments) throws BoundesuWordsException {
        if (targetDocument == null) {
            throw new BoundesuWordsException("目标文档不能为空");
        }

        if (sourceDocuments == null || sourceDocuments.isEmpty()) {
            log.debug("源文档列表为空，无需合并");
            return;
        }

        try {
            for (XWPFDocument sourceDoc : sourceDocuments) {
                if (sourceDoc != null) {
                    // 复制段落
                    for (XWPFParagraph sourceParagraph : sourceDoc.getParagraphs()) {
                        XWPFParagraph targetParagraph = targetDocument.createParagraph();
                        copyParagraph(sourceParagraph, targetParagraph);
                    }

                    // 复制表格
                    for (XWPFTable sourceTable : sourceDoc.getTables()) {
                        XWPFTable targetTable = targetDocument.createTable();
                        copyTable(sourceTable, targetTable);
                    }
                }
            }

            log.debug("文档合并成功，合并了 {} 个源文档", sourceDocuments.size());
        } catch (Exception e) {
            log.error("文档合并失败: {}", e.getMessage());
            throw new BoundesuWordsException("文档合并失败: " + e.getMessage(), e);
        }
    }

    /**
     * 复制段落内容
     */
    private static void copyParagraph(XWPFParagraph source, XWPFParagraph target) {
        try {
            // 复制段落文本
            for (XWPFRun sourceRun : source.getRuns()) {
                XWPFRun targetRun = target.createRun();
                targetRun.setText(sourceRun.getText(0));

                // 复制格式
                if (sourceRun.isBold()) {
                    targetRun.setBold(true);
                }
                if (sourceRun.isItalic()) {
                    targetRun.setItalic(true);
                }
                if (sourceRun.getFontSize() > 0) {
                    targetRun.setFontSize(sourceRun.getFontSize());
                }
            }
        } catch (Exception e) {
            log.warn("复制段落时出错: {}", e.getMessage());
        }
    }

    /**
     * 复制表格内容
     */
    private static void copyTable(XWPFTable source, XWPFTable target) {
        try {
            List<XWPFTableRow> sourceRows = source.getRows();

            for (int rowIndex = 0; rowIndex < sourceRows.size(); rowIndex++) {
                XWPFTableRow sourceRow = sourceRows.get(rowIndex);
                XWPFTableRow targetRow;

                if (rowIndex == 0 && target.getRows().size() > 0) {
                    targetRow = target.getRow(0);
                } else {
                    targetRow = target.createRow();
                }

                List<XWPFTableCell> sourceCells = sourceRow.getTableCells();
                for (int cellIndex = 0; cellIndex < sourceCells.size(); cellIndex++) {
                    XWPFTableCell sourceCell = sourceCells.get(cellIndex);
                    XWPFTableCell targetCell;

                    if (cellIndex < targetRow.getTableCells().size()) {
                        targetCell = targetRow.getCell(cellIndex);
                    } else {
                        targetCell = targetRow.addNewTableCell();
                    }

                    // 复制单元格内容
                    String cellText = sourceCell.getText();
                    if (!cellText.isEmpty()) {
                        targetCell.removeParagraph(0);
                        XWPFParagraph cellParagraph = targetCell.addParagraph();
                        XWPFRun cellRun = cellParagraph.createRun();
                        cellRun.setText(cellText);
                    }
                }
            }
        } catch (Exception e) {
            log.warn("复制表格时出错: {}", e.getMessage());
        }
    }

    /**
     * 获取文档段落数量
     * 静态工具方法，用于兼容原有的DocumentUtils API
     */
    public static int getParagraphCount(XWPFDocument document) throws BoundesuWordsException {
        if (document == null) {
            throw new BoundesuWordsException("文档不能为空");
        }

        return document.getParagraphs().size();
    }

    /**
     * 获取文档表格数量
     * 静态工具方法，用于兼容原有的DocumentUtils API
     */
    public static int getTableCount(XWPFDocument document) throws BoundesuWordsException {
        if (document == null) {
            throw new BoundesuWordsException("文档不能为空");
        }

        return document.getTables().size();
    }

    /**
     * 设置边框样式
     */
    private void setBorderStyle(CTBorder border, String colorHex) {
        border.setVal(STBorder.SINGLE);
        border.setSz(BigInteger.valueOf(4)); // 1pt = 8 units, so 0.5pt = 4 units
        border.setSpace(BigInteger.valueOf(0));
        border.setColor(colorHex.toUpperCase());
    }
}