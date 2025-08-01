package com.boundesu.words.sdk.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * Boundesu Words JDK17 SDK - 文档样式模型
 * 
 * @author Boundesu
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoundesuDocumentStyle {
    
    /**
     * 页面宽度（像素）
     */
    @Builder.Default
    private int pageWidth = 595; // A4宽度
    
    /**
     * 页面高度（像素）
     */
    @Builder.Default
    private int pageHeight = 842; // A4高度
    
    /**
     * 页面方向
     */
    @Builder.Default
    private PageOrientation orientation = PageOrientation.PORTRAIT;
    
    /**
     * 页边距
     */
    @Builder.Default
    private PageMargins margins = new PageMargins();
    
    /**
     * 默认字体
     */
    @Builder.Default
    private String defaultFontFamily = "Arial";
    
    /**
     * 默认字体大小
     */
    @Builder.Default
    private int defaultFontSize = 12;
    
    /**
     * 默认行间距
     */
    @Builder.Default
    private double defaultLineSpacing = 1.0;
    
    /**
     * 背景颜色
     */
    @Builder.Default
    private String backgroundColor = "#ffffff";
    
    /**
     * 文档主题
     */
    @Builder.Default
    private DocumentTheme theme = DocumentTheme.DEFAULT;
    
    /**
     * 页面方向枚举
     */
    public enum PageOrientation {
        PORTRAIT("纵向"),
        LANDSCAPE("横向");
        
        private final String description;
        
        PageOrientation(String description) {
            this.description = description;
        }
        
        public String getDescription() {
            return description;
        }
    }
    
    /**
     * 文档主题枚举
     */
    public enum DocumentTheme {
        DEFAULT("默认"),
        PROFESSIONAL("专业"),
        MODERN("现代"),
        CLASSIC("经典"),
        MINIMAL("简约");
        
        private final String description;
        
        DocumentTheme(String description) {
            this.description = description;
        }
        
        public String getDescription() {
            return description;
        }
    }
    
    /**
     * 页边距内部类
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PageMargins {
        
        /**
         * 上边距
         */
        @Builder.Default
        private int top = 72; // 1英寸
        
        /**
         * 右边距
         */
        @Builder.Default
        private int right = 72; // 1英寸
        
        /**
         * 下边距
         */
        @Builder.Default
        private int bottom = 72; // 1英寸
        
        /**
         * 左边距
         */
        @Builder.Default
        private int left = 72; // 1英寸
        
        /**
         * 设置所有边距
         */
        public PageMargins setAll(int margin) {
            this.top = margin;
            this.right = margin;
            this.bottom = margin;
            this.left = margin;
            return this;
        }
    }
    
    /**
     * 设置页面尺寸为A4
     */
    public BoundesuDocumentStyle setA4() {
        this.pageWidth = 595;
        this.pageHeight = 842;
        return this;
    }
    
    /**
     * 设置页面尺寸为A3
     */
    public BoundesuDocumentStyle setA3() {
        this.pageWidth = 842;
        this.pageHeight = 1191;
        return this;
    }
    
    /**
     * 设置页面尺寸为Letter
     */
    public BoundesuDocumentStyle setLetter() {
        this.pageWidth = 612;
        this.pageHeight = 792;
        return this;
    }
    
    /**
     * 设置自定义页面尺寸
     */
    public BoundesuDocumentStyle setCustomSize(int width, int height) {
        this.pageWidth = width;
        this.pageHeight = height;
        return this;
    }
    
    /**
     * 设置页面方向
     */
    public BoundesuDocumentStyle setOrientation(PageOrientation orientation) {
        this.orientation = orientation;
        if (orientation == PageOrientation.LANDSCAPE) {
            // 交换宽高
            int temp = this.pageWidth;
            this.pageWidth = this.pageHeight;
            this.pageHeight = temp;
        }
        return this;
    }
    
    /**
     * 设置页边距
     */
    public BoundesuDocumentStyle setMargins(int top, int right, int bottom, int left) {
        this.margins = new PageMargins(top, right, bottom, left);
        return this;
    }
    
    /**
     * 设置统一页边距
     */
    public BoundesuDocumentStyle setMargins(int margin) {
        this.margins = new PageMargins().setAll(margin);
        return this;
    }
    
    /**
     * 设置默认字体
     */
    public BoundesuDocumentStyle setDefaultFont(String fontFamily, int fontSize) {
        this.defaultFontFamily = fontFamily;
        this.defaultFontSize = fontSize;
        return this;
    }
    
    /**
     * 设置默认行间距
     */
    public BoundesuDocumentStyle setDefaultLineSpacing(double lineSpacing) {
        this.defaultLineSpacing = lineSpacing;
        return this;
    }
    
    /**
     * 设置背景颜色
     */
    public BoundesuDocumentStyle setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }
    
    /**
     * 应用主题
     */
    public BoundesuDocumentStyle applyTheme(DocumentTheme theme) {
        this.theme = theme;
        
        switch (theme) {
            case PROFESSIONAL:
                this.defaultFontFamily = "Times New Roman";
                this.defaultFontSize = 12;
                this.defaultLineSpacing = 1.15;
                this.backgroundColor = "#ffffff";
                break;
                
            case MODERN:
                this.defaultFontFamily = "Calibri";
                this.defaultFontSize = 11;
                this.defaultLineSpacing = 1.08;
                this.backgroundColor = "#ffffff";
                break;
                
            case CLASSIC:
                this.defaultFontFamily = "Georgia";
                this.defaultFontSize = 12;
                this.defaultLineSpacing = 1.2;
                this.backgroundColor = "#fefefe";
                break;
                
            case MINIMAL:
                this.defaultFontFamily = "Helvetica";
                this.defaultFontSize = 10;
                this.defaultLineSpacing = 1.0;
                this.backgroundColor = "#ffffff";
                break;
                
            default:
                this.defaultFontFamily = "Arial";
                this.defaultFontSize = 12;
                this.defaultLineSpacing = 1.0;
                this.backgroundColor = "#ffffff";
                break;
        }
        
        return this;
    }
    
    /**
     * 获取可用内容宽度
     */
    public int getContentWidth() {
        return pageWidth - margins.getLeft() - margins.getRight();
    }
    
    /**
     * 获取可用内容高度
     */
    public int getContentHeight() {
        return pageHeight - margins.getTop() - margins.getBottom();
    }
}