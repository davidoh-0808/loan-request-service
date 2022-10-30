package com.neo.front.product.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.neo.common.vo.MemberVO;

@Controller
public class FrontProductController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	
	/**
	 * 사업소개 페이지
	 * @param locale
	 * @param paramVO
	 * @return
	 */
	@RequestMapping(value="/front/product/intro")
	public ModelAndView intro(@ModelAttribute MemberVO paramVO) throws Exception{
		ModelAndView mav = new ModelAndView("front/product/intro");
		mav.addObject("paramVO", paramVO);
		return mav;
	}
	
	/**
	 * 지원대상 페이지 
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/front/product/target")
	public ModelAndView target(@ModelAttribute MemberVO paramVO) throws Exception{
		ModelAndView mav = new ModelAndView("front/product/target");
		mav.addObject("paramVO", paramVO);
		return mav;
	}
	
	/**
	 * 대출절차 페이지 
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/front/product/process")
	public ModelAndView process(@ModelAttribute MemberVO paramVO) throws Exception{
		ModelAndView mav = new ModelAndView("front/product/process");
		mav.addObject("paramVO", paramVO);
		return mav;
	}
	
	/**
	 * 대출거래기본약관 페이지 
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/front/product/policy")
	public ModelAndView policy(@ModelAttribute MemberVO paramVO) throws Exception{
		ModelAndView mav = new ModelAndView("front/product/policy");
		mav.addObject("paramVO", paramVO);
		return mav;
	}
	
}
