package com.boundesu.words.html.converter;

import com.boundesu.words.common.exception.BoundesuWordsException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * HtmlToDocxConverter 测试类
 * 
 * @author Boundesu
 * @version 1.0.0
 */
public class HtmlToDocxConverterTest {
    
    private HtmlToDocxConverter converter;
    private Path tempDir;
    
    @BeforeMethod
    public void setUp() throws IOException {
        converter = new HtmlToDocxConverter();
        tempDir = Files.createTempDirectory("boundesu-html-test");
    }
    
    @AfterMethod
    public void tearDown() throws IOException {
        // 清理临时目录
        Files.walk(tempDir)
                .sorted((a, b) -> b.compareTo(a)) // 先删除文件，再删除目录
                .forEach(path -> {
                    try {
                        Files.deleteIfExists(path);
                    } catch (IOException e) {
                        // 忽略删除错误
                    }
                });
    }
    
    @Test
    public void testConvertSimpleHtml() throws BoundesuWordsException {
        String html = "<h1>标题</h1><p>这是一个段落。</p>";
        
        XWPFDocument document = converter.convertHtmlToDocx(html);
        Assert.assertNotNull(document);
        Assert.assertTrue(document.getParagraphs().size() > 0);
        
        try {
            document.close();
        } catch (IOException e) {
            Assert.fail("Failed to close document");
        }
    }
    
    @Test
    public void testConvertComplexHtml() throws BoundesuWordsException {
        String html = "<html><body>" +
                "<h1>主标题</h1>" +
                "<h2>副标题</h2>" +
                "<p>这是第一个段落。</p>" +
                "<p>这是第二个段落，包含<strong>粗体</strong>和<em>斜体</em>文本。</p>" +
                "<ul><li>列表项1</li><li>列表项2</li></ul>" +
                "</body></html>";
        
        XWPFDocument document = converter.convertHtmlToDocx(html);
        Assert.assertNotNull(document);
        Assert.assertTrue(document.getParagraphs().size() > 0);
        
        try {
            document.close();
        } catch (IOException e) {
            Assert.fail("Failed to close document");
        }
    }
    
    @Test
    public void testConvertHtmlWithTable() throws BoundesuWordsException {
        String html = "<table>" +
                "<tr><th>标题1</th><th>标题2</th></tr>" +
                "<tr><td>数据1</td><td>数据2</td></tr>" +
                "<tr><td>数据3</td><td>数据4</td></tr>" +
                "</table>";
        
        XWPFDocument document = converter.convertHtmlToDocx(html);
        Assert.assertNotNull(document);
        Assert.assertTrue(document.getTables().size() > 0);
        
        try {
            document.close();
        } catch (IOException e) {
            Assert.fail("Failed to close document");
        }
    }
    
    @Test
    public void testConvertHtmlFromInputStream() throws BoundesuWordsException, IOException {
        String html = "<h1>从输入流转换</h1><p>这是从输入流读取的HTML内容。</p>";
        
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(html.getBytes("UTF-8"))) {
            XWPFDocument document = converter.convertHtmlToDocx(inputStream);
            Assert.assertNotNull(document);
            Assert.assertTrue(document.getParagraphs().size() > 0);
            
            document.close();
        }
    }
    
    @Test
    public void testSaveDocumentToFile() throws BoundesuWordsException, IOException {
        String html = "<h1>保存测试</h1><p>测试保存文档到文件。</p>";
        File outputFile = new File(tempDir.toFile(), "test-output.docx");
        
        XWPFDocument document = converter.convertHtmlToDocx(html);
        
        // 保存到文件
        try (FileOutputStream out = new FileOutputStream(outputFile)) {
            document.write(out);
        }
        
        Assert.assertTrue(outputFile.exists());
        Assert.assertTrue(outputFile.length() > 0);
        
        document.close();
    }
    
    @Test(expectedExceptions = BoundesuWordsException.class)
    public void testConvertEmptyHtml() throws BoundesuWordsException {
        converter.convertHtmlToDocx("");
    }
    
    @Test(expectedExceptions = BoundesuWordsException.class)
    public void testConvertNullHtml() throws BoundesuWordsException {
        converter.convertHtmlToDocx((String) null);
    }
    
    @Test
    public void testConvertHtmlWithSpecialCharacters() throws BoundesuWordsException {
        String html = "<h1>特殊字符测试</h1>" +
                "<p>包含特殊字符：&amp; &lt; &gt; &quot; &#39;</p>" +
                "<p>中文字符：你好世界</p>" +
                "<p>数字和符号：123 !@#$%^&*()</p>";
        
        XWPFDocument document = converter.convertHtmlToDocx(html);
        Assert.assertNotNull(document);
        Assert.assertTrue(document.getParagraphs().size() > 0);
        
        try {
            document.close();
        } catch (IOException e) {
            Assert.fail("Failed to close document");
        }
    }
    
    @Test
    public void testConvertHtmlWithLineBreaks() throws BoundesuWordsException {
        String html = "<p>第一行<br/>第二行<br/>第三行</p>";
        
        XWPFDocument document = converter.convertHtmlToDocx(html);
        Assert.assertNotNull(document);
        Assert.assertTrue(document.getParagraphs().size() > 0);
        
        try {
            document.close();
        } catch (IOException e) {
            Assert.fail("Failed to close document");
        }
    }
    
    @Test
    public void testConvertHtmlWithMultipleParagraphs() throws BoundesuWordsException {
        String html = "<p>段落1</p><p>段落2</p><p>段落3</p>";
        
        XWPFDocument document = converter.convertHtmlToDocx(html);
        Assert.assertNotNull(document);
        Assert.assertTrue(document.getParagraphs().size() >= 3);
        
        try {
            document.close();
        } catch (IOException e) {
            Assert.fail("Failed to close document");
        }
    }
}