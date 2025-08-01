package com.boundesu.words.sdk.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Map;
import java.util.HashMap;

/**
 * Boundesu Words JDK17 SDK - 段落模型
 * 
 * @author Boundesu
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoundesuParagraph {
    
    /**
     * 段落唯一标识
     */
    private String paragraphId;
    
    /**
     * 段落文本内容
     */
    private String text;
    
    /**
     * 段落样式
     */
    @Builder.Default
    private BoundesuParagraphStyle style = new BoundesuParagraphStyle();
    
    /**
     * 段落对齐方式
     */
    @Builder.Default
    private TextAlignment alignment = TextAlignment.LEFT;
    
    /**
     * 段落缩进（像素）
     */
    @Builder.Default
    private int indentation = 0;
    
    /**
     * 行间距
     */
    @Builder.Default
    private double lineSpacing = 1.0;
    
    /**
     * 段前间距（像素）
     */
    @Builder.Default
    private int spaceBefore = 0;
    
    /**
     * 段后间距（像素）
     */
    @Builder.Default
    private int spaceAfter = 0;
    
    /**
     * 是否分页符
     */
    @Builder.Default
    private boolean pageBreak = false;
    
    /**
     * 自定义属性
     */
    @Builder.Default
    private Map<String, Object> customProperties = new HashMap<>();
    
    /**
     * 文本对齐方式枚举
     */
    public enum TextAlignment {
        LEFT("左对齐"),
        CENTER("居中对齐"),
        RIGHT("右对齐"),
        JUSTIFY("两端对齐");
        
        private final String description;
        
        TextAlignment(String description) {
            this.description = description;
        }
        
        public String getDescription() {
            return description;
        }
    }
    
    /**
     * 设置文本内容
     */
    public BoundesuParagraph setText(String text) {
        this.text = text;
        return this;
    }
    
    /**
     * 设置对齐方式
     */
    public BoundesuParagraph setAlignment(TextAlignment alignment) {
        this.alignment = alignment;
        return this;
    }
    
    /**
     * 设置缩进
     */
    public BoundesuParagraph setIndentation(int indentation) {
        this.indentation = indentation;
        return this;
    }
    
    /**
     * 设置行间距
     */
    public BoundesuParagraph setLineSpacing(double lineSpacing) {
        this.lineSpacing = lineSpacing;
        return this;
    }
    
    /**
     * 设置段前间距
     */
    public BoundesuParagraph setSpaceBefore(int spaceBefore) {
        this.spaceBefore = spaceBefore;
        return this;
    }
    
    /**
     * 设置段后间距
     */
    public BoundesuParagraph setSpaceAfter(int spaceAfter) {
        this.spaceAfter = spaceAfter;
        return this;
    }
    
    /**
     * 设置分页符
     */
    public BoundesuParagraph setPageBreak(boolean pageBreak) {
        this.pageBreak = pageBreak;
        return this;
    }
    
    /**
     * 添加自定义属性
     */
    public BoundesuParagraph addCustomProperty(String key, Object value) {
        if (this.customProperties == null) {
            this.customProperties = new HashMap<>();
        }
        this.customProperties.put(key, value);
        return this;
    }
    
    /**
     * 获取字数
     */
    public int getWordCount() {
        return text != null ? text.split("\\s+").length : 0;
    }
    
    /**
     * 获取字符数
     */
    public int getCharacterCount() {
        return text != null ? text.length() : 0;
    }
    
    /**
     * 是否为空段落
     */
    public boolean isEmpty() {
        return text == null || text.trim().isEmpty();
    }
    
    /**
     * 是否为标题
     */
    public boolean isHeading() {
        return style != null && style.isHeading();
    }
    
    /**
     * 获取标题级别
     */
    public int getHeadingLevel() {
        return style != null ? style.getHeadingLevel() : 0;
    }
}