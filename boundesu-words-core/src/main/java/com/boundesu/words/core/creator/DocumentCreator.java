package com.boundesu.words.core.creator;

import java.io.IOException;
import java.nio.file.Path;

/**
 * 文档创建器接口
 * 支持多种方式创建DOCX文档
 * 
 * @author Boundesu Team
 * @version 1.0.0
 */
public interface DocumentCreator {

    /**
     * 创建DOCX文档并保存到指定路径
     *
     * @param outputPath 输出文件路径
     * @throws IOException 文件操作异常
     */
    void createDocument(Path outputPath) throws IOException;

    /**
     * 创建DOCX文档并返回字节数组
     *
     * @return 文档字节数组
     * @throws IOException 文档创建异常
     */
    byte[] createDocumentAsBytes() throws IOException;

    /**
     * 设置文档标题
     *
     * @param title 文档标题
     * @return 当前创建器实例
     */
    DocumentCreator setTitle(String title);

    /**
     * 设置文档作者
     *
     * @param author 文档作者
     * @return 当前创建器实例
     */
    DocumentCreator setAuthor(String author);

    /**
     * 添加段落文本
     *
     * @param text 段落文本
     * @return 当前创建器实例
     */
    DocumentCreator addParagraph(String text);

    /**
     * 添加标题
     *
     * @param text  标题文本
     * @param level 标题级别（1-6）
     * @return 当前创建器实例
     */
    DocumentCreator addHeading(String text, int level);

    /**
     * 设置页头
     *
     * @param headerText 页头文本
     * @return 当前创建器实例
     */
    DocumentCreator setHeader(String headerText);

    /**
     * 设置页脚
     *
     * @param footerText 页脚文本
     * @return 当前创建器实例
     */
    DocumentCreator setFooter(String footerText);

    /**
     * 启用页码
     *
     * @param enabled 是否启用页码
     * @return 当前创建器实例
     */
    DocumentCreator setPageNumberEnabled(boolean enabled);
}