package com.boundesu.words.examples;

import com.boundesu.words.BoundesuWordsSDK;
import com.boundesu.words.core.creator.DocumentCreator;
import com.boundesu.words.core.creator.DocumentCreatorFactory;
import com.boundesu.words.core.advanced.AdvancedDocumentGenerator;
import com.boundesu.words.common.util.PerformanceMonitor;
import com.boundesu.words.common.exception.BoundesuWordsException;
import com.boundesu.words.html.converter.HtmlToDocxConverter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Boundesu Words SDK 示例应用程序
 * 
 * 本示例展示了如何使用SDK的各种功能来创建不同类型的Word文档
 * 
 * @author Boundesu Team
 * @version 1.0.0
 */
public class SDKExampleApp {
    
    private static final String OUTPUT_DIR = "examples_output";
    
    public static void main(String[] args) {
        System.out.println("=== Boundesu Words SDK 示例应用程序 ===");
        
        try {
            // 创建输出目录
            createOutputDirectory();
            
            // 显示SDK信息
            displaySDKInfo();
            
            // 示例1：创建简单文档
            example1_CreateSimpleDocument();
            
            // 示例2：使用不同的创建器类型
            example2_DifferentCreatorTypes();
            
            // 示例3：创建高级报告文档
            example3_CreateAdvancedReport();
            
            // 示例4：从HTML创建文档
            example4_CreateFromHtml();
            
            // 示例5：从XML创建文档
            example5_CreateFromXml();
            
            // 示例6：创建技术文档
            example6_CreateTechnicalDocument();
            
            // 示例7：创建会议纪要
            example7_CreateMeetingMinutes();
            
            // 示例8：批量创建文档
            example8_BatchCreateDocuments();
            
            // 示例9：性能监控
            example9_PerformanceMonitoring();
            
            // 示例10：高级文档生成器
            example10_AdvancedDocumentGenerator();
            
            // 示例11：测试流转换器
            example11_TestStreamConverter();
            
            // 示例12：页边距设置功能
            example12_PageMarginsDemo();
            
            System.out.println("\n=== 所有示例执行完成！===");
            System.out.println("输出文件位置: " + Paths.get(OUTPUT_DIR).toAbsolutePath());
            
        } catch (Exception e) {
            System.err.println("执行示例时发生错误: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void createOutputDirectory() throws IOException {
        Path outputPath = Paths.get(OUTPUT_DIR);
        if (!Files.exists(outputPath)) {
            Files.createDirectories(outputPath);
            System.out.println("创建输出目录: " + outputPath.toAbsolutePath());
        }
    }
    
    private static void displaySDKInfo() {
        System.out.println("\n=== SDK 信息 ===");
        Map<String, Object> info = BoundesuWordsSDK.getSDKInfo();
        info.forEach((key, value) -> {
            if (value instanceof String[]) {
                System.out.println(key + ": " + Arrays.toString((String[]) value));
            } else {
                System.out.println(key + ": " + value);
            }
        });
        
        System.out.println("\n支持的创建器类型:");
        for (DocumentCreatorFactory.CreatorType type : BoundesuWordsSDK.getSupportedCreatorTypes()) {
            System.out.println("- " + type + ": " + BoundesuWordsSDK.getCreatorTypeDescription(type));
        }
    }
    
    private static void example1_CreateSimpleDocument() throws IOException {
        System.out.println("\n=== 示例1: 创建简单文档 ===");
        
        Path outputFile = Paths.get(OUTPUT_DIR, "example1_simple_document.docx");
        
        BoundesuWordsSDK.createSimpleDocument(
            "我的第一个文档",
            "SDK用户",
            "这是使用Boundesu Words SDK创建的第一个简单文档。\n\n" +
            "SDK提供了简单易用的API来创建Word文档。\n" +
            "您可以轻松地添加标题、作者和内容。",
            outputFile
        );
        
        System.out.println("简单文档已创建: " + outputFile.getFileName());
    }
    
    private static void example2_DifferentCreatorTypes() throws IOException {
        System.out.println("\n=== 示例2: 使用不同的创建器类型 ===");
        
        // POI直接创建器
        DocumentCreator poiCreator = BoundesuWordsSDK.createDocumentCreator("poi");
        Path poiFile = Paths.get(OUTPUT_DIR, "example2_poi_document.docx");
        
        poiCreator.setTitle("POI创建器示例")
                 .setAuthor("POI用户")
                 .setHeader("POI创建器示例文档")
                 .setFooter("Boundesu Words SDK")
                 .setPageNumberEnabled(true)
                 .addHeading("使用POI创建器", 1)
                 .addParagraph("这个文档是使用Apache POI直接创建器生成的。")
                 .addParagraph("POI创建器提供了最直接和高效的文档创建方式。")
                 .addHeading("特点", 2)
                 .addParagraph("• 高性能")
                 .addParagraph("• 直接操作")
                 .addParagraph("• 完全控制")
                 .createDocument(poiFile);
        
        System.out.println("POI文档已创建: " + poiFile.getFileName());
        
        // HTML创建器
        DocumentCreator htmlCreator = BoundesuWordsSDK.createDocumentCreator("html");
        Path htmlFile = Paths.get(OUTPUT_DIR, "example2_html_document.docx");
        
        htmlCreator.setTitle("HTML创建器示例")
                  .setAuthor("HTML用户")
                  .setHeader("HTML创建器示例文档")
                  .setFooter("HTML转换技术")
                  .setPageNumberEnabled(true)
                  .addHeading("使用HTML创建器", 1)
                  .addParagraph("这个文档是使用HTML转换创建器生成的。")
                  .addParagraph("HTML创建器允许您使用熟悉的HTML标记来创建文档。")
                  .createDocument(htmlFile);
        
        System.out.println("HTML文档已创建: " + htmlFile.getFileName());
        
        // XML创建器
        DocumentCreator xmlCreator = BoundesuWordsSDK.createDocumentCreator("xml");
        Path xmlFile = Paths.get(OUTPUT_DIR, "example2_xml_document.docx");
        
        xmlCreator.setTitle("XML创建器示例")
                 .setAuthor("XML用户")
                 .setHeader("XML创建器示例文档")
                 .setFooter("XML转换技术")
                 .setPageNumberEnabled(true)
                 .addHeading("使用XML创建器", 1)
                 .addParagraph("这个文档是使用XML转换创建器生成的。")
                 .addParagraph("XML创建器提供了结构化的文档创建方式。")
                 .createDocument(xmlFile);
        
        System.out.println("XML文档已创建: " + xmlFile.getFileName());
    }
    
    private static void example3_CreateAdvancedReport() throws IOException {
        System.out.println("\n=== 示例3: 创建高级报告文档 ===");
        
        Map<String, String> chapters = new LinkedHashMap<>();
        chapters.put("执行摘要", 
            "本季度业绩表现良好，各项指标均达到预期目标。\n" +
            "主要亮点包括：\n" +
            "• 销售额增长15%\n" +
            "• 客户满意度提升至95%\n" +
            "• 新产品成功上市");
            
        chapters.put("市场分析", 
            "市场环境分析显示：\n" +
            "1. 整体市场需求稳定增长\n" +
            "2. 竞争对手策略调整\n" +
            "3. 新兴技术带来机遇\n\n" +
            "我们的市场份额从上季度的12%增长到15%，" +
            "这主要得益于产品创新和营销策略的优化。");
            
        chapters.put("财务表现", 
            "财务数据分析：\n" +
            "• 营业收入：1,250万元（同比增长18%）\n" +
            "• 净利润：180万元（同比增长22%）\n" +
            "• 毛利率：35.2%（提升2.1个百分点）\n\n" +
            "成本控制效果显著，运营效率持续提升。");
            
        chapters.put("风险与挑战", 
            "当前面临的主要风险：\n" +
            "1. 原材料价格波动\n" +
            "2. 汇率变化影响\n" +
            "3. 新法规合规要求\n\n" +
            "应对措施：\n" +
            "• 建立供应链风险管理体系\n" +
            "• 采用金融工具对冲汇率风险\n" +
            "• 加强合规团队建设");
            
        chapters.put("未来展望", 
            "下季度工作重点：\n" +
            "1. 扩大市场覆盖范围\n" +
            "2. 加强研发投入\n" +
            "3. 优化组织结构\n\n" +
            "预期目标：\n" +
            "• 销售额增长20%\n" +
            "• 推出2款新产品\n" +
            "• 进入3个新市场");
        
        Path reportFile = Paths.get(OUTPUT_DIR, "example3_quarterly_report.docx");
        
        BoundesuWordsSDK.createReportDocument(
            "2024年第一季度业务报告",
            "业务分析团队",
            "本报告全面分析了2024年第一季度的业务表现，" +
            "包括市场分析、财务数据、风险评估和未来展望。",
            chapters,
            reportFile
        );
        
        System.out.println("高级报告已创建: " + reportFile.getFileName());
    }
    
    private static void example4_CreateFromHtml() throws IOException {
        System.out.println("\n=== 示例4: 从HTML创建文档 ===");
        
        String htmlContent = 
            "<html>" +
            "<head><title>产品介绍文档</title></head>" +
            "<body>" +
            "<h1>新产品发布</h1>" +
            "<p>我们很高兴地宣布推出我们的最新产品 - <strong>智能办公助手</strong>。</p>" +
            "<h2>产品特性</h2>" +
            "<ul>" +
            "<li><strong>智能语音识别</strong>：支持多种语言和方言</li>" +
            "<li><strong>自动文档生成</strong>：基于AI的内容创建</li>" +
            "<li><strong>云端同步</strong>：实时数据备份和同步</li>" +
            "<li><strong>团队协作</strong>：多人实时编辑和评论</li>" +
            "</ul>" +
            "<h2>技术规格</h2>" +
            "<table border='1'>" +
            "<tr><th>项目</th><th>规格</th></tr>" +
            "<tr><td>处理器</td><td>ARM Cortex-A78</td></tr>" +
            "<tr><td>内存</td><td>8GB LPDDR5</td></tr>" +
            "<tr><td>存储</td><td>256GB UFS 3.1</td></tr>" +
            "<tr><td>电池</td><td>5000mAh</td></tr>" +
            "</table>" +
            "<h2>价格信息</h2>" +
            "<p>标准版：<em>2,999元</em></p>" +
            "<p>专业版：<em>3,999元</em></p>" +
            "<p>企业版：<em>5,999元</em></p>" +
            "<h2>联系我们</h2>" +
            "<p>如需了解更多信息，请访问我们的官网：" +
            "<a href='https://www.example.com'>www.example.com</a></p>" +
            "</body>" +
            "</html>";
        
        Path htmlFile = Paths.get(OUTPUT_DIR, "example4_html_product.docx");
        
        BoundesuWordsSDK.createDocumentFromHtml(htmlContent, htmlFile);
        
        System.out.println("HTML文档已创建: " + htmlFile.getFileName());
    }
    
    private static void example5_CreateFromXml() throws IOException {
        System.out.println("\n=== 示例5: 从XML创建文档 ===");
        
        String xmlContent = 
            "<?xml version='1.0' encoding='UTF-8'?>" +
            "<document>" +
            "<title>项目管理指南</title>" +
            "<author>项目管理办公室</author>" +
            "<section>" +
            "<heading level='1'>项目管理概述</heading>" +
            "<paragraph>项目管理是运用专业知识、技能、工具和技术来满足项目要求的过程。</paragraph>" +
            "<paragraph>有效的项目管理能够确保项目按时、按预算、按质量要求完成。</paragraph>" +
            "<heading level='2'>项目生命周期</heading>" +
            "<paragraph>项目通常包含以下几个阶段：</paragraph>" +
            "<list type='ordered'>" +
            "<item>项目启动</item>" +
            "<item>项目规划</item>" +
            "<item>项目执行</item>" +
            "<item>项目监控</item>" +
            "<item>项目收尾</item>" +
            "</list>" +
            "<heading level='2'>关键成功因素</heading>" +
            "<list type='unordered'>" +
            "<item>明确的项目目标和范围</item>" +
            "<item>充分的资源配置</item>" +
            "<item>有效的沟通机制</item>" +
            "<item>风险管理和控制</item>" +
            "<item>团队协作和领导力</item>" +
            "</list>" +
            "<heading level='2'>项目管理工具</heading>" +
            "<paragraph>常用的项目管理工具包括：</paragraph>" +
            "<table>" +
            "<row><cell>工具类型</cell><cell>具体工具</cell><cell>主要用途</cell></row>" +
            "<row><cell>进度管理</cell><cell>甘特图</cell><cell>可视化项目时间线</cell></row>" +
            "<row><cell>任务管理</cell><cell>看板</cell><cell>跟踪任务状态</cell></row>" +
            "<row><cell>协作工具</cell><cell>在线文档</cell><cell>团队协作和信息共享</cell></row>" +
            "</table>" +
            "</section>" +
            "<section>" +
            "<heading level='1'>最佳实践</heading>" +
            "<paragraph>基于多年的项目管理经验，我们总结出以下最佳实践：</paragraph>" +
            "<paragraph><strong>1. 制定详细的项目计划</strong></paragraph>" +
            "<paragraph>在项目开始前，制定详细的项目计划，包括时间安排、资源分配、风险评估等。</paragraph>" +
            "<paragraph><strong>2. 建立有效的沟通机制</strong></paragraph>" +
            "<paragraph>定期举行项目会议，及时沟通项目进展和问题，确保信息透明。</paragraph>" +
            "<paragraph><strong>3. 持续监控和调整</strong></paragraph>" +
            "<paragraph>定期检查项目进度，及时发现偏差并采取纠正措施。</paragraph>" +
            "</section>" +
            "</document>";
        
        Path xmlFile = Paths.get(OUTPUT_DIR, "example5_xml_project_guide.docx");
        
        BoundesuWordsSDK.createDocumentFromXml(xmlContent, xmlFile);
        
        System.out.println("XML文档已创建: " + xmlFile.getFileName());
    }
    
    private static void example6_CreateTechnicalDocument() throws IOException {
        System.out.println("\n=== 示例6: 创建技术文档 ===");
        
        Map<String, String> sections = new LinkedHashMap<>();
        sections.put("系统架构", 
            "本系统采用微服务架构设计，主要包含以下组件：\n\n" +
            "1. API网关层\n" +
            "   - 负责请求路由和负载均衡\n" +
            "   - 实现统一的身份认证和授权\n" +
            "   - 提供API限流和监控功能\n\n" +
            "2. 业务服务层\n" +
            "   - 用户管理服务\n" +
            "   - 订单处理服务\n" +
            "   - 支付服务\n" +
            "   - 通知服务\n\n" +
            "3. 数据存储层\n" +
            "   - MySQL主从集群\n" +
            "   - Redis缓存集群\n" +
            "   - Elasticsearch搜索引擎");
            
        sections.put("技术选型", 
            "技术栈选择基于以下考虑：\n\n" +
            "后端技术：\n" +
            "• Spring Boot 2.7.x - 快速开发框架\n" +
            "• Spring Cloud 2021.x - 微服务治理\n" +
            "• MyBatis Plus - 数据访问层\n" +
            "• Redis - 缓存和会话存储\n" +
            "• RabbitMQ - 消息队列\n\n" +
            "前端技术：\n" +
            "• Vue.js 3.x - 前端框架\n" +
            "• Element Plus - UI组件库\n" +
            "• Axios - HTTP客户端\n" +
            "• Webpack - 构建工具\n\n" +
            "运维技术：\n" +
            "• Docker - 容器化部署\n" +
            "• Kubernetes - 容器编排\n" +
            "• Jenkins - CI/CD流水线\n" +
            "• Prometheus - 监控告警");
            
        sections.put("核心功能实现", 
            "1. 用户认证与授权\n" +
            "   采用JWT令牌机制，结合Redis实现分布式会话管理。\n" +
            "   支持多种登录方式：用户名密码、手机验证码、第三方OAuth。\n\n" +
            "2. 数据一致性保证\n" +
            "   使用分布式事务框架Seata确保跨服务数据一致性。\n" +
            "   关键业务操作采用TCC模式，提高系统可靠性。\n\n" +
            "3. 高可用设计\n" +
            "   服务多实例部署，支持自动故障转移。\n" +
            "   数据库主从复制，读写分离提升性能。\n" +
            "   关键数据多地备份，确保数据安全。\n\n" +
            "4. 性能优化\n" +
            "   多级缓存策略：浏览器缓存、CDN缓存、应用缓存、数据库缓存。\n" +
            "   数据库索引优化，SQL查询性能调优。\n" +
            "   异步处理非关键业务，提升用户体验。");
            
        sections.put("部署与运维", 
            "1. 容器化部署\n" +
            "   所有服务打包为Docker镜像，统一部署标准。\n" +
            "   使用Docker Compose进行本地开发环境搭建。\n" +
            "   生产环境使用Kubernetes进行容器编排。\n\n" +
            "2. CI/CD流水线\n" +
            "   代码提交触发自动构建和测试。\n" +
            "   通过质量门禁后自动部署到测试环境。\n" +
            "   人工审核通过后部署到生产环境。\n\n" +
            "3. 监控与告警\n" +
            "   应用性能监控：响应时间、吞吐量、错误率。\n" +
            "   基础设施监控：CPU、内存、磁盘、网络。\n" +
            "   业务指标监控：用户活跃度、交易量、转化率。\n" +
            "   多渠道告警：邮件、短信、钉钉、微信。\n\n" +
            "4. 日志管理\n" +
            "   ELK技术栈：Elasticsearch + Logstash + Kibana。\n" +
            "   结构化日志格式，便于查询和分析。\n" +
            "   日志分级存储，平衡成本和查询效率。");
        
        Path techFile = Paths.get(OUTPUT_DIR, "example6_technical_document.docx");
        
        BoundesuWordsSDK.Utils.createTechnicalDocument(
            "电商平台技术架构文档",
            "技术架构团队",
            "本文档详细描述了电商平台的技术架构设计、核心功能实现和部署运维方案。" +
            "旨在为开发团队提供技术指导，为运维团队提供部署参考。",
            sections,
            "通过采用现代化的技术架构和最佳实践，" +
            "本系统能够支撑大规模用户访问和高并发交易处理，" +
            "同时保证系统的高可用性、可扩展性和可维护性。",
            techFile
        );
        
        System.out.println("技术文档已创建: " + techFile.getFileName());
    }
    
    private static void example7_CreateMeetingMinutes() throws IOException {
        System.out.println("\n=== 示例7: 创建会议纪要 ===");
        
        List<String> attendees = Arrays.asList(
            "张三 - 项目经理",
            "李四 - 技术负责人", 
            "王五 - 前端开发工程师",
            "赵六 - 后端开发工程师",
            "钱七 - 测试工程师",
            "孙八 - 产品经理",
            "周九 - UI设计师"
        );
        
        List<String> agenda = Arrays.asList(
            "回顾上周工作进展和完成情况",
            "讨论当前项目遇到的技术难点",
            "确定本周的开发任务和优先级",
            "评估项目进度和风险点",
            "讨论用户反馈和产品优化方向",
            "安排下周的工作计划和资源分配"
        );
        
        List<String> decisions = Arrays.asList(
            "确定采用新的前端组件库来提升开发效率",
            "调整数据库索引策略以优化查询性能",
            "增加自动化测试覆盖率到80%以上",
            "建立代码审查机制，所有代码必须经过同行评审",
            "设立每日站会制度，及时同步项目进展",
            "引入性能监控工具，实时跟踪系统性能指标"
        );
        
        List<String> actionItems = Arrays.asList(
            "张三：更新项目计划，调整里程碑时间节点 - 截止日期：本周五",
            "李四：完成技术方案评审，输出详细设计文档 - 截止日期：下周二",
            "王五：重构用户界面组件，提升用户体验 - 截止日期：下周四",
            "赵六：优化数据库查询，解决性能瓶颈问题 - 截止日期：下周三",
            "钱七：编写自动化测试用例，提高测试覆盖率 - 截止日期：下周五",
            "孙八：收集用户反馈，制定产品优化计划 - 截止日期：下周一",
            "周九：设计新版本UI界面，输出设计稿 - 截止日期：下周三",
            "全员：参加技术分享会，学习新技术和最佳实践 - 时间：下周五下午"
        );
        
        Path meetingFile = Paths.get(OUTPUT_DIR, "example7_meeting_minutes.docx");
        
        BoundesuWordsSDK.Utils.createMeetingMinutes(
            "项目开发周例会",
            "2024年1月15日 14:00-15:30",
            attendees,
            agenda,
            decisions,
            actionItems,
            meetingFile
        );
        
        System.out.println("会议纪要已创建: " + meetingFile.getFileName());
    }
    
    private static void example8_BatchCreateDocuments() throws IOException {
        System.out.println("\n=== 示例8: 批量创建文档 ===");
        
        List<BoundesuWordsSDK.DocumentInfo> documents = new ArrayList<>();
        
        // 创建多个不同类型的文档
        String[] documentTypes = {"用户手册", "开发指南", "部署文档", "测试报告", "维护手册"};
        String[] authors = {"技术写作团队", "开发团队", "运维团队", "测试团队", "支持团队"};
        
        for (int i = 0; i < documentTypes.length; i++) {
            String title = documentTypes[i];
            String author = authors[i];
            String content = String.format(
                "这是%s的详细内容。\n\n" +
                "本文档由%s编写，旨在为相关人员提供详细的指导和参考。\n\n" +
                "主要内容包括：\n" +
                "1. 概述和背景介绍\n" +
                "2. 详细的操作步骤\n" +
                "3. 常见问题和解决方案\n" +
                "4. 最佳实践和建议\n" +
                "5. 相关资源和参考链接\n\n" +
                "如有任何疑问，请联系%s获取支持。",
                title, author, author
            );
            
            Path filePath = Paths.get(OUTPUT_DIR, String.format("example8_batch_%d_%s.docx", 
                i + 1, title.replace(" ", "_")));
            
            documents.add(new BoundesuWordsSDK.DocumentInfo(title, author, content, filePath));
        }
        
        // 批量创建文档
        BoundesuWordsSDK.Utils.batchCreateDocuments(documents);
        
        System.out.println("批量创建完成，共创建 " + documents.size() + " 个文档:");
        for (BoundesuWordsSDK.DocumentInfo doc : documents) {
            System.out.println("- " + doc.getFilePath().getFileName());
        }
    }
    
    private static void example9_PerformanceMonitoring() throws Exception {
        System.out.println("\n=== 示例9: 性能监控 ===");
        
        // 监控文档创建性能
        byte[] documentBytes = BoundesuWordsSDK.monitorDocumentCreation(() -> {
            DocumentCreator creator = BoundesuWordsSDK.createDocumentCreator("poi");
            
            return creator.setTitle("性能测试文档")
                         .setAuthor("性能测试团队")
                         .addHeading("性能测试报告", 1)
                         .addParagraph("本文档用于测试文档创建的性能表现。")
                         .addHeading("测试环境", 2)
                         .addParagraph("操作系统：Windows 11")
                         .addParagraph("JVM版本：OpenJDK 17")
                         .addParagraph("内存配置：8GB")
                         .addHeading("测试结果", 2)
                         .addParagraph("文档创建速度：优秀")
                         .addParagraph("内存使用：正常")
                         .addParagraph("CPU占用：低")
                         .createDocumentAsBytes();
        });
        
        // 保存监控结果
        Path performanceFile = Paths.get(OUTPUT_DIR, "example9_performance_test.docx");
        Files.write(performanceFile, documentBytes);
        
        System.out.println("性能监控文档已创建: " + performanceFile.getFileName());
        System.out.println("文档大小: " + documentBytes.length + " 字节");
        
        // 开始性能监控
        PerformanceMonitor.OperationContext context = PerformanceMonitor.startOperation("批量文档创建测试");
        
        // 模拟批量操作
        for (int i = 1; i <= 5; i++) {
            DocumentCreator creator = BoundesuWordsSDK.createDocumentCreator("poi");
            creator.setTitle("批量测试文档 " + i)
                   .setAuthor("自动化测试")
                   .addHeading("文档 " + i, 1)
                   .addParagraph("这是第 " + i + " 个批量创建的测试文档。")
                   .createDocumentAsBytes();
        }
        
        PerformanceMonitor.endOperation(context);
        System.out.println("批量创建性能监控完成");
    }
    
    private static void example10_AdvancedDocumentGenerator() throws IOException {
        System.out.println("\n=== 示例10: 高级文档生成器 ===");
        
        // 创建高级文档生成器
        AdvancedDocumentGenerator generator = BoundesuWordsSDK.createAdvancedGenerator();
        
        // 配置文档
        AdvancedDocumentGenerator.DocumentConfig config = generator.getConfig();
        config.setGenerateCoverPage(true);
        config.setGenerateToc(true);
        config.setCompany("Boundesu科技有限公司");
        config.setDepartment("研发中心");
        config.setDateFormat("yyyy年MM月dd日");
        
        // 设置文档基本信息
        generator.setTitle("企业级文档管理系统设计方案");
        generator.setAuthor("系统架构师团队");
        
        // 添加章节内容
        generator.addChapter("项目背景", 1,
            "随着企业数字化转型的深入推进，传统的文档管理方式已经无法满足现代企业的需求。" +
            "企业需要一个统一、高效、安全的文档管理系统来支撑日常业务运营。\n\n" +
            "本项目旨在设计和实现一个企业级的文档管理系统，" +
            "提供文档的创建、存储、检索、协作、版本控制等全生命周期管理功能。");
            
        generator.addChapter("需求分析", 1,
            "通过深入调研和需求收集，我们识别出以下核心需求：\n\n" +
            "功能性需求：\n" +
            "• 支持多种文档格式的上传和预览\n" +
            "• 提供强大的全文搜索功能\n" +
            "• 支持多人协作编辑\n" +
            "• 完善的权限管理机制\n" +
            "• 版本控制和变更追踪\n\n" +
            "非功能性需求：\n" +
            "• 系统可用性达到99.9%\n" +
            "• 支持1000+并发用户\n" +
            "• 响应时间小于2秒\n" +
            "• 数据安全和隐私保护");
            
        generator.addChapter("系统设计", 2,
            "系统采用分层架构设计，主要包含以下层次：\n\n" +
            "1. 表现层（Presentation Layer）\n" +
            "   - Web前端界面\n" +
            "   - 移动端应用\n" +
            "   - API接口\n\n" +
            "2. 业务逻辑层（Business Logic Layer）\n" +
            "   - 文档管理服务\n" +
            "   - 用户管理服务\n" +
            "   - 权限控制服务\n" +
            "   - 搜索服务\n\n" +
            "3. 数据访问层（Data Access Layer）\n" +
            "   - 关系型数据库\n" +
            "   - 文档存储\n" +
            "   - 缓存系统\n" +
            "   - 搜索引擎");
            
        generator.addChapter("技术实现", 2,
            "技术栈选择：\n\n" +
            "前端技术：\n" +
            "• React 18 + TypeScript\n" +
            "• Ant Design Pro\n" +
            "• Monaco Editor（代码编辑器）\n" +
            "• PDF.js（文档预览）\n\n" +
            "后端技术：\n" +
            "• Spring Boot 3.0\n" +
            "• Spring Security（安全框架）\n" +
            "• MyBatis Plus（ORM框架）\n" +
            "• Apache Tika（文档解析）\n\n" +
            "存储技术：\n" +
            "• PostgreSQL（主数据库）\n" +
            "• MinIO（对象存储）\n" +
            "• Elasticsearch（全文搜索）\n" +
            "• Redis（缓存）");
            
        generator.addChapter("部署方案", 2,
            "部署架构采用云原生设计：\n\n" +
            "1. 容器化部署\n" +
            "   - 所有服务打包为Docker镜像\n" +
            "   - 使用Kubernetes进行容器编排\n" +
            "   - 支持水平扩展和自动故障恢复\n\n" +
            "2. 微服务架构\n" +
            "   - 服务拆分和独立部署\n" +
            "   - API网关统一入口\n" +
            "   - 服务注册与发现\n\n" +
            "3. 高可用设计\n" +
            "   - 多可用区部署\n" +
            "   - 负载均衡和故障转移\n" +
            "   - 数据备份和灾难恢复");
        
        // 生成文档
        Path advancedFile = Paths.get(OUTPUT_DIR, "example10_advanced_document.docx");
        generator.createDocument(advancedFile);
        
        System.out.println("高级文档已创建: " + advancedFile.getFileName());
        
        // 生成技术文档
        Path techDocFile = Paths.get(OUTPUT_DIR, "example10_technical_doc.docx");
        Map<String, String> sections = new HashMap<>();
        sections.put("接口规范", "定义RESTful API的设计原则和标准。");
        sections.put("数据格式", "统一使用JSON格式进行数据交换。");
        sections.put("错误处理", "标准化错误码和错误信息格式。");
        
        AdvancedDocumentGenerator techGenerator = new AdvancedDocumentGenerator();
        techGenerator.generateTechnicalDocument(
            "API接口设计规范",
            "技术团队",
            "本规范定义了系统API接口的设计标准和最佳实践。",
            sections,
            "遵循本规范可以确保API的一致性和可维护性。"
        );
        techGenerator.createDocument(techDocFile);
        
        System.out.println("技术规范文档已创建: " + techDocFile.getFileName());
    }
    
    private static void example11_TestStreamConverter() throws IOException, BoundesuWordsException {
        System.out.println("\n=== 示例11: 测试流转换器 ===");
        
        String htmlContent = 
            "<html>" +
            "<head><title>流转换测试文档</title></head>" +
            "<body>" +
            "<h1>流转换功能测试</h1>" +
            "<p>这是一个测试<strong>InputStream</strong>转换功能的示例文档。</p>" +
            "<h2>测试内容</h2>" +
            "<ul>" +
            "<li>HTML内容转换为Word文档</li>" +
            "<li>使用InputStream作为输入源</li>" +
            "<li>验证转换结果的正确性</li>" +
            "</ul>" +
            "<p>转换完成后，应该能够正常打开和查看生成的Word文档。</p>" +
            "</body>" +
            "</html>";
        
        // 将HTML内容转换为InputStream
        java.io.ByteArrayInputStream htmlInputStream = 
            new java.io.ByteArrayInputStream(htmlContent.getBytes("UTF-8"));
        
        Path streamFile = Paths.get(OUTPUT_DIR, "example11_stream_converter.docx");
        
        // 使用InputStream转换功能
        BoundesuWordsSDK.convertHtmlToDocx(htmlInputStream, streamFile);
        
        System.out.println("流转换文档已创建: " + streamFile.getFileName());
    }
    
    private static void example12_PageMarginsDemo() throws IOException, BoundesuWordsException {
        System.out.println("\n=== 示例12: 页边距设置功能演示 ===");
        
        String htmlContent = 
            "<html>" +
            "<head><title>页边距设置演示</title></head>" +
            "<body>" +
            "<h1>页边距设置功能演示</h1>" +
            "<p>这个文档演示了如何设置不同的页边距。本文档使用了自定义的页边距设置。</p>" +
            "<h2>页边距说明</h2>" +
            "<ul>" +
            "<li><strong>上边距：</strong> 0.5英寸 (36磅)</li>" +
            "<li><strong>下边距：</strong> 0.5英寸 (36磅)</li>" +
            "<li><strong>左边距：</strong> 1.5英寸 (108磅)</li>" +
            "<li><strong>右边距：</strong> 1.5英寸 (108磅)</li>" +
            "</ul>" +
            "<h2>应用场景</h2>" +
            "<p>页边距设置功能在以下场景中非常有用：</p>" +
            "<ol>" +
            "<li>制作需要装订的文档时，可以增加左边距</li>" +
            "<li>打印成本控制，通过调整边距来优化页面利用率</li>" +
            "<li>符合特定的文档格式要求</li>" +
            "<li>提升文档的视觉效果和可读性</li>" +
            "</ol>" +
            "<h2>技术实现</h2>" +
            "<p>页边距设置通过Apache POI的CTPageMar类实现，支持以下单位：</p>" +
            "<ul>" +
            "<li>磅 (Points) - 1磅 = 1/72英寸</li>" +
            "<li>缇 (Twips) - 1缇 = 1/20磅 = 1/1440英寸</li>" +
            "</ul>" +
            "<p>本SDK内部使用缇作为单位，并提供了便捷的磅到缇的转换。</p>" +
            "<h2>使用示例</h2>" +
            "<p>以下是设置页边距的代码示例：</p>" +
            "<pre>" +
            "// 创建页边距对象（单位：磅）\n" +
            "PageMargins margins = new PageMargins(36, 36, 108, 108);\n" +
            "\n" +
            "// 转换HTML并应用页边距\n" +
            "BoundesuWordsSDK.convertHtmlToDocx(htmlContent, outputPath, margins);" +
            "</pre>" +
            "<p>这样就可以创建一个具有自定义页边距的Word文档了。</p>" +
            "</body>" +
            "</html>";
        
        // 创建自定义页边距：上下0.5英寸，左右1.5英寸
        HtmlToDocxConverter.PageMargins customMargins = new HtmlToDocxConverter.PageMargins(36, 36, 108, 108);
        
        Path marginsFile = Paths.get(OUTPUT_DIR, "example12_custom_margins.docx");
        
        // 使用自定义页边距转换HTML
        BoundesuWordsSDK.convertHtmlToDocx(htmlContent, marginsFile, customMargins);
        
        System.out.println("自定义页边距文档已创建: " + marginsFile.getFileName());
        
        // 创建另一个使用默认页边距的文档进行对比
        String defaultHtmlContent = 
            "<html>" +
            "<head><title>默认页边距对比</title></head>" +
            "<body>" +
            "<h1>默认页边距文档</h1>" +
            "<p>这个文档使用默认的页边距设置（上下左右各1英寸）。</p>" +
            "<p>通过对比两个文档，您可以清楚地看到页边距设置的效果。</p>" +
            "<h2>默认页边距规格</h2>" +
            "<ul>" +
            "<li>上边距：1英寸 (72磅)</li>" +
            "<li>下边距：1英寸 (72磅)</li>" +
            "<li>左边距：1英寸 (72磅)</li>" +
            "<li>右边距：1英寸 (72磅)</li>" +
            "</ul>" +
            "<p>默认页边距是最常用的设置，适合大多数文档类型。</p>" +
            "</body>" +
            "</html>";
        
        Path defaultMarginsFile = Paths.get(OUTPUT_DIR, "example12_default_margins.docx");
        
        // 使用默认页边距（不传递margins参数）
        BoundesuWordsSDK.convertHtmlToDocx(defaultHtmlContent, defaultMarginsFile, null);
        
        System.out.println("默认页边距文档已创建: " + defaultMarginsFile.getFileName());
        System.out.println("提示：请打开两个文档进行对比，观察页边距的差异效果。");
    }
}