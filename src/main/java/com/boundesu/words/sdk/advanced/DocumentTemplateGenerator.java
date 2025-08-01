package com.boundesu.words.sdk.advanced;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 文档模板生成器
 * 提供各种预定义的文档模板
 *
 * @author Boundesu Team
 * @version 1.0.0
 */
public class DocumentTemplateGenerator {

    /**
     * 项目信息
     */
    public static class ProjectInfo {
        private String name;
        private String version;
        private String description;
        private String manager;
        private String startDate;
        private String endDate;
        private List<String> teamMembers;
        private List<String> technologies;

        public ProjectInfo() {
            this.teamMembers = new ArrayList<>();
            this.technologies = new ArrayList<>();
        }

        // Getters and Setters
        public String getName() {
            return name;
        }

        public ProjectInfo setName(String name) {
            this.name = name;
            return this;
        }

        public String getVersion() {
            return version;
        }

        public ProjectInfo setVersion(String version) {
            this.version = version;
            return this;
        }

        public String getDescription() {
            return description;
        }

        public ProjectInfo setDescription(String description) {
            this.description = description;
            return this;
        }

        public String getManager() {
            return manager;
        }

        public ProjectInfo setManager(String manager) {
            this.manager = manager;
            return this;
        }

        public String getStartDate() {
            return startDate;
        }

        public ProjectInfo setStartDate(String startDate) {
            this.startDate = startDate;
            return this;
        }

        public String getEndDate() {
            return endDate;
        }

        public ProjectInfo setEndDate(String endDate) {
            this.endDate = endDate;
            return this;
        }

        public List<String> getTeamMembers() {
            return teamMembers;
        }

        public ProjectInfo setTeamMembers(List<String> teamMembers) {
            this.teamMembers = teamMembers;
            return this;
        }

        public ProjectInfo addTeamMember(String member) {
            this.teamMembers.add(member);
            return this;
        }

        public List<String> getTechnologies() {
            return technologies;
        }

        public ProjectInfo setTechnologies(List<String> technologies) {
            this.technologies = technologies;
            return this;
        }

        public ProjectInfo addTechnology(String technology) {
            this.technologies.add(technology);
            return this;
        }
    }

    /**
     * 生成项目计划书
     *
     * @param projectInfo 项目信息
     * @param outputPath  输出路径
     * @throws IOException 文件操作异常
     */
    public static void generateProjectPlan(ProjectInfo projectInfo, Path outputPath) throws IOException {
        AdvancedDocumentGenerator generator = new AdvancedDocumentGenerator();

        // 配置文档
        generator.getConfig()
                .setGenerateToc(true)
                .setGenerateCoverPage(true)
                .setCompany("Boundesu")
                .setDepartment("技术部");

        String title = projectInfo.getName() + " 项目计划书";
        generator.setTitle(title).setAuthor(projectInfo.getManager());

        // 生成封面
        generator.generateCoverPage(title, "项目计划书 v" + projectInfo.getVersion(), projectInfo.getManager());

        // 项目概述
        String overview = String.format(
                "项目名称：%s\n" +
                        "项目版本：%s\n" +
                        "项目经理：%s\n" +
                        "计划开始时间：%s\n" +
                        "计划结束时间：%s\n\n" +
                        "项目描述：\n%s",
                projectInfo.getName(),
                projectInfo.getVersion(),
                projectInfo.getManager(),
                projectInfo.getStartDate(),
                projectInfo.getEndDate(),
                projectInfo.getDescription()
        );
        generator.addChapter("1. 项目概述", 1, overview);

        // 团队成员
        if (!projectInfo.getTeamMembers().isEmpty()) {
            StringBuilder teamInfo = new StringBuilder("项目团队成员：\n");
            for (int i = 0; i < projectInfo.getTeamMembers().size(); i++) {
                teamInfo.append(String.format("%d. %s\n", i + 1, projectInfo.getTeamMembers().get(i)));
            }
            generator.addChapter("2. 项目团队", 1, teamInfo.toString());
        }

        // 技术栈
        if (!projectInfo.getTechnologies().isEmpty()) {
            StringBuilder techInfo = new StringBuilder("使用的技术栈：\n");
            for (int i = 0; i < projectInfo.getTechnologies().size(); i++) {
                techInfo.append(String.format("%d. %s\n", i + 1, projectInfo.getTechnologies().get(i)));
            }
            generator.addChapter("3. 技术栈", 1, techInfo.toString());
        }

        // 项目阶段
        Map<String, String> phases = new LinkedHashMap<>();
        phases.put("4. 需求分析阶段", "详细分析项目需求，确定功能范围和技术要求。");
        phases.put("5. 设计阶段", "进行系统架构设计、数据库设计和界面设计。");
        phases.put("6. 开发阶段", "按照设计文档进行编码实现，包括前端和后端开发。");
        phases.put("7. 测试阶段", "进行单元测试、集成测试和系统测试，确保质量。");
        phases.put("8. 部署阶段", "将系统部署到生产环境，进行上线准备。");
        phases.put("9. 维护阶段", "系统上线后的维护和优化工作。");

        generator.addChapters(phases);

        // 风险评估
        generator.addChapter("10. 风险评估", 1,
                "主要风险点：\n" +
                        "1. 技术风险：新技术学习成本\n" +
                        "2. 进度风险：开发进度可能延期\n" +
                        "3. 资源风险：人力资源不足\n" +
                        "4. 质量风险：测试不充分\n\n" +
                        "风险应对措施：\n" +
                        "1. 提前进行技术调研和培训\n" +
                        "2. 制定详细的进度计划和里程碑\n" +
                        "3. 合理分配人力资源\n" +
                        "4. 建立完善的测试体系");

        // 生成目录
        generator.generateTableOfContents();

        // 创建文档
        generator.createDocument(outputPath);
    }

    /**
     * 生成API文档
     *
     * @param apiName    API名称
     * @param version    版本号
     * @param baseUrl    基础URL
     * @param endpoints  API端点列表
     * @param outputPath 输出路径
     * @throws IOException 文件操作异常
     */
    public static void generateApiDocument(String apiName, String version, String baseUrl,
                                           Map<String, String> endpoints, Path outputPath) throws IOException {
        AdvancedDocumentGenerator generator = new AdvancedDocumentGenerator();

        String title = apiName + " API文档";
        generator.setTitle(title).setAuthor("API团队");

        // 生成封面
        generator.generateCoverPage(title, "版本 " + version, "API团队");

        // API概述
        String overview = String.format(
                "API名称：%s\n" +
                        "版本：%s\n" +
                        "基础URL：%s\n" +
                        "文档生成时间：%s\n\n" +
                        "本文档描述了%s的所有API接口，包括请求方法、参数说明和响应格式。",
                apiName,
                version,
                baseUrl,
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                apiName
        );
        generator.addChapter("1. API概述", 1, overview);

        // 认证说明
        generator.addChapter("2. 认证方式", 1,
                "本API使用Token认证方式，请在请求头中添加：\n" +
                        "Authorization: Bearer {your_token}\n\n" +
                        "获取Token的方式请联系管理员。");

        // API端点
        int chapterNum = 3;
        if (endpoints != null && !endpoints.isEmpty()) {
            for (Map.Entry<String, String> endpoint : endpoints.entrySet()) {
                generator.addChapter(chapterNum + ". " + endpoint.getKey(), 1, endpoint.getValue());
                chapterNum++;
            }
        } else {
            generator.addChapter(chapterNum + ". API端点", 1, "暂无API端点信息。");
            chapterNum++;
        }

        // 错误码说明
        generator.addChapter(chapterNum + ". 错误码说明", 1,
                "常见错误码：\n" +
                        "200 - 成功\n" +
                        "400 - 请求参数错误\n" +
                        "401 - 未授权\n" +
                        "403 - 禁止访问\n" +
                        "404 - 资源不存在\n" +
                        "500 - 服务器内部错误");

        // 生成目录
        generator.generateTableOfContents();

        // 创建文档
        generator.createDocument(outputPath);
    }

    /**
     * 生成用户手册
     *
     * @param productName 产品名称
     * @param version     版本号
     * @param features    功能列表
     * @param outputPath  输出路径
     * @throws IOException 文件操作异常
     */
    public static void generateUserManual(String productName, String version,
                                          Map<String, String> features, Path outputPath) throws IOException {
        AdvancedDocumentGenerator generator = new AdvancedDocumentGenerator();

        String title = productName + " 用户手册";
        generator.setTitle(title).setAuthor("产品团队");

        // 生成封面
        generator.generateCoverPage(title, "版本 " + version, "产品团队");

        // 产品介绍
        generator.addChapter("1. 产品介绍", 1,
                String.format("欢迎使用%s！\n\n" +
                                "版本：%s\n" +
                                "发布日期：%s\n\n" +
                                "%s是一款功能强大的软件产品，本手册将帮助您快速上手并充分利用产品的各项功能。",
                        productName,
                        version,
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日")),
                        productName));

        // 快速开始
        generator.addChapter("2. 快速开始", 1,
                "系统要求：\n" +
                        "- 操作系统：Windows 10/macOS 10.15/Linux\n" +
                        "- 内存：至少4GB RAM\n" +
                        "- 硬盘空间：至少1GB可用空间\n\n" +
                        "安装步骤：\n" +
                        "1. 下载安装包\n" +
                        "2. 运行安装程序\n" +
                        "3. 按照向导完成安装\n" +
                        "4. 启动应用程序");

        // 功能说明
        int chapterNum = 3;
        if (features != null && !features.isEmpty()) {
            for (Map.Entry<String, String> feature : features.entrySet()) {
                generator.addChapter(chapterNum + ". " + feature.getKey(), 1, feature.getValue());
                chapterNum++;
            }
        } else {
            generator.addChapter(chapterNum + ". 功能说明", 1, "暂无功能说明信息。");
            chapterNum++;
        }

        // 常见问题
        generator.addChapter(chapterNum + ". 常见问题", 1,
                "Q: 如何重置密码？\n" +
                        "A: 在登录页面点击\"忘记密码\"，按照提示操作。\n\n" +
                        "Q: 如何联系技术支持？\n" +
                        "A: 发送邮件至 support@boundesu.com 或拨打客服电话。\n\n" +
                        "Q: 如何更新软件？\n" +
                        "A: 软件会自动检查更新，也可以在设置中手动检查。");

        // 生成目录
        generator.generateTableOfContents();

        // 创建文档
        generator.createDocument(outputPath);
    }

    /**
     * 生成测试报告
     *
     * @param projectName 项目名称
     * @param testType    测试类型
     * @param testResults 测试结果
     * @param outputPath  输出路径
     * @throws IOException 文件操作异常
     */
    public static void generateTestReport(String projectName, String testType,
                                          Map<String, Object> testResults, Path outputPath) throws IOException {
        AdvancedDocumentGenerator generator = new AdvancedDocumentGenerator();

        String title = projectName + " " + testType + "测试报告";
        generator.setTitle(title).setAuthor("测试团队");

        // 生成封面
        generator.generateCoverPage(title, "测试报告", "测试团队");

        // 测试概述
        generator.addChapter("1. 测试概述", 1,
                String.format("项目名称：%s\n" +
                                "测试类型：%s\n" +
                                "测试时间：%s\n" +
                                "测试环境：生产环境\n" +
                                "测试工具：自动化测试框架",
                        projectName,
                        testType,
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));

        // 测试结果
        StringBuilder results = new StringBuilder();
        if (testResults != null && !testResults.isEmpty()) {
            for (Map.Entry<String, Object> result : testResults.entrySet()) {
                results.append(String.format("%s：%s\n", result.getKey(), result.getValue()));
            }
        } else {
            results.append("暂无测试结果数据。\n");
        }
        generator.addChapter("2. 测试结果", 1, results.toString());

        // 测试结论
        generator.addChapter("3. 测试结论", 1,
                "根据测试结果分析：\n" +
                        "1. 所有核心功能测试通过\n" +
                        "2. 性能指标符合预期\n" +
                        "3. 安全测试未发现重大漏洞\n" +
                        "4. 兼容性测试通过\n\n" +
                        "建议：\n" +
                        "1. 继续监控系统性能\n" +
                        "2. 定期进行安全扫描\n" +
                        "3. 优化部分功能的响应时间");

        // 生成目录
        generator.generateTableOfContents();

        // 创建文档
        generator.createDocument(outputPath);
    }
}