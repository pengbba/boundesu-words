package com.boundesu.words.sdk.advanced;

import com.boundesu.words.sdk.creator.DocumentCreator;
import com.boundesu.words.sdk.creator.DocumentCreatorFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 高级文档生成器
 * 提供自动生成页面、目录等高级功能
 *
 * @author Boundesu Team
 * @version 1.0.0
 */
public class AdvancedDocumentGenerator {

    private final DocumentCreator creator;
    private final List<TocEntry> tocEntries;
    private final DocumentConfig config;

    /**
     * 目录条目
     */
    public static class TocEntry {
        private final String title;
        private final int level;
        private final int pageNumber;

        public TocEntry(String title, int level, int pageNumber) {
            this.title = title;
            this.level = level;
            this.pageNumber = pageNumber;
        }

        public String getTitle() {
            return title;
        }

        public int getLevel() {
            return level;
        }

        public int getPageNumber() {
            return pageNumber;
        }
    }

    /**
     * 文档配置
     */
    public static class DocumentConfig {
        private boolean generateToc = true;
        private boolean generateCoverPage = true;
        private boolean generateFooter = true;
        private boolean generateHeader = true;
        private String tocTitle = "目录";
        private String dateFormat = "yyyy年MM月dd日";
        private String company = "Boundesu";
        private String department = "";

        // Getters and Setters
        public boolean isGenerateToc() {
            return generateToc;
        }

        public DocumentConfig setGenerateToc(boolean generateToc) {
            this.generateToc = generateToc;
            return this;
        }

        public boolean isGenerateCoverPage() {
            return generateCoverPage;
        }

        public DocumentConfig setGenerateCoverPage(boolean generateCoverPage) {
            this.generateCoverPage = generateCoverPage;
            return this;
        }

        public boolean isGenerateFooter() {
            return generateFooter;
        }

        public DocumentConfig setGenerateFooter(boolean generateFooter) {
            this.generateFooter = generateFooter;
            return this;
        }

        public boolean isGenerateHeader() {
            return generateHeader;
        }

        public DocumentConfig setGenerateHeader(boolean generateHeader) {
            this.generateHeader = generateHeader;
            return this;
        }

        public String getTocTitle() {
            return tocTitle;
        }

        public DocumentConfig setTocTitle(String tocTitle) {
            this.tocTitle = tocTitle;
            return this;
        }

        public String getDateFormat() {
            return dateFormat;
        }

        public DocumentConfig setDateFormat(String dateFormat) {
            this.dateFormat = dateFormat;
            return this;
        }

        public String getCompany() {
            return company;
        }

        public DocumentConfig setCompany(String company) {
            this.company = company;
            return this;
        }

        public String getDepartment() {
            return department;
        }

        public DocumentConfig setDepartment(String department) {
            this.department = department;
            return this;
        }
    }

    /**
     * 构造函数
     *
     * @param creatorType 创建器类型
     */
    public AdvancedDocumentGenerator(DocumentCreatorFactory.CreatorType creatorType) {
        this.creator = DocumentCreatorFactory.createDocumentCreator(creatorType);
        this.tocEntries = new ArrayList<>();
        this.config = new DocumentConfig();
    }

    /**
     * 构造函数（默认使用POI创建器）
     */
    public AdvancedDocumentGenerator() {
        this(DocumentCreatorFactory.CreatorType.DIRECT_POI);
    }

    /**
     * 获取文档配置
     *
     * @return 文档配置
     */
    public DocumentConfig getConfig() {
        return config;
    }

    /**
     * 设置文档标题
     *
     * @param title 文档标题
     * @return 当前生成器实例
     */
    public AdvancedDocumentGenerator setTitle(String title) {
        creator.setTitle(title);
        return this;
    }

    /**
     * 设置文档作者
     *
     * @param author 文档作者
     * @return 当前生成器实例
     */
    public AdvancedDocumentGenerator setAuthor(String author) {
        creator.setAuthor(author);
        return this;
    }

    /**
     * 自动生成封面页
     *
     * @param title    文档标题
     * @param subtitle 副标题
     * @param author   作者
     * @return 当前生成器实例
     */
    public AdvancedDocumentGenerator generateCoverPage(String title, String subtitle, String author) {
        if (!config.isGenerateCoverPage()) {
            return this;
        }

        // 添加封面标题
        creator.addHeading(title, 1);
        creator.addParagraph(""); // 空行

        if (subtitle != null && !subtitle.trim().isEmpty()) {
            creator.addHeading(subtitle, 2);
            creator.addParagraph(""); // 空行
        }

        // 添加作者信息
        creator.addParagraph("作者：" + author);
        creator.addParagraph("公司：" + config.getCompany());

        if (!config.getDepartment().isEmpty()) {
            creator.addParagraph("部门：" + config.getDepartment());
        }

        // 添加日期
        String currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern(config.getDateFormat()));
        creator.addParagraph("日期：" + currentDate);

        // 添加分页符（通过空段落模拟）
        creator.addParagraph("");
        creator.addParagraph("");
        creator.addParagraph("────────────────────────────────────");
        creator.addParagraph("");

        return this;
    }

    /**
     * 自动生成目录
     *
     * @return 当前生成器实例
     */
    public AdvancedDocumentGenerator generateTableOfContents() {
        if (!config.isGenerateToc() || tocEntries.isEmpty()) {
            return this;
        }

        creator.addHeading(config.getTocTitle(), 1);
        creator.addParagraph("");

        for (TocEntry entry : tocEntries) {
            StringBuilder indentBuilder = new StringBuilder();
            int indentLevel = Math.max(0, entry.getLevel() - 1);
            for (int i = 0; i < indentLevel; i++) {
                indentBuilder.append("  ");
            }
            String indent = indentBuilder.toString();
            String tocLine = String.format("%s%s ........................ %d",
                    indent, entry.getTitle(), entry.getPageNumber());
            creator.addParagraph(tocLine);
        }

        creator.addParagraph("");
        creator.addParagraph("────────────────────────────────────");
        creator.addParagraph("");

        return this;
    }

    /**
     * 添加章节（自动添加到目录）
     *
     * @param title   章节标题
     * @param level   章节级别
     * @param content 章节内容
     * @return 当前生成器实例
     */
    public AdvancedDocumentGenerator addChapter(String title, int level, String content) {
        // 添加到目录
        int pageNumber = tocEntries.size() + 1; // 简化的页码计算
        tocEntries.add(new TocEntry(title, level, pageNumber));

        // 添加章节内容
        creator.addHeading(title, level);
        if (content != null && !content.trim().isEmpty()) {
            creator.addParagraph(content);
        }
        creator.addParagraph(""); // 章节间空行

        return this;
    }

    /**
     * 添加多个章节
     *
     * @param chapters 章节列表（标题-内容对）
     * @return 当前生成器实例
     */
    public AdvancedDocumentGenerator addChapters(Map<String, String> chapters) {
        for (Map.Entry<String, String> chapter : chapters.entrySet()) {
            addChapter(chapter.getKey(), 1, chapter.getValue());
        }
        return this;
    }

    /**
     * 快速生成报告文档
     *
     * @param title    报告标题
     * @param author   作者
     * @param summary  摘要
     * @param chapters 章节内容
     * @return 当前生成器实例
     */
    public AdvancedDocumentGenerator generateReport(String title, String author, String summary, Map<String, String> chapters) {
        setTitle(title).setAuthor(author);

        // 生成封面
        generateCoverPage(title, "技术报告", author);

        // 添加摘要
        if (summary != null && !summary.trim().isEmpty()) {
            addChapter("摘要", 1, summary);
        }

        // 添加章节
        addChapters(chapters);

        // 生成目录（在内容后面，实际应用中可能需要调整位置）
        generateTableOfContents();

        return this;
    }

    /**
     * 快速生成技术文档
     *
     * @param title        文档标题
     * @param author       作者
     * @param introduction 介绍
     * @param sections     技术章节
     * @param conclusion   结论
     * @return 当前生成器实例
     */
    public AdvancedDocumentGenerator generateTechnicalDocument(String title, String author,
                                                               String introduction, Map<String, String> sections, String conclusion) {
        setTitle(title).setAuthor(author);

        // 生成封面
        generateCoverPage(title, "技术文档", author);

        // 添加介绍
        if (introduction != null && !introduction.trim().isEmpty()) {
            addChapter("1. 介绍", 1, introduction);
        }

        // 添加技术章节
        int chapterNum = 2;
        for (Map.Entry<String, String> section : sections.entrySet()) {
            addChapter(chapterNum + ". " + section.getKey(), 1, section.getValue());
            chapterNum++;
        }

        // 添加结论
        if (conclusion != null && !conclusion.trim().isEmpty()) {
            addChapter(chapterNum + ". 结论", 1, conclusion);
        }

        // 生成目录
        generateTableOfContents();

        return this;
    }

    /**
     * 生成会议纪要
     *
     * @param meetingTitle 会议标题
     * @param date         会议日期
     * @param attendees    参会人员
     * @param agenda       议程
     * @param decisions    决议
     * @param actionItems  行动项
     * @return 当前生成器实例
     */
    public AdvancedDocumentGenerator generateMeetingMinutes(String meetingTitle, String date,
                                                            List<String> attendees, List<String> agenda, List<String> decisions, List<String> actionItems) {
        setTitle(meetingTitle + " - 会议纪要").setAuthor("会议秘书");

        // 生成封面
        generateCoverPage(meetingTitle, "会议纪要", "会议秘书");

        // 会议基本信息
        addChapter("会议信息", 1, "");
        creator.addParagraph("会议时间：" + date);
        creator.addParagraph("参会人员：" + String.join("、", attendees));
        creator.addParagraph("");

        // 议程
        if (agenda != null && !agenda.isEmpty()) {
            addChapter("会议议程", 1, "");
            for (int i = 0; i < agenda.size(); i++) {
                creator.addParagraph((i + 1) + ". " + agenda.get(i));
            }
            creator.addParagraph("");
        }

        // 决议
        if (decisions != null && !decisions.isEmpty()) {
            addChapter("会议决议", 1, "");
            for (int i = 0; i < decisions.size(); i++) {
                creator.addParagraph((i + 1) + ". " + decisions.get(i));
            }
            creator.addParagraph("");
        }

        // 行动项
        if (actionItems != null && !actionItems.isEmpty()) {
            addChapter("行动项", 1, "");
            for (int i = 0; i < actionItems.size(); i++) {
                creator.addParagraph((i + 1) + ". " + actionItems.get(i));
            }
            creator.addParagraph("");
        }

        // 生成目录
        generateTableOfContents();

        return this;
    }

    /**
     * 添加页脚信息
     *
     * @return 当前生成器实例
     */
    public AdvancedDocumentGenerator addFooter() {
        if (!config.isGenerateFooter()) {
            return this;
        }

        creator.addParagraph("");
        creator.addParagraph("────────────────────────────────────");
        creator.addParagraph("© " + LocalDateTime.now().getYear() + " " + config.getCompany() + ". 保留所有权利。");
        creator.addParagraph("生成时间：" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        return this;
    }

    /**
     * 创建文档
     *
     * @param outputPath 输出路径
     * @throws IOException 文件操作异常
     */
    public void createDocument(Path outputPath) throws IOException {
        // 添加页脚
        addFooter();

        // 创建文档
        creator.createDocument(outputPath);
    }

    /**
     * 创建文档并返回字节数组
     *
     * @return 文档字节数组
     * @throws IOException 文档创建异常
     */
    public byte[] createDocumentAsBytes() throws IOException {
        // 添加页脚
        addFooter();

        // 创建文档
        return creator.createDocumentAsBytes();
    }

    /**
     * 获取目录条目列表
     *
     * @return 目录条目列表
     */
    public List<TocEntry> getTocEntries() {
        return new ArrayList<>(tocEntries);
    }

    /**
     * 清空目录
     *
     * @return 当前生成器实例
     */
    public AdvancedDocumentGenerator clearToc() {
        tocEntries.clear();
        return this;
    }
}