package com.boundesu.words.core.builder;

import org.apache.poi.xwpf.usermodel.*;
import org.apache.poi.util.Units;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * DocumentBuilder provides methods for building Word documents.
 * This class mimics the Aspose Words DocumentBuilder API.
 */
public class DocumentBuilder {
    private XWPFDocument document;
    private XWPFParagraph currentParagraph;
    private XWPFRun currentRun;
    private Font font;
    private ParagraphFormat paragraphFormat;
    private PageSetup pageSetup;

    public DocumentBuilder() {
        this.document = new XWPFDocument();
        this.font = new Font();
        this.paragraphFormat = new ParagraphFormat();
        this.pageSetup = new PageSetup();
        createNewParagraph();
    }

    public DocumentBuilder(XWPFDocument document) {
        this.document = document;
        this.font = new Font();
        this.paragraphFormat = new ParagraphFormat();
        this.pageSetup = new PageSetup();
        createNewParagraph();
    }

    private void createNewParagraph() {
        this.currentParagraph = document.createParagraph();
        this.currentRun = currentParagraph.createRun();
    }

    public XWPFDocument getDocument() {
        return document;
    }

    public Font getFont() {
        return font;
    }

    public ParagraphFormat getParagraphFormat() {
        return paragraphFormat;
    }

    public PageSetup getPageSetup() {
        return pageSetup;
    }

    public void write(String text) {
        if (currentRun == null) {
            createNewParagraph();
        }
        currentRun.setText(text);
        applyFontFormatting();
    }

    public void writeln(String text) {
        write(text);
        insertBreak();
    }

    public void insertBreak() {
        currentRun.addBreak();
        createNewParagraph();
    }

    public void insertParagraph() {
        createNewParagraph();
        applyParagraphFormatting();
    }

    private void applyFontFormatting() {
        if (currentRun != null && font != null) {
            currentRun.setFontFamily(font.getName());
            currentRun.setFontSize((int) font.getSize());
            currentRun.setBold(font.isBold());
            currentRun.setItalic(font.isItalic());
            
            if (font.getUnderline() != UnderlineType.NONE) {
                currentRun.setUnderline(UnderlinePatterns.SINGLE);
            }
            
            if (font.getColor() != null) {
                currentRun.setColor(String.format("%02x%02x%02x", 
                    font.getColor().getRed(), 
                    font.getColor().getGreen(), 
                    font.getColor().getBlue()));
            }
        }
    }

    private void applyParagraphFormatting() {
        if (currentParagraph != null && paragraphFormat != null) {
            switch (paragraphFormat.getAlignment()) {
                case LEFT:
                    currentParagraph.setAlignment(org.apache.poi.xwpf.usermodel.ParagraphAlignment.LEFT);
                    break;
                case CENTER:
                    currentParagraph.setAlignment(org.apache.poi.xwpf.usermodel.ParagraphAlignment.CENTER);
                    break;
                case RIGHT:
                    currentParagraph.setAlignment(org.apache.poi.xwpf.usermodel.ParagraphAlignment.RIGHT);
                    break;
                case JUSTIFY:
                    currentParagraph.setAlignment(org.apache.poi.xwpf.usermodel.ParagraphAlignment.BOTH);
                    break;
            }
            
            currentParagraph.setIndentationLeft((int) paragraphFormat.getLeftIndent());
            currentParagraph.setIndentationRight((int) paragraphFormat.getRightIndent());
            currentParagraph.setIndentationFirstLine((int) paragraphFormat.getFirstLineIndent());
            currentParagraph.setSpacingBefore((int) paragraphFormat.getSpaceBefore());
            currentParagraph.setSpacingAfter((int) paragraphFormat.getSpaceAfter());
        }
    }

    public Table startTable() {
        XWPFTable table = document.createTable();
        return new Table(table);
    }

    public Shape insertImage(String imagePath) throws IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
        File imageFile = new File(imagePath);
        if (!imageFile.exists()) {
            throw new IOException("Image file not found: " + imagePath);
        }
        
        String fileName = imageFile.getName().toLowerCase();
        int format;
        if (fileName.endsWith(".png")) {
            format = XWPFDocument.PICTURE_TYPE_PNG;
        } else if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
            format = XWPFDocument.PICTURE_TYPE_JPEG;
        } else if (fileName.endsWith(".gif")) {
            format = XWPFDocument.PICTURE_TYPE_GIF;
        } else {
            throw new IOException("Unsupported image format: " + fileName);
        }
        
        try (FileInputStream fis = new FileInputStream(imageFile)) {
            currentRun.addPicture(fis, format, fileName, 200, 200);
        }
        
        return new Shape();
    }

    public Field insertField(String fieldCode) {
        if (fieldCode.startsWith("PAGE")) {
            currentRun.setText("1");
        } else if (fieldCode.startsWith("DATE")) {
            currentRun.setText(new java.util.Date().toString());
        } else {
            currentRun.setText("[" + fieldCode + "]");
        }
        return new Field(fieldCode);
    }

    public void insertHyperlink(String address, String text) {
        String id = document.getPackagePart().addExternalRelationship(
            address, XWPFRelation.HYPERLINK.getRelation()).getId();
        
        CTHyperlink hyperlink = currentParagraph.getCTP().addNewHyperlink();
        hyperlink.setId(id);
        
        CTText ctText = CTText.Factory.newInstance();
        ctText.setStringValue(text);
        CTR ctr = CTR.Factory.newInstance();
        ctr.setTArray(new CTText[]{ctText});
        hyperlink.setRArray(new CTR[]{ctr});
    }

    public BookmarkStart startBookmark(String name) {
        return new BookmarkStart(name);
    }

    public BookmarkEnd endBookmark(String name) {
        return new BookmarkEnd(name);
    }

    public void insertHtml(String html) {
        String cleanText = html.replaceAll("<[^>]+>", "");
        write(cleanText);
    }

    // Inner classes
    public static class Font {
        private String name = "Calibri";
        private double size = 11.0;
        private boolean bold = false;
        private boolean italic = false;
        private UnderlineType underline = UnderlineType.NONE;
        private Color color = Color.BLACK;
        private boolean strikeThrough = false;
        private boolean doubleStrikeThrough = false;
        private boolean subscript = false;
        private boolean superscript = false;
        private boolean smallCaps = false;
        private boolean allCaps = false;
        private boolean hidden = false;
        private double spacing = 0.0;
        private double position = 0.0;
        private int scaling = 100;
        private Color highlightColor;
        private EmphasisMark emphasisMark = EmphasisMark.NONE;

        // Getters and setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        
        public double getSize() { return size; }
        public void setSize(double size) { this.size = size; }
        
        public boolean isBold() { return bold; }
        public void setBold(boolean bold) { this.bold = bold; }
        
        public boolean isItalic() { return italic; }
        public void setItalic(boolean italic) { this.italic = italic; }
        
        public UnderlineType getUnderline() { return underline; }
        public void setUnderline(UnderlineType underline) { this.underline = underline; }
        
        public Color getColor() { return color; }
        public void setColor(Color color) { this.color = color; }
        
        public boolean isStrikeThrough() { return strikeThrough; }
        public void setStrikeThrough(boolean strikeThrough) { this.strikeThrough = strikeThrough; }
        
        public boolean isDoubleStrikeThrough() { return doubleStrikeThrough; }
        public void setDoubleStrikeThrough(boolean doubleStrikeThrough) { this.doubleStrikeThrough = doubleStrikeThrough; }
        
        public boolean isSubscript() { return subscript; }
        public void setSubscript(boolean subscript) { this.subscript = subscript; }
        
        public boolean isSuperscript() { return superscript; }
        public void setSuperscript(boolean superscript) { this.superscript = superscript; }
        
        public boolean isSmallCaps() { return smallCaps; }
        public void setSmallCaps(boolean smallCaps) { this.smallCaps = smallCaps; }
        
        public boolean isAllCaps() { return allCaps; }
        public void setAllCaps(boolean allCaps) { this.allCaps = allCaps; }
        
        public boolean isHidden() { return hidden; }
        public void setHidden(boolean hidden) { this.hidden = hidden; }
        
        public double getSpacing() { return spacing; }
        public void setSpacing(double spacing) { this.spacing = spacing; }
        
        public double getPosition() { return position; }
        public void setPosition(double position) { this.position = position; }
        
        public int getScaling() { return scaling; }
        public void setScaling(int scaling) { this.scaling = scaling; }
        
        public Color getHighlightColor() { return highlightColor; }
        public void setHighlightColor(Color highlightColor) { this.highlightColor = highlightColor; }
        
        public EmphasisMark getEmphasisMark() { return emphasisMark; }
        public void setEmphasisMark(EmphasisMark emphasisMark) { this.emphasisMark = emphasisMark; }
        
        public void clearFormatting() {
            this.name = "Calibri";
            this.size = 11.0;
            this.bold = false;
            this.italic = false;
            this.underline = UnderlineType.NONE;
            this.color = Color.BLACK;
            this.strikeThrough = false;
            this.doubleStrikeThrough = false;
            this.subscript = false;
            this.superscript = false;
            this.smallCaps = false;
            this.allCaps = false;
            this.hidden = false;
            this.spacing = 0.0;
            this.position = 0.0;
            this.scaling = 100;
            this.highlightColor = null;
            this.emphasisMark = EmphasisMark.NONE;
        }
    }

    public static class ParagraphFormat {
        private ParagraphAlignment alignment = ParagraphAlignment.LEFT;
        private double leftIndent = 0.0;
        private double rightIndent = 0.0;
        private double firstLineIndent = 0.0;
        private double spaceBefore = 0.0;
        private double spaceAfter = 0.0;
        private double lineSpacing = 1.0;
        private LineSpacingRule lineSpacingRule = LineSpacingRule.AT_LEAST;
        private boolean keepTogether = false;
        private boolean keepWithNext = false;
        private boolean pageBreakBefore = false;
        private boolean widowControl = true;
        private int outlineLevel = 0;
        private boolean suppressAutoHyphens = false;
        private boolean suppressLineNumbers = false;
        private boolean noSpaceBetweenParagraphsOfSameStyle = false;
        private String styleName;
        private TabStopCollection tabStops = new TabStopCollection();
        private Shading shading = new Shading();
        private Borders borders = new Borders();

        // Getters and setters
        public ParagraphAlignment getAlignment() { return alignment; }
        public void setAlignment(ParagraphAlignment alignment) { this.alignment = alignment; }
        
        public double getLeftIndent() { return leftIndent; }
        public void setLeftIndent(double leftIndent) { this.leftIndent = leftIndent; }
        
        public double getRightIndent() { return rightIndent; }
        public void setRightIndent(double rightIndent) { this.rightIndent = rightIndent; }
        
        public double getFirstLineIndent() { return firstLineIndent; }
        public void setFirstLineIndent(double firstLineIndent) { this.firstLineIndent = firstLineIndent; }
        
        public double getSpaceBefore() { return spaceBefore; }
        public void setSpaceBefore(double spaceBefore) { this.spaceBefore = spaceBefore; }
        
        public double getSpaceAfter() { return spaceAfter; }
        public void setSpaceAfter(double spaceAfter) { this.spaceAfter = spaceAfter; }
        
        public double getLineSpacing() { return lineSpacing; }
        public void setLineSpacing(double lineSpacing) { this.lineSpacing = lineSpacing; }
        
        public LineSpacingRule getLineSpacingRule() { return lineSpacingRule; }
        public void setLineSpacingRule(LineSpacingRule lineSpacingRule) { this.lineSpacingRule = lineSpacingRule; }
        
        public boolean isKeepTogether() { return keepTogether; }
        public void setKeepTogether(boolean keepTogether) { this.keepTogether = keepTogether; }
        
        public boolean isKeepWithNext() { return keepWithNext; }
        public void setKeepWithNext(boolean keepWithNext) { this.keepWithNext = keepWithNext; }
        
        public boolean isPageBreakBefore() { return pageBreakBefore; }
        public void setPageBreakBefore(boolean pageBreakBefore) { this.pageBreakBefore = pageBreakBefore; }
        
        public boolean isWidowControl() { return widowControl; }
        public void setWidowControl(boolean widowControl) { this.widowControl = widowControl; }
        
        public int getOutlineLevel() { return outlineLevel; }
        public void setOutlineLevel(int outlineLevel) { this.outlineLevel = outlineLevel; }
        
        public boolean isSuppressAutoHyphens() { return suppressAutoHyphens; }
        public void setSuppressAutoHyphens(boolean suppressAutoHyphens) { this.suppressAutoHyphens = suppressAutoHyphens; }
        
        public boolean isSuppressLineNumbers() { return suppressLineNumbers; }
        public void setSuppressLineNumbers(boolean suppressLineNumbers) { this.suppressLineNumbers = suppressLineNumbers; }
        
        public boolean isNoSpaceBetweenParagraphsOfSameStyle() { return noSpaceBetweenParagraphsOfSameStyle; }
        public void setNoSpaceBetweenParagraphsOfSameStyle(boolean noSpaceBetweenParagraphsOfSameStyle) { this.noSpaceBetweenParagraphsOfSameStyle = noSpaceBetweenParagraphsOfSameStyle; }
        
        public String getStyleName() { return styleName; }
        public void setStyleName(String styleName) { this.styleName = styleName; }
        
        public TabStopCollection getTabStops() { return tabStops; }
        public void setTabStops(TabStopCollection tabStops) { this.tabStops = tabStops; }
        
        public Shading getShading() { return shading; }
        public void setShading(Shading shading) { this.shading = shading; }
        
        public Borders getBorders() { return borders; }
        public void setBorders(Borders borders) { this.borders = borders; }
        
        public void clearFormatting() {
            this.alignment = ParagraphAlignment.LEFT;
            this.leftIndent = 0.0;
            this.rightIndent = 0.0;
            this.firstLineIndent = 0.0;
            this.spaceBefore = 0.0;
            this.spaceAfter = 0.0;
            this.lineSpacing = 1.0;
            this.lineSpacingRule = LineSpacingRule.AT_LEAST;
            this.keepTogether = false;
            this.keepWithNext = false;
            this.pageBreakBefore = false;
            this.widowControl = true;
            this.outlineLevel = 0;
            this.suppressAutoHyphens = false;
            this.suppressLineNumbers = false;
            this.noSpaceBetweenParagraphsOfSameStyle = false;
            this.styleName = null;
            this.tabStops = new TabStopCollection();
            this.shading = new Shading();
            this.borders = new Borders();
        }
    }

    public static class PageSetup {
        private PaperSize paperSize = PaperSize.A4;
        private Orientation orientation = Orientation.PORTRAIT;
        private double topMargin = 72.0;
        private double bottomMargin = 72.0;
        private double leftMargin = 72.0;
        private double rightMargin = 72.0;
        private double headerDistance = 36.0;
        private double footerDistance = 36.0;
        private double gutter = 0.0;
        private GutterPosition gutterPos = GutterPosition.LEFT;
        private double pageWidth = 595.0;
        private double pageHeight = 842.0;
        private boolean differentFirstPageHeaderFooter = false;
        private boolean oddAndEvenPagesHeaderFooter = false;
        private SectionStart sectionStart = SectionStart.NEW_PAGE;
        private boolean suppressEndnotes = false;
        private VerticalAlignment verticalAlignment = VerticalAlignment.TOP;
        private PageNumberStyle pageNumberStyle = PageNumberStyle.ARABIC;
        private int pageStartingNumber = 1;
        private boolean restartPageNumbering = false;
        private int lineNumberCountBy = 1;
        private double lineNumberDistanceFromText = 36.0;
        private LineNumberRestartMode lineNumberRestartMode = LineNumberRestartMode.RESTART_PAGE;
        private int lineNumberStartingValue = 1;
        private String layoutMode;

        // Getters and setters
        public PaperSize getPaperSize() { return paperSize; }
        public void setPaperSize(PaperSize paperSize) { this.paperSize = paperSize; }
        
        public Orientation getOrientation() { return orientation; }
        public void setOrientation(Orientation orientation) { this.orientation = orientation; }
        
        public double getTopMargin() { return topMargin; }
        public void setTopMargin(double topMargin) { this.topMargin = topMargin; }
        
        public double getBottomMargin() { return bottomMargin; }
        public void setBottomMargin(double bottomMargin) { this.bottomMargin = bottomMargin; }
        
        public double getLeftMargin() { return leftMargin; }
        public void setLeftMargin(double leftMargin) { this.leftMargin = leftMargin; }
        
        public double getRightMargin() { return rightMargin; }
        public void setRightMargin(double rightMargin) { this.rightMargin = rightMargin; }
        
        public double getHeaderDistance() { return headerDistance; }
        public void setHeaderDistance(double headerDistance) { this.headerDistance = headerDistance; }
        
        public double getFooterDistance() { return footerDistance; }
        public void setFooterDistance(double footerDistance) { this.footerDistance = footerDistance; }
        
        public double getGutter() { return gutter; }
        public void setGutter(double gutter) { this.gutter = gutter; }
        
        public GutterPosition getGutterPos() { return gutterPos; }
        public void setGutterPos(GutterPosition gutterPos) { this.gutterPos = gutterPos; }
        
        public double getPageWidth() { return pageWidth; }
        public void setPageWidth(double pageWidth) { this.pageWidth = pageWidth; }
        
        public double getPageHeight() { return pageHeight; }
        public void setPageHeight(double pageHeight) { this.pageHeight = pageHeight; }
        
        public boolean isDifferentFirstPageHeaderFooter() { return differentFirstPageHeaderFooter; }
        public void setDifferentFirstPageHeaderFooter(boolean differentFirstPageHeaderFooter) { this.differentFirstPageHeaderFooter = differentFirstPageHeaderFooter; }
        
        public boolean isOddAndEvenPagesHeaderFooter() { return oddAndEvenPagesHeaderFooter; }
        public void setOddAndEvenPagesHeaderFooter(boolean oddAndEvenPagesHeaderFooter) { this.oddAndEvenPagesHeaderFooter = oddAndEvenPagesHeaderFooter; }
        
        public SectionStart getSectionStart() { return sectionStart; }
        public void setSectionStart(SectionStart sectionStart) { this.sectionStart = sectionStart; }
        
        public boolean isSuppressEndnotes() { return suppressEndnotes; }
        public void setSuppressEndnotes(boolean suppressEndnotes) { this.suppressEndnotes = suppressEndnotes; }
        
        public VerticalAlignment getVerticalAlignment() { return verticalAlignment; }
        public void setVerticalAlignment(VerticalAlignment verticalAlignment) { this.verticalAlignment = verticalAlignment; }
        
        public PageNumberStyle getPageNumberStyle() { return pageNumberStyle; }
        public void setPageNumberStyle(PageNumberStyle pageNumberStyle) { this.pageNumberStyle = pageNumberStyle; }
        
        public int getPageStartingNumber() { return pageStartingNumber; }
        public void setPageStartingNumber(int pageStartingNumber) { this.pageStartingNumber = pageStartingNumber; }
        
        public boolean isRestartPageNumbering() { return restartPageNumbering; }
        public void setRestartPageNumbering(boolean restartPageNumbering) { this.restartPageNumbering = restartPageNumbering; }
        
        public int getLineNumberCountBy() { return lineNumberCountBy; }
        public void setLineNumberCountBy(int lineNumberCountBy) { this.lineNumberCountBy = lineNumberCountBy; }
        
        public double getLineNumberDistanceFromText() { return lineNumberDistanceFromText; }
        public void setLineNumberDistanceFromText(double lineNumberDistanceFromText) { this.lineNumberDistanceFromText = lineNumberDistanceFromText; }
        
        public LineNumberRestartMode getLineNumberRestartMode() { return lineNumberRestartMode; }
        public void setLineNumberRestartMode(LineNumberRestartMode lineNumberRestartMode) { this.lineNumberRestartMode = lineNumberRestartMode; }
        
        public int getLineNumberStartingValue() { return lineNumberStartingValue; }
        public void setLineNumberStartingValue(int lineNumberStartingValue) { this.lineNumberStartingValue = lineNumberStartingValue; }
        
        public String getLayoutMode() { return layoutMode; }
        public void setLayoutMode(String layoutMode) { this.layoutMode = layoutMode; }
        
        public void clearFormatting() {
            this.paperSize = PaperSize.A4;
            this.orientation = Orientation.PORTRAIT;
            this.topMargin = 72.0;
            this.bottomMargin = 72.0;
            this.leftMargin = 72.0;
            this.rightMargin = 72.0;
            this.headerDistance = 36.0;
            this.footerDistance = 36.0;
            this.gutter = 0.0;
            this.gutterPos = GutterPosition.LEFT;
            this.pageWidth = 595.0;
            this.pageHeight = 842.0;
            this.differentFirstPageHeaderFooter = false;
            this.oddAndEvenPagesHeaderFooter = false;
            this.sectionStart = SectionStart.NEW_PAGE;
            this.suppressEndnotes = false;
            this.verticalAlignment = VerticalAlignment.TOP;
            this.pageNumberStyle = PageNumberStyle.ARABIC;
            this.pageStartingNumber = 1;
            this.restartPageNumbering = false;
            this.lineNumberCountBy = 1;
            this.lineNumberDistanceFromText = 36.0;
            this.lineNumberRestartMode = LineNumberRestartMode.RESTART_PAGE;
            this.lineNumberStartingValue = 1;
            this.layoutMode = null;
        }
    }

    // Supporting classes
    public static class Table {
        private XWPFTable table;
        
        public Table(XWPFTable table) {
            this.table = table;
        }
        
        public XWPFTable getTable() {
            return table;
        }
    }

    public static class Shape {
        // Shape implementation
    }

    public static class Field {
        private String fieldCode;
        
        public Field(String fieldCode) {
            this.fieldCode = fieldCode;
        }
        
        public String getFieldCode() {
            return fieldCode;
        }
    }

    public static class BookmarkStart {
        private String name;
        
        public BookmarkStart(String name) {
            this.name = name;
        }
        
        public String getName() {
            return name;
        }
    }

    public static class BookmarkEnd {
        private String name;
        
        public BookmarkEnd(String name) {
            this.name = name;
        }
        
        public String getName() {
            return name;
        }
    }

    // Enums
    public enum UnderlineType {
        NONE, SINGLE, DOUBLE, THICK, DOTTED, DASHED, DASH_DOT, DASH_DOT_DOT, WAVY
    }

    public enum EmphasisMark {
        NONE, DOT_ABOVE, COMMA_ABOVE, CIRCLE_ABOVE, DOT_BELOW
    }

    public enum ParagraphAlignment {
        LEFT, CENTER, RIGHT, JUSTIFY, DISTRIBUTED
    }

    public enum LineSpacingRule {
        AT_LEAST, EXACTLY, MULTIPLE
    }

    public enum PaperSize {
        A3, A4, A5, B4, B5, EXECUTIVE, FOLIO, LEDGER, LEGAL, LETTER, QUARTO, STATEMENT, TABLOID
    }

    public enum Orientation {
        PORTRAIT, LANDSCAPE
    }

    public enum GutterPosition {
        LEFT, TOP
    }

    public enum SectionStart {
        CONTINUOUS, NEW_COLUMN, NEW_PAGE, EVEN_PAGE, ODD_PAGE
    }

    public enum VerticalAlignment {
        TOP, CENTER, JUSTIFY, BOTTOM
    }

    public enum PageNumberStyle {
        ARABIC, UPPERCASE_ROMAN, LOWERCASE_ROMAN, UPPERCASE_LETTER, LOWERCASE_LETTER
    }

    public enum LineNumberRestartMode {
        CONTINUOUS, RESTART_PAGE, RESTART_SECTION
    }

    // Supporting classes for formatting
    public static class TabStopCollection {
        private List<TabStop> tabStops = new ArrayList<>();
        
        public void add(TabStop tabStop) {
            tabStops.add(tabStop);
        }
        
        public void clear() {
            tabStops.clear();
        }
        
        public List<TabStop> getTabStops() {
            return tabStops;
        }
    }

    public static class TabStop {
        private double position;
        private TabAlignment alignment;
        private TabLeader leader;
        
        public TabStop(double position, TabAlignment alignment, TabLeader leader) {
            this.position = position;
            this.alignment = alignment;
            this.leader = leader;
        }
        
        public double getPosition() { return position; }
        public void setPosition(double position) { this.position = position; }
        
        public TabAlignment getAlignment() { return alignment; }
        public void setAlignment(TabAlignment alignment) { this.alignment = alignment; }
        
        public TabLeader getLeader() { return leader; }
        public void setLeader(TabLeader leader) { this.leader = leader; }
    }

    public enum TabAlignment {
        LEFT, CENTER, RIGHT, DECIMAL, BAR
    }

    public enum TabLeader {
        NONE, DOTS, DASHES, UNDERLINE, THICK, EQUALS
    }

    public static class Shading {
        private Color backgroundColor;
        private Color foregroundPatternColor;
        private TextureIndex texture;
        
        public Color getBackgroundPatternColor() { return backgroundColor; }
        public void setBackgroundPatternColor(Color backgroundColor) { this.backgroundColor = backgroundColor; }
        
        public Color getForegroundPatternColor() { return foregroundPatternColor; }
        public void setForegroundPatternColor(Color foregroundPatternColor) { this.foregroundPatternColor = foregroundPatternColor; }
        
        public TextureIndex getTexture() { return texture; }
        public void setTexture(TextureIndex texture) { this.texture = texture; }
    }

    public enum TextureIndex {
        TEXTURE_NONE, TEXTURE_SOLID, TEXTURE_5_PERCENT, TEXTURE_10_PERCENT, TEXTURE_20_PERCENT,
        TEXTURE_25_PERCENT, TEXTURE_30_PERCENT, TEXTURE_40_PERCENT, TEXTURE_50_PERCENT,
        TEXTURE_60_PERCENT, TEXTURE_70_PERCENT, TEXTURE_75_PERCENT, TEXTURE_80_PERCENT,
        TEXTURE_90_PERCENT, TEXTURE_DARK_HORIZONTAL, TEXTURE_DARK_VERTICAL, TEXTURE_DARK_DIAGONAL_DOWN,
        TEXTURE_DARK_DIAGONAL_UP, TEXTURE_DARK_CROSS, TEXTURE_DARK_DIAGONAL_CROSS
    }

    public static class Borders {
        private Border top;
        private Border bottom;
        private Border left;
        private Border right;
        
        public Borders() {
            this.top = new Border();
            this.bottom = new Border();
            this.left = new Border();
            this.right = new Border();
        }
        
        public Border getTop() { return top; }
        public void setTop(Border top) { this.top = top; }
        
        public Border getBottom() { return bottom; }
        public void setBottom(Border bottom) { this.bottom = bottom; }
        
        public Border getLeft() { return left; }
        public void setLeft(Border left) { this.left = left; }
        
        public Border getRight() { return right; }
        public void setRight(Border right) { this.right = right; }
        
        public Border get(BorderType borderType) {
            switch (borderType) {
                case TOP: return top;
                case BOTTOM: return bottom;
                case LEFT: return left;
                case RIGHT: return right;
                default: return null;
            }
        }
    }

    public static class Border {
        private BorderType borderType;
        private double lineWidth;
        private Color color;
        private boolean isVisible;
        
        public BorderType getBorderType() { return borderType; }
        public void setBorderType(BorderType borderType) { this.borderType = borderType; }
        
        public double getLineWidth() { return lineWidth; }
        public void setLineWidth(double lineWidth) { this.lineWidth = lineWidth; }
        
        public Color getColor() { return color; }
        public void setColor(Color color) { this.color = color; }
        
        public boolean isVisible() { return isVisible; }
        public void setVisible(boolean visible) { isVisible = visible; }
    }

    public enum BorderType {
        NONE, SINGLE, THICK, DOUBLE, HAIRLINE, DOT, DASH_DOT, DASH_DOT_DOT, TRIPLE,
        THIN_THICK_SMALL_GAP, THICK_THIN_SMALL_GAP, THIN_THICK_THIN_SMALL_GAP,
        THIN_THICK_MEDIUM_GAP, THICK_THIN_MEDIUM_GAP, THIN_THICK_THIN_MEDIUM_GAP,
        THIN_THICK_LARGE_GAP, THICK_THIN_LARGE_GAP, THIN_THICK_THIN_LARGE_GAP,
        WAVE, DOUBLE_WAVE, DASHED_SMALL_GAP, DASH_DOT_STROKED, EMBOSS_3D, ENGRAVE_3D,
        TOP, BOTTOM, LEFT, RIGHT
    }
}