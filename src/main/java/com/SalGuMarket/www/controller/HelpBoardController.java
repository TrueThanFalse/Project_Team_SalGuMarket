package com.SalGuMarket.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SalGuMarket.www.service.HelpBoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/help/*")
public class HelpBoardController {
	
	//1:1 문의 게시판 컨트롤러
	
	private final HelpBoardService hbsv;
	
	@GetMapping("/register")
	public void register() {}
}
