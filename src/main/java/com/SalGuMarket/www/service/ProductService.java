package com.SalGuMarket.www.service;

import java.util.List;

import com.SalGuMarket.www.domain.FileVO;
import com.SalGuMarket.www.domain.ProductVO;

public interface ProductService {

	List<FileVO> getCategoriesSliderImegeList10Imege();
	
	ProductVO getProductById(Long pno);

	int saveProduct(ProductVO pvo);

}
