package com.SalGuMarket.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SalGuMarket.www.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/notice/*")
public class NoticeBoardController {
	
	@GetMapping("/noticeList")
	public void noticeList() {}
	
	@GetMapping("/noticeDetail")
	public void noticeDetail() {}
	
	@GetMapping("/noticeRegister")
	public void noticeRegister() {}
	
	@GetMapping("/noticeModify")
	public void noticeModify() {}
	
	

}
