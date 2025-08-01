package com.boundesu.words.sdk.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * Boundesu Words JDK17 SDK - 表格样式模型
 * 
 * @author Boundesu
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoundesuTableStyle {
    
    /**
     * 表格主题
     */
    @Builder.Default
    private TableTheme theme = TableTheme.DEFAULT;
    
    /**
     * 表格背景颜色
     */
    private String backgroundColor;
    
    /**
     * 表头背景颜色
     */
    @Builder.Default
    private String headerBackgroundColor = "#f0f0f0";
    
    /**
     * 表头文字颜色
     */
    @Builder.Default
    private String headerTextColor = "#000000";
    
    /**
     * 奇数行背景颜色
     */
    private String oddRowBackgroundColor;
    
    /**
     * 偶数行背景颜色
     */
    private String evenRowBackgroundColor;
    
    /**
     * 是否显示斑马纹
     */
    @Builder.Default
    private boolean striped = false;
    
    /**
     * 是否悬停效果
     */
    @Builder.Default
    private boolean hover = false;
    
    /**
     * 悬停背景颜色
     */
    @Builder.Default
    private String hoverBackgroundColor = "#f5f5f5";
    
    /**
     * 表格主题枚举
     */
    public enum TableTheme {
        DEFAULT("默认"),
        LIGHT("浅色"),
        DARK("深色"),
        BLUE("蓝色"),
        GREEN("绿色"),
        RED("红色"),
        PROFESSIONAL("专业"),
        MODERN("现代"),
        CLASSIC("经典");
        
        private final String description;
        
        TableTheme(String description) {
            this.description = description;
        }
        
        public String getDescription() {
            return description;
        }
    }
    
    /**
     * 应用主题
     */
    public BoundesuTableStyle applyTheme(TableTheme theme) {
        this.theme = theme;
        
        switch (theme) {
            case LIGHT:
                this.backgroundColor = "#ffffff";
                this.headerBackgroundColor = "#f8f9fa";
                this.headerTextColor = "#495057";
                this.oddRowBackgroundColor = "#ffffff";
                this.evenRowBackgroundColor = "#f8f9fa";
                break;
                
            case DARK:
                this.backgroundColor = "#343a40";
                this.headerBackgroundColor = "#495057";
                this.headerTextColor = "#ffffff";
                this.oddRowBackgroundColor = "#343a40";
                this.evenRowBackgroundColor = "#495057";
                break;
                
            case BLUE:
                this.backgroundColor = "#ffffff";
                this.headerBackgroundColor = "#007bff";
                this.headerTextColor = "#ffffff";
                this.oddRowBackgroundColor = "#ffffff";
                this.evenRowBackgroundColor = "#e3f2fd";
                break;
                
            case GREEN:
                this.backgroundColor = "#ffffff";
                this.headerBackgroundColor = "#28a745";
                this.headerTextColor = "#ffffff";
                this.oddRowBackgroundColor = "#ffffff";
                this.evenRowBackgroundColor = "#e8f5e8";
                break;
                
            case RED:
                this.backgroundColor = "#ffffff";
                this.headerBackgroundColor = "#dc3545";
                this.headerTextColor = "#ffffff";
                this.oddRowBackgroundColor = "#ffffff";
                this.evenRowBackgroundColor = "#ffeaea";
                break;
                
            case PROFESSIONAL:
                this.backgroundColor = "#ffffff";
                this.headerBackgroundColor = "#6c757d";
                this.headerTextColor = "#ffffff";
                this.oddRowBackgroundColor = "#ffffff";
                this.evenRowBackgroundColor = "#f1f3f4";
                break;
                
            case MODERN:
                this.backgroundColor = "#ffffff";
                this.headerBackgroundColor = "#17a2b8";
                this.headerTextColor = "#ffffff";
                this.oddRowBackgroundColor = "#ffffff";
                this.evenRowBackgroundColor = "#e1f7fa";
                break;
                
            case CLASSIC:
                this.backgroundColor = "#ffffff";
                this.headerBackgroundColor = "#6f42c1";
                this.headerTextColor = "#ffffff";
                this.oddRowBackgroundColor = "#ffffff";
                this.evenRowBackgroundColor = "#f3f0ff";
                break;
                
            default:
                this.backgroundColor = "#ffffff";
                this.headerBackgroundColor = "#f0f0f0";
                this.headerTextColor = "#000000";
                this.oddRowBackgroundColor = "#ffffff";
                this.evenRowBackgroundColor = "#f9f9f9";
                break;
        }
        
        return this;
    }
    
    /**
     * 设置斑马纹
     */
    public BoundesuTableStyle setStriped(boolean striped) {
        this.striped = striped;
        if (striped && oddRowBackgroundColor == null) {
            this.oddRowBackgroundColor = "#ffffff";
            this.evenRowBackgroundColor = "#f9f9f9";
        }
        return this;
    }
    
    /**
     * 设置悬停效果
     */
    public BoundesuTableStyle setHover(boolean hover) {
        this.hover = hover;
        return this;
    }
    
    /**
     * 设置背景颜色
     */
    public BoundesuTableStyle setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }
    
    /**
     * 设置表头样式
     */
    public BoundesuTableStyle setHeaderStyle(String backgroundColor, String textColor) {
        this.headerBackgroundColor = backgroundColor;
        this.headerTextColor = textColor;
        return this;
    }
    
    /**
     * 设置行颜色
     */
    public BoundesuTableStyle setRowColors(String oddRowColor, String evenRowColor) {
        this.oddRowBackgroundColor = oddRowColor;
        this.evenRowBackgroundColor = evenRowColor;
        this.striped = true;
        return this;
    }
    
    /**
     * 设置悬停颜色
     */
    public BoundesuTableStyle setHoverColor(String hoverBackgroundColor) {
        this.hoverBackgroundColor = hoverBackgroundColor;
        this.hover = true;
        return this;
    }
}