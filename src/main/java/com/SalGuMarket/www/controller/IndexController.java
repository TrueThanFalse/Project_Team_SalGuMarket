package com.SalGuMarket.www.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.SalGuMarket.www.domain.FileVO;
import com.SalGuMarket.www.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class IndexController {

	private final ProductService productService;
	
	@GetMapping("/")
	public String index(Model model) {
		List<FileVO> categoriesSliderImageList = productService.getCategoriesSliderImageList10Image();

	    for(FileVO file : categoriesSliderImageList) {
	    	file.setSaveDir(file.getSaveDir().replace(File.separator, "/"));
	    }
		
		model.addAttribute("categoriesSliderImageList", categoriesSliderImageList);	
		return "index";
	}
}
