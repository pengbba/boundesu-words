package com.boundesu.words.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

/**
 * Boundesu Words SDK - 文档工具类
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
     * 统计文本中的字数
     */
    public static int countWordsInText(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0;
        }

        text = text.trim();
        int wordCount = 0;
        boolean inWord = false;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (isChinese(c)) {
                wordCount++;
            } else if (Character.isLetterOrDigit(c)) {
                if (!inWord) {
                    wordCount++;
                    inWord = true;
                }
            } else {
                inWord = false;
            }
        }

        return wordCount;
    }

    /**
     * 判断字符是否为中文
     */
    private static boolean isChinese(char c) {
        return c >= 0x4E00 && c <= 0x9FFF;
    }

    /**
     * 验证字符串是否为空
     */
    public static boolean isNotEmpty(String str) {
        return str != null && !str.trim().isEmpty();
    }

    /**
     * 验证字符串是否为空
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * 安全地获取字符串长度
     */
    public static int safeLength(String str) {
        return str != null ? str.length() : 0;
    }

    /**
     * 截取字符串到指定长度
     */
    public static String truncate(String str, int maxLength) {
        if (str == null) return "";
        if (str.length() <= maxLength) return str;
        return str.substring(0, maxLength) + "...";
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