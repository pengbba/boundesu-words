package com.boundesu.words.sdk.utils;

import com.boundesu.words.sdk.constants.DocxConstants;
import com.boundesu.words.sdk.model.BoundesuDocument;

import java.nio.file.Path;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * 文档验证工具类
 * 提供各种验证方法，确保输入参数的有效性
 *
 * @author Boundesu Team
 * @version 1.0.0
 */
public final class DocumentValidator {

    // 文件名验证正则表达式
    private static final Pattern VALID_FILENAME_PATTERN = Pattern.compile("^[^<>:\"/\\\\|?*]+$");

    // 防止实例化
    private DocumentValidator() {
        throw new UnsupportedOperationException("工具类不能被实例化");
    }

    /**
     * 验证文档对象
     *
     * @param document 文档对象
     * @throws IllegalArgumentException 如果文档无效
     */
    public static void validateDocument(BoundesuDocument document) {
        Objects.requireNonNull(document, "文档对象不能为null");

        if (document.getTitle() == null || document.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("文档标题不能为空");
        }

        if (document.getTitle().length() > DocxConstants.MAX_DOCUMENT_TITLE_LENGTH) {
            throw new IllegalArgumentException(
                    String.format("文档标题长度不能超过%d个字符", DocxConstants.MAX_DOCUMENT_TITLE_LENGTH)
            );
        }
    }

    /**
     * 验证文档格式
     *
     * @param format 文档格式
     * @throws IllegalArgumentException 如果格式无效
     */
    public static void validateFormat(String format) {
        Objects.requireNonNull(format, "文档格式不能为null");

        String normalizedFormat = format.toLowerCase().trim();
        if (!isValidFormat(normalizedFormat)) {
            throw new IllegalArgumentException(
                    String.format(DocxConstants.ERROR_INVALID_FORMAT, format)
            );
        }
    }

    /**
     * 验证标题级别
     *
     * @param level 标题级别
     * @throws IllegalArgumentException 如果级别无效
     */
    public static void validateHeadingLevel(int level) {
        if (level < DocxConstants.HEADING_LEVEL_1 || level > DocxConstants.HEADING_LEVEL_6) {
            throw new IllegalArgumentException(
                    String.format(DocxConstants.ERROR_INVALID_HEADING_LEVEL, level)
            );
        }
    }

    /**
     * 验证表格大小
     *
     * @param rows 行数
     * @param cols 列数
     * @throws IllegalArgumentException 如果大小无效
     */
    public static void validateTableSize(int rows, int cols) {
        if (rows < DocxConstants.MIN_TABLE_ROWS || rows > DocxConstants.MAX_TABLE_ROWS) {
            throw new IllegalArgumentException(
                    String.format("表格行数必须在%d-%d之间: %d",
                            DocxConstants.MIN_TABLE_ROWS, DocxConstants.MAX_TABLE_ROWS, rows)
            );
        }

        if (cols < DocxConstants.MIN_TABLE_COLS || cols > DocxConstants.MAX_TABLE_COLS) {
            throw new IllegalArgumentException(
                    String.format("表格列数必须在%d-%d之间: %d",
                            DocxConstants.MIN_TABLE_COLS, DocxConstants.MAX_TABLE_COLS, cols)
            );
        }
    }

    /**
     * 验证表格索引
     *
     * @param document   文档对象
     * @param tableIndex 表格索引
     * @throws IllegalArgumentException 如果索引无效
     */
    public static void validateTableIndex(BoundesuDocument document, int tableIndex) {
        Objects.requireNonNull(document, "文档对象不能为null");

        if (tableIndex < 0 || tableIndex >= document.getTables().size()) {
            throw new IllegalArgumentException(
                    String.format(DocxConstants.ERROR_INVALID_TABLE_INDEX, tableIndex)
            );
        }
    }

    /**
     * 验证表格行列索引
     *
     * @param row       行索引
     * @param col       列索引
     * @param totalRows 总行数
     * @param totalCols 总列数
     * @throws IllegalArgumentException 如果索引无效
     */
    public static void validateTableIndex(int row, int col, int totalRows, int totalCols) {
        if (row < 0 || row >= totalRows) {
            throw new IllegalArgumentException(
                    String.format("表格行索引无效: %d，有效范围: 0-%d", row, totalRows - 1)
            );
        }

        if (col < 0 || col >= totalCols) {
            throw new IllegalArgumentException(
                    String.format("表格列索引无效: %d，有效范围: 0-%d", col, totalCols - 1)
            );
        }
    }

    /**
     * 验证文件路径
     *
     * @param filePath 文件路径
     * @throws IllegalArgumentException 如果路径无效
     */
    public static void validateFilePath(Path filePath) {
        Objects.requireNonNull(filePath, "文件路径不能为null");

        String fileName = filePath.getFileName().toString();
        if (!VALID_FILENAME_PATTERN.matcher(fileName).matches()) {
            throw new IllegalArgumentException("文件名包含非法字符: " + fileName);
        }
    }

    /**
     * 验证段落内容
     *
     * @param content 段落内容
     * @throws IllegalArgumentException 如果内容无效
     */
    public static void validateParagraphContent(String content) {
        Objects.requireNonNull(content, "段落内容不能为null");

        if (content.length() > DocxConstants.MAX_PARAGRAPH_LENGTH) {
            throw new IllegalArgumentException(
                    String.format("段落内容长度不能超过%d个字符", DocxConstants.MAX_PARAGRAPH_LENGTH)
            );
        }
    }

    /**
     * 验证关键词数量
     *
     * @param keywords 关键词数组
     * @throws IllegalArgumentException 如果关键词过多
     */
    public static void validateKeywords(String[] keywords) {
        if (keywords != null && keywords.length > DocxConstants.MAX_KEYWORDS_COUNT) {
            throw new IllegalArgumentException(
                    String.format("关键词数量不能超过%d个", DocxConstants.MAX_KEYWORDS_COUNT)
            );
        }
    }

    /**
     * 检查格式是否有效
     *
     * @param format 格式字符串
     * @return 是否有效
     */
    private static boolean isValidFormat(String format) {
        return DocxConstants.FORMAT_DOCX.equals(format) ||
                DocxConstants.FORMAT_HTML.equals(format) ||
                DocxConstants.FORMAT_MARKDOWN.equals(format) ||
                DocxConstants.FORMAT_TEXT.equals(format);
    }

    /**
     * 验证字符串不为空
     *
     * @param value     字符串值
     * @param fieldName 字段名称
     * @throws IllegalArgumentException 如果字符串为空
     */
    public static void validateNotEmpty(String value, String fieldName) {
        Objects.requireNonNull(value, fieldName + "不能为null");

        if (value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + "不能为空");
        }
    }

    /**
     * 验证数值范围
     *
     * @param value     数值
     * @param min       最小值
     * @param max       最大值
     * @param fieldName 字段名称
     * @throws IllegalArgumentException 如果数值超出范围
     */
    public static void validateRange(int value, int min, int max, String fieldName) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(
                    String.format("%s必须在%d-%d之间: %d", fieldName, min, max, value)
            );
        }
    }
}