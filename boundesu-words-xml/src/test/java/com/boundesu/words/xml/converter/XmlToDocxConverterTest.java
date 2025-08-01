package com.boundesu.words.xml.converter;

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
 * XmlToDocxConverter 测试类
 * 
 * @author Boundesu
 * @version 1.0.0
 */
public class XmlToDocxConverterTest {
    
    private XmlToDocxConverter converter;
    private Path tempDir;
    
    @BeforeMethod
    public void setUp() throws IOException {
        converter = new XmlToDocxConverter();
        tempDir = Files.createTempDirectory("boundesu-xml-test");
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
    public void testConvertSimpleXml() throws BoundesuWordsException {
        String xml = "<document><title>标题</title><paragraph>这是一个段落。</paragraph></document>";
        
        XWPFDocument document = converter.convertXmlToDocx(xml);
        Assert.assertNotNull(document);
        Assert.assertTrue(document.getParagraphs().size() > 0);
        
        try {
            document.close();
        } catch (IOException e) {
            Assert.fail("Failed to close document");
        }
    }
    
    @Test
    public void testConvertComplexXml() throws BoundesuWordsException {
        String xml = "<document>" +
                "<title>主标题</title>" +
                "<h1>一级标题</h1>" +
                "<h2>二级标题</h2>" +
                "<paragraph>这是第一个段落。</paragraph>" +
                "<paragraph>这是第二个段落。</paragraph>" +
                "<list>" +
                "<item>列表项1</item>" +
                "<item>列表项2</item>" +
                "</list>" +
                "</document>";
        
        XWPFDocument document = converter.convertXmlToDocx(xml);
        Assert.assertNotNull(document);
        Assert.assertTrue(document.getParagraphs().size() > 0);
        
        try {
            document.close();
        } catch (IOException e) {
            Assert.fail("Failed to close document");
        }
    }
    
    @Test
    public void testConvertXmlWithTable() throws BoundesuWordsException {
        String xml = "<document>" +
                "<table>" +
                "<row><cell>标题1</cell><cell>标题2</cell></row>" +
                "<row><cell>数据1</cell><cell>数据2</cell></row>" +
                "<row><cell>数据3</cell><cell>数据4</cell></row>" +
                "</table>" +
                "</document>";
        
        XWPFDocument document = converter.convertXmlToDocx(xml);
        Assert.assertNotNull(document);
        Assert.assertTrue(document.getTables().size() > 0);
        
        try {
            document.close();
        } catch (IOException e) {
            Assert.fail("Failed to close document");
        }
    }
    
    @Test
    public void testConvertXmlFromInputStream() throws BoundesuWordsException, IOException {
        String xml = "<document><title>从输入流转换</title><text>这是从输入流读取的XML内容。</text></document>";
        
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(xml.getBytes("UTF-8"))) {
            XWPFDocument document = converter.convertXmlToDocx(inputStream);
            Assert.assertNotNull(document);
            Assert.assertTrue(document.getParagraphs().size() > 0);
            
            document.close();
        }
    }
    
    @Test
    public void testConvertXmlFile() throws BoundesuWordsException, IOException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<document>" +
                "<title>文件转换测试</title>" +
                "<paragraph>测试从XML文件转换为DOCX。</paragraph>" +
                "</document>";
        
        // 创建临时XML文件
        File xmlFile = new File(tempDir.toFile(), "test.xml");
        try (FileWriter writer = new FileWriter(xmlFile, java.nio.charset.StandardCharsets.UTF_8)) {
            writer.write(xml);
        }
        
        File outputFile = new File(tempDir.toFile(), "test-output.docx");
        
        // 转换文件
        converter.convertXmlFileToDocx(xmlFile.getAbsolutePath(), outputFile.getAbsolutePath());
        
        Assert.assertTrue(outputFile.exists());
        Assert.assertTrue(outputFile.length() > 0);
    }
    
    @Test
    public void testConvertXmlWithOptions() throws BoundesuWordsException {
        String xml = "<document><title>选项测试</title><paragraph>测试转换选项。</paragraph></document>";
        
        XmlToDocxConverter.ConversionOptions options = new XmlToDocxConverter.ConversionOptions();
        options.setTitle("测试文档");
        options.setAuthor("测试作者");
        options.setPreserveFormatting(true);
        
        File outputFile = new File(tempDir.toFile(), "test-options.docx");
        
        converter.convertXmlToDocx(xml, outputFile.getAbsolutePath(), options);
        
        Assert.assertTrue(outputFile.exists());
        Assert.assertTrue(outputFile.length() > 0);
    }
    
    @Test(expectedExceptions = BoundesuWordsException.class)
    public void testConvertEmptyXml() throws BoundesuWordsException {
        converter.convertXmlToDocx("", "output.docx");
    }
    
    @Test(expectedExceptions = BoundesuWordsException.class)
    public void testConvertNullXml() throws BoundesuWordsException {
        converter.convertXmlToDocx((String) null, "output.docx");
    }
    
    @Test(expectedExceptions = BoundesuWordsException.class)
    public void testConvertWithEmptyOutputPath() throws BoundesuWordsException {
        String xml = "<document><title>测试</title></document>";
        converter.convertXmlToDocx(xml, "");
    }
    
    @Test(expectedExceptions = BoundesuWordsException.class)
    public void testConvertWithNullOutputPath() throws BoundesuWordsException {
        String xml = "<document><title>测试</title></document>";
        converter.convertXmlToDocx(xml, (String) null);
    }
    
    @Test(expectedExceptions = BoundesuWordsException.class)
    public void testConvertInvalidXml() throws BoundesuWordsException {
        String invalidXml = "<document><title>未闭合标签</document>";
        converter.convertXmlToDocx(invalidXml, "output.docx");
    }
    
    @Test
    public void testConvertXmlWithSpecialCharacters() throws BoundesuWordsException {
        String xml = "<document>" +
                "<title>特殊字符测试</title>" +
                "<paragraph>包含特殊字符：&amp; &lt; &gt; &quot; &#39;</paragraph>" +
                "<paragraph>中文字符：你好世界</paragraph>" +
                "<paragraph>数字和符号：123 !@#$%^&amp;*()</paragraph>" +
                "</document>";
        
        XWPFDocument document = converter.convertXmlToDocx(xml);
        Assert.assertNotNull(document);
        Assert.assertTrue(document.getParagraphs().size() > 0);
        
        try {
            document.close();
        } catch (IOException e) {
            Assert.fail("Failed to close document");
        }
    }
    
    @Test
    public void testConvertXmlWithNestedElements() throws BoundesuWordsException {
        String xml = "<document>" +
                "<section>" +
                "<title>章节标题</title>" +
                "<subsection>" +
                "<h2>子章节</h2>" +
                "<paragraph>嵌套段落内容。</paragraph>" +
                "</subsection>" +
                "</section>" +
                "</document>";
        
        XWPFDocument document = converter.convertXmlToDocx(xml);
        Assert.assertNotNull(document);
        Assert.assertTrue(document.getParagraphs().size() > 0);
        
        try {
            document.close();
        } catch (IOException e) {
            Assert.fail("Failed to close document");
        }
    }
    
    @Test
    public void testConvertXmlWithAttributes() throws BoundesuWordsException {
        String xml = "<document>" +
                "<title style=\"bold\">带属性的标题</title>" +
                "<paragraph align=\"center\">居中段落</paragraph>" +
                "<text color=\"red\">红色文本</text>" +
                "</document>";
        
        XWPFDocument document = converter.convertXmlToDocx(xml);
        Assert.assertNotNull(document);
        Assert.assertTrue(document.getParagraphs().size() > 0);
        
        try {
            document.close();
        } catch (IOException e) {
            Assert.fail("Failed to close document");
        }
    }
}