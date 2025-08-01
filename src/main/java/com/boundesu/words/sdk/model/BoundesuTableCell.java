package com.boundesu.words.sdk.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Map;
import java.util.HashMap;

/**
 * Boundesu Words JDK17 SDK - 表格单元格模型
 * 
 * @author Boundesu
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoundesuTableCell {
    
    /**
     * 单元格唯一标识
     */
    private String cellId;
    
    /**
     * 行索引
     */
    private int rowIndex;
    
    /**
     * 列索引
     */
    private int columnIndex;
    
    /**
     * 单元格文本内容
     */
    private String text;
    
    /**
     * 单元格样式
     */
    @Builder.Default
    private BoundesuTableCellStyle style = new BoundesuTableCellStyle();
    
    /**
     * 行跨度
     */
    @Builder.Default
    private int rowSpan = 1;
    
    /**
     * 列跨度
     */
    @Builder.Default
    private int columnSpan = 1;
    
    /**
     * 是否为表头单元格
     */
    @Builder.Default
    private boolean isHeader = false;
    
    /**
     * 是否被合并
     */
    @Builder.Default
    private boolean merged = false;
    
    /**
     * 单元格宽度
     */
    private String width;
    
    /**
     * 单元格高度
     */
    private String height;
    
    /**
     * 垂直对齐方式
     */
    @Builder.Default
    private VerticalAlignment verticalAlignment = VerticalAlignment.MIDDLE;
    
    /**
     * 水平对齐方式
     */
    @Builder.Default
    private HorizontalAlignment horizontalAlignment = HorizontalAlignment.LEFT;
    
    /**
     * 自定义属性
     */
    @Builder.Default
    private Map<String, Object> customProperties = new HashMap<>();
    
    /**
     * 垂直对齐方式枚举
     */
    public enum VerticalAlignment {
        TOP("顶部对齐"),
        MIDDLE("中间对齐"),
        BOTTOM("底部对齐");
        
        private final String description;
        
        VerticalAlignment(String description) {
            this.description = description;
        }
        
        public String getDescription() {
            return description;
        }
    }
    
    /**
     * 水平对齐方式枚举
     */
    public enum HorizontalAlignment {
        LEFT("左对齐"),
        CENTER("居中对齐"),
        RIGHT("右对齐"),
        JUSTIFY("两端对齐");
        
        private final String description;
        
        HorizontalAlignment(String description) {
            this.description = description;
        }
        
        public String getDescription() {
            return description;
        }
    }
    
    /**
     * 设置文本内容
     */
    public BoundesuTableCell setText(String text) {
        this.text = text;
        return this;
    }
    
    /**
     * 设置样式
     */
    public BoundesuTableCell setStyle(BoundesuTableCellStyle style) {
        this.style = style;
        return this;
    }
    
    /**
     * 设置跨度
     */
    public BoundesuTableCell setSpan(int rowSpan, int columnSpan) {
        this.rowSpan = Math.max(1, rowSpan);
        this.columnSpan = Math.max(1, columnSpan);
        return this;
    }
    
    /**
     * 设置为表头
     */
    public BoundesuTableCell setAsHeader() {
        this.isHeader = true;
        if (this.style != null) {
            this.style.setBold(true);
        }
        return this;
    }
    
    /**
     * 设置尺寸
     */
    public BoundesuTableCell setSize(String width, String height) {
        this.width = width;
        this.height = height;
        return this;
    }
    
    /**
     * 设置对齐方式
     */
    public BoundesuTableCell setAlignment(HorizontalAlignment horizontal, VerticalAlignment vertical) {
        this.horizontalAlignment = horizontal;
        this.verticalAlignment = vertical;
        return this;
    }
    
    /**
     * 添加自定义属性
     */
    public BoundesuTableCell addCustomProperty(String key, Object value) {
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
     * 是否为空单元格
     */
    public boolean isEmpty() {
        return text == null || text.trim().isEmpty();
    }
    
    /**
     * 是否跨行
     */
    public boolean isRowSpanned() {
        return rowSpan > 1;
    }
    
    /**
     * 是否跨列
     */
    public boolean isColumnSpanned() {
        return columnSpan > 1;
    }
    
    /**
     * 是否跨单元格
     */
    public boolean isSpanned() {
        return isRowSpanned() || isColumnSpanned();
    }
}