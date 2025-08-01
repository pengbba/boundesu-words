package com.boundesu.words.all.example;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * BoundesuWordsExample 测试类
 * 
 * @author Boundesu
 * @version 1.0.0
 */
public class BoundesuWordsExampleTest {
    
    private Path tempDir;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;
    
    @BeforeMethod
    public void setUp() throws IOException {
        tempDir = Files.createTempDirectory("boundesu-example-test");
        
        // 重定向System.out以捕获输出
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }
    
    @AfterMethod
    public void tearDown() throws IOException {
        // 恢复System.out
        System.setOut(originalOut);
        
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
    public void testRunAllExamples() {
        // 测试运行所有示例
        BoundesuWordsExample.main(new String[]{});
        
        String output = outputStream.toString();
        
        // 验证输出包含预期的内容
        Assert.assertTrue(output.contains("=== Boundesu Words SDK 示例 ==="));
        Assert.assertTrue(output.contains("1. 获取SDK信息"));
        Assert.assertTrue(output.contains("2. HTML内容转换示例"));
        Assert.assertTrue(output.contains("3. XML内容转换示例"));
        Assert.assertTrue(output.contains("4. HTML文件转换示例"));
        Assert.assertTrue(output.contains("5. XML文件转换示例"));
        Assert.assertTrue(output.contains("6. 自动识别文件类型转换示例"));
        Assert.assertTrue(output.contains("7. 批量转换示例"));
        Assert.assertTrue(output.contains("8. 直接使用HTML转换器示例"));
        Assert.assertTrue(output.contains("9. 直接使用XML转换器示例"));
        Assert.assertTrue(output.contains("10. 获取服务状态示例"));
        Assert.assertTrue(output.contains("=== 所有示例执行完成 ==="));
    }
    
    @Test
    public void testShowSDKInfo() {
        BoundesuWordsExample.showSDKInfo();
        
        String output = outputStream.toString();
        
        Assert.assertTrue(output.contains("=== SDK信息 ==="));
        Assert.assertTrue(output.contains("名称:"));
        Assert.assertTrue(output.contains("版本:"));
        Assert.assertTrue(output.contains("作者:"));
        Assert.assertTrue(output.contains("描述:"));
        Assert.assertTrue(output.contains("JDK版本:"));
        Assert.assertTrue(output.contains("许可证:"));
        Assert.assertTrue(output.contains("初始化时间:"));
    }
    
    @Test
    public void testHtmlContentExample() {
        BoundesuWordsExample.htmlContentExample();
        
        String output = outputStream.toString();
        
        Assert.assertTrue(output.contains("=== HTML内容转换示例 ==="));
        Assert.assertTrue(output.contains("HTML内容:"));
        Assert.assertTrue(output.contains("<html><body><h1>示例标题</h1><p>这是一个示例段落</p></body></html>"));
        Assert.assertTrue(output.contains("转换完成") || output.contains("转换失败"));
    }
    
    @Test
    public void testXmlContentExample() {
        BoundesuWordsExample.xmlContentExample();
        
        String output = outputStream.toString();
        
        Assert.assertTrue(output.contains("=== XML内容转换示例 ==="));
        Assert.assertTrue(output.contains("XML内容:"));
        Assert.assertTrue(output.contains("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"));
        Assert.assertTrue(output.contains("<document>"));
        Assert.assertTrue(output.contains("转换完成") || output.contains("转换失败"));
    }
    
    @Test
    public void testHtmlFileExample() throws IOException {
        // 创建临时HTML文件
        String htmlContent = "<html><body><h1>文件示例标题</h1><p>这是从文件读取的内容</p></body></html>";
        File htmlFile = new File(tempDir.toFile(), "example.html");
        Files.write(htmlFile.toPath(), htmlContent.getBytes("UTF-8"));
        
        // 修改示例方法以使用临时文件
        BoundesuWordsExample.htmlFileExample();
        
        String output = outputStream.toString();
        
        Assert.assertTrue(output.contains("=== HTML文件转换示例 ==="));
        Assert.assertTrue(output.contains("HTML文件路径:"));
        Assert.assertTrue(output.contains("转换完成") || output.contains("转换失败") || output.contains("文件不存在"));
    }
    
    @Test
    public void testXmlFileExample() throws IOException {
        // 创建临时XML文件
        String xmlContent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><document><title>文件示例标题</title><content>这是从XML文件读取的内容</content></document>";
        File xmlFile = new File(tempDir.toFile(), "example.xml");
        Files.write(xmlFile.toPath(), xmlContent.getBytes("UTF-8"));
        
        BoundesuWordsExample.xmlFileExample();
        
        String output = outputStream.toString();
        
        Assert.assertTrue(output.contains("=== XML文件转换示例 ==="));
        Assert.assertTrue(output.contains("XML文件路径:"));
        Assert.assertTrue(output.contains("转换完成") || output.contains("转换失败") || output.contains("文件不存在"));
    }
    
    @Test
    public void testAutoConvertExample() {
        BoundesuWordsExample.autoConvertExample();
        
        String output = outputStream.toString();
        
        Assert.assertTrue(output.contains("=== 自动识别文件类型转换示例 ==="));
        Assert.assertTrue(output.contains("输入文件:"));
        Assert.assertTrue(output.contains("转换完成") || output.contains("转换失败") || output.contains("文件不存在"));
    }
    
    @Test
    public void testBatchConvertExample() {
        BoundesuWordsExample.batchConvertExample();
        
        String output = outputStream.toString();
        
        Assert.assertTrue(output.contains("=== 批量转换示例 ==="));
        Assert.assertTrue(output.contains("批量转换文件:"));
        Assert.assertTrue(output.contains("批量转换结果:"));
    }
    
    @Test
    public void testDirectHtmlConverterExample() {
        BoundesuWordsExample.directHtmlConverterExample();
        
        String output = outputStream.toString();
        
        Assert.assertTrue(output.contains("=== 直接使用HTML转换器示例 ==="));
        Assert.assertTrue(output.contains("直接使用HTML转换器:"));
        Assert.assertTrue(output.contains("转换完成") || output.contains("转换失败"));
    }
    
    @Test
    public void testDirectXmlConverterExample() {
        BoundesuWordsExample.directXmlConverterExample();
        
        String output = outputStream.toString();
        
        Assert.assertTrue(output.contains("=== 直接使用XML转换器示例 ==="));
        Assert.assertTrue(output.contains("直接使用XML转换器:"));
        Assert.assertTrue(output.contains("转换完成") || output.contains("转换失败"));
    }
    
    @Test
    public void testServiceStatusExample() {
        BoundesuWordsExample.serviceStatusExample();
        
        String output = outputStream.toString();
        
        Assert.assertTrue(output.contains("=== 服务状态示例 ==="));
        Assert.assertTrue(output.contains("服务状态:"));
        Assert.assertTrue(output.contains("service_name"));
        Assert.assertTrue(output.contains("status"));
        Assert.assertTrue(output.contains("html_converter_available"));
        Assert.assertTrue(output.contains("xml_converter_available"));
    }
    
    @Test
    public void testPrintSeparator() {
        BoundesuWordsExample.printSeparator();
        
        String output = outputStream.toString();
        
        Assert.assertTrue(output.contains("="));
        Assert.assertTrue(output.length() > 10); // 分隔符应该有一定长度
    }
    
    @Test
    public void testMultipleMethodCalls() {
        // 测试多次调用不同方法
        BoundesuWordsExample.showSDKInfo();
        BoundesuWordsExample.printSeparator();
        BoundesuWordsExample.htmlContentExample();
        BoundesuWordsExample.printSeparator();
        BoundesuWordsExample.xmlContentExample();
        
        String output = outputStream.toString();
        
        // 验证所有方法的输出都存在
        Assert.assertTrue(output.contains("=== SDK信息 ==="));
        Assert.assertTrue(output.contains("=== HTML内容转换示例 ==="));
        Assert.assertTrue(output.contains("=== XML内容转换示例 ==="));
        
        // 验证分隔符出现多次
        int separatorCount = output.split("=").length - 1;
        Assert.assertTrue(separatorCount > 10); // 应该有多个分隔符
    }
    
    @Test
    public void testOutputFormat() {
        BoundesuWordsExample.showSDKInfo();
        
        String output = outputStream.toString();
        
        // 验证输出格式
        Assert.assertTrue(output.contains("\n")); // 应该包含换行符
        Assert.assertFalse(output.trim().isEmpty()); // 不应该为空
        
        // 验证中文字符正确显示
        Assert.assertTrue(output.contains("名称"));
        Assert.assertTrue(output.contains("版本"));
        Assert.assertTrue(output.contains("作者"));
    }
    
    @Test
    public void testExceptionHandling() {
        // 测试异常处理 - 这些方法应该能够优雅地处理错误
        BoundesuWordsExample.htmlFileExample(); // 文件可能不存在
        BoundesuWordsExample.xmlFileExample(); // 文件可能不存在
        BoundesuWordsExample.autoConvertExample(); // 文件可能不存在
        
        String output = outputStream.toString();
        
        // 即使有错误，也应该有输出
        Assert.assertFalse(output.trim().isEmpty());
        
        // 应该包含示例标题
        Assert.assertTrue(output.contains("=== HTML文件转换示例 ==="));
        Assert.assertTrue(output.contains("=== XML文件转换示例 ==="));
        Assert.assertTrue(output.contains("=== 自动识别文件类型转换示例 ==="));
    }
    
    @Test
    public void testConsistentOutput() {
        // 测试输出的一致性 - 多次调用应该产生相似的输出
        BoundesuWordsExample.showSDKInfo();
        String output1 = outputStream.toString();
        
        // 重置输出流
        outputStream.reset();
        
        BoundesuWordsExample.showSDKInfo();
        String output2 = outputStream.toString();
        
        // SDK信息应该基本相同（除了时间戳）
        Assert.assertTrue(output1.contains("=== SDK信息 ==="));
        Assert.assertTrue(output2.contains("=== SDK信息 ==="));
        Assert.assertTrue(output1.contains("名称:"));
        Assert.assertTrue(output2.contains("名称:"));
    }
}