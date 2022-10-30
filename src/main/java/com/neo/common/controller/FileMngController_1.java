package com.neo.common.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.LocaleResolver;

import com.neo.common.vo.DemoFileVO;
import com.neo.common.vo.FileVO;
import com.neo.demo.service.DemoService;
import com.neo.security.CustomAdminDetails;
import com.neo.security.CustomMemberDetails;
import com.neo.util.UtilCommon;
import com.neo.util.UtilFile;
import com.neo.util.UtilJsonResult;


@Controller
public class FileMngController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired MessageSource messageSource;
	@Autowired LocaleResolver localeResolver;
	
	@Resource(name="demoService")
	private DemoService demoService;

	@Value("${file.path.upload}") String FILE_PATH_UPLOAD;
	@Value("${file.group.demo}") String FILE_GROUP_DEMO;
	@Value("${server.domain.pc}") String SERVER_DOMAIN_PC;
	
	// 에디터
	@Value("${file.path.external}") String FILE_PATH_EXTERNAL;
	@Value("${file.viewPath.external}") String FILE_VIEWPATH_EXTERNAL;
	@Value("${file.store.editor}") String FILE_STORE_EDITOR;
	@Value("${file.prefix.editor}") String FILE_PREFIX_EDITOR;
	@Value("${file.ext.editor}") String FILE_EXT_EDITOR;
	
	// 샘플양식
	@Value("${file.store.sample}") String FILE_STORE_SAMPLE;
	@Value("${file.group.sample}") String FILE_GROUP_SAMPLE;
	
	/**
	 * 파일다운로드
	 * @param locale
	 * @param upload_group
	 * @return
	 */
	@PostMapping(value="/public/fileDownload/{upload_group}")
	public void fileDownloadPublic(@PathVariable String upload_group, @RequestParam("FILE_SEQ") Integer FILE_SEQ
			, HttpServletRequest request, HttpServletResponse response) throws Exception{

		logger.info("upload_group:"+upload_group);
		logger.info("file_seq:"+FILE_SEQ);
		
		FileVO fileVO = new FileVO();
		
		if(FILE_GROUP_DEMO.equalsIgnoreCase(upload_group)) {
			
			// 데모
			DemoFileVO paramVO = new DemoFileVO();
			DemoFileVO resultVO = new DemoFileVO();
			paramVO.setFILE_SEQ(FILE_SEQ);
			resultVO = demoService.demoFileDetail(paramVO);
			
			if(UtilCommon.isNotEmpty(resultVO)) {
				// 다운받을 파일정보 설정
				fileVO.setUPLOAD_PATH(resultVO.getUPLOAD_PATH());
				fileVO.setORG_FILE_NAME(resultVO.getORG_FILE_NAME());
				fileVO.setSYS_FILE_NAME(resultVO.getSYS_FILE_NAME());
			}
		}else {}
		
		// 파일 다운로드
		if(UtilCommon.isNotEmpty(fileVO)) {
			
			String uploadPath = fileVO.getUPLOAD_PATH();
			// upload_path의 경로 검증
			if(UtilCommon.isNotEmpty(uploadPath)) {
				uploadPath = uploadPath.replace(".", " ");
				uploadPath = uploadPath.replaceAll("\\.\\./", ""); // ../
				uploadPath = uploadPath.replaceAll("\\.\\.\\\\", ""); // ..\
				uploadPath = uploadPath.replace("&", " ");
				uploadPath = uploadPath.replaceAll(" ", "");
				fileVO.setUPLOAD_PATH(uploadPath);
			}
			
			
			File uFile = new File(FILE_PATH_UPLOAD + fileVO.getUPLOAD_PATH()+File.separator+fileVO.getSYS_FILE_NAME());
			int fSize = (int) uFile.length();
			
			logger.info("FILE DOWNLOAD-size:"+fSize);
			logger.info("FILE DOWNLOAD-path:"+FILE_PATH_UPLOAD + fileVO.getUPLOAD_PATH()+File.separator+fileVO.getSYS_FILE_NAME());
			
			if (fSize > 0) {
				String mimetype = "application/x-msdownload";
				int e = fileVO.getSYS_FILE_NAME().lastIndexOf(".");
				int p = Math.max(fileVO.getSYS_FILE_NAME().lastIndexOf("/"), fileVO.getSYS_FILE_NAME().lastIndexOf("\\"));
				String extension = "";
				if(e > p){
					extension = fileVO.getSYS_FILE_NAME().substring(e + 1);
					if("pdf".matches(extension.toLowerCase())){
						logger.info("MATCH OK:pdf");
						mimetype = "application/pdf";
					}else if("ppt".matches(extension.toLowerCase()) || "pptx".matches(extension.toLowerCase())){
						logger.info("MATCH OK:ppt or pptx");
						mimetype = "application/vnd.ms-powerpoint";
					}else if("xls".matches(extension.toLowerCase()) || "xlsx".matches(extension.toLowerCase())){
						logger.info("MATCH OK:xls or xlsx");
						mimetype = "application/vnd.ms-excel";
					}else if("doc".matches(extension.toLowerCase()) || "docx".matches(extension.toLowerCase())){
						logger.info("MATCH OK:doc or docs");
						mimetype = "application/msword";
					}else if("jpg".matches(extension.toLowerCase()) || "jpeg".matches(extension.toLowerCase())){
						logger.info("MATCH OK:jpg or jpeg");
						mimetype = "image/jpg";
					}
				}
				
				response.setContentType(mimetype);
				setDisposition(fileVO.getORG_FILE_NAME(), request, response);
				response.setContentLength(fSize);
				
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Dispotition", "attachment;filename="+fileVO.getORG_FILE_NAME());
				
				BufferedInputStream in = null;
				BufferedOutputStream out = null;
				
				try {
					in = new BufferedInputStream(new FileInputStream(uFile));
					out = new BufferedOutputStream(response.getOutputStream());
					
					FileCopyUtils.copy(in, out);
					out.flush();
				} catch (IOException ex) {
					ex.printStackTrace();
				} finally {
					UtilCommon.close(in, out);
				}
				
			} else {
				response.setContentType("application/x-msdownload");
				
				PrintWriter printwriter = response.getWriter();
				
				printwriter.println("<html>");
				printwriter.println("<br><br><br><h2> 파일을 찾을수가 없습니다:<br>" + fileVO.getORG_FILE_NAME() + "</h2>");
				printwriter.println("</html>");
				
				printwriter.flush();
				printwriter.close();
			}
		}
	}
	
	/**
	 * 파일다운로드
	 * @param locale
	 * @param upload_group
	 * @return
	 */
	@PostMapping(value="/member/fileDownload/{upload_group}")
	public void fileDownloadMember(@PathVariable String upload_group, @RequestParam("FILE_SEQ") Integer FILE_SEQ
			, HttpServletRequest request, HttpServletResponse response) throws Exception{
		CustomMemberDetails memberDetail = (CustomMemberDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
		logger.info("upload_group:"+upload_group);
		logger.info("file_seq:"+FILE_SEQ);
		
		FileVO fileVO = new FileVO();
		
		if(FILE_GROUP_DEMO.equalsIgnoreCase(upload_group)) {
			
			// 데모
			DemoFileVO paramVO = new DemoFileVO();
			DemoFileVO resultVO = new DemoFileVO();
			paramVO.setFILE_SEQ(FILE_SEQ);
			resultVO = demoService.demoFileDetail(paramVO);
			
			if(UtilCommon.isNotEmpty(resultVO)) {
				// 다운받을 파일정보 설정
				fileVO.setUPLOAD_PATH(resultVO.getUPLOAD_PATH());
				fileVO.setORG_FILE_NAME(resultVO.getORG_FILE_NAME());
				fileVO.setSYS_FILE_NAME(resultVO.getSYS_FILE_NAME());
			}
		}else {}
		
		// 파일 다운로드
		if(UtilCommon.isNotEmpty(fileVO)) {
			
			String uploadPath = fileVO.getUPLOAD_PATH();
			// upload_path의 경로 검증
			if(UtilCommon.isNotEmpty(uploadPath)) {
				uploadPath = uploadPath.replace(".", " ");
				uploadPath = uploadPath.replaceAll("\\.\\./", ""); // ../
				uploadPath = uploadPath.replaceAll("\\.\\.\\\\", ""); // ..\
				uploadPath = uploadPath.replace("&", " ");
				uploadPath = uploadPath.replaceAll(" ", "");
				fileVO.setUPLOAD_PATH(uploadPath);
			}
			
			
			File uFile = new File(FILE_PATH_UPLOAD + fileVO.getUPLOAD_PATH()+File.separator+fileVO.getSYS_FILE_NAME());
			int fSize = (int) uFile.length();
			
			logger.info("FILE DOWNLOAD-size:"+fSize);
			logger.info("FILE DOWNLOAD-path:"+FILE_PATH_UPLOAD + fileVO.getUPLOAD_PATH()+File.separator+fileVO.getSYS_FILE_NAME());
			
			if (fSize > 0) {
				String mimetype = "application/x-msdownload";
				int e = fileVO.getSYS_FILE_NAME().lastIndexOf(".");
				int p = Math.max(fileVO.getSYS_FILE_NAME().lastIndexOf("/"), fileVO.getSYS_FILE_NAME().lastIndexOf("\\"));
				String extension = "";
				if(e > p){
					extension = fileVO.getSYS_FILE_NAME().substring(e + 1);
					if("pdf".matches(extension.toLowerCase())){
						logger.info("MATCH OK:pdf");
						mimetype = "application/pdf";
					}else if("ppt".matches(extension.toLowerCase()) || "pptx".matches(extension.toLowerCase())){
						logger.info("MATCH OK:ppt or pptx");
						mimetype = "application/vnd.ms-powerpoint";
					}else if("xls".matches(extension.toLowerCase()) || "xlsx".matches(extension.toLowerCase())){
						logger.info("MATCH OK:xls or xlsx");
						mimetype = "application/vnd.ms-excel";
					}else if("doc".matches(extension.toLowerCase()) || "docx".matches(extension.toLowerCase())){
						logger.info("MATCH OK:doc or docs");
						mimetype = "application/msword";
					}else if("jpg".matches(extension.toLowerCase()) || "jpeg".matches(extension.toLowerCase())){
						logger.info("MATCH OK:jpg or jpeg");
						mimetype = "image/jpg";
					}
				}
				
				response.setContentType(mimetype);
				setDisposition(fileVO.getORG_FILE_NAME(), request, response);
				response.setContentLength(fSize);
				
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Dispotition", "attachment;filename="+fileVO.getORG_FILE_NAME());
				
				BufferedInputStream in = null;
				BufferedOutputStream out = null;
				
				try {
					in = new BufferedInputStream(new FileInputStream(uFile));
					out = new BufferedOutputStream(response.getOutputStream());
					
					FileCopyUtils.copy(in, out);
					out.flush();
				} catch (IOException ex) {
					ex.printStackTrace();
				} finally {
					UtilCommon.close(in, out);
				}
				
			} else {
				response.setContentType("application/x-msdownload");
				
				PrintWriter printwriter = response.getWriter();
				
				printwriter.println("<html>");
				printwriter.println("<br><br><br><h2> 파일을 찾을수가 없습니다:<br>" + fileVO.getORG_FILE_NAME() + "</h2>");
				printwriter.println("</html>");
				
				printwriter.flush();
				printwriter.close();
			}
		}
	}
	
	@PostMapping(value="/admin/fileDownload/{upload_group}")
	public void fileDownloadAdmin(@PathVariable String upload_group, @RequestParam("FILE_SEQ") Integer FILE_SEQ
			, HttpServletRequest request, HttpServletResponse response) throws Exception{
		CustomAdminDetails adminDetail = (CustomAdminDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
		logger.info("upload_group:"+upload_group);
		logger.info("file_seq:"+FILE_SEQ);
		
		FileVO fileVO = new FileVO();
		
		if(FILE_GROUP_DEMO.equalsIgnoreCase(upload_group)) {
			
			// 데모
			DemoFileVO paramVO = new DemoFileVO();
			DemoFileVO resultVO = new DemoFileVO();
			paramVO.setFILE_SEQ(FILE_SEQ);
			resultVO = demoService.demoFileDetail(paramVO);
			
			if(UtilCommon.isNotEmpty(resultVO)) {
				// 다운받을 파일정보 설정
				fileVO.setUPLOAD_PATH(resultVO.getUPLOAD_PATH());
				fileVO.setORG_FILE_NAME(resultVO.getORG_FILE_NAME());
				fileVO.setSYS_FILE_NAME(resultVO.getSYS_FILE_NAME());
			}
		}else {}
		
		// 파일 다운로드
		if(UtilCommon.isNotEmpty(fileVO)) {
			
			String uploadPath = fileVO.getUPLOAD_PATH();
			// upload_path의 경로 검증
			if(UtilCommon.isNotEmpty(uploadPath)) {
				uploadPath = uploadPath.replace(".", " ");
				uploadPath = uploadPath.replaceAll("\\.\\./", ""); // ../
				uploadPath = uploadPath.replaceAll("\\.\\.\\\\", ""); // ..\
				uploadPath = uploadPath.replace("&", " ");
				uploadPath = uploadPath.replaceAll(" ", "");
				fileVO.setUPLOAD_PATH(uploadPath);
			}
			
			
			File uFile = new File(FILE_PATH_UPLOAD + fileVO.getUPLOAD_PATH()+File.separator+fileVO.getSYS_FILE_NAME());
			int fSize = (int) uFile.length();
			
			logger.info("FILE DOWNLOAD-size:"+fSize);
			logger.info("FILE DOWNLOAD-path:"+FILE_PATH_UPLOAD + fileVO.getUPLOAD_PATH()+File.separator+fileVO.getSYS_FILE_NAME());
			
			if (fSize > 0) {
				String mimetype = "application/x-msdownload";
				int e = fileVO.getSYS_FILE_NAME().lastIndexOf(".");
				int p = Math.max(fileVO.getSYS_FILE_NAME().lastIndexOf("/"), fileVO.getSYS_FILE_NAME().lastIndexOf("\\"));
				String extension = "";
				if(e > p){
					extension = fileVO.getSYS_FILE_NAME().substring(e + 1);
					if("pdf".matches(extension.toLowerCase())){
						logger.info("MATCH OK:pdf");
						mimetype = "application/pdf";
					}else if("ppt".matches(extension.toLowerCase()) || "pptx".matches(extension.toLowerCase())){
						logger.info("MATCH OK:ppt or pptx");
						mimetype = "application/vnd.ms-powerpoint";
					}else if("xls".matches(extension.toLowerCase()) || "xlsx".matches(extension.toLowerCase())){
						logger.info("MATCH OK:xls or xlsx");
						mimetype = "application/vnd.ms-excel";
					}else if("doc".matches(extension.toLowerCase()) || "docx".matches(extension.toLowerCase())){
						logger.info("MATCH OK:doc or docs");
						mimetype = "application/msword";
					}else if("jpg".matches(extension.toLowerCase()) || "jpeg".matches(extension.toLowerCase())){
						logger.info("MATCH OK:jpg or jpeg");
						mimetype = "image/jpg";
					}
				}
				
				response.setContentType(mimetype);
				setDisposition(fileVO.getORG_FILE_NAME(), request, response);
				response.setContentLength(fSize);
				
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Dispotition", "attachment;filename="+fileVO.getORG_FILE_NAME());
				
				BufferedInputStream in = null;
				BufferedOutputStream out = null;
				
				try {
					in = new BufferedInputStream(new FileInputStream(uFile));
					out = new BufferedOutputStream(response.getOutputStream());
					
					FileCopyUtils.copy(in, out);
					out.flush();
				} catch (IOException ex) {
					ex.printStackTrace();
				} finally {
					UtilCommon.close(in, out);
				}
				
			} else {
				response.setContentType("application/x-msdownload");
				
				PrintWriter printwriter = response.getWriter();
				
				printwriter.println("<html>");
				printwriter.println("<br><br><br><h2> 파일을 찾을수가 없습니다:<br>" + fileVO.getORG_FILE_NAME() + "</h2>");
				printwriter.println("</html>");
				
				printwriter.flush();
				printwriter.close();
			}
		}
	}
	
	/**
	 * 서버에 올려논 파일 양식샘플같은
	 * @param locale
	 * @param upload_group
	 * @param org_file_name
	 * @param sys_file_name
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@PostMapping(value="/common/fileDownload/{upload_group}")
	public void fileDownloadCommon(Locale locale
			, @PathVariable String upload_group
			, @RequestParam("ORG_FILE_NAME") String org_file_name
			, @RequestParam("SYS_FILE_NAME") String sys_file_name
			, HttpServletRequest request, HttpServletResponse response) throws Exception{
		logger.info("upload_group:"+upload_group);
		
		FileVO fileVO = new FileVO();
		
		if(FILE_GROUP_SAMPLE.equalsIgnoreCase(upload_group)) {
			// 각종양식
			fileVO.setUPLOAD_PATH(FILE_STORE_SAMPLE);
			fileVO.setORG_FILE_NAME(org_file_name);
			fileVO.setSYS_FILE_NAME(sys_file_name);
		}else {}
		
		// 파일 다운로드
		if(UtilCommon.isNotEmpty(fileVO)) {
			
			String uploadPath = fileVO.getUPLOAD_PATH();
			// upload_path의 경로 검증
			if(UtilCommon.isNotEmpty(uploadPath)) {
				uploadPath = uploadPath.replace(".", " ");
				uploadPath = uploadPath.replaceAll("\\.\\./", ""); // ../
				uploadPath = uploadPath.replaceAll("\\.\\.\\\\", ""); // ..\
				uploadPath = uploadPath.replace("&", " ");
				uploadPath = uploadPath.replaceAll(" ", "");
				fileVO.setUPLOAD_PATH(uploadPath);
			}
			
			
			File uFile = new File(FILE_PATH_EXTERNAL + fileVO.getUPLOAD_PATH()+File.separator+fileVO.getSYS_FILE_NAME());
			int fSize = (int) uFile.length();
			
			logger.info("FILE DOWNLOAD-size:"+fSize);
			logger.info("FILE DOWNLOAD-path:"+FILE_PATH_EXTERNAL + fileVO.getUPLOAD_PATH()+File.separator+fileVO.getSYS_FILE_NAME());
			
			if (fSize > 0) {
				String mimetype = "application/x-msdownload";
				int e = fileVO.getSYS_FILE_NAME().lastIndexOf(".");
				int p = Math.max(fileVO.getSYS_FILE_NAME().lastIndexOf("/"), fileVO.getSYS_FILE_NAME().lastIndexOf("\\"));
				String extension = "";
				if(e > p){
					extension = fileVO.getSYS_FILE_NAME().substring(e + 1);
					if("pdf".matches(extension.toLowerCase())){
						logger.info("MATCH OK:pdf");
						mimetype = "application/pdf";
					}else if("ppt".matches(extension.toLowerCase()) || "pptx".matches(extension.toLowerCase())){
						logger.info("MATCH OK:ppt or pptx");
						mimetype = "application/vnd.ms-powerpoint";
					}else if("xls".matches(extension.toLowerCase()) || "xlsx".matches(extension.toLowerCase())){
						logger.info("MATCH OK:xls or xlsx");
						mimetype = "application/vnd.ms-excel";
					}else if("doc".matches(extension.toLowerCase()) || "docx".matches(extension.toLowerCase())){
						logger.info("MATCH OK:doc or docs");
						mimetype = "application/msword";
					}else if("jpg".matches(extension.toLowerCase()) || "jpeg".matches(extension.toLowerCase())){
						logger.info("MATCH OK:jpg or jpeg");
						mimetype = "image/jpg";
					}
				}
				
				response.setContentType(mimetype);
				setDisposition(fileVO.getORG_FILE_NAME(), request, response);
				response.setContentLength(fSize);
				
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Dispotition", "attachment;filename="+fileVO.getORG_FILE_NAME());
				
				BufferedInputStream in = null;
				BufferedOutputStream out = null;
				
				try {
					in = new BufferedInputStream(new FileInputStream(uFile));
					out = new BufferedOutputStream(response.getOutputStream());
					
					FileCopyUtils.copy(in, out);
					out.flush();
				} catch (IOException ex) {
					ex.printStackTrace();
				} finally {
					UtilCommon.close(in, out);
				}
				
			} else {
				response.setContentType("application/x-msdownload");
				
				PrintWriter printwriter = response.getWriter();
				
				printwriter.println("<html>");
				printwriter.println("<br><br><br><h2> 파일을 찾을수가 없습니다:<br>" + fileVO.getORG_FILE_NAME() + "</h2>");
				printwriter.println("</html>");
				
				printwriter.flush();
				printwriter.close();
			}
		}
	}
	
	/**
	 * 에디터 이미지 삽입시 업로드
	 * @param multiRequest
	 * @param model
	 * @param response
	 * @param locale
	 * @throws Exception
	 */
	@RequestMapping(value="/common/editorfileUpload", method=RequestMethod.POST)
	public void editorfileUpload(final MultipartHttpServletRequest multiRequest ,HttpServletResponse response, Locale locale) throws Exception {
		JSONObject json = new JSONObject();
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter writer = response.getWriter();
		FileVO fileVO = new FileVO();
		Iterator<String> fileIterator = multiRequest.getFileNames();
		
		while (fileIterator.hasNext()) {
			MultipartFile mFile = multiRequest.getFile((String) fileIterator.next());
		
			logger.debug(mFile.getContentType());
			logger.debug(mFile.getOriginalFilename());
			String fileExt = UtilFile.getFileExtension(mFile.getOriginalFilename());
			logger.debug(fileExt +"/"+FILE_EXT_EDITOR);
			if(FILE_EXT_EDITOR.indexOf(fileExt.toLowerCase()) == -1){
				UtilJsonResult.setReturnCodeFail(json, messageSource.getMessage("validation.file.availableExt", null, localeResolver.resolveLocale(multiRequest)));
			}else{
				if (mFile.getSize() > 0) {
					fileVO = UtilFile.parseFileInf(mFile, FILE_PREFIX_EDITOR, FILE_PATH_EXTERNAL, FILE_STORE_EDITOR);

					// 에디터로 돌려줘야 할 값
					json.put("uploaded", multiRequest.getParameter("CKEditorFuncNum") == null? 1 : multiRequest.getParameter("CKEditorFuncNum"));
					json.put("uploaded", 1);
					json.put("fileName",fileVO.getSYS_FILE_NAME());
					json.put("url", SERVER_DOMAIN_PC + FILE_VIEWPATH_EXTERNAL + fileVO.getUPLOAD_PATH() + "/" + fileVO.getSYS_FILE_NAME());
				}
			}
		} 
		logger.debug(json.toString());
		writer.print(json.toString());
	}
	
	public void setDisposition(String filename, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String browser = getBrowser(request);

		String dispositionPrefix = "attachment; filename=";
		String encodedFilename = null;

		logger.debug("BROWSER:"+browser);
		
		if (browser.equals("MSIE")) {
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Trident")) { 
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Firefox")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Opera")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("IOS")) {
			encodedFilename = "\"" + new String(filename.getBytes("ksc5601"), "euc-kr") + "\"";
		} else if (browser.equals("Chrome")) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < filename.length(); i++) {
				char c = filename.charAt(i);
				if (c > '~') {
					sb.append(URLEncoder.encode("" + c, "UTF-8"));
				} else {
					sb.append(c);
				}
			}
			encodedFilename = sb.toString();
		} else {
			throw new IOException("Not supported browser");
		}

		logger.info("인코딩파일:"+encodedFilename);
		
		response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);

		if ("Opera".equals(browser)) {
			response.setContentType("application/octet-stream;charset=UTF-8");
		}
	}
	
	public String getBrowser(HttpServletRequest request) {
		String header = request.getHeader("User-Agent");
		
		logger.debug("User-Agent:" + header);
		
		if (header.indexOf("MSIE") > -1) {
			return "MSIE";
		} else if (header.indexOf("Trident") > -1) {
			return "Trident";
		} else if (header.indexOf("Chrome") > -1) {
			return "Chrome";
		} else if (header.indexOf("Opera") > -1) {
			return "Opera";
		} else if (header.indexOf("Safari") > -1 || header.indexOf("iPad") > -1 || header.indexOf("iPhone") > -1 || header.indexOf("iPod") > -1) {
			return "IOS";
		}
		return "Firefox";
	}
	
}
