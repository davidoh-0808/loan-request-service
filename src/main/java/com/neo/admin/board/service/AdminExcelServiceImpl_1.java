//package com.neo.admin.board.service;
//
//import com.neo.common.service.CommonServiceImpl;
//import com.neo.common.vo.BoardVO;
//
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFFont;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.lang.reflect.Field;
//import java.util.List;
//
///**
// * 설명 : AdminThanksController.thanksList() 에서 뷰에 전달한 boardIdsForExport 모델 을 BoardExportVO 로 받는다.  그리고 HttpServlet Response 에 엑셀문서를 write 하는 방식으로 브라우저에서 직접 엑셀파일 다운로드를 실행시킨다.
// *
// */
//@Service("adminExcelService")
//public class AdminExcelServiceImpl implements AdminExcelService {
//
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    private XSSFWorkbook workbook;
//    private XSSFSheet sheet;
//
//    public AdminExcelServiceImpl() {
//        workbook = new XSSFWorkbook();
//    }
//
//    @Override
//    public void export(List<BoardVO> boardList, HttpServletResponse response) throws IOException {
//        writeHeaderRow(boardList);
//        writeDataRows(boardList);
//
//
//    }
//
//    @Override
//    public void writeHeaderRow(List<BoardVO> boardList) {
//        sheet = workbook.createSheet();
//        Row headerRow = sheet.createRow(0);
//        BoardVO headerRowData = boardList.get(0);
//
//        CellStyle style = workbook.createCellStyle();
//        XSSFFont font = workbook.createFont();
//        font.setFontHeight((short) 12);
//        font.setBold(true);
//        style.setFont(font);
//
//        //write header row for BoardVO
//        int headerRowLen = headerRowData.getClass().getDeclaredFields().length;
//        int columnCnt = 0;
//
//        //Class.getFields() -> for only public fields
//        Field[] headerRowFields = headerRowData.getClass().getDeclaredFields();
//        for(int i=0; i<headerRowLen; i++) {
//            createCell(headerRow, columnCnt++, headerRowFields[i].getName(), style);
//        }
//
//
//        int dataRowLen = boardList.get(0).getClass().getDeclaredFields().length;
//        Field[] dataRowFields = boardList.get(0).getClass().getFields();
//        int rowCnt = 1;
//
//        CellStyle style2 = workbook.createCellStyle();
//        XSSFFont font2 = workbook.createFont();
//        font2.setFontHeight((short) 12);
//        style2.setFont(font2);
//
//        //process actual data in BoardVO
//        for(BoardVO b : boardList) {
//            int columnCnt2 = 0;
//            Row row = sheet.createRow(rowCnt++);
//
//            createCell(row, columnCnt2++, b.getBOARD_CODE(), style2);
//            createCell(row, columnCnt2++, b.getBOARD_GUBUN(), style2);
//            createCell(row, columnCnt2++, b.getTITLE(), style2);
//            createCell(row, columnCnt2++, b.getCONTENT(), style2);
//            createCell(row, columnCnt2++, b.getVIEW_CNT(), style2);
//            createCell(row, columnCnt2++, b.getVIEW_CONTENT(), style2);
//            createCell(row, columnCnt2++, b.getPUBL_DATE(), style2);
//            createCell(row, columnCnt2++, b.getFileList(), style2);
//        }
//
//    }
//
//    @Override
//    public void writeDataRows(List<BoardVO> boardList) {
//        int dataRowLen = boardList.get(0).getClass().getDeclaredFields().length;
//        Field[] dataRowFields = boardList.get(0).getClass().getFields();
//        int rowCnt = 1;
//
//        CellStyle style = workbook.createCellStyle();
//        XSSFFont font = workbook.createFont();
//        font.setFontHeight((short) 12);
//        style.setFont(font);
//
//        //process actual data in BoardVO
//        for(BoardVO b : boardList) {
//            int columnCnt = 0;
//            Row row = sheet.createRow(rowCnt++);
//
//            createCell(row, columnCnt++, b.getBOARD_CODE(), style);
//            createCell(row, columnCnt++, b.getBOARD_GUBUN(), style);
//            createCell(row, columnCnt++, b.getTITLE(), style);
//            createCell(row, columnCnt++, b.getCONTENT(), style);
//            createCell(row, columnCnt++, b.getVIEW_CNT(), style);
//            createCell(row, columnCnt++, b.getVIEW_CONTENT(), style);
//            createCell(row, columnCnt++, b.getPUBL_DATE(), style);
//            createCell(row, columnCnt++, b.getFileList(), style);
//        }
//    }
//
//    @Override
//    public void createCell(Row row, int columnCount, Object value, CellStyle style) {
//        //config cell columns (no of columns, actual data value, cell style)
//        sheet.autoSizeColumn(columnCount);
//        Cell cell = row.createCell(columnCount);
//
//        if (value instanceof Integer) {
//            cell.setCellValue((Integer) value);
//        } else if (value instanceof Boolean) {
//            cell.setCellValue((Boolean) value);
//        } else {
//            cell.setCellValue((String) value);
//        }
//
//        cell.setCellStyle(style);
//    }
//}
//*/
