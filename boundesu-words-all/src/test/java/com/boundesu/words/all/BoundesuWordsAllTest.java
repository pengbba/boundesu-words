package com.boundesu.words.all;

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
 * BoundesuWordsAll 测试类
 * 
 * @author Boundesu
 * @version 1.0.0
 */
public class BoundesuWordsAllTest {
    
    private BoundesuWordsAll boundesuWordsAll;
    private Path tempDir;
    
    @BeforeMethod
    public void setUp() throws IOException {
        boundesuWordsAll = new BoundesuWordsAll();
        tempDir = Files.createTempDirectory("boundesu-words-all-test");
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
        Map<String, String> info = boundesuWordsAll.getSDKInfo();
        
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
        String version = boundesuWordsAll.getVersion();
        Assert.assertNotNull(version);
        Assert.assertFalse(version.trim().isEmpty());
    }
    
    @Test
    public void testConvertHtmlToDocx() throws BoundesuWordsException {
        String htmlContent = "<html><body><h1>All Test Title</h1><p>All test paragraph</p></body></html>";
        String outputPath = new File(tempDir.toFile(), "all_test.docx").getAbsolutePath();
        
        boundesuWordsAll.convertHtmlToDocx(htmlContent, outputPath);
        
        File outputFile = new File(outputPath);
        Assert.assertTrue(outputFile.exists());
        Assert.assertTrue(outputFile.length() > 0);
    }
    
    @Test
    public void testConvertHtmlFileToDocx() throws BoundesuWordsException, IOException {
        // 创建临时HTML文件
        String htmlContent = "<html><body><h1>All File Test</h1><p>All test from file</p></body></html>";
        File htmlFile = new File(tempDir.toFile(), "all_test.html");
        Files.write(htmlFile.toPath(), htmlContent.getBytes("UTF-8"));
        
        String outputPath = new File(tempDir.toFile(), "all_test_from_file.docx").getAbsolutePath();
        
        boundesuWordsAll.convertHtmlFileToDocx(htmlFile.getAbsolutePath(), outputPath);
        
        File outputFile = new File(outputPath);
        Assert.assertTrue(outputFile.exists());
        Assert.assertTrue(outputFile.length() > 0);
    }
    
    @Test
    public void testConvertXmlToDocx() throws BoundesuWordsException {
        String xmlContent = "<?xml version=\"1.0\"?><document><title>All XML Test Title</title><paragraph>All XML test content</paragraph></document>";
        String outputPath = new File(tempDir.toFile(), "all_test_xml.docx").getAbsolutePath();
        
        boundesuWordsAll.convertXmlToDocx(xmlContent, outputPath);
        
        File outputFile = new File(outputPath);
        Assert.assertTrue(outputFile.exists());
        Assert.assertTrue(outputFile.length() > 0);
    }
    
    @Test
    public void testConvertXmlFileToDocx() throws BoundesuWordsException, IOException {
        // 创建临时XML文件
        String xmlContent = "<?xml version=\"1.0\"?><document><title>All XML File Test</title><paragraph>All test from XML file</paragraph></document>";
        File xmlFile = new File(tempDir.toFile(), "all_test.xml");
        Files.write(xmlFile.toPath(), xmlContent.getBytes("UTF-8"));
        
        String outputPath = new File(tempDir.toFile(), "all_test_xml_from_file.docx").getAbsolutePath();
        
        boundesuWordsAll.convertXmlFileToDocx(xmlFile.getAbsolutePath(), outputPath);
        
        File outputFile = new File(outputPath);
        Assert.assertTrue(outputFile.exists());
        Assert.assertTrue(outputFile.length() > 0);
    }
    
    @Test
    public void testAutoConvertToDocxHtml() throws BoundesuWordsException, IOException {
        // 创建临时HTML文件
        String htmlContent = "<html><body><h1>All Auto Convert Test</h1></body></html>";
        File htmlFile = new File(tempDir.toFile(), "all_auto_test.html");
        Files.write(htmlFile.toPath(), htmlContent.getBytes("UTF-8"));
        
        String outputPath = new File(tempDir.toFile(), "all_auto_convert.docx").getAbsolutePath();
        
        boundesuWordsAll.autoConvertToDocx(htmlFile.getAbsolutePath(), outputPath);
        
        File outputFile = new File(outputPath);
        Assert.assertTrue(outputFile.exists());
        Assert.assertTrue(outputFile.length() > 0);
    }
    
    @Test
    public void testAutoConvertToDocxXml() throws BoundesuWordsException, IOException {
        // 创建临时XML文件
        String xmlContent = "<?xml version=\"1.0\"?><document><title>All Auto XML Test</title></document>";
        File xmlFile = new File(tempDir.toFile(), "all_auto_test.xml");
        Files.write(xmlFile.toPath(), xmlContent.getBytes("UTF-8"));
        
        String outputPath = new File(tempDir.toFile(), "all_auto_convert_xml.docx").getAbsolutePath();
        
        boundesuWordsAll.autoConvertToDocx(xmlFile.getAbsolutePath(), outputPath);
        
        File outputFile = new File(outputPath);
        Assert.assertTrue(outputFile.exists());
        Assert.assertTrue(outputFile.length() > 0);
    }
    
    @Test
    public void testBatchConvertToDocx() throws IOException {
        // 创建多个测试文件
        String htmlContent = "<html><body><h1>All Batch Test HTML</h1></body></html>";
        String xmlContent = "<?xml version=\"1.0\"?><document><title>All Batch Test XML</title></document>";
        
        File htmlFile = new File(tempDir.toFile(), "all_batch1.html");
        File xmlFile = new File(tempDir.toFile(), "all_batch2.xml");
        
        Files.write(htmlFile.toPath(), htmlContent.getBytes("UTF-8"));
        Files.write(xmlFile.toPath(), xmlContent.getBytes("UTF-8"));
        
        String[] inputFiles = {htmlFile.getAbsolutePath(), xmlFile.getAbsolutePath()};
        String outputDir = new File(tempDir.toFile(), "all_batch_output").getAbsolutePath();
        
        Map<String, String> results = boundesuWordsAll.batchConvertToDocx(inputFiles, outputDir);
        
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
        Map<String, Object> status = boundesuWordsAll.getServiceStatus();
        
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
        Assert.assertNotNull(boundesuWordsAll.getHtmlConverter());
        Assert.assertNotNull(boundesuWordsAll.getXmlConverter());
    }
    
    @Test
    public void testGetCoreService() {
        Assert.assertNotNull(boundesuWordsAll.getCoreService());
    }
    
    @Test
    public void testDirectConverterAccess() throws BoundesuWordsException {
        // 测试直接访问HTML转换器
        String htmlContent = "<html><body><h1>Direct HTML Test</h1></body></html>";
        String outputPath = new File(tempDir.toFile(), "direct_html_test.docx").getAbsolutePath();
        
        boundesuWordsAll.getHtmlConverter().convertHtmlToDocx(htmlContent, outputPath);
        
        File outputFile = new File(outputPath);
        Assert.assertTrue(outputFile.exists());
        Assert.assertTrue(outputFile.length() > 0);
    }
    
    @Test
    public void testDirectXmlConverterAccess() throws BoundesuWordsException {
        // 测试直接访问XML转换器
        String xmlContent = "<?xml version=\"1.0\"?><document><title>Direct XML Test</title></document>";
        String outputPath = new File(tempDir.toFile(), "direct_xml_test.docx").getAbsolutePath();
        
        boundesuWordsAll.getXmlConverter().convertXmlToDocx(xmlContent, outputPath);
        
        File outputFile = new File(outputPath);
        Assert.assertTrue(outputFile.exists());
        Assert.assertTrue(outputFile.length() > 0);
    }
    
    @Test(expectedExceptions = BoundesuWordsException.class)
    public void testConvertHtmlToDocxWithEmptyContent() throws BoundesuWordsException {
        String outputPath = new File(tempDir.toFile(), "all_empty_test.docx").getAbsolutePath();
        boundesuWordsAll.convertHtmlToDocx("", outputPath);
    }
    
    @Test(expectedExceptions = BoundesuWordsException.class)
    public void testConvertHtmlToDocxWithNullContent() throws BoundesuWordsException {
        String outputPath = new File(tempDir.toFile(), "all_null_test.docx").getAbsolutePath();
        boundesuWordsAll.convertHtmlToDocx(null, outputPath);
    }
    
    @Test(expectedExceptions = BoundesuWordsException.class)
    public void testConvertHtmlToDocxWithEmptyOutputPath() throws BoundesuWordsException {
        String htmlContent = "<html><body><h1>Test</h1></body></html>";
        boundesuWordsAll.convertHtmlToDocx(htmlContent, "");
    }
    
    @Test(expectedExceptions = BoundesuWordsException.class)
    public void testConvertHtmlToDocxWithInvalidExtension() throws BoundesuWordsException {
        String htmlContent = "<html><body><h1>Test</h1></body></html>";
        String outputPath = new File(tempDir.toFile(), "all_test.txt").getAbsolutePath();
        boundesuWordsAll.convertHtmlToDocx(htmlContent, outputPath);
    }
    
    @Test(expectedExceptions = BoundesuWordsException.class)
    public void testConvertHtmlFileToDocxWithNonExistentFile() throws BoundesuWordsException {
        String outputPath = new File(tempDir.toFile(), "all_test.docx").getAbsolutePath();
        boundesuWordsAll.convertHtmlFileToDocx("/nonexistent/file.html", outputPath);
    }
    
    @Test(expectedExceptions = BoundesuWordsException.class)
    public void testConvertXmlToDocxWithEmptyContent() throws BoundesuWordsException {
        String outputPath = new File(tempDir.toFile(), "all_empty_xml_test.docx").getAbsolutePath();
        boundesuWordsAll.convertXmlToDocx("", outputPath);
    }
    
    @Test(expectedExceptions = BoundesuWordsException.class)
    public void testConvertXmlFileToDocxWithNonExistentFile() throws BoundesuWordsException {
        String outputPath = new File(tempDir.toFile(), "all_test_xml.docx").getAbsolutePath();
        boundesuWordsAll.convertXmlFileToDocx("/nonexistent/file.xml", outputPath);
    }
    
    @Test(expectedExceptions = BoundesuWordsException.class)
    public void testAutoConvertToDocxWithUnsupportedFileType() throws BoundesuWordsException, IOException {
        // 创建不支持的文件类型
        File txtFile = new File(tempDir.toFile(), "all_test.txt");
        Files.write(txtFile.toPath(), "test content".getBytes("UTF-8"));
        
        String outputPath = new File(tempDir.toFile(), "all_test.docx").getAbsolutePath();
        boundesuWordsAll.autoConvertToDocx(txtFile.getAbsolutePath(), outputPath);
    }
    
    @Test(expectedExceptions = BoundesuWordsException.class)
    public void testAutoConvertToDocxWithNonExistentFile() throws BoundesuWordsException {
        String outputPath = new File(tempDir.toFile(), "all_test.docx").getAbsolutePath();
        boundesuWordsAll.autoConvertToDocx("/nonexistent/file.html", outputPath);
    }
    
    @Test
    public void testBatchConvertWithEmptyArray() {
        String outputDir = new File(tempDir.toFile(), "all_empty_batch").getAbsolutePath();
        Map<String, String> results = boundesuWordsAll.batchConvertToDocx(new String[0], outputDir);
        
        Assert.assertNotNull(results);
        Assert.assertTrue(results.isEmpty());
    }
    
    @Test
    public void testBatchConvertWithNullArray() {
        String outputDir = new File(tempDir.toFile(), "all_null_batch").getAbsolutePath();
        Map<String, String> results = boundesuWordsAll.batchConvertToDocx(null, outputDir);
        
        Assert.assertNotNull(results);
        Assert.assertTrue(results.isEmpty());
    }
    
    @Test
    public void testBatchConvertWithInvalidFiles() {
        String[] inputFiles = {"/nonexistent/file1.html", "/nonexistent/file2.xml", ""};
        String outputDir = new File(tempDir.toFile(), "all_invalid_batch").getAbsolutePath();
        
        Map<String, String> results = boundesuWordsAll.batchConvertToDocx(inputFiles, outputDir);
        
        Assert.assertNotNull(results);
        Assert.assertEquals(results.size(), 3);
        
        // 所有结果都应该是错误信息
        for (String result : results.values()) {
            Assert.assertTrue(result.startsWith("转换失败") || result.startsWith("输入文件"));
        }
    }
    
    @Test
    public void testMultipleInstancesIndependence() throws BoundesuWordsException {
        // 测试多个实例之间的独立性
        BoundesuWordsAll instance1 = new BoundesuWordsAll();
        BoundesuWordsAll instance2 = new BoundesuWordsAll();
        
        String htmlContent = "<html><body><h1>Independence Test</h1></body></html>";
        
        String outputPath1 = new File(tempDir.toFile(), "independence_test1.docx").getAbsolutePath();
        String outputPath2 = new File(tempDir.toFile(), "independence_test2.docx").getAbsolutePath();
        
        instance1.convertHtmlToDocx(htmlContent, outputPath1);
        instance2.convertHtmlToDocx(htmlContent, outputPath2);
        
        File outputFile1 = new File(outputPath1);
        File outputFile2 = new File(outputPath2);
        
        Assert.assertTrue(outputFile1.exists());
        Assert.assertTrue(outputFile2.exists());
        Assert.assertTrue(outputFile1.length() > 0);
        Assert.assertTrue(outputFile2.length() > 0);
    }
}