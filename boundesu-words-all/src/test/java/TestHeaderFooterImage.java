import com.boundesu.words.BoundesuWordsAll;
import com.boundesu.words.BoundesuWordsSDK;
import com.boundesu.words.common.constants.HeaderFooterConstants;
import com.boundesu.words.common.creator.DocumentCreator;

import java.io.File;
import java.io.FileOutputStream;

public class TestHeaderFooterImage {
    public static void main(String[] args) {
        try {
            // 创建SDK实例
            BoundesuWordsAll sdk = new BoundesuWordsAll();

            System.out.println("=== 测试页头页脚图片功能 ===");

            // 测试1: 使用POI直接创建器测试基础页头页脚图片功能
            System.out.println("\n1. 测试POI创建器基础页头页脚图片功能");
            DocumentCreator poiCreator = BoundesuWordsSDK.createDocumentCreator("poi");

            // 设置页头和页脚图片（基础功能）
            poiCreator.setHeaderWithImage("基础页头", "test-image.jpg", 80, 40)
                    .setFooterWithImage("基础页脚", "test-image.jpg", 60, 30)
                    .setPageNumberEnabled(true)
                    .addHeading("基础页头页脚图片测试", 1)
                    .addParagraph("这是基础页头页脚图片功能测试。")
                    .addParagraph("页头图片尺寸: 80x40 像素")
                    .addParagraph("页脚图片尺寸: 60x30 像素");

            poiCreator.createDocument(new File("poi-basic-header-footer-test.docx").toPath());
            System.out.println("POI基础测试文档已保存为: poi-basic-header-footer-test.docx");

            // 测试1.1: 小尺寸图片测试
            System.out.println("\n1.1 测试小尺寸页头页脚图片");
            DocumentCreator smallImageCreator = BoundesuWordsSDK.createDocumentCreator("poi");

            smallImageCreator.setHeaderWithSmallImage("小尺寸页头", "test-image.jpg")
                    .setFooterWithSmallImage("小尺寸页脚", "test-image.jpg")
                    .addHeading("小尺寸图片测试", 1)
                    .addParagraph("页头页脚使用小尺寸图片 (" +
                            HeaderFooterConstants.SMALL_IMAGE_WIDTH + "x" +
                            HeaderFooterConstants.SMALL_IMAGE_HEIGHT + " 像素)");

            smallImageCreator.createDocument(new File("poi-small-image-test.docx").toPath());
            System.out.println("小尺寸图片测试文档已保存为: poi-small-image-test.docx");

            // 测试1.2: 大尺寸图片测试
            System.out.println("\n1.2 测试大尺寸页头页脚图片");
            DocumentCreator largeImageCreator = BoundesuWordsSDK.createDocumentCreator("poi");

            largeImageCreator.setHeaderWithLargeImage("大尺寸页头", "test-image.jpg")
                    .setFooterWithLargeImage("大尺寸页脚", "test-image.jpg")
                    .addHeading("大尺寸图片测试", 1)
                    .addParagraph("页头页脚使用大尺寸图片 (" +
                            HeaderFooterConstants.LARGE_IMAGE_WIDTH + "x" +
                            HeaderFooterConstants.LARGE_IMAGE_HEIGHT + " 像素)");

            largeImageCreator.createDocument(new File("poi-large-image-test.docx").toPath());
            System.out.println("大尺寸图片测试文档已保存为: poi-large-image-test.docx");

            // 测试1.3: 占满页面宽度图片测试
            System.out.println("\n1.3 测试占满页面宽度的页头页脚图片");
            DocumentCreator fullWidthCreator = BoundesuWordsSDK.createDocumentCreator("poi");

            fullWidthCreator.setHeaderWithFullWidthImage("占满宽度页头", "test-image.jpg")
                    .setFooterWithFullWidthImage("占满宽度页脚", "test-image.jpg")
                    .addHeading("占满页面宽度图片测试", 1)
                    .addParagraph("页头页脚图片占满页面宽度 (" +
                            HeaderFooterConstants.FULL_WIDTH_IMAGE_WIDTH + "x" +
                            HeaderFooterConstants.FULL_WIDTH_IMAGE_HEIGHT + " 像素)")
                    .addParagraph("这种设置适合用作页面背景或装饰性横幅。");

            fullWidthCreator.createDocument(new File("poi-full-width-test.docx").toPath());
            System.out.println("占满宽度图片测试文档已保存为: poi-full-width-test.docx");

            // 测试2: 使用HTML创建器测试页头页脚图片
            System.out.println("\n2. 测试HTML创建器的页头页脚图片功能");
            DocumentCreator htmlCreator = BoundesuWordsSDK.createDocumentCreator("html");

            htmlCreator.setHeaderWithImage("HTML页头", "test-image.jpg", 100, 50)
                    .setFooterWithImage("HTML页脚", "test-image.jpg", 80, 40)
                    .addHeading("HTML创建器页头页脚图片测试", 1)
                    .addParagraph("这是使用HTML创建器生成的测试文档。")
                    .addParagraph("页头和页脚都包含了test-image.jpg图片。");

            htmlCreator.createDocument(new File("html-header-footer-test.docx").toPath());
            System.out.println("HTML创建器测试文档已保存为: html-header-footer-test.docx");

            // 测试3: 使用XML创建器测试页头页脚图片
            System.out.println("\n3. 测试XML创建器的页头页脚图片功能");
            DocumentCreator xmlCreator = BoundesuWordsSDK.createDocumentCreator("xml");

            xmlCreator.setHeaderWithImage("XML页头", "test-image.jpg")
                    .setFooterWithImage("XML页脚", "test-image.jpg")
                    .addHeading("XML创建器页头页脚图片测试", 1)
                    .addParagraph("这是使用XML创建器生成的测试文档。")
                    .addParagraph("页头和页脚使用默认图片尺寸。");

            xmlCreator.createDocument(new File("xml-header-footer-test.docx").toPath());
            System.out.println("XML创建器测试文档已保存为: xml-header-footer-test.docx");

            // 测试4: 使用BoundesuWordsAll进行常规转换（作为对比）
            System.out.println("\n4. 常规HTML转DOCX转换（无页头页脚图片）");
            String htmlContent = "<html><body>" +
                    "<h1>常规转换测试文档</h1>" +
                    "<p>这是使用BoundesuWordsAll常规转换功能生成的文档。</p>" +
                    "<p>此文档不包含页头页脚图片，用作对比。</p>" +
                    "</body></html>";

            try (FileOutputStream fos = new FileOutputStream("regular-conversion-test.docx")) {
                sdk.htmlToDocx(htmlContent).write(fos);
                System.out.println("常规转换测试文档已保存为: regular-conversion-test.docx");
            }

            // 测试文件转换功能
            File inputFile = new File("test-input.html");
            if (!inputFile.exists()) {
                // 创建一个测试HTML文件
                try (FileOutputStream fos = new FileOutputStream(inputFile)) {
                    fos.write(htmlContent.getBytes("UTF-8"));
                }
            }

            // 测试文件转换
            try (FileOutputStream fos = new FileOutputStream("converted-test.docx")) {
                sdk.convertToDocx(inputFile).write(fos);
                System.out.println("转换测试文档已保存为: converted-test.docx");
            }

            System.out.println("\n=== 页头页脚图片功能测试完成 ===");
            System.out.println("已生成以下测试文档:");
            System.out.println("1. poi-basic-header-footer-test.docx - POI基础页头页脚图片");
            System.out.println("2. poi-small-image-test.docx - POI小尺寸图片 (" +
                    HeaderFooterConstants.SMALL_IMAGE_WIDTH + "x" +
                    HeaderFooterConstants.SMALL_IMAGE_HEIGHT + ")");
            System.out.println("3. poi-large-image-test.docx - POI大尺寸图片 (" +
                    HeaderFooterConstants.LARGE_IMAGE_WIDTH + "x" +
                    HeaderFooterConstants.LARGE_IMAGE_HEIGHT + ")");
            System.out.println("4. poi-full-width-test.docx - POI占满页面宽度图片 (" +
                    HeaderFooterConstants.FULL_WIDTH_IMAGE_WIDTH + "x" +
                    HeaderFooterConstants.FULL_WIDTH_IMAGE_HEIGHT + ")");
            System.out.println("5. html-converter-test.docx - HTML转换器，CSS样式页头页脚");
            System.out.println("6. xml-header-footer-test.docx - XML创建器，页头页脚带图片");
            System.out.println("7. regular-conversion-test.docx - 常规转换，无页头页脚图片");
            System.out.println("8. converted-test.docx - 文件转换测试");

            System.out.println("\n🎯 页头页脚图片功能特性:");
            System.out.println("✓ 基础功能: setHeaderWithImage() / setFooterWithImage()");
            System.out.println("✓ 预设尺寸: 小尺寸、默认、大尺寸、占满页面宽度");
            System.out.println("✓ 常量管理: HeaderFooterConstants类统一管理尺寸配置");
            System.out.println("✓ 便捷方法: setHeaderWithSmallImage(), setHeaderWithLargeImage(), setHeaderWithFullWidthImage()");
            System.out.println("✓ 灵活配置: 支持自定义图片尺寸和对齐方式");
            System.out.println("✓ 多种创建器: POI、HTML、XML创建器都支持页头页脚图片");

            System.out.println("\n📐 支持的图片尺寸:");
            System.out.println("• 小尺寸: " + HeaderFooterConstants.SMALL_IMAGE_WIDTH + "x" + HeaderFooterConstants.SMALL_IMAGE_HEIGHT + " 像素");
            System.out.println("• 默认尺寸: " + HeaderFooterConstants.DEFAULT_HEADER_IMAGE_WIDTH + "x" + HeaderFooterConstants.DEFAULT_HEADER_IMAGE_HEIGHT + " 像素");
            System.out.println("• 大尺寸: " + HeaderFooterConstants.LARGE_IMAGE_WIDTH + "x" + HeaderFooterConstants.LARGE_IMAGE_HEIGHT + " 像素");
            System.out.println("• 占满宽度: " + HeaderFooterConstants.FULL_WIDTH_IMAGE_WIDTH + "x" + HeaderFooterConstants.FULL_WIDTH_IMAGE_HEIGHT + " 像素");

            System.out.println("\n💡 使用建议:");
            System.out.println("• 小尺寸适合简单图标或Logo");
            System.out.println("• 大尺寸适合详细的公司标识");
            System.out.println("• 占满宽度适合装饰性横幅或背景");
            System.out.println("• 所有配置都通过HeaderFooterConstants类统一管理，便于维护");

        } catch (Exception e) {
            System.err.println("测试过程中发生错误: " + e.getMessage());
            e.printStackTrace();
        }
    }
}