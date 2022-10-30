package com.neo.admin.board.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.LocaleResolver;

import com.neo.common.vo.BoardVO;
import com.neo.common.vo.MasterKeyVO;
import com.neo.mappers.BoardMapper;
import com.neo.mappers.CommonMapper;
import com.neo.util.UtilCommon;
import com.neo.util.UtilJsonResult;

@Service("adminFinanService")
@Transactional
public class AdminFinanServiceImpl implements AdminFinanService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired MessageSource messageSource;
	@Autowired LocaleResolver localeResolver;
	@Resource(name="boardMapper")
	private BoardMapper boardMapper;
	@Resource(name="commonMapper")
	private CommonMapper commonMapper;
	
	@Override
	public int finanListCount(BoardVO paramVO) throws Exception {
		return boardMapper.boardListCount(paramVO);
	}
	
	@Override
	public int finanUpdateViewCnt(BoardVO paramVO) throws Exception {
		return boardMapper.boardUpdateViewCnt(paramVO);
	}
	
	@Override
	public List<BoardVO> finanList(BoardVO paramVO) throws Exception {
		List<BoardVO> resultList = new ArrayList<>();
		resultList = boardMapper.boardList(paramVO);
		return resultList;
	}

	@Override
	public BoardVO finanDetail(BoardVO paramVO) throws Exception {
		BoardVO result = new BoardVO();
		result = boardMapper.boardDetail(paramVO);
		
		// 에디터데이타는 html 이므로 unescape 한다
		result.setCONTENT(StringEscapeUtils.unescapeHtml4(result.getCONTENT()));
		
		return result;
	}
	
	// 프론트에서 사용
	@Override
	public BoardVO finanDetailPre(BoardVO paramVO) throws Exception {
		BoardVO result = new BoardVO();
		result = boardMapper.boardDetailPre(paramVO);
		
		return result;
	}
	
	// 프론트에서 사용	
	@Override
	public BoardVO finanDetailNext(BoardVO paramVO) throws Exception {
		BoardVO result = new BoardVO();
		result = boardMapper.boardDetailNext(paramVO);
		
		return result;
	}

	@Override
	public JSONObject finanInsert(BoardVO paramVO, HttpServletRequest request) throws Exception {
		JSONObject json = new JSONObject();
		UtilJsonResult.setReturnCodeFail(json);
		String rMsg = messageSource.getMessage("error.system.default", null, localeResolver.resolveLocale(request));
		
		// S:유효성체크
		// 이름 체크
		if(UtilCommon.isEmpty(paramVO.getTITLE())) {
			rMsg = messageSource.getMessage("validation.empty.input", new String[] {"뉴스제목"}, localeResolver.resolveLocale(request));
			UtilJsonResult.setReturnCodeFail(json, rMsg);
			return json;
			
		}
		// E:유효성체크
		
		// S: 코드 생성
		String masterKey = "";
		MasterKeyVO masterKeyVO = new MasterKeyVO();		
		masterKeyVO.setKEY_GBN("BOARD_CODE");
		masterKey = commonMapper.getFnGetMasterKey(masterKeyVO);
		paramVO.setBOARD_CODE(masterKey);
		// E: 코드 생성
		
		// 글등록
		paramVO.setIN_USER(paramVO.getLoginCode());
		paramVO.setUP_USER(paramVO.getLoginCode());
		paramVO.setBOARD_GUBUN("MC0000500003");	
		boardMapper.boardInsert(paramVO);
		
		UtilJsonResult.setReturnCodeSuc(json, rMsg);
		
		return json;
	}

	@Override
	public JSONObject finanUpdate(BoardVO paramVO, HttpServletRequest request) throws Exception {
		JSONObject json = new JSONObject();
		UtilJsonResult.setReturnCodeFail(json);
		String rMsg = messageSource.getMessage("error.system.default", null, localeResolver.resolveLocale(request));
		
		// S:유효성체크
		if(UtilCommon.isEmpty(paramVO.getTITLE())) {
			rMsg = messageSource.getMessage("validation.empty.input", new String[] {"데모타이틀"}, localeResolver.resolveLocale(request)); 
			UtilJsonResult.setReturnCodeFail(json, rMsg);
			return json;
		}
		if(UtilCommon.isEmpty(boardMapper.boardDetail(paramVO))) {
			rMsg = messageSource.getMessage("validation.update.empty.object", null, localeResolver.resolveLocale(request)); 
			UtilJsonResult.setReturnCodeFail(json, rMsg);
			return json;
		}
		// E:유효성체크
		paramVO.setUP_USER(paramVO.getLoginCode());
		boardMapper.boardUpdate(paramVO);
		
		UtilJsonResult.setReturnCodeSuc(json);
		
		return json;
	}

	@Override
	public JSONObject finanDelete(BoardVO paramVO, HttpServletRequest request) throws Exception {
		JSONObject json = new JSONObject();
		UtilJsonResult.setReturnCodeFail(json);
		String rMsg = messageSource.getMessage("error.system.default", null, localeResolver.resolveLocale(request));
		
		// S:유효성체크
		if(UtilCommon.isEmpty(boardMapper.boardDetail(paramVO))) {
			rMsg = messageSource.getMessage("validation.delete.empty.object", null, localeResolver.resolveLocale(request)); 
			UtilJsonResult.setReturnCodeFail(json, rMsg);
			return json;
		}
		// E:유효성체크
		
		paramVO.setDEL_YN("Y");
		paramVO.setUP_USER(paramVO.getLoginCode());
		boardMapper.boardDelete(paramVO);
		
		UtilJsonResult.setReturnCodeSuc(json);
		
		return json;
	}

}
