package com.boundesu.words.sdk.converter;

import org.apache.poi.xwpf.usermodel.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;
import java.util.logging.Logger;
import java.util.logging.Level;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.VerticalAlign;

/**
 * HTML到DOCX转换器
 * 
 * @author Boundesu
 * @version 1.0.0
 */
public class HtmlToDocxConverter {
    
    private static final Logger logger = Logger.getLogger(HtmlToDocxConverter.class.getName());
    
    // 默认字体配置
    private static final String DEFAULT_FONT_FAMILY = "Microsoft YaHei";
    private static final int DEFAULT_FONT_SIZE = 12;
    
    // 缩进配置
    private static final int LIST_INDENT = 720; // 0.5英寸
    private static final int QUOTE_INDENT = 1440; // 1英寸
    
    // 颜色配置
    private static final String LINK_COLOR = "0000FF";
    private static final String CODE_BACKGROUND = "F5F5F5";
    
    /**
     * 将HTML字符串转换为DOCX文件
     * 
     * @param htmlContent HTML内容
     * @param outputPath 输出文件路径
     * @throws IOException 如果文件操作失败
     */
    public static void convertHtmlToDocx(String htmlContent, Path outputPath) throws IOException {
        logger.info("开始转换HTML字符串到DOCX文件: " + outputPath);
        
        if (htmlContent == null || htmlContent.trim().isEmpty()) {
            throw new IllegalArgumentException("HTML内容不能为空");
        }
        
        try {
            // 解析HTML
            Document htmlDoc = Jsoup.parse(htmlContent);
            
            // 创建DOCX文档
            XWPFDocument docxDoc = new XWPFDocument();
            
            try {
                // 设置文档属性
                setDocumentProperties(docxDoc, htmlDoc);
                
                // 设置默认字体
                setDefaultFont(docxDoc);
                
                // 处理HTML body内容
                Element body = htmlDoc.body();
                if (body != null) {
                    processElement(body, docxDoc, null);
                } else {
                    logger.warning("HTML文档中没有找到body元素");
                }
                
                // 保存文档
                try (FileOutputStream out = new FileOutputStream(outputPath.toFile())) {
                    docxDoc.write(out);
                }
                
                logger.info("HTML转换完成: " + outputPath);
                
            } finally {
                docxDoc.close();
            }
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "HTML转换失败: " + e.getMessage(), e);
            throw new IOException("HTML转换失败: " + e.getMessage(), e);
        }
    }
    
    /**
     * 将HTML文件转换为DOCX文件
     * 
     * @param htmlFilePath HTML文件路径
     * @param outputPath 输出文件路径
     * @throws IOException 如果文件操作失败
     */
    public static void convertHtmlFileToDocx(Path htmlFilePath, Path outputPath) throws IOException {
        logger.info("开始转换HTML文件到DOCX: " + htmlFilePath + " -> " + outputPath);
        
        if (htmlFilePath == null || !htmlFilePath.toFile().exists()) {
            throw new IllegalArgumentException("HTML文件不存在: " + htmlFilePath);
        }
        
        try {
            // 解析HTML文件
            Document htmlDoc = Jsoup.parse(htmlFilePath.toFile(), "UTF-8");
            
            // 创建DOCX文档
            XWPFDocument docxDoc = new XWPFDocument();
            
            try {
                // 设置文档属性
                setDocumentProperties(docxDoc, htmlDoc);
                
                // 设置默认字体
                setDefaultFont(docxDoc);
                
                // 处理HTML body内容
                Element body = htmlDoc.body();
                if (body != null) {
                    processElement(body, docxDoc, null);
                } else {
                    logger.warning("HTML文件中没有找到body元素: " + htmlFilePath);
                }
                
                // 保存文档
                try (FileOutputStream out = new FileOutputStream(outputPath.toFile())) {
                    docxDoc.write(out);
                }
                
                logger.info("HTML文件转换完成: " + outputPath);
                
            } finally {
                docxDoc.close();
            }
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "HTML文件转换失败: " + e.getMessage(), e);
            throw new IOException("HTML文件转换失败: " + e.getMessage(), e);
        }
    }
    
    /**
     * 设置文档属性
     */
    private static void setDocumentProperties(XWPFDocument docxDoc, Document htmlDoc) {
        try {
            org.apache.poi.ooxml.POIXMLProperties properties = docxDoc.getProperties();
            org.apache.poi.ooxml.POIXMLProperties.CoreProperties coreProperties = properties.getCoreProperties();
            
            // 设置标题
            Element titleElement = htmlDoc.selectFirst("title");
            if (titleElement != null && !titleElement.text().trim().isEmpty()) {
                coreProperties.setTitle(titleElement.text().trim());
                logger.fine("设置文档标题: " + titleElement.text().trim());
            }
            
            // 设置作者
            Element authorMeta = htmlDoc.selectFirst("meta[name=author]");
            if (authorMeta != null && !authorMeta.attr("content").trim().isEmpty()) {
                coreProperties.setCreator(authorMeta.attr("content").trim());
                logger.fine("设置文档作者: " + authorMeta.attr("content").trim());
            }
            
            // 设置描述
            Element descriptionMeta = htmlDoc.selectFirst("meta[name=description]");
            if (descriptionMeta != null && !descriptionMeta.attr("content").trim().isEmpty()) {
                coreProperties.setDescription(descriptionMeta.attr("content").trim());
                logger.fine("设置文档描述: " + descriptionMeta.attr("content").trim());
            }
            
            // 设置关键词
            Element keywordsMeta = htmlDoc.selectFirst("meta[name=keywords]");
            if (keywordsMeta != null && !keywordsMeta.attr("content").trim().isEmpty()) {
                coreProperties.setKeywords(keywordsMeta.attr("content").trim());
                logger.fine("设置文档关键词: " + keywordsMeta.attr("content").trim());
            }
            
            // 设置语言
            Element langMeta = htmlDoc.selectFirst("meta[name=language]");
            if (langMeta != null && !langMeta.attr("content").trim().isEmpty()) {
                org.apache.poi.ooxml.POIXMLProperties.CustomProperties customProperties = properties.getCustomProperties();
                customProperties.addProperty("Language", langMeta.attr("content").trim());
                logger.fine("设置文档语言: " + langMeta.attr("content").trim());
            }
            
        } catch (Exception e) {
            logger.log(Level.WARNING, "设置文档属性时出错: " + e.getMessage(), e);
        }
    }
    
    /**
     * 设置默认字体
     */
    private static void setDefaultFont(XWPFDocument docxDoc) {
        try {
            // 这里可以设置文档的默认样式
            // POI中设置默认字体比较复杂，通常在创建段落时设置
            logger.fine("准备设置默认字体: " + DEFAULT_FONT_FAMILY);
        } catch (Exception e) {
            logger.log(Level.WARNING, "设置默认字体时出错: " + e.getMessage(), e);
        }
    }
    
    /**
     * 处理HTML元素
     */
    private static void processElement(Element element, XWPFDocument docxDoc, XWPFParagraph currentParagraph) {
        String tagName = element.tagName().toLowerCase();
        
        try {
            switch (tagName) {
                case "h1":
                case "h2":
                case "h3":
                case "h4":
                case "h5":
                case "h6":
                    processHeading(element, docxDoc, getHeadingLevel(tagName));
                    break;
                case "p":
                    processParagraph(element, docxDoc);
                    break;
                case "div":
                    processDiv(element, docxDoc);
                    break;
                case "span":
                    processSpan(element, docxDoc, currentParagraph);
                    break;
                case "table":
                    processTable(element, docxDoc);
                    break;
                case "ul":
                case "ol":
                    processList(element, docxDoc, tagName.equals("ol"));
                    break;
                case "li":
                    // li元素通常在processList中处理，这里处理独立的li
                    processListItem(element, docxDoc, false, 0);
                    break;
                case "br":
                    if (currentParagraph != null) {
                        currentParagraph.createRun().addBreak();
                    } else {
                        docxDoc.createParagraph().createRun().addBreak();
                    }
                    break;
                case "hr":
                    processHorizontalRule(docxDoc);
                    break;
                case "a":
                    processLink(element, docxDoc, currentParagraph);
                    break;
                case "img":
                    processImage(element, docxDoc);
                    break;
                case "strong":
                case "b":
                    processStrongText(element, docxDoc, currentParagraph);
                    break;
                case "em":
                case "i":
                    processEmphasisText(element, docxDoc, currentParagraph);
                    break;
                case "u":
                    processUnderlineText(element, docxDoc, currentParagraph);
                    break;
                case "s":
                case "strike":
                case "del":
                    processStrikethroughText(element, docxDoc, currentParagraph);
                    break;
                case "code":
                    processCodeText(element, docxDoc, currentParagraph);
                    break;
                case "pre":
                    processPreformattedText(element, docxDoc);
                    break;
                case "blockquote":
                    processBlockquote(element, docxDoc);
                    break;
                case "sup":
                    processSuperscript(element, docxDoc, currentParagraph);
                    break;
                case "sub":
                    processSubscript(element, docxDoc, currentParagraph);
                    break;
                default:
                    // 处理其他元素的子节点
                    processChildNodes(element, docxDoc, currentParagraph);
                    break;
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "处理HTML元素时出错: " + tagName + " - " + e.getMessage(), e);
            // 出错时尝试处理子节点
            processChildNodes(element, docxDoc, currentParagraph);
        }
    }
    
    /**
     * 处理标题
     */
    private static void processHeading(Element heading, XWPFDocument docxDoc, int level) {
        XWPFParagraph paragraph = docxDoc.createParagraph();
        XWPFRun run = paragraph.createRun();
        
        String text = heading.text().trim();
        if (!text.isEmpty()) {
            run.setText(text);
            run.setBold(true);
            run.setFontFamily(DEFAULT_FONT_FAMILY);
            
            // 根据标题级别设置字体大小
            int fontSize = Math.max(12, 24 - level * 2);
            run.setFontSize(fontSize);
            
            // 设置段落样式
            paragraph.setStyle("Heading" + level);
            
            // 添加段落间距
            paragraph.setSpacingAfter(200); // 段后间距
            paragraph.setSpacingBefore(200); // 段前间距
            
            logger.fine("处理标题: H" + level + " - " + text);
        }
    }
    
    /**
     * 处理段落
     */
    private static void processParagraph(Element paragraph, XWPFDocument docxDoc) {
        XWPFParagraph docxParagraph = docxDoc.createParagraph();
        
        // 设置段落间距
        docxParagraph.setSpacingAfter(120);
        
        processChildNodes(paragraph, docxDoc, docxParagraph);
        
        // 如果段落为空，添加一个空行
        if (docxParagraph.getRuns().isEmpty()) {
            XWPFRun run = docxParagraph.createRun();
            run.setText("");
            run.setFontFamily(DEFAULT_FONT_FAMILY);
            run.setFontSize(DEFAULT_FONT_SIZE);
        }
    }
    
    /**
     * 处理div元素
     */
    private static void processDiv(Element div, XWPFDocument docxDoc) {
        // div通常作为容器，处理其子元素
        for (Element child : div.children()) {
            processElement(child, docxDoc, null);
        }
        
        // 如果div没有子元素但有文本内容，创建段落
        if (div.children().isEmpty() && !div.ownText().trim().isEmpty()) {
            XWPFParagraph paragraph = docxDoc.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText(div.ownText().trim());
            run.setFontFamily(DEFAULT_FONT_FAMILY);
            run.setFontSize(DEFAULT_FONT_SIZE);
        }
    }
    
    /**
     * 处理表格
     */
    private static void processTable(Element table, XWPFDocument docxDoc) {
        try {
            Elements rows = table.select("tr");
            if (rows.isEmpty()) {
                return;
            }
            
            // 计算列数
            int maxCols = 0;
            for (Element row : rows) {
                Elements cells = row.select("td, th");
                maxCols = Math.max(maxCols, cells.size());
            }
            
            if (maxCols == 0) {
                return;
            }
            
            // 创建表格
            XWPFTable docxTable = docxDoc.createTable(rows.size(), maxCols);
            
            // 设置表格样式
            docxTable.setWidth("100%");
            
            // 填充表格数据
            for (int i = 0; i < rows.size(); i++) {
                Element row = rows.get(i);
                Elements cells = row.select("td, th");
                XWPFTableRow docxRow = docxTable.getRow(i);
                
                for (int j = 0; j < cells.size() && j < maxCols; j++) {
                    Element cell = cells.get(j);
                    XWPFTableCell docxCell = docxRow.getCell(j);
                    
                    // 清除默认段落
                    docxCell.removeParagraph(0);
                    
                    // 添加单元格内容
                    XWPFParagraph cellParagraph = docxCell.addParagraph();
                    XWPFRun cellRun = cellParagraph.createRun();
                    cellRun.setText(cell.text());
                    cellRun.setFontFamily(DEFAULT_FONT_FAMILY);
                    cellRun.setFontSize(DEFAULT_FONT_SIZE);
                    
                    // 如果是表头，设置粗体
                    if (cell.tagName().equals("th")) {
                        cellRun.setBold(true);
                    }
                }
            }
            
            logger.info("成功处理表格，包含 " + rows.size() + " 行");
        } catch (Exception e) {
            logger.log(Level.WARNING, "处理表格时出错", e);
        }
    }
    
    /**
     * 处理列表
     */
    private static void processList(Element list, XWPFDocument docxDoc, boolean isOrdered) {
        try {
            Elements listItems = list.select("li");
            
            for (int i = 0; i < listItems.size(); i++) {
                Element item = listItems.get(i);
                XWPFParagraph paragraph = docxDoc.createParagraph();
                
                // 设置缩进
                paragraph.setIndentationLeft(LIST_INDENT);
                paragraph.setIndentationHanging(LIST_INDENT / 2);
                
                XWPFRun run = paragraph.createRun();
                
                // 添加列表标记
                String prefix = isOrdered ? (i + 1) + ". " : "• ";
                run.setText(prefix + item.text().trim());
                run.setFontFamily(DEFAULT_FONT_FAMILY);
                run.setFontSize(DEFAULT_FONT_SIZE);
                
                // 处理嵌套列表
                Elements nestedLists = item.select("ul, ol");
                for (Element nestedList : nestedLists) {
                    processList(nestedList, docxDoc, nestedList.tagName().equals("ol"));
                }
            }
            
            logger.info("成功处理" + (isOrdered ? "有序" : "无序") + "列表，包含 " + listItems.size() + " 项");
        } catch (Exception e) {
            logger.log(Level.WARNING, "处理列表时出错", e);
        }
    }
    
    /**
     * 处理强调文本（粗体）
     */
    private static void processStrongText(Element element, XWPFDocument docxDoc, XWPFParagraph currentParagraph) {
        XWPFParagraph paragraph = currentParagraph != null ? currentParagraph : docxDoc.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(element.text());
        run.setBold(true);
        run.setFontFamily(DEFAULT_FONT_FAMILY);
        run.setFontSize(DEFAULT_FONT_SIZE);
    }
    
    /**
     * 处理强调文本（斜体）
     */
    private static void processEmphasisText(Element element, XWPFDocument docxDoc, XWPFParagraph currentParagraph) {
        XWPFParagraph paragraph = currentParagraph != null ? currentParagraph : docxDoc.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(element.text());
        run.setItalic(true);
        run.setFontFamily(DEFAULT_FONT_FAMILY);
        run.setFontSize(DEFAULT_FONT_SIZE);
    }
    
    /**
     * 处理下划线文本
     */
    private static void processUnderlineText(Element element, XWPFDocument docxDoc, XWPFParagraph currentParagraph) {
        XWPFParagraph paragraph = currentParagraph != null ? currentParagraph : docxDoc.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(element.text());
        run.setUnderline(UnderlinePatterns.SINGLE);
        run.setFontFamily(DEFAULT_FONT_FAMILY);
        run.setFontSize(DEFAULT_FONT_SIZE);
    }
    
    /**
     * 处理子节点
     */
    private static void processChildNodes(Element element, XWPFDocument docxDoc, XWPFParagraph currentParagraph) {
        try {
            List<Node> childNodes = element.childNodes();
            
            for (Node node : childNodes) {
                if (node instanceof TextNode) {
                    TextNode textNode = (TextNode) node;
                    String text = textNode.text().trim();
                    if (!text.isEmpty()) {
                        XWPFParagraph paragraph = currentParagraph != null ? currentParagraph : docxDoc.createParagraph();
                        XWPFRun run = paragraph.createRun();
                        run.setText(text);
                        run.setFontFamily(DEFAULT_FONT_FAMILY);
                        run.setFontSize(DEFAULT_FONT_SIZE);
                    }
                } else if (node instanceof Element) {
                    Element childElement = (Element) node;
                    String tagName = childElement.tagName().toLowerCase();
                    
                    switch (tagName) {
                        case "strong":
                        case "b":
                            processStrongText(childElement, docxDoc, currentParagraph);
                            break;
                        case "em":
                        case "i":
                            processEmphasisText(childElement, docxDoc, currentParagraph);
                            break;
                        case "u":
                            processUnderlineText(childElement, docxDoc, currentParagraph);
                            break;
                        case "s":
                        case "strike":
                        case "del":
                            processStrikethroughText(childElement, docxDoc, currentParagraph);
                            break;
                        case "code":
                            processCodeText(childElement, docxDoc, currentParagraph);
                            break;
                        case "sup":
                            processSuperscript(childElement, docxDoc, currentParagraph);
                            break;
                        case "sub":
                            processSubscript(childElement, docxDoc, currentParagraph);
                            break;
                        case "a":
                            processLink(childElement, docxDoc, currentParagraph);
                            break;
                        case "br":
                            if (currentParagraph != null) {
                                currentParagraph.createRun().addBreak();
                            } else {
                                docxDoc.createParagraph().createRun().addBreak();
                            }
                            break;
                        case "span":
                            processSpan(childElement, docxDoc, currentParagraph);
                            break;
                        default:
                            // 对于其他元素，递归处理其子节点
                            processElement(childElement, docxDoc, currentParagraph);
                            break;
                    }
                }
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "处理子节点时出错: " + e.getMessage(), e);
        }
    }
    
    /**
     * 处理span元素
     */
    private static void processSpan(Element span, XWPFDocument docxDoc, XWPFParagraph currentParagraph) {
        // span通常用于内联样式，处理其子节点
        processChildNodes(span, docxDoc, currentParagraph);
    }
    
    /**
     * 处理水平分割线
     */
    private static void processHorizontalRule(XWPFDocument docxDoc) {
        XWPFParagraph paragraph = docxDoc.createParagraph();
        paragraph.setBorderBottom(Borders.SINGLE);
        paragraph.setSpacingAfter(200);
        paragraph.setSpacingBefore(200);
        logger.fine("添加水平分割线");
    }
    
    /**
     * 处理链接
     */
    private static void processLink(Element link, XWPFDocument docxDoc, XWPFParagraph currentParagraph) {
        XWPFParagraph paragraph = currentParagraph != null ? currentParagraph : docxDoc.createParagraph();
        XWPFRun run = paragraph.createRun();
        
        String text = link.text().trim();
        String href = link.attr("href");
        
        if (!text.isEmpty()) {
            run.setText(text);
            run.setColor(LINK_COLOR);
            run.setUnderline(UnderlinePatterns.SINGLE);
            run.setFontFamily(DEFAULT_FONT_FAMILY);
            
            if (!href.isEmpty()) {
                logger.fine("处理链接: " + text + " -> " + href);
            }
        }
    }
    
    /**
     * 处理图片
     */
    private static void processImage(Element img, XWPFDocument docxDoc) {
        XWPFParagraph paragraph = docxDoc.createParagraph();
        XWPFRun run = paragraph.createRun();
        
        String alt = img.attr("alt");
        String src = img.attr("src");
        
        // 由于无法直接嵌入图片，添加图片描述
        String imageText = "[图片";
        if (!alt.isEmpty()) {
            imageText += ": " + alt;
        }
        if (!src.isEmpty()) {
            imageText += " (" + src + ")";
        }
        imageText += "]";
        
        run.setText(imageText);
        run.setItalic(true);
        run.setFontFamily(DEFAULT_FONT_FAMILY);
        
        logger.fine("处理图片: " + imageText);
    }
    
    /**
     * 处理删除线文本
     */
    private static void processStrikethroughText(Element element, XWPFDocument docxDoc, XWPFParagraph currentParagraph) {
        XWPFParagraph paragraph = currentParagraph != null ? currentParagraph : docxDoc.createParagraph();
        XWPFRun run = paragraph.createRun();
        
        String text = element.text().trim();
        if (!text.isEmpty()) {
            run.setText(text);
            run.setStrikeThrough(true);
            run.setFontFamily(DEFAULT_FONT_FAMILY);
        }
    }
    
    /**
     * 处理代码文本
     */
    private static void processCodeText(Element code, XWPFDocument docxDoc, XWPFParagraph currentParagraph) {
        XWPFParagraph paragraph = currentParagraph != null ? currentParagraph : docxDoc.createParagraph();
        XWPFRun run = paragraph.createRun();
        
        String text = code.text().trim();
        if (!text.isEmpty()) {
            run.setText(text);
            run.setFontFamily("Consolas");
            run.setFontSize(10);
            // 注意：POI不直接支持背景色，这里只设置字体
            logger.fine("处理代码文本: " + text);
        }
    }
    
    /**
     * 处理预格式化文本
     */
    private static void processPreformattedText(Element pre, XWPFDocument docxDoc) {
        XWPFParagraph paragraph = docxDoc.createParagraph();
        XWPFRun run = paragraph.createRun();
        
        String text = pre.text();
        if (!text.isEmpty()) {
            run.setText(text);
            run.setFontFamily("Consolas");
            run.setFontSize(10);
            
            // 设置段落缩进
            paragraph.setIndentationLeft(360); // 0.25英寸
            paragraph.setSpacingAfter(200);
            paragraph.setSpacingBefore(200);
            
            logger.fine("处理预格式化文本，长度: " + text.length());
        }
    }
    
    /**
     * 处理引用块
     */
    private static void processBlockquote(Element blockquote, XWPFDocument docxDoc) {
        XWPFParagraph paragraph = docxDoc.createParagraph();
        
        // 设置引用样式
        paragraph.setIndentationLeft(QUOTE_INDENT);
        paragraph.setBorderLeft(Borders.SINGLE);
        paragraph.setSpacingAfter(200);
        paragraph.setSpacingBefore(200);
        
        // 处理引用内容
        processChildNodes(blockquote, docxDoc, paragraph);
        
        logger.fine("处理引用块");
    }
    
    /**
     * 处理上标
     */
    private static void processSuperscript(Element sup, XWPFDocument docxDoc, XWPFParagraph currentParagraph) {
        XWPFParagraph paragraph = currentParagraph != null ? currentParagraph : docxDoc.createParagraph();
        XWPFRun run = paragraph.createRun();
        
        String text = sup.text().trim();
        if (!text.isEmpty()) {
            run.setText(text);
            run.setSubscript(VerticalAlign.SUPERSCRIPT);
            run.setFontFamily(DEFAULT_FONT_FAMILY);
            run.setFontSize(8);
        }
    }
    
    /**
     * 处理下标
     */
    private static void processSubscript(Element sub, XWPFDocument docxDoc, XWPFParagraph currentParagraph) {
        XWPFParagraph paragraph = currentParagraph != null ? currentParagraph : docxDoc.createParagraph();
        XWPFRun run = paragraph.createRun();
        
        String text = sub.text().trim();
        if (!text.isEmpty()) {
            run.setText(text);
            run.setSubscript(VerticalAlign.SUBSCRIPT);
            run.setFontFamily(DEFAULT_FONT_FAMILY);
            run.setFontSize(8);
        }
    }
    
    /**
     * 处理列表项
     */
    private static void processListItem(Element item, XWPFDocument docxDoc, boolean isOrdered, int index) {
        XWPFParagraph paragraph = docxDoc.createParagraph();
        XWPFRun run = paragraph.createRun();
        
        String text = item.text().trim();
        if (!text.isEmpty()) {
            // 添加列表标记
            String prefix = isOrdered ? (index + 1) + ". " : "• ";
            run.setText(prefix + text);
            run.setFontFamily(DEFAULT_FONT_FAMILY);
            
            // 设置缩进
            paragraph.setIndentationLeft(LIST_INDENT);
        }
    }
    
    /**
     * 获取标题级别
     */
    private static int getHeadingLevel(String tagName) {
        switch (tagName) {
            case "h1": return 1;
            case "h2": return 2;
            case "h3": return 3;
            case "h4": return 4;
            case "h5": return 5;
            case "h6": return 6;
            default: return 1;
        }
    }
}