package com.boundesu.words.sdk.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * Boundesu Words JDK17 SDK - 表格单元格样式模型
 * 
 * @author Boundesu
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoundesuTableCellStyle {
    
    /**
     * 字体名称
     */
    @Builder.Default
    private String fontFamily = "Arial";
    
    /**
     * 字体大小
     */
    @Builder.Default
    private int fontSize = 12;
    
    /**
     * 是否粗体
     */
    @Builder.Default
    private boolean bold = false;
    
    /**
     * 是否斜体
     */
    @Builder.Default
    private boolean italic = false;
    
    /**
     * 是否下划线
     */
    @Builder.Default
    private boolean underline = false;
    
    /**
     * 字体颜色
     */
    @Builder.Default
    private String color = "#000000";
    
    /**
     * 背景颜色
     */
    private String backgroundColor;
    
    /**
     * 边框样式
     */
    @Builder.Default
    private BorderStyle borderStyle = new BorderStyle();
    
    /**
     * 内边距
     */
    @Builder.Default
    private int padding = 5;
    
    /**
     * 边框样式内部类
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BorderStyle {
        
        /**
         * 上边框
         */
        @Builder.Default
        private Border top = new Border();
        
        /**
         * 右边框
         */
        @Builder.Default
        private Border right = new Border();
        
        /**
         * 下边框
         */
        @Builder.Default
        private Border bottom = new Border();
        
        /**
         * 左边框
         */
        @Builder.Default
        private Border left = new Border();
        
        /**
         * 设置所有边框
         */
        public BorderStyle setAllBorders(int width, String color, BorderType type) {
            Border border = new Border(width, color, type);
            this.top = border;
            this.right = border;
            this.bottom = border;
            this.left = border;
            return this;
        }
        
        /**
         * 设置所有边框
         */
        public BorderStyle setAllBorders(Border border) {
            this.top = border;
            this.right = border;
            this.bottom = border;
            this.left = border;
            return this;
        }
    }
    
    /**
     * 边框内部类
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Border {
        
        /**
         * 边框宽度
         */
        @Builder.Default
        private int width = 1;
        
        /**
         * 边框颜色
         */
        @Builder.Default
        private String color = "#000000";
        
        /**
         * 边框类型
         */
        @Builder.Default
        private BorderType type = BorderType.SOLID;
    }
    
    /**
     * 边框类型枚举
     */
    public enum BorderType {
        NONE("无边框"),
        SOLID("实线"),
        DASHED("虚线"),
        DOTTED("点线"),
        DOUBLE("双线"),
        GROOVE("凹槽"),
        RIDGE("凸起"),
        INSET("内嵌"),
        OUTSET("外凸");
        
        private final String description;
        
        BorderType(String description) {
            this.description = description;
        }
        
        public String getDescription() {
            return description;
        }
    }
    
    /**
     * 设置字体
     */
    public BoundesuTableCellStyle setFont(String fontFamily, int fontSize) {
        this.fontFamily = fontFamily;
        this.fontSize = fontSize;
        return this;
    }
    
    /**
     * 设置粗体
     */
    public BoundesuTableCellStyle setBold(boolean bold) {
        this.bold = bold;
        return this;
    }
    
    /**
     * 设置斜体
     */
    public BoundesuTableCellStyle setItalic(boolean italic) {
        this.italic = italic;
        return this;
    }
    
    /**
     * 设置下划线
     */
    public BoundesuTableCellStyle setUnderline(boolean underline) {
        this.underline = underline;
        return this;
    }
    
    /**
     * 设置颜色
     */
    public BoundesuTableCellStyle setColor(String color) {
        this.color = color;
        return this;
    }
    
    /**
     * 设置背景颜色
     */
    public BoundesuTableCellStyle setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }
    
    /**
     * 设置内边距
     */
    public BoundesuTableCellStyle setPadding(int padding) {
        this.padding = padding;
        return this;
    }
    
    /**
     * 设置边框
     */
    public BoundesuTableCellStyle setBorder(int width, String color, BorderType type) {
        if (this.borderStyle == null) {
            this.borderStyle = new BorderStyle();
        }
        this.borderStyle.setAllBorders(width, color, type);
        return this;
    }
    
    /**
     * 设置特定边框
     */
    public BoundesuTableCellStyle setBorder(String side, int width, String color, BorderType type) {
        if (this.borderStyle == null) {
            this.borderStyle = new BorderStyle();
        }
        
        Border border = new Border(width, color, type);
        switch (side.toLowerCase()) {
            case "top":
                this.borderStyle.setTop(border);
                break;
            case "right":
                this.borderStyle.setRight(border);
                break;
            case "bottom":
                this.borderStyle.setBottom(border);
                break;
            case "left":
                this.borderStyle.setLeft(border);
                break;
        }
        return this;
    }
    
    /**
     * 移除所有边框
     */
    public BoundesuTableCellStyle removeBorders() {
        if (this.borderStyle == null) {
            this.borderStyle = new BorderStyle();
        }
        this.borderStyle.setAllBorders(0, "transparent", BorderType.NONE);
        return this;
    }
    
    /**
     * 复制样式
     */
    public BoundesuTableCellStyle copy() {
        return BoundesuTableCellStyle.builder()
                .fontFamily(this.fontFamily)
                .fontSize(this.fontSize)
                .bold(this.bold)
                .italic(this.italic)
                .underline(this.underline)
                .color(this.color)
                .backgroundColor(this.backgroundColor)
                .borderStyle(this.borderStyle)
                .padding(this.padding)
                .build();
    }
}