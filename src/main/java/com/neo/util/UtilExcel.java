package com.neo.util;

import com.neo.common.vo.BoardVO;
import com.neo.common.vo.ConsultInfoVO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UtilExcel {

    //UtilExcel 에서 설정하는 값들 : (no of columns, actual data value, cell style)
    public static XSSFWorkbook writeHeaderRow(List<ConsultInfoVO> ConsultInfoVOs, XSSFWorkbook workbook, XSSFSheet sheet) {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("new1");
        Row headerRow = sheet.createRow(0);
        ConsultInfoVO headerRowData = ConsultInfoVOs.get(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        //font.setFontHeight((short) 12);
        font.setBold(true);
        style.setFont(font);

        /*
        //ConsultInfoVO 첫번째 로우 (컬럼수/컬럼명) 가져오기
        int headerRowLen = headerRowData.getClass().getDeclaredFields().length;
        int columnCnt = 0;
        Field[] headerRowFields = headerRowData.getClass().getDeclaredFields();
        for (int i = 0; i < headerRowLen; i++) {
            createCell(sheet, headerRow, columnCnt++, headerRowFields[i].getName(), style);
        }
         */
        /*
            변경 사항 - 필요한 header 로우 와 data 로우의 컬럼을 직접 넣어준다 
            원인 - ( 그때그때 바뀌는 null 값인 header 로우와 data 로우를 맞추어 줄 방법이 생각이 나지 않음 )
         */
         int columnCnt = 0;
         String[] requiredColumnsArr = new String[] {
            "NO",
            "접수일",
            "상담일",
            "상담자",
            "지점",
            "게시자",
            "고객명",
            "생년월일",
            "조회동의",
            "녹취시간",
            "유입경로(1)",
            "유입경로(2)",
            "상품명",
            "업종",
            "업력",
            "채무조정",
            "처리현황",
            "완료일",
            "지점진행상태",
            "심사팀진행상태",
            "거절사유(심사)",
            "취소사유(고객)",
            "비고",
            "기타",
            "연락처"
         };
         for(int i=0; i<requiredColumnsArr.length; i++) {
             createCell(sheet, headerRow, columnCnt++, requiredColumnsArr[i],style);
         }

        int rowCnt = 1;

        CellStyle style2 = workbook.createCellStyle();
        XSSFFont font2 = workbook.createFont();
//        font2.setFontHeight((short) 12);
        style2.setFont(font2);

        //ConsultInfoVO 실제 데이터 가져와 시트에 입력하기
        for (ConsultInfoVO c : ConsultInfoVOs) {
            int columnCnt2 = 0;
            Row row = sheet.createRow(rowCnt++);

            createCell(sheet, row, columnCnt2++, c.getCONS_SEQ(), style2);
            //createCell(sheet, row, columnCnt2++, DateUtil.getExcelDate( c.getIN_DTTM() ), style2);
            createCell(sheet, row, columnCnt2++, c.getIN_DTTM(), style2);
            createCell(sheet, row, columnCnt2++, c.getCONS_DTTM(), style2);
            createCell(sheet, row, columnCnt2++, c.getCONS_MB_NAME(), style2);
            createCell(sheet, row, columnCnt2++, c.getBRANCH_NAME(), style2);
            createCell(sheet, row, columnCnt2++, c.getREGISTRAR(), style2);
            createCell(sheet, row, columnCnt2++, c.getCUST_NM(), style2);
            createCell(sheet, row, columnCnt2++, c.getCUST_REGI_NO(), style2);
            createCell(sheet, row, columnCnt2++, c.getINQU_CONS(), style2);
            createCell(sheet, row, columnCnt2++, c.getRECORD_TIME(), style2);
            createCell(sheet, row, columnCnt2++, c.getINFLOW_NAME1(), style2);
            createCell(sheet, row, columnCnt2++, c.getINFLOW_NAME2(), style2);
            createCell(sheet, row, columnCnt2++, c.getPRODUCT_NAME(), style2);
            createCell(sheet, row, columnCnt2++, c.getTYPE_NAME(), style2);
            createCell(sheet, row, columnCnt2++, c.getCORP_HIS(), style2);
            createCell(sheet, row, columnCnt2++, c.getDEBT_SETT(), style2);
            createCell(sheet, row, columnCnt2++, c.getSTATS_NAME(), style2);
            createCell(sheet, row, columnCnt2++, c.getCOMP_DATE(), style2);
            createCell(sheet, row, columnCnt2++, c.getBRANCH_RESULT_NAME(), style2);
            createCell(sheet, row, columnCnt2++, c.getEVAL_RESULT_NAME(), style2);
            createCell(sheet, row, columnCnt2++, c.getDECLINE_REASON_NAME(), style2);
            createCell(sheet, row, columnCnt2++, c.getCANCEL_REASON_NAME(), style2);
            createCell(sheet, row, columnCnt2++, c.getNOTE(), style2);
            createCell(sheet, row, columnCnt2++, c.getETC(), style2);
            createCell(sheet, row, columnCnt2++, c.getCUST_HP_NO(), style2);

        }
        
        System.out.println(sheet);
        return workbook;
        
    }


    public static void writeDataRows(List<ConsultInfoVO> ConsultInfoVOs, XSSFWorkbook workbook, XSSFSheet sheet) {
        int dataRowLen = ConsultInfoVOs.get(0).getClass().getDeclaredFields().length;
        Field[] dataRowFields = ConsultInfoVOs.get(0).getClass().getFields();
        int rowCnt = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight((short) 12);
        style.setFont(font);

        //process actual data in BoardVO
        for (ConsultInfoVO c : ConsultInfoVOs) {
            int columnCnt = 0;
            Row row = sheet.createRow(rowCnt++);

            createCell(sheet, row, columnCnt++, c.getCONS_SEQ(), style);
            //createCell(sheet, row, columnCnt++, DateUtil.getExcelDate( c.getIN_DTTM() ) , style);
            createCell(sheet, row, columnCnt++, c.getIN_DTTM(), style);
            createCell(sheet, row, columnCnt++, c.getCONS_DTTM(), style);
            createCell(sheet, row, columnCnt++, c.getCONS_MB_NAME(), style);
            createCell(sheet, row, columnCnt++, c.getBRANCH_NAME(), style);
            createCell(sheet, row, columnCnt++, c.getREGISTRAR(), style);
            createCell(sheet, row, columnCnt++, c.getCUST_NM(), style);
            createCell(sheet, row, columnCnt++, c.getCUST_REGI_NO(), style);
            createCell(sheet, row, columnCnt++, c.getINQU_CONS(), style);
            createCell(sheet, row, columnCnt++, c.getRECORD_TIME(), style);
            createCell(sheet, row, columnCnt++, c.getINFLOW_NAME1(), style);
            createCell(sheet, row, columnCnt++, c.getINFLOW_NAME2(), style);
            createCell(sheet, row, columnCnt++, c.getPRODUCT_NAME(), style);
            createCell(sheet, row, columnCnt++, c.getTYPE_NAME(), style);
            createCell(sheet, row, columnCnt++, c.getCORP_HIS(), style);
            createCell(sheet, row, columnCnt++, c.getDEBT_SETT(), style);
            createCell(sheet, row, columnCnt++, c.getSTATS_NAME(), style);
            createCell(sheet, row, columnCnt++, c.getCOMP_DATE(), style);
            createCell(sheet, row, columnCnt++, c.getBRANCH_RESULT_NAME(), style);
            createCell(sheet, row, columnCnt++, c.getEVAL_RESULT_NAME(), style);
            createCell(sheet, row, columnCnt++, c.getDECLINE_REASON_NAME(), style);
            createCell(sheet, row, columnCnt++, c.getCANCEL_REASON_NAME(), style);
            createCell(sheet, row, columnCnt++, c.getNOTE(), style);
            createCell(sheet, row, columnCnt++, c.getETC(), style);
            createCell(sheet, row, columnCnt++, c.getCUST_HP_NO(), style);
        }
    }

    public static void createCell(XSSFSheet sheet, Row row, int columnCount, Object value, CellStyle style) {
        //각 셀에 적용되는 스타일 설정
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);

        if (value instanceof Integer) {
            cell.setCellValue( (Integer) value );
        } else if (value instanceof Long){
            cell.setCellValue( (Long) value );
        } else if (value instanceof Boolean) {
            cell.setCellValue( (Boolean) value );
        } else if (value instanceof Date) {
            cell.setCellValue( (Date) value );
        } else if (value instanceof Double) {
              cell.setCellValue( (Double) value );
        } else {
            cell.setCellValue( (String) value );
        }

        cell.setCellStyle(style);
    }



}