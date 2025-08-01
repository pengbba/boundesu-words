package com.boundesu.words.sdk.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Boundesu Words JDK17 SDK - 核心文档模型
 * 
 * @author Boundesu
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoundesuDocument {
    
    /**
     * 文档唯一标识
     */
    private String documentId;
    
    /**
     * 文档标题
     */
    private String title;
    
    /**
     * 文档作者
     */
    private String author;
    
    /**
     * 文档主题
     */
    private String subject;
    
    /**
     * 文档关键词
     */
    private List<String> keywords;
    
    /**
     * 文档描述
     */
    private String description;
    
    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
    
    /**
     * 最后修改时间
     */
    private LocalDateTime lastModified;
    
    /**
     * 文档版本
     */
    private String version;
    
    /**
     * 文档语言
     */
    private String language;
    
    /**
     * 文档状态
     */
    private DocumentStatus status;
    
    /**
     * 文档段落列表
     */
    @Builder.Default
    private List<BoundesuParagraph> paragraphs = new ArrayList<>();
    
    /**
     * 文档表格列表
     */
    @Builder.Default
    private List<BoundesuTable> tables = new ArrayList<>();
    
    /**
     * 文档图片列表
     */
    @Builder.Default
    private List<BoundesuImage> images = new ArrayList<>();
    
    /**
     * 文档页眉
     */
    private BoundesuHeader header;
    
    /**
     * 文档页脚
     */
    private BoundesuFooter footer;
    
    /**
     * 文档样式设置
     */
    @Builder.Default
    private BoundesuDocumentStyle style = new BoundesuDocumentStyle();
    
    /**
     * 自定义属性
     */
    @Builder.Default
    private Map<String, Object> customProperties = new HashMap<>();
    
    /**
     * 文档状态枚举
     */
    public enum DocumentStatus {
        DRAFT("草稿"),
        REVIEW("审核中"),
        APPROVED("已批准"),
        PUBLISHED("已发布"),
        ARCHIVED("已归档");
        
        private final String description;
        
        DocumentStatus(String description) {
            this.description = description;
        }
        
        public String getDescription() {
            return description;
        }
    }
    
    /**
     * 添加段落
     */
    public BoundesuDocument addParagraph(BoundesuParagraph paragraph) {
        if (this.paragraphs == null) {
            this.paragraphs = new ArrayList<>();
        }
        this.paragraphs.add(paragraph);
        return this;
    }
    
    /**
     * 添加文本段落
     */
    public BoundesuDocument addParagraph(String text) {
        BoundesuParagraph paragraph = BoundesuParagraph.builder()
                .text(text)
                .build();
        return addParagraph(paragraph);
    }
    
    /**
     * 添加标题段落
     */
    public BoundesuDocument addHeading(String text, int level) {
        BoundesuParagraph paragraph = BoundesuParagraph.builder()
                .text(text)
                .style(BoundesuParagraphStyle.builder()
                        .isHeading(true)
                        .headingLevel(level)
                        .fontSize(24 - (level * 2))
                        .bold(true)
                        .build())
                .build();
        return addParagraph(paragraph);
    }
    
    /**
     * 添加表格
     */
    public BoundesuDocument addTable(BoundesuTable table) {
        if (this.tables == null) {
            this.tables = new ArrayList<>();
        }
        this.tables.add(table);
        return this;
    }
    
    /**
     * 添加图片
     */
    public BoundesuDocument addImage(BoundesuImage image) {
        if (this.images == null) {
            this.images = new ArrayList<>();
        }
        this.images.add(image);
        return this;
    }
    
    /**
     * 设置页眉
     */
    public BoundesuDocument setHeader(BoundesuHeader boundesuHeader) {
        this.header =boundesuHeader;
        return this;
    }
    
    /**
     * 设置页脚
     */
    public BoundesuDocument setFooter(BoundesuFooter boundesuFooter) {
        this.footer = boundesuFooter;
        return this;
    }
    
    /**
     * 获取文档总字数
     */
    public int getWordCount() {
        return paragraphs.stream()
                .mapToInt(p -> p.getText() != null ? p.getText().split("\\s+").length : 0)
                .sum();
    }
    
    /**
     * 获取文档总字符数
     */
    public int getCharacterCount() {
        return paragraphs.stream()
                .mapToInt(p -> p.getText() != null ? p.getText().length() : 0)
                .sum();
    }
    
    /**
     * 获取段落数量
     */
    public int getParagraphCount() {
        return paragraphs != null ? paragraphs.size() : 0;
    }
    
    /**
     * 获取表格数量
     */
    public int getTableCount() {
        return tables != null ? tables.size() : 0;
    }
    
    /**
     * 获取图片数量
     */
    public int getImageCount() {
        return images != null ? images.size() : 0;
    }
    
    /**
     * 添加自定义属性
     */
    public BoundesuDocument addCustomProperty(String key, Object value) {
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
    public BoundesuDocument updateLastModified() {
        this.lastModified = LocalDateTime.now();
        return this;
    }
}