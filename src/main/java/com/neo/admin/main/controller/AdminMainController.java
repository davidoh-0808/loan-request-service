package com.neo.admin.main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.neo.common.vo.MemberVO;
import com.neo.security.CustomAdminDetails;

@Controller
public class AdminMainController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	
	/**
	 * 멤버메인
	 * @param locale
	 * @param paramVO
	 * @return
	 */
	@RequestMapping(value="/admin/main/mainList")
	public ModelAndView mainList(@ModelAttribute MemberVO paramVO) throws Exception{
		ModelAndView mav = new ModelAndView("admin/main/mainList");
		CustomAdminDetails memberDetail = (CustomAdminDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
		mav.addObject("paramVO", paramVO);
		return mav;
	}
	
}
