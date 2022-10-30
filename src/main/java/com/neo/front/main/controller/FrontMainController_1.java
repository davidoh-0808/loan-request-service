package com.neo.front.main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.neo.common.vo.MemberVO;

@Controller
public class FrontMainController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	
	/**
	 * 멤버메인
	 * @param locale
	 * @param paramVO
	 * @return
	 */
	@RequestMapping(value="/front/main/mainList")
	public ModelAndView mainList(@ModelAttribute MemberVO paramVO) throws Exception{
		ModelAndView mav = new ModelAndView("front/main/mainList");
		mav.addObject("paramVO", paramVO);
		return mav;
	}
	
}
