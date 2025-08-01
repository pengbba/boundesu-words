package com.boundesu.words.xml.parser;

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

        public boolean hasChildren() {
            return !children.isEmpty();
        }

        public boolean hasContent() {
            return content != null && !content.trim().isEmpty();
        }

        @Override
        public String toString() {
            return String.format("XmlDocumentStructure{tagName='%s', content='%s', level=%d, children=%d}",
                    tagName, content != null ? content.substring(0, Math.min(content.length(), 50)) : "null",
                    level, children.size());
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
            this.headingTags = new HashMap<>();
            this.paragraphTags = new HashSet<>();
            this.listTags = new HashSet<>();
            this.tableTags = new HashSet<>();
            this.ignoreTags = new HashSet<>();

            // 默认标题标签映射
            headingTags.put("h1", 1);
            headingTags.put("h2", 2);
            headingTags.put("h3", 3);
            headingTags.put("h4", 4);
            headingTags.put("h5", 5);
            headingTags.put("h6", 6);
            headingTags.put("title", 1);
            headingTags.put("heading", 2);
            headingTags.put("subtitle", 3);

            // 默认段落标签
            paragraphTags.add("p");
            paragraphTags.add("paragraph");
            paragraphTags.add("text");
            paragraphTags.add("content");

            // 默认列表标签
            listTags.add("ul");
            listTags.add("ol");
            listTags.add("list");
            listTags.add("items");

            // 默认表格标签
            tableTags.add("table");
            tableTags.add("grid");

            // 默认忽略标签
            ignoreTags.add("meta");
            ignoreTags.add("metadata");
            ignoreTags.add("style");
            ignoreTags.add("script");
        }

        // Getters
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

        // 添加自定义映射的方法
        public XmlTagMapping addHeadingTag(String tag, int level) {
            headingTags.put(tag, level);
            return this;
        }

        public XmlTagMapping addParagraphTag(String tag) {
            paragraphTags.add(tag);
            return this;
        }

        public XmlTagMapping addListTag(String tag) {
            listTags.add(tag);
            return this;
        }

        public XmlTagMapping addTableTag(String tag) {
            tableTags.add(tag);
            return this;
        }

        public XmlTagMapping addIgnoreTag(String tag) {
            ignoreTags.add(tag);
            return this;
        }

        public boolean isHeadingTag(String tag) {
            return headingTags.containsKey(tag);
        }

        public boolean isParagraphTag(String tag) {
            return paragraphTags.contains(tag);
        }

        public boolean isListTag(String tag) {
            return listTags.contains(tag);
        }

        public boolean isTableTag(String tag) {
            return tableTags.contains(tag);
        }

        public boolean isIgnoreTag(String tag) {
            return ignoreTags.contains(tag);
        }

        public int getHeadingLevel(String tag) {
            return headingTags.getOrDefault(tag, 1);
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
     * 递归解析XML元素
     *
     * @param element    当前元素
     * @param structures 结构列表
     * @param tagMapping 标签映射
     * @param level      当前层级
     */
    private static void parseElement(Element element, List<XmlDocumentStructure> structures,
                                     XmlTagMapping tagMapping, int level) {
        String tagName = element.getTagName().toLowerCase();

        // 跳过忽略的标签
        if (tagMapping.isIgnoreTag(tagName)) {
            return;
        }

        // 创建文档结构
        String content = getElementTextContent(element);
        XmlDocumentStructure structure = new XmlDocumentStructure(tagName, content, level);

        // 添加属性
        NamedNodeMap attributes = element.getAttributes();
        for (int i = 0; i < attributes.getLength(); i++) {
            Node attr = attributes.item(i);
            structure.addAttribute(attr.getNodeName(), attr.getNodeValue());
        }

        // 处理子元素
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
     *
     * @param element XML元素
     * @return 文本内容
     */
    private static String getElementTextContent(Element element) {
        StringBuilder textContent = new StringBuilder();
        NodeList children = element.getChildNodes();

        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.TEXT_NODE || child.getNodeType() == Node.CDATA_SECTION_NODE) {
                textContent.append(child.getNodeValue());
            }
        }

        return textContent.toString().trim();
    }

    /**
     * 智能解析XML内容，自动识别常见文档结构
     *
     * @param xmlContent XML内容
     * @return 智能标签映射
     */
    public static XmlTagMapping createSmartTagMapping(String xmlContent) {
        XmlTagMapping mapping = new XmlTagMapping();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new ByteArrayInputStream(xmlContent.getBytes(StandardCharsets.UTF_8)));

            // 分析XML结构，自动识别标签类型
            analyzeXmlStructure(doc.getDocumentElement(), mapping);

        } catch (Exception e) {
            // 如果解析失败，返回默认映射
        }

        return mapping;
    }

    /**
     * 分析XML结构，自动识别标签类型
     *
     * @param element 根元素
     * @param mapping 标签映射
     */
    private static void analyzeXmlStructure(Element element, XmlTagMapping mapping) {
        // 递归分析所有元素
        analyzeElement(element, mapping, new HashSet<>());
    }

    /**
     * 分析单个元素
     *
     * @param element     元素
     * @param mapping     标签映射
     * @param analyzedTags 已分析的标签集合
     */
    private static void analyzeElement(Element element, XmlTagMapping mapping, Set<String> analyzedTags) {
        String tagName = element.getTagName().toLowerCase();

        // 避免重复分析
        if (analyzedTags.contains(tagName)) {
            return;
        }
        analyzedTags.add(tagName);

        // 根据标签名称和内容特征进行智能识别
        if (isLikelyHeading(tagName, element)) {
            int level = guessHeadingLevel(tagName, element);
            mapping.addHeadingTag(tagName, level);
        } else if (isLikelyParagraph(tagName, element)) {
            mapping.addParagraphTag(tagName);
        } else if (isLikelyList(tagName, element)) {
            mapping.addListTag(tagName);
        } else if (isLikelyTable(tagName, element)) {
            mapping.addTableTag(tagName);
        }

        // 递归分析子元素
        NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                analyzeElement((Element) child, mapping, analyzedTags);
            }
        }
    }

    /**
     * 判断是否可能是标题标签
     */
    private static boolean isLikelyHeading(String tagName, Element element) {
        return tagName.contains("title") || tagName.contains("head") ||
                tagName.matches("h[1-6]") || tagName.equals("caption");
    }

    /**
     * 判断是否可能是段落标签
     */
    private static boolean isLikelyParagraph(String tagName, Element element) {
        return tagName.contains("para") || tagName.contains("text") ||
                tagName.contains("content") || tagName.equals("p");
    }

    /**
     * 判断是否可能是列表标签
     */
    private static boolean isLikelyList(String tagName, Element element) {
        return tagName.contains("list") || tagName.contains("item") ||
                tagName.equals("ul") || tagName.equals("ol");
    }

    /**
     * 判断是否可能是表格标签
     */
    private static boolean isLikelyTable(String tagName, Element element) {
        return tagName.contains("table") || tagName.contains("grid") ||
                tagName.contains("row") || tagName.contains("cell");
    }

    /**
     * 猜测标题级别
     */
    private static int guessHeadingLevel(String tagName, Element element) {
        if (tagName.matches("h[1-6]")) {
            return Integer.parseInt(tagName.substring(1));
        }
        if (tagName.contains("title")) {
            return 1;
        }
        if (tagName.contains("subtitle")) {
            return 2;
        }
        return 2; // 默认级别
    }

    /**
     * 获取XML结构统计信息
     *
     * @param structures 文档结构列表
     * @return 统计信息映射
     */
    public static Map<String, Object> getXmlStatistics(List<XmlDocumentStructure> structures) {
        Map<String, Object> stats = new HashMap<>();
        Map<String, Integer> tagCounts = new HashMap<>();
        int totalElements = 0;
        int maxDepth = 0;

        for (XmlDocumentStructure structure : structures) {
            collectStatistics(structure, tagCounts, 0, stats);
            totalElements++;
            maxDepth = Math.max(maxDepth, calculateDepth(structure));
        }

        stats.put("totalElements", totalElements);
        stats.put("maxDepth", maxDepth);
        stats.put("tagCounts", tagCounts);

        return stats;
    }

    /**
     * 收集统计信息
     */
    private static void collectStatistics(XmlDocumentStructure structure, Map<String, Integer> tagCounts,
                                          int currentDepth, Map<String, Object> stats) {
        String tagName = structure.getTagName();
        tagCounts.put(tagName, tagCounts.getOrDefault(tagName, 0) + 1);

        for (XmlDocumentStructure child : structure.getChildren()) {
            collectStatistics(child, tagCounts, currentDepth + 1, stats);
        }
    }

    /**
     * 计算结构深度
     */
    private static int calculateDepth(XmlDocumentStructure structure) {
        if (structure.getChildren().isEmpty()) {
            return 1;
        }

        int maxChildDepth = 0;
        for (XmlDocumentStructure child : structure.getChildren()) {
            maxChildDepth = Math.max(maxChildDepth, calculateDepth(child));
        }

        return 1 + maxChildDepth;
    }
}