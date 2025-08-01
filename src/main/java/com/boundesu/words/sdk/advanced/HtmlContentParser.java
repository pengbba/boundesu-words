package com.boundesu.words.sdk.advanced;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

/**
 * HTML内容解析器
 * 自动识别HTML标签并生成相应的文档结构
 *
 * @author Boundesu Team
 * @version 1.0.0
 */
public class HtmlContentParser {

    /**
     * 文档结构项
     */
    public static class DocumentStructure {
        private String title;
        private int level;
        private String content;
        private List<DocumentStructure> children;

        public DocumentStructure(String title, int level, String content) {
            this.title = title;
            this.level = level;
            this.content = content;
            this.children = new ArrayList<>();
        }

        // Getters and Setters
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public List<DocumentStructure> getChildren() {
            return children;
        }

        public void setChildren(List<DocumentStructure> children) {
            this.children = children;
        }

        public void addChild(DocumentStructure child) {
            this.children.add(child);
        }
    }

    /**
     * 解析HTML内容并生成文档结构
     *
     * @param htmlContent HTML内容
     * @return 文档结构列表
     */
    public static List<DocumentStructure> parseHtmlContent(String htmlContent) {
        Document doc = Jsoup.parse(htmlContent);
        List<DocumentStructure> structures = new ArrayList<>();

        // 查找所有标题标签 h1-h6
        Elements headings = doc.select("h1, h2, h3, h4, h5, h6");

        for (Element heading : headings) {
            int level = getHeadingLevel(heading.tagName());
            String title = heading.text();

            // 获取标题后的内容，直到下一个同级或更高级标题
            String content = getContentAfterHeading(heading);

            DocumentStructure structure = new DocumentStructure(title, level, content);
            structures.add(structure);
        }

        return buildHierarchy(structures);
    }

    /**
     * 解析HTML并自动生成Word文档
     *
     * @param htmlContent HTML内容
     * @param title       文档标题
     * @param author      文档作者
     * @return AdvancedDocumentGenerator实例
     */
    public static AdvancedDocumentGenerator parseHtmlToDocument(String htmlContent, String title, String author) {
        AdvancedDocumentGenerator generator = new AdvancedDocumentGenerator();

        // 设置文档基本信息
        generator.setTitle(title).setAuthor(author);

        // 生成封面页
        generator.generateCoverPage(title, "基于HTML内容自动生成", author);

        // 解析HTML结构
        List<DocumentStructure> structures = parseHtmlContent(htmlContent);

        // 添加章节内容
        for (DocumentStructure structure : structures) {
            addStructureToDocument(generator, structure);
        }

        // 生成目录
        generator.generateTableOfContents();

        return generator;
    }

    /**
     * 智能解析HTML内容，支持更多标签识别
     *
     * @param htmlContent HTML内容
     * @return AdvancedDocumentGenerator实例
     */
    public static AdvancedDocumentGenerator smartParseHtml(String htmlContent, String title, String author) {
        AdvancedDocumentGenerator generator = new AdvancedDocumentGenerator();
        Document doc = Jsoup.parse(htmlContent);

        // 设置文档基本信息
        generator.setTitle(title).setAuthor(author);

        // 生成封面页
        generator.generateCoverPage(title, "智能HTML解析生成", author);

        // 解析文档结构
        parseDocumentElements(doc, generator);

        // 生成目录
        generator.generateTableOfContents();

        return generator;
    }

    /**
     * 解析文档元素
     */
    private static void parseDocumentElements(Document doc, AdvancedDocumentGenerator generator) {
        Elements bodyElements = doc.body().children();

        for (Element element : bodyElements) {
            String tagName = element.tagName().toLowerCase();

            switch (tagName) {
                case "h1":
                    generator.addChapter(element.text(), 1, getFollowingContent(element));
                    break;
                case "h2":
                    generator.addChapter(element.text(), 2, getFollowingContent(element));
                    break;
                case "h3":
                    generator.addChapter(element.text(), 3, getFollowingContent(element));
                    break;
                case "h4":
                case "h5":
                case "h6":
                    generator.addChapter(element.text(), getHeadingLevel(tagName), "");
                    break;
                case "p":
                    if (!element.text().trim().isEmpty()) {
                        generator.addChapter("", 0, element.text());
                    }
                    break;
                case "ul":
                case "ol":
                    generator.addChapter("", 0, parseList(element));
                    break;
                case "table":
                    generator.addChapter("", 0, parseTable(element));
                    break;
                case "blockquote":
                    generator.addChapter("", 0, "引用：" + element.text());
                    break;
                case "pre":
                case "code":
                    generator.addChapter("", 0, "代码：\n" + element.text());
                    break;
                default:
                    // 其他标签作为普通段落处理
                    if (!element.text().trim().isEmpty()) {
                        generator.addChapter("", 0, element.text());
                    }
                    break;
            }
        }
    }

    /**
     * 解析列表元素
     */
    private static String parseList(Element listElement) {
        StringBuilder content = new StringBuilder();
        Elements items = listElement.select("li");

        for (int i = 0; i < items.size(); i++) {
            Element item = items.get(i);
            if (listElement.tagName().equals("ol")) {
                content.append((i + 1)).append(". ");
            } else {
                content.append("• ");
            }
            content.append(item.text()).append("\n");
        }

        return content.toString();
    }

    /**
     * 解析表格元素
     */
    private static String parseTable(Element tableElement) {
        StringBuilder content = new StringBuilder("表格内容：\n");

        // 解析表头
        Elements headers = tableElement.select("thead tr th, tr th");
        if (!headers.isEmpty()) {
            content.append("表头：");
            for (Element header : headers) {
                content.append(header.text()).append(" | ");
            }
            content.append("\n");
        }

        // 解析表格行
        Elements rows = tableElement.select("tbody tr, tr");
        for (Element row : rows) {
            Elements cells = row.select("td");
            if (!cells.isEmpty()) {
                for (Element cell : cells) {
                    content.append(cell.text()).append(" | ");
                }
                content.append("\n");
            }
        }

        return content.toString();
    }

    /**
     * 获取标题级别
     */
    private static int getHeadingLevel(String tagName) {
        switch (tagName.toLowerCase()) {
            case "h1":
                return 1;
            case "h2":
                return 2;
            case "h3":
                return 3;
            case "h4":
                return 4;
            case "h5":
                return 5;
            case "h6":
                return 6;
            default:
                return 1;
        }
    }

    /**
     * 获取标题后的内容
     */
    private static String getContentAfterHeading(Element heading) {
        StringBuilder content = new StringBuilder();
        Element nextElement = heading.nextElementSibling();

        while (nextElement != null) {
            String tagName = nextElement.tagName().toLowerCase();

            // 如果遇到同级或更高级的标题，停止
            if (tagName.matches("h[1-6]")) {
                int currentLevel = getHeadingLevel(heading.tagName());
                int nextLevel = getHeadingLevel(tagName);
                if (nextLevel <= currentLevel) {
                    break;
                }
            }

            // 添加内容
            if (!nextElement.text().trim().isEmpty()) {
                content.append(nextElement.text()).append("\n");
            }

            nextElement = nextElement.nextElementSibling();
        }

        return content.toString().trim();
    }

    /**
     * 获取元素后续内容
     */
    private static String getFollowingContent(Element element) {
        StringBuilder content = new StringBuilder();
        Element next = element.nextElementSibling();

        while (next != null && !next.tagName().toLowerCase().matches("h[1-6]")) {
            if (!next.text().trim().isEmpty()) {
                content.append(next.text()).append("\n");
            }
            next = next.nextElementSibling();
        }

        return content.toString().trim();
    }

    /**
     * 构建层次结构
     */
    private static List<DocumentStructure> buildHierarchy(List<DocumentStructure> flatStructures) {
        List<DocumentStructure> result = new ArrayList<>();
        Stack<DocumentStructure> stack = new Stack<>();

        for (DocumentStructure structure : flatStructures) {
            // 弹出比当前级别高或相等的结构
            while (!stack.isEmpty() && stack.peek().getLevel() >= structure.getLevel()) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                result.add(structure);
            } else {
                stack.peek().addChild(structure);
            }

            stack.push(structure);
        }

        return result;
    }

    /**
     * 将结构添加到文档
     */
    private static void addStructureToDocument(AdvancedDocumentGenerator generator, DocumentStructure structure) {
        generator.addChapter(structure.getTitle(), structure.getLevel(), structure.getContent());

        // 递归添加子结构
        for (DocumentStructure child : structure.getChildren()) {
            addStructureToDocument(generator, child);
        }
    }

    /**
     * 从HTML字符串中提取纯文本内容
     */
    public static String extractTextFromHtml(String htmlContent) {
        Document doc = Jsoup.parse(htmlContent);
        return doc.text();
    }

    /**
     * 检查HTML内容是否包含结构化标签
     */
    public static boolean hasStructuredContent(String htmlContent) {
        Document doc = Jsoup.parse(htmlContent);
        Elements headings = doc.select("h1, h2, h3, h4, h5, h6");
        return !headings.isEmpty();
    }

    /**
     * 获取HTML内容的标题层次统计
     */
    public static Map<String, Integer> getHeadingStatistics(String htmlContent) {
        Document doc = Jsoup.parse(htmlContent);
        Map<String, Integer> stats = new LinkedHashMap<>();

        for (int i = 1; i <= 6; i++) {
            Elements headings = doc.select("h" + i);
            if (!headings.isEmpty()) {
                stats.put("H" + i, headings.size());
            }
        }

        return stats;
    }
}