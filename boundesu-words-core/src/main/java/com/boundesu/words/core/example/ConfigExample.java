package com.boundesu.words.core.example;

import com.boundesu.words.common.exception.BoundesuWordsException;
import com.boundesu.words.core.Document;
import com.boundesu.words.core.config.DocumentPageConfig;
import com.boundesu.words.core.config.HtmlLoadConfig;
import com.boundesu.words.core.config.XmlLoadConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 配置类使用示例
 * 演示如何使用 DocumentPageConfig、HtmlLoadConfig 和 XmlLoadConfig
 */
public class ConfigExample {
    private static final Logger log = LoggerFactory.getLogger(ConfigExample.class);

    public static void main(String[] args) {
        try {
            // 示例1：使用页面配置
            demonstratePageConfig();
            
            // 示例2：使用HTML加载配置
            demonstrateHtmlConfig();
            
            // 示例3：使用XML加载配置
            demonstrateXmlConfig();
            
            log.info("配置示例演示完成");
        } catch (Exception e) {
            log.error("配置示例执行失败: {}", e.getMessage(), e);
        }
    }
    
    /**
     * 演示页面配置的使用
     */
    private static void demonstratePageConfig() throws BoundesuWordsException {
        log.info("=== 页面配置示例 ===");
        
        // 创建文档
        Document document = new Document();
        
        // 获取并配置页面设置
        DocumentPageConfig pageConfig = document.getPageConfig();
        
        // 设置页面大小为A4
        pageConfig.setPaperSize(DocumentPageConfig.PaperSize.A4);
        log.info("设置页面大小: A4");
        
        // 设置页面方向为横向
        pageConfig.setOrientation(DocumentPageConfig.Orientation.LANDSCAPE);
        log.info("设置页面方向: 横向");
        
        // 设置页边距（磅为单位，1英寸=72磅）
        pageConfig.setMargins(108, 108, 144, 144); // 上下1.5英寸，左右2英寸
        log.info("设置页边距: 左右2.0英寸, 上下1.5英寸");
        
        // 设置页眉页脚
        pageConfig.setHeaderText("文档页眉");
        pageConfig.setFooterText("页码: {PAGE}");
        log.info("设置页眉页脚内容");
        
        // 设置背景颜色
        pageConfig.setBackgroundColor("#F5F5F5");
        log.info("设置背景颜色: #F5F5F5");
        
        // 启用目录生成
        pageConfig.setGenerateToc(true);
        pageConfig.setTocTitle("文档目录");
        pageConfig.setTocLevels("1-2");
        log.info("启用目录生成");
        
        // 保存文档
        document.save("page-config-example.docx");
        log.info("页面配置示例文档已保存");
    }
    
    /**
     * 演示HTML加载配置的使用
     */
    private static void demonstrateHtmlConfig() throws BoundesuWordsException {
        log.info("=== HTML加载配置示例 ===");
        
        // 创建文档
        Document document = new Document();
        
        // 获取并配置HTML加载选项
        HtmlLoadConfig htmlConfig = document.getHtmlConfig();
        
        // 设置编码
        htmlConfig.setEncoding(java.nio.charset.StandardCharsets.UTF_8);
        log.info("设置HTML编码: UTF-8");
        
        // 设置CSS处理选项
        htmlConfig.setCssOptions(true, true);
        log.info("启用CSS处理和内联CSS");
        
        // 表格处理设置
        htmlConfig.setTableOptions(true, true);
        log.info("配置表格处理选项: 保留布局和自动调整");
        
        // 图片处理设置
        htmlConfig.setImageOptions(true, true, null);
        log.info("配置图片处理选项: 转换图片并嵌入");
        
        // 字体设置
        htmlConfig.setDefaultFont("Arial", 12);
        htmlConfig.setFontSubstitution(true);
        log.info("配置字体选项: 默认字体Arial 12pt和字体替换");
        
        // 链接处理设置
        htmlConfig.setLinkOptions(true, false, null);
        log.info("配置链接处理选项: 保留超链接");
        
        // 间距选项
        htmlConfig.setSpacingOptions(true, true);
        log.info("配置间距选项: 保留行间距和段落间距");
        
        // 列表选项
        htmlConfig.setListOptions(true, true, true);
        log.info("配置列表选项: 保留所有列表格式");
        
        // 清理选项
        htmlConfig.setCleanupOptions(true, true, true);
        log.info("配置清理选项: 移除空段落和多余空格");
        
        // 应用配置到文档
        document.setHtmlConfig(htmlConfig);
        
        log.info("HTML加载配置已应用");
    }
    
    /**
     * 演示XML加载配置的使用
     */
    private static void demonstrateXmlConfig() throws BoundesuWordsException {
        log.info("=== XML加载配置示例 ===");
        
        // 创建文档
        Document document = new Document();
        
        // 获取并配置XML加载选项
        XmlLoadConfig xmlConfig = document.getXmlConfig();
        
        // 设置解析模式
        xmlConfig.setParseMode(XmlLoadConfig.XmlParseMode.STRICT);
        log.info("设置XML解析模式: 严格模式");
        
        // 设置命名空间处理
        xmlConfig.setNamespaceMode(XmlLoadConfig.NamespaceMode.PRESERVE);
        log.info("设置命名空间处理: 保留命名空间");
        
        // 设置验证选项
        xmlConfig.setValidationOptions(true, null, null);
        log.info("启用XML验证");
        
        // 设置内容保留选项
        xmlConfig.setContentPreservation(false, true, true);
        log.info("设置内容保留选项: 不保留空白，保留注释和处理指令");
        
        // 设置元素处理
        xmlConfig.setElementOptions(true, true, false);
        log.info("设置元素处理选项: 转换空元素，合并相邻文本");
        
        // 设置属性处理
        xmlConfig.setAttributeOptions(true, false);
        log.info("设置属性处理选项: 保留属性，不转换为元素");
        
        // 设置CDATA处理
        xmlConfig.setCDataOptions(true, false);
        log.info("设置CDATA处理选项: 保留CDATA，不转换为文本");
        
        // 设置实体处理
        xmlConfig.setEntityOptions(false, true);
        log.info("设置实体处理选项: 不解析外部实体，展开实体引用");
        
        // 设置错误处理
        xmlConfig.setErrorHandling(false, true, 10);
        log.info("设置错误处理: 不忽略错误，记录错误，最大错误数: 10");
        
        // 应用配置到文档
        document.setXmlConfig(xmlConfig);
        
        log.info("XML加载配置已应用");
    }
}