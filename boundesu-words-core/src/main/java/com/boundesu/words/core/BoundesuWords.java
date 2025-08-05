package com.boundesu.words.core;

import com.boundesu.words.common.constants.BoundesuConstants;
import com.boundesu.words.common.exception.BoundesuWordsException;
import com.boundesu.words.common.util.StringUtils;
import com.boundesu.words.core.builder.DocumentBuilder;
import com.boundesu.words.core.options.LoadOptions;
import com.boundesu.words.core.options.SaveOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Boundesu Words SDK 主入口类
 *
 * @author Boundesu
 * @version 1.0.0
 */
public class BoundesuWords {

    private static final Logger log = LoggerFactory.getLogger(BoundesuWords.class);

    /**
     * 构造函数
     */
    public BoundesuWords() {
        log.info("Boundesu Words Core 初始化完成");
    }

    /**
     * 获取SDK版本信息
     *
     * @return SDK信息
     */
    public static Map<String, String> getSDKInfo() {
        Map<String, String> info = new LinkedHashMap<>();
        info.put("name", BoundesuConstants.SDK_NAME + " Core");
        info.put("version", BoundesuConstants.SDK_VERSION);
        info.put("description", BoundesuConstants.SDK_DESCRIPTION);
        info.put("author", BoundesuConstants.SDK_AUTHOR);
        return info;
    }

    /**
     * 获取SDK版本号
     *
     * @return 版本号
     */
    public static String getVersion() {
        return BoundesuConstants.SDK_VERSION;
    }

    /**
     * 保存DOCX文档到文件
     *
     * @param document   DOCX文档
     * @param outputFile 输出文件
     * @throws BoundesuWordsException 保存异常
     */
    public void saveToFile(XWPFDocument document, File outputFile) throws BoundesuWordsException {
        if (document == null) {
            throw new BoundesuWordsException("文档不能为空");
        }
        if (outputFile == null) {
            throw new BoundesuWordsException("输出文件不能为空");
        }

        try {
            // 确保父目录存在
            File parentDir = outputFile.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                document.write(fos);
                log.info("文档已保存到: {}", outputFile.getAbsolutePath());
            }
        } catch (IOException e) {
            throw new BoundesuWordsException("保存文档失败: " + e.getMessage(), e);
        }
    }

    /**
     * 保存DOCX文档到文件
     *
     * @param document       DOCX文档
     * @param outputFilePath 输出文件路径
     * @throws BoundesuWordsException 保存异常
     */
    public void saveToFile(XWPFDocument document, String outputFilePath) throws BoundesuWordsException {
        if (StringUtils.isEmpty(outputFilePath)) {
            throw new BoundesuWordsException("输出文件路径不能为空");
        }
        saveToFile(document, new File(outputFilePath));
    }

    /**
     * 创建新的文档
     *
     * @return Document实例
     */
    public Document createDocument() {
        return new Document();
    }

    /**
     * 从文件加载文档
     *
     * @param fileName 文件名
     * @return Document实例
     * @throws BoundesuWordsException 加载异常
     */
    public Document loadDocument(String fileName) throws BoundesuWordsException {
        try {
            return new Document(fileName);
        } catch (IOException e) {
            throw new BoundesuWordsException("加载文档失败: " + e.getMessage(), e);
        }
    }

    /**
     * 从文件加载文档，使用加载选项
     *
     * @param fileName    文件名
     * @param loadOptions 加载选项
     * @return Document实例
     * @throws BoundesuWordsException 加载异常
     */
    public Document loadDocument(String fileName, LoadOptions loadOptions) throws BoundesuWordsException {
        try {
            return new Document(fileName, loadOptions);
        } catch (IOException e) {
            throw new BoundesuWordsException("加载文档失败: " + e.getMessage(), e);
        }
    }

    /**
     * 从输入流加载文档
     *
     * @param stream 输入流
     * @return Document实例
     * @throws BoundesuWordsException 加载异常
     */
    public Document loadDocument(InputStream stream) throws BoundesuWordsException {
        try {
            return new Document(stream);
        } catch (IOException e) {
            throw new BoundesuWordsException("加载文档失败: " + e.getMessage(), e);
        }
    }

    /**
     * 从输入流加载文档，使用加载选项
     *
     * @param stream      输入流
     * @param loadOptions 加载选项
     * @return Document实例
     * @throws BoundesuWordsException 加载异常
     */
    public Document loadDocument(InputStream stream, LoadOptions loadOptions) throws BoundesuWordsException {
        try {
            return new Document(stream, loadOptions);
        } catch (IOException e) {
            throw new BoundesuWordsException("加载文档失败: " + e.getMessage(), e);
        }
    }

    /**
     * 创建文档构建器
     *
     * @return DocumentBuilder实例
     */
    public DocumentBuilder createDocumentBuilder() {
        return new DocumentBuilder();
    }

    /**
     * 为指定文档创建文档构建器
     *
     * @param document 文档
     * @return DocumentBuilder实例
     */
    public DocumentBuilder createDocumentBuilder(Document document) {
        return new DocumentBuilder(document.getInternalDocument());
    }

    /**
     * 保存文档
     *
     * @param document   文档
     * @param fileName   文件名
     * @param saveOptions 保存选项
     * @throws BoundesuWordsException 保存异常
     */
    public void saveDocument(Document document, String fileName, SaveOptions saveOptions) throws BoundesuWordsException {
        try {
            document.save(fileName, saveOptions);
            log.info("文档已保存到: {}", fileName);
        } catch (IOException e) {
            throw new BoundesuWordsException("保存文档失败: " + e.getMessage(), e);
        }
    }

    /**
     * 保存文档
     *
     * @param document 文档
     * @param fileName 文件名
     * @throws BoundesuWordsException 保存异常
     */
    public void saveDocument(Document document, String fileName) throws BoundesuWordsException {
        try {
            document.save(fileName);
            log.info("文档已保存到: {}", fileName);
        } catch (IOException e) {
            throw new BoundesuWordsException("保存文档失败: " + e.getMessage(), e);
        }
    }
}