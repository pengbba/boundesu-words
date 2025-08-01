package com.boundesu.words.sdk.example;

import com.boundesu.words.sdk.model.*;
import com.boundesu.words.sdk.service.BoundesuDocumentService;
import com.boundesu.words.sdk.util.BoundesuDocumentUtils;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

/**
 * Boundesu Words JDK17 SDK - 示例应用程序
 * 
 * @author Boundesu
 * @version 1.0.0
 */
public class BoundesuWordsExample {
    
    public static void main(String[] args) {
        try {
            // 创建文档服务实例
            BoundesuDocumentService documentService = new BoundesuDocumentService();
            
            // 示例1：创建基本文档
            System.out.println("=== 示例1：创建基本文档 ===");
            createBasicDocument(documentService);
            
            // 示例2：创建复杂文档
            System.out.println("\n=== 示例2：创建复杂文档 ===");
            createComplexDocument(documentService);
            
            // 示例3：文档操作示例
            System.out.println("\n=== 示例3：文档操作示例 ===");
            documentOperationsExample(documentService);
            
            // 示例4：导出示例
            System.out.println("\n=== 示例4：导出示例 ===");
            exportExample(documentService);
            
        } catch (Exception e) {
            System.err.println("示例执行出错: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * 示例1：创建基本文档
     */
    private static void createBasicDocument(BoundesuDocumentService documentService) {
        // 创建文档
        BoundesuDocument document = documentService.createDocument("我的第一个文档", "张三");
        
        // 添加段落
        documentService.addParagraph(document, "这是文档的第一个段落。");
        documentService.addParagraph(document, "这是文档的第二个段落，包含更多内容。");
        
        // 添加标题
        documentService.addHeading(document, "第一章 概述", 1);
        documentService.addParagraph(document, "这是第一章的内容。");
        
        // 设置文档属性
        document.setDescription("这是一个示例文档，用于演示Boundesu Words SDK的基本功能。");
        documentService.addKeywords(document, "示例", "文档", "SDK");
        
        // 获取统计信息
        // BoundesuDocumentUtils.DocumentStatistics stats = documentService.getDocumentStatistics(document);
        // System.out.println("文档统计信息: " + stats);
        
        // 验证文档
        boolean isValid = documentService.validateDocument(document);
        System.out.println("文档验证结果: " + (isValid ? "通过" : "失败"));
        
        System.out.println("基本文档创建完成！");
    }
    
    /**
     * 示例2：创建复杂文档
     */
    private static void createComplexDocument(BoundesuDocumentService documentService) {
        // 创建文档
        BoundesuDocument document = documentService.createDocument("技术报告", "李四");
        
        // 设置文档元信息
        document.setSubject("系统架构设计");
        document.setDescription("本报告详细描述了系统的架构设计和实现方案。");
        documentService.addKeywords(document, "架构", "设计", "系统", "技术");
        
        // 添加标题和内容
        documentService.addHeading(document, "1. 项目概述", 1);
        documentService.addParagraph(document, "本项目旨在构建一个高性能、可扩展的文档处理系统。");
        
        documentService.addHeading(document, "1.1 项目目标", 2);
        documentService.addParagraph(document, "• 提供高效的文档创建和编辑功能");
        documentService.addParagraph(document, "• 支持多种文档格式的导入导出");
        documentService.addParagraph(document, "• 实现文档的版本控制和协作编辑");
        
        documentService.addHeading(document, "2. 系统架构", 1);
        documentService.addParagraph(document, "系统采用分层架构设计，包括以下几个主要层次：");
        
        // 添加表格
        documentService.addTable(document, 4, 3);
        String[][] tableData = {
            {"层次", "组件", "职责"},
            {"表示层", "Web界面", "用户交互"},
            {"业务层", "文档服务", "业务逻辑处理"},
            {"数据层", "数据库", "数据存储"}
        };
        documentService.setTableData(document, 0, tableData);
        
        // 设置页眉页脚
        documentService.setHeader(document, "技术报告 - 系统架构设计");
        documentService.setFooter(document, "版权所有 © 2024 Boundesu");
        
        // 设置自定义属性
        documentService.setCustomProperty(document, "department", "技术部");
        documentService.setCustomProperty(document, "confidentiality", "内部");
        
        // 设置文档状态
        documentService.setDocumentStatus(document, BoundesuDocument.DocumentStatus.REVIEW);
        
        System.out.println("复杂文档创建完成！");
        System.out.println("文档标题: " + document.getTitle());
        System.out.println("段落数量: " + document.getParagraphs().size());
        System.out.println("表格数量: " + document.getTables().size());
    }
    
    /**
     * 示例3：文档操作示例
     */
    private static void documentOperationsExample(BoundesuDocumentService documentService) {
        // 创建原始文档
        BoundesuDocument originalDoc = documentService.createDocument("原始文档", "王五");
        documentService.addParagraph(originalDoc, "这是原始文档的内容。");
        documentService.addParagraph(originalDoc, "包含一些重要信息。");
        
        // 复制文档
        BoundesuDocument clonedDoc = documentService.cloneDocument(originalDoc);
        System.out.println("文档复制完成: " + clonedDoc.getTitle());
        
        // 创建另一个文档用于合并
        BoundesuDocument mergeDoc = documentService.createDocument("合并文档", "赵六");
        documentService.addParagraph(mergeDoc, "这是要合并的内容。");
        documentService.addTable(mergeDoc, 2, 2);
        
        // 合并文档
        documentService.mergeDocuments(clonedDoc, mergeDoc);
        System.out.println("文档合并完成，合并后段落数: " + clonedDoc.getParagraphs().size());
        System.out.println("合并后表格数: " + clonedDoc.getTables().size());
        
        // 搜索内容
        documentService.addParagraph(clonedDoc, "这里包含搜索关键词。");
        List<BoundesuParagraph> searchResults = documentService.searchInDocument(clonedDoc, "关键词");
        System.out.println("搜索结果数量: " + searchResults.size());
        
        // 替换内容
        documentService.replaceInDocument(clonedDoc, "重要信息", "关键信息");
        System.out.println("内容替换完成");
        
        // 生成摘要
        String summary = documentService.generateDocumentSummary(clonedDoc, 50);
        System.out.println("文档摘要: " + summary);
    }
    
    /**
     * 示例4：导出示例
     */
    private static void exportExample(BoundesuDocumentService documentService) throws IOException {
        // 创建示例文档
        BoundesuDocument document = documentService.createDocument("导出示例文档", "测试用户");
        
        // 添加内容
        documentService.addHeading(document, "导出功能演示", 1);
        documentService.addParagraph(document, "本文档用于演示各种导出格式的效果。");
        
        documentService.addHeading(document, "HTML导出", 2);
        documentService.addParagraph(document, "HTML格式支持丰富的样式和布局。");
        
        documentService.addHeading(document, "Markdown导出", 2);
        documentService.addParagraph(document, "Markdown格式简洁明了，适合技术文档。");
        
        // 添加表格
        documentService.addTable(document, 3, 2);
        String[][] data = {
            {"格式", "特点"},
            {"HTML", "丰富样式"},
            {"Markdown", "简洁明了"}
        };
        documentService.setTableData(document, 0, data);
        
        // 导出为不同格式
        System.out.println("=== HTML导出 ===");
        String html = documentService.exportToHtml(document);
        System.out.println("HTML长度: " + html.length() + " 字符");
        
        System.out.println("\n=== Markdown导出 ===");
        String markdown = documentService.exportToMarkdown(document);
        System.out.println("Markdown内容:");
        System.out.println(markdown.substring(0, Math.min(200, markdown.length())) + "...");
        
        System.out.println("\n=== 纯文本导出 ===");
        String text = documentService.exportToText(document);
        System.out.println("纯文本内容:");
        System.out.println(text.substring(0, Math.min(200, text.length())) + "...");
        
        // 保存到文件（注释掉以避免实际文件操作）
        /*
        try {
            documentService.saveDocumentToFile(document, "html", Paths.get("example.html"));
            documentService.saveDocumentToFile(document, "markdown", Paths.get("example.md"));
            documentService.saveDocumentToFile(document, "text", Paths.get("example.txt"));
            System.out.println("文件保存完成！");
        } catch (IOException e) {
            System.err.println("文件保存失败: " + e.getMessage());
        }
        */
        
        System.out.println("导出示例完成！");
    }
}