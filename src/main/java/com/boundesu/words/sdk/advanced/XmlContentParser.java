package com.boundesu.words.sdk.advanced;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * XML内容解析器
 * 自动识别XML标签并生成相应的文档结构
 *
 * @author Boundesu Team
 * @version 1.0.0
 */
public class XmlContentParser {

    /**
     * XML文档结构项
     */
    public static class XmlDocumentStructure {
        private String tagName;
        private String content;
        private Map<String, String> attributes;
        private List<XmlDocumentStructure> children;
        private int level;

        public XmlDocumentStructure(String tagName, String content, int level) {
            this.tagName = tagName;
            this.content = content;
            this.level = level;
            this.attributes = new HashMap<>();
            this.children = new ArrayList<>();
        }

        // Getters and Setters
        public String getTagName() {
            return tagName;
        }

        public void setTagName(String tagName) {
            this.tagName = tagName;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Map<String, String> getAttributes() {
            return attributes;
        }

        public void setAttributes(Map<String, String> attributes) {
            this.attributes = attributes;
        }

        public List<XmlDocumentStructure> getChildren() {
            return children;
        }

        public void setChildren(List<XmlDocumentStructure> children) {
            this.children = children;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public void addChild(XmlDocumentStructure child) {
            this.children.add(child);
        }

        public void addAttribute(String name, String value) {
            this.attributes.put(name, value);
        }
    }

    /**
     * XML标签映射配置
     */
    public static class XmlTagMapping {
        private final Map<String, Integer> headingTags;
        private final Set<String> paragraphTags;
        private final Set<String> listTags;
        private final Set<String> tableTags;
        private final Set<String> ignoreTags;

        public XmlTagMapping() {
            // 默认标题标签映射
            headingTags = new HashMap<>();
            headingTags.put("title", 1);
            headingTags.put("h1", 1);
            headingTags.put("h2", 2);
            headingTags.put("h3", 3);
            headingTags.put("h4", 4);
            headingTags.put("h5", 5);
            headingTags.put("h6", 6);
            headingTags.put("chapter", 1);
            headingTags.put("section", 2);
            headingTags.put("subsection", 3);
            headingTags.put("header", 1);
            headingTags.put("heading", 2);

            // 段落标签
            paragraphTags = new HashSet<>();
            paragraphTags.add("p");
            paragraphTags.add("paragraph");
            paragraphTags.add("text");
            paragraphTags.add("content");
            paragraphTags.add("description");
            paragraphTags.add("summary");
            paragraphTags.add("abstract");

            // 列表标签
            listTags = new HashSet<>();
            listTags.add("ul");
            listTags.add("ol");
            listTags.add("list");
            listTags.add("items");

            // 表格标签
            tableTags = new HashSet<>();
            tableTags.add("table");
            tableTags.add("grid");
            tableTags.add("data");

            // 忽略的标签
            ignoreTags = new HashSet<>();
            ignoreTags.add("meta");
            ignoreTags.add("style");
            ignoreTags.add("script");
        }

        // Getters and Setters
        public Map<String, Integer> getHeadingTags() {
            return headingTags;
        }

        public Set<String> getParagraphTags() {
            return paragraphTags;
        }

        public Set<String> getListTags() {
            return listTags;
        }

        public Set<String> getTableTags() {
            return tableTags;
        }

        public Set<String> getIgnoreTags() {
            return ignoreTags;
        }

        public void addHeadingTag(String tag, int level) {
            headingTags.put(tag.toLowerCase(), level);
        }

        public void addParagraphTag(String tag) {
            paragraphTags.add(tag.toLowerCase());
        }

        public void addListTag(String tag) {
            listTags.add(tag.toLowerCase());
        }

        public void addTableTag(String tag) {
            tableTags.add(tag.toLowerCase());
        }

        public void addIgnoreTag(String tag) {
            ignoreTags.add(tag.toLowerCase());
        }
    }

    /**
     * 解析XML内容并生成文档结构
     *
     * @param xmlContent XML内容
     * @return 文档结构列表
     */
    public static List<XmlDocumentStructure> parseXmlContent(String xmlContent) {
        return parseXmlContent(xmlContent, new XmlTagMapping());
    }

    /**
     * 解析XML内容并生成文档结构（自定义标签映射）
     *
     * @param xmlContent XML内容
     * @param tagMapping 标签映射配置
     * @return 文档结构列表
     */
    public static List<XmlDocumentStructure> parseXmlContent(String xmlContent, XmlTagMapping tagMapping) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new ByteArrayInputStream(xmlContent.getBytes(StandardCharsets.UTF_8)));

            List<XmlDocumentStructure> structures = new ArrayList<>();
            Element root = doc.getDocumentElement();

            parseElement(root, structures, tagMapping, 0);

            return structures;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException("XML解析失败: " + e.getMessage(), e);
        }
    }

    /**
     * 解析XML并自动生成Word文档
     *
     * @param xmlContent XML内容
     * @param title      文档标题
     * @param author     文档作者
     * @return AdvancedDocumentGenerator实例
     */
    public static AdvancedDocumentGenerator parseXmlToDocument(String xmlContent, String title, String author) {
        return parseXmlToDocument(xmlContent, title, author, new XmlTagMapping());
    }

    /**
     * 解析XML并自动生成Word文档（自定义标签映射）
     *
     * @param xmlContent XML内容
     * @param title      文档标题
     * @param author     文档作者
     * @param tagMapping 标签映射配置
     * @return AdvancedDocumentGenerator实例
     */
    public static AdvancedDocumentGenerator parseXmlToDocument(String xmlContent, String title, String author, XmlTagMapping tagMapping) {
        AdvancedDocumentGenerator generator = new AdvancedDocumentGenerator();

        // 设置文档基本信息
        generator.setTitle(title).setAuthor(author);

        // 生成封面页
        generator.generateCoverPage(title, "基于XML内容自动生成", author);

        // 解析XML结构
        List<XmlDocumentStructure> structures = parseXmlContent(xmlContent, tagMapping);

        // 添加章节内容
        for (XmlDocumentStructure structure : structures) {
            addXmlStructureToDocument(generator, structure, tagMapping);
        }

        // 生成目录
        generator.generateTableOfContents();

        return generator;
    }

    /**
     * 智能解析XML内容，自动识别常见文档结构
     *
     * @param xmlContent XML内容
     * @param title      文档标题
     * @param author     文档作者
     * @return AdvancedDocumentGenerator实例
     */
    public static AdvancedDocumentGenerator smartParseXml(String xmlContent, String title, String author) {
        try {
            AdvancedDocumentGenerator generator = new AdvancedDocumentGenerator();
            XmlTagMapping tagMapping = createSmartTagMapping(xmlContent);

            // 设置文档基本信息
            generator.setTitle(title).setAuthor(author);

            // 生成封面页
            generator.generateCoverPage(title, "智能XML解析生成", author);

            // 解析XML结构
            List<XmlDocumentStructure> structures = parseXmlContent(xmlContent, tagMapping);

            // 添加章节内容
            for (XmlDocumentStructure structure : structures) {
                addXmlStructureToDocument(generator, structure, tagMapping);
            }

            // 生成目录
            generator.generateTableOfContents();

            return generator;
        } catch (Exception e) {
            throw new RuntimeException("智能XML解析失败: " + e.getMessage(), e);
        }
    }

    /**
     * 解析XML元素
     */
    private static void parseElement(Element element, List<XmlDocumentStructure> structures, XmlTagMapping tagMapping, int level) {
        String tagName = element.getTagName().toLowerCase();

        // 跳过忽略的标签
        if (tagMapping.getIgnoreTags().contains(tagName)) {
            return;
        }

        String textContent = getDirectTextContent(element);
        XmlDocumentStructure structure = new XmlDocumentStructure(tagName, textContent, level);

        // 添加属性
        NamedNodeMap attributes = element.getAttributes();
        for (int i = 0; i < attributes.getLength(); i++) {
            Node attr = attributes.item(i);
            structure.addAttribute(attr.getNodeName(), attr.getNodeValue());
        }

        // 解析子元素
        NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                parseElement((Element) child, structure.getChildren(), tagMapping, level + 1);
            }
        }

        structures.add(structure);
    }

    /**
     * 获取元素的直接文本内容（不包括子元素）
     */
    private static String getDirectTextContent(Element element) {
        StringBuilder content = new StringBuilder();
        NodeList children = element.getChildNodes();

        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.TEXT_NODE) {
                String text = child.getTextContent().trim();
                if (!text.isEmpty()) {
                    content.append(text).append(" ");
                }
            }
        }

        return content.toString().trim();
    }

    /**
     * 将XML结构添加到文档
     */
    private static void addXmlStructureToDocument(AdvancedDocumentGenerator generator, XmlDocumentStructure structure, XmlTagMapping tagMapping) {
        String tagName = structure.getTagName().toLowerCase();
        String content = structure.getContent();

        // 处理属性信息
        if (!structure.getAttributes().isEmpty()) {
            StringBuilder attrInfo = new StringBuilder();
            for (Map.Entry<String, String> attr : structure.getAttributes().entrySet()) {
                attrInfo.append(attr.getKey()).append("=").append(attr.getValue()).append(" ");
            }
            if (!content.isEmpty()) {
                content = content + "\n属性: " + attrInfo.toString().trim();
            } else {
                content = "属性: " + attrInfo.toString().trim();
            }
        }

        // 根据标签类型处理
        if (tagMapping.getHeadingTags().containsKey(tagName)) {
            int level = Math.min(tagMapping.getHeadingTags().get(tagName), 6); // 限制级别在1-6之间
            generator.addChapter(content.isEmpty() ? tagName : content, level, "");
        } else if (tagMapping.getParagraphTags().contains(tagName)) {
            if (!content.isEmpty()) {
                generator.addChapter("", 1, content); // 使用addChapter方法
            }
        } else if (tagMapping.getListTags().contains(tagName)) {
            generator.addChapter("", 1, formatListContent(structure)); // 使用addChapter方法
        } else if (tagMapping.getTableTags().contains(tagName)) {
            generator.addChapter("", 1, formatTableContent(structure)); // 使用addChapter方法
        } else {
            // 默认处理
            if (!content.isEmpty()) {
                int level = Math.min(structure.getLevel() + 1, 6); // 限制级别在1-6之间
                generator.addChapter(tagName, level, content);
            }
        }

        // 递归处理子元素
        for (XmlDocumentStructure child : structure.getChildren()) {
            addXmlStructureToDocument(generator, child, tagMapping);
        }
    }

    /**
     * 格式化列表内容
     */
    private static String formatListContent(XmlDocumentStructure listStructure) {
        StringBuilder content = new StringBuilder("列表内容：\n");

        for (int i = 0; i < listStructure.getChildren().size(); i++) {
            XmlDocumentStructure item = listStructure.getChildren().get(i);
            content.append((i + 1)).append(". ").append(item.getContent()).append("\n");
        }

        return content.toString();
    }

    /**
     * 格式化表格内容
     */
    private static String formatTableContent(XmlDocumentStructure tableStructure) {
        StringBuilder content = new StringBuilder("表格内容：\n");

        for (XmlDocumentStructure row : tableStructure.getChildren()) {
            if (row.getTagName().toLowerCase().contains("row") || row.getTagName().toLowerCase().contains("tr")) {
                for (XmlDocumentStructure cell : row.getChildren()) {
                    content.append(cell.getContent()).append(" | ");
                }
                content.append("\n");
            }
        }

        return content.toString();
    }

    /**
     * 创建智能标签映射
     */
    private static XmlTagMapping createSmartTagMapping(String xmlContent) {
        XmlTagMapping mapping = new XmlTagMapping();

        // 分析XML内容，自动识别可能的标签类型
        Set<String> allTags = extractAllTags(xmlContent);

        for (String tag : allTags) {
            String lowerTag = tag.toLowerCase();

            // 智能识别标题标签
            if (lowerTag.contains("title") || lowerTag.contains("head") || lowerTag.contains("caption")) {
                mapping.addHeadingTag(lowerTag, 1);
            } else if (lowerTag.contains("chapter") || lowerTag.contains("section")) {
                mapping.addHeadingTag(lowerTag, 2);
            } else if (lowerTag.contains("subsection") || lowerTag.contains("subtitle")) {
                mapping.addHeadingTag(lowerTag, 3);
            }

            // 智能识别段落标签
            if (lowerTag.contains("text") || lowerTag.contains("content") || lowerTag.contains("desc")) {
                mapping.addParagraphTag(lowerTag);
            }

            // 智能识别列表标签
            if (lowerTag.contains("list") || lowerTag.contains("item") || lowerTag.contains("menu")) {
                mapping.addListTag(lowerTag);
            }

            // 智能识别表格标签
            if (lowerTag.contains("table") || lowerTag.contains("grid") || lowerTag.contains("data")) {
                mapping.addTableTag(lowerTag);
            }
        }

        return mapping;
    }

    /**
     * 提取XML中的所有标签名
     */
    private static Set<String> extractAllTags(String xmlContent) {
        Set<String> tags = new HashSet<>();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new ByteArrayInputStream(xmlContent.getBytes(StandardCharsets.UTF_8)));

            extractTagsFromElement(doc.getDocumentElement(), tags);
        } catch (Exception e) {
            // 如果解析失败，使用正则表达式提取
            java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("<(\\w+)");
            java.util.regex.Matcher matcher = pattern.matcher(xmlContent);
            while (matcher.find()) {
                tags.add(matcher.group(1));
            }
        }

        return tags;
    }

    /**
     * 从元素中提取标签名
     */
    private static void extractTagsFromElement(Element element, Set<String> tags) {
        tags.add(element.getTagName());

        NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                extractTagsFromElement((Element) child, tags);
            }
        }
    }

    /**
     * 从XML字符串中提取纯文本内容
     */
    public static String extractTextFromXml(String xmlContent) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new ByteArrayInputStream(xmlContent.getBytes(StandardCharsets.UTF_8)));
            return doc.getDocumentElement().getTextContent();
        } catch (Exception e) {
            return xmlContent.replaceAll("<[^>]+>", "");
        }
    }

    /**
     * 检查XML内容是否包含结构化标签
     */
    public static boolean hasStructuredContent(String xmlContent) {
        return xmlContent.contains("<") && xmlContent.contains(">");
    }

    /**
     * 获取XML内容的标签统计
     */
    public static Map<String, Integer> getTagStatistics(String xmlContent) {
        Map<String, Integer> stats = new LinkedHashMap<>();
        Set<String> allTags = extractAllTags(xmlContent);

        for (String tag : allTags) {
            java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("<" + tag + "\\b");
            java.util.regex.Matcher matcher = pattern.matcher(xmlContent);
            int count = 0;
            while (matcher.find()) {
                count++;
            }
            stats.put(tag, count);
        }

        return stats;
    }
}