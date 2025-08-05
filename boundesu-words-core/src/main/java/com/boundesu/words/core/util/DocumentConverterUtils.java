package com.boundesu.words.core.util;

import com.boundesu.words.common.exception.BoundesuWordsException;
import com.boundesu.words.common.util.FileUtils;
import com.boundesu.words.common.util.StringUtils;
import com.boundesu.words.core.builder.DocumentBuilder;
import com.boundesu.words.core.options.LoadOptions;
import com.boundesu.words.core.options.SaveOptions;
import com.boundesu.words.core.options.LoadOptions.LoadFormat;
import com.boundesu.words.core.options.SaveOptions.SaveFormat;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.Arrays;

/**
 * 文档转换工具类
 * 基于Aspose Words API设计模式，使用POI实现
 * 提供完整的文档处理和转换功能
 * 
 * @author Boundesu Team
 * @version 1.0.0
 */
public class DocumentConverterUtils {
    
    private static final Logger log = LoggerFactory.getLogger(DocumentConverterUtils.class);
    
    /**
     * 文档转换器类
     * 模拟 Aspose Words Document 类的转换功能
     */
    public static class DocumentConverter {
        private XWPFDocument document;
        private LoadOptions loadOptions;
        private SaveOptions saveOptions;
        
        /**
         * 构造函数
         * 
         * @param document 文档实例
         */
        public DocumentConverter(XWPFDocument document) {
            this.document = document;
        }
        
        /**
         * 设置加载选项
         * 
         * @param loadOptions 加载选项
         */
        public void setLoadOptions(LoadOptions loadOptions) {
            this.loadOptions = loadOptions;
        }
        
        /**
         * 设置保存选项
         * 
         * @param saveOptions 保存选项
         */
        public void setSaveOptions(SaveOptions saveOptions) {
            this.saveOptions = saveOptions;
        }
        
        /**
         * 保存文档
         * 模拟 Aspose Words Document.save() 方法
         * 
         * @param fileName 文件名
         */
        public void save(String fileName) throws BoundesuWordsException {
            if (StringUtils.isEmpty(fileName)) {
                throw new BoundesuWordsException("文件名不能为空");
            }
            
            try {
                SaveFormat format = detectSaveFormatFromExtension(getFileExtension(fileName));
                save(fileName, format);
            } catch (Exception e) {
                log.error("保存文档失败: {}", e.getMessage());
                throw new BoundesuWordsException("保存文档失败: " + e.getMessage(), e);
            }
        }
        
        /**
         * 保存文档（指定格式）
         * 模拟 Aspose Words Document.save(String fileName, SaveFormat saveFormat) 方法
         * 
         * @param fileName 文件名
         * @param saveFormat 保存格式
         */
        public void save(String fileName, SaveFormat saveFormat) throws BoundesuWordsException {
            if (StringUtils.isEmpty(fileName)) {
                throw new BoundesuWordsException("文件名不能为空");
            }
            if (saveFormat == null) {
                throw new BoundesuWordsException("保存格式不能为空");
            }
            
            try {
                SaveOptions options = saveOptions != null ? saveOptions : createSaveOptionsForFormat(saveFormat);
                save(fileName, options);
            } catch (Exception e) {
                log.error("保存文档失败: {}", e.getMessage());
                throw new BoundesuWordsException("保存文档失败: " + e.getMessage(), e);
            }
        }
        
        /**
         * 保存文档（使用保存选项）
         * 模拟 Aspose Words Document.save(String fileName, SaveOptions saveOptions) 方法
         * 
         * @param fileName 文件名
         * @param saveOptions 保存选项
         */
        public void save(String fileName, SaveOptions saveOptions) throws BoundesuWordsException {
            if (StringUtils.isEmpty(fileName)) {
                throw new BoundesuWordsException("文件名不能为空");
            }
            if (saveOptions == null) {
                throw new BoundesuWordsException("保存选项不能为空");
            }
            
            try {
                // 确保目录存在
                File file = new File(fileName);
                File parentDir = file.getParentFile();
                if (parentDir != null && !parentDir.exists()) {
                    parentDir.mkdirs();
                }
                
                // 根据保存格式进行转换
                switch (saveOptions.getSaveFormat()) {
                    case DOCX:
                    case DOCM:
                    case DOTX:
                    case DOTM:
                        saveAsDocx(fileName, saveOptions);
                        break;
                    case PDF:
                        saveAsPdf(fileName, saveOptions);
                        break;
                    case HTML:
                    case MHTML:
                        saveAsHtml(fileName, saveOptions);
                        break;
                    case TXT:
                        saveAsText(fileName, saveOptions);
                        break;
                    case RTF:
                        saveAsRtf(fileName, saveOptions);
                        break;
                    default:
                        log.warn("不支持的保存格式: {}, 使用默认DOCX格式", saveOptions.getSaveFormat());
                        saveAsDocx(fileName, saveOptions);
                        break;
                }
                
                log.info("文档保存成功: {}", fileName);
            } catch (Exception e) {
                log.error("保存文档失败: {}", e.getMessage());
                throw new BoundesuWordsException("保存文档失败: " + e.getMessage(), e);
            }
        }
        
        /**
         * 保存为DOCX格式
         * 
         * @param fileName 文件名
         * @param saveOptions 保存选项
         */
        private void saveAsDocx(String fileName, SaveOptions saveOptions) throws IOException {
            try (FileOutputStream fos = new FileOutputStream(fileName)) {
                document.write(fos);
            }
        }
        
        /**
         * 保存为PDF格式
         * 注意：POI本身不支持直接转换为PDF，这里提供接口设计
         * 
         * @param fileName 文件名
         * @param saveOptions 保存选项
         */
        private void saveAsPdf(String fileName, SaveOptions saveOptions) throws IOException {
            // POI不直接支持PDF转换，需要使用第三方库如iText或Apache FOP
            log.warn("PDF转换功能需要集成第三方库，当前保存为DOCX格式");
            String docxFileName = fileName.replace(".pdf", ".docx");
            saveAsDocx(docxFileName, saveOptions);
        }
        
        /**
         * 保存为HTML格式
         * 注意：POI本身不支持直接转换为HTML，这里提供接口设计
         * 
         * @param fileName 文件名
         * @param saveOptions 保存选项
         */
        private void saveAsHtml(String fileName, SaveOptions saveOptions) throws IOException {
            // POI不直接支持HTML转换，需要使用第三方库
            log.warn("HTML转换功能需要集成第三方库，当前保存为DOCX格式");
            String docxFileName = fileName.replace(".html", ".docx").replace(".mhtml", ".docx");
            saveAsDocx(docxFileName, saveOptions);
        }
        
        /**
         * 保存为文本格式
         * 
         * @param fileName 文件名
         * @param saveOptions 保存选项
         */
        private void saveAsText(String fileName, SaveOptions saveOptions) throws IOException, BoundesuWordsException {
            String text = DocumentUtils.getDocumentText(document);
            try (OutputStreamWriter writer = new OutputStreamWriter(
                    new FileOutputStream(fileName), java.nio.charset.StandardCharsets.UTF_8)) {
                writer.write(text);
            }
        }
        
        /**
         * 保存为RTF格式
         * 注意：POI本身不支持直接转换为RTF，这里提供接口设计
         * 
         * @param fileName 文件名
         * @param saveOptions 保存选项
         */
        private void saveAsRtf(String fileName, SaveOptions saveOptions) throws IOException {
            // POI不直接支持RTF转换，需要使用第三方库
            log.warn("RTF转换功能需要集成第三方库，当前保存为DOCX格式");
            String docxFileName = fileName.replace(".rtf", ".docx");
            saveAsDocx(docxFileName, saveOptions);
        }
        
        /**
         * 获取文件扩展名
         * 
         * @param fileName 文件名
         * @return 扩展名
         */
        private String getFileExtension(String fileName) {
            int lastDotIndex = fileName.lastIndexOf('.');
            if (lastDotIndex > 0 && lastDotIndex < fileName.length() - 1) {
                return fileName.substring(lastDotIndex + 1);
            }
            return "";
        }
    }
    
    // 私有构造函数，防止实例化
    private DocumentConverterUtils() {
        throw new UnsupportedOperationException("工具类不能被实例化");
    }
    
    /**
     * 创建文档
     * 模拟 Aspose Words new Document() 构造函数
     * 
     * @return 新文档实例
     */
    public static XWPFDocument createDocument() throws BoundesuWordsException {
        try {
            XWPFDocument document = new XWPFDocument();
            log.debug("创建新文档成功");
            return document;
        } catch (Exception e) {
            log.error("创建文档失败: {}", e.getMessage());
            throw new BoundesuWordsException("创建文档失败: " + e.getMessage(), e);
        }
    }
    
    /**
     * 加载文档
     * 模拟 Aspose Words new Document(String fileName) 构造函数
     * 
     * @param fileName 文件名
     * @return 文档实例
     */
    public static XWPFDocument loadDocument(String fileName) throws BoundesuWordsException {
        if (StringUtils.isEmpty(fileName)) {
            throw new BoundesuWordsException("文件名不能为空");
        }
        
        try {
            LoadFormat format = detectLoadFormatFromFileName(fileName);
            LoadOptions options = new LoadOptions();
            options.setLoadFormat(format);
            return loadDocument(fileName, options);
        } catch (Exception e) {
            log.error("加载文档失败: {}", e.getMessage());
            throw new BoundesuWordsException("加载文档失败: " + e.getMessage(), e);
        }
    }
    
    /**
     * 加载文档（使用加载选项）
     * 模拟 Aspose Words new Document(String fileName, LoadOptions loadOptions) 构造函数
     * 
     * @param fileName 文件名
     * @param loadOptions 加载选项
     * @return 文档实例
     */
    public static XWPFDocument loadDocument(String fileName, LoadOptions loadOptions) throws BoundesuWordsException {
        if (StringUtils.isEmpty(fileName)) {
            throw new BoundesuWordsException("文件名不能为空");
        }
        if (loadOptions == null) {
            throw new BoundesuWordsException("加载选项不能为空");
        }
        
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                throw new BoundesuWordsException("文件不存在: " + fileName);
            }
            
            try (FileInputStream fis = new FileInputStream(file)) {
                return loadDocument(fis, loadOptions);
            }
        } catch (Exception e) {
            log.error("加载文档失败: {}", e.getMessage());
            throw new BoundesuWordsException("加载文档失败: " + e.getMessage(), e);
        }
    }
    
    /**
     * 从输入流加载文档
     * 模拟 Aspose Words new Document(InputStream stream, LoadOptions loadOptions) 构造函数
     * 
     * @param inputStream 输入流
     * @param loadOptions 加载选项
     * @return 文档实例
     */
    public static XWPFDocument loadDocument(InputStream inputStream, LoadOptions loadOptions) throws BoundesuWordsException {
        if (inputStream == null) {
            throw new BoundesuWordsException("输入流不能为空");
        }
        if (loadOptions == null) {
            throw new BoundesuWordsException("加载选项不能为空");
        }
        
        try {
            // 根据加载格式处理不同类型的文档
            switch (loadOptions.getLoadFormat()) {
                case DOCX:
                case DOCM:
                case DOTX:
                case DOTM:
                    return new XWPFDocument(inputStream);
                case DOC:
                case DOT:
                    // POI不直接支持DOC格式，需要转换
                    log.warn("DOC格式需要先转换为DOCX格式");
                    return new XWPFDocument();
                case HTML:
                case MHTML:
                case RTF:
                case TXT:
                    // 这些格式需要第三方库支持
                    log.warn("格式 {} 需要第三方库支持，创建空文档", loadOptions.getLoadFormat());
                    return new XWPFDocument();
                case AUTO:
                default:
                    // 尝试作为DOCX加载
                    return new XWPFDocument(inputStream);
            }
        } catch (Exception e) {
            log.error("从输入流加载文档失败: {}", e.getMessage());
            throw new BoundesuWordsException("从输入流加载文档失败: " + e.getMessage(), e);
        }
    }
    
    /**
     * 创建文档转换器
     * 
     * @param document 文档实例
     * @return 文档转换器
     */
    public static DocumentConverter createConverter(XWPFDocument document) throws BoundesuWordsException {
        if (document == null) {
            throw new BoundesuWordsException("文档不能为空");
        }
        return new DocumentConverter(document);
    }
    
    /**
     * 转换文档格式
     * 
     * @param inputFileName 输入文件名
     * @param outputFileName 输出文件名
     */
    public static void convertDocument(String inputFileName, String outputFileName) throws BoundesuWordsException {
        if (StringUtils.isEmpty(inputFileName)) {
            throw new BoundesuWordsException("输入文件名不能为空");
        }
        if (StringUtils.isEmpty(outputFileName)) {
            throw new BoundesuWordsException("输出文件名不能为空");
        }
        
        try {
            XWPFDocument document = loadDocument(inputFileName);
            DocumentConverter converter = createConverter(document);
            converter.save(outputFileName);
            
            log.info("文档转换成功: {} -> {}", inputFileName, outputFileName);
        } catch (Exception e) {
            log.error("文档转换失败: {}", e.getMessage());
            throw new BoundesuWordsException("文档转换失败: " + e.getMessage(), e);
        }
    }
    
    /**
     * 转换文档格式（指定保存格式）
     * 
     * @param inputFileName 输入文件名
     * @param outputFileName 输出文件名
     * @param saveFormat 保存格式
     */
    public static void convertDocument(String inputFileName, String outputFileName, SaveFormat saveFormat) throws BoundesuWordsException {
        if (StringUtils.isEmpty(inputFileName)) {
            throw new BoundesuWordsException("输入文件名不能为空");
        }
        if (StringUtils.isEmpty(outputFileName)) {
            throw new BoundesuWordsException("输出文件名不能为空");
        }
        if (saveFormat == null) {
            throw new BoundesuWordsException("保存格式不能为空");
        }
        
        try {
            XWPFDocument document = loadDocument(inputFileName);
            DocumentConverter converter = createConverter(document);
            converter.save(outputFileName, saveFormat);
            
            log.info("文档转换成功: {} -> {} ({})", inputFileName, outputFileName, saveFormat);
        } catch (Exception e) {
            log.error("文档转换失败: {}", e.getMessage());
            throw new BoundesuWordsException("文档转换失败: " + e.getMessage(), e);
        }
    }
    
    /**
     * 批量转换文档
     * 
     * @param inputDirectory 输入目录
     * @param outputDirectory 输出目录
     * @param saveFormat 保存格式
     */
    public static void batchConvertDocuments(String inputDirectory, String outputDirectory, SaveFormat saveFormat) throws BoundesuWordsException {
        if (StringUtils.isEmpty(inputDirectory)) {
            throw new BoundesuWordsException("输入目录不能为空");
        }
        if (StringUtils.isEmpty(outputDirectory)) {
            throw new BoundesuWordsException("输出目录不能为空");
        }
        if (saveFormat == null) {
            throw new BoundesuWordsException("保存格式不能为空");
        }
        
        try {
            Path inputPath = Paths.get(inputDirectory);
            Path outputPath = Paths.get(outputDirectory);
            
            if (!Files.exists(inputPath) || !Files.isDirectory(inputPath)) {
                throw new BoundesuWordsException("输入目录不存在或不是目录: " + inputDirectory);
            }
            
            // 创建输出目录
            if (!Files.exists(outputPath)) {
                Files.createDirectories(outputPath);
            }
            
            // 获取支持的输入格式
            Set<String> supportedExtensions = new HashSet<>(Arrays.asList("docx", "doc", "rtf", "html", "txt"));
            
            // 遍历输入目录中的文件
            List<Path> inputFiles = Files.walk(inputPath)
                    .filter(Files::isRegularFile)
                    .filter(path -> {
                        String fileName = path.getFileName().toString().toLowerCase();
                        return supportedExtensions.stream().anyMatch(fileName::endsWith);
                    })
                    .collect(Collectors.toList());
            
            int successCount = 0;
            int failCount = 0;
            
            for (Path inputFile : inputFiles) {
                try {
                    String fileName = inputFile.getFileName().toString();
                    String nameWithoutExt = fileName.substring(0, fileName.lastIndexOf('.'));
                    String outputFileName = nameWithoutExt + "." + getExtensionForFormat(saveFormat);
                    Path outputFile = outputPath.resolve(outputFileName);
                    
                    convertDocument(inputFile.toString(), outputFile.toString(), saveFormat);
                    successCount++;
                    
                } catch (Exception e) {
                    log.error("转换文件失败: {} - {}", inputFile, e.getMessage());
                    failCount++;
                }
            }
            
            log.info("批量转换完成: 成功 {} 个，失败 {} 个", successCount, failCount);
            
        } catch (Exception e) {
            log.error("批量转换失败: {}", e.getMessage());
            throw new BoundesuWordsException("批量转换失败: " + e.getMessage(), e);
        }
    }
    
    /**
     * 合并文档
     * 模拟 Aspose Words Document.appendDocument() 方法
     * 
     * @param mainDocument 主文档
     * @param documentsToMerge 要合并的文档列表
     * @return 合并后的文档
     */
    public static XWPFDocument mergeDocuments(XWPFDocument mainDocument, List<XWPFDocument> documentsToMerge) throws BoundesuWordsException {
        if (mainDocument == null) {
            throw new BoundesuWordsException("主文档不能为空");
        }
        if (documentsToMerge == null || documentsToMerge.isEmpty()) {
            return mainDocument;
        }
        
        try {
            for (XWPFDocument docToMerge : documentsToMerge) {
                if (docToMerge != null) {
                    DocumentUtils.mergeDocuments(mainDocument, Arrays.asList(docToMerge));
                }
            }
            
            log.info("文档合并成功，合并了 {} 个文档", documentsToMerge.size());
            return mainDocument;
            
        } catch (Exception e) {
            log.error("文档合并失败: {}", e.getMessage());
            throw new BoundesuWordsException("文档合并失败: " + e.getMessage(), e);
        }
    }
    
    /**
     * 获取文档信息
     * 
     * @param document 文档实例
     * @return 文档信息映射
     */
    public static Map<String, Object> getDocumentInfo(XWPFDocument document) throws BoundesuWordsException {
        if (document == null) {
            throw new BoundesuWordsException("文档不能为空");
        }
        
        Map<String, Object> info = new HashMap<>();
        
        try {
            // 基本信息
            // 页数统计暂时设为1，因为POI难以准确计算页数
            info.put("pageCount", 1);
            info.put("paragraphCount", DocumentUtils.getParagraphCount(document));
            info.put("tableCount", DocumentUtils.getTableCount(document));
            
            // 文档属性
            if (document.getProperties() != null) {
                if (document.getProperties().getCoreProperties() != null) {
                    info.put("title", document.getProperties().getCoreProperties().getTitle());
                    info.put("author", document.getProperties().getCoreProperties().getCreator());
                    info.put("subject", document.getProperties().getCoreProperties().getSubject());
                    info.put("keywords", document.getProperties().getCoreProperties().getKeywords());
                    info.put("created", document.getProperties().getCoreProperties().getCreated());
                    info.put("modified", document.getProperties().getCoreProperties().getModified());
                }
            }
            
            log.debug("获取文档信息成功");
            return info;
            
        } catch (Exception e) {
            log.error("获取文档信息失败: {}", e.getMessage());
            throw new BoundesuWordsException("获取文档信息失败: " + e.getMessage(), e);
        }
    }
    
    /**
     * 验证文档格式
     * 
     * @param fileName 文件名
     * @return 是否为有效的文档格式
     */
    public static boolean isValidDocumentFormat(String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            return false;
        }
        
        // 简化格式检测逻辑
        String extension = getFileExtensionStatic(fileName).toLowerCase();
        return extension.equals(".docx") || extension.equals(".doc") || extension.equals(".rtf") || extension.equals(".html") || extension.equals(".txt");
    }
    
    /**
     * 获取支持的输入格式
     * 
     * @return 支持的输入格式列表
     */
    public static List<String> getSupportedInputFormats() {
        return Arrays.asList("docx", "doc", "rtf", "html", "txt");
    }
    
    /**
     * 获取支持的输出格式
     * 
     * @return 支持的输出格式列表
     */
    public static List<String> getSupportedOutputFormats() {
        return Arrays.asList("docx", "pdf", "html", "txt", "rtf");
    }
    
    /**
     * 获取保存格式对应的文件扩展名
     */
    private static String getExtensionForFormat(SaveFormat saveFormat) {
        switch (saveFormat) {
            case DOCX:
                return "docx";
            case PDF:
                return "pdf";
            case HTML:
                return "html";
            case TXT:
                return "txt";
            case RTF:
                return "rtf";
            default:
                return "docx";
        }
    }
    
    /**
     * 获取文件扩展名
     */
    private static String getFileExtensionStatic(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return "";
        }
        int lastDotIndex = fileName.lastIndexOf('.');
        return lastDotIndex > 0 ? fileName.substring(lastDotIndex + 1).toLowerCase() : "";
    }
    
    /**
     * 从文件名检测加载格式
     */
    private static LoadFormat detectLoadFormatFromFileName(String fileName) {
        String extension = getFileExtensionStatic(fileName).toLowerCase();
        switch (extension) {
            case "docx":
                return LoadFormat.DOCX;
            case "doc":
                return LoadFormat.DOC;
            case "rtf":
                return LoadFormat.RTF;
            case "html":
            case "htm":
                return LoadFormat.HTML;
            case "txt":
                return LoadFormat.TXT;
            default:
                return LoadFormat.AUTO;
        }
    }
    
    private static SaveFormat detectSaveFormatFromExtension(String extension) {
        if (extension.startsWith(".")) {
            extension = extension.substring(1);
        }
        extension = extension.toLowerCase();
        switch (extension) {
            case "docx":
                return SaveFormat.DOCX;
            case "pdf":
                return SaveFormat.PDF;
            case "html":
            case "htm":
                return SaveFormat.HTML;
            case "txt":
                return SaveFormat.TXT;
            case "rtf":
                return SaveFormat.RTF;
            default:
                return SaveFormat.DOCX;
        }
    }
    
    private static SaveOptions createSaveOptionsForFormat(SaveFormat saveFormat) {
        switch (saveFormat) {
            case PDF:
                return new com.boundesu.words.core.options.PdfSaveOptions();
            case DOCX:
            case DOC:
            case DOCM:
            case DOT:
            case DOTX:
            case DOTM:
                return new com.boundesu.words.core.options.DocxSaveOptions(saveFormat);
            case HTML:
            case MHTML:
            case RTF:
            case TXT:
            default:
                // 对于其他格式，创建一个基本的DocxSaveOptions
                return new com.boundesu.words.core.options.DocxSaveOptions(saveFormat);
        }
    }
}