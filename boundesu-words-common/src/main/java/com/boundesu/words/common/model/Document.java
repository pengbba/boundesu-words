package com.boundesu.words.common.model;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Boundesu Words 文档封装类
 * 封装了Apache POI的XWPFDocument，提供更友好的API
 *
 * @author Boundesu Team
 * @version 1.0.0
 */
public class Document {

    private final XWPFDocument xwpfDocument;

    /**
     * 构造函数
     *
     * @param xwpfDocument Apache POI的XWPFDocument实例
     */
    public Document(XWPFDocument xwpfDocument) {
        if (xwpfDocument == null) {
            throw new IllegalArgumentException("XWPFDocument不能为null");
        }
        this.xwpfDocument = xwpfDocument;
    }

    /**
     * 获取底层的XWPFDocument对象
     * 用于需要直接操作Apache POI API的场景
     *
     * @return XWPFDocument对象
     */
    public XWPFDocument getXWPFDocument() {
        return xwpfDocument;
    }

    /**
     * 保存文档到文件
     *
     * @param file 目标文件
     * @throws IOException 文件操作异常
     */
    public void saveToFile(File file) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            xwpfDocument.write(fos);
        }
    }

    /**
     * 保存文档到文件
     *
     * @param filePath 目标文件路径
     * @throws IOException 文件操作异常
     */
    public void saveToFile(String filePath) throws IOException {
        saveToFile(new File(filePath));
    }

    /**
     * 保存文档到输出流
     *
     * @param outputStream 输出流
     * @throws IOException 文件操作异常
     */
    public void saveToStream(OutputStream outputStream) throws IOException {
        xwpfDocument.write(outputStream);
    }

    /**
     * 关闭文档，释放资源
     *
     * @param out 输出流
     * @throws IOException 关闭异常/**
     *                     将文档写入到输出流
     * @throws IOException 如果写入失败
     */
    public void write(OutputStream out) throws IOException {
        if (xwpfDocument != null) {
            xwpfDocument.write(out);
        }
    }

    /**
     * 关闭文档资源
     */
    public void close() throws IOException {
        if (xwpfDocument != null) {
            xwpfDocument.close();
        }
    }

    /**
     * 获取文档的段落数量
     *
     * @return 段落数量
     */
    public int getParagraphCount() {
        return xwpfDocument.getParagraphs().size();
    }

    /**
     * 获取文档的表格数量
     *
     * @return 表格数量
     */
    public int getTableCount() {
        return xwpfDocument.getTables().size();
    }

    /**
     * 检查文档是否为空
     *
     * @return 如果文档为空返回true
     */
    public boolean isEmpty() {
        return getParagraphCount() == 0 && getTableCount() == 0;
    }

    @Override
    public String toString() {
        return "Document{" +
                "paragraphs=" + getParagraphCount() +
                ", tables=" + getTableCount() +
                '}';
    }
}