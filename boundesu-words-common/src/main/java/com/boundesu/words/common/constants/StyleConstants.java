package com.boundesu.words.common.constants;

/**
 * 文档样式相关常量
 * 管理字体、颜色、对齐方式等样式配置
 * 
 * @author Boundesu Team
 * @version 1.0.0
 */
public final class StyleConstants {
    
    /**
     * 私有构造函数，防止实例化
     */
    private StyleConstants() {
        throw new UnsupportedOperationException("常量类不能被实例化");
    }
    
    // ========== 字体常量 ==========
    
    /** 默认中文字体 */
    public static final String DEFAULT_CHINESE_FONT = "宋体";
    
    /** 默认英文字体 */
    public static final String DEFAULT_ENGLISH_FONT = "Times New Roman";
    
    /** 标题字体 */
    public static final String HEADING_FONT = "黑体";
    
    /** 代码字体 */
    public static final String CODE_FONT = "Consolas";
    
    /** 等宽字体 */
    public static final String MONOSPACE_FONT = "Courier New";
    
    // ========== 字体大小常量 ==========
    
    /** 超小字体 */
    public static final int FONT_SIZE_EXTRA_SMALL = 8;
    
    /** 小字体 */
    public static final int FONT_SIZE_SMALL = 10;
    
    /** 正常字体 */
    public static final int FONT_SIZE_NORMAL = 12;
    
    /** 大字体 */
    public static final int FONT_SIZE_LARGE = 14;
    
    /** 超大字体 */
    public static final int FONT_SIZE_EXTRA_LARGE = 16;
    
    /** 标题1字体大小 */
    public static final int FONT_SIZE_H1 = 24;
    
    /** 标题2字体大小 */
    public static final int FONT_SIZE_H2 = 20;
    
    /** 标题3字体大小 */
    public static final int FONT_SIZE_H3 = 18;
    
    /** 标题4字体大小 */
    public static final int FONT_SIZE_H4 = 16;
    
    /** 标题5字体大小 */
    public static final int FONT_SIZE_H5 = 14;
    
    /** 标题6字体大小 */
    public static final int FONT_SIZE_H6 = 12;
    
    // ========== 颜色常量 ==========
    
    /** 黑色 */
    public static final String COLOR_BLACK = "000000";
    
    /** 白色 */
    public static final String COLOR_WHITE = "FFFFFF";
    
    /** 红色 */
    public static final String COLOR_RED = "FF0000";
    
    /** 绿色 */
    public static final String COLOR_GREEN = "00FF00";
    
    /** 蓝色 */
    public static final String COLOR_BLUE = "0000FF";
    
    /** 灰色 */
    public static final String COLOR_GRAY = "808080";
    
    /** 深灰色 */
    public static final String COLOR_DARK_GRAY = "404040";
    
    /** 浅灰色 */
    public static final String COLOR_LIGHT_GRAY = "C0C0C0";
    
    /** 主题蓝色 */
    public static final String COLOR_THEME_BLUE = "4472C4";
    
    /** 主题绿色 */
    public static final String COLOR_THEME_GREEN = "70AD47";
    
    /** 警告橙色 */
    public static final String COLOR_WARNING_ORANGE = "FF8C00";
    
    /** 错误红色 */
    public static final String COLOR_ERROR_RED = "DC143C";
    
    /** 信息蓝色 */
    public static final String COLOR_INFO_BLUE = "1E90FF";
    
    /** 成功绿色 */
    public static final String COLOR_SUCCESS_GREEN = "32CD32";
    
    // ========== 对齐方式常量 ==========
    
    /** 左对齐 */
    public static final String ALIGN_LEFT = "left";
    
    /** 居中对齐 */
    public static final String ALIGN_CENTER = "center";
    
    /** 右对齐 */
    public static final String ALIGN_RIGHT = "right";
    
    /** 两端对齐 */
    public static final String ALIGN_JUSTIFY = "justify";
    
    /** 分散对齐 */
    public static final String ALIGN_DISTRIBUTE = "distribute";
    
    // ========== 行间距常量 ==========
    
    /** 单倍行距 */
    public static final double LINE_SPACING_SINGLE = 1.0;
    
    /** 1.5倍行距 */
    public static final double LINE_SPACING_ONE_HALF = 1.5;
    
    /** 双倍行距 */
    public static final double LINE_SPACING_DOUBLE = 2.0;
    
    /** 紧密行距 */
    public static final double LINE_SPACING_TIGHT = 0.9;
    
    /** 宽松行距 */
    public static final double LINE_SPACING_LOOSE = 1.2;
    
    // ========== 字体样式常量 ==========
    
    /** 粗体 */
    public static final String FONT_WEIGHT_BOLD = "bold";
    
    /** 正常粗细 */
    public static final String FONT_WEIGHT_NORMAL = "normal";
    
    /** 斜体 */
    public static final String FONT_STYLE_ITALIC = "italic";
    
    /** 正常样式 */
    public static final String FONT_STYLE_NORMAL = "normal";
    
    /** 下划线 */
    public static final String TEXT_DECORATION_UNDERLINE = "underline";
    
    /** 删除线 */
    public static final String TEXT_DECORATION_STRIKETHROUGH = "line-through";
    
    /** 无装饰 */
    public static final String TEXT_DECORATION_NONE = "none";
    
    // ========== 边框样式常量 ==========
    
    /** 实线边框 */
    public static final String BORDER_STYLE_SOLID = "solid";
    
    /** 虚线边框 */
    public static final String BORDER_STYLE_DASHED = "dashed";
    
    /** 点线边框 */
    public static final String BORDER_STYLE_DOTTED = "dotted";
    
    /** 双线边框 */
    public static final String BORDER_STYLE_DOUBLE = "double";
    
    /** 无边框 */
    public static final String BORDER_STYLE_NONE = "none";
    
    // ========== 边框宽度常量 ==========
    
    /** 细边框 */
    public static final String BORDER_WIDTH_THIN = "1pt";
    
    /** 中等边框 */
    public static final String BORDER_WIDTH_MEDIUM = "2pt";
    
    /** 粗边框 */
    public static final String BORDER_WIDTH_THICK = "3pt";
    
    // ========== 阴影样式常量 ==========
    
    /** 轻微阴影 */
    public static final String SHADOW_LIGHT = "2px 2px 4px rgba(0,0,0,0.1)";
    
    /** 中等阴影 */
    public static final String SHADOW_MEDIUM = "4px 4px 8px rgba(0,0,0,0.2)";
    
    /** 重阴影 */
    public static final String SHADOW_HEAVY = "6px 6px 12px rgba(0,0,0,0.3)";
    
    // ========== 预设样式配置 ==========
    
    /**
     * 样式配置类
     */
    public static class StyleConfig {
        private final String fontFamily;
        private final int fontSize;
        private final String color;
        private final String alignment;
        private final double lineSpacing;
        private final boolean bold;
        private final boolean italic;
        
        public StyleConfig(String fontFamily, int fontSize, String color, 
                          String alignment, double lineSpacing, boolean bold, boolean italic) {
            this.fontFamily = fontFamily;
            this.fontSize = fontSize;
            this.color = color;
            this.alignment = alignment;
            this.lineSpacing = lineSpacing;
            this.bold = bold;
            this.italic = italic;
        }
        
        // Getters
        public String getFontFamily() { return fontFamily; }
        public int getFontSize() { return fontSize; }
        public String getColor() { return color; }
        public String getAlignment() { return alignment; }
        public double getLineSpacing() { return lineSpacing; }
        public boolean isBold() { return bold; }
        public boolean isItalic() { return italic; }
    }
    
    /**
     * 获取默认正文样式
     */
    public static StyleConfig getDefaultBodyStyle() {
        return new StyleConfig(DEFAULT_CHINESE_FONT, FONT_SIZE_NORMAL, COLOR_BLACK, 
                              ALIGN_LEFT, LINE_SPACING_ONE_HALF, false, false);
    }
    
    /**
     * 获取标题样式
     */
    public static StyleConfig getHeadingStyle(int level) {
        int fontSize;
        switch (level) {
            case 1: fontSize = FONT_SIZE_H1; break;
            case 2: fontSize = FONT_SIZE_H2; break;
            case 3: fontSize = FONT_SIZE_H3; break;
            case 4: fontSize = FONT_SIZE_H4; break;
            case 5: fontSize = FONT_SIZE_H5; break;
            case 6: fontSize = FONT_SIZE_H6; break;
            default: fontSize = FONT_SIZE_NORMAL;
        }
        return new StyleConfig(HEADING_FONT, fontSize, COLOR_BLACK, 
                              ALIGN_LEFT, LINE_SPACING_SINGLE, true, false);
    }
    
    /**
     * 获取代码样式
     */
    public static StyleConfig getCodeStyle() {
        return new StyleConfig(CODE_FONT, FONT_SIZE_SMALL, COLOR_DARK_GRAY, 
                              ALIGN_LEFT, LINE_SPACING_SINGLE, false, false);
    }
}