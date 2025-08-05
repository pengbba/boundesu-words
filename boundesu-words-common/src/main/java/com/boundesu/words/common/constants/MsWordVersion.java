package com.boundesu.words.common.constants;

/**
 * Microsoft Word版本枚举
 * 定义支持的Word版本及其相关信息
 * 
 * @author Boundesu Team
 * @version 1.0.0
 */
public enum MsWordVersion {
    WORD_2003("2003", "Microsoft Word 2003"),
    WORD_2007("2007", "Microsoft Word 2007"),
    WORD_2010("2010", "Microsoft Word 2010"),
    WORD_2013("2013", "Microsoft Word 2013"),
    WORD_2016("2016", "Microsoft Word 2016"),
    WORD_2019("2019", "Microsoft Word 2019"),
    WORD_365("365", "Microsoft Word 365");

    private final String version;
    private final String displayName;

    MsWordVersion(String version, String displayName) {
        this.version = version;
        this.displayName = displayName;
    }

    public String getVersion() {
        return version;
    }

    public String getDisplayName() {
        return displayName;
    }

    /**
     * 根据版本号获取对应的枚举值
     * 
     * @param version 版本号
     * @return 对应的枚举值，如果未找到则返回null
     */
    public static MsWordVersion fromVersion(String version) {
        for (MsWordVersion wordVersion : values()) {
            if (wordVersion.getVersion().equals(version)) {
                return wordVersion;
            }
        }
        return null;
    }

    /**
     * 获取默认的Word版本
     * 
     * @return 默认版本（Word 2019）
     */
    public static MsWordVersion getDefault() {
        return WORD_2019;
    }

    @Override
    public String toString() {
        return displayName;
    }
}