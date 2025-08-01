package com.boundesu.words.common.util;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * StringUtils 测试类
 * 
 * @author Boundesu
 * @version 1.0.0
 */
public class StringUtilsTest {
    
    @Test
    public void testIsEmpty() {
        Assert.assertTrue(StringUtils.isEmpty(null));
        Assert.assertTrue(StringUtils.isEmpty(""));
        Assert.assertFalse(StringUtils.isEmpty(" "));
        Assert.assertFalse(StringUtils.isEmpty("hello"));
    }
    
    @Test
    public void testIsNotEmpty() {
        Assert.assertFalse(StringUtils.isNotEmpty(null));
        Assert.assertFalse(StringUtils.isNotEmpty(""));
        Assert.assertTrue(StringUtils.isNotEmpty(" "));
        Assert.assertTrue(StringUtils.isNotEmpty("hello"));
    }
    
    @Test
    public void testIsBlank() {
        Assert.assertTrue(StringUtils.isBlank(null));
        Assert.assertTrue(StringUtils.isBlank(""));
        Assert.assertTrue(StringUtils.isBlank(" "));
        Assert.assertTrue(StringUtils.isBlank("\t\n\r"));
        Assert.assertFalse(StringUtils.isBlank("hello"));
        Assert.assertFalse(StringUtils.isBlank(" hello "));
    }
    
    @Test
    public void testIsNotBlank() {
        Assert.assertFalse(StringUtils.isNotBlank(null));
        Assert.assertFalse(StringUtils.isNotBlank(""));
        Assert.assertFalse(StringUtils.isNotBlank(" "));
        Assert.assertFalse(StringUtils.isNotBlank("\t\n\r"));
        Assert.assertTrue(StringUtils.isNotBlank("hello"));
        Assert.assertTrue(StringUtils.isNotBlank(" hello "));
    }
    
    @Test
    public void testSafeToString() {
        Assert.assertEquals(StringUtils.safeToString(null), "");
        Assert.assertEquals(StringUtils.safeToString(""), "");
        Assert.assertEquals(StringUtils.safeToString("hello"), "hello");
        Assert.assertEquals(StringUtils.safeToString(123), "123");
        Assert.assertEquals(StringUtils.safeToString(true), "true");
    }
    
    @Test
    public void testSafeToStringWithDefault() {
        Assert.assertEquals(StringUtils.safeToString(null, "default"), "default");
        Assert.assertEquals(StringUtils.safeToString("", "default"), "");
        Assert.assertEquals(StringUtils.safeToString("hello", "default"), "hello");
        Assert.assertEquals(StringUtils.safeToString(123, "default"), "123");
    }
    
    @Test
    public void testCapitalize() {
        Assert.assertEquals(StringUtils.capitalize(null), null);
        Assert.assertEquals(StringUtils.capitalize(""), "");
        Assert.assertEquals(StringUtils.capitalize("hello"), "Hello");
        Assert.assertEquals(StringUtils.capitalize("Hello"), "Hello");
        Assert.assertEquals(StringUtils.capitalize("HELLO"), "HELLO");
        Assert.assertEquals(StringUtils.capitalize("h"), "H");
    }
    
    @Test
    public void testUncapitalize() {
        Assert.assertEquals(StringUtils.uncapitalize(null), null);
        Assert.assertEquals(StringUtils.uncapitalize(""), "");
        Assert.assertEquals(StringUtils.uncapitalize("Hello"), "hello");
        Assert.assertEquals(StringUtils.uncapitalize("hello"), "hello");
        Assert.assertEquals(StringUtils.uncapitalize("HELLO"), "hELLO");
        Assert.assertEquals(StringUtils.uncapitalize("H"), "h");
    }
    
    @Test
    public void testTrim() {
        Assert.assertEquals(StringUtils.trim(null), null);
        Assert.assertEquals(StringUtils.trim(""), "");
        Assert.assertEquals(StringUtils.trim(" hello "), "hello");
        Assert.assertEquals(StringUtils.trim("\t\nhello\r\n"), "hello");
        Assert.assertEquals(StringUtils.trim("hello"), "hello");
    }
    
    @Test
    public void testTrimToEmpty() {
        Assert.assertEquals(StringUtils.trimToEmpty(null), "");
        Assert.assertEquals(StringUtils.trimToEmpty(""), "");
        Assert.assertEquals(StringUtils.trimToEmpty(" hello "), "hello");
        Assert.assertEquals(StringUtils.trimToEmpty("\t\nhello\r\n"), "hello");
        Assert.assertEquals(StringUtils.trimToEmpty("hello"), "hello");
    }
    
    @Test
    public void testDefaultIfEmpty() {
        Assert.assertEquals(StringUtils.defaultIfEmpty(null, "default"), "default");
        Assert.assertEquals(StringUtils.defaultIfEmpty("", "default"), "default");
        Assert.assertEquals(StringUtils.defaultIfEmpty("hello", "default"), "hello");
        Assert.assertEquals(StringUtils.defaultIfEmpty(" ", "default"), " ");
    }
    
    @Test
    public void testDefaultIfBlank() {
        Assert.assertEquals(StringUtils.defaultIfBlank(null, "default"), "default");
        Assert.assertEquals(StringUtils.defaultIfBlank("", "default"), "default");
        Assert.assertEquals(StringUtils.defaultIfBlank(" ", "default"), "default");
        Assert.assertEquals(StringUtils.defaultIfBlank("\t\n", "default"), "default");
        Assert.assertEquals(StringUtils.defaultIfBlank("hello", "default"), "hello");
        Assert.assertEquals(StringUtils.defaultIfBlank(" hello ", "default"), " hello ");
    }
}