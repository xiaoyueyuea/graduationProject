package com.yuelei.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ExcelUtil {

    @Retention(value = RetentionPolicy.RUNTIME)
    @Target(value = ElementType.FIELD)
    public static @interface ExcelColumn {

        String name() default StringUtil.EMPTY;

        String format() default StringUtil.EMPTY;
    }

    public static void exportExcel(Map<String, List<?>> data, OutputStream stream) throws IllegalArgumentException, IllegalAccessException, IOException {
        exportExcel(new SXSSFWorkbook(), data, stream);
    }

    public static void exportExcel(Workbook workbook, Map<String, List<?>> data, OutputStream outputStream) throws IllegalArgumentException, IllegalAccessException, IOException {
        fillExcel(workbook, data);
        workbook.write(outputStream);
    }

    public static void fillExcel(Workbook workbook, Map<String, List<?>> data) throws IllegalArgumentException, IllegalAccessException {
        for (final Map.Entry<String, List<?>> entry : data.entrySet()) {
            final Sheet sheet = workbook.createSheet(entry.getKey());
            final List<?> values = entry.getValue();
            if (!values.isEmpty()) {
                createHead(sheet, values.get(0).getClass());
                createBody(sheet, values);
            }
        }
    }

    private static void createHead(Sheet sheet, Class<?> clazz) {
        final Field[] fields = clazz.getDeclaredFields();
        final Row row = sheet.createRow(0);
        for (int i = 0; i < fields.length; i++) {
            final Field field = fields[i];
            final ExcelColumn annotation = field.getAnnotation(ExcelColumn.class);
            if (annotation != null) {
                row.createCell(i).setCellValue(StringUtil.isNotEmpty(annotation.name()) ? annotation.name() : field.getName());
            }
        }

    }

    private static void createBody(Sheet sheet, List<?> values) throws IllegalArgumentException, IllegalAccessException {
        for (int i = 0; i < values.size(); i++) {
            final Object value = values.get(i);
            final Field[] fields = value.getClass().getDeclaredFields();
            if (fields.length > 0) {
                final Row row = sheet.createRow(i + 1);
                for (int j = 0; j < fields.length; j++) {
                    final Field field = fields[j];
                    field.setAccessible(true);
                    final ExcelColumn annotation = field.getAnnotation(ExcelColumn.class);
                    if (annotation != null) {
                        final String format = annotation.format();
                        final Object fieldValue = field.get(value);
                        final Cell cell = row.createCell(j);
                        if (fieldValue == null) {
                            cell.setCellType(Cell.CELL_TYPE_BLANK);
                        } else if (fieldValue instanceof String) {
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            cell.setCellValue(fieldValue.toString());
                        } else if (fieldValue instanceof Character) {
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            cell.setCellValue(fieldValue.toString());
                        } else if (fieldValue instanceof Boolean) {
                            cell.setCellType(Cell.CELL_TYPE_BOOLEAN);
                            cell.setCellValue((Boolean) fieldValue);
                        } else if (fieldValue instanceof Number) {
                            final Number number = (Number) fieldValue;
                            cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                            if (StringUtil.isNotEmpty(format)) {
                                cell.setCellValue(new DecimalFormat(format).format(fieldValue));
                            } else {
                                cell.setCellValue(number.doubleValue());
                            }
                        } else if (fieldValue instanceof Date) {
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            if (StringUtil.isNotEmpty(format)) {
                                cell.setCellValue(new SimpleDateFormat(format).format(fieldValue));
                            } else {
                                cell.setCellValue(fieldValue.toString());
                            }
                        } else {
                            cell.setCellValue(fieldValue.toString());
                        }
                    }
                }
            }
        }
    }

}
