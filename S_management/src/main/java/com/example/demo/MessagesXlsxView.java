package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

public class MessagesXlsxView extends AbstractXlsxView {
	@Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        @SuppressWarnings("unchecked")
        List<Member> messages = (List<Member>) model.get("messages");

        Sheet sheet = workbook.createSheet("Recent messages");

        // create header
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("ID");
        row.createCell(1).setCellValue("顧客");
        row.createCell(2).setCellValue("受注日");
        row.createCell(3).setCellValue("シリアル番号");
        row.createCell(4).setCellValue("件名");
        row.createCell(5).setCellValue("数量");
        row.createCell(6).setCellValue("納入指定日");
        row.createCell(7).setCellValue("納入日");
        row.createCell(8).setCellValue("請求日");
        row.createCell(9).setCellValue("見積金額");
        row.createCell(10).setCellValue("受注金額");
        row.createCell(11).setCellValue("ステータス");

        // create body
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i=0; i<messages.size(); i++) {
        	Member message = messages.get(i);
            row = sheet.createRow(i+1);
            row.createCell(0).setCellValue(message.getId());
            row.createCell(1).setCellValue(message.getClientname());
            row.createCell(2).setCellValue(message.getDay());
            row.createCell(3).setCellValue(message.getSnumber());
            row.createCell(4).setCellValue(message.getSubject());
            row.createCell(5).setCellValue(message.getQuantity());
            row.createCell(6).setCellValue(message.getDeliveryday());
            row.createCell(7).setCellValue(message.getDeliveryday2());
            row.createCell(8).setCellValue(message.getBillingday());
            row.createCell(9).setCellValue(message.getMoney());
            row.createCell(10).setCellValue(message.getMoney2());
            row.createCell(11).setCellValue(message.getStatus());

        }

        // enable auto filter
        sheet.setAutoFilter(new CellRangeAddress(0, 0, 0, 4));

        // adjust column width
        for (int i=0; i<5; i++) {
            sheet.autoSizeColumn(i);
        }
    }

}
