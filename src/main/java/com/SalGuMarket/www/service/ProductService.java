package com.SalGuMarket.www.service;

import java.util.List;

import com.SalGuMarket.www.domain.FileVO;
import com.SalGuMarket.www.domain.ProductDTO;
import com.SalGuMarket.www.domain.ProductVO;

public interface ProductService {

	List<FileVO> getCategoriesSliderImageList10Image();
	
	ProductVO getProductById(Long pno);

	int saveProduct(ProductDTO productDTO);

	int modifyWalletAddress(String staticBackdropInput, String loginEmail);

}
