package com.boundesu.words.sdk.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Boundesu Words JDK17 SDK - 图片模型
 * 
 * @author Boundesu
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoundesuImage {
    
    /**
     * 图片唯一标识
     */
    private String imageId;
    
    /**
     * 图片标题
     */
    private String title;
    
    /**
     * 图片描述
     */
    private String description;
    
    /**
     * 图片文件路径或URL
     */
    private String source;
    
    /**
     * 图片宽度（像素）
     */
    private Integer width;
    
    /**
     * 图片高度（像素）
     */
    private Integer height;
    
    /**
     * 图片格式（如：PNG, JPG, GIF, SVG等）
     */
    private String format;
    
    /**
     * 图片文件大小（字节）
     */
    private Long fileSize;
    
    /**
     * 图片对齐方式
     */
    @Builder.Default
    private ImageAlignment alignment = ImageAlignment.LEFT;
    
    /**
     * 图片边距设置
     */
    @Builder.Default
    private ImageMargins margins = new ImageMargins();
    
    /**
     * 图片边框设置
     */
    private ImageBorder border;
    
    /**
     * 图片透明度（0.0-1.0）
     */
    @Builder.Default
    private Double opacity = 1.0;
    
    /**
     * 图片旋转角度（度）
     */
    @Builder.Default
    private Integer rotation = 0;
    
    /**
     * 是否保持宽高比
     */
    @Builder.Default
    private Boolean maintainAspectRatio = true;
    
    /**
     * 图片替代文本（用于无障碍访问）
     */
    private String altText;
    
    /**
     * 图片标题文本（鼠标悬停显示）
     */
    private String titleText;
    
    /**
     * 创建时间
     */
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
    
    /**
     * 最后修改时间
     */
    @Builder.Default
    private LocalDateTime lastModified = LocalDateTime.now();
    
    /**
     * 自定义属性
     */
    @Builder.Default
    private Map<String, Object> customProperties = new HashMap<>();
    
    /**
     * 图片对齐方式枚举
     */
    public enum ImageAlignment {
        LEFT("左对齐"),
        CENTER("居中对齐"),
        RIGHT("右对齐"),
        JUSTIFY("两端对齐");
        
        private final String description;
        
        ImageAlignment(String description) {
            this.description = description;
        }
        
        public String getDescription() {
            return description;
        }
    }
    
    /**
     * 图片边距设置
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ImageMargins {
        @Builder.Default
        private Integer top = 0;
        @Builder.Default
        private Integer right = 0;
        @Builder.Default
        private Integer bottom = 0;
        @Builder.Default
        private Integer left = 0;
        
        public ImageMargins(int all) {
            this.top = this.right = this.bottom = this.left = all;
        }
        
        public ImageMargins(int vertical, int horizontal) {
            this.top = this.bottom = vertical;
            this.left = this.right = horizontal;
        }
    }
    
    /**
     * 图片边框设置
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ImageBorder {
        @Builder.Default
        private Integer width = 1;
        @Builder.Default
        private String color = "#000000";
        @Builder.Default
        private BorderStyle style = BorderStyle.SOLID;
        @Builder.Default
        private Integer radius = 0;
        
        public enum BorderStyle {
            SOLID("实线"),
            DASHED("虚线"),
            DOTTED("点线"),
            DOUBLE("双线"),
            NONE("无边框");
            
            private final String description;
            
            BorderStyle(String description) {
                this.description = description;
            }
            
            public String getDescription() {
                return description;
            }
        }
    }
    
    /**
     * 设置图片尺寸
     */
    public BoundesuImage setSize(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }
    
    /**
     * 设置图片对齐方式
     */
    public BoundesuImage setAlignment(ImageAlignment alignment) {
        this.alignment = alignment;
        return this;
    }
    
    /**
     * 设置图片边距
     */
    public BoundesuImage setMargins(int top, int right, int bottom, int left) {
        this.margins = new ImageMargins(top, right, bottom, left);
        return this;
    }
    
    /**
     * 设置图片边距（统一值）
     */
    public BoundesuImage setMargins(int all) {
        this.margins = new ImageMargins(all);
        return this;
    }
    
    /**
     * 设置图片边框
     */
    public BoundesuImage setBorder(int width, String color, ImageBorder.BorderStyle style) {
        this.border = ImageBorder.builder()
                .width(width)
                .color(color)
                .style(style)
                .build();
        return this;
    }
    
    /**
     * 设置图片透明度
     */
    public BoundesuImage setOpacity(double opacity) {
        this.opacity = Math.max(0.0, Math.min(1.0, opacity));
        return this;
    }
    
    /**
     * 设置图片旋转角度
     */
    public BoundesuImage setRotation(int rotation) {
        this.rotation = rotation % 360;
        return this;
    }
    
    /**
     * 获取图片宽高比
     */
    public Double getAspectRatio() {
        if (width != null && height != null && height != 0) {
            return (double) width / height;
        }
        return null;
    }
    
    /**
     * 根据宽度计算高度（保持宽高比）
     */
    public Integer calculateHeightFromWidth(int newWidth) {
        Double aspectRatio = getAspectRatio();
        if (aspectRatio != null) {
            return (int) (newWidth / aspectRatio);
        }
        return null;
    }
    
    /**
     * 根据高度计算宽度（保持宽高比）
     */
    public Integer calculateWidthFromHeight(int newHeight) {
        Double aspectRatio = getAspectRatio();
        if (aspectRatio != null) {
            return (int) (newHeight * aspectRatio);
        }
        return null;
    }
    
    /**
     * 添加自定义属性
     */
    public BoundesuImage addCustomProperty(String key, Object value) {
        if (this.customProperties == null) {
            this.customProperties = new HashMap<>();
        }
        this.customProperties.put(key, value);
        return this;
    }
    
    /**
     * 获取自定义属性
     */
    public Object getCustomProperty(String key) {
        return this.customProperties != null ? this.customProperties.get(key) : null;
    }
    
    /**
     * 更新最后修改时间
     */
    public BoundesuImage updateLastModified() {
        this.lastModified = LocalDateTime.now();
        return this;
    }
    
    /**
     * 检查图片是否有效
     */
    public boolean isValid() {
        return source != null && !source.trim().isEmpty();
    }
    
    /**
     * 获取图片信息摘要
     */
    public String getSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("图片: ").append(title != null ? title : "未命名");
        if (width != null && height != null) {
            summary.append(" (").append(width).append("x").append(height).append(")");
        }
        if (format != null) {
            summary.append(" [").append(format.toUpperCase()).append("]");
        }
        return summary.toString();
    }
}