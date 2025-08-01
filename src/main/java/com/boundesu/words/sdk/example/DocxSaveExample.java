package com.boundesu.words.sdk.example;

import com.boundesu.words.sdk.model.BoundesuDocument;
import com.boundesu.words.sdk.service.BoundesuDocumentService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * DOCX文档保存示例
 */
public class DocxSaveExample {
    
    public static void main(String[] args) {
        try {
            BoundesuDocumentService documentService = new BoundesuDocumentService();
            
            // 创建输出目录
            Path outputDir = Paths.get("output");
            if (!outputDir.toFile().exists()) {
                outputDir.toFile().mkdirs();
            }
            
            // 示例1：创建简单文档并保存为DOCX
            System.out.println("创建简单文档...");
            BoundesuDocument simpleDoc = createSimpleDocument(documentService);
            Path simpleDocxPath = outputDir.resolve("simple-document.docx");
            documentService.saveDocumentToFile(simpleDoc, "docx", simpleDocxPath);
            System.out.println("简单文档已保存到: " + simpleDocxPath.toAbsolutePath());
            
            // 示例2：创建复杂文档并保存为DOCX
            System.out.println("\n创建复杂文档...");
            BoundesuDocument complexDoc = createComplexDocument(documentService);
            Path complexDocxPath = outputDir.resolve("complex-document.docx");
            documentService.saveDocumentToFile(complexDoc, "docx", complexDocxPath);
            System.out.println("复杂文档已保存到: " + complexDocxPath.toAbsolutePath());
            
            // 示例3：创建技术文档并保存为DOCX
            System.out.println("\n创建技术文档...");
            BoundesuDocument techDoc = createTechnicalDocument(documentService);
            Path techDocxPath = outputDir.resolve("technical-document.docx");
            documentService.saveDocumentToFile(techDoc, "docx", techDocxPath);
            System.out.println("技术文档已保存到: " + techDocxPath.toAbsolutePath());
            
            System.out.println("\n所有DOCX文档保存完成！");
            
        } catch (IOException | InvalidFormatException e) {
            System.err.println("保存文档时出错: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static BoundesuDocument createSimpleDocument(BoundesuDocumentService service) {
        BoundesuDocument document = service.createDocument();
        
        // 设置文档属性
        document.setTitle("简单DOCX文档");
        document.setAuthor("Boundesu Words SDK");
        document.setDescription("这是一个使用Boundesu Words SDK创建的简单DOCX文档示例");
        document.setKeywords(Arrays.asList("DOCX", "文档", "示例", "Java"));
        document.setLanguage("zh-CN");
        
        // 添加标题
        service.addHeading(document, "欢迎使用Boundesu Words SDK", 1);
        
        // 添加段落
        service.addParagraph(document, "这是一个简单的DOCX文档示例。Boundesu Words SDK现在支持将文档导出为Microsoft Word格式。");
        service.addParagraph(document, "您可以使用这个SDK来创建、编辑和导出各种格式的文档，包括HTML、Markdown、纯文本和DOCX。");
        
        // 添加子标题
        service.addHeading(document, "主要特性", 2);
        
        // 添加列表内容
        service.addParagraph(document, "• 支持多种文档格式导出");
        service.addParagraph(document, "• 简单易用的API");
        service.addParagraph(document, "• 丰富的文档元数据支持");
        service.addParagraph(document, "• 表格和段落格式化");
        
        return document;
    }
    
    private static BoundesuDocument createComplexDocument(BoundesuDocumentService service) {
        BoundesuDocument document = service.createDocument();
        
        // 设置文档属性
        document.setTitle("复杂DOCX文档示例");
        document.setAuthor("Boundesu开发团队");
        document.setDescription("包含表格、多级标题和格式化内容的复杂文档示例");
        document.setKeywords(Arrays.asList("复杂文档", "表格", "格式化", "DOCX"));
        document.setLanguage("zh-CN");
        
        // 设置页眉页脚
        service.setHeader(document, "Boundesu Words SDK - 复杂文档示例");
        service.setFooter(document, "版权所有 © 2024 Boundesu");
        
        // 添加主标题
        service.addHeading(document, "项目管理报告", 1);
        
        // 添加概述
        service.addHeading(document, "项目概述", 2);
        service.addParagraph(document, "本报告详细描述了项目的当前状态、进展情况和未来计划。");
        
        // 添加项目信息表格
        service.addHeading(document, "项目基本信息", 3);
        service.addTable(document, 2, 4);
        String[][] infoData = {
            {"项目名称", "Boundesu Words SDK", "项目经理", "张三"},
            {"开始日期", "2024-01-01", "预计完成", "2024-12-31"}
        };
        service.setTableData(document, 0, infoData);
        
        // 添加进度信息
        service.addHeading(document, "项目进度", 2);
        service.addParagraph(document, "当前项目进度良好，各个模块按计划推进。");
        
        // 添加进度表格
        service.addTable(document, 4, 4);
        String[][] progressData = {
            {"模块", "负责人", "进度", "状态"},
            {"文档创建", "李四", "100%", "已完成"},
            {"HTML导出", "王五", "100%", "已完成"},
            {"DOCX导出", "赵六", "95%", "进行中"}
        };
        service.setTableData(document, 1, progressData);
        
        // 添加总结
        service.addHeading(document, "总结", 2);
        service.addParagraph(document, "项目整体进展顺利，DOCX导出功能即将完成。预计能够按时交付所有功能。");
        
        return document;
    }
    
    private static BoundesuDocument createTechnicalDocument(BoundesuDocumentService service) {
        BoundesuDocument document = service.createDocument();
        
        // 设置文档属性
        document.setTitle("API技术文档");
        document.setAuthor("技术团队");
        document.setDescription("Boundesu Words SDK API技术文档");
        document.setKeywords(Arrays.asList("API", "技术文档", "SDK", "Java"));
        document.setLanguage("zh-CN");
        
        // 设置页眉页脚
        service.setHeader(document, "Boundesu Words SDK API文档 v1.0");
        service.setFooter(document, "机密文档 - 仅供内部使用");
        
        // 添加标题
        service.addHeading(document, "Boundesu Words SDK API文档", 1);
        
        // 添加版本信息
        service.addHeading(document, "版本信息", 2);
        service.addParagraph(document, "版本: 1.0.0");
        service.addParagraph(document, "发布日期: " + LocalDateTime.now().toLocalDate());
        service.addParagraph(document, "兼容性: Java 17+");
        
        // 添加API概述
        service.addHeading(document, "API概述", 2);
        service.addParagraph(document, "Boundesu Words SDK提供了一套完整的文档处理API，支持创建、编辑和导出多种格式的文档。");
        
        // 添加主要类说明
        service.addHeading(document, "主要类", 2);
        
        service.addHeading(document, "BoundesuDocumentService", 3);
        service.addParagraph(document, "文档服务类，提供文档创建、编辑和导出的核心功能。");
        
        // 添加方法表格
        service.addTable(document, 6, 3);
        String[][] methodData = {
            {"方法名", "参数", "说明"},
            {"createDocument()", "无", "创建新文档"},
            {"addParagraph()", "document, text", "添加段落"},
            {"addHeading()", "document, text, level", "添加标题"},
            {"addTable()", "document, rows, cols", "添加表格"},
            {"exportToDocx()", "document, filePath", "导出为DOCX格式"}
        };
        service.setTableData(document, 0, methodData);
        
        // 添加使用示例
        service.addHeading(document, "使用示例", 2);
        service.addParagraph(document, "以下是一个简单的使用示例：");
        service.addParagraph(document, "");
        service.addParagraph(document, "BoundesuDocumentService service = new BoundesuDocumentService();");
        service.addParagraph(document, "BoundesuDocument doc = service.createDocument();");
        service.addParagraph(document, "service.setDocumentTitle(doc, \"我的文档\");");
        service.addParagraph(document, "service.addHeading(doc, \"标题\", 1);");
        service.addParagraph(document, "service.addParagraph(doc, \"这是一个段落。\");");
        service.addParagraph(document, "service.saveDocumentToFile(doc, \"docx\", Paths.get(\"output.docx\"));");
        
        return document;
    }
}