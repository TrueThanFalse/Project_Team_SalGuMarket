package com.SalGuMarket.www.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.SalGuMarket.www.domain.FileVO;
import com.SalGuMarket.www.domain.ProductVO;
import com.SalGuMarket.www.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/product/*")
public class ProductController {
	
	private final ProductService productService;

	@GetMapping("/")
	public String transferIndex(Model model) {
		List<FileVO> categoriesSliderImegeList = productService.getCategoriesSliderImegeList10Imege();
		log.info(">>> categoriesSliderImegeList >>>" + categoriesSliderImegeList);
		List<String> imegeUrlList = new ArrayList<String>();
		
//		for(int i=0; i<categoriesSliderImegeList.size(); i++) {
//			Stirng imageUrl("/upload/"
//					+ categoriesSliderImegeList.get(i).getSaveDir()
//					+ "/"
//					+ categoriesSliderImegeList.get(i).getUuid()
//					+ "_"
//					+ categoriesSliderImegeList.get(i).getFileName());
//			
//			String result = path.toString();
//			
//			if(!Files.exists(path)) {
//				result = "/img/categories/cat-" + (i+1) + ".jpg";
//			}
//			
//			imegeUrlList.add(result);
//		}
		
	    for(FileVO file : categoriesSliderImegeList) {
	        String imageUrl = "/upload/" + file.getSaveDir() + "/" + file.getUuid() + "_" + file.getFileName();
	        imegeUrlList.add(imageUrl);
	    }
		
		model.addAttribute("imegeUrlList", imegeUrlList);
		model.addAttribute("categoriesSliderImegeList", categoriesSliderImegeList);
		return "/";
	}
	
	@GetMapping("/productDetail")
	public String transferProductDetail(@RequestParam("pno") Long pno, Model model) {
		ProductVO pvo = productService.getProductById(pno);
		log.info(">>> pvo >>>", pvo);
		
		model.addAttribute(pvo);
		return "productDetail";
	}
	
	@GetMapping("/productSale")
	public void transferProductSale() {}
}
