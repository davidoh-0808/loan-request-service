package com.neo.admin.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.LocaleResolver;

import com.neo.common.vo.BoardFileVO;
import com.neo.common.vo.BoardVO;
import com.neo.common.vo.DemoFileVO;
import com.neo.common.vo.FileVO;
import com.neo.common.vo.MasterKeyVO;
import com.neo.mappers.BoardMapper;
import com.neo.mappers.CommonMapper;
import com.neo.util.UtilCommon;
import com.neo.util.UtilDate;
import com.neo.util.UtilFile;
import com.neo.util.UtilJsonResult;

@Service("adminThanksService")
@Transactional
public class AdminThanksServiceImpl implements AdminThanksService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired MessageSource messageSource;
	@Autowired LocaleResolver localeResolver;
	@Resource(name="boardMapper")
	private BoardMapper boardMapper;
	@Resource(name="commonMapper")
	private CommonMapper commonMapper;
	
	@Value("${file.path.external}") String FILE_PATH_UPLOAD;
	@Value("${file.ext.board}") String FILE_EXT;
	@Value("${file.store.board}") String FILE_STORE_PATH;
	@Value("${file.group.board}") String FILE_UPLOAD_GROUP;
	
	@Override
	public int thanksListCount(BoardVO paramVO) throws Exception {
		return boardMapper.boardListCount(paramVO);
	}
	
	@Override
	public int thanksUpdateViewCnt(BoardVO paramVO) throws Exception {
		return boardMapper.boardUpdateViewCnt(paramVO);
	}
	
	@Override
	public List<BoardVO> thanksList(BoardVO paramVO) throws Exception {
		List<BoardVO> resultList = new ArrayList<>();
		resultList = boardMapper.boardList(paramVO);
		return resultList;
	}

	@Override
	public BoardVO thanksDetail(BoardVO paramVO) throws Exception {
		BoardVO result = new BoardVO();
		result = boardMapper.boardDetail(paramVO);
		
		// 에디터데이타는 html 이므로 unescape 한다
		result.setCONTENT(StringEscapeUtils.unescapeHtml4(result.getCONTENT()));
		
		return result;
	}

	// 프론트에서 사용
	@Override
	public BoardVO thanksDetailPre(BoardVO paramVO) throws Exception {
		BoardVO result = new BoardVO();
		result = boardMapper.boardDetailPre(paramVO);
		
		return result;
	}
	
	// 프론트에서 사용	
	@Override
	public BoardVO thanksDetailNext(BoardVO paramVO) throws Exception {
		BoardVO result = new BoardVO();
		result = boardMapper.boardDetailNext(paramVO);
		
		return result;
	}

	@Override
	public JSONObject thanksUpdate(BoardVO paramVO, MultipartHttpServletRequest request) throws Exception {
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
		// S:파일업로드 처리
		Map<String, MultipartFile> mFiles = request.getFileMap();
		List<FileVO> files = new ArrayList<FileVO>();
		if(UtilCommon.isEmpty(mFiles)) {
//			rMsg = messageSource.getMessage("validation.empty.input", new String[] {"파일"}, localeResolver.resolveLocale(request));
//			UtilJsonResult.setReturnCodeFail(json, rMsg);
//			return json;
		}else {
			
			// S:파일정보삭제
			// 현재삭제방식은 게시글수정시 첨부파일이 등록되면 기존 등록된 파일모두 제거후 새로등록함
			// 파일별 수정가능하면 아래 삭제로직 수정해야함
			BoardFileVO paramFileVO = new BoardFileVO();
			List<BoardFileVO> resultListFile = new ArrayList<BoardFileVO>();
			paramFileVO.setBOARD_CODE(paramVO.getBOARD_CODE());
			resultListFile = boardMapper.boardFileList(paramFileVO);
			if(UtilCommon.isNotEmpty(resultListFile)) {
				boardMapper.boardFileDelete(paramFileVO);
				// 실제파일삭제
				for(BoardFileVO item: resultListFile) {
					UtilFile.deleteFileInf(item.getSYS_FILE_NAME(), FILE_PATH_UPLOAD, item.getUPLOAD_PATH());
				}
			}
			// E:파일정보삭제
			
			
			//파일 등록 시작
			files = UtilFile.parseFileInf(mFiles, UtilCommon.getRandomStr(5, "_"), FILE_PATH_UPLOAD, FILE_STORE_PATH);
			boolean extChkResult = false;
			if(files != null && !files.isEmpty()){
				for(int n=0; n<files.size(); n++) {
					FileVO extFileChk = files.get(n);
					String extChk = extFileChk.getFILE_EXT();
					if(FILE_EXT.indexOf(extChk.toUpperCase()) < 0) {
						extChkResult = false;
						break;
					}else {
						extChkResult = true;
					}
				}
			}
			
			if(!extChkResult) {
				for(int m=0; m<files.size(); m++) {
					FileVO extFileChk = files.get(m);
					logger.info("허용되지 않은 파일 확장자이기 때문에 파일이 지워집니다.");
					UtilFile.deleteFileInf(extFileChk.getSYS_FILE_NAME(), FILE_PATH_UPLOAD, FILE_STORE_PATH + "/" +UtilDate.getDateFormatt("yyyy"));
				}
				
				rMsg = messageSource.getMessage("validation.file.availableExt", null, localeResolver.resolveLocale(request));
				UtilJsonResult.setReturnCodeFail(json, rMsg);
				return json;
			}
			
			// 파일등록
			BoardFileVO paramVO_file;
			for(FileVO item : files) {
				paramVO_file = new BoardFileVO();
				// 여러개 업로드 가능하다
				paramVO_file.setBOARD_CODE(paramVO.getBOARD_CODE());
				paramVO_file.setUPLOAD_GROUP(FILE_UPLOAD_GROUP);
				paramVO_file.setUPLOAD_PATH(FILE_STORE_PATH + item.getUPLOAD_PATH());
				paramVO_file.setORG_FILE_NAME(item.getORG_FILE_NAME());
				paramVO_file.setSYS_FILE_NAME(item.getSYS_FILE_NAME());
				paramVO_file.setFILE_EXT(item.getFILE_EXT());
				paramVO_file.setFILE_SIZE(item.getFILE_SIZE());
				paramVO_file.setIN_USER(paramVO.getLoginCode());
				boardMapper.boardFileInsert(paramVO_file);
			}
		}
		// E:파일업로드 처리
		
		paramVO.setBOARD_GUBUN("MC0000500002");	
		
		UtilJsonResult.setReturnCodeSuc(json);
		
		return json;
	}
	

	@Override
	public JSONObject thanksDelete(BoardVO paramVO, HttpServletRequest request) throws Exception {
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
	
	@Override
	public JSONObject thanksInsert(BoardVO paramVO, MultipartHttpServletRequest request) throws Exception {
		JSONObject json = new JSONObject();
		UtilJsonResult.setReturnCodeFail(json);
		String rMsg = messageSource.getMessage("error.system.default", null, localeResolver.resolveLocale(request));
		
		// S:유효성체크
		// 이름 체크
		if(UtilCommon.isEmpty(paramVO.getTITLE())) {
			rMsg = messageSource.getMessage("validation.empty.input", new String[] {"타이틀"}, localeResolver.resolveLocale(request));
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
		
		// S:파일업로드 처리
		Map<String, MultipartFile> mFiles = request.getFileMap();
		List<FileVO> files = new ArrayList<FileVO>();
		if(UtilCommon.isEmpty(mFiles)) {
//			rMsg = messageSource.getMessage("validation.empty.input", new String[] {"파일"}, localeResolver.resolveLocale(request));
//			UtilJsonResult.setReturnCodeFail(json, rMsg);
//			return json;
		}else {
			//파일 등록 시작
			files = UtilFile.parseFileInf(mFiles, UtilCommon.getRandomStr(5, "_"), FILE_PATH_UPLOAD, FILE_STORE_PATH);
			boolean extChkResult = false;
			if(files != null && !files.isEmpty()){
				for(int n=0; n<files.size(); n++) {
					FileVO extFileChk = files.get(n);
					String extChk = extFileChk.getFILE_EXT();
					if(FILE_EXT.indexOf(extChk.toUpperCase()) < 0) {
						extChkResult = false;
						break;
					}else {
						extChkResult = true;
					}
				}
			}
			
			if(!extChkResult) {
				for(int m=0; m<files.size(); m++) {
					FileVO extFileChk = files.get(m);
					logger.info("허용되지 않은 파일 확장자이기 때문에 파일이 지워집니다.");
					UtilFile.deleteFileInf(extFileChk.getSYS_FILE_NAME(), FILE_PATH_UPLOAD, FILE_STORE_PATH + "/" +UtilDate.getDateFormatt("yyyy"));
				}
				
				rMsg = messageSource.getMessage("validation.file.availableExt", null, localeResolver.resolveLocale(request));
				UtilJsonResult.setReturnCodeFail(json, rMsg);
				return json;
			}
			
			// 파일등록
			BoardFileVO paramVO_file;
			for(FileVO item : files) {
				paramVO_file = new BoardFileVO();
				// 여러개 업로드 가능하다
				paramVO_file.setBOARD_CODE(paramVO.getBOARD_CODE());
				paramVO_file.setUPLOAD_GROUP(FILE_UPLOAD_GROUP);
				paramVO_file.setUPLOAD_PATH(FILE_STORE_PATH + item.getUPLOAD_PATH());
				paramVO_file.setORG_FILE_NAME(item.getORG_FILE_NAME());
				paramVO_file.setSYS_FILE_NAME(item.getSYS_FILE_NAME());
				paramVO_file.setFILE_EXT(item.getFILE_EXT());
				paramVO_file.setFILE_SIZE(item.getFILE_SIZE());
				paramVO_file.setIN_USER(paramVO.getLoginCode());
				boardMapper.boardFileInsert(paramVO_file);
			}
		}
		// E:파일업로드 처리
		
		// 게시글등록
		paramVO.setIN_USER(paramVO.getLoginCode());
		paramVO.setUP_USER(paramVO.getLoginCode());
		paramVO.setBOARD_GUBUN("MC0000500002");	
		boardMapper.boardInsert(paramVO);
		
		UtilJsonResult.setReturnCodeSuc(json, rMsg);
		
		return json;
	}
	
	@Override
	public List<BoardFileVO> boardFileList(BoardFileVO paramVO) throws Exception {
		List<BoardFileVO> resultListFile = new ArrayList<BoardFileVO>();
		resultListFile = boardMapper.boardFileList(paramVO);
		return resultListFile;
	}

}
