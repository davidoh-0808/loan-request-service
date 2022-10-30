package com.neo.demo.service;

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

import com.neo.common.vo.DemoFileVO;
import com.neo.common.vo.DemoVO;
import com.neo.common.vo.FileVO;
import com.neo.common.vo.MasterKeyVO;
import com.neo.mappers.CommonMapper;
import com.neo.mappers.DemoMapper;
import com.neo.util.UtilCommon;
import com.neo.util.UtilDate;
import com.neo.util.UtilFile;
import com.neo.util.UtilJsonResult;

@Service("demoService")
@Transactional
public class DemoServiceImpl implements DemoService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired MessageSource messageSource;
	@Autowired LocaleResolver localeResolver;
	@Resource(name="demoMapper")
	private DemoMapper demoMapper;
	@Resource(name="commonMapper")
	private CommonMapper commonMapper;
	
	@Value("${file.path.upload}") String FILE_PATH_UPLOAD;
	@Value("${file.ext.board}") String FILE_EXT;
	@Value("${file.store.demo}") String FILE_STORE_PATH;
	@Value("${file.group.demo}") String FILE_UPLOAD_GROUP;
	
	@Override
	public int demoListCount(DemoVO paramVO) throws Exception {
		return demoMapper.demoListCount(paramVO);
	}
	
	@Override
	public List<DemoVO> demoList(DemoVO paramVO) throws Exception {
		List<DemoVO> resultList = new ArrayList<>();
		resultList = demoMapper.demoList(paramVO);
		return resultList;
	}

	@Override
	public DemoVO demoDetail(DemoVO paramVO) throws Exception {
		DemoVO result = new DemoVO();
		result = demoMapper.demoDetail(paramVO);
		
		// 에디터데이타는 html 이므로 unescape 한다
		result.setDEMO_CONTENTS(StringEscapeUtils.unescapeHtml4(result.getDEMO_CONTENTS()));
		
		return result;
	}

	@Override
	public JSONObject demoInsert(DemoVO paramVO, HttpServletRequest request) throws Exception {
		JSONObject json = new JSONObject();
		UtilJsonResult.setReturnCodeFail(json);
		String rMsg = messageSource.getMessage("error.system.default", null, localeResolver.resolveLocale(request));

		// S:유효성체크
		// 이름 체크
		if(UtilCommon.isEmpty(paramVO.getDEMO_TITLE())) {
			rMsg = messageSource.getMessage("validation.empty.input", new String[] {"데모타이틀"}, localeResolver.resolveLocale(request)); 
			UtilJsonResult.setReturnCodeFail(json, rMsg);
			return json;
			
		}
		// E:유효성체크
		
		// S: 코드 생성
		String masterKey = "";
		MasterKeyVO masterKeyVO = new MasterKeyVO();		
		masterKeyVO.setKEY_GBN("DEMO_CODE");
		masterKey = commonMapper.getFnGetMasterKey(masterKeyVO);
		paramVO.setDEMO_CODE(masterKey);
		// E: 코드 생성
		
		paramVO.setIN_USER(paramVO.getLoginId());
		paramVO.setUP_USER(paramVO.getLoginId());
		demoMapper.demoInsert(paramVO);

		UtilJsonResult.setReturnCodeSuc(json);
		
		return json;
	}

	@Override
	public JSONObject demoUpdate(DemoVO paramVO, HttpServletRequest request) throws Exception {
		JSONObject json = new JSONObject();
		UtilJsonResult.setReturnCodeFail(json);
		String rMsg = messageSource.getMessage("error.system.default", null, localeResolver.resolveLocale(request));
		
		// S:유효성체크
		if(UtilCommon.isEmpty(paramVO.getDEMO_TITLE())) {
			rMsg = messageSource.getMessage("validation.empty.input", new String[] {"데모타이틀"}, localeResolver.resolveLocale(request)); 
			UtilJsonResult.setReturnCodeFail(json, rMsg);
			return json;
		}
		if(UtilCommon.isEmpty(demoMapper.demoDetail(paramVO))) {
			rMsg = messageSource.getMessage("validation.update.empty.object", null, localeResolver.resolveLocale(request)); 
			UtilJsonResult.setReturnCodeFail(json, rMsg);
			return json;
		}
		// E:유효성체크
		
		paramVO.setUP_USER(paramVO.getLoginId());
		demoMapper.demoUpdate(paramVO);
		
		UtilJsonResult.setReturnCodeSuc(json);
		
		return json;
	}

	@Override
	public JSONObject demoDelete(DemoVO paramVO, HttpServletRequest request) throws Exception {
		JSONObject json = new JSONObject();
		UtilJsonResult.setReturnCodeFail(json);
		String rMsg = messageSource.getMessage("error.system.default", null, localeResolver.resolveLocale(request));
		
		// S:유효성체크
		if(UtilCommon.isEmpty(demoMapper.demoDetail(paramVO))) {
			rMsg = messageSource.getMessage("validation.delete.empty.object", null, localeResolver.resolveLocale(request)); 
			UtilJsonResult.setReturnCodeFail(json, rMsg);
			return json;
		}
		// E:유효성체크
		
		paramVO.setDEL_YN("Y");
		paramVO.setUP_USER(paramVO.getLoginId());
		demoMapper.demoDelete(paramVO);
		
		// S:파일정보삭제
		DemoFileVO paramFileVO = new DemoFileVO();
		List<DemoFileVO> resultListFile = new ArrayList<DemoFileVO>();
		paramFileVO.setDEMO_CODE(paramVO.getDEMO_CODE());
		resultListFile = demoMapper.demoFileList(paramFileVO);
		if(UtilCommon.isNotEmpty(resultListFile)) {
			demoMapper.demoFileDelete(paramFileVO);
			// 실제파일삭제
			for(DemoFileVO item: resultListFile) {
				UtilFile.deleteFileInf(item.getSYS_FILE_NAME(), FILE_PATH_UPLOAD, item.getUPLOAD_PATH());
			}
		}
		// E:파일정보삭제
		
		UtilJsonResult.setReturnCodeSuc(json);
		
		return json;
	}

	@Override
	public JSONObject demoInsert(DemoVO paramVO, MultipartHttpServletRequest request) throws Exception {
		JSONObject json = new JSONObject();
		UtilJsonResult.setReturnCodeFail(json);
		String rMsg = messageSource.getMessage("error.system.default", null, localeResolver.resolveLocale(request));
		
		// S:유효성체크
		// 이름 체크
		if(UtilCommon.isEmpty(paramVO.getDEMO_TITLE())) {
			rMsg = messageSource.getMessage("validation.empty.input", new String[] {"데모타이틀"}, localeResolver.resolveLocale(request));
			UtilJsonResult.setReturnCodeFail(json, rMsg);
			return json;
			
		}
		// E:유효성체크
		
		// S: 코드 생성
		String masterKey = "";
		MasterKeyVO masterKeyVO = new MasterKeyVO();		
		masterKeyVO.setKEY_GBN("DEMO_CODE");
		masterKey = commonMapper.getFnGetMasterKey(masterKeyVO);
		paramVO.setDEMO_CODE(masterKey);
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
			DemoFileVO paramVO_file;
			for(FileVO item : files) {
				paramVO_file = new DemoFileVO();
				// 여러개 업로드 가능하다
				paramVO_file.setDEMO_CODE(paramVO.getDEMO_CODE());
				paramVO_file.setUPLOAD_GROUP(FILE_UPLOAD_GROUP);
				paramVO_file.setUPLOAD_PATH(FILE_STORE_PATH + item.getUPLOAD_PATH());
				paramVO_file.setORG_FILE_NAME(item.getORG_FILE_NAME());
				paramVO_file.setSYS_FILE_NAME(item.getSYS_FILE_NAME());
				paramVO_file.setFILE_EXT(item.getFILE_EXT());
				paramVO_file.setFILE_SIZE(item.getFILE_SIZE());
				paramVO_file.setIN_USER(paramVO.getLoginId());
				demoMapper.demoFileInsert(paramVO_file);
			}
		}
		// E:파일업로드 처리
		
		// 데모등록
		paramVO.setIN_USER(paramVO.getLoginId());
		paramVO.setUP_USER(paramVO.getLoginId());
		demoMapper.demoInsert(paramVO);
		
		UtilJsonResult.setReturnCodeSuc(json, rMsg);
		
		return json;
	}

	@Override
	public JSONObject demoUpdate(DemoVO paramVO, MultipartHttpServletRequest request) throws Exception {
		JSONObject json = new JSONObject();
		UtilJsonResult.setReturnCodeFail(json);
		String rMsg = messageSource.getMessage("error.system.default", null, localeResolver.resolveLocale(request));
		
		// S:유효성체크
		if(UtilCommon.isEmpty(paramVO.getDEMO_TITLE())) {
			rMsg = messageSource.getMessage("validation.empty.input", new String[] {"데모타이틀"}, localeResolver.resolveLocale(request)); 
			UtilJsonResult.setReturnCodeFail(json, rMsg);
			return json;
		}
		if(UtilCommon.isEmpty(demoMapper.demoDetail(paramVO))) {
			rMsg = messageSource.getMessage("validation.update.empty.object", null, localeResolver.resolveLocale(request)); 
			UtilJsonResult.setReturnCodeFail(json, rMsg);
			return json;
		}
		// E:유효성체크
		
		paramVO.setUP_USER(paramVO.getLoginId());
		demoMapper.demoUpdate(paramVO);
		
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
			DemoFileVO paramFileVO = new DemoFileVO();
			List<DemoFileVO> resultListFile = new ArrayList<DemoFileVO>();
			paramFileVO.setDEMO_CODE(paramVO.getDEMO_CODE());
			resultListFile = demoMapper.demoFileList(paramFileVO);
			if(UtilCommon.isNotEmpty(resultListFile)) {
				demoMapper.demoFileDelete(paramFileVO);
				// 실제파일삭제
				for(DemoFileVO item: resultListFile) {
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
			DemoFileVO paramVO_file;
			for(FileVO item : files) {
				paramVO_file = new DemoFileVO();
				// 여러개 업로드 가능하다
				paramVO_file.setDEMO_CODE(paramVO.getDEMO_CODE());
				paramVO_file.setUPLOAD_GROUP(FILE_UPLOAD_GROUP);
				paramVO_file.setUPLOAD_PATH(FILE_STORE_PATH + item.getUPLOAD_PATH());
				paramVO_file.setORG_FILE_NAME(item.getORG_FILE_NAME());
				paramVO_file.setSYS_FILE_NAME(item.getSYS_FILE_NAME());
				paramVO_file.setFILE_EXT(item.getFILE_EXT());
				paramVO_file.setFILE_SIZE(item.getFILE_SIZE());
				paramVO_file.setIN_USER(paramVO.getLoginId());
				demoMapper.demoFileInsert(paramVO_file);
			}
		}
		// E:파일업로드 처리
		
		
		
		UtilJsonResult.setReturnCodeSuc(json);
		
		return json;
	}
	
	@Override
	public List<DemoFileVO> demoFileList(DemoFileVO paramVO) throws Exception {
		List<DemoFileVO> resultListFile = new ArrayList<DemoFileVO>();
		resultListFile = demoMapper.demoFileList(paramVO);
		return resultListFile;
	}

	@Override
	public DemoFileVO demoFileDetail(DemoFileVO paramVO) throws Exception {
		return demoMapper.demoFileDetail(paramVO);
	}

}
