package com.vsquare.reader.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.vsquare.reader.Reader;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ExcelReader implements Reader<List<Map<String, String>>> {

    @Override
    public List<Map<String, String>> read(String filePath) {

        Workbook workbook = null;
        String ext = FilenameUtils.getExtension(filePath);

        try {

            FileInputStream fis=new FileInputStream(filePath);

            if ( ext.toLowerCase().equals("xls") ) {
                workbook = new HSSFWorkbook(fis);
            } else {
                workbook = new XSSFWorkbook(fis);
            }

            if ( null == workbook ) {
                throw new RuntimeException("workbook not found.");
            }

            Sheet sheet = workbook.getSheetAt(0);

            int numberOfRows = sheet.getPhysicalNumberOfRows();

            Row titleRow = sheet.getRow(0);

            List<String> titles = new ArrayList();
            for (Iterator<Cell> it = titleRow.cellIterator(); it.hasNext(); ) {
                Cell titleCell = it.next();

                if ( titleCell.getCellTypeEnum() == CellType.STRING ) {
                    titles.add(titleCell.getStringCellValue());
                }
            }

            List<Map<String, String>> dataList = Lists.newArrayList();
            // 첫번째 row는 title
            Row dataRow;
            for ( int rowNumber = 1; rowNumber < numberOfRows; rowNumber++ ) {
                dataRow = sheet.getRow(rowNumber);

                int numberOfCells = dataRow.getPhysicalNumberOfCells();
                Map<String, String> dataMap = Maps.newLinkedHashMap();
                Cell cell;
                for ( int cellNumber = 0; cellNumber < numberOfCells; cellNumber++ ) {

                    cell = dataRow.getCell(cellNumber);
                    if (cell != null) {
                        String value = "";

                        switch (cell.getCellTypeEnum()) {
                            case NUMERIC:
                                value = "" + cell.getNumericCellValue();
                                break;
                            case STRING:
                                value = cell.getStringCellValue();
                                break;
                            default:
                        }
                        if (StringUtils.isBlank(value) ) {
                            System.err.println(titles.get(cellNumber) + " : " + value);
                        }
                        dataMap.put(titles.get(cellNumber), value.replace("\n", "<br>").replace("&", "&amp;"));
                    }
                }
                dataList.add(dataMap);
            }

            dataList.stream().forEach(System.out::println);

            return dataList;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
