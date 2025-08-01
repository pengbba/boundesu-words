package com.boundesu.words.common.util;

import com.boundesu.words.common.exception.BoundesuWordsException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 文件工具类
 * 
 * @author Boundesu
 * @version 1.0.0
 */
public class FileUtils {
    
    /**
     * 私有构造函数，防止实例化
     */
    private FileUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
    
    /**
     * 读取文件内容为字符串
     * 
     * @param filePath 文件路径
     * @return 文件内容
     * @throws BoundesuWordsException 异常
     */
    public static String readFileToString(String filePath) throws BoundesuWordsException {
        try {
            Path path = Paths.get(filePath);
            byte[] bytes = Files.readAllBytes(path);
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new BoundesuWordsException("读取文件失败: " + filePath, e);
        }
    }
    
    /**
     * 读取文件内容为字符串，指定编码
     * 
     * @param filePath 文件路径
     * @param encoding 编码
     * @return 文件内容
     * @throws BoundesuWordsException 异常
     */
    public static String readFileToString(String filePath, String encoding) throws BoundesuWordsException {
        try {
            Path path = Paths.get(filePath);
            byte[] bytes = Files.readAllBytes(path);
            return new String(bytes, encoding);
        } catch (IOException e) {
            throw new BoundesuWordsException("读取文件失败: " + filePath, e);
        }
    }
    
    /**
     * 写入字符串到文件
     * 
     * @param filePath 文件路径
     * @param content 内容
     * @throws BoundesuWordsException 异常
     */
    public static void writeStringToFile(String filePath, String content) throws BoundesuWordsException {
        try {
            Path path = Paths.get(filePath);
            // 确保父目录存在
            Files.createDirectories(path.getParent());
            Files.write(path, content.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new BoundesuWordsException("写入文件失败: " + filePath, e);
        }
    }
    
    /**
     * 写入字符串到文件，指定编码
     * 
     * @param filePath 文件路径
     * @param content 内容
     * @param encoding 编码
     * @throws BoundesuWordsException 异常
     */
    public static void writeStringToFile(String filePath, String content, String encoding) throws BoundesuWordsException {
        try {
            Path path = Paths.get(filePath);
            // 确保父目录存在
            Files.createDirectories(path.getParent());
            Files.write(path, content.getBytes(encoding));
        } catch (IOException e) {
            throw new BoundesuWordsException("写入文件失败: " + filePath, e);
        }
    }
    
    /**
     * 获取文件扩展名
     * 
     * @param fileName 文件名
     * @return 扩展名
     */
    public static String getFileExtension(String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            return "";
        }
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex == -1 || lastDotIndex == fileName.length() - 1) {
            return "";
        }
        return fileName.substring(lastDotIndex + 1).toLowerCase();
    }
    
    /**
     * 获取文件名（不含扩展名）
     * 
     * @param fileName 文件名
     * @return 文件名（不含扩展名）
     */
    public static String getFileNameWithoutExtension(String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            return "";
        }
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex == -1) {
            return fileName;
        }
        return fileName.substring(0, lastDotIndex);
    }
    
    /**
     * 检查文件是否存在
     * 
     * @param filePath 文件路径
     * @return 是否存在
     */
    public static boolean exists(String filePath) {
        return Files.exists(Paths.get(filePath));
    }
    
    /**
     * 创建目录
     * 
     * @param dirPath 目录路径
     * @throws BoundesuWordsException 异常
     */
    public static void createDirectories(String dirPath) throws BoundesuWordsException {
        try {
            Files.createDirectories(Paths.get(dirPath));
        } catch (IOException e) {
            throw new BoundesuWordsException("创建目录失败: " + dirPath, e);
        }
    }
}