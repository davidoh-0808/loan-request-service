package com.neo.admin.board.service;

import com.neo.common.vo.BoardVO;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface AdminExcelService {

    public void export(List<BoardVO> boardList, HttpServletResponse response) throws IOException;

    public void writeHeaderRow(List<BoardVO> boardList);

    public void writeDataRows(List<BoardVO> boardList);

    public void createCell(Row row, int columnCount, Object value, CellStyle style);

}
