package com.boundesu.words.html.util;

import com.boundesu.words.common.util.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Safelist;
import org.jsoup.select.Elements;

/**
 * HTML工具类
 *
 * @author Boundesu
 * @version 1.0.0
 */
public class HtmlUtils {

    /**
     * 私有构造函数，防止实例化
     */
    private HtmlUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /**
     * 清理HTML，移除不安全的标签和属性
     *
     * @param html HTML内容
     * @return 清理后的HTML
     */
    public static String cleanHtml(String html) {
        if (StringUtils.isBlank(html)) {
            return "";
        }

        return Jsoup.clean(html, Safelist.relaxed());
    }

    /**
     * 提取HTML中的纯文本
     *
     * @param html HTML内容
     * @return 纯文本
     */
    public static String extractText(String html) {
        if (StringUtils.isBlank(html)) {
            return "";
        }

        Document doc = Jsoup.parse(html);
        return doc.text();
    }

    /**
     * 检查HTML是否有效
     *
     * @param html HTML内容
     * @return 是否有效
     */
    public static boolean isValidHtml(String html) {
        if (StringUtils.isBlank(html)) {
            return false;
        }

        try {
            Document doc = Jsoup.parse(html);
            return doc != null;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取HTML中的所有链接
     *
     * @param html HTML内容
     * @return 链接列表
     */
    public static Elements getLinks(String html) {
        if (StringUtils.isBlank(html)) {
            return new Elements();
        }

        Document doc = Jsoup.parse(html);
        return doc.select("a[href]");
    }

    /**
     * 获取HTML中的所有图片
     *
     * @param html HTML内容
     * @return 图片元素列表
     */
    public static Elements getImages(String html) {
        if (StringUtils.isBlank(html)) {
            return new Elements();
        }

        Document doc = Jsoup.parse(html);
        return doc.select("img[src]");
    }

    /**
     * 获取HTML中的所有表格
     *
     * @param html HTML内容
     * @return 表格元素列表
     */
    public static Elements getTables(String html) {
        if (StringUtils.isBlank(html)) {
            return new Elements();
        }

        Document doc = Jsoup.parse(html);
        return doc.select("table");
    }

    /**
     * 移除HTML中的所有标签
     *
     * @param html HTML内容
     * @return 移除标签后的文本
     */
    public static String stripTags(String html) {
        if (StringUtils.isBlank(html)) {
            return "";
        }

        return Jsoup.parse(html).text();
    }

    /**
     * 格式化HTML，使其更易读
     *
     * @param html HTML内容
     * @return 格式化后的HTML
     */
    public static String formatHtml(String html) {
        if (StringUtils.isBlank(html)) {
            return "";
        }

        Document doc = Jsoup.parse(html);
        doc.outputSettings().prettyPrint(true);
        doc.outputSettings().indentAmount(2);
        return doc.html();
    }

    /**
     * 压缩HTML，移除多余的空白字符
     *
     * @param html HTML内容
     * @return 压缩后的HTML
     */
    public static String compressHtml(String html) {
        if (StringUtils.isBlank(html)) {
            return "";
        }

        Document doc = Jsoup.parse(html);
        doc.outputSettings().prettyPrint(false);
        return doc.html();
    }

    /**
     * 获取HTML文档的标题
     *
     * @param html HTML内容
     * @return 标题
     */
    public static String getTitle(String html) {
        if (StringUtils.isBlank(html)) {
            return "";
        }

        Document doc = Jsoup.parse(html);
        Element titleElement = doc.selectFirst("title");
        return titleElement != null ? titleElement.text() : "";
    }

    /**
     * 设置HTML文档的标题
     *
     * @param html  HTML内容
     * @param title 新标题
     * @return 更新后的HTML
     */
    public static String setTitle(String html, String title) {
        if (StringUtils.isBlank(html)) {
            return "";
        }

        Document doc = Jsoup.parse(html);
        doc.title(title != null ? title : "");
        return doc.html();
    }

    /**
     * 获取HTML中指定CSS选择器的元素
     *
     * @param html        HTML内容
     * @param cssSelector CSS选择器
     * @return 匹配的元素列表
     */
    public static Elements selectElements(String html, String cssSelector) {
        if (StringUtils.isBlank(html) || StringUtils.isBlank(cssSelector)) {
            return new Elements();
        }

        Document doc = Jsoup.parse(html);
        return doc.select(cssSelector);
    }

    /**
     * 检查HTML是否包含指定的标签
     *
     * @param html    HTML内容
     * @param tagName 标签名
     * @return 是否包含
     */
    public static boolean containsTag(String html, String tagName) {
        if (StringUtils.isBlank(html) || StringUtils.isBlank(tagName)) {
            return false;
        }

        Document doc = Jsoup.parse(html);
        Elements elements = doc.select(tagName);
        return !elements.isEmpty();
    }
}