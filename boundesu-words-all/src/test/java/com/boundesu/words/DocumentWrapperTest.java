package com.boundesu.words;

import com.boundesu.words.common.exception.BoundesuWordsException;
import com.boundesu.words.common.model.Document;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Document封装类测试
 *
 * @author Boundesu Team
 * @version 1.0.0
 */
public class DocumentWrapperTest {

    @Test
    public void testHtmlToDocumentWrapper() throws BoundesuWordsException, IOException {
        BoundesuWordsAll sdk = new BoundesuWordsAll();
        
        // 测试HTML转换返回封装的Document
        String htmlContent = "<h1>测试标题</h1><p>这是测试段落。</p>";
        Document document = sdk.htmlToDocx(htmlContent);
        
        // 验证返回的是Document类型
        Assert.assertNotNull(document, "Document对象不应为null");
        Assert.assertNotNull(document.getXWPFDocument(), "底层XWPFDocument不应为null");
        
        // 验证文档内容
        Assert.assertFalse(document.isEmpty(), "文档不应为空");
        Assert.assertTrue(document.getParagraphCount() > 0, "段落数量应大于0");
        
        // 测试保存功能
        Path tempFile = Files.createTempFile("test_document_wrapper", ".docx");
        try {
            document.saveToFile(tempFile.toFile());
            Assert.assertTrue(Files.exists(tempFile), "文件应该被创建");
            Assert.assertTrue(Files.size(tempFile) > 0, "文件大小应大于0");
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    public void testXmlToDocumentWrapper() throws BoundesuWordsException, IOException {
        BoundesuWordsAll sdk = new BoundesuWordsAll();
        
        // 测试XML转换返回封装的Document
        String xmlContent = "<document><title>XML测试标题</title><paragraph>这是XML测试段落。</paragraph></document>";
        Document document = sdk.xmlToDocx(xmlContent);
        
        // 验证返回的是Document类型
        Assert.assertNotNull(document, "Document对象不应为null");
        Assert.assertNotNull(document.getXWPFDocument(), "底层XWPFDocument不应为null");
        
        // 验证文档内容
        Assert.assertFalse(document.isEmpty(), "文档不应为空");
        
        // 测试使用SDK的保存方法
        Path tempFile = Files.createTempFile("test_xml_document_wrapper", ".docx");
        try {
            sdk.saveToFile(document, tempFile.toFile());
            Assert.assertTrue(Files.exists(tempFile), "文件应该被创建");
            Assert.assertTrue(Files.size(tempFile) > 0, "文件大小应大于0");
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    public void testDocumentToString() throws BoundesuWordsException {
        BoundesuWordsAll sdk = new BoundesuWordsAll();
        
        String htmlContent = "<h1>测试</h1><p>段落1</p><p>段落2</p>";
        Document document = sdk.htmlToDocx(htmlContent);
        
        String documentString = document.toString();
        Assert.assertNotNull(documentString, "toString()不应返回null");
        Assert.assertTrue(documentString.contains("Document{"), "toString()应包含Document信息");
        Assert.assertTrue(documentString.contains("paragraphs="), "toString()应包含段落信息");
    }

    @Test
    public void testDocumentClose() throws BoundesuWordsException, IOException {
        BoundesuWordsAll sdk = new BoundesuWordsAll();
        
        String htmlContent = "<h1>测试关闭</h1>";
        Document document = sdk.htmlToDocx(htmlContent);
        
        // 测试关闭功能
        document.close();
        // 关闭后应该仍然可以访问基本信息（虽然底层资源已释放）
        Assert.assertNotNull(document, "Document对象不应为null");
    }
}