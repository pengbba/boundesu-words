package com.boundesu.words.sdk.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * Boundesu Words JDK17 SDK - 页眉模型
 * 
 * @author Boundesu
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoundesuHeader {
    
    /**
     * 页眉文本
     */
    private String text;
    
    /**
     * 页眉样式
     */
    @Builder.Default
    private BoundesuParagraphStyle style = new BoundesuParagraphStyle();
    
    /**
     * 页眉对齐方式
     */
    @Builder.Default
    private BoundesuParagraph.TextAlignment alignment = BoundesuParagraph.TextAlignment.CENTER;
    
    /**
     * 页眉高度
     */
    @Builder.Default
    private int height = 50;
    
    /**
     * 是否显示页眉
     */
    @Builder.Default
    private boolean visible = true;
    
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
    public BoundesuHeader setText(String text) {
        this.text = text;
        return this;
    }
    
    /**
     * 设置样式
     */
    public BoundesuHeader setStyle(BoundesuParagraphStyle style) {
        this.style = style;
        return this;
    }
    
    /**
     * 设置对齐方式
     */
    public BoundesuHeader setAlignment(BoundesuParagraph.TextAlignment alignment) {
        this.alignment = alignment;
        return this;
    }
    
    /**
     * 设置高度
     */
    public BoundesuHeader setHeight(int height) {
        this.height = height;
        return this;
    }
    
    /**
     * 设置可见性
     */
    public BoundesuHeader setVisible(boolean visible) {
        this.visible = visible;
        return this;
    }
    
    /**
     * 设置边框
     */
    public BoundesuHeader setBorder(boolean showBorder, String borderColor) {
        this.showBorder = showBorder;
        this.borderColor = borderColor;
        return this;
    }
}