package com.boundesu.words.common.util;

import com.boundesu.words.common.exception.BoundesuWordsException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * FileUtils 测试类
 * 
 * @author Boundesu
 * @version 1.0.0
 */
public class FileUtilsTest {
    
    private Path tempDir;
    private File testFile;
    
    @BeforeMethod
    public void setUp() throws IOException {
        // 创建临时目录
        tempDir = Files.createTempDirectory("boundesu-test");
        testFile = new File(tempDir.toFile(), "test.txt");
    }
    
    @AfterMethod
    public void tearDown() throws IOException {
        // 清理临时文件和目录
        if (testFile.exists()) {
            testFile.delete();
        }
        Files.deleteIfExists(tempDir);
    }
    
    @Test
    public void testWriteAndReadString() throws BoundesuWordsException {
        String content = "Hello, Boundesu Words!";
        
        // 写入文件
        FileUtils.writeString(testFile, content);
        Assert.assertTrue(testFile.exists());
        
        // 读取文件
        String readContent = FileUtils.readString(testFile);
        Assert.assertEquals(readContent, content);
    }
    
    @Test
    public void testWriteAndReadStringWithEncoding() throws BoundesuWordsException {
        String content = "你好，Boundesu Words！";
        
        // 使用UTF-8编码写入
        FileUtils.writeString(testFile, content, StandardCharsets.UTF_8);
        Assert.assertTrue(testFile.exists());
        
        // 使用UTF-8编码读取
        String readContent = FileUtils.readString(testFile, StandardCharsets.UTF_8);
        Assert.assertEquals(readContent, content);
    }
    
    @Test
    public void testGetFileExtension() {
        Assert.assertEquals(FileUtils.getFileExtension("test.txt"), "txt");
        Assert.assertEquals(FileUtils.getFileExtension("test.html"), "html");
        Assert.assertEquals(FileUtils.getFileExtension("test.docx"), "docx");
        Assert.assertEquals(FileUtils.getFileExtension("test"), "");
        Assert.assertEquals(FileUtils.getFileExtension(""), "");
        Assert.assertEquals(FileUtils.getFileExtension(null), "");
        Assert.assertEquals(FileUtils.getFileExtension("path/to/file.xml"), "xml");
        Assert.assertEquals(FileUtils.getFileExtension("file.name.with.dots.json"), "json");
    }
    
    @Test
    public void testGetFileNameWithoutExtension() {
        Assert.assertEquals(FileUtils.getFileNameWithoutExtension("test.txt"), "test");
        Assert.assertEquals(FileUtils.getFileNameWithoutExtension("test.html"), "test");
        Assert.assertEquals(FileUtils.getFileNameWithoutExtension("test"), "test");
        Assert.assertEquals(FileUtils.getFileNameWithoutExtension(""), "");
        Assert.assertEquals(FileUtils.getFileNameWithoutExtension(null), "");
        Assert.assertEquals(FileUtils.getFileNameWithoutExtension("path/to/file.xml"), "file");
        Assert.assertEquals(FileUtils.getFileNameWithoutExtension("file.name.with.dots.json"), "file.name.with.dots");
    }
    
    @Test
    public void testExists() {
        Assert.assertFalse(FileUtils.exists(testFile));
        
        try {
            testFile.createNewFile();
            Assert.assertTrue(FileUtils.exists(testFile));
        } catch (IOException e) {
            Assert.fail("Failed to create test file");
        }
    }
    
    @Test
    public void testCreateDirectories() throws BoundesuWordsException {
        File newDir = new File(tempDir.toFile(), "new/nested/directory");
        Assert.assertFalse(newDir.exists());
        
        FileUtils.createDirectories(newDir);
        Assert.assertTrue(newDir.exists());
        Assert.assertTrue(newDir.isDirectory());
    }
    
    @Test(expectedExceptions = BoundesuWordsException.class)
    public void testReadNonExistentFile() throws BoundesuWordsException {
        File nonExistentFile = new File("non-existent-file.txt");
        FileUtils.readString(nonExistentFile);
    }
    
    @Test
    public void testWriteToNonExistentDirectory() throws BoundesuWordsException {
        File fileInNewDir = new File(tempDir.toFile(), "new-dir/test.txt");
        String content = "Test content";
        
        // 应该自动创建目录
        FileUtils.writeString(fileInNewDir, content);
        Assert.assertTrue(fileInNewDir.exists());
        
        String readContent = FileUtils.readString(fileInNewDir);
        Assert.assertEquals(readContent, content);
    }
    
    @Test
    public void testEmptyContent() throws BoundesuWordsException {
        String emptyContent = "";
        
        FileUtils.writeString(testFile, emptyContent);
        Assert.assertTrue(testFile.exists());
        
        String readContent = FileUtils.readString(testFile);
        Assert.assertEquals(readContent, emptyContent);
    }
}