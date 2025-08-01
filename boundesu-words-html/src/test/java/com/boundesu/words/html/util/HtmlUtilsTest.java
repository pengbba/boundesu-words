package com.boundesu.words.html.util;

import org.jsoup.select.Elements;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * HtmlUtils 测试类
 * 
 * @author Boundesu
 * @version 1.0.0
 */
public class HtmlUtilsTest {
    
    @Test
    public void testCleanHtml() {
        String dirtyHtml = "<script>alert('xss')</script><p>Hello <b>World</b></p>";
        String cleanHtml = HtmlUtils.cleanHtml(dirtyHtml);
        
        Assert.assertFalse(cleanHtml.contains("<script>"));
        Assert.assertTrue(cleanHtml.contains("<p>"));
        Assert.assertTrue(cleanHtml.contains("<b>"));
    }
    
    @Test
    public void testExtractText() {
        String html = "<h1>标题</h1><p>这是一个<strong>段落</strong>。</p>";
        String text = HtmlUtils.extractText(html);
        
        Assert.assertEquals(text, "标题 这是一个段落。");
    }
    
    @Test
    public void testIsValidHtml() {
        Assert.assertTrue(HtmlUtils.isValidHtml("<p>Hello World</p>"));
        Assert.assertTrue(HtmlUtils.isValidHtml("<html><body><h1>Title</h1></body></html>"));
        Assert.assertFalse(HtmlUtils.isValidHtml(""));
        Assert.assertFalse(HtmlUtils.isValidHtml(null));
    }
    
    @Test
    public void testGetLinks() {
        String html = "<p>访问 <a href='https://example.com'>示例网站</a> 和 <a href='https://test.com'>测试网站</a></p>";
        Elements links = HtmlUtils.getLinks(html);
        
        Assert.assertEquals(links.size(), 2);
        Assert.assertEquals(links.get(0).attr("href"), "https://example.com");
        Assert.assertEquals(links.get(1).attr("href"), "https://test.com");
    }
    
    @Test
    public void testGetImages() {
        String html = "<p>图片: <img src='image1.jpg' alt='图片1'/> <img src='image2.png' alt='图片2'/></p>";
        Elements images = HtmlUtils.getImages(html);
        
        Assert.assertEquals(images.size(), 2);
        Assert.assertEquals(images.get(0).attr("src"), "image1.jpg");
        Assert.assertEquals(images.get(1).attr("src"), "image2.png");
    }
    
    @Test
    public void testGetTables() {
        String html = "<table><tr><td>数据1</td></tr></table><table><tr><td>数据2</td></tr></table>";
        Elements tables = HtmlUtils.getTables(html);
        
        Assert.assertEquals(tables.size(), 2);
    }
    
    @Test
    public void testStripTags() {
        String html = "<h1>标题</h1><p>这是一个<strong>段落</strong>。</p>";
        String text = HtmlUtils.stripTags(html);
        
        Assert.assertEquals(text, "标题 这是一个段落。");
        Assert.assertFalse(text.contains("<"));
        Assert.assertFalse(text.contains(">"));
    }
    
    @Test
    public void testFormatHtml() {
        String html = "<html><head><title>Test</title></head><body><h1>Title</h1><p>Content</p></body></html>";
        String formatted = HtmlUtils.formatHtml(html);
        
        Assert.assertTrue(formatted.contains("\n"));
        Assert.assertTrue(formatted.length() > html.length());
    }
    
    @Test
    public void testCompressHtml() {
        String html = "<html>\n  <head>\n    <title>Test</title>\n  </head>\n  <body>\n    <h1>Title</h1>\n  </body>\n</html>";
        String compressed = HtmlUtils.compressHtml(html);
        
        Assert.assertFalse(compressed.contains("\n"));
        Assert.assertTrue(compressed.length() < html.length());
    }
    
    @Test
    public void testGetTitle() {
        String html = "<html><head><title>测试标题</title></head><body><h1>内容</h1></body></html>";
        String title = HtmlUtils.getTitle(html);
        
        Assert.assertEquals(title, "测试标题");
    }
    
    @Test
    public void testSetTitle() {
        String html = "<html><head><title>旧标题</title></head><body><h1>内容</h1></body></html>";
        String newHtml = HtmlUtils.setTitle(html, "新标题");
        
        Assert.assertTrue(newHtml.contains("新标题"));
        Assert.assertFalse(newHtml.contains("旧标题"));
    }
    
    @Test
    public void testSelectElements() {
        String html = "<div class='content'><p>段落1</p><p>段落2</p></div>";
        Elements paragraphs = HtmlUtils.selectElements(html, "p");
        
        Assert.assertEquals(paragraphs.size(), 2);
        Assert.assertEquals(paragraphs.get(0).text(), "段落1");
        Assert.assertEquals(paragraphs.get(1).text(), "段落2");
        
        Elements divs = HtmlUtils.selectElements(html, "div.content");
        Assert.assertEquals(divs.size(), 1);
    }
    
    @Test
    public void testContainsTag() {
        String html = "<div><p>段落</p><span>文本</span></div>";
        
        Assert.assertTrue(HtmlUtils.containsTag(html, "div"));
        Assert.assertTrue(HtmlUtils.containsTag(html, "p"));
        Assert.assertTrue(HtmlUtils.containsTag(html, "span"));
        Assert.assertFalse(HtmlUtils.containsTag(html, "h1"));
        Assert.assertFalse(HtmlUtils.containsTag(html, "table"));
    }
    
    @Test
    public void testEmptyAndNullInputs() {
        // 测试空字符串和null输入
        Assert.assertEquals(HtmlUtils.cleanHtml(null), "");
        Assert.assertEquals(HtmlUtils.cleanHtml(""), "");
        
        Assert.assertEquals(HtmlUtils.extractText(null), "");
        Assert.assertEquals(HtmlUtils.extractText(""), "");
        
        Assert.assertFalse(HtmlUtils.isValidHtml(null));
        Assert.assertFalse(HtmlUtils.isValidHtml(""));
        
        Assert.assertEquals(HtmlUtils.getLinks(null).size(), 0);
        Assert.assertEquals(HtmlUtils.getLinks("").size(), 0);
        
        Assert.assertEquals(HtmlUtils.getImages(null).size(), 0);
        Assert.assertEquals(HtmlUtils.getImages("").size(), 0);
        
        Assert.assertEquals(HtmlUtils.getTables(null).size(), 0);
        Assert.assertEquals(HtmlUtils.getTables("").size(), 0);
        
        Assert.assertEquals(HtmlUtils.stripTags(null), "");
        Assert.assertEquals(HtmlUtils.stripTags(""), "");
        
        Assert.assertEquals(HtmlUtils.formatHtml(null), "");
        Assert.assertEquals(HtmlUtils.formatHtml(""), "");
        
        Assert.assertEquals(HtmlUtils.compressHtml(null), "");
        Assert.assertEquals(HtmlUtils.compressHtml(""), "");
        
        Assert.assertEquals(HtmlUtils.getTitle(null), "");
        Assert.assertEquals(HtmlUtils.getTitle(""), "");
        
        Assert.assertEquals(HtmlUtils.setTitle(null, "title"), "");
        Assert.assertEquals(HtmlUtils.setTitle("", "title"), "");
        
        Assert.assertEquals(HtmlUtils.selectElements(null, "p").size(), 0);
        Assert.assertEquals(HtmlUtils.selectElements("", "p").size(), 0);
        Assert.assertEquals(HtmlUtils.selectElements("<p>test</p>", null).size(), 0);
        Assert.assertEquals(HtmlUtils.selectElements("<p>test</p>", "").size(), 0);
        
        Assert.assertFalse(HtmlUtils.containsTag(null, "p"));
        Assert.assertFalse(HtmlUtils.containsTag("", "p"));
        Assert.assertFalse(HtmlUtils.containsTag("<p>test</p>", null));
        Assert.assertFalse(HtmlUtils.containsTag("<p>test</p>", ""));
    }
}