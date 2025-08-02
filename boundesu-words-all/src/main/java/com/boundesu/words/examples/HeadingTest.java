package com.boundesu.words.examples;

import com.boundesu.words.BoundesuWordsSDK;
import com.boundesu.words.html.converter.HtmlToDocxConverter;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 标题层级测试程序
 */
public class HeadingTest {
    private static final String OUTPUT_DIR = "examples_output";
    
    public static void main(String[] args) {
        System.out.println("=== 标题层级测试 ===");
        
        try {
            // 创建输出目录
            createOutputDirectory();
            
            // 测试标题层级
            testHeadingLevels();
            
            System.out.println("\n=== 标题层级测试完成！===");
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
    
    private static void testHeadingLevels() {
        System.out.println("\n=== 标题层级功能演示 ===");
        
        String htmlContent = "<html>" +
                "<head>" +
                "<title>标题层级测试文档</title>" +
                "</head>" +
                "<body>" +
                "<h1>第一级标题 - 项目概述</h1>" +
                "<p>这是一个测试文档，用于验证HTML标题标签是否正确转换为Word文档的标题样式。</p>" +
                "<h2>第二级标题 - 功能介绍</h2>" +
                "<p>本节介绍主要功能特性。</p>" +
                "<h3>第三级标题 - 核心功能</h3>" +
                "<p>详细描述核心功能的实现。</p>" +
                "<h4>第四级标题 - 技术细节</h4>" +
                "<p>技术实现的具体细节说明。</p>" +
                "<h5>第五级标题 - 配置选项</h5>" +
                "<p>各种配置选项的说明。</p>" +
                "<h6>第六级标题 - 注意事项</h6>" +
                "<p>使用过程中需要注意的事项。</p>" +
                "<h2>第二级标题 - 使用示例</h2>" +
                "<p>以下是一些使用示例。</p>" +
                "<h3>第三级标题 - 基础用法</h3>" +
                "<p>基础功能的使用方法。</p>" +
                "<h3>第三级标题 - 高级用法</h3>" +
                "<p>高级功能的使用方法。</p>" +
                "<h1>第一级标题 - 总结</h1>" +
                "<p>本文档展示了从H1到H6的所有标题层级，验证了HTML到Word文档的标题样式转换功能。</p>" +
                "</body>" +
                "</html>";
        
        try {
            System.out.println("\n正在创建包含多级标题的测试文档...");
            
            // 使用默认页边距创建文档
            HtmlToDocxConverter.PageMargins defaultMargins = HtmlToDocxConverter.PageMargins.defaultMargins();
            Path outputPath = Paths.get(OUTPUT_DIR, "heading_levels_test.docx");
            
            BoundesuWordsSDK.convertHtmlToDocx(htmlContent, outputPath, defaultMargins);
            
            System.out.println("测试文档已创建: heading_levels_test.docx");
            System.out.println("\n文档包含以下标题层级:");
            System.out.println("- H1: 第一级标题 (应显示为Word的标题1样式)");
            System.out.println("- H2: 第二级标题 (应显示为Word的标题2样式)");
            System.out.println("- H3: 第三级标题 (应显示为Word的标题3样式)");
            System.out.println("- H4: 第四级标题 (应显示为Word的标题4样式)");
            System.out.println("- H5: 第五级标题 (应显示为Word的标题5样式)");
            System.out.println("- H6: 第六级标题 (应显示为Word的标题6样式)");
            System.out.println("\n请打开生成的Word文档验证标题样式是否正确应用。");
            
        } catch (Exception e) {
            System.err.println("标题层级测试失败: " + e.getMessage());
            e.printStackTrace();
        }
    }
}