package com.boundesu.words.html.parser;

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
     * 解析HTML内容为文档结构
     *
     * @param htmlContent HTML内容
     * @return 文档结构列表
     */
    public static List<DocumentStructure> parseHtmlContent(String htmlContent) {
        if (htmlContent == null || htmlContent.trim().isEmpty()) {
            return new ArrayList<>();
        }

        Document doc = Jsoup.parse(htmlContent);
        List<DocumentStructure> flatStructures = new ArrayList<>();

        // 查找所有标题元素
        Elements headings = doc.select("h1, h2, h3, h4, h5, h6");
        for (Element heading : headings) {
            int level = getHeadingLevel(heading.tagName());
            String title = heading.text();
            String content = getContentAfterHeading(heading);
            flatStructures.add(new DocumentStructure(title, level, content));
        }

        return buildHierarchy(flatStructures);
    }

    /**
     * 解析列表元素
     */
    private static String parseList(Element listElement) {
        StringBuilder content = new StringBuilder();
        Elements items = listElement.select("li");
        
        for (Element item : items) {
            content.append("• ").append(item.text()).append("\n");
        }
        
        return content.toString();
    }

    /**
     * 解析表格元素
     */
    private static String parseTable(Element tableElement) {
        StringBuilder content = new StringBuilder();
        Elements rows = tableElement.select("tr");
        
        for (Element row : rows) {
            Elements cells = row.select("td, th");
            for (int i = 0; i < cells.size(); i++) {
                content.append(cells.get(i).text());
                if (i < cells.size() - 1) {
                    content.append("\t");
                }
            }
            content.append("\n");
        }
        
        return content.toString();
    }

    /**
     * 获取标题级别
     */
    private static int getHeadingLevel(String tagName) {
        switch (tagName.toLowerCase()) {
            case "h1": return 1;
            case "h2": return 2;
            case "h3": return 3;
            case "h4": return 4;
            case "h5": return 5;
            case "h6": return 6;
            default: return 1;
        }
    }

    /**
     * 获取标题后的内容
     */
    private static String getContentAfterHeading(Element heading) {
        StringBuilder content = new StringBuilder();
        Element nextElement = heading.nextElementSibling();
        
        while (nextElement != null && !isHeading(nextElement)) {
            if (nextElement.tagName().equals("p")) {
                content.append(nextElement.text()).append("\n\n");
            } else if (nextElement.tagName().matches("ul|ol")) {
                content.append(parseList(nextElement)).append("\n");
            } else if (nextElement.tagName().equals("table")) {
                content.append(parseTable(nextElement)).append("\n");
            } else {
                content.append(nextElement.text()).append("\n");
            }
            nextElement = nextElement.nextElementSibling();
        }
        
        return content.toString().trim();
    }

    /**
     * 判断是否为标题元素
     */
    private static boolean isHeading(Element element) {
        return element.tagName().matches("h[1-6]");
    }

    /**
     * 获取跟随的内容
     */
    private static String getFollowingContent(Element element) {
        StringBuilder content = new StringBuilder();
        Element sibling = element.nextElementSibling();
        
        while (sibling != null && !isHeading(sibling)) {
            content.append(sibling.text()).append("\n");
            sibling = sibling.nextElementSibling();
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
     * 从HTML中提取纯文本
     *
     * @param htmlContent HTML内容
     * @return 纯文本
     */
    public static String extractTextFromHtml(String htmlContent) {
        if (htmlContent == null || htmlContent.trim().isEmpty()) {
            return "";
        }
        
        Document doc = Jsoup.parse(htmlContent);
        return doc.text();
    }

    /**
     * 检查是否包含结构化内容
     *
     * @param htmlContent HTML内容
     * @return 是否包含结构化内容
     */
    public static boolean hasStructuredContent(String htmlContent) {
        if (htmlContent == null || htmlContent.trim().isEmpty()) {
            return false;
        }
        
        Document doc = Jsoup.parse(htmlContent);
        return !doc.select("h1, h2, h3, h4, h5, h6, table, ul, ol").isEmpty();
    }

    /**
     * 获取标题统计信息
     *
     * @param htmlContent HTML内容
     * @return 标题统计信息
     */
    public static Map<String, Integer> getHeadingStatistics(String htmlContent) {
        Map<String, Integer> stats = new HashMap<>();
        
        if (htmlContent == null || htmlContent.trim().isEmpty()) {
            return stats;
        }
        
        Document doc = Jsoup.parse(htmlContent);
        Elements headings = doc.select("h1, h2, h3, h4, h5, h6");
        
        for (Element heading : headings) {
            String tagName = heading.tagName();
            stats.put(tagName, stats.getOrDefault(tagName, 0) + 1);
        }
        
        return stats;
    }
}