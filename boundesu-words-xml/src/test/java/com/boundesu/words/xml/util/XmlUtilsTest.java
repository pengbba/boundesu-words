package com.boundesu.words.xml.util;

import com.boundesu.words.common.exception.BoundesuWordsException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * XmlUtils 测试类
 * 
 * @author Boundesu
 * @version 1.0.0
 */
public class XmlUtilsTest {
    
    private Path tempDir;
    
    @BeforeMethod
    public void setUp() throws IOException {
        tempDir = Files.createTempDirectory("boundesu-xml-utils-test");
    }
    
    @AfterMethod
    public void tearDown() throws IOException {
        // 清理临时目录
        Files.walk(tempDir)
                .sorted((a, b) -> b.compareTo(a)) // 先删除文件，再删除目录
                .forEach(path -> {
                    try {
                        Files.deleteIfExists(path);
                    } catch (IOException e) {
                        // 忽略删除错误
                    }
                });
    }
    
    @Test
    public void testParseXmlString() throws BoundesuWordsException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><root><child>value</child></root>";
        
        Document document = XmlUtils.parseXmlString(xml);
        Assert.assertNotNull(document);
        Assert.assertEquals(document.getDocumentElement().getTagName(), "root");
        Assert.assertEquals(document.getElementsByTagName("child").item(0).getTextContent(), "value");
    }
    
    @Test
    public void testParseXmlFile() throws BoundesuWordsException, IOException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><root><child>file content</child></root>";
        
        // 创建临时XML文件
        File xmlFile = new File(tempDir.toFile(), "test.xml");
        Files.write(xmlFile.toPath(), xml.getBytes("UTF-8"));
        
        Document document = XmlUtils.parseXmlFile(xmlFile.getAbsolutePath());
        Assert.assertNotNull(document);
        Assert.assertEquals(document.getDocumentElement().getTagName(), "root");
        Assert.assertEquals(document.getElementsByTagName("child").item(0).getTextContent(), "file content");
    }
    
    @Test
    public void testDocumentToString() throws BoundesuWordsException {
        Document document = XmlUtils.createDocument();
        Element root = document.createElement("root");
        document.appendChild(root);
        
        Element child = document.createElement("child");
        child.setTextContent("test value");
        root.appendChild(child);
        
        String xmlString = XmlUtils.documentToString(document);
        Assert.assertNotNull(xmlString);
        Assert.assertTrue(xmlString.contains("<root>"));
        Assert.assertTrue(xmlString.contains("<child>test value</child>"));
        Assert.assertTrue(xmlString.contains("</root>"));
    }
    
    @Test
    public void testDocumentToFormattedString() throws BoundesuWordsException {
        Document document = XmlUtils.createDocument();
        Element root = document.createElement("root");
        document.appendChild(root);
        
        Element child = document.createElement("child");
        child.setTextContent("test value");
        root.appendChild(child);
        
        String formattedXml = XmlUtils.documentToFormattedString(document);
        Assert.assertNotNull(formattedXml);
        Assert.assertTrue(formattedXml.contains("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"));
        Assert.assertTrue(formattedXml.contains("<root>"));
        Assert.assertTrue(formattedXml.contains("  <child>test value</child>"));
        Assert.assertTrue(formattedXml.contains("</root>"));
    }
    
    @Test
    public void testSaveDocumentToFile() throws BoundesuWordsException, IOException {
        Document document = XmlUtils.createDocument();
        Element root = document.createElement("root");
        document.appendChild(root);
        
        Element child = document.createElement("child");
        child.setTextContent("saved content");
        root.appendChild(child);
        
        File outputFile = new File(tempDir.toFile(), "output.xml");
        XmlUtils.saveDocumentToFile(document, outputFile.getAbsolutePath());
        
        Assert.assertTrue(outputFile.exists());
        Assert.assertTrue(outputFile.length() > 0);
        
        // 验证保存的内容
        String savedContent = new String(Files.readAllBytes(outputFile.toPath()), "UTF-8");
        Assert.assertTrue(savedContent.contains("<root>"));
        Assert.assertTrue(savedContent.contains("<child>saved content</child>"));
    }
    
    @Test
    public void testCreateDocument() throws BoundesuWordsException {
        Document document = XmlUtils.createDocument();
        Assert.assertNotNull(document);
    }
    
    @Test
    public void testCreateDocumentWithRoot() throws BoundesuWordsException {
        Document document = XmlUtils.createDocument("testRoot");
        Assert.assertNotNull(document);
        Assert.assertEquals(document.getDocumentElement().getTagName(), "testRoot");
    }
    
    @Test
    public void testGetElementText() throws BoundesuWordsException {
        String xml = "<root><child>element text</child></root>";
        Document document = XmlUtils.parseXmlString(xml);
        Element child = (Element) document.getElementsByTagName("child").item(0);
        
        String text = XmlUtils.getElementText(child);
        Assert.assertEquals(text, "element text");
    }
    
    @Test
    public void testSetElementText() throws BoundesuWordsException {
        Document document = XmlUtils.createDocument("root");
        Element root = document.getDocumentElement();
        Element child = document.createElement("child");
        root.appendChild(child);
        
        XmlUtils.setElementText(child, "new text");
        Assert.assertEquals(child.getTextContent(), "new text");
    }
    
    @Test
    public void testGetElementAttribute() throws BoundesuWordsException {
        String xml = "<root><child attr=\"attribute value\">text</child></root>";
        Document document = XmlUtils.parseXmlString(xml);
        Element child = (Element) document.getElementsByTagName("child").item(0);
        
        String attrValue = XmlUtils.getElementAttribute(child, "attr");
        Assert.assertEquals(attrValue, "attribute value");
    }
    
    @Test
    public void testSetElementAttribute() throws BoundesuWordsException {
        Document document = XmlUtils.createDocument("root");
        Element root = document.getDocumentElement();
        Element child = document.createElement("child");
        root.appendChild(child);
        
        XmlUtils.setElementAttribute(child, "testAttr", "test value");
        Assert.assertEquals(child.getAttribute("testAttr"), "test value");
    }
    
    @Test
    public void testGetChildElement() throws BoundesuWordsException {
        String xml = "<root><child1>text1</child1><child2>text2</child2></root>";
        Document document = XmlUtils.parseXmlString(xml);
        Element root = document.getDocumentElement();
        
        Element child1 = XmlUtils.getChildElement(root, "child1");
        Assert.assertNotNull(child1);
        Assert.assertEquals(child1.getTextContent(), "text1");
        
        Element child2 = XmlUtils.getChildElement(root, "child2");
        Assert.assertNotNull(child2);
        Assert.assertEquals(child2.getTextContent(), "text2");
        
        Element nonExistent = XmlUtils.getChildElement(root, "nonexistent");
        Assert.assertNull(nonExistent);
    }
    
    @Test
    public void testCreateChildElement() throws BoundesuWordsException {
        Document document = XmlUtils.createDocument("root");
        Element root = document.getDocumentElement();
        
        Element child = XmlUtils.createChildElement(root, "newChild");
        Assert.assertNotNull(child);
        Assert.assertEquals(child.getTagName(), "newChild");
        Assert.assertEquals(child.getParentNode(), root);
    }
    
    @Test
    public void testCreateChildElementWithText() throws BoundesuWordsException {
        Document document = XmlUtils.createDocument("root");
        Element root = document.getDocumentElement();
        
        Element child = XmlUtils.createChildElement(root, "newChild", "child text");
        Assert.assertNotNull(child);
        Assert.assertEquals(child.getTagName(), "newChild");
        Assert.assertEquals(child.getTextContent(), "child text");
        Assert.assertEquals(child.getParentNode(), root);
    }
    
    @Test
    public void testRemoveChildElement() throws BoundesuWordsException {
        String xml = "<root><child1>text1</child1><child2>text2</child2></root>";
        Document document = XmlUtils.parseXmlString(xml);
        Element root = document.getDocumentElement();
        
        // 验证child1存在
        Element child1 = XmlUtils.getChildElement(root, "child1");
        Assert.assertNotNull(child1);
        
        // 移除child1
        boolean removed = XmlUtils.removeChildElement(root, "child1");
        Assert.assertTrue(removed);
        
        // 验证child1已被移除
        Element removedChild = XmlUtils.getChildElement(root, "child1");
        Assert.assertNull(removedChild);
        
        // 验证child2仍然存在
        Element child2 = XmlUtils.getChildElement(root, "child2");
        Assert.assertNotNull(child2);
        
        // 尝试移除不存在的元素
        boolean notRemoved = XmlUtils.removeChildElement(root, "nonexistent");
        Assert.assertFalse(notRemoved);
    }
    
    @Test
    public void testIsWellFormed() {
        String wellFormedXml = "<?xml version=\"1.0\"?><root><child>text</child></root>";
        Assert.assertTrue(XmlUtils.isWellFormed(wellFormedXml));
        
        String malformedXml = "<root><child>text</root>";
        Assert.assertFalse(XmlUtils.isWellFormed(malformedXml));
        
        String emptyXml = "";
        Assert.assertFalse(XmlUtils.isWellFormed(emptyXml));
        
        String nullXml = null;
        Assert.assertFalse(XmlUtils.isWellFormed(nullXml));
    }
    
    @Test
    public void testFormatXml() {
        String unformattedXml = "<root><child>text</child><child2>text2</child2></root>";
        String formattedXml = XmlUtils.formatXml(unformattedXml);
        
        Assert.assertNotNull(formattedXml);
        Assert.assertTrue(formattedXml.contains("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"));
        Assert.assertTrue(formattedXml.contains("\n"));
        Assert.assertTrue(formattedXml.contains("  <child>"));
    }
    
    @Test
    public void testCompressXml() {
        String formattedXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<root>\n  <child>text</child>\n  <child2>text2</child2>\n</root>";
        String compressedXml = XmlUtils.compressXml(formattedXml);
        
        Assert.assertNotNull(compressedXml);
        Assert.assertFalse(compressedXml.contains("\n"));
        Assert.assertFalse(compressedXml.contains("  "));
        Assert.assertTrue(compressedXml.contains("<root><child>text</child><child2>text2</child2></root>"));
    }
    
    @Test(expectedExceptions = BoundesuWordsException.class)
    public void testParseInvalidXmlString() throws BoundesuWordsException {
        String invalidXml = "<root><child>unclosed tag</root>";
        XmlUtils.parseXmlString(invalidXml);
    }
    
    @Test(expectedExceptions = BoundesuWordsException.class)
    public void testParseEmptyXmlString() throws BoundesuWordsException {
        XmlUtils.parseXmlString("");
    }
    
    @Test(expectedExceptions = BoundesuWordsException.class)
    public void testParseNullXmlString() throws BoundesuWordsException {
        XmlUtils.parseXmlString(null);
    }
    
    @Test(expectedExceptions = BoundesuWordsException.class)
    public void testParseNonExistentFile() throws BoundesuWordsException {
        XmlUtils.parseXmlFile("/nonexistent/path/file.xml");
    }
    
    @Test
    public void testFormatInvalidXml() {
        String invalidXml = "<root><child>unclosed";
        String result = XmlUtils.formatXml(invalidXml);
        Assert.assertEquals(result, invalidXml); // 应该返回原始字符串
    }
    
    @Test
    public void testCompressInvalidXml() {
        String invalidXml = "<root><child>unclosed";
        String result = XmlUtils.compressXml(invalidXml);
        Assert.assertEquals(result, invalidXml); // 应该返回原始字符串
    }
    
    @Test
    public void testGetElementTextWithEmptyElement() throws BoundesuWordsException {
        String xml = "<root><empty></empty></root>";
        Document document = XmlUtils.parseXmlString(xml);
        Element empty = (Element) document.getElementsByTagName("empty").item(0);
        
        String text = XmlUtils.getElementText(empty);
        Assert.assertEquals(text, "");
    }
    
    @Test
    public void testGetElementAttributeNonExistent() throws BoundesuWordsException {
        String xml = "<root><child>text</child></root>";
        Document document = XmlUtils.parseXmlString(xml);
        Element child = (Element) document.getElementsByTagName("child").item(0);
        
        String attrValue = XmlUtils.getElementAttribute(child, "nonexistent");
        Assert.assertEquals(attrValue, "");
    }
}