package com.boundesu.words.common.constants;

/**
 * 页头页脚常量配置类
 * 管理页头页脚的尺寸、布局、边距等配置
 */
public class HeaderFooterConstants {

    // ========== 图片尺寸常量 ==========

    /**
     * 默认页头图片宽度（像素）
     */
    public static final int DEFAULT_HEADER_IMAGE_WIDTH = 100;

    /**
     * 默认页头图片高度（像素）
     */
    public static final int DEFAULT_HEADER_IMAGE_HEIGHT = 50;

    /**
     * 默认页脚图片宽度（像素）
     */
    public static final int DEFAULT_FOOTER_IMAGE_WIDTH = 80;

    /**
     * 默认页脚图片高度（像素）
     */
    public static final int DEFAULT_FOOTER_IMAGE_HEIGHT = 40;

    /**
     * 小尺寸图片宽度
     */
    public static final int SMALL_IMAGE_WIDTH = 60;

    /**
     * 小尺寸图片高度
     */
    public static final int SMALL_IMAGE_HEIGHT = 30;

    /**
     * 中等尺寸图片宽度
     */
    public static final int MEDIUM_IMAGE_WIDTH = 120;

    /**
     * 中等尺寸图片高度
     */
    public static final int MEDIUM_IMAGE_HEIGHT = 60;

    /**
     * 大尺寸图片宽度
     */
    public static final int LARGE_IMAGE_WIDTH = 200;

    /**
     * 大尺寸图片高度
     */
    public static final int LARGE_IMAGE_HEIGHT = 100;

    /**
     * 占满页面宽度的图片宽度（A4纸张宽度减去边距）
     */
    public static final int FULL_WIDTH_IMAGE_WIDTH = 595; // A4宽度约595像素

    /**
     * 占满页面宽度时的图片高度
     */
    public static final int FULL_WIDTH_IMAGE_HEIGHT = 80;

    // ========== 边距常量 ==========

    /**
     * 默认页头边距（磅）
     */
    public static final double DEFAULT_HEADER_MARGIN = 36.0; // 0.5英寸

    /**
     * 默认页脚边距（磅）
     */
    public static final double DEFAULT_FOOTER_MARGIN = 36.0; // 0.5英寸

    /**
     * 最小页头边距（磅）
     */
    public static final double MIN_HEADER_MARGIN = 18.0; // 0.25英寸

    /**
     * 最小页脚边距（磅）
     */
    public static final double MIN_FOOTER_MARGIN = 18.0; // 0.25英寸

    /**
     * 最大页头边距（磅）
     */
    public static final double MAX_HEADER_MARGIN = 72.0; // 1英寸

    /**
     * 最大页脚边距（磅）
     */
    public static final double MAX_FOOTER_MARGIN = 72.0; // 1英寸

    // ========== 对齐方式常量 ==========

    /**
     * 左对齐
     */
    public static final String ALIGN_LEFT = "left";

    /**
     * 居中对齐
     */
    public static final String ALIGN_CENTER = "center";

    /**
     * 右对齐
     */
    public static final String ALIGN_RIGHT = "right";

    /**
     * 两端对齐
     */
    public static final String ALIGN_JUSTIFY = "justify";

    // ========== 字体常量 ==========

    /**
     * 默认页头页脚字体
     */
    public static final String DEFAULT_FONT_FAMILY = "Times New Roman";

    /**
     * 默认页头页脚字体大小
     */
    public static final int DEFAULT_FONT_SIZE = 10;

    /**
     * 小字体大小
     */
    public static final int SMALL_FONT_SIZE = 8;

    /**
     * 大字体大小
     */
    public static final int LARGE_FONT_SIZE = 12;

    // ========== 布局常量 ==========

    /**
     * 图片在文本左侧
     */
    public static final String IMAGE_POSITION_LEFT = "left";

    /**
     * 图片在文本右侧
     */
    public static final String IMAGE_POSITION_RIGHT = "right";

    /**
     * 图片在文本上方
     */
    public static final String IMAGE_POSITION_TOP = "top";

    /**
     * 图片在文本下方
     */
    public static final String IMAGE_POSITION_BOTTOM = "bottom";

    /**
     * 图片作为背景
     */
    public static final String IMAGE_POSITION_BACKGROUND = "background";

    // ========== 预设配置 ==========

    /**
     * 获取小尺寸图片配置
     */
    public static ImageConfig getSmallImageConfig() {
        return new ImageConfig(SMALL_IMAGE_WIDTH, SMALL_IMAGE_HEIGHT, ALIGN_CENTER);
    }

    /**
     * 获取默认图片配置
     */
    public static ImageConfig getDefaultImageConfig() {
        return new ImageConfig(DEFAULT_HEADER_IMAGE_WIDTH, DEFAULT_HEADER_IMAGE_HEIGHT, ALIGN_CENTER);
    }

    /**
     * 获取大尺寸图片配置
     */
    public static ImageConfig getLargeImageConfig() {
        return new ImageConfig(LARGE_IMAGE_WIDTH, LARGE_IMAGE_HEIGHT, ALIGN_CENTER);
    }

    /**
     * 获取占满页面宽度的图片配置
     */
    public static ImageConfig getFullWidthImageConfig() {
        return new ImageConfig(FULL_WIDTH_IMAGE_WIDTH, FULL_WIDTH_IMAGE_HEIGHT, ALIGN_CENTER);
    }

    /**
     * 获取默认边距配置
     */
    public static MarginConfig getDefaultMarginConfig() {
        return new MarginConfig(DEFAULT_HEADER_MARGIN, DEFAULT_FOOTER_MARGIN,
                DEFAULT_HEADER_MARGIN, DEFAULT_HEADER_MARGIN);
    }

    /**
     * 获取最小边距配置
     */
    public static MarginConfig getMinMarginConfig() {
        return new MarginConfig(MIN_HEADER_MARGIN, MIN_FOOTER_MARGIN,
                MIN_HEADER_MARGIN, MIN_HEADER_MARGIN);
    }

    /**
     * 获取最大边距配置
     */
    public static MarginConfig getMaxMarginConfig() {
        return new MarginConfig(MAX_HEADER_MARGIN, MAX_FOOTER_MARGIN,
                MAX_HEADER_MARGIN, MAX_HEADER_MARGIN);
    }

    /**
     * 图片配置类
     */
    public static class ImageConfig {
        private final int width;
        private final int height;
        private final String alignment;

        public ImageConfig(int width, int height, String alignment) {
            this.width = width;
            this.height = height;
            this.alignment = alignment;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public String getAlignment() {
            return alignment;
        }
    }

    /**
     * 边距配置类
     */
    public static class MarginConfig {
        private final double top;
        private final double bottom;
        private final double left;
        private final double right;

        public MarginConfig(double top, double bottom, double left, double right) {
            this.top = top;
            this.bottom = bottom;
            this.left = left;
            this.right = right;
        }

        public double getTop() {
            return top;
        }

        public double getBottom() {
            return bottom;
        }

        public double getLeft() {
            return left;
        }

        public double getRight() {
            return right;
        }
    }
}