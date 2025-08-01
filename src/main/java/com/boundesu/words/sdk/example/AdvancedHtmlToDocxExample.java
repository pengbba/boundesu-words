package com.boundesu.words.sdk.example;

import com.boundesu.words.sdk.converter.HtmlToDocxConverter;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 高级HTML转DOCX测试示例
 * 
 * @author Boundesu
 * @version 1.0.0
 */
public class AdvancedHtmlToDocxExample {
    
    public static void main(String[] args) {
        try {
            System.out.println("开始高级HTML转DOCX测试...");
            
            // 输入和输出路径
            Path inputPath = Paths.get("output/advanced-sample.html");
            Path outputPath = Paths.get("output/advanced-sample.docx");
            
            // 转换HTML文件到DOCX
            HtmlToDocxConverter.convertHtmlFileToDocx(inputPath, outputPath);
            
            System.out.println("高级HTML转DOCX测试完成！");
            System.out.println("输出文件: " + outputPath.toAbsolutePath());
            
        } catch (Exception e) {
            System.err.println("转换失败: " + e.getMessage());
            e.printStackTrace();
        }
    }
}