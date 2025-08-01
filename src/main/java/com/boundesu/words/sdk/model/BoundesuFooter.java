package com.boundesu.words.sdk.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * Boundesu Words JDK17 SDK - 页脚模型
 * 
 * @author Boundesu
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoundesuFooter {
    
    /**
     * 页脚文本
     */
    private String text;
    
    /**
     * 页脚样式
     */
    @Builder.Default
    private BoundesuParagraphStyle style = new BoundesuParagraphStyle();
    
    /**
     * 页脚对齐方式
     */
    @Builder.Default
    private BoundesuParagraph.TextAlignment alignment = BoundesuParagraph.TextAlignment.CENTER;
    
    /**
     * 页脚高度
     */
    @Builder.Default
    private int height = 50;
    
    /**
     * 是否显示页脚
     */
    @Builder.Default
    private boolean visible = true;
    
    /**
     * 是否显示页码
     */
    @Builder.Default
    private boolean showPageNumber = true;
    
    /**
     * 页码格式
     */
    @Builder.Default
    private String pageNumberFormat = "第 {page} 页，共 {total} 页";
    
    /**
     * 是否显示边框
     */
    @Builder.Default
    private boolean showBorder = false;
    
    /**
     * 边框颜色
     */
    @Builder.Default
    private String borderColor = "#000000";
    
    /**
     * 设置文本
     */
    public BoundesuFooter setText(String text) {
        this.text = text;
        return this;
    }
    
    /**
     * 设置样式
     */
    public BoundesuFooter setStyle(BoundesuParagraphStyle style) {
        this.style = style;
        return this;
    }
    
    /**
     * 设置对齐方式
     */
    public BoundesuFooter setAlignment(BoundesuParagraph.TextAlignment alignment) {
        this.alignment = alignment;
        return this;
    }
    
    /**
     * 设置高度
     */
    public BoundesuFooter setHeight(int height) {
        this.height = height;
        return this;
    }
    
    /**
     * 设置可见性
     */
    public BoundesuFooter setVisible(boolean visible) {
        this.visible = visible;
        return this;
    }
    
    /**
     * 设置页码显示
     */
    public BoundesuFooter setPageNumber(boolean showPageNumber, String format) {
        this.showPageNumber = showPageNumber;
        this.pageNumberFormat = format;
        return this;
    }
    
    /**
     * 设置边框
     */
    public BoundesuFooter setBorder(boolean showBorder, String borderColor) {
        this.showBorder = showBorder;
        this.borderColor = borderColor;
        return this;
    }
}