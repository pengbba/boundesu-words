package com.boundesu.words.common.constants;

/**
 * 表格相关常量
 * 管理表格样式、边框、单元格配置等
 * 
 * @author Boundesu Team
 * @version 1.0.0
 */
public final class TableConstants {
    
    /**
     * 私有构造函数，防止实例化
     */
    private TableConstants() {
        throw new UnsupportedOperationException("常量类不能被实例化");
    }
    
    // ========== 表格尺寸常量 ==========
    
    /** 最小表格行数 */
    public static final int MIN_ROWS = 1;
    
    /** 最小表格列数 */
    public static final int MIN_COLUMNS = 1;
    
    /** 最大表格行数 */
    public static final int MAX_ROWS = 1000;
    
    /** 最大表格列数 */
    public static final int MAX_COLUMNS = 63;
    
    /** 默认表格行数 */
    public static final int DEFAULT_ROWS = 3;
    
    /** 默认表格列数 */
    public static final int DEFAULT_COLUMNS = 3;
    
    // ========== 表格宽度常量 ==========
    
    /** 默认表格宽度（DXA单位） */
    public static final int DEFAULT_TABLE_WIDTH = 9000;
    
    /** 最小表格宽度 */
    public static final int MIN_TABLE_WIDTH = 1000;
    
    /** 最大表格宽度 */
    public static final int MAX_TABLE_WIDTH = 15000;
    
    /** 自动调整宽度 */
    public static final int AUTO_WIDTH = -1;
    
    /** 占满页面宽度 */
    public static final int FULL_PAGE_WIDTH = 9000;
    
    // ========== 单元格尺寸常量 ==========
    
    /** 默认单元格宽度 */
    public static final int DEFAULT_CELL_WIDTH = 3000;
    
    /** 最小单元格宽度 */
    public static final int MIN_CELL_WIDTH = 200;
    
    /** 最大单元格宽度 */
    public static final int MAX_CELL_WIDTH = 9000;
    
    /** 默认单元格高度 */
    public static final int DEFAULT_CELL_HEIGHT = 400;
    
    /** 最小单元格高度 */
    public static final int MIN_CELL_HEIGHT = 200;
    
    /** 自动调整高度 */
    public static final int AUTO_HEIGHT = -1;
    
    // ========== 表格边框常量 ==========
    
    /** 无边框 */
    public static final String BORDER_NONE = "none";
    
    /** 细边框 */
    public static final String BORDER_THIN = "thin";
    
    /** 中等边框 */
    public static final String BORDER_MEDIUM = "medium";
    
    /** 粗边框 */
    public static final String BORDER_THICK = "thick";
    
    /** 双线边框 */
    public static final String BORDER_DOUBLE = "double";
    
    /** 虚线边框 */
    public static final String BORDER_DASHED = "dashed";
    
    /** 点线边框 */
    public static final String BORDER_DOTTED = "dotted";
    
    // ========== 边框宽度常量（磅） ==========
    
    /** 细边框宽度 */
    public static final double BORDER_WIDTH_THIN = 0.5;
    
    /** 中等边框宽度 */
    public static final double BORDER_WIDTH_MEDIUM = 1.0;
    
    /** 粗边框宽度 */
    public static final double BORDER_WIDTH_THICK = 2.0;
    
    /** 超粗边框宽度 */
    public static final double BORDER_WIDTH_EXTRA_THICK = 3.0;
    
    // ========== 表格样式常量 ==========
    
    /** 简单表格样式 */
    public static final String STYLE_SIMPLE = "simple";
    
    /** 经典表格样式 */
    public static final String STYLE_CLASSIC = "classic";
    
    /** 现代表格样式 */
    public static final String STYLE_MODERN = "modern";
    
    /** 专业表格样式 */
    public static final String STYLE_PROFESSIONAL = "professional";
    
    /** 彩色表格样式 */
    public static final String STYLE_COLORFUL = "colorful";
    
    // ========== 表头样式常量 ==========
    
    /** 表头背景色 */
    public static final String HEADER_BACKGROUND_COLOR = "D9E1F2";
    
    /** 表头字体颜色 */
    public static final String HEADER_FONT_COLOR = "000000";
    
    /** 表头字体大小 */
    public static final int HEADER_FONT_SIZE = 12;
    
    /** 表头是否加粗 */
    public static final boolean HEADER_BOLD = true;
    
    /** 表头对齐方式 */
    public static final String HEADER_ALIGNMENT = "center";
    
    // ========== 单元格样式常量 ==========
    
    /** 默认单元格背景色 */
    public static final String CELL_BACKGROUND_COLOR = "FFFFFF";
    
    /** 交替行背景色 */
    public static final String ALTERNATE_ROW_COLOR = "F2F2F2";
    
    /** 单元格字体颜色 */
    public static final String CELL_FONT_COLOR = "000000";
    
    /** 单元格字体大小 */
    public static final int CELL_FONT_SIZE = 11;
    
    /** 单元格对齐方式 */
    public static final String CELL_ALIGNMENT = "left";
    
    // ========== 单元格内边距常量 ==========
    
    /** 默认单元格内边距（磅） */
    public static final double DEFAULT_CELL_PADDING = 4.0;
    
    /** 最小单元格内边距 */
    public static final double MIN_CELL_PADDING = 0.0;
    
    /** 最大单元格内边距 */
    public static final double MAX_CELL_PADDING = 20.0;
    
    /** 紧密内边距 */
    public static final double TIGHT_CELL_PADDING = 2.0;
    
    /** 宽松内边距 */
    public static final double LOOSE_CELL_PADDING = 8.0;
    
    // ========== 表格对齐常量 ==========
    
    /** 表格左对齐 */
    public static final String TABLE_ALIGN_LEFT = "left";
    
    /** 表格居中对齐 */
    public static final String TABLE_ALIGN_CENTER = "center";
    
    /** 表格右对齐 */
    public static final String TABLE_ALIGN_RIGHT = "right";
    
    // ========== 单元格合并常量 ==========
    
    /** 水平合并 */
    public static final String MERGE_HORIZONTAL = "horizontal";
    
    /** 垂直合并 */
    public static final String MERGE_VERTICAL = "vertical";
    
    /** 合并起始单元格 */
    public static final String MERGE_START = "start";
    
    /** 合并继续单元格 */
    public static final String MERGE_CONTINUE = "continue";
    
    // ========== 预设表格配置 ==========
    
    /**
     * 表格配置类
     */
    public static class TableConfig {
        private final int rows;
        private final int columns;
        private final int width;
        private final String style;
        private final String borderStyle;
        private final double borderWidth;
        private final String alignment;
        private final boolean hasHeader;
        private final boolean alternateRows;
        
        public TableConfig(int rows, int columns, int width, String style, 
                          String borderStyle, double borderWidth, String alignment, 
                          boolean hasHeader, boolean alternateRows) {
            this.rows = rows;
            this.columns = columns;
            this.width = width;
            this.style = style;
            this.borderStyle = borderStyle;
            this.borderWidth = borderWidth;
            this.alignment = alignment;
            this.hasHeader = hasHeader;
            this.alternateRows = alternateRows;
        }
        
        // Getters
        public int getRows() { return rows; }
        public int getColumns() { return columns; }
        public int getWidth() { return width; }
        public String getStyle() { return style; }
        public String getBorderStyle() { return borderStyle; }
        public double getBorderWidth() { return borderWidth; }
        public String getAlignment() { return alignment; }
        public boolean hasHeader() { return hasHeader; }
        public boolean hasAlternateRows() { return alternateRows; }
    }
    
    /**
     * 单元格配置类
     */
    public static class CellConfig {
        private final String backgroundColor;
        private final String fontColor;
        private final int fontSize;
        private final String alignment;
        private final boolean bold;
        private final double padding;
        
        public CellConfig(String backgroundColor, String fontColor, int fontSize, 
                         String alignment, boolean bold, double padding) {
            this.backgroundColor = backgroundColor;
            this.fontColor = fontColor;
            this.fontSize = fontSize;
            this.alignment = alignment;
            this.bold = bold;
            this.padding = padding;
        }
        
        // Getters
        public String getBackgroundColor() { return backgroundColor; }
        public String getFontColor() { return fontColor; }
        public int getFontSize() { return fontSize; }
        public String getAlignment() { return alignment; }
        public boolean isBold() { return bold; }
        public double getPadding() { return padding; }
    }
    
    /**
     * 获取默认表格配置
     */
    public static TableConfig getDefaultTableConfig() {
        return new TableConfig(DEFAULT_ROWS, DEFAULT_COLUMNS, DEFAULT_TABLE_WIDTH, 
                              STYLE_SIMPLE, BORDER_THIN, BORDER_WIDTH_THIN, 
                              TABLE_ALIGN_LEFT, true, false);
    }
    
    /**
     * 获取简单表格配置
     */
    public static TableConfig getSimpleTableConfig() {
        return new TableConfig(DEFAULT_ROWS, DEFAULT_COLUMNS, DEFAULT_TABLE_WIDTH, 
                              STYLE_SIMPLE, BORDER_THIN, BORDER_WIDTH_THIN, 
                              TABLE_ALIGN_CENTER, false, false);
    }
    
    /**
     * 获取专业表格配置
     */
    public static TableConfig getProfessionalTableConfig() {
        return new TableConfig(DEFAULT_ROWS, DEFAULT_COLUMNS, FULL_PAGE_WIDTH, 
                              STYLE_PROFESSIONAL, BORDER_MEDIUM, BORDER_WIDTH_MEDIUM, 
                              TABLE_ALIGN_CENTER, true, true);
    }
    
    /**
     * 获取表头单元格配置
     */
    public static CellConfig getHeaderCellConfig() {
        return new CellConfig(HEADER_BACKGROUND_COLOR, HEADER_FONT_COLOR, 
                             HEADER_FONT_SIZE, HEADER_ALIGNMENT, HEADER_BOLD, 
                             DEFAULT_CELL_PADDING);
    }
    
    /**
     * 获取普通单元格配置
     */
    public static CellConfig getNormalCellConfig() {
        return new CellConfig(CELL_BACKGROUND_COLOR, CELL_FONT_COLOR, 
                             CELL_FONT_SIZE, CELL_ALIGNMENT, false, 
                             DEFAULT_CELL_PADDING);
    }
    
    /**
     * 获取交替行单元格配置
     */
    public static CellConfig getAlternateCellConfig() {
        return new CellConfig(ALTERNATE_ROW_COLOR, CELL_FONT_COLOR, 
                             CELL_FONT_SIZE, CELL_ALIGNMENT, false, 
                             DEFAULT_CELL_PADDING);
    }
}