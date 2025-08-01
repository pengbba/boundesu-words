package com.boundesu.words.sdk.util;

import com.boundesu.words.sdk.model.BoundesuDocument;
import com.boundesu.words.sdk.model.BoundesuParagraph;
import com.boundesu.words.sdk.model.BoundesuTable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Boundesu Words JDK17 SDK - 文档工具类
 *
 * @author Boundesu
 * @version 1.0.0
 */
public class BoundesuDocumentUtils {

    private static final DateTimeFormatter DEFAULT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final Pattern HTML_TAG_PATTERN = Pattern.compile("<[^>]+>");

    /**
     * 格式化日期时间
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.format(DEFAULT_DATE_FORMAT) : "";
    }

    /**
     * 格式化日期时间（自定义格式）
     */
    public static String formatDateTime(LocalDateTime dateTime, String pattern) {
        if (dateTime == null) return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return dateTime.format(formatter);
    }

    /**
     * 清理HTML标签
     */
    public static String stripHtmlTags(String html) {
        if (html == null || html.isEmpty()) {
            return "";
        }
        return HTML_TAG_PATTERN.matcher(html).replaceAll("");
    }

    /**
     * 计算文档字数（不包含HTML标签）
     */
    public static int countWords(BoundesuDocument document) {
        if (document == null || document.getParagraphs() == null) {
            return 0;
        }

        int wordCount = 0;
        for (BoundesuParagraph paragraph : document.getParagraphs()) {
            if (paragraph.getText() != null) {
                String cleanText = stripHtmlTags(paragraph.getText());
                // 中文字符按字计算，英文按单词计算
                wordCount += countWordsInText(cleanText);
            }
        }
        return wordCount;
    }

    /**
     * 计算文本字数
     */
    private static int countWordsInText(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0;
        }

        text = text.trim();
        int chineseCount = 0;
        int englishWordCount = 0;

        // 统计中文字符
        for (char c : text.toCharArray()) {
            if (isChinese(c)) {
                chineseCount++;
            }
        }

        // 统计英文单词
        String englishText = text.replaceAll("[\\u4e00-\\u9fa5]", " ");
        String[] words = englishText.split("\\s+");
        for (String word : words) {
            if (!word.trim().isEmpty() && word.matches(".*[a-zA-Z].*")) {
                englishWordCount++;
            }
        }

        return chineseCount + englishWordCount;
    }

    /**
     * 判断是否为中文字符
     */
    private static boolean isChinese(char c) {
        return c >= 0x4e00 && c <= 0x9fa5;
    }

    /**
     * 验证文档完整性
     */
    public static boolean validateDocument(BoundesuDocument document) {
        if (document == null) {
            return false;
        }

        // 检查必要字段
        if (document.getDocumentId() == null || document.getDocumentId().trim().isEmpty()) {
            return false;
        }

        if (document.getTitle() == null || document.getTitle().trim().isEmpty()) {
            return false;
        }

        // 检查段落内容
        if (document.getParagraphs() != null) {
            for (BoundesuParagraph paragraph : document.getParagraphs()) {
                if (paragraph.getParagraphId() == null || paragraph.getParagraphId().trim().isEmpty()) {
                    return false;
                }
            }
        }

        // 检查表格内容
        if (document.getTables() != null) {
            for (BoundesuTable table : document.getTables()) {
                if (table.getTableId() == null || table.getTableId().trim().isEmpty()) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 生成文档摘要
     */
    public static String generateSummary(BoundesuDocument document, int maxLength) {
        if (document == null || document.getParagraphs() == null || document.getParagraphs().isEmpty()) {
            return "";
        }

        StringBuilder summary = new StringBuilder();
        for (BoundesuParagraph paragraph : document.getParagraphs()) {
            if (paragraph.getText() != null) {
                String cleanText = stripHtmlTags(paragraph.getText()).trim();
                if (!cleanText.isEmpty()) {
                    if (summary.length() + cleanText.length() <= maxLength) {
                        summary.append(cleanText).append(" ");
                    } else {
                        int remainingLength = maxLength - summary.length();
                        if (remainingLength > 0) {
                            summary.append(cleanText, 0, remainingLength).append("...");
                        }
                        break;
                    }
                }
            }
        }

        return summary.toString().trim();
    }

    /**
     * 查找包含指定文本的段落
     */
    public static List<BoundesuParagraph> findParagraphsContaining(BoundesuDocument document, String searchText) {
        if (document == null || document.getParagraphs() == null || searchText == null) {
            return Collections.emptyList();
        }

        return document.getParagraphs().stream()
                .filter(paragraph -> paragraph.getText() != null &&
                        paragraph.getText().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * 获取文档统计信息
     */
    public static DocumentStatistics getDocumentStatistics(BoundesuDocument document) {
        if (document == null) {
            return new DocumentStatistics();
        }

        DocumentStatistics stats = new DocumentStatistics();
        stats.setWordCount(countWords(document));
        stats.setParagraphCount(document.getParagraphs() != null ? document.getParagraphs().size() : 0);
        stats.setTableCount(document.getTables() != null ? document.getTables().size() : 0);
        stats.setImageCount(document.getImages() != null ? document.getImages().size() : 0);
        stats.setCharacterCount(calculateCharacterCount(document));

        return stats;
    }

    /**
     * 计算字符数
     */
    private static int calculateCharacterCount(BoundesuDocument document) {
        if (document == null || document.getParagraphs() == null) {
            return 0;
        }

        return document.getParagraphs().stream()
                .mapToInt(paragraph -> paragraph.getText() != null ?
                        stripHtmlTags(paragraph.getText()).length() : 0)
                .sum();
    }

    /**
     * 文档统计信息类
     */
    public static class DocumentStatistics {
        private int wordCount;
        private int characterCount;
        private int paragraphCount;
        private int tableCount;
        private int imageCount;

        // Getters and Setters
        public int getWordCount() {
            return wordCount;
        }

        public void setWordCount(int wordCount) {
            this.wordCount = wordCount;
        }

        public int getCharacterCount() {
            return characterCount;
        }

        public void setCharacterCount(int characterCount) {
            this.characterCount = characterCount;
        }

        public int getParagraphCount() {
            return paragraphCount;
        }

        public void setParagraphCount(int paragraphCount) {
            this.paragraphCount = paragraphCount;
        }

        public int getTableCount() {
            return tableCount;
        }

        public void setTableCount(int tableCount) {
            this.tableCount = tableCount;
        }

        public int getImageCount() {
            return imageCount;
        }

        public void setImageCount(int imageCount) {
            this.imageCount = imageCount;
        }

        @Override
        public String toString() {
            return String.format("DocumentStatistics{wordCount=%d, characterCount=%d, paragraphCount=%d, tableCount=%d, imageCount=%d}",
                    wordCount, characterCount, paragraphCount, tableCount, imageCount);
        }
    }
}