package com.boundesu.words.common.creator;

import com.boundesu.words.common.constants.HeaderFooterConstants;

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
     * 设置页头并插入图片
     *
     * @param headerText 页头文本
     * @param imagePath  图片文件路径
     * @return 当前创建器实例
     */
    DocumentCreator setHeaderWithImage(String headerText, String imagePath);

    /**
     * 设置页头并插入图片（带尺寸控制）
     *
     * @param headerText 页头文本
     * @param imagePath  图片文件路径
     * @param width      图片宽度（像素）
     * @param height     图片高度（像素）
     * @return 当前创建器实例
     */
    DocumentCreator setHeaderWithImage(String headerText, String imagePath, int width, int height);

    /**
     * 设置页脚
     *
     * @param footerText 页脚文本
     * @return 当前创建器实例
     */
    DocumentCreator setFooter(String footerText);

    /**
     * 设置页脚并插入图片
     *
     * @param footerText 页脚文本
     * @param imagePath  图片文件路径
     * @return 当前创建器实例
     */
    DocumentCreator setFooterWithImage(String footerText, String imagePath);

    /**
     * 设置页脚并插入图片（带尺寸控制）
     *
     * @param footerText 页脚文本
     * @param imagePath  图片文件路径
     * @param width      图片宽度（像素）
     * @param height     图片高度（像素）
     * @return 当前创建器实例
     */
    DocumentCreator setFooterWithImage(String footerText, String imagePath, int width, int height);

    /**
     * 启用页码
     *
     * @param enabled 是否启用页码
     * @return 当前创建器实例
     */
    DocumentCreator setPageNumberEnabled(boolean enabled);

    // ========== 高级页头页脚配置方法 ==========

    /**
     * 设置页头并插入图片（带完整配置）
     *
     * @param headerText  页头文本
     * @param imagePath   图片文件路径
     * @param imageConfig 图片配置（尺寸和对齐方式）
     * @return 当前创建器实例
     */
    default DocumentCreator setHeaderWithImageAdvanced(String headerText, String imagePath,
                                                       HeaderFooterConstants.ImageConfig imageConfig) {
        return setHeaderWithImage(headerText, imagePath, imageConfig.getWidth(), imageConfig.getHeight());
    }

    /**
     * 设置页脚并插入图片（带完整配置）
     *
     * @param footerText  页脚文本
     * @param imagePath   图片文件路径
     * @param imageConfig 图片配置（尺寸和对齐方式）
     * @return 当前创建器实例
     */
    default DocumentCreator setFooterWithImageAdvanced(String footerText, String imagePath,
                                                       HeaderFooterConstants.ImageConfig imageConfig) {
        return setFooterWithImage(footerText, imagePath, imageConfig.getWidth(), imageConfig.getHeight());
    }

    /**
     * 设置页头图片（占满页面宽度）
     *
     * @param headerText 页头文本
     * @param imagePath  图片文件路径
     * @return 当前创建器实例
     */
    default DocumentCreator setHeaderWithFullWidthImage(String headerText, String imagePath) {
        HeaderFooterConstants.ImageConfig config = HeaderFooterConstants.getFullWidthImageConfig();
        return setHeaderWithImage(headerText, imagePath, config.getWidth(), config.getHeight());
    }

    /**
     * 设置页脚图片（占满页面宽度）
     *
     * @param footerText 页脚文本
     * @param imagePath  图片文件路径
     * @return 当前创建器实例
     */
    default DocumentCreator setFooterWithFullWidthImage(String footerText, String imagePath) {
        HeaderFooterConstants.ImageConfig config = HeaderFooterConstants.getFullWidthImageConfig();
        return setFooterWithImage(footerText, imagePath, config.getWidth(), config.getHeight());
    }

    /**
     * 设置页头图片（小尺寸）
     *
     * @param headerText 页头文本
     * @param imagePath  图片文件路径
     * @return 当前创建器实例
     */
    default DocumentCreator setHeaderWithSmallImage(String headerText, String imagePath) {
        HeaderFooterConstants.ImageConfig config = HeaderFooterConstants.getSmallImageConfig();
        return setHeaderWithImage(headerText, imagePath, config.getWidth(), config.getHeight());
    }

    /**
     * 设置页脚图片（小尺寸）
     *
     * @param footerText 页脚文本
     * @param imagePath  图片文件路径
     * @return 当前创建器实例
     */
    default DocumentCreator setFooterWithSmallImage(String footerText, String imagePath) {
        HeaderFooterConstants.ImageConfig config = HeaderFooterConstants.getSmallImageConfig();
        return setFooterWithImage(footerText, imagePath, config.getWidth(), config.getHeight());
    }

    /**
     * 设置页头图片（大尺寸）
     *
     * @param headerText 页头文本
     * @param imagePath  图片文件路径
     * @return 当前创建器实例
     */
    default DocumentCreator setHeaderWithLargeImage(String headerText, String imagePath) {
        HeaderFooterConstants.ImageConfig config = HeaderFooterConstants.getLargeImageConfig();
        return setHeaderWithImage(headerText, imagePath, config.getWidth(), config.getHeight());
    }

    /**
     * 设置页脚图片（大尺寸）
     *
     * @param footerText 页脚文本
     * @param imagePath  图片文件路径
     * @return 当前创建器实例
     */
    default DocumentCreator setFooterWithLargeImage(String footerText, String imagePath) {
        HeaderFooterConstants.ImageConfig config = HeaderFooterConstants.getLargeImageConfig();
        return setFooterWithImage(footerText, imagePath, config.getWidth(), config.getHeight());
    }

    /**
     * 设置页头对齐方式
     *
     * @param alignment 对齐方式（left, center, right, justify）
     * @return 当前创建器实例
     */
    default DocumentCreator setHeaderAlignment(String alignment) {
        // 默认实现，子类可以重写
        return this;
    }

    /**
     * 设置页脚对齐方式
     *
     * @param alignment 对齐方式（left, center, right, justify）
     * @return 当前创建器实例
     */
    default DocumentCreator setFooterAlignment(String alignment) {
        // 默认实现，子类可以重写
        return this;
    }

    /**
     * 设置页头边距
     *
     * @param marginConfig 边距配置
     * @return 当前创建器实例
     */
    default DocumentCreator setHeaderMargin(HeaderFooterConstants.MarginConfig marginConfig) {
        // 默认实现，子类可以重写
        return this;
    }

    /**
     * 设置页脚边距
     *
     * @param marginConfig 边距配置
     * @return 当前创建器实例
     */
    default DocumentCreator setFooterMargin(HeaderFooterConstants.MarginConfig marginConfig) {
        // 默认实现，子类可以重写
        return this;
    }
}