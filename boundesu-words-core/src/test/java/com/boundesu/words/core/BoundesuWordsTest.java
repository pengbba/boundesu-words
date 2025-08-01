package com.boundesu.words.core;

import com.boundesu.words.common.exception.BoundesuWordsException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

/**
 * BoundesuWords 测试类
 * 
 * @author Boundesu
 * @version 1.0.0
 */
public class BoundesuWordsTest {
    
    private Path tempDir;
    
    @BeforeMethod
    public void setUp() throws IOException {
        tempDir = Files.createTempDirectory("boundesu-words-test");
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
    public void testGetSDKInfo() {
        Map<String, String> info = BoundesuWords.getSDKInfo();
        
        Assert.assertNotNull(info);
        Assert.assertTrue(info.containsKey("name"));
        Assert.assertTrue(info.containsKey("version"));
        Assert.assertTrue(info.containsKey("author"));
        Assert.assertTrue(info.containsKey("description"));
        Assert.assertTrue(info.containsKey("jdk"));
        Assert.assertTrue(info.containsKey("license"));
        Assert.assertTrue(info.containsKey("initialized_at"));
        
        Assert.assertNotNull(info.get("name"));
        Assert.assertNotNull(info.get("version"));
        Assert.assertNotNull(info.get("author"));
    }
    
    @Test
    public void testGetVersion() {
        String version = BoundesuWords.getVersion();
        Assert.assertNotNull(version);
        Assert.assertFalse(version.trim().isEmpty());
    }
    
    @Test
    public void testConvertHtmlToDocx() throws BoundesuWordsException {
        String htmlContent = "<html><body><h1>Static Test Title</h1><p>Static test paragraph</p></body></html>";
        String outputPath = new File(tempDir.toFile(), "static_test.docx").getAbsolutePath();
        
        BoundesuWords.convertHtmlToDocx(htmlContent, outputPath);
        
        File outputFile = new File(outputPath);
        Assert.assertTrue(outputFile.exists());
        Assert.assertTrue(outputFile.length() > 0);
    }
    
    @Test
    public void testConvertHtmlFileToDocx() throws BoundesuWordsException, IOException {
        // 创建临时HTML文件
        String htmlContent = "<html><body><h1>Static File Test</h1><p>Static test from file</p></body></html>";
        File htmlFile = new File(tempDir.toFile(), "static_test.html");
        Files.write(htmlFile.toPath(), htmlContent.getBytes("UTF-8"));
        
        String outputPath = new File(tempDir.toFile(), "static_test_from_file.docx").getAbsolutePath();
        
        BoundesuWords.convertHtmlFileToDocx(htmlFile.getAbsolutePath(), outputPath);
        
        File outputFile = new File(outputPath);
        Assert.assertTrue(outputFile.exists());
        Assert.assertTrue(outputFile.length() > 0);
    }
    
    @Test
    public void testConvertXmlToDocx() throws BoundesuWordsException {
        String xmlContent = "<?xml version=\"1.0\"?><document><title>Static XML Test Title</title><paragraph>Static XML test content</paragraph></document>";
        String outputPath = new File(tempDir.toFile(), "static_test_xml.docx").getAbsolutePath();
        
        BoundesuWords.convertXmlToDocx(xmlContent, outputPath);
        
        File outputFile = new File(outputPath);
        Assert.assertTrue(outputFile.exists());
        Assert.assertTrue(outputFile.length() > 0);
    }
    
    @Test
    public void testConvertXmlFileToDocx() throws BoundesuWordsException, IOException {
        // 创建临时XML文件
        String xmlContent = "<?xml version=\"1.0\"?><document><title>Static XML File Test</title><paragraph>Static test from XML file</paragraph></document>";
        File xmlFile = new File(tempDir.toFile(), "static_test.xml");
        Files.write(xmlFile.toPath(), xmlContent.getBytes("UTF-8"));
        
        String outputPath = new File(tempDir.toFile(), "static_test_xml_from_file.docx").getAbsolutePath();
        
        BoundesuWords.convertXmlFileToDocx(xmlFile.getAbsolutePath(), outputPath);
        
        File outputFile = new File(outputPath);
        Assert.assertTrue(outputFile.exists());
        Assert.assertTrue(outputFile.length() > 0);
    }
    
    @Test
    public void testAutoConvertToDocxHtml() throws BoundesuWordsException, IOException {
        // 创建临时HTML文件
        String htmlContent = "<html><body><h1>Static Auto Convert Test</h1></body></html>";
        File htmlFile = new File(tempDir.toFile(), "static_auto_test.html");
        Files.write(htmlFile.toPath(), htmlContent.getBytes("UTF-8"));
        
        String outputPath = new File(tempDir.toFile(), "static_auto_convert.docx").getAbsolutePath();
        
        BoundesuWords.autoConvertToDocx(htmlFile.getAbsolutePath(), outputPath);
        
        File outputFile = new File(outputPath);
        Assert.assertTrue(outputFile.exists());
        Assert.assertTrue(outputFile.length() > 0);
    }
    
    @Test
    public void testAutoConvertToDocxXml() throws BoundesuWordsException, IOException {
        // 创建临时XML文件
        String xmlContent = "<?xml version=\"1.0\"?><document><title>Static Auto XML Test</title></document>";
        File xmlFile = new File(tempDir.toFile(), "static_auto_test.xml");
        Files.write(xmlFile.toPath(), xmlContent.getBytes("UTF-8"));
        
        String outputPath = new File(tempDir.toFile(), "static_auto_convert_xml.docx").getAbsolutePath();
        
        BoundesuWords.autoConvertToDocx(xmlFile.getAbsolutePath(), outputPath);
        
        File outputFile = new File(outputPath);
        Assert.assertTrue(outputFile.exists());
        Assert.assertTrue(outputFile.length() > 0);
    }
    
    @Test
    public void testBatchConvertToDocx() throws IOException {
        // 创建多个测试文件
        String htmlContent = "<html><body><h1>Static Batch Test HTML</h1></body></html>";
        String xmlContent = "<?xml version=\"1.0\"?><document><title>Static Batch Test XML</title></document>";
        
        File htmlFile = new File(tempDir.toFile(), "static_batch1.html");
        File xmlFile = new File(tempDir.toFile(), "static_batch2.xml");
        
        Files.write(htmlFile.toPath(), htmlContent.getBytes("UTF-8"));
        Files.write(xmlFile.toPath(), xmlContent.getBytes("UTF-8"));
        
        String[] inputFiles = {htmlFile.getAbsolutePath(), xmlFile.getAbsolutePath()};
        String outputDir = new File(tempDir.toFile(), "static_batch_output").getAbsolutePath();
        
        Map<String, String> results = BoundesuWords.batchConvertToDocx(inputFiles, outputDir);
        
        Assert.assertNotNull(results);
        Assert.assertEquals(results.size(), 2);
        
        // 验证输出文件存在
        for (String result : results.values()) {
            if (!result.startsWith("转换失败")) {
                File outputFile = new File(result);
                Assert.assertTrue(outputFile.exists());
                Assert.assertTrue(outputFile.length() > 0);
            }
        }
    }
    
    @Test
    public void testGetServiceStatus() {
        Map<String, Object> status = BoundesuWords.getServiceStatus();
        
        Assert.assertNotNull(status);
        Assert.assertTrue(status.containsKey("service_name"));
        Assert.assertTrue(status.containsKey("status"));
        Assert.assertTrue(status.containsKey("html_converter_available"));
        Assert.assertTrue(status.containsKey("xml_converter_available"));
        Assert.assertTrue(status.containsKey("current_time"));
        Assert.assertTrue(status.containsKey("sdk_info"));
        
        Assert.assertEquals(status.get("service_name"), "BoundesuWordsService");
        Assert.assertEquals(status.get("status"), "running");
        Assert.assertEquals(status.get("html_converter_available"), true);
        Assert.assertEquals(status.get("xml_converter_available"), true);
    }
    
    @Test
    public void testGetConverters() {
        Assert.assertNotNull(BoundesuWords.getHtmlConverter());
        Assert.assertNotNull(BoundesuWords.getXmlConverter());
    }
    
    @Test(expectedExceptions = BoundesuWordsException.class)
    public void testConvertHtmlToDocxWithEmptyContent() throws BoundesuWordsException {
        String outputPath = new File(tempDir.toFile(), "static_empty_test.docx").getAbsolutePath();
        BoundesuWords.convertHtmlToDocx("", outputPath);
    }
    
    @Test(expectedExceptions = BoundesuWordsException.class)
    public void testConvertHtmlToDocxWithNullContent() throws BoundesuWordsException {
        String outputPath = new File(tempDir.toFile(), "static_null_test.docx").getAbsolutePath();
        BoundesuWords.convertHtmlToDocx(null, outputPath);
    }
    
    @Test(expectedExceptions = BoundesuWordsException.class)
    public void testConvertHtmlToDocxWithEmptyOutputPath() throws BoundesuWordsException {
        String htmlContent = "<html><body><h1>Test</h1></body></html>";
        BoundesuWords.convertHtmlToDocx(htmlContent, "");
    }
    
    @Test(expectedExceptions = BoundesuWordsException.class)
    public void testConvertHtmlToDocxWithInvalidExtension() throws BoundesuWordsException {
        String htmlContent = "<html><body><h1>Test</h1></body></html>";
        String outputPath = new File(tempDir.toFile(), "static_test.txt").getAbsolutePath();
        BoundesuWords.convertHtmlToDocx(htmlContent, outputPath);
    }
    
    @Test(expectedExceptions = BoundesuWordsException.class)
    public void testConvertHtmlFileToDocxWithNonExistentFile() throws BoundesuWordsException {
        String outputPath = new File(tempDir.toFile(), "static_test.docx").getAbsolutePath();
        BoundesuWords.convertHtmlFileToDocx("/nonexistent/file.html", outputPath);
    }
    
    @Test(expectedExceptions = BoundesuWordsException.class)
    public void testConvertXmlToDocxWithEmptyContent() throws BoundesuWordsException {
        String outputPath = new File(tempDir.toFile(), "static_empty_xml_test.docx").getAbsolutePath();
        BoundesuWords.convertXmlToDocx("", outputPath);
    }
    
    @Test(expectedExceptions = BoundesuWordsException.class)
    public void testConvertXmlFileToDocxWithNonExistentFile() throws BoundesuWordsException {
        String outputPath = new File(tempDir.toFile(), "static_test_xml.docx").getAbsolutePath();
        BoundesuWords.convertXmlFileToDocx("/nonexistent/file.xml", outputPath);
    }
    
    @Test(expectedExceptions = BoundesuWordsException.class)
    public void testAutoConvertToDocxWithUnsupportedFileType() throws BoundesuWordsException, IOException {
        // 创建不支持的文件类型
        File txtFile = new File(tempDir.toFile(), "static_test.txt");
        Files.write(txtFile.toPath(), "test content".getBytes("UTF-8"));
        
        String outputPath = new File(tempDir.toFile(), "static_test.docx").getAbsolutePath();
        BoundesuWords.autoConvertToDocx(txtFile.getAbsolutePath(), outputPath);
    }
    
    @Test(expectedExceptions = BoundesuWordsException.class)
    public void testAutoConvertToDocxWithNonExistentFile() throws BoundesuWordsException {
        String outputPath = new File(tempDir.toFile(), "static_test.docx").getAbsolutePath();
        BoundesuWords.autoConvertToDocx("/nonexistent/file.html", outputPath);
    }
    
    @Test
    public void testBatchConvertWithEmptyArray() {
        String outputDir = new File(tempDir.toFile(), "static_empty_batch").getAbsolutePath();
        Map<String, String> results = BoundesuWords.batchConvertToDocx(new String[0], outputDir);
        
        Assert.assertNotNull(results);
        Assert.assertTrue(results.isEmpty());
    }
    
    @Test
    public void testBatchConvertWithNullArray() {
        String outputDir = new File(tempDir.toFile(), "static_null_batch").getAbsolutePath();
        Map<String, String> results = BoundesuWords.batchConvertToDocx(null, outputDir);
        
        Assert.assertNotNull(results);
        Assert.assertTrue(results.isEmpty());
    }
    
    @Test
    public void testBatchConvertWithInvalidFiles() {
        String[] inputFiles = {"/nonexistent/file1.html", "/nonexistent/file2.xml", ""};
        String outputDir = new File(tempDir.toFile(), "static_invalid_batch").getAbsolutePath();
        
        Map<String, String> results = BoundesuWords.batchConvertToDocx(inputFiles, outputDir);
        
        Assert.assertNotNull(results);
        Assert.assertEquals(results.size(), 3);
        
        // 所有结果都应该是错误信息
        for (String result : results.values()) {
            Assert.assertTrue(result.startsWith("转换失败") || result.startsWith("输入文件"));
        }
    }
    
    @Test
    public void testMultipleStaticCalls() throws BoundesuWordsException {
        // 测试多次静态调用不会产生问题
        String htmlContent = "<html><body><h1>Multiple Calls Test</h1></body></html>";
        
        for (int i = 0; i < 3; i++) {
            String outputPath = new File(tempDir.toFile(), "multiple_test_" + i + ".docx").getAbsolutePath();
            BoundesuWords.convertHtmlToDocx(htmlContent, outputPath);
            
            File outputFile = new File(outputPath);
            Assert.assertTrue(outputFile.exists());
            Assert.assertTrue(outputFile.length() > 0);
        }
    }
}