package com.boundesu.words.sdk.example;

import com.boundesu.words.sdk.constants.DocxConstants;
import com.boundesu.words.sdk.factory.DocumentFactory;
import com.boundesu.words.sdk.model.BoundesuDocument;
import com.boundesu.words.sdk.service.BoundesuDocumentService;
import com.boundesu.words.sdk.utils.DocumentValidator;
import com.boundesu.words.sdk.utils.PerformanceMonitor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 改进的DOCX导出示例
 * 展示如何使用新的工具类来提高代码质量和可维护性
 */
public class ImprovedDocxExample {

    public static void main(String[] args) {
        try {
            // 创建文档服务
            BoundesuDocumentService documentService = new BoundesuDocumentService();

            System.out.println("=== 改进的DOCX导出示例 ===");
            System.out.println("使用新的工具类和常量来提高代码质量");
            System.out.println();

            // 示例1：使用文档工厂创建预定义模板
            createTemplateDocuments(documentService);

            // 示例2：展示验证功能
            demonstrateValidation(documentService);

            // 示例3：展示性能监控
            demonstratePerformanceMonitoring(documentService);

            // 打印性能统计
            PerformanceMonitor.printAllStats();

            System.out.println("所有示例已完成！");
            System.out.println("生成的文件保存在: " + DocxConstants.OUTPUT_DIRECTORY);

        } catch (Exception e) {
            System.err.println("示例执行失败: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 使用文档工厂创建预定义模板
     */
    private static void createTemplateDocuments(BoundesuDocumentService documentService)
            throws IOException, InvalidFormatException {

        System.out.println("1. 使用文档工厂创建预定义模板");

        // 创建业务报告模板
        BoundesuDocument businessReport = DocumentFactory.createBusinessReportTemplate(
                "2024年第一季度业务报告", "张三", "市场部"
        );
        Path businessReportPath = Paths.get(DocxConstants.OUTPUT_DIRECTORY, "template-business-report.docx");
        documentService.exportToDocx(businessReport, businessReportPath);
        System.out.println("✓ 业务报告模板已创建: " + businessReportPath);

        // 创建技术文档模板
        BoundesuDocument techDoc = DocumentFactory.createTechnicalDocumentTemplate(
                "API接口设计文档", "李四", "技术项目"
        );
        Path techDocPath = Paths.get(DocxConstants.OUTPUT_DIRECTORY, "template-technical-doc.docx");
        documentService.exportToDocx(techDoc, techDocPath);
        System.out.println("✓ 技术文档模板已创建: " + techDocPath);

        // 创建用户手册模板
        BoundesuDocument userManual = DocumentFactory.createUserManualTemplate(
                "产品使用手册", "王五", "v1.0"
        );
        Path userManualPath = Paths.get(DocxConstants.OUTPUT_DIRECTORY, "template-user-manual.docx");
        documentService.exportToDocx(userManual, userManualPath);
        System.out.println("✓ 用户手册模板已创建: " + userManualPath);

        System.out.println();
    }

    /**
     * 展示验证功能
     */
    private static void demonstrateValidation(BoundesuDocumentService documentService) {
        System.out.println("2. 展示验证功能");

        try {
            // 测试文档验证
            BoundesuDocument validDoc = documentService.createDocument("有效文档", "测试用户");
            DocumentValidator.validateDocument(validDoc);
            System.out.println("✓ 文档验证通过");

            // 测试标题级别验证
            DocumentValidator.validateHeadingLevel(1);
            DocumentValidator.validateHeadingLevel(2);
            DocumentValidator.validateHeadingLevel(3);
            System.out.println("✓ 标题级别验证通过");

            // 测试表格大小验证
            DocumentValidator.validateTableSize(5, 3);
            System.out.println("✓ 表格大小验证通过");

            // 测试文件路径验证
            Path validPath = Paths.get(DocxConstants.OUTPUT_DIRECTORY, "test.docx");
            DocumentValidator.validateFilePath(validPath);
            System.out.println("✓ 文件路径验证通过");

        } catch (Exception e) {
            System.err.println("✗ 验证失败: " + e.getMessage());
        }

        // 测试无效输入的验证
        try {
            DocumentValidator.validateDocument(null);
        } catch (IllegalArgumentException e) {
            System.out.println("✓ 空文档验证正确拒绝: " + e.getMessage());
        }

        try {
            DocumentValidator.validateHeadingLevel(0);
        } catch (IllegalArgumentException e) {
            System.out.println("✓ 无效标题级别验证正确拒绝: " + e.getMessage());
        }

        System.out.println();
    }

    /**
     * 展示性能监控
     */
    private static void demonstratePerformanceMonitoring(BoundesuDocumentService documentService)
            throws IOException, InvalidFormatException {

        System.out.println("3. 展示性能监控");

        // 监控文档创建性能
        PerformanceMonitor.OperationContext context = PerformanceMonitor.startOperation("document_creation");
        BoundesuDocument document = documentService.createDocument("性能测试文档", "测试用户");

        // 添加一些内容
        documentService.addHeading(document, "性能测试", 1);
        documentService.addParagraph(document, "这是一个用于测试性能监控功能的文档。");

        for (int i = 1; i <= 5; i++) {
            documentService.addHeading(document, "章节 " + i, 2);
            documentService.addParagraph(document, "这是第 " + i + " 章节的内容。");
        }

        PerformanceMonitor.endOperation(context);

        // 监控DOCX导出性能
        Path performancePath = Paths.get(DocxConstants.OUTPUT_DIRECTORY, "performance-test.docx");
        documentService.exportToDocx(document, performancePath);

        System.out.println("✓ 性能监控完成，文档已保存: " + performancePath);
        System.out.println();
    }
}