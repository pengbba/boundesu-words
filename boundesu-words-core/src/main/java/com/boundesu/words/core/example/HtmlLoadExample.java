package com.boundesu.words.core.example;

import com.boundesu.words.common.exception.BoundesuWordsException;
import com.boundesu.words.core.Document;
import com.boundesu.words.core.options.HtmlLoadOptions;
import com.boundesu.words.core.options.LoadOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

/**
 * HTML文档加载示例
 * 演示如何使用HtmlLoadOptions加载HTML文件
 */
public class HtmlLoadExample {
    private static final Logger log = LoggerFactory.getLogger(HtmlLoadExample.class);

    public static void main(String[] args) {
        try {
            // 示例HTML文件路径（使用项目中的测试文件）
            String htmlFilePath = "test-input.html";

            // 如果文件不存在，使用绝对路径
            java.io.File htmlFile = new java.io.File(htmlFilePath);
            if (!htmlFile.exists()) {
                htmlFilePath = "e:\\drug\\boundesu-words\\test-input.html";
            }

            // 设置 HTML 加载选项
            HtmlLoadOptions optionsHtml = new HtmlLoadOptions();

            // 字符编码设置
            optionsHtml.setEncoding(StandardCharsets.UTF_8);

            // 设置MS Word版本
            optionsHtml.setMsWordVersion(LoadOptions.MsWordVersion.WORD_2019);

            // 设置HTML特定选项
            optionsHtml.setConvertSvgToEmf(false);
            optionsHtml.setIgnoreNoscriptElements(true);
            optionsHtml.setSupportVml(true);
            optionsHtml.setSupportFontFaceRules(true);
            optionsHtml.setWebRequestTimeout(120000); // 2分钟超时
            optionsHtml.setBlockImportMode(HtmlLoadOptions.BlockImportMode.MERGE);
            optionsHtml.setPreferredControlType(HtmlLoadOptions.HtmlControlType.FORM_FIELD);

            log.info("开始加载HTML文件: {}", htmlFilePath);

            // 加载 HTML 文件
            Document doc = new Document(htmlFilePath, optionsHtml);

            // 优化文档兼容性
            doc.getCompatibilityOptions().optimizeFor(LoadOptions.MsWordVersion.WORD_2019);

            log.info("HTML文档加载成功！");
            log.info("文档文本内容预览: {}", doc.getText().substring(0, Math.min(100, doc.getText().length())));

            // 保存为DOCX格式
            String outputPath = htmlFilePath.replace(".html", ".docx");
            doc.save(outputPath);
            log.info("文档已保存为: {}", outputPath);

        } catch (BoundesuWordsException e) {
            log.error("加载HTML文档时发生错误: {}", e.getMessage(), e);
        } catch (Exception e) {
            log.error("处理文档时发生未知错误: {}", e.getMessage(), e);
        }
    }

    /**
     * 演示不同的HTML加载选项配置
     */
    public static void demonstrateHtmlLoadOptions() {
        HtmlLoadOptions options = new HtmlLoadOptions();

        log.info("=== HTML加载选项演示 ===");

        // 基本选项
        options.setEncoding(StandardCharsets.UTF_8);
        options.setMsWordVersion(LoadOptions.MsWordVersion.WORD_2019);
        log.info("字符编码: {}", options.getEncoding());
        log.info("MS Word版本: {}", options.getMsWordVersion());

        // HTML特定选项
        options.setConvertSvgToEmf(true);
        options.setIgnoreNoscriptElements(false);
        options.setSupportVml(true);
        options.setSupportFontFaceRules(false);
        options.setWebRequestTimeout(60000);
        options.setBlockImportMode(HtmlLoadOptions.BlockImportMode.PRESERVE);
        options.setPreferredControlType(HtmlLoadOptions.HtmlControlType.STRUCTURED_DOCUMENT_TAG);

        log.info("转换SVG为EMF: {}", options.getConvertSvgToEmf());
        log.info("忽略noscript元素: {}", options.getIgnoreNoscriptElements());
        log.info("支持VML: {}", options.getSupportVml());
        log.info("支持字体规则: {}", options.getSupportFontFaceRules());
        log.info("Web请求超时: {} ms", options.getWebRequestTimeout());
        log.info("块导入模式: {}", options.getBlockImportMode());
        log.info("首选控件类型: {}", options.getPreferredControlType());
    }
}