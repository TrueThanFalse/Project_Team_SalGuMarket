package com.SalGuMarket.www.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.SalGuMarket.www.domain.FileVO;
import com.SalGuMarket.www.domain.PagingVO;
import com.SalGuMarket.www.handler.PagingHandler;
import com.SalGuMarket.www.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class IndexController {

	private final ProductService productService;
	
	@GetMapping("/")
	public String index(Model model, PagingVO pgvo) {
		List<FileVO> categoriesSliderImageList = productService.getCategoriesSliderImageList10Image();
		for(FileVO file : categoriesSliderImageList) {
	    	file.setSaveDir(file.getSaveDir().replace(File.separator, "/"));
	    }
	    
	    List<FileVO> categoryImageList = productService.get8MainImage(pgvo);
	    for(FileVO file : categoryImageList) {
	    	file.setSaveDir(file.getSaveDir().replace(File.separator, "/"));
	    }
	    
	    int totalCount = productService.getTotalCount(pgvo);
	    
	    PagingHandler ph = new PagingHandler(pgvo, totalCount);
		
		model.addAttribute("categoriesSliderImageList", categoriesSliderImageList);
		model.addAttribute("categoryImageList", categoryImageList);
		model.addAttribute("ph", ph);
		return "index";
	}
}
