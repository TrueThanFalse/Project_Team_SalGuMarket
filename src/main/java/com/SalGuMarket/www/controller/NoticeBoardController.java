package com.SalGuMarket.www.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SalGuMarket.www.domain.NoticeBoardVO;
import com.SalGuMarket.www.domain.PagingVO;
import com.SalGuMarket.www.handler.FileHandler;
import com.SalGuMarket.www.handler.PagingHandler;
import com.SalGuMarket.www.service.BoardService;
import com.SalGuMarket.www.service.NoticeBoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/notice/*")
public class NoticeBoardController {

	private final NoticeBoardService noticeBoardService;

	private final FileHandler fileHandler;

	
	 @GetMapping("/noticeList")
	 public void noticeList(Model m,PagingVO pgvo) {
		 List<NoticeBoardVO> list=noticeBoardService.noticeBoardList(pgvo); int
		 totalCount=noticeBoardService.getTotalCount(pgvo); PagingHandler ph=new
		 PagingHandler(pgvo,totalCount); 
	 }
	 

	@GetMapping("/noticeDetail")
	public void noticeDetail() {
	}

	@GetMapping("/noticeRegister")
	public void noticeRegister() {
	}

	@GetMapping("/noticeModify")
	public void noticeModify() {
	}

}
