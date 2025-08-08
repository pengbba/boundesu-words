package com.boundesu.words.core;

import com.boundesu.words.common.constants.BoundesuConstants;
import com.boundesu.words.common.constants.DocxConstants;
import com.boundesu.words.common.exception.BoundesuWordsException;
import com.boundesu.words.common.util.FileUtils;
import com.boundesu.words.common.util.StringUtils;
import com.boundesu.words.core.builder.DocumentBuilder;
import com.boundesu.words.core.options.DocxSaveOptions;
import com.boundesu.words.core.options.HtmlLoadOptions;
import com.boundesu.words.core.options.LoadOptions;
import com.boundesu.words.core.options.PdfSaveOptions;
import com.boundesu.words.core.options.SaveOptions;
import com.boundesu.words.core.options.LoadOptions.LoadFormat;
import com.boundesu.words.core.options.SaveOptions.SaveFormat;
import org.apache.poi.xwpf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Boundesu Words SDK 主入口类
 *
 * @author Boundesu
 * @version 1.0.0
 */
public class BoundesuWords {

    private static final Logger log = LoggerFactory.getLogger(BoundesuWords.class);

    /**
     * 构造函数
     */
    public BoundesuWords() {
        log.info("Boundesu Words Core 初始化完成");
    }

    /**
     * 获取SDK版本信息
     *
     * @return SDK信息
     */
    public static Map<String, String> getSDKInfo() {
        Map<String, String> info = new LinkedHashMap<>();
        info.put("name", BoundesuConstants.SDK_NAME + " Core");
        info.put("version", BoundesuConstants.SDK_VERSION);
        info.put("description", BoundesuConstants.SDK_DESCRIPTION);
        info.put("author", BoundesuConstants.SDK_AUTHOR);
        return info;
    }

    /**
     * 获取SDK版本号
     *
     * @return 版本号
     */
    public static String getVersion() {
        return BoundesuConstants.SDK_VERSION;
    }

    /**
     * 保存DOCX文档到文件
     *
     * @param document   DOCX文档
     * @param outputFile 输出文件
     * @throws BoundesuWordsException 保存异常
     */
    public void saveToFile(XWPFDocument document, File outputFile) throws BoundesuWordsException {
        if (document == null) {
            throw new BoundesuWordsException("文档不能为空");
        }
        if (outputFile == null) {
            throw new BoundesuWordsException("输出文件不能为空");
        }

        try {
            // 确保父目录存在
            File parentDir = outputFile.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                document.write(fos);
                log.info("文档已保存到: {}", outputFile.getAbsolutePath());
            }
        } catch (IOException e) {
            throw new BoundesuWordsException("保存文档失败: " + e.getMessage(), e);
        }
    }

    /**
     * 保存DOCX文档到文件
     *
     * @param document       DOCX文档
     * @param outputFilePath 输出文件路径
     * @throws BoundesuWordsException 保存异常
     */
    public void saveToFile(XWPFDocument document, String outputFilePath) throws BoundesuWordsException {
        if (StringUtils.isEmpty(outputFilePath)) {
            throw new BoundesuWordsException("输出文件路径不能为空");
        }
        saveToFile(document, new File(outputFilePath));
    }

    /**
     * 创建新的文档
     *
     * @return Document实例
     * @throws BoundesuWordsException 创建异常
     */
    public Document createDocument() throws BoundesuWordsException {
        return new Document();
    }

    /**
     * 从文件加载文档
     *
     * @param fileName 文件名
     * @return Document实例
     * @throws BoundesuWordsException 加载异常
     */
    public Document loadDocument(String fileName) throws BoundesuWordsException {
        return new Document(fileName);
    }

    /**
     * 从文件加载文档，使用加载选项
     *
     * @param fileName    文件名
     * @param loadOptions 加载选项
     * @return Document实例
     * @throws BoundesuWordsException 加载异常
     */
    public Document loadDocument(String fileName, LoadOptions loadOptions) throws BoundesuWordsException {
        return new Document(fileName, loadOptions);
    }

    /**
     * 从输入流加载文档
     *
     * @param stream 输入流
     * @return Document实例
     * @throws BoundesuWordsException 加载异常
     */
    public Document loadDocument(InputStream stream) throws BoundesuWordsException {
        return new Document(stream);
    }

    /**
     * 从输入流加载文档，使用加载选项
     *
     * @param stream      输入流
     * @param loadOptions 加载选项
     * @return Document实例
     * @throws BoundesuWordsException 加载异常
     */
    public Document loadDocument(InputStream stream, LoadOptions loadOptions) throws BoundesuWordsException {
        return new Document(stream, loadOptions);
    }

    /**
     * 创建文档构建器
     *
     * @return DocumentBuilder实例
     */
    public DocumentBuilder createDocumentBuilder() {
        return new DocumentBuilder();
    }

    /**
     * 为指定文档创建文档构建器
     *
     * @param document 文档
     * @return DocumentBuilder实例
     */
    public DocumentBuilder createDocumentBuilder(Document document) {
        return new DocumentBuilder(document.getInternalDocument());
    }

    /**
     * 保存文档
     *
     * @param document   文档
     * @param fileName   文件名
     * @param saveOptions 保存选项
     * @throws BoundesuWordsException 保存异常
     */
    public void saveDocument(Document document, String fileName, SaveOptions saveOptions) throws BoundesuWordsException {
        document.save(fileName, saveOptions);
        log.info("文档已保存到: {}", fileName);
    }

    /**
     * 保存文档
     *
     * @param document 文档
     * @param fileName 文件名
     * @throws BoundesuWordsException 保存异常
     */
    public void saveDocument(Document document, String fileName) throws BoundesuWordsException {
        document.save(fileName);
    }

    // ==================== 文档工具方法 ====================

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

        public Date getCreatedTime() {
            return document.getProperties().getCoreProperties().getCreated();
        }

        public void setCreatedTime(Date createdTime) {
            document.getProperties().getCoreProperties().setCreated(Optional.ofNullable(createdTime));
        }

        public Date getLastSavedTime() {
            return document.getProperties().getCoreProperties().getModified();
        }

        public void setLastSavedTime(Date lastSavedTime) {
            document.getProperties().getCoreProperties().setModified(Optional.ofNullable(lastSavedTime));
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
     * 页面设置类
     */
    public static class PageSetup {
        private double leftMargin = 72.0; // 1英寸
        private double rightMargin = 72.0;
        private double topMargin = 72.0;
        private double bottomMargin = 72.0;
        private double pageWidth = 612.0; // A4宽度
        private double pageHeight = 792.0; // A4高度

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
     * 节类
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

        public XWPFDocument getDocument() {
            return document;
        }
    }

    /**
     * 节集合类
     */
    public static class SectionCollection {
        private XWPFDocument document;
        private List<Section> sections;

        public SectionCollection(XWPFDocument document) {
            this.document = document;
            this.sections = new ArrayList<>();
            // 默认添加一个节
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
     * 兼容性选项类
     */
    public static class CompatibilityOptions {
        private XWPFDocument document;
        private LoadOptions.MsWordVersion optimizedVersion;

        public CompatibilityOptions(XWPFDocument document) {
            this.document = document;
            this.optimizedVersion = LoadOptions.MsWordVersion.WORD_2019; // 默认版本
        }

        public void optimizeFor(LoadOptions.MsWordVersion version) {
            this.optimizedVersion = version;
            log.debug("优化兼容性设置为: {}", version);
            
            // 这里可以根据不同版本设置不同的兼容性选项
            switch (version) {
                case WORD_2007:
                case WORD_2010:
                case WORD_2013:
                case WORD_2016:
                case WORD_2019:
                    // 设置相应的兼容性选项
                    break;
                default:
                    log.warn("未知的Word版本: {}", version);
                    break;
            }
        }

        public LoadOptions.MsWordVersion getOptimizedVersion() {
            return optimizedVersion;
        }
    }

    // ==================== 静态工具方法 ====================

    /**
     * 创建新文档
     */
    public static XWPFDocument createXWPFDocument() throws BoundesuWordsException {
        try {
            XWPFDocument document = new XWPFDocument();
            log.debug("创建新文档成功");
            return document;
        } catch (Exception e) {
            log.error("创建文档失败: {}", e.getMessage(), e);
            throw new BoundesuWordsException("创建文档失败: " + e.getMessage(), e);
        }
    }

    /**
     * 加载文档
     */
    public static XWPFDocument loadXWPFDocument(String filePath) throws BoundesuWordsException {
        if (StringUtils.isEmpty(filePath)) {
            throw new BoundesuWordsException("文件路径不能为空");
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            XWPFDocument document = new XWPFDocument(fis);
            log.debug("从文件加载文档成功: {}", filePath);
            return document;
        } catch (Exception e) {
            log.error("加载文档失败: {}", e.getMessage(), e);
            throw new BoundesuWordsException("加载文档失败: " + e.getMessage(), e);
        }
    }

    /**
     * 从输入流加载文档
     */
    public static XWPFDocument loadXWPFDocument(InputStream inputStream) throws BoundesuWordsException {
        if (inputStream == null) {
            throw new BoundesuWordsException("输入流不能为空");
        }

        try {
            XWPFDocument document = new XWPFDocument(inputStream);
            log.debug("从输入流加载文档成功");
            return document;
        } catch (Exception e) {
            log.error("从输入流加载文档失败: {}", e.getMessage(), e);
            throw new BoundesuWordsException("从输入流加载文档失败: " + e.getMessage(), e);
        }
    }

    /**
     * 保存文档
     */
    public static void saveXWPFDocument(XWPFDocument document, String filePath) throws BoundesuWordsException {
        if (document == null) {
            throw new BoundesuWordsException("文档不能为空");
        }
        if (StringUtils.isEmpty(filePath)) {
            throw new BoundesuWordsException("文件路径不能为空");
        }

        try {
            // 确保目录存在
            Path path = Paths.get(filePath);
            Path parentDir = path.getParent();
            if (parentDir != null && !Files.exists(parentDir)) {
                Files.createDirectories(parentDir);
            }

            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                document.write(fos);
                log.info("文档保存成功: {}", filePath);
            }
        } catch (Exception e) {
            log.error("保存文档失败: {}", e.getMessage(), e);
            throw new BoundesuWordsException("保存文档失败: " + e.getMessage(), e);
        }
    }

    /**
     * 保存文档到输出流
     */
    public static void saveXWPFDocument(XWPFDocument document, OutputStream outputStream) throws BoundesuWordsException {
        if (document == null) {
            throw new BoundesuWordsException("文档不能为空");
        }
        if (outputStream == null) {
            throw new BoundesuWordsException("输出流不能为空");
        }

        try {
            document.write(outputStream);
            log.debug("文档保存到输出流成功");
        } catch (Exception e) {
            log.error("保存文档到输出流失败: {}", e.getMessage(), e);
            throw new BoundesuWordsException("保存文档到输出流失败: " + e.getMessage(), e);
        }
    }

    /**
     * 获取文档文本内容
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
            
            // 处理表格中的文本
            for (XWPFTable table : document.getTables()) {
                for (XWPFTableRow row : table.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {
                        text.append(cell.getText()).append("\t");
                    }
                    text.append("\n");
                }
            }
            
            return text.toString();
        } catch (Exception e) {
            log.error("获取文档文本失败: {}", e.getMessage(), e);
            throw new BoundesuWordsException("获取文档文本失败: " + e.getMessage(), e);
        }
    }

    /**
     * 设置文档属性
     */
    public static void setDocumentProperties(XWPFDocument document, String title, String author, String subject, String keywords) throws BoundesuWordsException {
        if (document == null) {
            throw new BoundesuWordsException("文档不能为空");
        }

        try {
            if (!StringUtils.isEmpty(title)) {
                document.getProperties().getCoreProperties().setTitle(title);
            }
            if (!StringUtils.isEmpty(author)) {
                document.getProperties().getCoreProperties().setCreator(author);
            }
            if (!StringUtils.isEmpty(subject)) {
                document.getProperties().getCoreProperties().setSubjectProperty(subject);
            }
            if (!StringUtils.isEmpty(keywords)) {
                document.getProperties().getCoreProperties().setKeywords(keywords);
            }
            
            // 设置创建和修改时间
            Date now = new Date();
            document.getProperties().getCoreProperties().setCreated(Optional.of(now));
            document.getProperties().getCoreProperties().setModified(Optional.of(now));
            
            log.debug("文档属性设置成功");
        } catch (Exception e) {
            log.error("设置文档属性失败: {}", e.getMessage(), e);
            throw new BoundesuWordsException("设置文档属性失败: " + e.getMessage(), e);
        }
    }

    /**
     * 插入段落
     */
    public static XWPFParagraph insertParagraph(XWPFDocument document, String text) throws BoundesuWordsException {
        if (document == null) {
            throw new BoundesuWordsException("文档不能为空");
        }

        try {
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            if (!StringUtils.isEmpty(text)) {
                run.setText(text);
            }
            
            log.debug("插入段落成功");
            return paragraph;
        } catch (Exception e) {
            log.error("插入段落失败: {}", e.getMessage(), e);
            throw new BoundesuWordsException("插入段落失败: " + e.getMessage(), e);
        }
    }

    /**
     * 插入标题
     */
    public static XWPFParagraph insertHeading(XWPFDocument document, String text, int level) throws BoundesuWordsException {
        if (document == null) {
            throw new BoundesuWordsException("文档不能为空");
        }
        if (level < 1 || level > 6) {
            throw new BoundesuWordsException("标题级别必须在1-6之间");
        }

        try {
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            
            if (!StringUtils.isEmpty(text)) {
                run.setText(text);
            }
            
            // 设置标题样式
            run.setBold(true);
            switch (level) {
                case 1:
                    run.setFontSize(18);
                    break;
                case 2:
                    run.setFontSize(16);
                    break;
                case 3:
                    run.setFontSize(14);
                    break;
                case 4:
                    run.setFontSize(12);
                    break;
                case 5:
                case 6:
                    run.setFontSize(11);
                    break;
            }
            
            log.debug("插入{}级标题成功: {}", level, text);
            return paragraph;
        } catch (Exception e) {
            log.error("插入标题失败: {}", e.getMessage(), e);
            throw new BoundesuWordsException("插入标题失败: " + e.getMessage(), e);
        }
    }

    /**
     * 插入分页符
     */
    public static void insertPageBreak(XWPFDocument document) throws BoundesuWordsException {
        if (document == null) {
            throw new BoundesuWordsException("文档不能为空");
        }

        try {
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.addBreak(BreakType.PAGE);
            
            log.debug("插入分页符成功");
        } catch (Exception e) {
            log.error("插入分页符失败: {}", e.getMessage(), e);
            throw new BoundesuWordsException("插入分页符失败: " + e.getMessage(), e);
        }
    }

    /**
     * 创建表格
     */
    public static XWPFTable createTable(XWPFDocument document, int rows, int cols) throws BoundesuWordsException {
        if (document == null) {
            throw new BoundesuWordsException("文档不能为空");
        }
        if (rows <= 0 || cols <= 0) {
            throw new BoundesuWordsException("表格行数和列数必须大于0");
        }

        try {
            XWPFTable table = document.createTable(rows, cols);
            log.debug("创建{}x{}表格成功", rows, cols);
            return table;
        } catch (Exception e) {
            log.error("创建表格失败: {}", e.getMessage(), e);
            throw new BoundesuWordsException("创建表格失败: " + e.getMessage(), e);
        }
    }

    /**
     * 设置表格单元格文本
     */
    public static void setTableCellText(XWPFTable table, int row, int col, String text) throws BoundesuWordsException {
        if (table == null) {
            throw new BoundesuWordsException("表格不能为空");
        }
        if (row < 0 || row >= table.getNumberOfRows()) {
            throw new BoundesuWordsException("行索引超出范围: " + row);
        }
        if (col < 0 || col >= table.getRow(row).getTableCells().size()) {
            throw new BoundesuWordsException("列索引超出范围: " + col);
        }

        try {
            XWPFTableCell cell = table.getRow(row).getCell(col);
            cell.setText(text != null ? text : "");
            log.debug("设置表格单元格[{},{}]文本成功", row, col);
        } catch (Exception e) {
            log.error("设置表格单元格文本失败: {}", e.getMessage(), e);
            throw new BoundesuWordsException("设置表格单元格文本失败: " + e.getMessage(), e);
        }
    }

    /**
     * 查找并替换文本
     */
    public static int findAndReplace(XWPFDocument document, String searchText, String replaceText) throws BoundesuWordsException {
        if (document == null) {
            throw new BoundesuWordsException("文档不能为空");
        }
        if (StringUtils.isEmpty(searchText)) {
            throw new BoundesuWordsException("搜索文本不能为空");
        }

        int replaceCount = 0;
        try {
            // 替换段落中的文本
            for (XWPFParagraph paragraph : document.getParagraphs()) {
                for (XWPFRun run : paragraph.getRuns()) {
                    String text = run.getText(0);
                    if (text != null && text.contains(searchText)) {
                        text = text.replace(searchText, replaceText != null ? replaceText : "");
                        run.setText(text, 0);
                        replaceCount++;
                    }
                }
            }
            
            // 替换表格中的文本
            for (XWPFTable table : document.getTables()) {
                for (XWPFTableRow row : table.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {
                        for (XWPFParagraph paragraph : cell.getParagraphs()) {
                            for (XWPFRun run : paragraph.getRuns()) {
                                String text = run.getText(0);
                                if (text != null && text.contains(searchText)) {
                                    text = text.replace(searchText, replaceText != null ? replaceText : "");
                                    run.setText(text, 0);
                                    replaceCount++;
                                }
                            }
                        }
                    }
                }
            }
            
            log.debug("查找并替换完成，替换了{}处文本", replaceCount);
            return replaceCount;
        } catch (Exception e) {
            log.error("查找并替换失败: {}", e.getMessage(), e);
            throw new BoundesuWordsException("查找并替换失败: " + e.getMessage(), e);
        }
    }

    /**
     * 获取段落数量
     */
    public static int getParagraphCount(XWPFDocument document) throws BoundesuWordsException {
        if (document == null) {
            throw new BoundesuWordsException("文档不能为空");
        }

        try {
            return document.getParagraphs().size();
        } catch (Exception e) {
            log.error("获取段落数量失败: {}", e.getMessage(), e);
            throw new BoundesuWordsException("获取段落数量失败: " + e.getMessage(), e);
        }
    }

    /**
     * 获取表格数量
     */
    public static int getTableCount(XWPFDocument document) throws BoundesuWordsException {
        if (document == null) {
            throw new BoundesuWordsException("文档不能为空");
        }

        try {
            return document.getTables().size();
        } catch (Exception e) {
            log.error("获取表格数量失败: {}", e.getMessage(), e);
            throw new BoundesuWordsException("获取表格数量失败: " + e.getMessage(), e);
        }
    }

    /**
     * 设置字体
     */
    public static void setFont(XWPFRun run, String fontName, int fontSize) throws BoundesuWordsException {
        if (run == null) {
            throw new BoundesuWordsException("文本运行对象不能为空");
        }

        try {
            if (!StringUtils.isEmpty(fontName)) {
                run.setFontFamily(fontName);
            }
            if (fontSize > 0) {
                run.setFontSize(fontSize);
            }
            log.debug("设置字体成功: {} {}", fontName, fontSize);
        } catch (Exception e) {
            log.error("设置字体失败: {}", e.getMessage(), e);
            throw new BoundesuWordsException("设置字体失败: " + e.getMessage(), e);
        }
    }

    /**
     * 关闭文档
     */
    public static void closeDocument(XWPFDocument document) {
        if (document != null) {
            try {
                document.close();
                log.debug("文档关闭成功");
            } catch (IOException e) {
                log.warn("关闭文档时发生异常: {}", e.getMessage());
            }
        }
    }

    // ==================== 文档转换功能 ====================

    /**
     * 文档转换器类
     * 模拟 Aspose Words Document 类的转换功能
     */
    public static class DocumentConverter {
        private XWPFDocument document;
        private LoadOptions loadOptions;
        private SaveOptions saveOptions;
        
        /**
         * 构造函数
         * 
         * @param document 文档实例
         */
        public DocumentConverter(XWPFDocument document) {
            this.document = document;
        }
        
        /**
         * 设置加载选项
         * 
         * @param loadOptions 加载选项
         */
        public void setLoadOptions(LoadOptions loadOptions) {
            this.loadOptions = loadOptions;
        }
        
        /**
         * 设置保存选项
         * 
         * @param saveOptions 保存选项
         */
        public void setSaveOptions(SaveOptions saveOptions) {
            this.saveOptions = saveOptions;
        }
        
        /**
         * 保存文档
         * 
         * @param fileName 文件名
         * @throws BoundesuWordsException 保存异常
         */
        public void save(String fileName) throws BoundesuWordsException {
            if (StringUtils.isEmpty(fileName)) {
                throw new BoundesuWordsException("文件名不能为空");
            }
            
            String extension = getFileExtension(fileName);
            SaveFormat saveFormat = detectSaveFormatFromExtension(extension);
            save(fileName, saveFormat);
        }
        
        /**
         * 保存文档为指定格式
         * 
         * @param fileName 文件名
         * @param saveFormat 保存格式
         * @throws BoundesuWordsException 保存异常
         */
        public void save(String fileName, SaveFormat saveFormat) throws BoundesuWordsException {
            if (StringUtils.isEmpty(fileName)) {
                throw new BoundesuWordsException("文件名不能为空");
            }
            if (saveFormat == null) {
                throw new BoundesuWordsException("保存格式不能为空");
            }
            
            SaveOptions options = saveOptions != null ? saveOptions : createSaveOptionsForFormat(saveFormat);
            save(fileName, options);
        }
        
        /**
         * 保存文档使用保存选项
         * 
         * @param fileName 文件名
         * @param saveOptions 保存选项
         * @throws BoundesuWordsException 保存异常
         */
        public void save(String fileName, SaveOptions saveOptions) throws BoundesuWordsException {
            if (StringUtils.isEmpty(fileName)) {
                throw new BoundesuWordsException("文件名不能为空");
            }
            if (saveOptions == null) {
                throw new BoundesuWordsException("保存选项不能为空");
            }
            
            try {
                SaveFormat saveFormat = saveOptions.getSaveFormat();
                switch (saveFormat) {
                    case DOCX:
                        saveAsDocx(fileName, saveOptions);
                        break;
                    case PDF:
                        saveAsPdf(fileName, saveOptions);
                        break;
                    case HTML:
                        saveAsHtml(fileName, saveOptions);
                        break;
                    case TXT:
                        saveAsText(fileName, saveOptions);
                        break;
                    case RTF:
                        saveAsRtf(fileName, saveOptions);
                        break;
                    default:
                        throw new BoundesuWordsException("不支持的保存格式: " + saveFormat);
                }
                
                log.info("文档保存成功: {}", fileName);
            } catch (IOException e) {
                log.error("保存文档失败: {}", e.getMessage(), e);
                throw new BoundesuWordsException("保存文档失败: " + e.getMessage(), e);
            }
        }
        
        /**
         * 保存为DOCX格式
         */
        private void saveAsDocx(String fileName, SaveOptions saveOptions) throws IOException {
            try (FileOutputStream fos = new FileOutputStream(fileName)) {
                document.write(fos);
            }
        }
        
        /**
         * 保存为PDF格式（暂不支持）
         */
        private void saveAsPdf(String fileName, SaveOptions saveOptions) throws IOException {
            // POI不直接支持PDF导出，这里抛出异常
            throw new UnsupportedOperationException("PDF格式导出暂不支持，请使用第三方库如iText或Apache FOP");
        }
        
        /**
         * 保存为HTML格式（暂不支持）
         */
        private void saveAsHtml(String fileName, SaveOptions saveOptions) throws IOException {
            // POI不直接支持HTML导出
            throw new UnsupportedOperationException("HTML格式导出暂不支持");
        }
        
        /**
         * 保存为文本格式
         */
        private void saveAsText(String fileName, SaveOptions saveOptions) throws IOException, BoundesuWordsException {
            String text = getDocumentText(document);
            try (FileWriter writer = new FileWriter(fileName)) {
                writer.write(text);
            }
        }
        
        /**
         * 保存为RTF格式（暂不支持）
         */
        private void saveAsRtf(String fileName, SaveOptions saveOptions) throws IOException {
            // POI不直接支持RTF导出
            throw new UnsupportedOperationException("RTF格式导出暂不支持");
        }
        
        /**
         * 获取文件扩展名
         */
        private String getFileExtension(String fileName) {
            int lastDotIndex = fileName.lastIndexOf('.');
            return lastDotIndex > 0 ? fileName.substring(lastDotIndex + 1).toLowerCase() : "";
        }
    }

    // ==================== 文档转换静态方法 ====================

    /**
     * 创建文档转换器
     */
    public static DocumentConverter createConverter(XWPFDocument document) throws BoundesuWordsException {
        if (document == null) {
            throw new BoundesuWordsException("文档不能为空");
        }
        
        try {
            return new DocumentConverter(document);
        } catch (Exception e) {
            log.error("创建文档转换器失败: {}", e.getMessage(), e);
            throw new BoundesuWordsException("创建文档转换器失败: " + e.getMessage(), e);
        }
    }

    /**
     * 转换文档
     */
    public static void convertDocument(String inputFileName, String outputFileName) throws BoundesuWordsException {
        if (StringUtils.isEmpty(inputFileName)) {
            throw new BoundesuWordsException("输入文件名不能为空");
        }
        if (StringUtils.isEmpty(outputFileName)) {
            throw new BoundesuWordsException("输出文件名不能为空");
        }
        
        try {
            XWPFDocument document = loadXWPFDocument(inputFileName);
            DocumentConverter converter = createConverter(document);
            converter.save(outputFileName);
            closeDocument(document);
            
            log.info("文档转换完成: {} -> {}", inputFileName, outputFileName);
        } catch (Exception e) {
            log.error("文档转换失败: {}", e.getMessage(), e);
            throw new BoundesuWordsException("文档转换失败: " + e.getMessage(), e);
        }
    }

    /**
     * 转换文档为指定格式
     */
    public static void convertDocument(String inputFileName, String outputFileName, SaveFormat saveFormat) throws BoundesuWordsException {
        if (StringUtils.isEmpty(inputFileName)) {
            throw new BoundesuWordsException("输入文件名不能为空");
        }
        if (StringUtils.isEmpty(outputFileName)) {
            throw new BoundesuWordsException("输出文件名不能为空");
        }
        if (saveFormat == null) {
            throw new BoundesuWordsException("保存格式不能为空");
        }
        
        try {
            XWPFDocument document = loadXWPFDocument(inputFileName);
            DocumentConverter converter = createConverter(document);
            converter.save(outputFileName, saveFormat);
            closeDocument(document);
            
            log.info("文档转换完成: {} -> {} ({})", inputFileName, outputFileName, saveFormat);
        } catch (Exception e) {
            log.error("文档转换失败: {}", e.getMessage(), e);
            throw new BoundesuWordsException("文档转换失败: " + e.getMessage(), e);
        }
    }

    /**
     * 批量转换文档
     */
    public static void batchConvertDocuments(String inputDirectory, String outputDirectory, SaveFormat saveFormat) throws BoundesuWordsException {
        if (StringUtils.isEmpty(inputDirectory)) {
            throw new BoundesuWordsException("输入目录不能为空");
        }
        if (StringUtils.isEmpty(outputDirectory)) {
            throw new BoundesuWordsException("输出目录不能为空");
        }
        if (saveFormat == null) {
            throw new BoundesuWordsException("保存格式不能为空");
        }
        
        try {
            Path inputPath = Paths.get(inputDirectory);
            Path outputPath = Paths.get(outputDirectory);
            
            if (!Files.exists(inputPath) || !Files.isDirectory(inputPath)) {
                throw new BoundesuWordsException("输入目录不存在或不是目录: " + inputDirectory);
            }
            
            // 确保输出目录存在
            if (!Files.exists(outputPath)) {
                Files.createDirectories(outputPath);
            }
            
            // 获取支持的输入格式
            List<String> supportedFormats = getSupportedInputFormats();
            String outputExtension = getExtensionForFormat(saveFormat);
            
            // 遍历输入目录中的文件
            Files.walk(inputPath)
                .filter(Files::isRegularFile)
                .filter(path -> {
                    String fileName = path.getFileName().toString().toLowerCase();
                    return supportedFormats.stream().anyMatch(fileName::endsWith);
                })
                .forEach(inputFile -> {
                    try {
                        String fileName = inputFile.getFileName().toString();
                        String nameWithoutExt = fileName.substring(0, fileName.lastIndexOf('.'));
                        String outputFileName = nameWithoutExt + outputExtension;
                        Path outputFile = outputPath.resolve(outputFileName);
                        
                        convertDocument(inputFile.toString(), outputFile.toString(), saveFormat);
                        log.info("批量转换: {} -> {}", fileName, outputFileName);
                    } catch (Exception e) {
                        log.error("批量转换文件失败: {}", inputFile, e);
                    }
                });
                
            log.info("批量转换完成");
        } catch (Exception e) {
            log.error("批量转换失败: {}", e.getMessage(), e);
            throw new BoundesuWordsException("批量转换失败: " + e.getMessage(), e);
        }
    }

    /**
     * 合并文档
     */
    public static XWPFDocument mergeDocuments(XWPFDocument mainDocument, List<XWPFDocument> documentsToMerge) throws BoundesuWordsException {
        if (mainDocument == null) {
            throw new BoundesuWordsException("主文档不能为空");
        }
        if (documentsToMerge == null || documentsToMerge.isEmpty()) {
            throw new BoundesuWordsException("要合并的文档列表不能为空");
        }
        
        try {
            for (XWPFDocument docToMerge : documentsToMerge) {
                if (docToMerge != null) {
                    // 复制段落
                    for (XWPFParagraph paragraph : docToMerge.getParagraphs()) {
                        XWPFParagraph newParagraph = mainDocument.createParagraph();
                        copyParagraph(paragraph, newParagraph);
                    }
                    
                    // 复制表格
                    for (XWPFTable table : docToMerge.getTables()) {
                        copyTable(table, mainDocument);
                    }
                }
            }
            
            log.info("文档合并完成，合并了{}个文档", documentsToMerge.size());
            return mainDocument;
        } catch (Exception e) {
            log.error("合并文档失败: {}", e.getMessage(), e);
            throw new BoundesuWordsException("合并文档失败: " + e.getMessage(), e);
        }
    }

    /**
     * 复制段落
     */
    private static void copyParagraph(XWPFParagraph source, XWPFParagraph target) {
        for (XWPFRun sourceRun : source.getRuns()) {
            XWPFRun targetRun = target.createRun();
            targetRun.setText(sourceRun.getText(0));
            targetRun.setBold(sourceRun.isBold());
            targetRun.setItalic(sourceRun.isItalic());
            targetRun.setUnderline(sourceRun.getUnderline());
            if (sourceRun.getFontFamily() != null) {
                targetRun.setFontFamily(sourceRun.getFontFamily());
            }
            if (sourceRun.getFontSize() > 0) {
                targetRun.setFontSize(sourceRun.getFontSize());
            }
        }
    }

    /**
     * 复制表格
     */
    private static void copyTable(XWPFTable sourceTable, XWPFDocument targetDocument) {
        XWPFTable targetTable = targetDocument.createTable();
        
        for (int i = 0; i < sourceTable.getRows().size(); i++) {
            XWPFTableRow sourceRow = sourceTable.getRow(i);
            XWPFTableRow targetRow;
            
            if (i == 0) {
                targetRow = targetTable.getRow(0);
            } else {
                targetRow = targetTable.createRow();
            }
            
            for (int j = 0; j < sourceRow.getTableCells().size(); j++) {
                XWPFTableCell sourceCell = sourceRow.getCell(j);
                XWPFTableCell targetCell;
                
                if (j < targetRow.getTableCells().size()) {
                    targetCell = targetRow.getCell(j);
                } else {
                    targetCell = targetRow.addNewTableCell();
                }
                
                targetCell.setText(sourceCell.getText());
            }
        }
    }

    /**
     * 获取文档信息
     */
    public static Map<String, Object> getDocumentInfo(XWPFDocument document) throws BoundesuWordsException {
        if (document == null) {
            throw new BoundesuWordsException("文档不能为空");
        }
        
        try {
            Map<String, Object> info = new LinkedHashMap<>();
            
            // 基本信息
            info.put("paragraphCount", getParagraphCount(document));
            info.put("tableCount", getTableCount(document));
            
            // 文档属性
            if (document.getProperties().getCoreProperties().getTitle() != null) {
                info.put("title", document.getProperties().getCoreProperties().getTitle());
            }
            if (document.getProperties().getCoreProperties().getCreator() != null) {
                info.put("author", document.getProperties().getCoreProperties().getCreator());
            }
            if (document.getProperties().getCoreProperties().getSubject() != null) {
                info.put("subject", document.getProperties().getCoreProperties().getSubject());
            }
            if (document.getProperties().getCoreProperties().getKeywords() != null) {
                info.put("keywords", document.getProperties().getCoreProperties().getKeywords());
            }
            if (document.getProperties().getCoreProperties().getCreated() != null) {
                info.put("createdTime", document.getProperties().getCoreProperties().getCreated());
            }
            if (document.getProperties().getCoreProperties().getModified() != null) {
                info.put("lastModifiedTime", document.getProperties().getCoreProperties().getModified());
            }
            
            return info;
        } catch (Exception e) {
            log.error("获取文档信息失败: {}", e.getMessage(), e);
            throw new BoundesuWordsException("获取文档信息失败: " + e.getMessage(), e);
        }
    }

    /**
     * 检查是否为有效的文档格式
     */
    public static boolean isValidDocumentFormat(String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            return false;
        }
        
        String extension = getFileExtensionStatic(fileName).toLowerCase();
        List<String> supportedFormats = getSupportedInputFormats();
        return supportedFormats.contains("." + extension);
    }

    /**
     * 获取支持的输入格式
     */
    public static List<String> getSupportedInputFormats() {
        return Arrays.asList(".docx", ".doc");
    }

    /**
     * 获取支持的输出格式
     */
    public static List<String> getSupportedOutputFormats() {
        return Arrays.asList(".docx", ".txt");
    }

    /**
     * 根据保存格式获取文件扩展名
     */
    private static String getExtensionForFormat(SaveFormat saveFormat) {
        switch (saveFormat) {
            case DOCX:
                return ".docx";
            case PDF:
                return ".pdf";
            case HTML:
                return ".html";
            case TXT:
                return ".txt";
            case RTF:
                return ".rtf";
            default:
                return ".docx";
        }
    }

    /**
     * 获取文件扩展名（静态方法）
     */
    private static String getFileExtensionStatic(String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            return "";
        }
        
        int lastDotIndex = fileName.lastIndexOf('.');
        return lastDotIndex > 0 ? fileName.substring(lastDotIndex + 1) : "";
    }

    /**
     * 从文件名检测加载格式
     */
    private static LoadFormat detectLoadFormatFromFileName(String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            return LoadFormat.AUTO;
        }
        
        String extension = getFileExtensionStatic(fileName).toLowerCase();
        switch (extension) {
            case "docx":
                return LoadFormat.DOCX;
            case "doc":
                return LoadFormat.DOC;
            case "html":
            case "htm":
                return LoadFormat.HTML;
            case "xml":
                return LoadFormat.TXT;
            default:
                return LoadFormat.AUTO;
        }
    }

    /**
     * 从扩展名检测保存格式
     */
    private static SaveFormat detectSaveFormatFromExtension(String extension) {
        if (StringUtils.isEmpty(extension)) {
            return SaveFormat.DOCX;
        }
        
        switch (extension.toLowerCase()) {
            case "docx":
                return SaveFormat.DOCX;
            case "pdf":
                return SaveFormat.PDF;
            case "html":
            case "htm":
                return SaveFormat.HTML;
            case "txt":
                return SaveFormat.TXT;
            case "rtf":
                return SaveFormat.RTF;
            default:
                return SaveFormat.DOCX;
        }
    }

    /**
     * 为指定格式创建保存选项
     */
    private static SaveOptions createSaveOptionsForFormat(SaveFormat saveFormat) {
        switch (saveFormat) {
            case DOCX:
                return new DocxSaveOptions();
            case PDF:
                return new PdfSaveOptions();
            case HTML:
                return new DocxSaveOptions(SaveFormat.HTML);
            case TXT:
                return new DocxSaveOptions(SaveFormat.TXT);
            case RTF:
                return new DocxSaveOptions(SaveFormat.RTF);
            default:
                return new DocxSaveOptions();
        }
    }
}