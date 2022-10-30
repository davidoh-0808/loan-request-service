package com.neo.member.main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.neo.common.vo.MemberVO;
import com.neo.security.CustomMemberDetails;

@Controller
public class MemberMainController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	
	/**
	 * 멤버메인
	 * @param locale
	 * @param paramVO
	 * @return
	 */
	@RequestMapping(value="/member/main/mainList")
	public ModelAndView mainList(@ModelAttribute MemberVO paramVO) throws Exception{
		ModelAndView mav = new ModelAndView("member/main/mainList");
		CustomMemberDetails memberDetail = (CustomMemberDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
		mav.addObject("paramVO", paramVO);
		return mav;
	}
	
}
