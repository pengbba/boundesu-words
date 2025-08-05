package com.boundesu.words.example;

import com.boundesu.words.BoundesuWordsAll;
import com.boundesu.words.common.exception.BoundesuWordsException;
import com.boundesu.words.common.model.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Map;

/**
 * Boundesu Words SDK 使用示例
 *
 * @author Boundesu
 * @version 1.0.0
 */
public class BoundesuWordsExample {

    private static final Logger log = LoggerFactory.getLogger(BoundesuWordsExample.class);

    public static void main(String[] args) {
        // 创建SDK实例
        BoundesuWordsAll sdk = new BoundesuWordsAll();

        try {
            // 1. 获取SDK信息
            System.out.println("=== SDK信息 ===");
            Map<String, String> sdkInfo = BoundesuWordsAll.getSDKInfo();
            sdkInfo.forEach((key, value) -> System.out.println(key + ": " + value));
            System.out.println();

            // 2. HTML内容转换示例
            System.out.println("=== HTML内容转换 ===");
            String htmlContent = "<h1>标题</h1><p>这是一个段落。</p><p>这是另一个段落。</p>";
            Document docFromHtml = sdk.htmlToDocx(htmlContent);
            sdk.saveToFile(docFromHtml, new File("output_from_html.docx"));
            System.out.println("HTML内容已转换并保存到 output_from_html.docx");

            // 3. XML内容转换示例
            System.out.println("\n=== XML内容转换 ===");
            String xmlContent = "<document><title>XML标题</title><paragraph>这是XML段落。</paragraph></document>";
            Document docFromXml = sdk.xmlToDocx(xmlContent);
            sdk.saveToFile(docFromXml, new File("output_from_xml.docx"));
            System.out.println("XML内容已转换并保存到 output_from_xml.docx");

            // 4. 文件转换示例（如果文件存在）
            System.out.println("\n=== 文件转换示例 ===");
            File inputFile = new File("input.html");
            if (inputFile.exists()) {
                sdk.convert(inputFile, new File("output_from_file.docx"));
                System.out.println("文件已转换并保存到 output_from_file.docx");
            } else {
                System.out.println("输入文件 input.html 不存在，跳过文件转换示例");
            }

            // 5. 一键转换示例
            System.out.println("\n=== 一键转换示例 ===");
            try {
                sdk.convert("sample.html", "sample_output.docx");
                System.out.println("一键转换完成：sample.html -> sample_output.docx");
            } catch (BoundesuWordsException e) {
                System.out.println("一键转换失败（可能是文件不存在）: " + e.getMessage());
            }

            System.out.println("\n=== 示例执行完成 ===");

        } catch (BoundesuWordsException e) {
            log.error("转换过程中发生错误", e);
            System.err.println("错误: " + e.getMessage());
        }
    }

    /**
     * 演示如何使用各个转换器
     */
    public static void demonstrateConverters() {
        BoundesuWordsAll sdk = new BoundesuWordsAll();

        try {
            System.out.println("=== 转换器演示 ===");

            // 直接使用HTML转换器
            String html = "<h2>使用HTML转换器</h2><p>直接调用HTML转换器。</p>";
            Document htmlDoc = sdk.getHtmlConverter().convertHtmlToDocx(html);
            sdk.saveToFile(htmlDoc, new File("direct_html_converter.docx"));
            System.out.println("直接使用HTML转换器完成");

            // 直接使用XML转换器
            String xml = "<root><heading>使用XML转换器</heading><content>直接调用XML转换器。</content></root>";
            Document xmlDoc = sdk.getXmlConverter().convertXmlToDocx(xml);
            sdk.saveToFile(xmlDoc, new File("direct_xml_converter.docx"));
            System.out.println("直接使用XML转换器完成");

        } catch (BoundesuWordsException e) {
            log.error("转换器演示过程中发生错误", e);
            System.err.println("错误: " + e.getMessage());
        }
    }
}