package com.boundesu.words;

import com.boundesu.words.common.creator.DocumentCreator;
import com.boundesu.words.common.util.PerformanceMonitor;
import com.boundesu.words.core.advanced.AdvancedDocumentGenerator;
import com.boundesu.words.core.creator.DocumentCreatorFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Boundesu Words SDK 综合测试类
 *
 * @author Boundesu Team
 * @version 1.0.0
 */
public class BoundesuWordsSDKTest {

    @Test
    public void testSDKInfo() {
        Map<String, Object> info = BoundesuWordsSDK.getSDKInfo();

        Assert.assertNotNull(info);
        Assert.assertEquals(info.get("version"), "1.0.0");
        Assert.assertEquals(info.get("name"), "Boundesu Words SDK");
        Assert.assertNotNull(info.get("supportedFormats"));
        Assert.assertNotNull(info.get("supportedInputs"));
        Assert.assertNotNull(info.get("creatorTypes"));
    }

    @Test
    public void testCreateDocumentCreator() {
        // 测试字符串类型创建
        DocumentCreator poiCreator = BoundesuWordsSDK.createDocumentCreator("poi");
        Assert.assertNotNull(poiCreator);

        DocumentCreator htmlCreator = BoundesuWordsSDK.createDocumentCreator("html");
        Assert.assertNotNull(htmlCreator);

        DocumentCreator xmlCreator = BoundesuWordsSDK.createDocumentCreator("xml");
        Assert.assertNotNull(xmlCreator);

        // 测试枚举类型创建
        DocumentCreator poiCreator2 = BoundesuWordsSDK.createDocumentCreator(DocumentCreatorFactory.CreatorType.DIRECT_POI);
        Assert.assertNotNull(poiCreator2);

        DocumentCreator htmlCreator2 = BoundesuWordsSDK.createDocumentCreator(DocumentCreatorFactory.CreatorType.HTML_CONVERSION);
        Assert.assertNotNull(htmlCreator2);

        DocumentCreator xmlCreator2 = BoundesuWordsSDK.createDocumentCreator(DocumentCreatorFactory.CreatorType.XML_CONVERSION);
        Assert.assertNotNull(xmlCreator2);
    }

    @Test
    public void testCreateAdvancedGenerator() {
        AdvancedDocumentGenerator generator1 = BoundesuWordsSDK.createAdvancedGenerator();
        Assert.assertNotNull(generator1);

        AdvancedDocumentGenerator generator2 = BoundesuWordsSDK.createAdvancedGenerator(DocumentCreatorFactory.CreatorType.DIRECT_POI);
        Assert.assertNotNull(generator2);
    }

    @Test
    public void testCreateSimpleDocument() throws IOException {
        Path tempFile = Files.createTempFile("test_simple", ".docx");

        try {
            BoundesuWordsSDK.createSimpleDocument(
                    "测试文档",
                    "测试作者",
                    "这是一个简单的测试文档内容。\n包含多行文本。",
                    tempFile
            );

            Assert.assertTrue(Files.exists(tempFile));
            Assert.assertTrue(Files.size(tempFile) > 0);
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    public void testCreateReportDocument() throws IOException {
        Path tempFile = Files.createTempFile("test_report", ".docx");

        try {
            Map<String, String> chapters = new HashMap<>();
            chapters.put("概述", "这是概述章节的内容，介绍了报告的基本情况。");
            chapters.put("详细分析", "这是详细分析章节，包含了深入的数据分析和见解。");
            chapters.put("结论与建议", "基于分析结果，我们得出以下结论和建议。");

            BoundesuWordsSDK.createReportDocument(
                    "季度业务报告",
                    "业务分析师",
                    "本报告分析了本季度的业务表现和关键指标。",
                    chapters,
                    tempFile
            );

            Assert.assertTrue(Files.exists(tempFile));
            Assert.assertTrue(Files.size(tempFile) > 0);
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    public void testCreateDocumentFromHtml() throws IOException {
        Path tempFile = Files.createTempFile("test_html", ".docx");

        try {
            String htmlContent = "<html><head><title>HTML测试文档</title></head><body>" +
                    "<h1>主标题</h1>" +
                    "<p>这是第一段内容。</p>" +
                    "<h2>副标题</h2>" +
                    "<p>这是第二段内容，包含<strong>粗体</strong>和<em>斜体</em>文本。</p>" +
                    "<ul><li>列表项1</li><li>列表项2</li></ul>" +
                    "</body></html>";

            BoundesuWordsSDK.createDocumentFromHtml(htmlContent, tempFile);

            Assert.assertTrue(Files.exists(tempFile));
            Assert.assertTrue(Files.size(tempFile) > 0);
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    public void testCreateDocumentFromXml() throws IOException {
        Path tempFile = Files.createTempFile("test_xml", ".docx");

        try {
            String xmlContent = "<?xml version='1.0' encoding='UTF-8'?>" +
                    "<document>" +
                    "<title>XML测试文档</title>" +
                    "<section>" +
                    "<heading level='1'>主标题</heading>" +
                    "<paragraph>这是第一段内容。</paragraph>" +
                    "<heading level='2'>副标题</heading>" +
                    "<paragraph>这是第二段内容。</paragraph>" +
                    "</section>" +
                    "</document>";

            BoundesuWordsSDK.createDocumentFromXml(xmlContent, tempFile);

            Assert.assertTrue(Files.exists(tempFile));
            Assert.assertTrue(Files.size(tempFile) > 0);
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    public void testValidateDocumentParameters() {
        Assert.assertTrue(BoundesuWordsSDK.validateDocumentParameters("有效标题", "test.docx"));
        Assert.assertFalse(BoundesuWordsSDK.validateDocumentParameters("", "test.docx"));
        Assert.assertFalse(BoundesuWordsSDK.validateDocumentParameters("有效标题", ""));
        Assert.assertFalse(BoundesuWordsSDK.validateDocumentParameters(null, "test.docx"));
    }

    @Test
    public void testGetSupportedCreatorTypes() {
        DocumentCreatorFactory.CreatorType[] types = BoundesuWordsSDK.getSupportedCreatorTypes();
        Assert.assertNotNull(types);
        Assert.assertEquals(types.length, 3);

        Set<DocumentCreatorFactory.CreatorType> typeSet = new HashSet<>(Arrays.asList(types));
        Assert.assertTrue(typeSet.contains(DocumentCreatorFactory.CreatorType.DIRECT_POI));
        Assert.assertTrue(typeSet.contains(DocumentCreatorFactory.CreatorType.HTML_CONVERSION));
        Assert.assertTrue(typeSet.contains(DocumentCreatorFactory.CreatorType.XML_CONVERSION));
    }

    @Test
    public void testIsCreatorTypeSupported() {
        Assert.assertTrue(BoundesuWordsSDK.isCreatorTypeSupported("poi"));
        Assert.assertTrue(BoundesuWordsSDK.isCreatorTypeSupported("html"));
        Assert.assertTrue(BoundesuWordsSDK.isCreatorTypeSupported("xml"));
        Assert.assertTrue(BoundesuWordsSDK.isCreatorTypeSupported("direct"));
        Assert.assertFalse(BoundesuWordsSDK.isCreatorTypeSupported("invalid"));
        Assert.assertFalse(BoundesuWordsSDK.isCreatorTypeSupported(null));
        Assert.assertFalse(BoundesuWordsSDK.isCreatorTypeSupported(""));
    }

    @Test
    public void testGetCreatorTypeDescription() {
        String poiDesc = BoundesuWordsSDK.getCreatorTypeDescription(DocumentCreatorFactory.CreatorType.DIRECT_POI);
        Assert.assertNotNull(poiDesc);
        Assert.assertFalse(poiDesc.isEmpty());

        String htmlDesc = BoundesuWordsSDK.getCreatorTypeDescription(DocumentCreatorFactory.CreatorType.HTML_CONVERSION);
        Assert.assertNotNull(htmlDesc);
        Assert.assertFalse(htmlDesc.isEmpty());

        String xmlDesc = BoundesuWordsSDK.getCreatorTypeDescription(DocumentCreatorFactory.CreatorType.XML_CONVERSION);
        Assert.assertNotNull(xmlDesc);
        Assert.assertFalse(xmlDesc.isEmpty());
    }

    @Test
    public void testCreatePerformanceMonitor() {
        PerformanceMonitor.OperationContext monitor = BoundesuWordsSDK.createPerformanceMonitor();
        Assert.assertNotNull(monitor);
    }

    @Test
    public void testMonitorDocumentCreation() throws Exception {
        byte[] result = BoundesuWordsSDK.monitorDocumentCreation(() -> {
            DocumentCreator creator = BoundesuWordsSDK.createDocumentCreator("poi");
            return creator.setTitle("性能测试文档")
                    .setAuthor("测试作者")
                    .addHeading("测试标题", 1)
                    .addParagraph("测试内容")
                    .createDocumentAsBytes();
        });

        Assert.assertNotNull(result);
        Assert.assertTrue(result.length > 0);
    }

    @Test
    public void testUtilsCreateTechnicalDocument() throws IOException {
        Path tempFile = Files.createTempFile("test_technical", ".docx");

        try {
            Map<String, String> sections = new HashMap<>();
            sections.put("系统架构", "本节描述了系统的整体架构设计。");
            sections.put("技术实现", "本节详细说明了关键技术的实现方案。");
            sections.put("性能优化", "本节介绍了系统性能优化的策略和方法。");

            BoundesuWordsSDK.Utils.createTechnicalDocument(
                    "系统技术文档",
                    "技术团队",
                    "本文档详细介绍了系统的技术实现细节。",
                    sections,
                    "通过本文档的技术方案，系统能够满足业务需求并保证良好的性能。",
                    tempFile
            );

            Assert.assertTrue(Files.exists(tempFile));
            Assert.assertTrue(Files.size(tempFile) > 0);
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    public void testUtilsCreateMeetingMinutes() throws IOException {
        Path tempFile = Files.createTempFile("test_meeting", ".docx");

        try {
            List<String> attendees = Arrays.asList("张三", "李四", "王五", "赵六");
            List<String> agenda = Arrays.asList(
                    "回顾上周工作进展",
                    "讨论本周工作计划",
                    "解决遇到的技术问题",
                    "确定下周目标"
            );
            List<String> decisions = Arrays.asList(
                    "确定新功能的开发优先级",
                    "调整项目时间计划",
                    "增加代码审查流程"
            );
            List<String> actionItems = Arrays.asList(
                    "张三负责完成用户界面设计",
                    "李四负责后端API开发",
                    "王五负责数据库优化",
                    "赵六负责测试用例编写"
            );

            BoundesuWordsSDK.Utils.createMeetingMinutes(
                    "周例会",
                    "2024年1月15日",
                    attendees,
                    agenda,
                    decisions,
                    actionItems,
                    tempFile
            );

            Assert.assertTrue(Files.exists(tempFile));
            Assert.assertTrue(Files.size(tempFile) > 0);
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    public void testUtilsBatchCreateDocuments() throws IOException {
        List<BoundesuWordsSDK.DocumentInfo> documents = new ArrayList<>();
        List<Path> tempFiles = new ArrayList<>();

        try {
            for (int i = 1; i <= 3; i++) {
                Path tempFile = Files.createTempFile("batch_test_" + i, ".docx");
                tempFiles.add(tempFile);

                documents.add(new BoundesuWordsSDK.DocumentInfo(
                        "批量文档 " + i,
                        "批量作者 " + i,
                        "这是第 " + i + " 个批量创建的文档内容。",
                        tempFile
                ));
            }

            BoundesuWordsSDK.Utils.batchCreateDocuments(documents);

            for (Path tempFile : tempFiles) {
                Assert.assertTrue(Files.exists(tempFile));
                Assert.assertTrue(Files.size(tempFile) > 0);
            }
        } finally {
            for (Path tempFile : tempFiles) {
                Files.deleteIfExists(tempFile);
            }
        }
    }

    @Test
    public void testDocumentInfo() {
        Path testPath = Paths.get("test.docx");
        BoundesuWordsSDK.DocumentInfo info = new BoundesuWordsSDK.DocumentInfo(
                "测试标题", "测试作者", "测试内容", testPath
        );

        Assert.assertEquals(info.getTitle(), "测试标题");
        Assert.assertEquals(info.getAuthor(), "测试作者");
        Assert.assertEquals(info.getContent(), "测试内容");
        Assert.assertEquals(info.getFilePath(), testPath);

        // 测试setter
        info.setTitle("新标题");
        info.setAuthor("新作者");
        info.setContent("新内容");
        Path newPath = Paths.get("new.docx");
        info.setFilePath(newPath);

        Assert.assertEquals(info.getTitle(), "新标题");
        Assert.assertEquals(info.getAuthor(), "新作者");
        Assert.assertEquals(info.getContent(), "新内容");
        Assert.assertEquals(info.getFilePath(), newPath);
    }
}