package com.boundesu.words.sdk.factory;

import com.boundesu.words.sdk.constants.DocxConstants;
import com.boundesu.words.sdk.model.BoundesuDocument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 文档工厂类
 * 提供各种预定义的文档模板和创建方法
 *
 * @author Boundesu Team
 * @version 1.0.0
 */
public final class DocumentFactory {

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern(DocxConstants.DATE_FORMAT_PATTERN);

    // 防止实例化
    private DocumentFactory() {
        throw new UnsupportedOperationException("工厂类不能被实例化");
    }

    /**
     * 创建基础文档对象
     *
     * @param title  文档标题
     * @param author 作者
     * @return 基础文档对象
     */
    private static BoundesuDocument createBasicDocument(String title, String author) {
        LocalDateTime now = LocalDateTime.now();

        BoundesuDocument document = new BoundesuDocument();
        document.setDocumentId(UUID.randomUUID().toString());
        document.setTitle(title);
        document.setAuthor(author);
        document.setCreatedAt(now);
        document.setLastModified(now);
        document.setVersion("1.0");
        document.setLanguage("zh-CN");
        document.setStatus(BoundesuDocument.DocumentStatus.DRAFT);

        return document;
    }

    /**
     * 创建商业报告文档模板
     *
     * @param title      报告标题
     * @param author     作者
     * @param department 部门
     * @return 文档对象
     */
    public static BoundesuDocument createBusinessReportTemplate(String title, String author, String department) {
        BoundesuDocument document = createBasicDocument(title, author);

        document.setSubject("商业报告");
        document.setDescription("使用Boundesu Words SDK创建的商业报告文档");

        // 设置关键词列表
        List<String> keywords = new ArrayList<>();
        keywords.add("商业报告");
        keywords.add("数据分析");
        keywords.add("业务总结");
        document.setKeywords(keywords);

        // 设置自定义属性
        document.addCustomProperty("document_type", "business_report");
        document.addCustomProperty("department", department);
        document.addCustomProperty("template_version", "1.0");
        document.addCustomProperty("confidentiality", "internal");

        return document;
    }

    /**
     * 创建技术文档模板
     *
     * @param title   文档标题
     * @param author  作者
     * @param project 项目名称
     * @return 文档对象
     */
    public static BoundesuDocument createTechnicalDocumentTemplate(String title, String author, String project) {
        BoundesuDocument document = createBasicDocument(title, author);

        document.setSubject("技术文档");
        document.setDescription("技术规范和实现文档");

        // 设置关键词列表
        List<String> keywords = new ArrayList<>();
        keywords.add("技术文档");
        keywords.add("系统设计");
        keywords.add("开发规范");
        document.setKeywords(keywords);

        // 设置自定义属性
        document.addCustomProperty("document_type", "technical_document");
        document.addCustomProperty("project", project);
        document.addCustomProperty("template_version", "1.0");
        document.addCustomProperty("review_required", "true");

        return document;
    }

    /**
     * 创建用户手册模板
     *
     * @param title   手册标题
     * @param author  作者
     * @param version 版本号
     * @return 文档对象
     */
    public static BoundesuDocument createUserManualTemplate(String title, String author, String version) {
        BoundesuDocument document = createBasicDocument(title, author);

        document.setSubject("用户手册");
        document.setDescription("产品使用指南和操作手册");

        // 设置关键词列表
        List<String> keywords = new ArrayList<>();
        keywords.add("用户手册");
        keywords.add("操作指南");
        keywords.add("使用说明");
        document.setKeywords(keywords);

        // 设置自定义属性
        document.addCustomProperty("document_type", "user_manual");
        document.addCustomProperty("product_version", version);
        document.addCustomProperty("template_version", "1.0");
        document.addCustomProperty("target_audience", "end_users");

        return document;
    }

    /**
     * 创建会议纪要模板
     *
     * @param title       会议标题
     * @param organizer   组织者
     * @param meetingDate 会议日期
     * @return 文档对象
     */
    public static BoundesuDocument createMeetingMinutesTemplate(String title, String organizer, LocalDateTime meetingDate) {
        BoundesuDocument document = createBasicDocument(title, organizer);

        document.setSubject("会议纪要");
        document.setDescription("会议记录和决议事项");

        // 设置关键词列表
        List<String> keywords = new ArrayList<>();
        keywords.add("会议纪要");
        keywords.add("会议记录");
        keywords.add("决议事项");
        document.setKeywords(keywords);

        // 设置自定义属性
        document.addCustomProperty("document_type", "meeting_minutes");
        document.addCustomProperty("meeting_date", meetingDate.format(DATE_FORMATTER));
        document.addCustomProperty("template_version", "1.0");
        document.addCustomProperty("status", "draft");

        return document;
    }

    /**
     * 创建项目计划文档模板
     *
     * @param title       计划标题
     * @param author      作者
     * @param projectCode 项目代码
     * @return 文档对象
     */
    public static BoundesuDocument createProjectPlanTemplate(String title, String author, String projectCode) {
        BoundesuDocument document = createBasicDocument(title, author);

        document.setSubject("项目计划");
        document.setDescription("项目实施计划和时间安排");

        // 设置关键词列表
        List<String> keywords = new ArrayList<>();
        keywords.add("项目计划");
        keywords.add("时间安排");
        keywords.add("里程碑");
        document.setKeywords(keywords);

        // 设置自定义属性
        document.addCustomProperty("document_type", "project_plan");
        document.addCustomProperty("project_code", projectCode);
        document.addCustomProperty("template_version", "1.0");
        document.addCustomProperty("approval_required", "true");

        return document;
    }

    /**
     * 创建测试报告模板
     *
     * @param title     报告标题
     * @param author    作者
     * @param testPhase 测试阶段
     * @return 文档对象
     */
    public static BoundesuDocument createTestReportTemplate(String title, String author, String testPhase) {
        BoundesuDocument document = createBasicDocument(title, author);

        document.setSubject("测试报告");
        document.setDescription("软件测试结果和质量评估报告");

        // 设置关键词列表
        List<String> keywords = new ArrayList<>();
        keywords.add("测试报告");
        keywords.add("质量评估");
        keywords.add("缺陷分析");
        document.setKeywords(keywords);

        // 设置自定义属性
        document.addCustomProperty("document_type", "test_report");
        document.addCustomProperty("test_phase", testPhase);
        document.addCustomProperty("template_version", "1.0");
        document.addCustomProperty("quality_gate", "pending");

        return document;
    }

    /**
     * 生成唯一的文档ID
     *
     * @return 文档ID
     */
    private static String generateDocumentId() {
        return "DOC-" + UUID.randomUUID().toString().replace("-", "").substring(0, 12).toUpperCase();
    }

    /**
     * 获取当前时间戳字符串
     *
     * @return 时间戳字符串
     */
    public static String getCurrentTimestamp() {
        return LocalDateTime.now().format(DATE_FORMATTER);
    }

    /**
     * 创建空白文档（用于测试）
     *
     * @return 空白文档对象
     */
    public static BoundesuDocument createBlankDocument() {
        return createBasicDocument("空白文档", DocxConstants.DEFAULT_AUTHOR);
    }
}