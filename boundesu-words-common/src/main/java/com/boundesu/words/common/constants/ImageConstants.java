package com.boundesu.words.common.constants;

/**
 * 图片相关常量
 * 管理图片格式、尺寸、质量等配置
 *
 * @author Boundesu Team
 * @version 1.0.0
 */
public final class ImageConstants {

    /**
     * JPEG格式
     */
    public static final String FORMAT_JPEG = "jpeg";

    // ========== 图片格式常量 ==========
    /**
     * JPG格式
     */
    public static final String FORMAT_JPG = "jpg";
    /**
     * PNG格式
     */
    public static final String FORMAT_PNG = "png";
    /**
     * GIF格式
     */
    public static final String FORMAT_GIF = "gif";
    /**
     * BMP格式
     */
    public static final String FORMAT_BMP = "bmp";
    /**
     * TIFF格式
     */
    public static final String FORMAT_TIFF = "tiff";
    /**
     * SVG格式
     */
    public static final String FORMAT_SVG = "svg";
    /**
     * WEBP格式
     */
    public static final String FORMAT_WEBP = "webp";
    /**
     * JPEG文件扩展名
     */
    public static final String EXT_JPEG = ".jpeg";

    // ========== 图片文件扩展名常量 ==========
    /**
     * JPG文件扩展名
     */
    public static final String EXT_JPG = ".jpg";
    /**
     * PNG文件扩展名
     */
    public static final String EXT_PNG = ".png";
    /**
     * GIF文件扩展名
     */
    public static final String EXT_GIF = ".gif";
    /**
     * BMP文件扩展名
     */
    public static final String EXT_BMP = ".bmp";
    /**
     * TIFF文件扩展名
     */
    public static final String EXT_TIFF = ".tiff";
    /**
     * SVG文件扩展名
     */
    public static final String EXT_SVG = ".svg";
    /**
     * WEBP文件扩展名
     */
    public static final String EXT_WEBP = ".webp";
    /**
     * 支持的图片格式
     */
    public static final String[] SUPPORTED_FORMATS = {
            FORMAT_JPEG, FORMAT_JPG, FORMAT_PNG, FORMAT_GIF,
            FORMAT_BMP, FORMAT_TIFF, FORMAT_SVG, FORMAT_WEBP
    };

    // ========== 支持的图片格式数组 ==========
    /**
     * 支持的图片扩展名
     */
    public static final String[] SUPPORTED_EXTENSIONS = {
            EXT_JPEG, EXT_JPG, EXT_PNG, EXT_GIF,
            EXT_BMP, EXT_TIFF, EXT_SVG, EXT_WEBP
    };
    /**
     * 缩略图宽度
     */
    public static final int THUMBNAIL_WIDTH = 150;

    // ========== 图片尺寸常量（像素） ==========
    /**
     * 缩略图高度
     */
    public static final int THUMBNAIL_HEIGHT = 150;
    /**
     * 小图片宽度
     */
    public static final int SMALL_WIDTH = 300;
    /**
     * 小图片高度
     */
    public static final int SMALL_HEIGHT = 200;
    /**
     * 中等图片宽度
     */
    public static final int MEDIUM_WIDTH = 600;
    /**
     * 中等图片高度
     */
    public static final int MEDIUM_HEIGHT = 400;
    /**
     * 大图片宽度
     */
    public static final int LARGE_WIDTH = 1200;
    /**
     * 大图片高度
     */
    public static final int LARGE_HEIGHT = 800;
    /**
     * 超大图片宽度
     */
    public static final int EXTRA_LARGE_WIDTH = 1920;
    /**
     * 超大图片高度
     */
    public static final int EXTRA_LARGE_HEIGHT = 1080;
    /**
     * 最大图片宽度
     */
    public static final int MAX_WIDTH = 4096;
    /**
     * 最大图片高度
     */
    public static final int MAX_HEIGHT = 4096;
    /**
     * 最小图片宽度
     */
    public static final int MIN_WIDTH = 1;
    /**
     * 最小图片高度
     */
    public static final int MIN_HEIGHT = 1;
    /**
     * 低质量
     */
    public static final float QUALITY_LOW = 0.3f;

    // ========== 图片质量常量 ==========
    /**
     * 中等质量
     */
    public static final float QUALITY_MEDIUM = 0.7f;
    /**
     * 高质量
     */
    public static final float QUALITY_HIGH = 0.9f;
    /**
     * 最高质量
     */
    public static final float QUALITY_MAXIMUM = 1.0f;
    /**
     * 默认质量
     */
    public static final float QUALITY_DEFAULT = QUALITY_HIGH;
    /**
     * 无压缩
     */
    public static final String COMPRESSION_NONE = "none";

    // ========== 图片压缩常量 ==========
    /**
     * 轻度压缩
     */
    public static final String COMPRESSION_LIGHT = "light";
    /**
     * 中度压缩
     */
    public static final String COMPRESSION_MEDIUM = "medium";
    /**
     * 重度压缩
     */
    public static final String COMPRESSION_HEAVY = "heavy";
    /**
     * 最大压缩
     */
    public static final String COMPRESSION_MAXIMUM = "maximum";
    /**
     * 左对齐
     */
    public static final String ALIGN_LEFT = "left";

    // ========== 图片对齐常量 ==========
    /**
     * 居中对齐
     */
    public static final String ALIGN_CENTER = "center";
    /**
     * 右对齐
     */
    public static final String ALIGN_RIGHT = "right";
    /**
     * 顶部对齐
     */
    public static final String ALIGN_TOP = "top";
    /**
     * 底部对齐
     */
    public static final String ALIGN_BOTTOM = "bottom";
    /**
     * 中间对齐
     */
    public static final String ALIGN_MIDDLE = "middle";
    /**
     * 保持比例缩放
     */
    public static final String SCALE_PROPORTIONAL = "proportional";

    // ========== 图片缩放模式常量 ==========
    /**
     * 拉伸填充
     */
    public static final String SCALE_STRETCH = "stretch";
    /**
     * 裁剪填充
     */
    public static final String SCALE_CROP = "crop";
    /**
     * 适应容器
     */
    public static final String SCALE_FIT = "fit";
    /**
     * 填充容器
     */
    public static final String SCALE_FILL = "fill";
    /**
     * 内联图片
     */
    public static final String POSITION_INLINE = "inline";

    // ========== 图片位置常量 ==========
    /**
     * 浮动左侧
     */
    public static final String POSITION_FLOAT_LEFT = "float-left";
    /**
     * 浮动右侧
     */
    public static final String POSITION_FLOAT_RIGHT = "float-right";
    /**
     * 绝对定位
     */
    public static final String POSITION_ABSOLUTE = "absolute";
    /**
     * 相对定位
     */
    public static final String POSITION_RELATIVE = "relative";
    /**
     * 背景图片
     */
    public static final String POSITION_BACKGROUND = "background";
    /**
     * 无边框
     */
    public static final String BORDER_NONE = "none";

    // ========== 图片边框常量 ==========
    /**
     * 细边框
     */
    public static final String BORDER_THIN = "thin";
    /**
     * 中等边框
     */
    public static final String BORDER_MEDIUM = "medium";
    /**
     * 粗边框
     */
    public static final String BORDER_THICK = "thick";
    /**
     * 圆角边框
     */
    public static final String BORDER_ROUNDED = "rounded";
    /**
     * 阴影边框
     */
    public static final String BORDER_SHADOW = "shadow";
    /**
     * 最大文件大小 - 10MB
     */
    public static final long MAX_FILE_SIZE = 10 * 1024 * 1024;

    // ========== 图片文件大小限制常量（字节） ==========
    /**
     * 推荐文件大小 - 2MB
     */
    public static final long RECOMMENDED_FILE_SIZE = 2 * 1024 * 1024;
    /**
     * 缩略图最大文件大小 - 100KB
     */
    public static final long THUMBNAIL_MAX_SIZE = 100 * 1024;
    /**
     * 默认DPI
     */
    public static final int DEFAULT_DPI = 96;

    // ========== DPI常量 ==========
    /**
     * 打印DPI
     */
    public static final int PRINT_DPI = 300;
    /**
     * 高质量DPI
     */
    public static final int HIGH_QUALITY_DPI = 150;
    /**
     * 网页DPI
     */
    public static final int WEB_DPI = 72;

    /**
     * 私有构造函数，防止实例化
     */
    private ImageConstants() {
        throw new UnsupportedOperationException("常量类不能被实例化");
    }

    // ========== 预设图片配置 ==========

    /**
     * 获取缩略图配置
     */
    public static ImageConfig getThumbnailConfig() {
        return new ImageConfig(THUMBNAIL_WIDTH, THUMBNAIL_HEIGHT, FORMAT_JPEG,
                QUALITY_MEDIUM, ALIGN_CENTER, SCALE_PROPORTIONAL,
                POSITION_INLINE, BORDER_NONE, DEFAULT_DPI);
    }

    /**
     * 获取小图片配置
     */
    public static ImageConfig getSmallImageConfig() {
        return new ImageConfig(SMALL_WIDTH, SMALL_HEIGHT, FORMAT_JPEG,
                QUALITY_HIGH, ALIGN_CENTER, SCALE_PROPORTIONAL,
                POSITION_INLINE, BORDER_NONE, DEFAULT_DPI);
    }

    /**
     * 获取中等图片配置
     */
    public static ImageConfig getMediumImageConfig() {
        return new ImageConfig(MEDIUM_WIDTH, MEDIUM_HEIGHT, FORMAT_JPEG,
                QUALITY_HIGH, ALIGN_CENTER, SCALE_PROPORTIONAL,
                POSITION_INLINE, BORDER_NONE, DEFAULT_DPI);
    }

    /**
     * 获取大图片配置
     */
    public static ImageConfig getLargeImageConfig() {
        return new ImageConfig(LARGE_WIDTH, LARGE_HEIGHT, FORMAT_JPEG,
                QUALITY_HIGH, ALIGN_CENTER, SCALE_PROPORTIONAL,
                POSITION_INLINE, BORDER_NONE, DEFAULT_DPI);
    }

    /**
     * 获取打印质量配置
     */
    public static ImageConfig getPrintQualityConfig() {
        return new ImageConfig(LARGE_WIDTH, LARGE_HEIGHT, FORMAT_PNG,
                QUALITY_MAXIMUM, ALIGN_CENTER, SCALE_PROPORTIONAL,
                POSITION_INLINE, BORDER_NONE, PRINT_DPI);
    }

    /**
     * 获取网页优化配置
     */
    public static ImageConfig getWebOptimizedConfig() {
        return new ImageConfig(MEDIUM_WIDTH, MEDIUM_HEIGHT, FORMAT_JPEG,
                QUALITY_MEDIUM, ALIGN_CENTER, SCALE_PROPORTIONAL,
                POSITION_INLINE, BORDER_NONE, WEB_DPI);
    }

    /**
     * 检查是否为支持的图片格式
     */
    public static boolean isSupportedFormat(String format) {
        if (format == null) return false;
        for (String supportedFormat : SUPPORTED_FORMATS) {
            if (supportedFormat.equalsIgnoreCase(format)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检查是否为支持的图片扩展名
     */
    public static boolean isSupportedExtension(String extension) {
        if (extension == null) return false;
        for (String supportedExt : SUPPORTED_EXTENSIONS) {
            if (supportedExt.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 从文件名获取扩展名
     */
    public static String getFileExtension(String filename) {
        if (filename == null || filename.isEmpty()) {
            return "";
        }
        int lastDotIndex = filename.lastIndexOf('.');
        if (lastDotIndex == -1 || lastDotIndex == filename.length() - 1) {
            return "";
        }
        return filename.substring(lastDotIndex).toLowerCase();
    }

    /**
     * 图片配置类
     */
    public static class ImageConfig {
        private final int width;
        private final int height;
        private final String format;
        private final float quality;
        private final String alignment;
        private final String scaleMode;
        private final String position;
        private final String borderStyle;
        private final int dpi;

        public ImageConfig(int width, int height, String format, float quality,
                           String alignment, String scaleMode, String position,
                           String borderStyle, int dpi) {
            this.width = width;
            this.height = height;
            this.format = format;
            this.quality = quality;
            this.alignment = alignment;
            this.scaleMode = scaleMode;
            this.position = position;
            this.borderStyle = borderStyle;
            this.dpi = dpi;
        }

        // Getters
        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public String getFormat() {
            return format;
        }

        public float getQuality() {
            return quality;
        }

        public String getAlignment() {
            return alignment;
        }

        public String getScaleMode() {
            return scaleMode;
        }

        public String getPosition() {
            return position;
        }

        public String getBorderStyle() {
            return borderStyle;
        }

        public int getDpi() {
            return dpi;
        }
    }

    /**
     * 图片尺寸类
     */
    public static class ImageSize {
        private final int width;
        private final int height;

        public ImageSize(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        /**
         * 计算宽高比
         */
        public double getAspectRatio() {
            return (double) width / height;
        }

        /**
         * 按比例缩放到指定宽度
         */
        public ImageSize scaleToWidth(int targetWidth) {
            int targetHeight = (int) (height * ((double) targetWidth / width));
            return new ImageSize(targetWidth, targetHeight);
        }

        /**
         * 按比例缩放到指定高度
         */
        public ImageSize scaleToHeight(int targetHeight) {
            int targetWidth = (int) (width * ((double) targetHeight / height));
            return new ImageSize(targetWidth, targetHeight);
        }

        /**
         * 按比例缩放到适应指定尺寸
         */
        public ImageSize scaleToFit(int maxWidth, int maxHeight) {
            double widthRatio = (double) maxWidth / width;
            double heightRatio = (double) maxHeight / height;
            double ratio = Math.min(widthRatio, heightRatio);

            return new ImageSize((int) (width * ratio), (int) (height * ratio));
        }
    }
}