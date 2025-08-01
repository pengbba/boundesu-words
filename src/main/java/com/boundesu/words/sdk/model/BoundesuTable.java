package com.boundesu.words.sdk.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * Boundesu Words JDK17 SDK - 表格模型
 * 
 * @author Boundesu
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoundesuTable {
    
    /**
     * 表格唯一标识
     */
    private String tableId;
    
    /**
     * 表格标题
     */
    private String title;
    
    /**
     * 表格行数
     */
    private int rows;
    
    /**
     * 表格列数
     */
    private int columns;
    
    /**
     * 表格数据
     */
    @Builder.Default
    private List<List<BoundesuTableCell>> data = new ArrayList<>();
    
    /**
     * 表格样式
     */
    @Builder.Default
    private BoundesuTableStyle style = new BoundesuTableStyle();
    
    /**
     * 表格宽度（像素或百分比）
     */
    @Builder.Default
    private String width = "100%";
    
    /**
     * 表格对齐方式
     */
    @Builder.Default
    private TableAlignment alignment = TableAlignment.LEFT;
    
    /**
     * 是否显示边框
     */
    @Builder.Default
    private boolean showBorders = true;
    
    /**
     * 边框宽度
     */
    @Builder.Default
    private int borderWidth = 1;
    
    /**
     * 边框颜色
     */
    @Builder.Default
    private String borderColor = "#000000";
    
    /**
     * 单元格间距
     */
    @Builder.Default
    private int cellSpacing = 0;
    
    /**
     * 单元格内边距
     */
    @Builder.Default
    private int cellPadding = 5;
    
    /**
     * 自定义属性
     */
    @Builder.Default
    private Map<String, Object> customProperties = new HashMap<>();
    
    /**
     * 表格对齐方式枚举
     */
    public enum TableAlignment {
        LEFT("左对齐"),
        CENTER("居中对齐"),
        RIGHT("右对齐");
        
        private final String description;
        
        TableAlignment(String description) {
            this.description = description;
        }
        
        public String getDescription() {
            return description;
        }
    }
    
    /**
     * 初始化表格
     */
    public BoundesuTable initializeTable(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.data = new ArrayList<>();
        
        for (int i = 0; i < rows; i++) {
            List<BoundesuTableCell> row = new ArrayList<>();
            for (int j = 0; j < columns; j++) {
                row.add(BoundesuTableCell.builder()
                        .rowIndex(i)
                        .columnIndex(j)
                        .text("")
                        .build());
            }
            this.data.add(row);
        }
        return this;
    }
    
    /**
     * 设置单元格内容
     */
    public BoundesuTable setCellText(int row, int column, String text) {
        if (isValidPosition(row, column)) {
            data.get(row).get(column).setText(text);
        }
        return this;
    }
    
    /**
     * 获取单元格内容
     */
    public String getCellText(int row, int column) {
        if (isValidPosition(row, column)) {
            return data.get(row).get(column).getText();
        }
        return null;
    }
    
    /**
     * 设置单元格样式
     */
    public BoundesuTable setCellStyle(int row, int column, BoundesuTableCellStyle style) {
        if (isValidPosition(row, column)) {
            data.get(row).get(column).setStyle(style);
        }
        return this;
    }
    
    /**
     * 获取单元格
     */
    public BoundesuTableCell getCell(int row, int column) {
        if (isValidPosition(row, column)) {
            return data.get(row).get(column);
        }
        return null;
    }
    
    /**
     * 添加行
     */
    public BoundesuTable addRow() {
        List<BoundesuTableCell> newRow = new ArrayList<>();
        for (int j = 0; j < columns; j++) {
            newRow.add(BoundesuTableCell.builder()
                    .rowIndex(rows)
                    .columnIndex(j)
                    .text("")
                    .build());
        }
        data.add(newRow);
        rows++;
        return this;
    }
    
    /**
     * 添加列
     */
    public BoundesuTable addColumn() {
        for (int i = 0; i < rows; i++) {
            data.get(i).add(BoundesuTableCell.builder()
                    .rowIndex(i)
                    .columnIndex(columns)
                    .text("")
                    .build());
        }
        columns++;
        return this;
    }
    
    /**
     * 删除行
     */
    public BoundesuTable removeRow(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < rows && rows > 1) {
            data.remove(rowIndex);
            rows--;
            // 更新行索引
            for (int i = rowIndex; i < rows; i++) {
                for (BoundesuTableCell cell : data.get(i)) {
                    cell.setRowIndex(i);
                }
            }
        }
        return this;
    }
    
    /**
     * 删除列
     */
    public BoundesuTable removeColumn(int columnIndex) {
        if (columnIndex >= 0 && columnIndex < columns && columns > 1) {
            for (List<BoundesuTableCell> row : data) {
                row.remove(columnIndex);
            }
            columns--;
            // 更新列索引
            for (List<BoundesuTableCell> row : data) {
                for (int j = columnIndex; j < columns; j++) {
                    row.get(j).setColumnIndex(j);
                }
            }
        }
        return this;
    }
    
    /**
     * 设置表头
     */
    public BoundesuTable setHeaderRow(String... headers) {
        if (headers.length <= columns && rows > 0) {
            for (int i = 0; i < headers.length; i++) {
                BoundesuTableCell cell = data.get(0).get(i);
                cell.setText(headers[i]);
                cell.setHeader(true);
                cell.getStyle().setBold(true);
            }
        }
        return this;
    }
    
    /**
     * 合并单元格
     */
    public BoundesuTable mergeCells(int startRow, int startColumn, int endRow, int endColumn) {
        if (isValidPosition(startRow, startColumn) && isValidPosition(endRow, endColumn)) {
            BoundesuTableCell mainCell = data.get(startRow).get(startColumn);
            mainCell.setRowSpan(endRow - startRow + 1);
            mainCell.setColumnSpan(endColumn - startColumn + 1);
            
            // 标记其他单元格为合并状态
            for (int i = startRow; i <= endRow; i++) {
                for (int j = startColumn; j <= endColumn; j++) {
                    if (i != startRow || j != startColumn) {
                        data.get(i).get(j).setMerged(true);
                    }
                }
            }
        }
        return this;
    }
    
    /**
     * 验证位置是否有效
     */
    private boolean isValidPosition(int row, int column) {
        return row >= 0 && row < rows && column >= 0 && column < columns && 
               data != null && data.size() > row && data.get(row).size() > column;
    }
    
    /**
     * 设置表格宽度
     */
    public BoundesuTable setWidth(String width) {
        this.width = width;
        return this;
    }
    
    /**
     * 设置表格对齐方式
     */
    public BoundesuTable setAlignment(TableAlignment alignment) {
        this.alignment = alignment;
        return this;
    }
    
    /**
     * 设置边框
     */
    public BoundesuTable setBorder(boolean showBorders, int borderWidth, String borderColor) {
        this.showBorders = showBorders;
        this.borderWidth = borderWidth;
        this.borderColor = borderColor;
        return this;
    }
    
    /**
     * 设置单元格间距和内边距
     */
    public BoundesuTable setCellSpacing(int cellSpacing, int cellPadding) {
        this.cellSpacing = cellSpacing;
        this.cellPadding = cellPadding;
        return this;
    }
    
    /**
     * 获取表格总字数
     */
    public int getWordCount() {
        return data.stream()
                .flatMap(List::stream)
                .mapToInt(cell -> cell.getText() != null ? cell.getText().split("\\s+").length : 0)
                .sum();
    }
    
    /**
     * 获取表格总字符数
     */
    public int getCharacterCount() {
        return data.stream()
                .flatMap(List::stream)
                .mapToInt(cell -> cell.getText() != null ? cell.getText().length() : 0)
                .sum();
    }
}