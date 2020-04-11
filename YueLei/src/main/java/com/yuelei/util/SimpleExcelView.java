package com.yuelei.util;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxStreamingView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SimpleExcelView extends AbstractXlsxStreamingView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        final Map<String, List<?>> data = new LinkedHashMap<>();
        for (final Map.Entry<String, Object> entry : model.entrySet()) {
            if (entry.getValue() instanceof List<?>) {
                data.put(entry.getKey(), (List<?>) entry.getValue());
            }
        }
        ExcelUtil.fillExcel(workbook, data);
        response.setHeader("Content-Disposition", "attachment; filename=" + DateUtil.format(DateUtil.FORMAT_SECOND, new Date()) + ".xlsx");
    }
}