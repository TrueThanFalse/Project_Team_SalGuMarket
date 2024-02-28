package com.SalGuMarket.www.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.SalGuMarket.www.domain.FileVO;
import com.SalGuMarket.www.domain.ProductDTO;
import com.SalGuMarket.www.domain.ProductVO;
import com.SalGuMarket.www.handler.FileHandler;
import com.SalGuMarket.www.security.AuthMember;
import com.SalGuMarket.www.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/product/*")
public class ProductController {
	
	private final ProductService productService;
	private final FileHandler fileHandler;

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
		log.info(">>> pvo >>> {}", pvo);
		
		model.addAttribute(pvo);
		return "/productDetail";
	}
	
	@GetMapping("/productSale")
	public String sendProductSale(Authentication authentication, RedirectAttributes re) {
		AuthMember authMember = (AuthMember)authentication.getPrincipal();
		String WalletAddress = authMember.getMvo().getWalletAddress();
		log.info(">>> Principal WalletAddress >>> {}", WalletAddress);
		
		if(WalletAddress == null) {
			re.addFlashAttribute("WalletAddressNull", "1");
			return "redirect:/";
		}
		
		return "/product/productSale";
	}
	
	@PostMapping("/productSale")
	public String saveProduct(ProductVO pvo, RedirectAttributes re, Authentication authentication,
			@RequestParam(name="files", required = false) MultipartFile[] files) {
		log.info(">>> pvo >>> {}", pvo);
		if(pvo.getCategory().equals("free")) {
			pvo.setSell("n");
		}else {
			pvo.setSell("y");
		}
		String SellerEmail = authentication.getName();
		pvo.setSellerEmail(SellerEmail);
		
		List<FileVO> flist = null;
		if(files[0].getSize() > 0 || files != null) {
			flist = fileHandler.uploadFile(files);
		}
		
		int isOK = productService.saveProduct(new ProductDTO(pvo, flist));
		
		re.addFlashAttribute("saveProduct", isOK);
		return "redirect:/";
	}
}
