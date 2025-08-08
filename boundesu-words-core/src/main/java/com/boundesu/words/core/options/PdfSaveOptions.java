package com.boundesu.words.core.options;

/**
 * PdfSaveOptions provides options for saving PDF documents.
 *
 * @author Boundesu Team
 * @version 1.0.0
 */
public class PdfSaveOptions extends SaveOptions {
    private boolean additionalTextPositioning;
    private ColorMode colorMode;
    private PdfCompliance compliance;
    private boolean createNoteHyperlinks;
    private String customPropertiesExport;
    private DmlEffectsRenderingMode dmlEffectsRenderingMode;
    private DmlRenderingMode dmlRenderingMode;
    private boolean downsampleImages;
    private boolean embedFullFonts;
    private boolean exportDocumentStructure;
    private boolean exportGeneratorName;
    private HeaderFooterBookmarksExportMode headerFooterBookmarksExportMode;
    private boolean imageColorSpaceExportMode;
    private int imageCompression;
    private int jpegQuality;
    private NumeralFormat numeralFormat;
    private boolean openHyperlinksInNewWindow;
    private boolean optimizeOutput;
    private String outlineOptions;
    private int pageCount;
    private int pageIndex;
    private boolean preserveFormFields;
    private PdfTextCompression textCompression;
    private boolean useBookFoldPrintingSettings;
    private boolean useCoreFonts;
    private String userPassword;
    private String ownerPassword;
    private String encryptionDetails;

    public PdfSaveOptions() {
        super(SaveFormat.PDF);
        this.additionalTextPositioning = false;
        this.colorMode = ColorMode.NORMAL;
        this.compliance = PdfCompliance.PDF_17;
        this.createNoteHyperlinks = false;
        this.customPropertiesExport = "None";
        this.dmlEffectsRenderingMode = DmlEffectsRenderingMode.SIMPLIFIED;
        this.dmlRenderingMode = DmlRenderingMode.FALLBACK;
        this.downsampleImages = true;
        this.embedFullFonts = false;
        this.exportDocumentStructure = false;
        this.exportGeneratorName = true;
        this.headerFooterBookmarksExportMode = HeaderFooterBookmarksExportMode.AUTO;
        this.imageColorSpaceExportMode = false;
        this.imageCompression = 0;
        this.jpegQuality = 100;
        this.numeralFormat = NumeralFormat.ARABIC_INDIC;
        this.openHyperlinksInNewWindow = false;
        this.optimizeOutput = false;
        this.pageCount = 0;
        this.pageIndex = 0;
        this.preserveFormFields = false;
        this.textCompression = PdfTextCompression.FLATE;
        this.useBookFoldPrintingSettings = false;
        this.useCoreFonts = false;
    }

    // Getters and setters
    public boolean isAdditionalTextPositioning() {
        return additionalTextPositioning;
    }

    public void setAdditionalTextPositioning(boolean additionalTextPositioning) {
        this.additionalTextPositioning = additionalTextPositioning;
    }

    public ColorMode getColorMode() {
        return colorMode;
    }

    public void setColorMode(ColorMode colorMode) {
        this.colorMode = colorMode;
    }

    public PdfCompliance getCompliance() {
        return compliance;
    }

    public void setCompliance(PdfCompliance compliance) {
        this.compliance = compliance;
    }

    public boolean isCreateNoteHyperlinks() {
        return createNoteHyperlinks;
    }

    public void setCreateNoteHyperlinks(boolean createNoteHyperlinks) {
        this.createNoteHyperlinks = createNoteHyperlinks;
    }

    public String getCustomPropertiesExport() {
        return customPropertiesExport;
    }

    public void setCustomPropertiesExport(String customPropertiesExport) {
        this.customPropertiesExport = customPropertiesExport;
    }

    public DmlEffectsRenderingMode getDmlEffectsRenderingMode() {
        return dmlEffectsRenderingMode;
    }

    public void setDmlEffectsRenderingMode(DmlEffectsRenderingMode dmlEffectsRenderingMode) {
        this.dmlEffectsRenderingMode = dmlEffectsRenderingMode;
    }

    public DmlRenderingMode getDmlRenderingMode() {
        return dmlRenderingMode;
    }

    public void setDmlRenderingMode(DmlRenderingMode dmlRenderingMode) {
        this.dmlRenderingMode = dmlRenderingMode;
    }

    public boolean isDownsampleImages() {
        return downsampleImages;
    }

    public void setDownsampleImages(boolean downsampleImages) {
        this.downsampleImages = downsampleImages;
    }

    public boolean isEmbedFullFonts() {
        return embedFullFonts;
    }

    public void setEmbedFullFonts(boolean embedFullFonts) {
        this.embedFullFonts = embedFullFonts;
    }

    public boolean isExportDocumentStructure() {
        return exportDocumentStructure;
    }

    public void setExportDocumentStructure(boolean exportDocumentStructure) {
        this.exportDocumentStructure = exportDocumentStructure;
    }

    public boolean isExportGeneratorName() {
        return exportGeneratorName;
    }

    public void setExportGeneratorName(boolean exportGeneratorName) {
        this.exportGeneratorName = exportGeneratorName;
    }

    public HeaderFooterBookmarksExportMode getHeaderFooterBookmarksExportMode() {
        return headerFooterBookmarksExportMode;
    }

    public void setHeaderFooterBookmarksExportMode(HeaderFooterBookmarksExportMode headerFooterBookmarksExportMode) {
        this.headerFooterBookmarksExportMode = headerFooterBookmarksExportMode;
    }

    public boolean isImageColorSpaceExportMode() {
        return imageColorSpaceExportMode;
    }

    public void setImageColorSpaceExportMode(boolean imageColorSpaceExportMode) {
        this.imageColorSpaceExportMode = imageColorSpaceExportMode;
    }

    public int getImageCompression() {
        return imageCompression;
    }

    public void setImageCompression(int imageCompression) {
        this.imageCompression = imageCompression;
    }

    public int getJpegQuality() {
        return jpegQuality;
    }

    public void setJpegQuality(int jpegQuality) {
        this.jpegQuality = jpegQuality;
    }

    public NumeralFormat getNumeralFormat() {
        return numeralFormat;
    }

    public void setNumeralFormat(NumeralFormat numeralFormat) {
        this.numeralFormat = numeralFormat;
    }

    public boolean isOpenHyperlinksInNewWindow() {
        return openHyperlinksInNewWindow;
    }

    public void setOpenHyperlinksInNewWindow(boolean openHyperlinksInNewWindow) {
        this.openHyperlinksInNewWindow = openHyperlinksInNewWindow;
    }

    public boolean isOptimizeOutput() {
        return optimizeOutput;
    }

    public void setOptimizeOutput(boolean optimizeOutput) {
        this.optimizeOutput = optimizeOutput;
    }

    public String getOutlineOptions() {
        return outlineOptions;
    }

    public void setOutlineOptions(String outlineOptions) {
        this.outlineOptions = outlineOptions;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public boolean isPreserveFormFields() {
        return preserveFormFields;
    }

    public void setPreserveFormFields(boolean preserveFormFields) {
        this.preserveFormFields = preserveFormFields;
    }

    public PdfTextCompression getTextCompression() {
        return textCompression;
    }

    public void setTextCompression(PdfTextCompression textCompression) {
        this.textCompression = textCompression;
    }

    public boolean isUseBookFoldPrintingSettings() {
        return useBookFoldPrintingSettings;
    }

    public void setUseBookFoldPrintingSettings(boolean useBookFoldPrintingSettings) {
        this.useBookFoldPrintingSettings = useBookFoldPrintingSettings;
    }

    public boolean isUseCoreFonts() {
        return useCoreFonts;
    }

    public void setUseCoreFonts(boolean useCoreFonts) {
        this.useCoreFonts = useCoreFonts;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getOwnerPassword() {
        return ownerPassword;
    }

    public void setOwnerPassword(String ownerPassword) {
        this.ownerPassword = ownerPassword;
    }

    public String getEncryptionDetails() {
        return encryptionDetails;
    }

    public void setEncryptionDetails(String encryptionDetails) {
        this.encryptionDetails = encryptionDetails;
    }

    // Enums
    public enum ColorMode {
        NORMAL, GRAYSCALE
    }

    public enum PdfCompliance {
        PDF_17, PDF_20, PDF_A_1A, PDF_A_1B, PDF_A_2A, PDF_A_2B, PDF_A_2U, PDF_A_4, PDF_UA_1
    }

    public enum DmlEffectsRenderingMode {
        SIMPLIFIED, NONE, FINE
    }

    public enum DmlRenderingMode {
        FALLBACK, DRAWING_ML
    }

    public enum HeaderFooterBookmarksExportMode {
        NONE, FIRST, ALL, AUTO
    }

    public enum NumeralFormat {
        ARABIC_INDIC, CONTEXT, EASTERN_ARABIC_INDIC, EUROPEAN, SYSTEM
    }

    public enum PdfTextCompression {
        NONE, FLATE
    }
}