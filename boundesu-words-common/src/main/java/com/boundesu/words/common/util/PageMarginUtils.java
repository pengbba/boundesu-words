package com.boundesu.words.common.util;

import com.boundesu.words.common.constants.PageConstants;

/**
 * 页边距工具类
 * 提供页边距计算、验证和转换等实用功能
 * 
 * @author Boundesu Team
 * @version 1.0.0
 */
public final class PageMarginUtils {
    
    /**
     * 私有构造函数，防止实例化
     */
    private PageMarginUtils() {
        throw new UnsupportedOperationException("工具类不能被实例化");
    }
    
    /**
     * 验证页边距值是否有效
     * 
     * @param margin 页边距值（磅）
     * @return 是否有效
     */
    public static boolean isValidMargin(double margin) {
        return margin >= PageConstants.MIN_MARGIN && margin <= PageConstants.MAX_MARGIN;
    }
    
    /**
     * 验证页边距值，如果无效则抛出异常
     * 
     * @param margin 页边距值（磅）
     * @param marginName 边距名称
     * @throws IllegalArgumentException 如果页边距无效
     */
    public static void validateMargin(double margin, String marginName) {
        if (!isValidMargin(margin)) {
            throw new IllegalArgumentException(
                String.format("%s值%.1f磅无效，必须在%.1f到%.1f磅之间", 
                    marginName, margin, PageConstants.MIN_MARGIN, PageConstants.MAX_MARGIN));
        }
    }
    
    /**
     * 计算页面可用宽度
     * 
     * @param pageWidth 页面宽度（磅）
     * @param leftMargin 左边距（磅）
     * @param rightMargin 右边距（磅）
     * @return 可用宽度（磅）
     */
    public static double calculateAvailableWidth(double pageWidth, double leftMargin, double rightMargin) {
        return pageWidth - leftMargin - rightMargin;
    }
    
    /**
     * 计算页面可用高度
     * 
     * @param pageHeight 页面高度（磅）
     * @param topMargin 上边距（磅）
     * @param bottomMargin 下边距（磅）
     * @return 可用高度（磅）
     */
    public static double calculateAvailableHeight(double pageHeight, double topMargin, double bottomMargin) {
        return pageHeight - topMargin - bottomMargin;
    }
    
    /**
     * 计算A4纸张的可用区域
     * 
     * @param marginConfig 边距配置
     * @return 可用区域信息
     */
    public static AvailableArea calculateA4AvailableArea(PageConstants.MarginConfig marginConfig) {
        double availableWidth = calculateAvailableWidth(
            PageConstants.A4_WIDTH, marginConfig.getLeft(), marginConfig.getRight());
        double availableHeight = calculateAvailableHeight(
            PageConstants.A4_HEIGHT, marginConfig.getTop(), marginConfig.getBottom());
        
        return new AvailableArea(availableWidth, availableHeight, 
            PageConstants.A4_WIDTH, PageConstants.A4_HEIGHT);
    }
    
    /**
     * 根据内容宽度推荐页边距
     * 
     * @param contentWidth 内容宽度（磅）
     * @param pageWidth 页面宽度（磅，默认A4）
     * @return 推荐的左右边距（磅）
     */
    public static double recommendHorizontalMargin(double contentWidth, double pageWidth) {
        if (pageWidth <= 0) {
            pageWidth = PageConstants.A4_WIDTH;
        }
        
        double margin = (pageWidth - contentWidth) / 2;
        
        // 确保边距在有效范围内
        if (margin < PageConstants.MIN_MARGIN) {
            return PageConstants.MIN_MARGIN;
        }
        if (margin > PageConstants.MAX_MARGIN) {
            return PageConstants.MAX_MARGIN;
        }
        
        return margin;
    }
    
    /**
     * 根据内容高度推荐页边距
     * 
     * @param contentHeight 内容高度（磅）
     * @param pageHeight 页面高度（磅，默认A4）
     * @return 推荐的上下边距（磅）
     */
    public static double recommendVerticalMargin(double contentHeight, double pageHeight) {
        if (pageHeight <= 0) {
            pageHeight = PageConstants.A4_HEIGHT;
        }
        
        double margin = (pageHeight - contentHeight) / 2;
        
        // 确保边距在有效范围内
        if (margin < PageConstants.MIN_MARGIN) {
            return PageConstants.MIN_MARGIN;
        }
        if (margin > PageConstants.MAX_MARGIN) {
            return PageConstants.MAX_MARGIN;
        }
        
        return margin;
    }
    
    /**
     * 创建对称页边距配置
     * 
     * @param horizontalMargin 水平边距（磅）
     * @param verticalMargin 垂直边距（磅）
     * @return 边距配置
     */
    public static PageConstants.MarginConfig createSymmetricMargins(double horizontalMargin, double verticalMargin) {
        validateMargin(horizontalMargin, "水平边距");
        validateMargin(verticalMargin, "垂直边距");
        
        return new PageConstants.MarginConfig(verticalMargin, verticalMargin, horizontalMargin, horizontalMargin);
    }
    
    /**
     * 创建装订友好的页边距配置
     * 
     * @param baseMargin 基础边距（磅）
     * @param bindingExtra 装订额外边距（磅）
     * @param isLeftBinding 是否左侧装订
     * @return 边距配置
     */
    public static PageConstants.MarginConfig createBindingFriendlyMargins(double baseMargin, double bindingExtra, boolean isLeftBinding) {
        validateMargin(baseMargin, "基础边距");
        validateMargin(bindingExtra, "装订额外边距");
        
        if (isLeftBinding) {
            return new PageConstants.MarginConfig(baseMargin, baseMargin, baseMargin + bindingExtra, baseMargin);
        } else {
            return new PageConstants.MarginConfig(baseMargin, baseMargin, baseMargin, baseMargin + bindingExtra);
        }
    }
    
    /**
     * 格式化页边距信息为可读字符串
     * 
     * @param marginConfig 边距配置
     * @return 格式化的字符串
     */
    public static String formatMarginInfo(PageConstants.MarginConfig marginConfig) {
        return String.format("页边距 - 上:%.1f磅, 下:%.1f磅, 左:%.1f磅, 右:%.1f磅",
            marginConfig.getTop(), marginConfig.getBottom(), 
            marginConfig.getLeft(), marginConfig.getRight());
    }
    
    /**
     * 可用区域信息类
     */
    public static class AvailableArea {
        private final double width;
        private final double height;
        private final double pageWidth;
        private final double pageHeight;
        
        public AvailableArea(double width, double height, double pageWidth, double pageHeight) {
            this.width = width;
            this.height = height;
            this.pageWidth = pageWidth;
            this.pageHeight = pageHeight;
        }
        
        public double getWidth() { return width; }
        public double getHeight() { return height; }
        public double getPageWidth() { return pageWidth; }
        public double getPageHeight() { return pageHeight; }
        
        /**
         * 获取可用区域占页面的百分比
         */
        public double getUsagePercentage() {
            double totalPageArea = pageWidth * pageHeight;
            double availableArea = width * height;
            return (availableArea / totalPageArea) * 100;
        }
        
        @Override
        public String toString() {
            return String.format("可用区域: %.1f×%.1f磅 (页面利用率: %.1f%%)", 
                width, height, getUsagePercentage());
        }
    }
}