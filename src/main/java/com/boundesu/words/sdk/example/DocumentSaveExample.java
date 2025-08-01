package com.boundesu.words.sdk.example;

import com.boundesu.words.sdk.model.BoundesuDocument;
import com.boundesu.words.sdk.service.BoundesuDocumentService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 文档保存示例
 * 演示如何将创建的文档保存到文件系统中
 */
public class DocumentSaveExample {
    
    public static void main(String[] args) {
        try {
            // 创建文档服务实例
            BoundesuDocumentService documentService = new BoundesuDocumentService();
            
            // 创建输出目录
            Path outputDir = Paths.get("output");
            if (!Files.exists(outputDir)) {
                Files.createDirectories(outputDir);
                System.out.println("创建输出目录: " + outputDir.toAbsolutePath());
            }
            
            // 示例1：创建并保存简单文档
            createAndSaveSimpleDocument(documentService, outputDir);
            
            // 示例2：创建并保存复杂文档
            createAndSaveComplexDocument(documentService, outputDir);
            
            System.out.println("\n所有文档已保存到: " + outputDir.toAbsolutePath());
            
        } catch (Exception e) {
            System.err.println("文档保存出错: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * 创建并保存简单文档
     */
    private static void createAndSaveSimpleDocument(BoundesuDocumentService documentService, Path outputDir) throws IOException, InvalidFormatException {
        System.out.println("=== 创建简单文档 ===");
        
        // 创建文档
        BoundesuDocument document = documentService.createDocument("我的第一个文档", "张三");
        
        // 添加内容
        documentService.addHeading(document, "欢迎使用 Boundesu Words SDK", 1);
        documentService.addParagraph(document, "这是一个使用 Boundesu Words SDK 创建的示例文档。");
        documentService.addParagraph(document, "SDK 提供了丰富的文档创建和编辑功能。");
        
        documentService.addHeading(document, "主要特性", 2);
        documentService.addParagraph(document, "• 支持多种文档格式");
        documentService.addParagraph(document, "• 提供丰富的样式设置");
        documentService.addParagraph(document, "• 支持表格和图片");
        
        // 设置文档属性
        document.setDescription("这是一个 Boundesu Words SDK 的示例文档");
        documentService.addKeywords(document, "示例", "文档", "SDK", "演示");
        
        // 保存为不同格式
        String baseName = "simple-document";
        
        // 保存为 HTML
        Path htmlFile = outputDir.resolve(baseName + ".html");
        documentService.saveDocumentToFile(document, "html", htmlFile);
        System.out.println("HTML 文件已保存: " + htmlFile.toAbsolutePath());
        
        // 保存为 Markdown
        Path markdownFile = outputDir.resolve(baseName + ".md");
        documentService.saveDocumentToFile(document, "markdown", markdownFile);
        System.out.println("Markdown 文件已保存: " + markdownFile.toAbsolutePath());
        
        // 保存为纯文本
        Path textFile = outputDir.resolve(baseName + ".txt");
        documentService.saveDocumentToFile(document, "text", textFile);
        System.out.println("文本文件已保存: " + textFile.toAbsolutePath());
    }
    
    /**
     * 创建并保存复杂文档
     */
    private static void createAndSaveComplexDocument(BoundesuDocumentService documentService, Path outputDir) throws IOException, InvalidFormatException {
        System.out.println("\n=== 创建复杂文档 ===");
        
        // 创建文档
        BoundesuDocument document = documentService.createDocument("项目技术方案", "技术团队");
        
        // 设置文档元信息
        document.setSubject("系统架构设计方案");
        document.setDescription("本文档详细描述了项目的技术架构和实现方案");
        documentService.addKeywords(document, "技术方案", "架构设计", "系统设计");
        
        // 设置页眉页脚
        documentService.setHeader(document, "项目技术方案 - 机密文档");
        documentService.setFooter(document, "版权所有 © 2024 技术团队");
        
        // 添加内容
        documentService.addHeading(document, "1. 项目概述", 1);
        documentService.addParagraph(document, "本项目旨在构建一个高性能、可扩展的文档处理系统，支持多种文档格式的创建、编辑和转换。");
        
        documentService.addHeading(document, "1.1 项目目标", 2);
        documentService.addParagraph(document, "• 提供简单易用的文档创建API");
        documentService.addParagraph(document, "• 支持HTML、Markdown、纯文本等多种输出格式");
        documentService.addParagraph(document, "• 实现高性能的文档处理能力");
        documentService.addParagraph(document, "• 提供丰富的文档样式和布局选项");
        
        documentService.addHeading(document, "2. 技术架构", 1);
        documentService.addParagraph(document, "系统采用分层架构设计，各层职责明确，便于维护和扩展。");
        
        // 添加架构表格
        documentService.addTable(document, 5, 3);
        String[][] architectureData = {
            {"层次", "组件", "主要职责"},
            {"API层", "BoundesuWordsSDK", "对外提供统一的API接口"},
            {"服务层", "BoundesuDocumentService", "核心业务逻辑处理"},
            {"模型层", "Document Models", "文档数据模型定义"},
            {"工具层", "Utils & Exporters", "工具类和导出功能"}
        };
        documentService.setTableData(document, 0, architectureData);
        
        documentService.addHeading(document, "3. 核心功能", 1);
        documentService.addParagraph(document, "系统提供以下核心功能模块：");
        
        // 添加功能表格
        documentService.addTable(document, 6, 2);
        String[][] featureData = {
            {"功能模块", "描述"},
            {"文档创建", "支持创建各种类型的文档"},
            {"内容编辑", "提供段落、标题、表格等内容编辑"},
            {"样式设置", "支持丰富的文档和段落样式"},
            {"格式导出", "支持多种格式的文档导出"},
            {"文档操作", "提供复制、合并、搜索等操作"}
        };
        documentService.setTableData(document, 1, featureData);
        
        documentService.addHeading(document, "4. 实施计划", 1);
        documentService.addParagraph(document, "项目将分三个阶段实施：");
        documentService.addParagraph(document, "第一阶段：核心功能开发（4周）");
        documentService.addParagraph(document, "第二阶段：功能完善和测试（3周）");
        documentService.addParagraph(document, "第三阶段：文档和部署（1周）");
        
        // 设置自定义属性
        documentService.setCustomProperty(document, "department", "技术部");
        documentService.setCustomProperty(document, "confidentiality", "机密");
        documentService.setCustomProperty(document, "version", "1.0");
        
        // 保存文档
        String baseName = "technical-proposal";
        
        // 保存为 HTML（带样式）
        Path htmlFile = outputDir.resolve(baseName + ".html");
        documentService.saveDocumentToFile(document, "html", htmlFile);
        System.out.println("技术方案 HTML 文件已保存: " + htmlFile.toAbsolutePath());
        
        // 保存为 Markdown
        Path markdownFile = outputDir.resolve(baseName + ".md");
        documentService.saveDocumentToFile(document, "markdown", markdownFile);
        System.out.println("技术方案 Markdown 文件已保存: " + markdownFile.toAbsolutePath());
        
        // 保存为纯文本
        Path textFile = outputDir.resolve(baseName + ".txt");
        documentService.saveDocumentToFile(document, "text", textFile);
        System.out.println("技术方案文本文件已保存: " + textFile.toAbsolutePath());
        
        // 显示文档统计信息
        System.out.println("\n文档统计信息:");
        System.out.println("- 文档标题: " + document.getTitle());
        System.out.println("- 段落数量: " + document.getParagraphs().size());
        System.out.println("- 表格数量: " + document.getTables().size());
        System.out.println("- 文档状态: " + document.getStatus());
    }
}