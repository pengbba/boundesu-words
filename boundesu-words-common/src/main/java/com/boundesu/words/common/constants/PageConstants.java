package com.boundesu.words.common.constants;

/**
 * 页面布局相关常量
 * 管理页面尺寸、边距、方向等配置
 * 
 * @author Boundesu Team
 * @version 1.0.0
 */
public final class PageConstants {
    
    /**
     * 私有构造函数，防止实例化
     */
    private PageConstants() {
        throw new UnsupportedOperationException("常量类不能被实例化");
    }
    
    // ========== 页面尺寸常量（磅） ==========
    
    /** A4纸张宽度 */
    public static final double A4_WIDTH = 595.0;
    
    /** A4纸张高度 */
    public static final double A4_HEIGHT = 842.0;
    
    /** A3纸张宽度 */
    public static final double A3_WIDTH = 842.0;
    
    /** A3纸张高度 */
    public static final double A3_HEIGHT = 1191.0;
    
    /** A5纸张宽度 */
    public static final double A5_WIDTH = 420.0;
    
    /** A5纸张高度 */
    public static final double A5_HEIGHT = 595.0;
    
    /** Letter纸张宽度 */
    public static final double LETTER_WIDTH = 612.0;
    
    /** Letter纸张高度 */
    public static final double LETTER_HEIGHT = 792.0;
    
    /** Legal纸张宽度 */
    public static final double LEGAL_WIDTH = 612.0;
    
    /** Legal纸张高度 */
    public static final double LEGAL_HEIGHT = 1008.0;
    
    // ========== 页面方向常量 ==========
    
    /** 纵向（竖向） */
    public static final String ORIENTATION_PORTRAIT = "portrait";
    
    /** 横向（横向） */
    public static final String ORIENTATION_LANDSCAPE = "landscape";
    
    // ========== 页边距常量（磅） ==========
    
    /** 默认页边距 */
    public static final double DEFAULT_MARGIN = 72.0; // 1英寸
    
    /** 窄页边距 */
    public static final double NARROW_MARGIN = 36.0; // 0.5英寸
    
    /** 宽页边距 */
    public static final double WIDE_MARGIN = 108.0; // 1.5英寸
    
    /** 最小页边距 */
    public static final double MIN_MARGIN = 18.0; // 0.25英寸
    
    /** 最大页边距 */
    public static final double MAX_MARGIN = 144.0; // 2英寸
    
    /** 默认上边距 */
    public static final double DEFAULT_TOP_MARGIN = 72.0;
    
    /** 默认下边距 */
    public static final double DEFAULT_BOTTOM_MARGIN = 72.0;
    
    /** 默认左边距 */
    public static final double DEFAULT_LEFT_MARGIN = 72.0;
    
    /** 默认右边距 */
    public static final double DEFAULT_RIGHT_MARGIN = 72.0;
    
    // ========== 页眉页脚边距常量（磅） ==========
    
    /** 默认页眉边距 */
    public static final double DEFAULT_HEADER_MARGIN = 36.0; // 0.5英寸
    
    /** 默认页脚边距 */
    public static final double DEFAULT_FOOTER_MARGIN = 36.0; // 0.5英寸
    
    /** 最小页眉边距 */
    public static final double MIN_HEADER_MARGIN = 18.0; // 0.25英寸
    
    /** 最小页脚边距 */
    public static final double MIN_FOOTER_MARGIN = 18.0; // 0.25英寸
    
    /** 最大页眉边距 */
    public static final double MAX_HEADER_MARGIN = 72.0; // 1英寸
    
    /** 最大页脚边距 */
    public static final double MAX_FOOTER_MARGIN = 72.0; // 1英寸
    
    // ========== 分栏常量 ==========
    
    /** 单栏 */
    public static final int COLUMNS_SINGLE = 1;
    
    /** 双栏 */
    public static final int COLUMNS_DOUBLE = 2;
    
    /** 三栏 */
    public static final int COLUMNS_TRIPLE = 3;
    
    /** 最大分栏数 */
    public static final int MAX_COLUMNS = 6;
    
    /** 默认栏间距（磅） */
    public static final double DEFAULT_COLUMN_SPACING = 36.0;
    
    /** 最小栏间距 */
    public static final double MIN_COLUMN_SPACING = 18.0;
    
    /** 最大栏间距 */
    public static final double MAX_COLUMN_SPACING = 72.0;
    
    // ========== 页面背景常量 ==========
    
    /** 无背景 */
    public static final String BACKGROUND_NONE = "none";
    
    /** 纯色背景 */
    public static final String BACKGROUND_COLOR = "color";
    
    /** 图片背景 */
    public static final String BACKGROUND_IMAGE = "image";
    
    /** 渐变背景 */
    public static final String BACKGROUND_GRADIENT = "gradient";
    
    /** 默认背景色 */
    public static final String DEFAULT_BACKGROUND_COLOR = "FFFFFF";
    
    // ========== 页码常量 ==========
    
    /** 页码位置 - 页眉左侧 */
    public static final String PAGE_NUMBER_HEADER_LEFT = "header-left";
    
    /** 页码位置 - 页眉中央 */
    public static final String PAGE_NUMBER_HEADER_CENTER = "header-center";
    
    /** 页码位置 - 页眉右侧 */
    public static final String PAGE_NUMBER_HEADER_RIGHT = "header-right";
    
    /** 页码位置 - 页脚左侧 */
    public static final String PAGE_NUMBER_FOOTER_LEFT = "footer-left";
    
    /** 页码位置 - 页脚中央 */
    public static final String PAGE_NUMBER_FOOTER_CENTER = "footer-center";
    
    /** 页码位置 - 页脚右侧 */
    public static final String PAGE_NUMBER_FOOTER_RIGHT = "footer-right";
    
    /** 页码格式 - 数字 */
    public static final String PAGE_NUMBER_FORMAT_DECIMAL = "decimal";
    
    /** 页码格式 - 罗马数字小写 */
    public static final String PAGE_NUMBER_FORMAT_LOWER_ROMAN = "lower-roman";
    
    /** 页码格式 - 罗马数字大写 */
    public static final String PAGE_NUMBER_FORMAT_UPPER_ROMAN = "upper-roman";
    
    /** 页码格式 - 字母小写 */
    public static final String PAGE_NUMBER_FORMAT_LOWER_LETTER = "lower-letter";
    
    /** 页码格式 - 字母大写 */
    public static final String PAGE_NUMBER_FORMAT_UPPER_LETTER = "upper-letter";
    
    // ========== 单位转换常量 ==========
    
    /** 英寸到磅的转换比例 */
    public static final double INCHES_TO_POINTS = 72.0;
    
    /** 厘米到磅的转换比例 */
    public static final double CM_TO_POINTS = 28.35;
    
    /** 毫米到磅的转换比例 */
    public static final double MM_TO_POINTS = 2.835;
    
    /** 磅到英寸的转换比例 */
    public static final double POINTS_TO_INCHES = 1.0 / 72.0;
    
    /** 磅到厘米的转换比例 */
    public static final double POINTS_TO_CM = 1.0 / 28.35;
    
    /** 磅到毫米的转换比例 */
    public static final double POINTS_TO_MM = 1.0 / 2.835;
    
    // ========== 预设页面配置 ==========
    
    /**
     * 页面配置类
     */
    public static class PageConfig {
        private final double width;
        private final double height;
        private final String orientation;
        private final double topMargin;
        private final double bottomMargin;
        private final double leftMargin;
        private final double rightMargin;
        private final double headerMargin;
        private final double footerMargin;
        private final int columns;
        private final double columnSpacing;
        
        public PageConfig(double width, double height, String orientation,
                         double topMargin, double bottomMargin, double leftMargin, double rightMargin,
                         double headerMargin, double footerMargin, int columns, double columnSpacing) {
            this.width = width;
            this.height = height;
            this.orientation = orientation;
            this.topMargin = topMargin;
            this.bottomMargin = bottomMargin;
            this.leftMargin = leftMargin;
            this.rightMargin = rightMargin;
            this.headerMargin = headerMargin;
            this.footerMargin = footerMargin;
            this.columns = columns;
            this.columnSpacing = columnSpacing;
        }
        
        // Getters
        public double getWidth() { return width; }
        public double getHeight() { return height; }
        public String getOrientation() { return orientation; }
        public double getTopMargin() { return topMargin; }
        public double getBottomMargin() { return bottomMargin; }
        public double getLeftMargin() { return leftMargin; }
        public double getRightMargin() { return rightMargin; }
        public double getHeaderMargin() { return headerMargin; }
        public double getFooterMargin() { return footerMargin; }
        public int getColumns() { return columns; }
        public double getColumnSpacing() { return columnSpacing; }
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
        
        public MarginConfig(double all) {
            this(all, all, all, all);
        }
        
        // Getters
        public double getTop() { return top; }
        public double getBottom() { return bottom; }
        public double getLeft() { return left; }
        public double getRight() { return right; }
    }
    
    /**
     * 获取A4纵向页面配置
     */
    public static PageConfig getA4PortraitConfig() {
        return new PageConfig(A4_WIDTH, A4_HEIGHT, ORIENTATION_PORTRAIT,
                             DEFAULT_TOP_MARGIN, DEFAULT_BOTTOM_MARGIN, 
                             DEFAULT_LEFT_MARGIN, DEFAULT_RIGHT_MARGIN,
                             DEFAULT_HEADER_MARGIN, DEFAULT_FOOTER_MARGIN,
                             COLUMNS_SINGLE, DEFAULT_COLUMN_SPACING);
    }
    
    /**
     * 获取A4横向页面配置
     */
    public static PageConfig getA4LandscapeConfig() {
        return new PageConfig(A4_HEIGHT, A4_WIDTH, ORIENTATION_LANDSCAPE,
                             DEFAULT_TOP_MARGIN, DEFAULT_BOTTOM_MARGIN, 
                             DEFAULT_LEFT_MARGIN, DEFAULT_RIGHT_MARGIN,
                             DEFAULT_HEADER_MARGIN, DEFAULT_FOOTER_MARGIN,
                             COLUMNS_SINGLE, DEFAULT_COLUMN_SPACING);
    }
    
    /**
     * 获取Letter纵向页面配置
     */
    public static PageConfig getLetterPortraitConfig() {
        return new PageConfig(LETTER_WIDTH, LETTER_HEIGHT, ORIENTATION_PORTRAIT,
                             DEFAULT_TOP_MARGIN, DEFAULT_BOTTOM_MARGIN, 
                             DEFAULT_LEFT_MARGIN, DEFAULT_RIGHT_MARGIN,
                             DEFAULT_HEADER_MARGIN, DEFAULT_FOOTER_MARGIN,
                             COLUMNS_SINGLE, DEFAULT_COLUMN_SPACING);
    }
    
    /**
     * 获取窄边距配置
     */
    public static MarginConfig getNarrowMarginConfig() {
        return new MarginConfig(NARROW_MARGIN);
    }
    
    /**
     * 获取默认边距配置
     */
    public static MarginConfig getDefaultMarginConfig() {
        return new MarginConfig(DEFAULT_MARGIN);
    }
    
    /**
     * 获取宽边距配置
     */
    public static MarginConfig getWideMarginConfig() {
        return new MarginConfig(WIDE_MARGIN);
    }
    
    /**
     * 单位转换工具方法
     */
    public static class UnitConverter {
        
        /**
         * 英寸转磅
         */
        public static double inchesToPoints(double inches) {
            return inches * INCHES_TO_POINTS;
        }
        
        /**
         * 厘米转磅
         */
        public static double cmToPoints(double cm) {
            return cm * CM_TO_POINTS;
        }
        
        /**
         * 毫米转磅
         */
        public static double mmToPoints(double mm) {
            return mm * MM_TO_POINTS;
        }
        
        /**
         * 磅转英寸
         */
        public static double pointsToInches(double points) {
            return points * POINTS_TO_INCHES;
        }
        
        /**
         * 磅转厘米
         */
        public static double pointsToCm(double points) {
            return points * POINTS_TO_CM;
        }
        
        /**
         * 磅转毫米
         */
        public static double pointsToMm(double points) {
            return points * POINTS_TO_MM;
        }
    }
}