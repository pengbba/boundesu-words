package com.boundesu.words;

import com.boundesu.words.common.creator.DocumentCreator;
import com.boundesu.words.common.exception.BoundesuWordsException;
import com.boundesu.words.common.util.DocumentValidator;
import com.boundesu.words.common.util.PerformanceMonitor;
import com.boundesu.words.core.advanced.AdvancedDocumentGenerator;
import com.boundesu.words.core.creator.DocumentCreatorFactory;
import com.boundesu.words.html.converter.HtmlToDocxConverter;
import com.boundesu.words.html.parser.HtmlContentParser;
import com.boundesu.words.xml.parser.XmlContentParser;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Boundesu Words SDK 主入口类
 * 提供统一的API接口来使用所有模块功能
 *
 * @author Boundesu Team
 * @version 1.0.0
 */
public class BoundesuWordsSDK {

    /**
     * SDK版本号
     */
    public static final String VERSION = "1.0.0";

    /**
     * 创建文档创建器
     *
     * @param type 创建器类型 ("poi", "html", "xml")
     * @return 文档创建器实例
     */
    public static DocumentCreator createDocumentCreator(String type) {
        return DocumentCreatorFactory.createDocumentCreator(type);
    }

    /**
     * 创建文档创建器
     *
     * @param type 创建器类型枚举
     * @return 文档创建器实例
     */
    public static DocumentCreator createDocumentCreator(DocumentCreatorFactory.CreatorType type) {
        return DocumentCreatorFactory.createDocumentCreator(type);
    }

    /**
     * 创建高级文档生成器
     *
     * @return 高级文档生成器实例
     */
    public static AdvancedDocumentGenerator createAdvancedGenerator() {
        return new AdvancedDocumentGenerator();
    }

    /**
     * 创建高级文档生成器
     *
     * @param creatorType 底层创建器类型
     * @return 高级文档生成器实例
     */
    public static AdvancedDocumentGenerator createAdvancedGenerator(DocumentCreatorFactory.CreatorType creatorType) {
        return new AdvancedDocumentGenerator(creatorType);
    }

    /**
     * 快速创建简单文档
     *
     * @param title    文档标题
     * @param author   文档作者
     * @param content  文档内容
     * @param filePath 输出文件路径
     * @throws IOException 文件操作异常
     */
    public static void createSimpleDocument(String title, String author, String content, Path filePath) throws IOException {
        DocumentCreator creator = createDocumentCreator("poi");
        creator.setTitle(title)
                .setAuthor(author)
                .addHeading(title, 1)
                .addParagraph(content)
                .createDocument(filePath);
    }

    /**
     * 快速创建报告文档
     *
     * @param title    报告标题
     * @param author   报告作者
     * @param summary  摘要
     * @param chapters 章节内容
     * @param filePath 输出文件路径
     * @throws IOException 文件操作异常
     */
    public static void createReportDocument(String title, String author, String summary,
                                            Map<String, String> chapters, Path filePath) throws IOException {
        AdvancedDocumentGenerator generator = createAdvancedGenerator();
        generator.generateReport(title, author, summary, chapters);
        generator.createDocument(filePath);
    }

    /**
     * 从HTML创建文档
     *
     * @param htmlContent HTML内容
     * @param filePath    输出文件路径
     * @throws IOException 文件操作异常
     */
    public static void createDocumentFromHtml(String htmlContent, Path filePath) throws IOException {
        DocumentCreator creator = createDocumentCreator("html");

        // 解析HTML并生成文档结构
        List<HtmlContentParser.DocumentStructure> structures = HtmlContentParser.parseHtmlContent(htmlContent);

        if (!structures.isEmpty() && structures.get(0).getTitle() != null && !structures.get(0).getTitle().isEmpty()) {
            creator.setTitle(structures.get(0).getTitle());
        }

        for (HtmlContentParser.DocumentStructure structure : structures) {
            addStructureToCreator(creator, structure);
        }
        creator.createDocument(filePath);
    }

    /**
     * 将HTML输入流转换为DOCX文档并保存到指定路径
     *
     * @param htmlInputStream HTML输入流
     * @param outputPath      输出文件路径
     * @throws IOException            文件操作异常
     * @throws BoundesuWordsException 转换异常
     */
    public static void convertHtmlToDocx(InputStream htmlInputStream, Path outputPath) throws IOException, BoundesuWordsException {
        convertHtmlToDocx(htmlInputStream, outputPath, null);
    }

    /**
     * 将HTML输入流转换为DOCX文档并保存到指定路径，支持设置页边距
     *
     * @param htmlInputStream HTML输入流
     * @param outputPath      输出文件路径
     * @param margins         页边距设置（可为null使用默认值）
     * @throws IOException            文件操作异常
     * @throws BoundesuWordsException 转换异常
     */
    public static void convertHtmlToDocx(InputStream htmlInputStream, Path outputPath, HtmlToDocxConverter.PageMargins margins) throws IOException, BoundesuWordsException {
        HtmlToDocxConverter converter = new HtmlToDocxConverter();
        com.boundesu.words.common.model.Document document = converter.convertHtmlToDocx(htmlInputStream, margins);
        document.saveToFile(outputPath.toString());
    }

    /**
     * 将HTML内容转换为DOCX文档并保存到指定路径，支持设置页边距
     *
     * @param htmlContent HTML内容字符串
     * @param outputPath  输出文件路径
     * @param margins     页边距设置（可为null使用默认值）
     * @throws IOException            文件操作异常
     * @throws BoundesuWordsException 转换异常
     */
    public static void convertHtmlToDocx(String htmlContent, Path outputPath, HtmlToDocxConverter.PageMargins margins) throws IOException, BoundesuWordsException {
        HtmlToDocxConverter converter = new HtmlToDocxConverter();
        com.boundesu.words.common.model.Document document = converter.convertHtmlToDocx(htmlContent, margins);
        document.saveToFile(outputPath.toString());
    }

    /**
     * 从XML创建文档
     *
     * @param xmlContent XML内容
     * @param filePath   输出文件路径
     * @throws IOException 文件操作异常
     */
    public static void createDocumentFromXml(String xmlContent, Path filePath) throws IOException {
        DocumentCreator creator = createDocumentCreator("xml");

        // 解析XML并生成文档结构
        List<XmlContentParser.XmlDocumentStructure> structures = XmlContentParser.parseXmlContent(xmlContent);

        if (!structures.isEmpty()) {
            XmlContentParser.XmlDocumentStructure firstStructure = structures.get(0);
            if (firstStructure.getTagName() != null && !firstStructure.getTagName().isEmpty()) {
                creator.setTitle(firstStructure.getTagName());
            }

            for (XmlContentParser.XmlDocumentStructure structure : structures) {
                addXmlStructureToCreator(creator, structure);
            }
        }
        creator.createDocument(filePath);
    }

    /**
     * 验证文档参数
     *
     * @param title    文档标题
     * @param filePath 文件路径
     * @return 验证结果
     */
    public static boolean validateDocumentParameters(String title, String filePath) {
        try {
            DocumentValidator.validateDocumentTitle(title);
            DocumentValidator.validateFilePath(java.nio.file.Paths.get(filePath));
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * 获取支持的创建器类型
     *
     * @return 支持的创建器类型数组
     */
    public static DocumentCreatorFactory.CreatorType[] getSupportedCreatorTypes() {
        return DocumentCreatorFactory.getSupportedTypes();
    }

    /**
     * 检查创建器类型是否支持
     *
     * @param type 创建器类型字符串
     * @return 是否支持
     */
    public static boolean isCreatorTypeSupported(String type) {
        return DocumentCreatorFactory.isSupported(type);
    }

    /**
     * 获取创建器类型描述
     *
     * @param type 创建器类型
     * @return 描述信息
     */
    public static String getCreatorTypeDescription(DocumentCreatorFactory.CreatorType type) {
        return DocumentCreatorFactory.getTypeDescription(type);
    }

    /**
     * 创建性能监控器
     *
     * @return 性能监控器实例
     */
    public static PerformanceMonitor.OperationContext createPerformanceMonitor() {
        return PerformanceMonitor.startOperation("Document Creation");
    }

    /**
     * 监控文档创建性能
     *
     * @param operation 文档创建操作
     * @param <T>       返回类型
     * @return 操作结果
     * @throws Exception 操作异常
     */
    public static <T> T monitorDocumentCreation(PerformanceMonitor.MonitoredOperation<T> operation) throws Exception {
        return PerformanceMonitor.monitor("Document Creation", operation);
    }

    /**
     * 获取SDK信息
     *
     * @return SDK信息映射
     */
    public static Map<String, Object> getSDKInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("version", VERSION);
        info.put("name", "Boundesu Words SDK");
        info.put("description", "Self-implemented document processing SDK");
        info.put("supportedFormats", new String[]{"DOCX"});
        info.put("supportedInputs", new String[]{"Text", "HTML", "XML"});
        info.put("creatorTypes", getSupportedCreatorTypes());
        return info;
    }

    /**
     * 将HTML文档结构添加到创建器
     *
     * @param creator   文档创建器
     * @param structure HTML文档结构
     */
    private static void addStructureToCreator(DocumentCreator creator, HtmlContentParser.DocumentStructure structure) {
        if (structure.getContent() != null && !structure.getContent().isEmpty()) {
            creator.addHeading(structure.getTitle(), structure.getLevel());
            creator.addParagraph(structure.getContent());
        }

        if (structure.getChildren() != null) {
            for (HtmlContentParser.DocumentStructure child : structure.getChildren()) {
                addStructureToCreator(creator, child);
            }
        }
    }

    /**
     * 将XML文档结构添加到创建器
     *
     * @param creator   文档创建器
     * @param structure XML文档结构
     */
    private static void addXmlStructureToCreator(DocumentCreator creator, XmlContentParser.XmlDocumentStructure structure) {
        if (structure.getContent() != null && !structure.getContent().isEmpty()) {
            creator.addHeading(structure.getTagName(), structure.getLevel());
            creator.addParagraph(structure.getContent());
        }

        if (structure.getChildren() != null) {
            for (XmlContentParser.XmlDocumentStructure child : structure.getChildren()) {
                addXmlStructureToCreator(creator, child);
            }
        }
    }

    /**
     * 工具类，提供便捷的文档创建方法
     */
    public static class Utils {

        /**
         * 创建技术文档
         *
         * @param title        文档标题
         * @param author       文档作者
         * @param introduction 介绍
         * @param sections     技术章节
         * @param conclusion   结论
         * @param filePath     输出文件路径
         * @throws IOException 文件操作异常
         */
        public static void createTechnicalDocument(String title, String author, String introduction,
                                                   Map<String, String> sections, String conclusion, Path filePath) throws IOException {
            AdvancedDocumentGenerator generator = createAdvancedGenerator();
            generator.generateTechnicalDocument(title, author, introduction, sections, conclusion);
            generator.createDocument(filePath);
        }

        /**
         * 创建会议纪要
         *
         * @param meetingTitle 会议标题
         * @param date         会议日期
         * @param attendees    参会人员
         * @param agenda       议程
         * @param decisions    决议
         * @param actionItems  行动项
         * @param filePath     输出文件路径
         * @throws IOException 文件操作异常
         */
        public static void createMeetingMinutes(String meetingTitle, String date, List<String> attendees,
                                                List<String> agenda, List<String> decisions, List<String> actionItems, Path filePath) throws IOException {
            AdvancedDocumentGenerator generator = createAdvancedGenerator();
            generator.generateMeetingMinutes(meetingTitle, date, attendees, agenda, decisions, actionItems);
            generator.createDocument(filePath);
        }

        /**
         * 批量创建文档
         *
         * @param documents 文档信息列表
         * @throws IOException 文件操作异常
         */
        public static void batchCreateDocuments(List<DocumentInfo> documents) throws IOException {
            for (DocumentInfo doc : documents) {
                createSimpleDocument(doc.getTitle(), doc.getAuthor(), doc.getContent(), doc.getFilePath());
            }
        }
    }

    /**
     * 文档信息类
     */
    public static class DocumentInfo {
        private String title;
        private String author;
        private String content;
        private Path filePath;

        public DocumentInfo(String title, String author, String content, Path filePath) {
            this.title = title;
            this.author = author;
            this.content = content;
            this.filePath = filePath;
        }

        // Getters
        public String getTitle() {
            return title;
        }

        // Setters
        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Path getFilePath() {
            return filePath;
        }

        public void setFilePath(Path filePath) {
            this.filePath = filePath;
        }
    }
}