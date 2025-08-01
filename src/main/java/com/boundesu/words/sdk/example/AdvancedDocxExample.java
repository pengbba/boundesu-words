package com.boundesu.words.sdk.example;

import com.boundesu.words.sdk.model.BoundesuDocument;
import com.boundesu.words.sdk.service.BoundesuDocumentService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * 高级DOCX文档示例 - 展示更多功能
 */
public class AdvancedDocxExample {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy年MM月dd日");

    public static void main(String[] args) {
        try {
            BoundesuDocumentService documentService = new BoundesuDocumentService();

            // 创建输出目录
            Path outputDir = Paths.get("output");
            if (!outputDir.toFile().exists()) {
                outputDir.toFile().mkdirs();
            }

            // 示例1：创建商业报告
            System.out.println("创建商业报告...");
            BoundesuDocument businessReport = createBusinessReport(documentService);
            Path businessPath = outputDir.resolve("business-report.docx");
            documentService.saveDocumentToFile(businessReport, "docx", businessPath);
            System.out.println("商业报告已保存到: " + businessPath.toAbsolutePath());

            // 示例2：创建用户手册
            System.out.println("\n创建用户手册...");
            BoundesuDocument userManual = createUserManual(documentService);
            Path manualPath = outputDir.resolve("user-manual.docx");
            documentService.saveDocumentToFile(userManual, "docx", manualPath);
            System.out.println("用户手册已保存到: " + manualPath.toAbsolutePath());

            // 示例3：创建会议纪要
            System.out.println("\n创建会议纪要...");
            BoundesuDocument meetingMinutes = createMeetingMinutes(documentService);
            Path minutesPath = outputDir.resolve("meeting-minutes.docx");
            documentService.saveDocumentToFile(meetingMinutes, "docx", minutesPath);
            System.out.println("会议纪要已保存到: " + minutesPath.toAbsolutePath());

            System.out.println("\n所有高级DOCX文档保存完成！");

        } catch (IOException | InvalidFormatException e) {
            System.err.println("保存文档时出错: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 创建商业报告文档
     */
    private static BoundesuDocument createBusinessReport(BoundesuDocumentService service) {
        BoundesuDocument document = service.createDocument();

        // 设置文档属性
        document.setTitle("2024年第三季度业务报告");
        document.setAuthor("财务部");
        document.setDescription("公司第三季度业务表现分析报告");
        document.setKeywords(Arrays.asList("业务报告", "财务分析", "季度报告", "2024"));
        document.setLanguage("zh-CN");

        // 设置页眉页脚
        service.setHeader(document, "机密文件 - 2024年Q3业务报告");
        service.setFooter(document, "© 2024 公司名称 | 报告日期: " + LocalDateTime.now().format(DATE_FORMATTER));

        // 封面信息
        service.addHeading(document, "2024年第三季度业务报告", 1);
        service.addParagraph(document, "");
        service.addParagraph(document, "报告期间：2024年7月1日 - 2024年9月30日");
        service.addParagraph(document, "编制部门：财务部");
        service.addParagraph(document, "报告日期：" + LocalDateTime.now().format(DATE_FORMATTER));
        service.addParagraph(document, "");

        // 执行摘要
        service.addHeading(document, "执行摘要", 2);
        service.addParagraph(document, "本季度公司业务表现良好，各项关键指标均达到预期目标。营收同比增长15%，净利润率保持稳定。");
        service.addParagraph(document, "主要亮点包括：新产品线成功推出、市场份额扩大、运营效率提升。");

        // 财务概况
        service.addHeading(document, "财务概况", 2);
        service.addTable(document, 5, 4);
        String[][] financialData = {
                {"指标", "Q3 2024", "Q2 2024", "同比变化"},
                {"营业收入（万元）", "12,500", "10,800", "+15.7%"},
                {"净利润（万元）", "2,100", "1,850", "+13.5%"},
                {"毛利率", "35.2%", "34.8%", "+0.4%"},
                {"净利润率", "16.8%", "17.1%", "-0.3%"}
        };
        service.setTableData(document, 0, financialData);

        // 业务分析
        service.addHeading(document, "业务分析", 2);

        service.addHeading(document, "收入分析", 3);
        service.addParagraph(document, "第三季度营业收入达到12,500万元，较上季度增长15.7%。增长主要来源于：");
        service.addParagraph(document, "• 新产品线贡献收入2,800万元");
        service.addParagraph(document, "• 传统业务稳步增长8%");
        service.addParagraph(document, "• 海外市场拓展带来新增收入1,200万元");

        service.addHeading(document, "成本控制", 3);
        service.addParagraph(document, "通过优化供应链和提升运营效率，成本控制效果显著：");
        service.addParagraph(document, "• 原材料成本下降3.2%");
        service.addParagraph(document, "• 运营费用率控制在18.5%");
        service.addParagraph(document, "• 研发投入占收入比例保持在12%");

        // 市场表现
        service.addHeading(document, "市场表现", 2);
        service.addTable(document, 4, 3);
        String[][] marketData = {
                {"产品线", "市场份额", "增长率"},
                {"核心产品A", "25.3%", "+2.1%"},
                {"新产品B", "8.7%", "+8.7%"},
                {"传统产品C", "15.2%", "-1.3%"}
        };
        service.setTableData(document, 1, marketData);

        // 风险与机遇
        service.addHeading(document, "风险与机遇", 2);
        service.addParagraph(document, "面临的主要风险：");
        service.addParagraph(document, "• 原材料价格波动风险");
        service.addParagraph(document, "• 市场竞争加剧");
        service.addParagraph(document, "• 汇率变动影响");
        service.addParagraph(document, "");
        service.addParagraph(document, "发展机遇：");
        service.addParagraph(document, "• 新兴市场需求增长");
        service.addParagraph(document, "• 技术创新带来的差异化优势");
        service.addParagraph(document, "• 政策支持力度加大");

        // 下季度展望
        service.addHeading(document, "第四季度展望", 2);
        service.addParagraph(document, "预计第四季度将继续保持良好发展势头，主要目标包括：");
        service.addParagraph(document, "• 营收目标：13,800万元（同比增长18%）");
        service.addParagraph(document, "• 新产品推广：覆盖5个新城市");
        service.addParagraph(document, "• 成本优化：运营费用率控制在18%以内");

        return document;
    }

    /**
     * 创建用户手册文档
     */
    private static BoundesuDocument createUserManual(BoundesuDocumentService service) {
        BoundesuDocument document = service.createDocument();

        // 设置文档属性
        document.setTitle("Boundesu Words SDK 用户手册");
        document.setAuthor("产品团队");
        document.setDescription("Boundesu Words SDK完整使用指南");
        document.setKeywords(Arrays.asList("用户手册", "SDK", "使用指南", "API文档"));
        document.setLanguage("zh-CN");

        // 设置页眉页脚
        service.setHeader(document, "Boundesu Words SDK 用户手册 v2.0");
        service.setFooter(document, "技术支持: support@boundesu.com | 更新日期: " + LocalDateTime.now().format(DATE_FORMATTER));

        // 目录
        service.addHeading(document, "Boundesu Words SDK 用户手册", 1);
        service.addParagraph(document, "版本: 2.0");
        service.addParagraph(document, "发布日期: " + LocalDateTime.now().format(DATE_FORMATTER));
        service.addParagraph(document, "");

        // 快速开始
        service.addHeading(document, "快速开始", 2);
        service.addParagraph(document, "欢迎使用Boundesu Words SDK！本SDK提供了强大的文档处理能力，支持多种格式的文档创建和导出。");

        service.addHeading(document, "系统要求", 3);
        service.addTable(document, 4, 2);
        String[][] requirements = {
                {"项目", "要求"},
                {"Java版本", "17或更高版本"},
                {"内存", "最少512MB可用内存"},
                {"磁盘空间", "至少100MB可用空间"}
        };
        service.setTableData(document, 0, requirements);

        // 安装指南
        service.addHeading(document, "安装指南", 2);
        service.addParagraph(document, "1. 添加Maven依赖");
        service.addParagraph(document, "在您的pom.xml文件中添加以下依赖：");
        service.addParagraph(document, "");
        service.addParagraph(document, "<dependency>");
        service.addParagraph(document, "    <groupId>com.boundesu</groupId>");
        service.addParagraph(document, "    <artifactId>words-sdk</artifactId>");
        service.addParagraph(document, "    <version>2.0.0</version>");
        service.addParagraph(document, "</dependency>");
        service.addParagraph(document, "");

        service.addParagraph(document, "2. 导入必要的类");
        service.addParagraph(document, "import com.boundesu.words.sdk.service.BoundesuDocumentService;");
        service.addParagraph(document, "import com.boundesu.words.sdk.model.BoundesuDocument;");

        // API参考
        service.addHeading(document, "API参考", 2);
        service.addHeading(document, "核心类和方法", 3);
        service.addTable(document, 8, 3);
        String[][] apiData = {
                {"类/方法", "功能", "示例"},
                {"BoundesuDocumentService", "文档服务主类", "new BoundesuDocumentService()"},
                {"createDocument()", "创建新文档", "service.createDocument()"},
                {"addHeading()", "添加标题", "service.addHeading(doc, \"标题\", 1)"},
                {"addParagraph()", "添加段落", "service.addParagraph(doc, \"内容\")"},
                {"addTable()", "添加表格", "service.addTable(doc, 3, 4)"},
                {"setHeader()", "设置页眉", "service.setHeader(doc, \"页眉\")"},
                {"saveDocumentToFile()", "保存文档", "service.saveDocumentToFile(doc, \"docx\", path)"}
        };
        service.setTableData(document, 1, apiData);

        // 最佳实践
        service.addHeading(document, "最佳实践", 2);
        service.addParagraph(document, "为了获得最佳的使用体验，请遵循以下建议：");
        service.addParagraph(document, "");
        service.addParagraph(document, "1. 异常处理");
        service.addParagraph(document, "   始终使用try-catch块处理IOException和InvalidFormatException");
        service.addParagraph(document, "");
        service.addParagraph(document, "2. 资源管理");
        service.addParagraph(document, "   确保及时释放文档资源，避免内存泄漏");
        service.addParagraph(document, "");
        service.addParagraph(document, "3. 性能优化");
        service.addParagraph(document, "   对于大型文档，建议分批处理内容");
        service.addParagraph(document, "");
        service.addParagraph(document, "4. 文档结构");
        service.addParagraph(document, "   合理使用标题层级，保持文档结构清晰");

        // 故障排除
        service.addHeading(document, "故障排除", 2);
        service.addTable(document, 4, 3);
        String[][] troubleshooting = {
                {"问题", "可能原因", "解决方案"},
                {"文档保存失败", "文件路径不存在", "确保目标目录存在"},
                {"内存不足错误", "文档内容过大", "分批处理或增加堆内存"},
                {"格式错误", "不支持的字符", "检查文本编码格式"}
        };
        service.setTableData(document, 2, troubleshooting);

        // 联系支持
        service.addHeading(document, "技术支持", 2);
        service.addParagraph(document, "如果您在使用过程中遇到问题，请通过以下方式联系我们：");
        service.addParagraph(document, "• 邮箱: support@boundesu.com");
        service.addParagraph(document, "• 官网: https://www.boundesu.com");
        service.addParagraph(document, "• 文档: https://docs.boundesu.com");
        service.addParagraph(document, "• GitHub: https://github.com/boundesu/words-sdk");

        return document;
    }

    /**
     * 创建会议纪要文档
     */
    private static BoundesuDocument createMeetingMinutes(BoundesuDocumentService service) {
        BoundesuDocument document = service.createDocument();

        // 设置文档属性
        document.setTitle("产品规划会议纪要");
        document.setAuthor("会议秘书");
        document.setDescription("2024年第四季度产品规划会议记录");
        document.setKeywords(Arrays.asList("会议纪要", "产品规划", "决策记录"));
        document.setLanguage("zh-CN");

        // 设置页眉页脚
        service.setHeader(document, "会议纪要 - 产品规划讨论");
        service.setFooter(document, "会议日期: " + LocalDateTime.now().format(DATE_FORMATTER) + " | 页码: {PAGE}");

        // 会议基本信息
        service.addHeading(document, "产品规划会议纪要", 1);
        service.addParagraph(document, "");

        service.addTable(document, 6, 2);
        String[][] meetingInfo = {
                {"会议主题", "2024年第四季度产品规划"},
                {"会议时间", LocalDateTime.now().format(DATE_FORMATTER) + " 14:00-16:00"},
                {"会议地点", "总部会议室A"},
                {"主持人", "产品总监 - 李明"},
                {"记录人", "产品助理 - 王小红"},
                {"参会人员", "产品团队、技术团队、市场团队代表"}
        };
        service.setTableData(document, 0, meetingInfo);

        // 会议议程
        service.addHeading(document, "会议议程", 2);
        service.addParagraph(document, "1. Q3产品表现回顾");
        service.addParagraph(document, "2. Q4产品规划讨论");
        service.addParagraph(document, "3. 新功能优先级排序");
        service.addParagraph(document, "4. 资源分配计划");
        service.addParagraph(document, "5. 时间节点确认");

        // 讨论要点
        service.addHeading(document, "讨论要点", 2);

        service.addHeading(document, "Q3产品表现回顾", 3);
        service.addParagraph(document, "• 用户增长率达到25%，超出预期目标");
        service.addParagraph(document, "• 核心功能使用率提升15%");
        service.addParagraph(document, "• 用户反馈整体积极，满意度评分4.2/5.0");
        service.addParagraph(document, "• 发现的主要问题：移动端体验需要优化");

        service.addHeading(document, "Q4新功能规划", 3);
        service.addTable(document, 6, 4);
        String[][] features = {
                {"功能名称", "优先级", "预计工期", "负责团队"},
                {"DOCX导出增强", "高", "4周", "后端团队"},
                {"移动端优化", "高", "6周", "前端团队"},
                {"批量处理功能", "中", "3周", "后端团队"},
                {"用户权限管理", "中", "5周", "全栈团队"},
                {"API性能优化", "低", "2周", "后端团队"}
        };
        service.setTableData(document, 1, features);

        // 决策事项
        service.addHeading(document, "决策事项", 2);
        service.addParagraph(document, "经过充分讨论，会议达成以下决策：");
        service.addParagraph(document, "");
        service.addParagraph(document, "1. 优先开发DOCX导出增强功能");
        service.addParagraph(document, "   - 负责人：张工程师");
        service.addParagraph(document, "   - 完成时间：11月30日");
        service.addParagraph(document, "   - 验收标准：支持复杂表格和图片导出");
        service.addParagraph(document, "");
        service.addParagraph(document, "2. 启动移动端优化项目");
        service.addParagraph(document, "   - 负责人：前端团队");
        service.addParagraph(document, "   - 完成时间：12月15日");
        service.addParagraph(document, "   - 验收标准：移动端加载速度提升50%");
        service.addParagraph(document, "");
        service.addParagraph(document, "3. 推迟用户权限管理功能至下季度");
        service.addParagraph(document, "   - 原因：资源优先分配给高优先级功能");
        service.addParagraph(document, "   - 重新评估时间：12月中旬");

        // 行动计划
        service.addHeading(document, "行动计划", 2);
        service.addTable(document, 5, 4);
        String[][] actions = {
                {"任务", "负责人", "截止日期", "状态"},
                {"完成技术方案设计", "张工程师", "10月25日", "进行中"},
                {"UI/UX设计评审", "设计团队", "10月28日", "待开始"},
                {"开发环境准备", "运维团队", "10月30日", "待开始"},
                {"测试用例编写", "测试团队", "11月5日", "待开始"}
        };
        service.setTableData(document, 2, actions);

        // 下次会议
        service.addHeading(document, "下次会议安排", 2);
        service.addParagraph(document, "• 时间：下周三（10月30日）下午2:00");
        service.addParagraph(document, "• 地点：总部会议室B");
        service.addParagraph(document, "• 主题：技术方案评审");
        service.addParagraph(document, "• 参会人员：技术团队全员");

        // 会议总结
        service.addHeading(document, "会议总结", 2);
        service.addParagraph(document, "本次会议成功确定了Q4产品发展方向，明确了各项功能的优先级和时间安排。");
        service.addParagraph(document, "所有参会人员对决策达成一致，将按照既定计划推进各项工作。");
        service.addParagraph(document, "");
        service.addParagraph(document, "会议记录人：王小红");
        service.addParagraph(document, "记录时间：" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm")));

        return document;
    }
}