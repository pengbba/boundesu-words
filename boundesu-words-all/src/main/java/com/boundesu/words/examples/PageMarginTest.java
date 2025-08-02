package com.boundesu.words.examples;

import com.boundesu.words.BoundesuWordsSDK;
import com.boundesu.words.html.converter.HtmlToDocxConverter;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 页边距功能测试程序
 */
public class PageMarginTest {
    private static final String OUTPUT_DIR = "examples_output";

    public static void main(String[] args) {
        System.out.println("=== 页边距功能测试 ===");

        try {
            // 创建输出目录
            createOutputDirectory();

            // 测试页边距功能
            testPageMargins();

            System.out.println("\n=== 页边距测试完成！===");
            System.out.println("输出文件位置: " + Paths.get(OUTPUT_DIR).toAbsolutePath());

        } catch (Exception e) {
            System.err.println("测试过程中发生错误: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void createOutputDirectory() {
        File outputDir = new File(OUTPUT_DIR);
        if (!outputDir.exists()) {
            outputDir.mkdirs();
            System.out.println("创建输出目录: " + outputDir.getAbsolutePath());
        }
    }

    private static void testPageMargins() {
        System.out.println("\n=== 页边距功能演示 ===");

        String htmlContent = "<html><body><h1>页边距测试文档</h1><p>这是一个用于测试页边距功能的文档。</p></body></html>";

        try {
            // 1. 默认页边距
            System.out.println("\n1. 使用默认页边距创建文档");
            HtmlToDocxConverter.PageMargins defaultMargins = HtmlToDocxConverter.PageMargins.defaultMargins();
            System.out.println("默认页边距信息: " + defaultMargins.getMarginInfo());
            Path defaultPath = Paths.get(OUTPUT_DIR, "test_default_margins.docx");
            BoundesuWordsSDK.convertHtmlToDocx(htmlContent, defaultPath, defaultMargins);
            System.out.println("文档已创建: test_default_margins.docx");

            // 2. 窄页边距
            System.out.println("\n2. 使用窄页边距创建文档");
            HtmlToDocxConverter.PageMargins narrowMargins = HtmlToDocxConverter.PageMargins.narrowMargins();
            System.out.println("窄页边距信息: " + narrowMargins.getMarginInfo());
            Path narrowPath = Paths.get(OUTPUT_DIR, "test_narrow_margins.docx");
            BoundesuWordsSDK.convertHtmlToDocx(htmlContent, narrowPath, narrowMargins);
            System.out.println("文档已创建: test_narrow_margins.docx");

            // 3. 宽页边距
            System.out.println("\n3. 使用宽页边距创建文档");
            HtmlToDocxConverter.PageMargins wideMargins = HtmlToDocxConverter.PageMargins.wideMargins();
            System.out.println("宽页边距信息: " + wideMargins.getMarginInfo());
            Path widePath = Paths.get(OUTPUT_DIR, "test_wide_margins.docx");
            BoundesuWordsSDK.convertHtmlToDocx(htmlContent, widePath, wideMargins);
            System.out.println("文档已创建: test_wide_margins.docx");

            // 4. 装订页边距
            System.out.println("\n4. 使用装订页边距创建文档");
            HtmlToDocxConverter.PageMargins bindingMargins = HtmlToDocxConverter.PageMargins.bindingMargins(1.5);
            System.out.println("装订页边距信息: " + bindingMargins.getMarginInfo());
            Path bindingPath = Paths.get(OUTPUT_DIR, "test_binding_margins.docx");
            BoundesuWordsSDK.convertHtmlToDocx(htmlContent, bindingPath, bindingMargins);
            System.out.println("文档已创建: test_binding_margins.docx");

            // 5. 从英寸创建页边距
            System.out.println("\n5. 从英寸单位创建页边距");
            HtmlToDocxConverter.PageMargins inchMargins = HtmlToDocxConverter.PageMargins.fromInches(1.5, 1.0, 1.25, 1.0);
            System.out.println("英寸页边距信息: " + inchMargins.getMarginInfo());
            Path inchPath = Paths.get(OUTPUT_DIR, "test_inch_margins.docx");
            BoundesuWordsSDK.convertHtmlToDocx(htmlContent, inchPath, inchMargins);
            System.out.println("文档已创建: test_inch_margins.docx");

            // 6. 从厘米创建页边距
            System.out.println("\n6. 从厘米单位创建页边距");
            HtmlToDocxConverter.PageMargins cmMargins = HtmlToDocxConverter.PageMargins.fromCentimeters(3.0, 2.5, 3.0, 2.5);
            System.out.println("厘米页边距信息: " + cmMargins.getMarginInfo());
            Path cmPath = Paths.get(OUTPUT_DIR, "test_cm_margins.docx");
            BoundesuWordsSDK.convertHtmlToDocx(htmlContent, cmPath, cmMargins);
            System.out.println("文档已创建: test_cm_margins.docx");

            // 7. 测试页边距验证
            System.out.println("\n7. 测试页边距验证功能");
            try {
                HtmlToDocxConverter.PageMargins invalidMargins = new HtmlToDocxConverter.PageMargins(-10, 50, 100, 200);
                System.out.println("无效页边距信息: " + invalidMargins.getMarginInfo());
            } catch (IllegalArgumentException e) {
                System.out.println("页边距验证成功，捕获到异常: " + e.getMessage());
            }

            // 8. 显示页边距详细信息
            System.out.println("\n8. 页边距详细信息");
            System.out.println("默认页边距 - 上: " + defaultMargins.getTopInPoints() + "点, 下: " + defaultMargins.getBottomInPoints() + "点");
            System.out.println("默认页边距 - 左: " + defaultMargins.getLeftInPoints() + "点, 右: " + defaultMargins.getRightInPoints() + "点");

        } catch (Exception e) {
            System.err.println("页边距测试失败: " + e.getMessage());
            e.printStackTrace();
        }
    }
}