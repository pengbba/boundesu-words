package com.boundesu.words.core.creator;

import com.boundesu.words.common.creator.DocumentCreator;
import com.boundesu.words.core.creator.impl.PoiDirectDocxCreator;
import com.boundesu.words.core.creator.impl.XmlBasedDocxCreator;

/**
 * DOCX文档创建器工厂类
 * 提供三种不同的DOCX创建方式
 *
 * @author Boundesu Team
 * @version 1.0.0
 */
public class DocumentCreatorFactory {

    /**
     * 创建文档创建器
     *
     * @param type 创建器类型
     * @return 文档创建器实例
     */
    public static DocumentCreator createDocumentCreator(CreatorType type) {
        switch (type) {
            case DIRECT_POI:
                return new PoiDirectDocxCreator();
            case XML_CONVERSION:
                return new XmlBasedDocxCreator();
            case HTML_CONVERSION:
            default:
                throw new IllegalArgumentException("不支持的创建器类型: " + type + "。HTML转换器请使用html模块。");
        }
    }

    /**
     * 创建直接POI文档创建器
     *
     * @return POI直接创建器
     */
    public static PoiDirectDocxCreator createDirectCreator() {
        return new PoiDirectDocxCreator();
    }

    /**
     * 创建HTML转换文档创建器
     * 注意：HTML转换器已移至html模块，请使用html模块中的HtmlToDocxCreator
     *
     * @throws UnsupportedOperationException HTML转换器不在core模块中
     * @deprecated 请使用html模块中的HtmlToDocxCreator
     */
    @Deprecated
    public static DocumentCreator createHtmlCreator() {
        throw new UnsupportedOperationException("HTML转换器已移至html模块，请使用html模块中的HtmlToDocxCreator");
    }

    /**
     * 创建XML转换文档创建器
     *
     * @return XML转换创建器
     */
    public static XmlBasedDocxCreator createXmlCreator() {
        return new XmlBasedDocxCreator();
    }

    /**
     * 根据字符串类型创建文档创建器
     *
     * @param typeString 类型字符串 ("poi", "html", "xml")
     * @return 文档创建器实例
     */
    public static DocumentCreator createDocumentCreator(String typeString) {
        if (typeString == null || typeString.trim().isEmpty()) {
            throw new IllegalArgumentException("创建器类型不能为空");
        }

        String type = typeString.trim().toLowerCase();
        switch (type) {
            case "poi":
            case "direct":
                return createDirectCreator();
            case "html":
                return createHtmlCreator();
            case "xml":
                return createXmlCreator();
            default:
                throw new IllegalArgumentException("不支持的创建器类型: " + typeString +
                        "。支持的类型: poi/direct, html, xml");
        }
    }

    /**
     * 获取所有支持的创建器类型
     *
     * @return 创建器类型数组
     */
    public static CreatorType[] getSupportedTypes() {
        return CreatorType.values();
    }

    /**
     * 获取创建器类型的描述信息
     *
     * @param type 创建器类型
     * @return 描述信息
     */
    public static String getTypeDescription(CreatorType type) {
        switch (type) {
            case DIRECT_POI:
                return "直接使用Apache POI创建DOCX文档，性能最佳，功能完整";
            case HTML_CONVERSION:
                return "通过HTML转换创建DOCX文档，支持丰富的HTML格式";
            case XML_CONVERSION:
                return "通过自定义XML格式转换创建DOCX文档，结构化程度最高";
            default:
                return "未知类型";
        }
    }

    /**
     * 检查是否支持指定的创建器类型
     *
     * @param typeString 类型字符串
     * @return 是否支持
     */
    public static boolean isSupported(String typeString) {
        if (typeString == null || typeString.trim().isEmpty()) {
            return false;
        }

        String type = typeString.trim().toLowerCase();
        return "poi".equals(type) || "direct".equals(type) ||
                "html".equals(type) || "xml".equals(type);
    }

    /**
     * 获取默认创建器
     *
     * @return 默认创建器（POI直接创建器）
     */
    public static DocumentCreator createDefaultCreator() {
        return createDirectCreator();
    }

    /**
     * 创建方式枚举
     */
    public enum CreatorType {
        /**
         * 直接使用POI类创建DOCX
         */
        DIRECT_POI,

        /**
         * 通过HTML转换创建DOCX
         */
        HTML_CONVERSION,

        /**
         * 通过XML转换创建DOCX
         */
        XML_CONVERSION
    }
}