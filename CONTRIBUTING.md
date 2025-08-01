# 🤝 贡献指南

欢迎为 **Boundesu Words JDK17** 项目做出贡献！我们非常感谢您的参与和支持。

## 📋 目录

- [环境要求](#环境要求)
- [项目设置](#项目设置)
- [贡献类型](#贡献类型)
- [开发工作流](#开发工作流)
- [编码规范](#编码规范)
- [测试要求](#测试要求)
- [提交信息规范](#提交信息规范)
- [Pull Request 最佳实践](#pull-request-最佳实践)
- [代码审查流程](#代码审查流程)
- [发布流程](#发布流程)
- [沟通渠道](#沟通渠道)
- [开发者资源](#开发者资源)
- [贡献者指南](#贡献者指南)
- [贡献者认可](#贡献者认可)
- [行为准则](#行为准则)

## 🛠️ 环境要求

在开始贡献之前，请确保您的开发环境满足以下要求：

### 必需软件
- **Java**: JDK 8 或更高版本
- **Maven**: 3.6.0 或更高版本
- **Git**: 2.20 或更高版本
- **IDE**: 推荐使用 IntelliJ IDEA 或 Eclipse

### 推荐工具
- **代码格式化**: Google Java Format 插件
- **静态分析**: SonarLint 插件
- **版本控制**: Git 命令行或图形化工具
- **文档编辑**: Markdown 编辑器

### 验证环境
```bash
# 检查Java版本
java -version

# 检查Maven版本
mvn -version

# 检查Git版本
git --version
```

## 🚀 项目设置

### 1. Fork 仓库
1. 访问 [项目主页](https://github.com/your-org/boundesu-words-jdk17)
2. 点击右上角的 "Fork" 按钮
3. 选择您的GitHub账户作为Fork目标

### 2. 克隆仓库
```bash
# 克隆您的Fork仓库
git clone https://github.com/YOUR_USERNAME/boundesu-words-jdk17.git
cd boundesu-words-jdk17

# 添加上游仓库
git remote add upstream https://github.com/your-org/boundesu-words-jdk17.git

# 验证远程仓库配置
git remote -v
```

### 3. 安装依赖
```bash
# 安装项目依赖
mvn clean install

# 跳过测试的快速安装（仅用于首次设置）
mvn clean install -DskipTests
```

### 4. 运行测试
```bash
# 运行所有测试
mvn test

# 运行特定测试类
mvn test -Dtest=BoundesuWordsSDKTest

# 运行测试并生成覆盖率报告
mvn clean test jacoco:report
```

### 5. 验证构建
```bash
# 完整构建验证
mvn clean compile test package

# 验证代码风格
mvn checkstyle:check

# 运行静态分析
mvn spotbugs:check
```

## 🎯 贡献类型

我们欢迎以下类型的贡献：

### 🐛 Bug 报告
- 发现并报告软件缺陷
- 提供重现步骤和环境信息
- 建议可能的解决方案

### ✨ 功能请求
- 提出新功能或改进建议
- 描述使用场景和预期收益
- 讨论实现方案的可行性

### 💻 代码贡献
- 修复已知Bug
- 实现新功能
- 性能优化
- 代码重构
- 安全改进

### 📚 文档改进
- 完善API文档
- 更新使用指南
- 添加示例代码
- 翻译文档

### 🧪 测试增强
- 添加单元测试
- 改进集成测试
- 增加测试覆盖率
- 性能测试

## 🔄 开发工作流

### 分支策略
我们使用 **Git Flow** 分支模型：

- **main**: 主分支，包含稳定的发布版本
- **develop**: 开发分支，包含最新的开发代码
- **feature/***: 功能分支，用于开发新功能
- **bugfix/***: 修复分支，用于修复Bug
- **hotfix/***: 热修复分支，用于紧急修复
- **release/***: 发布分支，用于准备新版本发布

### 创建功能分支
```bash
# 确保在最新的develop分支
git checkout develop
git pull upstream develop

# 创建新的功能分支
git checkout -b feature/your-feature-name

# 或者创建Bug修复分支
git checkout -b bugfix/issue-number-description
```

### 分支命名规范
- **功能分支**: `feature/功能描述` 或 `feature/issue-编号-功能描述`
- **Bug修复**: `bugfix/issue-编号-问题描述`
- **热修复**: `hotfix/紧急问题描述`
- **文档**: `docs/文档类型-描述`
- **测试**: `test/测试类型-描述`

### 示例分支名称
```bash
feature/docx-export-enhancement
feature/issue-123-html-parser-improvement
bugfix/issue-456-memory-leak-fix
hotfix/critical-security-vulnerability
docs/api-documentation-update
test/integration-test-coverage
```

## 📝 编码规范

### Java 代码风格
我们遵循 **Google Java Style Guide** 和项目特定的编码规范：

#### 基本规范
```java
// ✅ 正确的类命名（PascalCase）
public class DocumentCreatorFactory {
    
    // ✅ 正确的常量命名（UPPER_SNAKE_CASE）
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final int MAX_RETRY_COUNT = 3;
    
    // ✅ 正确的变量命名（camelCase）
    private String documentTitle;
    private List<String> authorNames;
    
    // ✅ 正确的方法命名（camelCase）
    public DocumentCreator createDocumentCreator(String type) {
        // 实现逻辑
    }
    
    // ✅ 正确的参数命名
    public void processDocument(String fileName, CreationOptions options) {
        // 实现逻辑
    }
}
```

#### 注释规范
```java
/**
 * 文档创建器工厂类，用于创建不同类型的文档创建器实例。
 * 
 * <p>支持的创建器类型包括：
 * <ul>
 *   <li>POI直接创建器 - 用于精确控制文档结构</li>
 *   <li>HTML转换创建器 - 用于HTML内容转换</li>
 *   <li>XML转换创建器 - 用于XML结构化数据转换</li>
 * </ul>
 * 
 * <p>使用示例：
 * <pre>{@code
 * DocumentCreator creator = DocumentCreatorFactory.createDocumentCreator("poi");
 * creator.setTitle("文档标题").addParagraph("内容").createDocument(path);
 * }</pre>
 * 
 * @author Boundesu Development Team
 * @since 1.0.0
 * @see DocumentCreator
 * @see CreationType
 */
public class DocumentCreatorFactory {
    
    /**
     * 根据指定类型创建文档创建器实例。
     * 
     * @param type 创建器类型，支持 "poi"、"html"、"xml"
     * @return 对应类型的文档创建器实例
     * @throws IllegalArgumentException 当类型不支持时抛出
     * @throws DocumentCreationException 当创建器初始化失败时抛出
     */
    public static DocumentCreator createDocumentCreator(String type) {
        // 实现逻辑
    }
}
```

#### 异常处理
```java
// ✅ 正确的异常处理
public void saveDocument(Path filePath) throws DocumentSaveException {
    try {
        // 文档保存逻辑
        Files.write(filePath, documentContent);
        log.info("文档成功保存到: {}", filePath);
    } catch (IOException e) {
        log.error("保存文档失败: {}", filePath, e);
        throw new DocumentSaveException("无法保存文档到指定路径: " + filePath, e);
    } catch (SecurityException e) {
        log.error("没有权限保存文档: {}", filePath, e);
        throw new DocumentSaveException("没有权限保存文档: " + filePath, e);
    }
}

// ✅ 资源管理
public String readTemplate(String templateName) throws TemplateLoadException {
    try (InputStream inputStream = getClass().getResourceAsStream("/templates/" + templateName);
         BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
        
        return reader.lines().collect(Collectors.joining("\n"));
    } catch (IOException e) {
        throw new TemplateLoadException("无法加载模板: " + templateName, e);
    }
}
```

### 代码质量检查
```bash
# 运行代码风格检查
mvn checkstyle:check

# 运行静态分析
mvn spotbugs:check

# 运行依赖检查
mvn dependency:analyze

# 运行安全检查
mvn org.owasp:dependency-check-maven:check
```

## 🧪 测试要求

### 测试覆盖率要求
- **新代码**: 至少80%的测试覆盖率
- **关键业务逻辑**: 100%的测试覆盖率
- **公共API**: 100%的测试覆盖率

### 单元测试示例
```java
@Test
public class DocumentCreatorFactoryTest {
    
    @Test
    @DisplayName("应该成功创建POI类型的文档创建器")
    public void shouldCreatePoiDocumentCreator() {
        // Given
        String creatorType = "poi";
        
        // When
        DocumentCreator creator = DocumentCreatorFactory.createDocumentCreator(creatorType);
        
        // Then
        assertThat(creator).isNotNull();
        assertThat(creator).isInstanceOf(PoiDirectDocxCreator.class);
    }
    
    @Test
    @DisplayName("当传入不支持的类型时应该抛出异常")
    public void shouldThrowExceptionForUnsupportedType() {
        // Given
        String unsupportedType = "unsupported";
        
        // When & Then
        assertThatThrownBy(() -> DocumentCreatorFactory.createDocumentCreator(unsupportedType))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("不支持的创建器类型");
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"poi", "html", "xml"})
    @DisplayName("应该支持所有有效的创建器类型")
    public void shouldSupportAllValidCreatorTypes(String type) {
        // When
        DocumentCreator creator = DocumentCreatorFactory.createDocumentCreator(type);
        
        // Then
        assertThat(creator).isNotNull();
    }
}
```

### 集成测试示例
```java
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class DocumentCreationIntegrationTest {
    
    @TempDir
    Path tempDir;
    
    @Test
    @Order(1)
    @DisplayName("完整的文档创建流程测试")
    public void shouldCreateCompleteDocument() throws Exception {
        // Given
        Path outputPath = tempDir.resolve("integration_test.docx");
        DocumentCreator creator = DocumentCreatorFactory.createDocumentCreator("poi");
        
        // When
        creator.setTitle("集成测试文档")
               .setAuthor("测试作者")
               .addHeading("第一章", 1)
               .addParagraph("这是一个集成测试段落。")
               .addTable(new String[][]{
                   {"列1", "列2"},
                   {"数据1", "数据2"}
               })
               .createDocument(outputPath);
        
        // Then
        assertThat(Files.exists(outputPath)).isTrue();
        assertThat(Files.size(outputPath)).isGreaterThan(0);
        
        // 验证文档内容
        try (XWPFDocument document = new XWPFDocument(Files.newInputStream(outputPath))) {
            assertThat(document.getProperties().getCoreProperties().getTitle()).isEqualTo("集成测试文档");
            assertThat(document.getParagraphs()).hasSizeGreaterThan(0);
            assertThat(document.getTables()).hasSize(1);
        }
    }
}
```

### 运行测试
```bash
# 运行所有测试
mvn test

# 运行特定测试类
mvn test -Dtest=DocumentCreatorFactoryTest

# 运行特定测试方法
mvn test -Dtest=DocumentCreatorFactoryTest#shouldCreatePoiDocumentCreator

# 生成测试报告
mvn surefire-report:report

# 查看测试覆盖率
mvn jacoco:report
open target/site/jacoco/index.html
```

## 📝 提交信息规范

我们使用 **Conventional Commits** 规范来编写提交信息：

### 提交信息格式
```
<类型>[可选的作用域]: <描述>

[可选的正文]

[可选的脚注]
```

### 提交类型
- **feat**: 新功能
- **fix**: Bug修复
- **docs**: 文档更新
- **style**: 代码格式化（不影响功能）
- **refactor**: 代码重构
- **test**: 测试相关
- **chore**: 构建过程或辅助工具的变动
- **perf**: 性能优化
- **ci**: CI/CD相关
- **build**: 构建系统相关

### 提交信息示例
```bash
# 新功能
feat(docx): 添加XML转DOCX的转换功能

实现了XmlToDocxCreator类，支持将XML结构化数据转换为DOCX文档。
主要功能包括：
- 智能标签映射
- 多级标题处理
- 属性解析
- 自定义映射配置

Closes #123

# Bug修复
fix(memory): 修复大文档处理时的内存泄漏问题

- 优化文档对象的生命周期管理
- 添加自动资源释放机制
- 改进内存使用监控

Fixes #456

# 文档更新
docs(api): 更新DocumentCreator接口的API文档

- 添加详细的方法说明
- 补充使用示例
- 更新参数描述

# 代码重构
refactor(factory): 重构DocumentCreatorFactory的实现

- 使用策略模式替换if-else结构
- 提高代码可维护性
- 添加类型安全检查

# 测试
test(integration): 添加文档创建的集成测试

- 覆盖完整的文档创建流程
- 验证文档内容的正确性
- 测试异常情况处理
```

## 🔍 Pull Request 最佳实践

### PR 标题规范
- 使用清晰、描述性的标题
- 遵循提交信息规范
- 包含相关的Issue编号

### PR 描述模板
```markdown
## 📝 变更描述
简要描述本次PR的主要变更内容。

## 🎯 相关Issue
- Closes #123
- Fixes #456
- Related to #789

## 🔄 变更类型
- [ ] Bug修复
- [ ] 新功能
- [ ] 代码重构
- [ ] 文档更新
- [ ] 测试改进
- [ ] 性能优化

## 🧪 测试
- [ ] 添加了新的测试用例
- [ ] 所有现有测试通过
- [ ] 手动测试通过
- [ ] 测试覆盖率满足要求

## 📋 检查清单
- [ ] 代码遵循项目编码规范
- [ ] 添加了必要的文档
- [ ] 更新了CHANGELOG.md
- [ ] 测试覆盖率达到要求
- [ ] 没有引入新的警告或错误

## 📸 截图（如适用）
如果有UI变更，请提供截图。

## 📝 额外说明
其他需要说明的内容。
```

### PR 提交流程
```bash
# 1. 确保代码是最新的
git checkout develop
git pull upstream develop

# 2. 创建功能分支
git checkout -b feature/your-feature-name

# 3. 进行开发和测试
# ... 编写代码 ...

# 4. 提交变更
git add .
git commit -m "feat(scope): 描述变更内容"

# 5. 推送到您的Fork仓库
git push origin feature/your-feature-name

# 6. 创建Pull Request
# 在GitHub上创建PR，填写详细的描述信息
```

## 👥 代码审查流程

### 审查标准
- **代码质量**: 遵循编码规范，代码清晰易读
- **测试覆盖**: 充分的测试覆盖率
- **文档完整**: 必要的文档和注释
- **性能考虑**: 不引入性能问题
- **安全性**: 没有安全漏洞
- **兼容性**: 保持向后兼容

### 审查流程
1. **自动检查**: CI/CD流水线自动运行测试和检查
2. **同行审查**: 至少一名团队成员进行代码审查
3. **维护者审查**: 项目维护者进行最终审查
4. **测试验证**: 确保所有测试通过
5. **合并**: 审查通过后合并到目标分支

### 审查反馈处理
```bash
# 处理审查反馈
git checkout feature/your-feature-name

# 进行必要的修改
# ... 修改代码 ...

# 提交修改
git add .
git commit -m "fix: 根据审查反馈修复问题"

# 推送更新
git push origin feature/your-feature-name
```

## 🚀 发布流程

### 版本号规范
我们遵循 [语义化版本](https://semver.org/lang/zh-CN/) 规范：
- **主版本号**: 不兼容的API修改
- **次版本号**: 向下兼容的功能性新增
- **修订号**: 向下兼容的问题修正

### 发布步骤
1. **更新CHANGELOG.md**: 记录所有变更
2. **创建发布分支**: `release/v1.x.x`
3. **版本号更新**: 更新pom.xml中的版本号
4. **创建标签**: `git tag v1.x.x`
5. **发布到Maven**: 发布到Maven中央仓库

### 发布命令
```bash
# 创建发布分支
git checkout -b release/v1.1.0 develop

# 更新版本号
mvn versions:set -DnewVersion=1.1.0

# 提交版本更新
git commit -am "chore: 发布版本1.1.0"

# 合并到main分支
git checkout main
git merge --no-ff release/v1.1.0

# 创建标签
git tag -a v1.1.0 -m "发布版本1.1.0"

# 推送到远程仓库
git push origin main --tags
```

## 💬 沟通渠道

### GitHub
- **Issues**: [报告Bug和功能请求](https://github.com/your-org/boundesu-words-jdk17/issues)
- **Discussions**: [一般讨论和问答](https://github.com/your-org/boundesu-words-jdk17/discussions)
- **Pull Requests**: [代码贡献](https://github.com/your-org/boundesu-words-jdk17/pulls)

### 邮件
- **技术支持**: support@boundesu.com
- **安全问题**: security@boundesu.com
- **商务合作**: business@boundesu.com

### 社区
- **Stack Overflow**: 使用标签 `boundesu-words`
- **微信群**: 扫描README中的二维码加入
- **QQ群**: 123456789

## 📚 开发者资源

### 文档
- **项目Wiki**: [详细的开发文档](https://github.com/your-org/boundesu-words-jdk17/wiki)
- **API文档**: [在线API参考](https://docs.boundesu.com/api)
- **架构设计**: [系统架构文档](https://docs.boundesu.com/architecture)

### 工具和模板
- **IDE配置**: [IntelliJ IDEA配置文件](/.idea)
- **代码模板**: [代码生成模板](/templates)
- **Git钩子**: [预提交检查脚本](/.githooks)

### 学习资源
- **Apache POI文档**: [官方文档](https://poi.apache.org/)
- **Spring Boot指南**: [官方指南](https://spring.io/guides)
- **TestNG教程**: [测试框架文档](https://testng.org/doc/)

## 🎯 贡献者指南

### 新手贡献者
- **Good First Issues**: 查找标记为 `good first issue` 的问题
- **文档改进**: 从文档更新开始贡献
- **测试用例**: 添加测试用例是很好的起点
- **Bug报告**: 报告发现的问题

### 高级贡献者
- **架构讨论**: 参与系统架构的设计讨论
- **代码审查**: 帮助审查其他贡献者的代码
- **功能设计**: 设计和实现新功能
- **性能优化**: 改进系统性能

### 长期贡献者
- **维护者角色**: 有机会成为项目维护者
- **技术决策**: 参与重要技术决策
- **社区建设**: 帮助建设开发者社区
- **导师角色**: 指导新的贡献者

## 🏆 贡献者认可

### 认可方式
- **README展示**: 在项目README中展示贡献者
- **发布说明**: 在版本发布说明中感谢贡献者
- **GitHub图表**: 在GitHub贡献者图表中显示
- **特殊徽章**: 为重要贡献者提供特殊徽章

### 贡献者等级
- **贡献者**: 提交过代码或文档的开发者
- **活跃贡献者**: 持续贡献的开发者
- **核心贡献者**: 对项目有重大贡献的开发者
- **维护者**: 拥有仓库写权限的核心团队成员

## 📜 行为准则

### 我们的承诺
我们致力于为每个人提供友好、安全和包容的环境，无论：
- 年龄、身体大小、残疾、种族、性别认同和表达
- 经验水平、教育背景、社会经济地位、国籍
- 个人外貌、种族、宗教或性取向

### 我们的标准
**积极行为包括**：
- 使用友好和包容的语言
- 尊重不同的观点和经验
- 优雅地接受建设性批评
- 关注对社区最有利的事情
- 对其他社区成员表示同理心

**不可接受的行为包括**：
- 使用性化的语言或图像
- 恶意评论、侮辱或人身攻击
- 公开或私下骚扰
- 未经许可发布他人的私人信息
- 其他在专业环境中不当的行为

### 执行
项目维护者有权利和责任删除、编辑或拒绝不符合行为准则的评论、提交、代码、wiki编辑、问题和其他贡献。

## 📄 许可证

通过贡献代码，您同意您的贡献将在与项目相同的 [MIT许可证](LICENSE) 下授权。

---

## 🙏 感谢

感谢您考虑为 Boundesu Words JDK17 项目做出贡献！您的参与使这个项目变得更好。

如果您有任何问题或需要帮助，请随时通过上述沟通渠道联系我们。

---

<div align="center">

**让我们一起构建更好的文档处理工具！**

[🏠 返回主页](README.md) | 
[📚 查看文档](README.md) | 
[🐛 报告问题](https://github.com/your-org/boundesu-words-jdk17/issues) | 
[💬 参与讨论](https://github.com/your-org/boundesu-words-jdk17/discussions)

</div>