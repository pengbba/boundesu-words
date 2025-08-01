package com.boundesu.words.core.service;

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
 * BoundesuWordsService 测试类
 * 
 * @author Boundesu
 * @version 1.0.0
 */
public class BoundesuWordsServiceTest {
    
    private BoundesuWordsService service;
    private Path tempDir;
    
    @BeforeMethod
    public void setUp() throws IOException {
        service = new BoundesuWordsService();
        tempDir = Files.createTempDirectory("boundesu-service-test");
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
        Map<String, String> info = service.getSDKInfo();
        
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
    public void testConvertHtmlToDocx() throws BoundesuWordsException {
        String htmlContent = "<html><body><h1>Test Title</h1><p>Test paragraph</p></body></html>";
        String outputPath = new File(tempDir.toFile(), "test.docx").getAbsolutePath();
        
        service.convertHtmlToDocx(htmlContent, outputPath);
        
        File outputFile = new File(outputPath);
        Assert.assertTrue(outputFile.exists());
        Assert.assertTrue(outputFile.length() > 0);
    }
    
    @Test
    public void testConvertHtmlFileToDocx() throws BoundesuWordsException, IOException {
        // 创建临时HTML文件
        String htmlContent = "<html><body><h1>File Test</h1><p>Test from file</p></body></html>";
        File htmlFile = new File(tempDir.toFile(), "test.html");
        Files.write(htmlFile.toPath(), htmlContent.getBytes("UTF-8"));
        
        String outputPath = new File(tempDir.toFile(), "test_from_file.docx").getAbsolutePath();
        
        service.convertHtmlFileToDocx(htmlFile.getAbsolutePath(), outputPath);
        
        File outputFile = new File(outputPath);
        Assert.assertTrue(outputFile.exists());
        Assert.assertTrue(outputFile.length() > 0);
    }
    
    @Test
    public void testConvertXmlToDocx() throws BoundesuWordsException {
        String xmlContent = "<?xml version=\"1.0\"?><document><title>Test Title</title><paragraph>Test content</paragraph></document>";
        String outputPath = new File(tempDir.toFile(), "test_xml.docx").getAbsolutePath();
        
        service.convertXmlToDocx(xmlContent, outputPath);
        
        File outputFile = new File(outputPath);
        Assert.assertTrue(outputFile.exists());
        Assert.assertTrue(outputFile.length() > 0);
    }
    
    @Test
    public void testConvertXmlFileToDocx() throws BoundesuWordsException, IOException {
        // 创建临时XML文件
        String xmlContent = "<?xml version=\"1.0\"?><document><title>XML File Test</title><paragraph>Test from XML file</paragraph></document>";
        File xmlFile = new File(tempDir.toFile(), "test.xml");
        Files.write(xmlFile.toPath(), xmlContent.getBytes("UTF-8"));
        
        String outputPath = new File(tempDir.toFile(), "test_xml_from_file.docx").getAbsolutePath();
        
        service.convertXmlFileToDocx(xmlFile.getAbsolutePath(), outputPath);
        
        File outputFile = new File(outputPath);
        Assert.assertTrue(outputFile.exists());
        Assert.assertTrue(outputFile.length() > 0);
    }
    
    @Test
    public void testAutoConvertToDocxHtml() throws BoundesuWordsException, IOException {
        // 创建临时HTML文件
        String htmlContent = "<html><body><h1>Auto Convert Test</h1></body></html>";
        File htmlFile = new File(tempDir.toFile(), "auto_test.html");
        Files.write(htmlFile.toPath(), htmlContent.getBytes("UTF-8"));
        
        String outputPath = new File(tempDir.toFile(), "auto_convert.docx").getAbsolutePath();
        
        service.autoConvertToDocx(htmlFile.getAbsolutePath(), outputPath);
        
        File outputFile = new File(outputPath);
        Assert.assertTrue(outputFile.exists());
        Assert.assertTrue(outputFile.length() > 0);
    }
    
    @Test
    public void testAutoConvertToDocxXml() throws BoundesuWordsException, IOException {
        // 创建临时XML文件
        String xmlContent = "<?xml version=\"1.0\"?><document><title>Auto XML Test</title></document>";
        File xmlFile = new File(tempDir.toFile(), "auto_test.xml");
        Files.write(xmlFile.toPath(), xmlContent.getBytes("UTF-8"));
        
        String outputPath = new File(tempDir.toFile(), "auto_convert_xml.docx").getAbsolutePath();
        
        service.autoConvertToDocx(xmlFile.getAbsolutePath(), outputPath);
        
        File outputFile = new File(outputPath);
        Assert.assertTrue(outputFile.exists());
        Assert.assertTrue(outputFile.length() > 0);
    }
    
    @Test
    public void testBatchConvertToDocx() throws IOException {
        // 创建多个测试文件
        String htmlContent = "<html><body><h1>Batch Test HTML</h1></body></html>";
        String xmlContent = "<?xml version=\"1.0\"?><document><title>Batch Test XML</title></document>";
        
        File htmlFile = new File(tempDir.toFile(), "batch1.html");
        File xmlFile = new File(tempDir.toFile(), "batch2.xml");
        
        Files.write(htmlFile.toPath(), htmlContent.getBytes("UTF-8"));
        Files.write(xmlFile.toPath(), xmlContent.getBytes("UTF-8"));
        
        String[] inputFiles = {htmlFile.getAbsolutePath(), xmlFile.getAbsolutePath()};
        String outputDir = new File(tempDir.toFile(), "batch_output").getAbsolutePath();
        
        Map<String, String> results = service.batchConvertToDocx(inputFiles, outputDir);
        
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
        Map<String, Object> status = service.getServiceStatus();
        
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
        Assert.assertNotNull(service.getHtmlConverter());
        Assert.assertNotNull(service.getXmlConverter());
    }
    
    @Test(expectedExceptions = BoundesuWordsException.class)
    public void testConvertHtmlToDocxWithEmptyContent() throws BoundesuWordsException {
        String outputPath = new File(tempDir.toFile(), "empty_test.docx").getAbsolutePath();
        service.convertHtmlToDocx("", outputPath);
    }
    
    @Test(expectedExceptions = BoundesuWordsException.class)
    public void testConvertHtmlToDocxWithNullContent() throws BoundesuWordsException {
        String outputPath = new File(tempDir.toFile(), "null_test.docx").getAbsolutePath();
        service.convertHtmlToDocx(null, outputPath);
    }
    
    @Test(expectedExceptions = BoundesuWordsException.class)
    public void testConvertHtmlToDocxWithEmptyOutputPath() throws BoundesuWordsException {
        String htmlContent = "<html><body><h1>Test</h1></body></html>";
        service.convertHtmlToDocx(htmlContent, "");
    }
    
    @Test(expectedExceptions = BoundesuWordsException.class)
    public void testConvertHtmlToDocxWithInvalidExtension() throws BoundesuWordsException {
        String htmlContent = "<html><body><h1>Test</h1></body></html>";
        String outputPath = new File(tempDir.toFile(), "test.txt").getAbsolutePath();
        service.convertHtmlToDocx(htmlContent, outputPath);
    }
    
    @Test(expectedExceptions = BoundesuWordsException.class)
    public void testConvertHtmlFileToDocxWithNonExistentFile() throws BoundesuWordsException {
        String outputPath = new File(tempDir.toFile(), "test.docx").getAbsolutePath();
        service.convertHtmlFileToDocx("/nonexistent/file.html", outputPath);
    }
    
    @Test(expectedExceptions = BoundesuWordsException.class)
    public void testConvertXmlToDocxWithEmptyContent() throws BoundesuWordsException {
        String outputPath = new File(tempDir.toFile(), "empty_xml_test.docx").getAbsolutePath();
        service.convertXmlToDocx("", outputPath);
    }
    
    @Test(expectedExceptions = BoundesuWordsException.class)
    public void testConvertXmlFileToDocxWithNonExistentFile() throws BoundesuWordsException {
        String outputPath = new File(tempDir.toFile(), "test_xml.docx").getAbsolutePath();
        service.convertXmlFileToDocx("/nonexistent/file.xml", outputPath);
    }
    
    @Test(expectedExceptions = BoundesuWordsException.class)
    public void testAutoConvertToDocxWithUnsupportedFileType() throws BoundesuWordsException, IOException {
        // 创建不支持的文件类型
        File txtFile = new File(tempDir.toFile(), "test.txt");
        Files.write(txtFile.toPath(), "test content".getBytes("UTF-8"));
        
        String outputPath = new File(tempDir.toFile(), "test.docx").getAbsolutePath();
        service.autoConvertToDocx(txtFile.getAbsolutePath(), outputPath);
    }
    
    @Test(expectedExceptions = BoundesuWordsException.class)
    public void testAutoConvertToDocxWithNonExistentFile() throws BoundesuWordsException {
        String outputPath = new File(tempDir.toFile(), "test.docx").getAbsolutePath();
        service.autoConvertToDocx("/nonexistent/file.html", outputPath);
    }
    
    @Test
    public void testBatchConvertWithEmptyArray() {
        String outputDir = new File(tempDir.toFile(), "empty_batch").getAbsolutePath();
        Map<String, String> results = service.batchConvertToDocx(new String[0], outputDir);
        
        Assert.assertNotNull(results);
        Assert.assertTrue(results.isEmpty());
    }
    
    @Test
    public void testBatchConvertWithNullArray() {
        String outputDir = new File(tempDir.toFile(), "null_batch").getAbsolutePath();
        Map<String, String> results = service.batchConvertToDocx(null, outputDir);
        
        Assert.assertNotNull(results);
        Assert.assertTrue(results.isEmpty());
    }
    
    @Test
    public void testBatchConvertWithInvalidFiles() {
        String[] inputFiles = {"/nonexistent/file1.html", "/nonexistent/file2.xml", ""};
        String outputDir = new File(tempDir.toFile(), "invalid_batch").getAbsolutePath();
        
        Map<String, String> results = service.batchConvertToDocx(inputFiles, outputDir);
        
        Assert.assertNotNull(results);
        Assert.assertEquals(results.size(), 3);
        
        // 所有结果都应该是错误信息
        for (String result : results.values()) {
            Assert.assertTrue(result.startsWith("转换失败") || result.startsWith("输入文件"));
        }
    }
}