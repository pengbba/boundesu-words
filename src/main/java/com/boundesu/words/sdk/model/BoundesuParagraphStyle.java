package com.boundesu.words.sdk.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * Boundesu Words JDK17 SDK - 段落样式模型
 * 
 * @author Boundesu
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoundesuParagraphStyle {
    
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
     * 是否删除线
     */
    @Builder.Default
    private boolean strikethrough = false;
    
    /**
     * 字体颜色（十六进制）
     */
    @Builder.Default
    private String color = "#000000";
    
    /**
     * 背景颜色（十六进制）
     */
    private String backgroundColor;
    
    /**
     * 是否为标题
     */
    @Builder.Default
    private boolean isHeading = false;
    
    /**
     * 标题级别（1-6）
     */
    @Builder.Default
    private int headingLevel = 1;
    
    /**
     * 字符间距
     */
    @Builder.Default
    private double characterSpacing = 0.0;
    
    /**
     * 文本装饰
     */
    @Builder.Default
    private TextDecoration decoration = TextDecoration.NONE;
    
    /**
     * 文本大小写
     */
    @Builder.Default
    private TextCase textCase = TextCase.NORMAL;
    
    /**
     * 文本装饰枚举
     */
    public enum TextDecoration {
        NONE("无"),
        UNDERLINE("下划线"),
        OVERLINE("上划线"),
        LINE_THROUGH("删除线"),
        DOUBLE_UNDERLINE("双下划线");
        
        private final String description;
        
        TextDecoration(String description) {
            this.description = description;
        }
        
        public String getDescription() {
            return description;
        }
    }
    
    /**
     * 文本大小写枚举
     */
    public enum TextCase {
        NORMAL("正常"),
        UPPERCASE("大写"),
        LOWERCASE("小写"),
        CAPITALIZE("首字母大写"),
        SMALL_CAPS("小型大写字母");
        
        private final String description;
        
        TextCase(String description) {
            this.description = description;
        }
        
        public String getDescription() {
            return description;
        }
    }
    
    /**
     * 设置字体
     */
    public BoundesuParagraphStyle setFont(String fontFamily, int fontSize) {
        this.fontFamily = fontFamily;
        this.fontSize = fontSize;
        return this;
    }
    
    /**
     * 设置粗体
     */
    public BoundesuParagraphStyle setBold(boolean bold) {
        this.bold = bold;
        return this;
    }
    
    /**
     * 设置斜体
     */
    public BoundesuParagraphStyle setItalic(boolean italic) {
        this.italic = italic;
        return this;
    }
    
    /**
     * 设置下划线
     */
    public BoundesuParagraphStyle setUnderline(boolean underline) {
        this.underline = underline;
        return this;
    }
    
    /**
     * 设置删除线
     */
    public BoundesuParagraphStyle setStrikethrough(boolean strikethrough) {
        this.strikethrough = strikethrough;
        return this;
    }
    
    /**
     * 设置颜色
     */
    public BoundesuParagraphStyle setColor(String color) {
        this.color = color;
        return this;
    }
    
    /**
     * 设置背景颜色
     */
    public BoundesuParagraphStyle setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }
    
    /**
     * 设置为标题
     */
    public BoundesuParagraphStyle setAsHeading(int level) {
        this.isHeading = true;
        this.headingLevel = Math.max(1, Math.min(6, level));
        this.bold = true;
        this.fontSize = 24 - (level * 2);
        return this;
    }
    
    /**
     * 设置字符间距
     */
    public BoundesuParagraphStyle setCharacterSpacing(double spacing) {
        this.characterSpacing = spacing;
        return this;
    }
    
    /**
     * 设置文本装饰
     */
    public BoundesuParagraphStyle setDecoration(TextDecoration decoration) {
        this.decoration = decoration;
        return this;
    }
    
    /**
     * 设置文本大小写
     */
    public BoundesuParagraphStyle setTextCase(TextCase textCase) {
        this.textCase = textCase;
        return this;
    }
    
    /**
     * 复制样式
     */
    public BoundesuParagraphStyle copy() {
        return BoundesuParagraphStyle.builder()
                .fontFamily(this.fontFamily)
                .fontSize(this.fontSize)
                .bold(this.bold)
                .italic(this.italic)
                .underline(this.underline)
                .strikethrough(this.strikethrough)
                .color(this.color)
                .backgroundColor(this.backgroundColor)
                .isHeading(this.isHeading)
                .headingLevel(this.headingLevel)
                .characterSpacing(this.characterSpacing)
                .decoration(this.decoration)
                .textCase(this.textCase)
                .build();
    }
}