package com.vsquare.impl;

import com.vsquare.Reader;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReader<T> implements Reader<T> {

    @Override
    public T read(String filePath) {

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

            // 첫번째 row는 title
            Row dataRow = null;
            for ( int rowNumber = 1; rowNumber < numberOfRows; rowNumber++ ) {
                dataRow = sheet.getRow(rowNumber);

                int numberOfCells = dataRow.getPhysicalNumberOfCells();
                Cell cell = null;
                for ( int cellNumber = 0; cellNumber < numberOfCells; cellNumber++ ) {

                    cell = dataRow.getCell(cellNumber);
                    if (cell != null) {
                        String value = null;

                        switch (cell.getCellTypeEnum()) {
                            case NUMERIC:
                                value = "" + cell.getNumericCellValue();
                                break;
                            case STRING:
                                value = cell.getStringCellValue();
                                break;
                            default:
                        }
                        System.out.print( titles.get(cellNumber) + " : " + value.replace("\n", "<br>") + "\n");
                    }

                }
            }



        } catch (Exception e) {

        }

        return null;

    }
}
