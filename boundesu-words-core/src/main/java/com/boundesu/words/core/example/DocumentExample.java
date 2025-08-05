package com.boundesu.words.core.example;

import com.boundesu.words.core.Document;
import com.boundesu.words.core.builder.DocumentBuilder;
import com.boundesu.words.core.options.DocxSaveOptions;
import com.boundesu.words.core.options.HtmlLoadOptions;
import com.boundesu.words.core.options.LoadOptions;
import com.boundesu.words.core.options.PdfSaveOptions;

import java.awt.Color;
import java.io.IOException;

/**
 * DocumentExample demonstrates how to use the Boundesu Words core classes.
 * This example mimics the Aspose Words API usage patterns.
 */
public class DocumentExample {
    
    public static void main(String[] args) {
        try {
            // Example 1: Create a new document using DocumentBuilder
            createDocumentWithBuilder();
            
            // Example 2: Load and save documents with options
            loadAndSaveWithOptions();
            
            // Example 3: Format text and paragraphs
            formatTextAndParagraphs();
            
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void createDocumentWithBuilder() throws IOException {
        // Create a new document
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc.getInternalDocument());
        
        // Set font properties
        builder.getFont().setName("Arial");
        builder.getFont().setSize(12);
        builder.getFont().setBold(true);
        builder.getFont().setColor(Color.BLUE);
        
        // Write text
        builder.write("Hello, Boundesu Words!");
        builder.writeln("");
        
        // Set paragraph formatting
        builder.getParagraphFormat().setAlignment(DocumentBuilder.ParagraphAlignment.CENTER);
        builder.getParagraphFormat().setSpaceBefore(10);
        builder.getParagraphFormat().setSpaceAfter(10);
        
        // Write more text
        builder.getFont().setBold(false);
        builder.getFont().setItalic(true);
        builder.writeln("This is a sample document created with Boundesu Words.");
        
        // Insert a table
        DocumentBuilder.Table table = builder.startTable();
        System.out.println("Table created: " + (table != null ? "Success" : "Failed"));
        
        // Save the document
        doc.save("example_output.docx");
        System.out.println("Document saved as example_output.docx");
        
        doc.close();
    }
    
    private static void loadAndSaveWithOptions() throws IOException {
        // Load options for HTML
        HtmlLoadOptions htmlLoadOptions = new HtmlLoadOptions();
        htmlLoadOptions.setWebRequestTimeout(5000);
        htmlLoadOptions.setBlockImportMode(HtmlLoadOptions.BlockImportMode.PRESERVE);
        
        // Create a simple document for demonstration
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc.getInternalDocument());
        builder.writeln("Document with save options");
        
        // Save as DOCX with compression
        DocxSaveOptions docxOptions = new DocxSaveOptions();
        docxOptions.setCompressionLevel(DocxSaveOptions.CompressionLevel.MAXIMUM);
        docxOptions.setPassword("password123");
        doc.save("compressed_output.docx", docxOptions);
        System.out.println("Document saved as compressed DOCX with password");
        
        // Save as PDF with options
        PdfSaveOptions pdfOptions = new PdfSaveOptions();
        pdfOptions.setCompliance(PdfSaveOptions.PdfCompliance.PDF_A_1B);
        pdfOptions.setEmbedFullFonts(true);
        pdfOptions.setOptimizeOutput(true);
        pdfOptions.setJpegQuality(90);
        doc.save("output.pdf", pdfOptions);
        System.out.println("Document saved as PDF with PDF/A-1b compliance");
        
        doc.close();
    }
    
    private static void formatTextAndParagraphs() throws IOException {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc.getInternalDocument());
        
        // Title with large font
        builder.getFont().setName("Times New Roman");
        builder.getFont().setSize(18);
        builder.getFont().setBold(true);
        builder.getFont().setUnderline(DocumentBuilder.UnderlineType.SINGLE);
        builder.getParagraphFormat().setAlignment(DocumentBuilder.ParagraphAlignment.CENTER);
        builder.writeln("Document Formatting Example");
        
        // Reset formatting
        builder.getFont().clearFormatting();
        builder.getParagraphFormat().clearFormatting();
        
        // Normal paragraph
        builder.getFont().setName("Calibri");
        builder.getFont().setSize(11);
        builder.getParagraphFormat().setAlignment(DocumentBuilder.ParagraphAlignment.JUSTIFY);
        builder.getParagraphFormat().setFirstLineIndent(36);
        builder.getParagraphFormat().setSpaceAfter(6);
        
        builder.writeln("This is a normal paragraph with justified alignment and first line indent. " +
                       "It demonstrates the text formatting capabilities of Boundesu Words.");
        
        // Highlighted text
        builder.getFont().setHighlightColor(Color.YELLOW);
        builder.getFont().setBold(true);
        builder.write("This text is highlighted and bold. ");
        
        // Reset highlighting
        builder.getFont().setHighlightColor(null);
        builder.getFont().setBold(false);
        builder.writeln("This text is normal again.");
        
        // Subscript and superscript
        builder.write("Water formula: H");
        builder.getFont().setSubscript(true);
        builder.write("2");
        builder.getFont().setSubscript(false);
        builder.write("O. Einstein's equation: E=mc");
        builder.getFont().setSuperscript(true);
        builder.write("2");
        builder.getFont().setSuperscript(false);
        builder.writeln(".");
        
        // Page setup
        builder.getPageSetup().setPaperSize(DocumentBuilder.PaperSize.A4);
        builder.getPageSetup().setOrientation(DocumentBuilder.Orientation.PORTRAIT);
        builder.getPageSetup().setTopMargin(72);
        builder.getPageSetup().setBottomMargin(72);
        builder.getPageSetup().setLeftMargin(90);
        builder.getPageSetup().setRightMargin(90);
        
        // Save the formatted document
        doc.save("formatted_output.docx");
        System.out.println("Formatted document saved as formatted_output.docx");
        
        doc.close();
    }
}