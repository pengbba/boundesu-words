package com.boundesu.words.sdk;

import com.boundesu.words.sdk.model.*;
import com.boundesu.words.sdk.service.BoundesuDocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Boundesu Words JDK17 SDK - 主入口类
 * 
 * @author Boundesu
 * @version 1.0.0
 */
public class BoundesuWordsSDK {

    private static final Logger log = LoggerFactory.getLogger(BoundesuWordsSDK.class);

    private final BoundesuDocumentService documentService;
    
    /**
     * 构造函数
     */
    public BoundesuWordsSDK() {
        this.documentService = new BoundesuDocumentService();
    }
    
    /**
     * 获取SDK版本信息
     */
    public static String getVersion() {
        return "1.0.0";
    }
    
    /**
     * 获取SDK信息
     */
    public static Map<String, String> getSDKInfo() {
        Map<String, String> info = new LinkedHashMap<>();
        info.put("name", "Boundesu Words SDK");
        info.put("version", getVersion());
        info.put("author", "Boundesu Team");
        info.put("description", "自主研发的文档处理SDK，不依赖第三方库");
        info.put("jdk", "8+");
        info.put("license", "MIT");
        return info;
    }
    
    // ==================== 文档创建方法 ====================
    
    /**
     * 创建新文档
     */
    public BoundesuDocument createDocument() {
        return documentService.createDocument();
    }
    
    /**
     * 创建带标题和作者的文档
     */
    public BoundesuDocument createDocument(String title, String author) {
        return documentService.createDocument(title, author);
    }
    
    // ==================== 内容添加方法 ====================
    
    /**
     * 添加段落
     */
    public BoundesuDocument addParagraph(BoundesuDocument document, String text) {
        return documentService.addParagraph(document, text);
    }
    
    /**
     * 添加带样式的段落
     */
    public BoundesuDocument addParagraph(BoundesuDocument document, String text, BoundesuParagraphStyle style) {
        return documentService.addParagraph(document, text, style);
    }
    
    /**
     * 添加标题
     */
    public BoundesuDocument addHeading(BoundesuDocument document, String text, int level) {
        return documentService.addHeading(document, text, level);
    }
    
    /**
     * 添加表格
     */
    public BoundesuDocument addTable(BoundesuDocument document, int rows, int columns) {
        return documentService.addTable(document, rows, columns);
    }
    
    /**
     * 设置表格数据
     */
    public BoundesuDocument setTableData(BoundesuDocument document, int tableIndex, String[][] data) {
        return documentService.setTableData(document, tableIndex, data);
    }
    
    // ==================== 样式设置方法 ====================
    
    /**
     * 设置文档样式
     */
    public BoundesuDocument setDocumentStyle(BoundesuDocument document, BoundesuDocumentStyle style) {
        return documentService.setDocumentStyle(document, style);
    }
    
    /**
     * 设置页眉
     */
    public BoundesuDocument setHeader(BoundesuDocument document, String text) {
        return documentService.setHeader(document, text);
    }
    
    /**
     * 设置页脚
     */
    public BoundesuDocument setFooter(BoundesuDocument document, String text) {
        return documentService.setFooter(document, text);
    }
    
    // ==================== 文档保存方法 ====================
    
    /**
     * 保存为HTML格式
     */
    public String saveAsHtml(BoundesuDocument document, String filePath) throws IOException {
        return documentService.saveAsHtml(document, filePath);
    }
    
    /**
     * 保存为文本格式
     */
    public String saveAsText(BoundesuDocument document, String filePath) throws IOException {
        return documentService.saveAsText(document, filePath);
    }
    
    // ==================== 工具方法 ====================
    
    
    /**
     * 创建段落样式构建器
     */
    public static BoundesuParagraphStyle.BoundesuParagraphStyleBuilder createParagraphStyle() {
        return BoundesuParagraphStyle.builder();
    }
    
    /**
     * 创建文档样式构建器
     */
    public static BoundesuDocumentStyle.BoundesuDocumentStyleBuilder createDocumentStyle() {
        return BoundesuDocumentStyle.builder();
    }
    
    /**
     * 创建表格样式构建器
     */
    public static BoundesuTableStyle.BoundesuTableStyleBuilder createTableStyle() {
        return BoundesuTableStyle.builder();
    }
    
    /**
     * 创建页眉构建器
     */
    public static BoundesuHeader.BoundesuHeaderBuilder createHeader() {
        return BoundesuHeader.builder();
    }
    
    /**
     * 创建页脚构建器
     */
    public static BoundesuFooter.BoundesuFooterBuilder createFooter() {
        return BoundesuFooter.builder();
    }
    
    // ==================== 快捷方法 ====================
    
    /**
     * 快速创建简单文档
     */
    public BoundesuDocument quickCreateDocument(String title, String content) {
        BoundesuDocument document = createDocument(title, "Boundesu SDK User");
        addParagraph(document, content);
        return document;
    }
    
    /**
     * 快速创建报告文档
     */
    public BoundesuDocument quickCreateReport(String title, String author, String[] sections, String[] contents) {
        if (sections.length != contents.length) {
            throw new IllegalArgumentException("章节标题和内容数量不匹配");
        }
        
        BoundesuDocument document = createDocument(title, author);
        
        // 设置专业样式
        BoundesuDocumentStyle style = createDocumentStyle()
                .theme(BoundesuDocumentStyle.DocumentTheme.PROFESSIONAL)
                .build()
                .applyTheme(BoundesuDocumentStyle.DocumentTheme.PROFESSIONAL);
        setDocumentStyle(document, style);
        
        // 添加主标题
        addHeading(document, title, 1);
        
        // 添加章节
        for (int i = 0; i < sections.length; i++) {
            addHeading(document, sections[i], 2);
            addParagraph(document, contents[i]);
        }
        
        return document;
    }
    
    /**
     * 快速创建表格文档
     */
    public BoundesuDocument quickCreateTableDocument(String title, String[] headers, String[][] data) {
        BoundesuDocument document = createDocument(title, "Boundesu SDK User");
        
        addHeading(document, title, 1);
        
        // 创建表格
        int rows = data.length + 1; // 包含表头
        int cols = headers.length;
        addTable(document, rows, cols);
        
        // 准备完整的表格数据
        String[][] fullData = new String[rows][cols];
        fullData[0] = headers; // 第一行是表头
        System.arraycopy(data, 0, fullData, 1, data.length);
        
        setTableData(document, 0, fullData);
        
        return document;
    }
    
    /**
     * 打印SDK信息
     */
    public static void printSDKInfo() {
        Map<String, String> info = getSDKInfo();
        String separator = "==================================================";
        System.out.println(separator);
        System.out.println("Boundesu Words SDK");
        System.out.println(separator);
        for (Map.Entry<String, String> entry : info.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println(separator);
    }
}