package com.boundesu.words.core.util;

import com.boundesu.words.common.constants.BoundesuConstants;
import com.boundesu.words.common.constants.DocxConstants;
// import com.boundesu.words.common.constants.ErrorConstants; // 暂时注释掉，如果需要可以后续添加
import com.boundesu.words.common.exception.BoundesuWordsException;
import com.boundesu.words.common.util.StringUtils;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.Optional;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;

/**
 * 文档处理工具类
 * 基于Aspose Words Document API设计模式，使用POI实现核心功能
 * 提供与Aspose Words Document类相似的API接口
 * 
 * @author Boundesu Team
 * @version 1.0.0
 */
public class DocumentUtils {
    
    private static final Logger log = LoggerFactory.getLogger(DocumentUtils.class);
    
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
            // POI可能不支持setSubject方法，暂时注释
            // document.getProperties().getCoreProperties().setSubject(subject);
            log.warn("setSubject方法暂未实现");
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
        
        public XWPFDocument getDocument() {
            return document;
        }
    }
    
    /**
     * 页面设置类
     * 模拟Aspose Words PageSetup类
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
     * Document包装类
     * 模拟Aspose Words Document类的核心功能
     */
    public static class Document {
        private XWPFDocument document;
        private BuiltInDocumentProperties builtInDocumentProperties;
        private CustomDocumentProperties customDocumentProperties;
        private SectionCollection sections;
        
        /**
         * 创建空白文档
         * 模拟 Aspose Words Document() 构造函数
         */
        public Document() throws BoundesuWordsException {
            try {
                this.document = new XWPFDocument();
                this.builtInDocumentProperties = new BuiltInDocumentProperties(this.document);
                this.customDocumentProperties = new CustomDocumentProperties(this.document);
                this.sections = new SectionCollection(this.document);
                log.debug("创建新的空白文档成功");
            } catch (Exception e) {
                log.error("创建文档失败: {}", e.getMessage());
                throw new BoundesuWordsException("文档创建失败: " + e.getMessage(), e);
            }
        }
        
        /**
         * 从文件加载文档
         * 模拟 Aspose Words Document(String fileName) 构造函数
         */
        public Document(String fileName) throws BoundesuWordsException {
            if (StringUtils.isEmpty(fileName)) {
                throw new BoundesuWordsException("文件路径不能为空");
            }
            
            try {
                FileInputStream fis = new FileInputStream(fileName);
                this.document = new XWPFDocument(fis);
                fis.close();
                this.builtInDocumentProperties = new BuiltInDocumentProperties(this.document);
                this.customDocumentProperties = new CustomDocumentProperties(this.document);
                this.sections = new SectionCollection(this.document);
                log.debug("从文件加载文档成功: {}", fileName);
            } catch (Exception e) {
                log.error("加载文档失败: {}", e.getMessage());
                throw new BoundesuWordsException("文档加载失败: " + e.getMessage(), e);
            }
        }
        
        /**
         * 从输入流加载文档
         * 模拟 Aspose Words Document(InputStream stream) 构造函数
         */
        public Document(InputStream stream) throws BoundesuWordsException {
            if (stream == null) {
                throw new BoundesuWordsException("输入流不能为空");
            }
            
            try {
                this.document = new XWPFDocument(stream);
                this.builtInDocumentProperties = new BuiltInDocumentProperties(this.document);
                this.customDocumentProperties = new CustomDocumentProperties(this.document);
                this.sections = new SectionCollection(this.document);
                log.debug("从输入流加载文档成功");
            } catch (Exception e) {
                log.error("从输入流加载文档失败: {}", e.getMessage());
                throw new BoundesuWordsException("文档加载失败: " + e.getMessage(), e);
            }
        }
        
        /**
         * 使用加载选项从文件加载文档
         * 模拟 Aspose Words Document(String fileName, LoadOptions loadOptions) 构造函数
         */
        public Document(String fileName, com.boundesu.words.core.options.LoadOptions loadOptions) throws BoundesuWordsException {
            if (StringUtils.isEmpty(fileName)) {
                throw new BoundesuWordsException("文件路径不能为空");
            }
            
            try {
                FileInputStream fis = new FileInputStream(fileName);
                this.document = new XWPFDocument(fis);
                fis.close();
                this.builtInDocumentProperties = new BuiltInDocumentProperties(this.document);
                this.customDocumentProperties = new CustomDocumentProperties(this.document);
                this.sections = new SectionCollection(this.document);
                
                // 应用加载选项
                if (loadOptions != null) {
                    applyLoadOptions(loadOptions);
                }
                
                log.debug("使用加载选项从文件加载文档成功: {}", fileName);
            } catch (Exception e) {
                log.error("加载文档失败: {}", e.getMessage());
                throw new BoundesuWordsException("文档加载失败: " + e.getMessage(), e);
            }
        }
        
        /**
         * 使用加载选项从输入流加载文档
         * 模拟 Aspose Words Document(InputStream stream, LoadOptions loadOptions) 构造函数
         */
        public Document(InputStream stream, com.boundesu.words.core.options.LoadOptions loadOptions) throws BoundesuWordsException {
            if (stream == null) {
                throw new BoundesuWordsException("输入流不能为空");
            }
            
            try {
                this.document = new XWPFDocument(stream);
                this.builtInDocumentProperties = new BuiltInDocumentProperties(this.document);
                this.customDocumentProperties = new CustomDocumentProperties(this.document);
                this.sections = new SectionCollection(this.document);
                
                // 应用加载选项
                if (loadOptions != null) {
                    applyLoadOptions(loadOptions);
                }
                
                log.debug("使用加载选项从输入流加载文档成功");
            } catch (Exception e) {
                log.error("从输入流加载文档失败: {}", e.getMessage());
                throw new BoundesuWordsException("文档加载失败: " + e.getMessage(), e);
            }
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
         * 保存文档
         * 模拟 Aspose Words Document.save(String fileName) 方法
         */
        public void save(String fileName) throws BoundesuWordsException {
            if (StringUtils.isEmpty(fileName)) {
                throw new BoundesuWordsException("文件路径不能为空");
            }
            
            try {
                // 确保父目录存在
                Path path = Paths.get(fileName);
                if (path.getParent() != null) {
                    Files.createDirectories(path.getParent());
                }
                
                FileOutputStream fos = new FileOutputStream(fileName);
                document.write(fos);
                fos.close();
                log.info("文档保存成功: {}", fileName);
            } catch (Exception e) {
                log.error("保存文档失败: {}", e.getMessage());
                throw new BoundesuWordsException("文档保存失败: " + e.getMessage(), e);
            }
        }
        
        /**
         * 保存文档到输出流
         * 模拟 Aspose Words Document.save(OutputStream stream, SaveFormat saveFormat) 方法
         */
        public void save(OutputStream stream, com.boundesu.words.core.options.SaveOptions.SaveFormat saveFormat) throws BoundesuWordsException {
            if (stream == null) {
                throw new BoundesuWordsException("输出流不能为空");
            }
            
            try {
                document.write(stream);
                log.debug("文档保存到输出流成功");
            } catch (Exception e) {
                log.error("保存文档到输出流失败: {}", e.getMessage());
                throw new BoundesuWordsException("文档保存失败: " + e.getMessage(), e);
            }
        }
        
        /**
         * 使用保存选项保存文档
         * 模拟 Aspose Words Document.save(String fileName, SaveOptions saveOptions) 方法
         */
        public void save(String fileName, com.boundesu.words.core.options.SaveOptions saveOptions) throws BoundesuWordsException {
            if (StringUtils.isEmpty(fileName)) {
                throw new BoundesuWordsException("文件路径不能为空");
            }
            
            try {
                // 确保父目录存在
                Path path = Paths.get(fileName);
                if (path.getParent() != null) {
                    Files.createDirectories(path.getParent());
                }
                
                FileOutputStream fos = new FileOutputStream(fileName);
                document.write(fos);
                fos.close();
                
                // 应用保存选项（这里可以根据需要扩展）
                if (saveOptions != null) {
                    applySaveOptions(saveOptions);
                }
                
                log.info("使用保存选项保存文档成功: {}", fileName);
            } catch (Exception e) {
                log.error("保存文档失败: {}", e.getMessage());
                throw new BoundesuWordsException("文档保存失败: " + e.getMessage(), e);
            }
        }
        
        /**
         * 获取页数
         * 模拟 Aspose Words Document.getPageCount() 方法
         */
        public int getPageCount() {
            try {
                // POI没有直接的页数获取方法，这里提供一个估算
                int paragraphCount = document.getParagraphs().size();
                int tableCount = document.getTables().size();
                // 简单估算：每页约25个段落或5个表格
                return Math.max(1, (paragraphCount + tableCount * 5) / 25);
            } catch (Exception e) {
                log.error("获取页数失败: {}", e.getMessage());
                return 1;
            }
        }
        
        /**
         * 获取底层POI文档对象
         */
        public XWPFDocument getXWPFDocument() {
            return document;
        }
        
        /**
         * 应用加载选项
         */
        private void applyLoadOptions(com.boundesu.words.core.options.LoadOptions loadOptions) {
            // 这里可以根据加载选项进行相应的处理
            // 由于POI的限制，某些选项可能无法完全实现
            log.debug("应用加载选项: {}", loadOptions.getClass().getSimpleName());
        }
        
        /**
         * 应用保存选项
         */
        private void applySaveOptions(com.boundesu.words.core.options.SaveOptions saveOptions) {
            // 这里可以根据保存选项进行相应的处理
            log.debug("应用保存选项: {}", saveOptions.getClass().getSimpleName());
        }
    }
    
    // 私有构造函数，防止实例化
    private DocumentUtils() {
        throw new UnsupportedOperationException("工具类不能被实例化");
    }
    
    /**
     * 创建新的空白文档
     * 模拟 Aspose Words Document() 构造函数
     * 
     * @return 新的XWPFDocument实例
     */
    public static XWPFDocument createDocument() throws BoundesuWordsException {
        try {
            XWPFDocument document = new XWPFDocument();
            log.debug("创建新的空白文档成功");
            return document;
        } catch (Exception e) {
            log.error("创建文档失败: {}", e.getMessage());
            throw new BoundesuWordsException("文档创建失败: " + e.getMessage(), e);
        }
    }
    
    /**
     * 从文件加载文档
     * 模拟 Aspose Words Document(String fileName) 构造函数
     * 
     * @param filePath 文件路径
     * @return XWPFDocument实例
     */
    public static XWPFDocument loadDocument(String filePath) throws BoundesuWordsException {
        if (StringUtils.isEmpty(filePath)) {
            throw new BoundesuWordsException("文件路径不能为空");
        }
        
        try {
            FileInputStream fis = new FileInputStream(filePath);
            XWPFDocument document = new XWPFDocument(fis);
            fis.close();
            log.debug("从文件加载文档成功: {}", filePath);
            return document;
        } catch (Exception e) {
            log.error("加载文档失败: {}", e.getMessage());
            throw new BoundesuWordsException("文档加载失败: " + e.getMessage(), e);
        }
    }
    
    /**
     * 从输入流加载文档
     * 模拟 Aspose Words Document(InputStream stream) 构造函数
     * 
     * @param inputStream 输入流
     * @return XWPFDocument实例
     */
    public static XWPFDocument loadDocument(InputStream inputStream) throws BoundesuWordsException {
        if (inputStream == null) {
            throw new BoundesuWordsException("输入流不能为空");
        }
        
        try {
            XWPFDocument document = new XWPFDocument(inputStream);
            log.debug("从输入流加载文档成功");
            return document;
        } catch (Exception e) {
            log.error("从输入流加载文档失败: {}", e.getMessage());
            throw new BoundesuWordsException("文档加载失败: " + e.getMessage(), e);
        }
    }
    
    /**
     * 保存文档到文件
     * 模拟 Aspose Words Document.save(String fileName) 方法
     * 
     * @param document 文档实例
     * @param filePath 输出文件路径
     */
    public static void saveDocument(XWPFDocument document, String filePath) throws BoundesuWordsException {
        if (document == null) {
            throw new BoundesuWordsException("文档不能为空");
        }
        if (StringUtils.isEmpty(filePath)) {
            throw new BoundesuWordsException("文件路径不能为空");
        }
        
        try {
            // 确保父目录存在
            Path path = Paths.get(filePath);
            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }
            
            FileOutputStream fos = new FileOutputStream(filePath);
            document.write(fos);
            fos.close();
            log.info("文档保存成功: {}", filePath);
        } catch (Exception e) {
            log.error("保存文档失败: {}", e.getMessage());
            throw new BoundesuWordsException("文档保存失败: " + e.getMessage(), e);
        }
    }
    
    /**
     * 保存文档到输出流
     * 模拟 Aspose Words Document.save(OutputStream stream, int saveFormat) 方法
     * 
     * @param document 文档实例
     * @param outputStream 输出流
     */
    public static void saveDocument(XWPFDocument document, OutputStream outputStream) throws BoundesuWordsException {
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
            log.error("保存文档到输出流失败: {}", e.getMessage());
            throw new BoundesuWordsException("文档保存失败: " + e.getMessage(), e);
        }
    }
    
    /**
     * 获取文档文本内容
     * 模拟 Aspose Words Document.getText() 方法
     * 
     * @param document 文档实例
     * @return 文本内容
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
     * 设置文档属性
     * 模拟 Aspose Words Document.getBuiltInDocumentProperties() 功能
     * 
     * @param document 文档实例
     * @param title 标题
     * @param author 作者
     * @param subject 主题
     * @param keywords 关键词
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
            
            // 设置创建时间和修改时间
            Date now = new Date();
            document.getProperties().getCoreProperties().setCreated(Optional.of(now));
            document.getProperties().getCoreProperties().setModified(Optional.of(now));
            
            log.debug("文档属性设置完成");
        } catch (Exception e) {
            log.error("设置文档属性失败: {}", e.getMessage());
            throw new BoundesuWordsException("设置文档属性失败: " + e.getMessage(), e);
        }
    }
    
    /**
     * 插入段落
     * 模拟 Aspose Words DocumentBuilder.writeln(String text) 方法
     * 
     * @param document 文档实例
     * @param text 段落文本
     * @return 创建的段落
     */
    public static XWPFParagraph insertParagraph(XWPFDocument document, String text) throws BoundesuWordsException {
        if (document == null) {
            throw new BoundesuWordsException("文档不能为空");
        }
        
        try {
            XWPFParagraph paragraph = document.createParagraph();
            if (!StringUtils.isEmpty(text)) {
                XWPFRun run = paragraph.createRun();
                run.setText(text);
            }
            log.debug("插入段落成功");
            return paragraph;
        } catch (Exception e) {
            log.error("插入段落失败: {}", e.getMessage());
            throw new BoundesuWordsException("插入段落失败: " + e.getMessage(), e);
        }
    }
    
    /**
     * 插入标题
     * 模拟 Aspose Words DocumentBuilder 标题样式功能
     * 
     * @param document 文档实例
     * @param text 标题文本
     * @param level 标题级别 (1-6)
     * @return 创建的段落
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
            
            if (!StringUtils.isEmpty(text)) {
                XWPFRun run = paragraph.createRun();
                run.setText(text);
                
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
                        run.setFontSize(11);
                        break;
                    case 6:
                        run.setFontSize(10);
                        break;
                }
                
                // 设置段落样式
                paragraph.setStyle("Heading" + level);
            }
            
            log.debug("插入{}级标题成功: {}", level, text);
            return paragraph;
        } catch (Exception e) {
            log.error("插入标题失败: {}", e.getMessage());
            throw new BoundesuWordsException("插入标题失败: " + e.getMessage(), e);
        }
    }
    
    /**
     * 插入分页符
     * 模拟 Aspose Words DocumentBuilder.insertBreak(BreakType.PAGE_BREAK) 方法
     * 
     * @param document 文档实例
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
            log.error("插入分页符失败: {}", e.getMessage());
            throw new BoundesuWordsException("插入分页符失败: " + e.getMessage(), e);
        }
    }
    
    /**
     * 创建表格
     * 模拟 Aspose Words DocumentBuilder 表格创建功能
     * 
     * @param document 文档实例
     * @param rows 行数
     * @param cols 列数
     * @return 创建的表格
     */
    public static XWPFTable createTable(XWPFDocument document, int rows, int cols) throws BoundesuWordsException {
        if (document == null) {
            throw new BoundesuWordsException("文档不能为空");
        }
        
        if (rows <= 0 || cols <= 0) {
            throw new BoundesuWordsException("行数和列数必须大于0");
        }
        
        try {
            XWPFTable table = document.createTable(rows, cols);
            
            // 设置表格样式
            table.setWidth("100%");
            
            log.debug("创建表格成功: {}行{}列", rows, cols);
            return table;
        } catch (Exception e) {
            log.error("创建表格失败: {}", e.getMessage());
            throw new BoundesuWordsException("创建表格失败: " + e.getMessage(), e);
        }
    }
    
    /**
     * 设置表格单元格文本
     * 
     * @param table 表格实例
     * @param row 行索引
     * @param col 列索引
     * @param text 文本内容
     */
    public static void setTableCellText(XWPFTable table, int row, int col, String text) throws BoundesuWordsException {
        if (table == null) {
            throw new BoundesuWordsException("表格不能为空");
        }
        
        try {
            XWPFTableRow tableRow = table.getRow(row);
            if (tableRow != null) {
                XWPFTableCell cell = tableRow.getCell(col);
                if (cell != null) {
                    cell.setText(text != null ? text : "");
                }
            }
            log.debug("设置表格单元格文本成功: [{},{}] = {}", row, col, text);
        } catch (Exception e) {
            log.error("设置表格单元格文本失败: {}", e.getMessage());
            throw new BoundesuWordsException("设置表格单元格文本失败: " + e.getMessage(), e);
        }
    }
    
    /**
     * 合并多个文档
     * 模拟 Aspose Words Document.appendDocument() 方法
     * 
     * @param targetDocument 目标文档
     * @param sourceDocuments 源文档列表
     */
    public static void mergeDocuments(XWPFDocument targetDocument, List<XWPFDocument> sourceDocuments) throws BoundesuWordsException {
        if (targetDocument == null) {
            throw new BoundesuWordsException("目标文档不能为空");
        }
        if (sourceDocuments == null || sourceDocuments.isEmpty()) {
            throw new BoundesuWordsException("源文档列表不能为空");
        }
        
        try {
            for (XWPFDocument sourceDoc : sourceDocuments) {
                if (sourceDoc != null) {
                    // 复制段落
                    for (XWPFParagraph paragraph : sourceDoc.getParagraphs()) {
                        XWPFParagraph newParagraph = targetDocument.createParagraph();
                        copyParagraph(paragraph, newParagraph);
                    }
                    
                    // 复制表格
                    for (XWPFTable table : sourceDoc.getTables()) {
                        copyTable(table, targetDocument);
                    }
                }
            }
            log.info("文档合并完成，合并了{}个文档", sourceDocuments.size());
        } catch (Exception e) {
            log.error("合并文档失败: {}", e.getMessage());
            throw new BoundesuWordsException("合并文档失败: " + e.getMessage(), e);
        }
    }
    
    /**
     * 复制段落
     * 
     * @param source 源段落
     * @param target 目标段落
     */
    private static void copyParagraph(XWPFParagraph source, XWPFParagraph target) {
        try {
            for (XWPFRun run : source.getRuns()) {
                XWPFRun newRun = target.createRun();
                newRun.setText(run.getText(0));
                
                // 复制格式
                if (run.isBold()) newRun.setBold(true);
                if (run.isItalic()) newRun.setItalic(true);
                if (run.getFontSize() != -1) newRun.setFontSize(run.getFontSize());
                if (run.getFontFamily() != null) newRun.setFontFamily(run.getFontFamily());
            }
        } catch (Exception e) {
            log.warn("复制段落时出现警告: {}", e.getMessage());
        }
    }
    
    /**
     * 复制表格
     * 
     * @param sourceTable 源表格
     * @param targetDocument 目标文档
     */
    private static void copyTable(XWPFTable sourceTable, XWPFDocument targetDocument) {
        try {
            int rows = sourceTable.getRows().size();
            int cols = sourceTable.getRow(0).getTableCells().size();
            
            XWPFTable newTable = targetDocument.createTable(rows, cols);
            
            for (int i = 0; i < rows; i++) {
                XWPFTableRow sourceRow = sourceTable.getRow(i);
                XWPFTableRow targetRow = newTable.getRow(i);
                
                for (int j = 0; j < cols && j < sourceRow.getTableCells().size(); j++) {
                    XWPFTableCell sourceCell = sourceRow.getCell(j);
                    XWPFTableCell targetCell = targetRow.getCell(j);
                    targetCell.setText(sourceCell.getText());
                }
            }
        } catch (Exception e) {
            log.warn("复制表格时出现警告: {}", e.getMessage());
        }
    }
    
    /**
     * 查找并替换文本
     * 模拟 Aspose Words Range.replace() 方法
     * 
     * @param document 文档实例
     * @param searchText 搜索文本
     * @param replaceText 替换文本
     * @return 替换次数
     */
    public static int findAndReplace(XWPFDocument document, String searchText, String replaceText) throws BoundesuWordsException {
        if (document == null) {
            throw new BoundesuWordsException("文档不能为空");
        }
        
        if (StringUtils.isEmpty(searchText)) {
            return 0;
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
            log.error("查找并替换失败: {}", e.getMessage());
            throw new BoundesuWordsException("查找并替换失败: " + e.getMessage(), e);
        }
    }
    
    /**
     * 获取文档段落数量
     * 
     * @param document 文档实例
     * @return 段落数量
     */
    public static int getParagraphCount(XWPFDocument document) throws BoundesuWordsException {
        if (document == null) {
            throw new BoundesuWordsException("文档不能为空");
        }
        
        return document.getParagraphs().size();
    }
    
    /**
     * 获取文档表格数量
     * 
     * @param document 文档实例
     * @return 表格数量
     */
    public static int getTableCount(XWPFDocument document) throws BoundesuWordsException {
        if (document == null) {
            throw new BoundesuWordsException("文档不能为空");
        }
        
        return document.getTables().size();
    }
    
    /**
     * 设置默认字体
     * 模拟 Aspose Words DocumentBuilder.getFont() 功能
     * 
     * @param run 文本运行实例
     * @param fontName 字体名称
     * @param fontSize 字体大小
     */
    public static void setFont(XWPFRun run, String fontName, int fontSize) throws BoundesuWordsException {
        if (run == null) {
            throw new BoundesuWordsException("文本运行实例不能为空");
        }
        
        try {
            if (!StringUtils.isEmpty(fontName)) {
                run.setFontFamily(fontName);
            } else {
                run.setFontFamily(DocxConstants.DEFAULT_FONT_FAMILY);
            }
            
            if (fontSize > 0) {
                run.setFontSize(fontSize);
            } else {
                run.setFontSize(DocxConstants.DEFAULT_FONT_SIZE);
            }
            
            log.debug("字体设置完成: {}, {}", fontName, fontSize);
        } catch (Exception e) {
            log.error("设置字体失败: {}", e.getMessage());
            throw new BoundesuWordsException("设置字体失败: " + e.getMessage(), e);
        }
    }
    
    /**
     * 关闭文档
     * 模拟资源清理
     * 
     * @param document 文档实例
     */
    public static void closeDocument(XWPFDocument document) {
        if (document != null) {
            try {
                document.close();
                log.debug("文档关闭成功");
            } catch (Exception e) {
                log.warn("关闭文档时出现警告: {}", e.getMessage());
            }
        }
    }
}