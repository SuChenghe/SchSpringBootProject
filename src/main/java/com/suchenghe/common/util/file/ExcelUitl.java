package com.suchenghe.common.util.file;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author SuChenghe
 * @date 2018/10/19 14:58
 */
public class ExcelUitl {

    final static Logger LOGGER = LoggerFactory.getLogger(ExcelUitl.class);
    final static String EXCEL_2003 = "xls";
    final static String EXCEL_2007 = "xlsx";

    /**
     * 获取Excel表格每行的数据
     *
     * @param in
     * @param filename
     * @return
     */
    public static List<Row> getExcelOriginalData(InputStream in, String filename) {
        try {
            Workbook workbook = getWorkbook(in, filename);
            if (workbook != null) {
                Sheet sheet = workbook.getSheetAt(0);
                List<Row> rowList = new ArrayList<>();
                for (Row row : sheet) {
                    rowList.add(row);
                }
                return rowList;
            }
        } catch (Exception e) {
            LOGGER.error("", e);
        }
        return null;
    }

    /**
     * 创建Excel表格
     *
     * @param rowValueList
     * @param sheetName
     * @param excelPath
     */
    public static boolean createExcelFile(List<List<String>> rowValueList, String sheetName, String excelPath) {
        Workbook workbook;
        if (null == excelPath) {
            return false;
        }
        if (excelPath.endsWith(EXCEL_2007)) {
            workbook = new XSSFWorkbook();
        } else {
            workbook = new HSSFWorkbook();
        }
        Sheet sheet = workbook.createSheet(sheetName);
        for (int i = 0; i < rowValueList.size(); i++) {
            Row row = sheet.createRow(i);
            List<String> rowCellValueList = rowValueList.get(i);
            for (int j = 0; j < rowCellValueList.size(); j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(rowCellValueList.get(j));
            }
        }
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(excelPath);
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 创建Workbook对象
     *
     * @param in
     * @param filename
     * @return
     * @throws IOException
     */
    private static Workbook getWorkbook(InputStream in, String filename) {
        try {
            if (null != filename) {
                if (filename.endsWith(EXCEL_2007)) {
                    return new XSSFWorkbook(in);
                } else if (filename.endsWith(EXCEL_2003)) {
                    return new HSSFWorkbook(in);
                }
            }
        } catch (IOException e) {
            LOGGER.error("创建Workbook对象失败");
        }
        return null;
    }

    /**
     * 获取Cell数据
     *
     * @param row
     * @param index
     * @return
     */
    private static String getCellValue(Row row, int index) {
        Cell cell = row.getCell(index);
        String result = "";
        if (null != cell) {
            CellType cellType = cell.getCellType();
            if (cellType == CellType.STRING) {
                String cellValue = cell.getStringCellValue().replaceAll(" ", "");
                if (null != cellValue) {
                    result = cellValue;
                }
            } else if (cellType == CellType.NUMERIC) {
                if (DateUtil.isCellDateFormatted(cell)) {
                    Date cellDate = cell.getDateCellValue();
                    if (null != cellDate) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        return sdf.format(cellDate);
                    }
                } else {
                    String numberVal = String.valueOf(cell.getNumericCellValue());
                    //stripTrailingZeros()去掉后面多余的0
                    //toPlainString()科学记数法,例如1.24E-10,对应0.0000000001238761976
                    return new BigDecimal(numberVal).stripTrailingZeros().toPlainString();
                }
            } else if (cellType == CellType.FORMULA) {
                try {
                    result = String.valueOf(cell.getNumericCellValue());
                } catch (IllegalStateException e) {
                    result = String.valueOf(cell.getRichStringCellValue());
                }
            } else if (cellType == CellType.BOOLEAN) {
                result = Boolean.toString(cell.getBooleanCellValue());
            } else if (cellType == CellType.ERROR) {
                result = "ERROR";
            }
        }
        return result;
    }


    public static void main(String[] args) {
        //读取excel
        String fileName = "D:\\file\\sch.xls";
        File file = new File(fileName);
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        //去除第一行
        List<Row> rowList = getExcelOriginalData(inputStream, fileName);
        if (!rowList.isEmpty()) {
            String value = getCellValue(rowList.get(0), 0);
            if (value.indexOf("序号") > -1) {
                rowList.remove(0);
            }
        }
        for (Row row : rowList) {
            String value = getCellValue(row, 0);
            System.out.println(value + ":" + value.length());
            String value1 = getCellValue(row, 1);
            System.out.println(value1 + ":" + value.length());
            String value2 = getCellValue(row, 2);
            System.out.println(value2 + ":" + value.length());
            break;
        }
//    //创建excel
//    List<List<String>> rowValueList = new ArrayList<>();
//    List<String> rowCellValueList ;
//    for(int i= 1 ;i<= 2;i++){
//      //第i行数据
//      rowCellValueList = new ArrayList<>();
//      rowCellValueList.add(String.valueOf(i+0.1));
//      rowCellValueList.add(i+"code");
//      rowCellValueList.add(i+"名字");
//      //将第i行数据加入
//      rowValueList.add(rowCellValueList);
//    }
//    createExcelFile(rowValueList, "SheetTest", "D:\\file\\demonew.xlsx");
    }

}
