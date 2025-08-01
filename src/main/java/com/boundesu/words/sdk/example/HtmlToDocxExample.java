package com.boundesu.words.sdk.example;

import com.boundesu.words.sdk.service.BoundesuDocumentService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * HTML转DOCX示例
 * 
 * @author Boundesu
 * @version 1.0.0
 */
public class HtmlToDocxExample {
    
    public static void main(String[] args) {
        try {
            BoundesuDocumentService documentService = new BoundesuDocumentService();
            
            // 创建输出目录
            Path outputDir = Paths.get("output");
            if (!outputDir.toFile().exists()) {
                outputDir.toFile().mkdirs();
            }
            
            // 示例1：将HTML字符串转换为DOCX
            System.out.println("示例1：HTML字符串转DOCX");
            String htmlContent = createSampleHtmlContent();
            Path htmlToDocxPath = outputDir.resolve("html-string-to-docx.docx");
            documentService.convertHtmlToDocx(htmlContent, htmlToDocxPath);
            System.out.println("HTML字符串已转换为DOCX: " + htmlToDocxPath.toAbsolutePath());
            
            // 示例2：创建HTML文件然后转换为DOCX
            System.out.println("\n示例2：HTML文件转DOCX");
            Path htmlFilePath = outputDir.resolve("sample.html");
            createSampleHtmlFile(htmlFilePath);
            Path htmlFileToDocxPath = outputDir.resolve("html-file-to-docx.docx");
            documentService.convertHtmlFileToDocx(htmlFilePath, htmlFileToDocxPath);
            System.out.println("HTML文件已转换为DOCX: " + htmlFileToDocxPath.toAbsolutePath());
            
            // 示例3：复杂HTML转DOCX
            System.out.println("\n示例3：复杂HTML转DOCX");
            String complexHtmlContent = createComplexHtmlContent();
            Path complexHtmlToDocxPath = outputDir.resolve("complex-html-to-docx.docx");
            documentService.convertHtmlToDocx(complexHtmlContent, complexHtmlToDocxPath);
            System.out.println("复杂HTML已转换为DOCX: " + complexHtmlToDocxPath.toAbsolutePath());
            
            // 示例4：技术文档HTML转DOCX
            System.out.println("\n示例4：技术文档HTML转DOCX");
            String techHtmlContent = createTechnicalHtmlContent();
            Path techHtmlToDocxPath = outputDir.resolve("technical-html-to-docx.docx");
            documentService.convertHtmlToDocx(techHtmlContent, techHtmlToDocxPath);
            System.out.println("技术文档HTML已转换为DOCX: " + techHtmlToDocxPath.toAbsolutePath());
            
            System.out.println("\n所有HTML转DOCX示例完成！");
            
        } catch (IOException e) {
            System.err.println("转换过程中出错: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * 创建示例HTML内容
     */
    private static String createSampleHtmlContent() {
        return "<!DOCTYPE html>\n" +
            "<html lang=\"zh-CN\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta name=\"author\" content=\"Boundesu Words SDK\">\n" +
            "    <meta name=\"description\" content=\"HTML转DOCX示例文档\">\n" +
            "    <meta name=\"keywords\" content=\"HTML, DOCX, 转换, 示例\">\n" +
            "    <title>HTML转DOCX示例</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "    <h1>HTML转DOCX功能演示</h1>\n" +
            "    \n" +
            "    <h2>简介</h2>\n" +
            "    <p>这是一个演示HTML转DOCX功能的示例文档。Boundesu Words SDK现在支持将HTML内容直接转换为Microsoft Word格式。</p>\n" +
            "    \n" +
            "    <h3>主要特性</h3>\n" +
            "    <ul>\n" +
            "        <li><strong>支持多级标题</strong> - 从H1到H6</li>\n" +
            "        <li><em>文本格式化</em> - 粗体、斜体、下划线</li>\n" +
            "        <li><u>表格支持</u> - 完整的表格转换</li>\n" +
            "        <li>列表支持 - 有序和无序列表</li>\n" +
            "    </ul>\n" +
            "    \n" +
            "    <h3>文本样式示例</h3>\n" +
            "    <p>这是一个包含<strong>粗体文本</strong>、<em>斜体文本</em>和<u>下划线文本</u>的段落。</p>\n" +
            "    <p>您还可以组合使用这些样式，比如<strong><em>粗体斜体</em></strong>文本。</p>\n" +
            "    \n" +
            "    <h3>有序列表示例</h3>\n" +
            "    <ol>\n" +
            "        <li>第一项内容</li>\n" +
            "        <li>第二项内容</li>\n" +
            "        <li>第三项内容</li>\n" +
            "    </ol>\n" +
            "    \n" +
            "    <div>\n" +
            "        <p>这是一个div容器中的段落，用于测试容器元素的处理。</p>\n" +
            "    </div>\n" +
            "</body>\n" +
            "</html>";
    }
    
    /**
     * 创建示例HTML文件
     */
    private static void createSampleHtmlFile(Path filePath) throws IOException {
        String htmlContent = "<!DOCTYPE html>\n" +
            "<html lang=\"zh-CN\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta name=\"author\" content=\"文档作者\">\n" +
            "    <meta name=\"description\" content=\"从HTML文件转换的DOCX文档\">\n" +
            "    <title>HTML文件转DOCX示例</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "    <h1>从HTML文件转换</h1>\n" +
            "    <p>这个文档是从HTML文件转换而来的。</p>\n" +
            "    \n" +
            "    <h2>转换过程</h2>\n" +
            "    <p>转换过程包括以下步骤：</p>\n" +
            "    <ol>\n" +
            "        <li>读取HTML文件</li>\n" +
            "        <li>解析HTML结构</li>\n" +
            "        <li>转换为DOCX格式</li>\n" +
            "        <li>保存到指定位置</li>\n" +
            "    </ol>\n" +
            "    \n" +
            "    <p>转换完成后，您将得到一个完整的Word文档。</p>\n" +
            "</body>\n" +
            "</html>";
        
        Files.write(filePath, htmlContent.getBytes(StandardCharsets.UTF_8));
    }
    
    /**
     * 创建复杂HTML内容
     */
    private static String createComplexHtmlContent() {
        return "<!DOCTYPE html>" +
                "<html lang=\"zh-CN\">" +
                "<head>" +
                "<meta charset=\"UTF-8\">" +
                "<meta name=\"author\" content=\"技术团队\">" +
                "<meta name=\"description\" content=\"复杂HTML结构转DOCX示例\">" +
                "<meta name=\"keywords\" content=\"复杂HTML, 表格, 列表, 格式化\">" +
                "<title>复杂HTML转DOCX示例</title>" +
                "</head>" +
                "<body>" +
                "<h1>项目管理报告</h1>" +
                "<h2>项目概述</h2>" +
                "<p>本报告详细描述了<strong>Boundesu Words SDK</strong>项目的当前状态和进展情况。</p>"
                +
                "<h3>项目基本信息</h3>" +
                "<table border=\"1\">" +
                "<tr><th>项目名称</th><th>负责人</th><th>开始日期</th><th>预计完成</th></tr>" +
                "<tr><td>Boundesu Words SDK</td><td>张三</td><td>2024-01-01</td><td>2024-12-31</td></tr>" +
                "</table>" +
                "<h3>功能模块进度</h3>" +
                "<table border=\"1\">" +
                "<tr><th>模块名称</th><th>负责人</th><th>进度</th><th>状态</th></tr>" +
                "<tr><td>文档创建</td><td>李四</td><td>100%</td><td><strong>已完成</strong></td></tr>" +
                "<tr><td>HTML导出</td><td>王五</td><td>100%</td><td><strong>已完成</strong></td></tr>" +
                "<tr><td>DOCX导出</td><td>赵六</td><td>95%</td><td><em>进行中</em></td></tr>" +
                "<tr><td>HTML转DOCX</td><td>钱七</td><td>100%</td><td><strong>已完成</strong></td></tr>" +
                "</table>" +
                "<h3>待办事项</h3>" +
                "<ul>" +
                "<li>完成DOCX导出功能的最后5%</li>" +
                "<li>进行全面测试</li>" +
                "<li>编写用户文档</li>" +
                "<li>准备发布版本</li>" +
                "</ul>" +
                "<h3>风险评估</h3>" +
                "<ol>" +
                "<li><strong>技术风险</strong>：低 - 核心功能已实现</li>" +
                "<li><strong>时间风险</strong>：中 - 需要密切关注进度</li>" +
                "<li><strong>质量风险</strong>：低 - 有完善的测试计划</li>" +
                "</ol>" +
                "<h2>总结</h2>" +
                "<p>项目整体进展<strong>良好</strong>，预计能够<em>按时交付</em>。HTML转DOCX功能的完成为项目增加了重要价值。</p>" +
                "<div><p><u>注意</u>：本报告包含机密信息，请勿外传。</p></div>" +
                "</body></html>";
    }
    
    /**
     * 创建技术文档HTML内容
     */
    private static String createTechnicalHtmlContent() {
        return "<!DOCTYPE html>" +
                "<html lang=\"zh-CN\">" +
                "<head>" +
                "<meta charset=\"UTF-8\">" +
                "<meta name=\"author\" content=\"API文档团队\">" +
                "<meta name=\"description\" content=\"HTML转DOCX API技术文档\">" +
                "<meta name=\"keywords\" content=\"API, 技术文档, HTML转DOCX, 开发指南\">" +
                "<title>HTML转DOCX API文档</title>" +
                "</head>" +
                "<body>" +
                "<h1>HTML转DOCX API文档</h1>" +
                "<h2>概述</h2>" +
                "<p><strong>HtmlToDocxConverter</strong>类提供了将HTML内容转换为Microsoft Word DOCX格式的功能。</p>" +
                "<h3>主要方法</h3>" +
                "<table border=\"1\">" +
                "<tr><th>方法名</th><th>参数</th><th>返回值</th><th>说明</th></tr>" +
                "<tr><td><strong>convertHtmlToDocx</strong></td><td>String htmlContent, Path outputPath</td><td>void</td><td>将HTML字符串转换为DOCX文件</td></tr>" +
                "<tr><td><strong>convertHtmlFileToDocx</strong></td><td>Path htmlFilePath, Path outputPath</td><td>void</td><td>将HTML文件转换为DOCX文件</td></tr>" +
                "</table>" +
                "<h3>支持的HTML元素</h3>" +
                "<ul>" +
                "<li><strong>标题</strong>：h1, h2, h3, h4, h5, h6</li>" +
                "<li><strong>段落</strong>：p, div</li>" +
                "<li><strong>文本格式</strong>：strong, b, em, i, u</li>" +
                "<li><strong>列表</strong>：ul, ol, li</li>" +
                "<li><strong>表格</strong>：table, tr, td, th</li>" +
                "<li><strong>换行</strong>：br</li>" +
                "</ul>" +
                "<h3>使用示例</h3>" +
                "<h4>示例1：HTML字符串转换</h4>" +
                "<div>" +
                "<p><strong>Java代码：</strong></p>" +
                "<p>BoundesuDocumentService service = new BoundesuDocumentService();</p>" +
                "<p>String html = \"&lt;h1&gt;标题&lt;/h1&gt;&lt;p&gt;内容&lt;/p&gt;\";</p>" +
                "<p>service.convertHtmlToDocx(html, Paths.get(\"output.docx\"));</p>" +
                "</div>" +
                "<h4>示例2：HTML文件转换</h4>" +
                "<div>" +
                "<p><strong>Java代码：</strong></p>" +
                "<p>BoundesuDocumentService service = new BoundesuDocumentService();</p>" +
                "<p>Path htmlFile = Paths.get(\"input.html\");</p>" +
                "<p>Path docxFile = Paths.get(\"output.docx\");</p>" +
                "<p>service.convertHtmlFileToDocx(htmlFile, docxFile);</p>" +
                "</div>" +
                "<h3>注意事项</h3>" +
                "<ol>" +
                "<li>确保HTML格式正确，避免解析错误</li>" +
                "<li>复杂的CSS样式可能无法完全转换</li>" +
                "<li>图片和媒体文件需要特殊处理</li>" +
                "<li>转换过程中会保留基本的文档结构和格式</li>" +
                "</ol>" +
                "<h3>异常处理</h3>" +
                "<p>转换过程中可能抛出<strong>IOException</strong>异常，请确保：</p>" +
                "<ul>" +
                "<li>输入HTML文件存在且可读</li>" +
                "<li>输出路径有写入权限</li>" +
                "<li>磁盘空间充足</li>" +
                "</ul>" +
                "<h2>版本信息</h2>" +
                "<table border=\"1\">" +
                "<tr><th>版本</th><th>发布日期</th><th>主要更新</th></tr>" +
                "<tr><td>1.0.0</td><td>2024-07-21</td><td>初始版本，支持基本HTML转DOCX功能</td></tr>" +
                "</table>" +
                "<div>" +
                "<p><em>更多信息请参考完整的API文档或联系技术支持团队。</em></p>" +
                "</div>" +
                "</body>" +
                "</html>";
    }
}