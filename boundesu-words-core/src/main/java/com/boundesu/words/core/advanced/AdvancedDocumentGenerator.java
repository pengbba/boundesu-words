package com.boundesu.words.core.advanced;

import com.boundesu.words.common.creator.DocumentCreator;
import com.boundesu.words.core.creator.DocumentCreatorFactory;

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
     * 默认构造函数，使用POI直接创建器
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
     * 生成封面页
     *
     * @param title    文档标题
     * @param subtitle 副标题
     * @param author   作者
     * @return 当前生成器实例
     */
    public AdvancedDocumentGenerator generateCoverPage(String title, String subtitle, String author) {
        if (config.isGenerateCoverPage()) {
            // 添加标题
            creator.addHeading(title, 1);

            // 添加副标题
            if (subtitle != null && !subtitle.trim().isEmpty()) {
                creator.addHeading(subtitle, 2);
            }

            // 添加作者信息
            if (author != null && !author.trim().isEmpty()) {
                creator.addParagraph("作者: " + author);
            }

            // 添加公司信息
            if (!config.getCompany().isEmpty()) {
                creator.addParagraph("公司: " + config.getCompany());
            }

            // 添加部门信息
            if (!config.getDepartment().isEmpty()) {
                creator.addParagraph("部门: " + config.getDepartment());
            }

            // 添加日期
            String currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern(config.getDateFormat()));
            creator.addParagraph("日期: " + currentDate);

            // 添加分页符
            creator.addParagraph("\n\n");
        }
        return this;
    }

    /**
     * 生成目录
     *
     * @return 当前生成器实例
     */
    public AdvancedDocumentGenerator generateTableOfContents() {
        if (config.isGenerateToc() && !tocEntries.isEmpty()) {
            creator.addHeading(config.getTocTitle(), 1);

            for (TocEntry entry : tocEntries) {
                StringBuilder tocLine = new StringBuilder();

                // 添加缩进
                for (int i = 1; i < entry.getLevel(); i++) {
                    tocLine.append("    ");
                }

                tocLine.append(entry.getTitle());
                tocLine.append(" .................. ");
                tocLine.append(entry.getPageNumber());

                creator.addParagraph(tocLine.toString());
            }

            // 添加分页符
            creator.addParagraph("\n\n");
        }
        return this;
    }

    /**
     * 添加章节
     *
     * @param title   章节标题
     * @param level   标题级别
     * @param content 章节内容
     * @return 当前生成器实例
     */
    public AdvancedDocumentGenerator addChapter(String title, int level, String content) {
        // 添加到目录
        if (config.isGenerateToc()) {
            tocEntries.add(new TocEntry(title, level, tocEntries.size() + 1));
        }

        // 添加标题和内容
        creator.addHeading(title, level);
        if (content != null && !content.trim().isEmpty()) {
            creator.addParagraph(content);
        }

        return this;
    }

    /**
     * 批量添加章节
     *
     * @param chapters 章节映射（标题 -> 内容）
     * @return 当前生成器实例
     */
    public AdvancedDocumentGenerator addChapters(Map<String, String> chapters) {
        for (Map.Entry<String, String> entry : chapters.entrySet()) {
            addChapter(entry.getKey(), 2, entry.getValue());
        }
        return this;
    }

    /**
     * 生成报告文档
     *
     * @param title    报告标题
     * @param author   报告作者
     * @param summary  摘要
     * @param chapters 章节内容
     * @return 当前生成器实例
     */
    public AdvancedDocumentGenerator generateReport(String title, String author, String summary, Map<String, String> chapters) {
        // 生成封面
        generateCoverPage(title, "报告", author);

        // 添加摘要
        if (summary != null && !summary.trim().isEmpty()) {
            addChapter("摘要", 1, summary);
        }

        // 生成目录
        generateTableOfContents();

        // 添加章节
        addChapters(chapters);

        // 添加页脚
        addFooter();

        return this;
    }

    /**
     * 生成技术文档
     *
     * @param title        文档标题
     * @param author       文档作者
     * @param introduction 介绍
     * @param sections     章节内容
     * @param conclusion   结论
     * @return 当前生成器实例
     */
    public AdvancedDocumentGenerator generateTechnicalDocument(String title, String author,
                                                               String introduction, Map<String, String> sections, String conclusion) {
        // 生成封面
        generateCoverPage(title, "技术文档", author);

        // 添加介绍
        if (introduction != null && !introduction.trim().isEmpty()) {
            addChapter("介绍", 1, introduction);
        }

        // 生成目录
        generateTableOfContents();

        // 添加技术章节
        addChapters(sections);

        // 添加结论
        if (conclusion != null && !conclusion.trim().isEmpty()) {
            addChapter("结论", 1, conclusion);
        }

        // 添加页脚
        addFooter();

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
        // 生成封面
        generateCoverPage(meetingTitle, "会议纪要", "");

        // 添加会议信息
        addChapter("会议信息", 1, "日期: " + date);

        // 添加参会人员
        if (attendees != null && !attendees.isEmpty()) {
            StringBuilder attendeesList = new StringBuilder();
            for (String attendee : attendees) {
                attendeesList.append("• ").append(attendee).append("\n");
            }
            addChapter("参会人员", 2, attendeesList.toString());
        }

        // 添加议程
        if (agenda != null && !agenda.isEmpty()) {
            StringBuilder agendaList = new StringBuilder();
            for (int i = 0; i < agenda.size(); i++) {
                agendaList.append((i + 1)).append(". ").append(agenda.get(i)).append("\n");
            }
            addChapter("会议议程", 2, agendaList.toString());
        }

        // 添加决议
        if (decisions != null && !decisions.isEmpty()) {
            StringBuilder decisionsList = new StringBuilder();
            for (String decision : decisions) {
                decisionsList.append("• ").append(decision).append("\n");
            }
            addChapter("会议决议", 2, decisionsList.toString());
        }

        // 添加行动项
        if (actionItems != null && !actionItems.isEmpty()) {
            StringBuilder actionList = new StringBuilder();
            for (String action : actionItems) {
                actionList.append("• ").append(action).append("\n");
            }
            addChapter("行动项", 2, actionList.toString());
        }

        // 添加页脚
        addFooter();

        return this;
    }

    /**
     * 添加页脚
     *
     * @return 当前生成器实例
     */
    public AdvancedDocumentGenerator addFooter() {
        if (config.isGenerateFooter()) {
            creator.addParagraph("\n\n");
            creator.addParagraph("---");

            String footerText = "由 " + config.getCompany() + " 生成";
            if (!config.getDepartment().isEmpty()) {
                footerText += " - " + config.getDepartment();
            }

            String currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern(config.getDateFormat()));
            footerText += " | " + currentDate;

            creator.addParagraph(footerText);
        }
        return this;
    }

    /**
     * 创建文档并保存到指定路径
     *
     * @param outputPath 输出路径
     * @throws IOException 文件操作异常
     */
    public void createDocument(Path outputPath) throws IOException {
        // 如果配置了生成目录但还没有生成，则先生成目录
        if (config.isGenerateToc() && !tocEntries.isEmpty()) {
            // 这里可以在最终生成前重新排列内容
        }

        creator.createDocument(outputPath);
    }

    /**
     * 创建文档并返回字节数组
     *
     * @return 文档字节数组
     * @throws IOException 文档创建异常
     */
    public byte[] createDocumentAsBytes() throws IOException {
        // 如果配置了生成目录但还没有生成，则先生成目录
        if (config.isGenerateToc() && !tocEntries.isEmpty()) {
            // 这里可以在最终生成前重新排列内容
        }

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
}