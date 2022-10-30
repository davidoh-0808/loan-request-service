package com.neo.util;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.neo.common.vo.FileVO;


public class UtilFile {
	private static final Logger logger = LoggerFactory.getLogger(UtilFile.class);

	private static String allow_file;
	private static String deny_file;
	
	public static String getFileExtension(String fileName) {
		String[] file = fileName.split("[.]");
		logger.debug("getFileExtension" + file.length);
		return (file.length > 1) ? file[1] : "";
	}

	/**
	 * 단 건 첨부파일 등록시 파일정보 (폼에 파일이 하나인 경우)
	 * 파일명은 접두어 + yyyyMMddhhmmssSSS
	 * @param file
	 * @param KeyStr	파일접두어
	 * @param fileStorePath	파일저장 루트경로
	 * @param storePath	파일저장 추가경로
	 * @return
	 * @throws Exception
	 */
	public static FileVO parseFileInf(MultipartFile file, String KeyStr, String fileStorePath, String storePath)
			throws Exception {
		if (UtilCommon.isEmpty(file)) {
			return null;
		}
		String orginFileName = file.getOriginalFilename();
		// --------------------------------------
		// 원 파일명이 없는 경우 처리
		// (첨부가 되지 않은 input file type)
		// --------------------------------------
		if (UtilCommon.isEmpty(orginFileName)) { return null; }

		logger.debug("FILESTOREPATH:" + fileStorePath);
		logger.debug("STOREPATH:" + storePath);
		
		String savePath = filePathBlackList(fileStorePath + storePath);
		File saveFolder = new File(savePath);

		if (!saveFolder.exists() || saveFolder.isFile()) {
			saveFolder.mkdirs();
		}

		int index = orginFileName.lastIndexOf(".");
		String fileExt = orginFileName.substring(index + 1);
		String newName = KeyStr + getTimeStamp() + "." + fileExt;
		long size = file.getSize();
		String filePath = savePath + "/" + newName;
		logger.debug("filePath" + filePath);
		file.transferTo(new File(filePath));

		FileVO fileVO = new FileVO();
		fileVO.setFILE_EXT(fileExt);	
		fileVO.setUPLOAD_PATH(storePath);
		fileVO.setFILE_SIZE(String.valueOf(size));
		fileVO.setORG_FILE_NAME(orginFileName);
		fileVO.setSYS_FILE_NAME(newName);
		
		return fileVO;
	}
	
	/**
	 * 다중 건 첨부파일 등록시 파일정보  (폼에 파일이 하나이상 경우)
	 * 파일명은 접두어 + yyyyMMddhhmmssSSS + seq(멀티일경우)
	 * @param files
	 * @param KeyStr	파일접두어
	 * @param fileStorePath	파일저장 루트경로
	 * @param storePath	파일저장 추가경로
	 * @return
	 * @throws Exception
	 */
	public static List<FileVO> parseFileInf(Map<String, MultipartFile> files, String KeyStr, String fileStorePath, String storePath) throws Exception {
		if (UtilCommon.isEmpty(files)) {
			return null;
		}
		int fileKey = 0; 

		String saveFilePath = "/" + UtilDate.getDateFormatt("yyyy");
		String savePath = filePathBlackList(fileStorePath + storePath + saveFilePath);
		File saveFolder = new File(savePath);

		if (!saveFolder.exists() || saveFolder.isFile()) {
			saveFolder.mkdirs();
		}

		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		MultipartFile file;
		String filePath = "";
		List<FileVO> result = new ArrayList<FileVO>();
		FileVO fileVO = new FileVO();

		while (itr.hasNext()) {
			Entry<String, MultipartFile> entry = itr.next();
			
			file = entry.getValue();
			String orginFileName = file.getOriginalFilename();

			logger.info("ContentType():"+file.getContentType());
			
			// --------------------------------------
			// 원 파일명이 없는 경우 처리
			// (첨부가 되지 않은 input file type)
			// --------------------------------------
			if (UtilCommon.isEmpty(orginFileName)) {
				continue;
			}

			int index = orginFileName.lastIndexOf(".");
			String fileExt = "";
			if(index != -1) {
				fileExt = "." + orginFileName.substring(index + 1);
			}

			String newName = "";
			newName = KeyStr + getTimeStamp() + fileKey + fileExt;

			logger.debug("entry.getKey():"+entry.getKey());
			logger.debug("KeyStr:"+KeyStr);
			logger.debug("newName:"+newName);
			
			long size = file.getSize();

			filePath = savePath + "/" + newName;
			logger.debug("filePath:" + filePath);
			file.transferTo(new File(filePath));

			fileVO = new FileVO();
			fileVO.setFILE_EXT(fileExt);	
			fileVO.setUPLOAD_PATH(saveFilePath);
			fileVO.setFILE_SIZE(String.valueOf(size));
			fileVO.setORG_FILE_NAME(orginFileName);
			fileVO.setSYS_FILE_NAME(newName);
			fileVO.setELEMENT_NM(entry.getKey());
			result.add(fileVO);

			fileKey++;
		}

		if (result.size() == 0) {
			result = null;
		}
		return result;
	}	
	
	/**
	 * <pre>
	 * files 각각의 파일의 경로가 다를경우
	 * Required value : [필수값을 입력해주세요.]
	 * </pre>
	 * [메소드 설명을 상세하게 작성해 주세요. (html태그 사용가능)] <br>
	 * <p style="color:red;"> [강조 할 때 사용합니다.]<p>
	 * @author leekw
	 * @since 2019. 12. 6.
	 * @param files
	 * @param KeyStr
	 * @param fileStorePath
	 * @param storePath
	 * @return
	 * @throws Exception List<FileVO> [이글을 지우고 위 파라메타 및 설명을 각 값 오른쪽에 작성해주세요.]
	 */
	public static List<FileVO> parseFileInf(Map<String, MultipartFile> files, String KeyStr, String fileStorePath, Map<String, String> storePaths) throws Exception {
		if (UtilCommon.isEmpty(files)) {
			return null;
		}
		int fileKey = 0;
		
		for(String key : storePaths.keySet()) {
			String savePath = filePathBlackList(fileStorePath + storePaths.get(key));
			File saveFolder = new File(savePath);
			if (!saveFolder.exists() || saveFolder.isFile()) {
				saveFolder.mkdirs();
			}
		}
		
		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		MultipartFile file;
		String filePath = "";
		List<FileVO> result = new ArrayList<FileVO>();
		FileVO fileVO = new FileVO();
		
		while (itr.hasNext()) {
			Entry<String, MultipartFile> entry = itr.next();
			
			file = entry.getValue();
			String orginFileName = file.getOriginalFilename();
			
			logger.info("ContentType():"+file.getContentType());
			
			// --------------------------------------
			// 원 파일명이 없는 경우 처리
			// (첨부가 되지 않은 input file type)
			// --------------------------------------
			if (UtilCommon.isEmpty(orginFileName)) {
				continue;
			}
			
			int index = orginFileName.lastIndexOf(".");
			String fileExt = "";
			if(index != -1) {
				fileExt = "." + orginFileName.substring(index + 1);
			}
			
			String newName = "";
			newName = KeyStr + getTimeStamp() + fileKey + fileExt;
			
			logger.debug("entry.getKey():"+entry.getKey());
			logger.debug("KeyStr:"+KeyStr);
			logger.debug("newName:"+newName);
			
			long size = file.getSize();
			
			String savePath =fileStorePath;
			String storePath ="";
			for(String key : storePaths.keySet()) {
				if(entry.getKey().equalsIgnoreCase(key)) {
					savePath = fileStorePath + storePaths.get(key);
					storePath = storePaths.get(key);
				}
			}
			
			filePath = savePath + "/" + newName;
			file.transferTo(new File(filePathBlackList(filePath)));
			
			fileVO = new FileVO();
			fileVO.setFILE_EXT(fileExt);	
			fileVO.setUPLOAD_PATH(storePath);
			fileVO.setFILE_SIZE(String.valueOf(size));
			fileVO.setORG_FILE_NAME(orginFileName);
			fileVO.setSYS_FILE_NAME(newName);
			fileVO.setELEMENT_NM(entry.getKey());
			result.add(fileVO);
			
			fileKey++;
		}
		
		if (result.size() == 0) {
			result = null;
		}
		return result;
	}	
	
	private static String filePathBlackList(String value) {
		String returnValue = value;
		if (returnValue == null || returnValue.trim().equals("")) {
			return "";
		}

		returnValue = returnValue.replaceAll("\\.\\./", ""); // ../
		returnValue = returnValue.replaceAll("\\.\\.\\\\", ""); // ..\

		return returnValue;
	}

	/**
	 * 파일명으로 사용하기 위해 밀리세컨드까지
	 * @return
	 */
	private static String getTimeStamp() {
		String rtnStr = null;
		String pattern = "yyyyMMddhhmmssSSS";
		SimpleDateFormat sdfCurrent = new SimpleDateFormat(pattern, Locale.KOREA);
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		rtnStr = sdfCurrent.format(ts.getTime());
		return rtnStr;
	}
	
	
	public static boolean checkFileExtension(MultipartHttpServletRequest multiRequest) {

		String file_extension;
		String filename = "";
		int rtn = 0;
		// String deny_file_extension="jsp,asp,php,java,sh";
		// String
		// allow_file_extension="txt,doc,docx,ppt,pptx,xls,xlsx,hwp,pdf,png,gif,jpeg";
		// logger.debug("### checkFileExtension
		// allow_file:["+allow_file+"]"+multiRequest.getContentType());

		List<String> allowFileExtension = Arrays.asList(allow_file.split(","));
		List<String> denyFileExtension = Arrays.asList(deny_file.split(","));

		Iterator<String> names = multiRequest.getFileNames();

		// logger.debug("### checkFileExtension
		// multiRequest.getFileMap().size():["+multiRequest.getFileMap().size()+"]");

		if (multiRequest.getFileMap().size() == 0) {
			rtn++;
		}
		while (names.hasNext()) {
			filename = (String) names.next();
			// logger.debug("### checkFileExtension filename:["+filename+"]");
			List<MultipartFile> files3 = multiRequest.getFiles(filename);

			for (MultipartFile mfile : files3) {
				// logger.debug("### checkFileExtension
				// ["+mfile.getName()+"],OriginalFilename:["+mfile.getOriginalFilename()+"],file
				// Extension["+FilenameUtils.getExtension(mfile.getOriginalFilename())+"]");
				if (mfile.getOriginalFilename() == null || mfile.getOriginalFilename().equals("")) {
					rtn++;
				}

				file_extension = FilenameUtils.getExtension(mfile.getOriginalFilename());
				if (allowFileExtension.size() > 0 && allowFileExtension.contains(file_extension.toLowerCase())) {
					// logger.debug("### checkFileExtension"+(new
					// StringBuilder(String.valueOf(file_extension))).append("
					// file type is allowed to be uploaded!").toString());
					rtn++;
				}
				if (denyFileExtension.size() > 0 && denyFileExtension.contains(file_extension.toLowerCase())) {
					// logger.debug("### checkFileExtension"+(new
					// StringBuilder(String.valueOf(file_extension))).append("
					// file type is not allowed to be uploaded!").toString());
					rtn = -2;
				}
			}
			logger.debug("### checkFileExtension rtn :" + rtn);
		}
		logger.debug("### checkFileExtension b_rtn:[" + rtn + "]");
		return (rtn > 0) ? true : false;
	}

	public static void deleteFileInf(String sysFileName, String fileStorePath, String storePath) throws Exception {
		String savePath = filePathBlackList(fileStorePath + storePath +"/"+ sysFileName);
		File saveFolder = new File(savePath);
		saveFolder.delete();
	}
	
	
}
