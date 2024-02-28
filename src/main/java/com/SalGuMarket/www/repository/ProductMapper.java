package com.SalGuMarket.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.SalGuMarket.www.domain.FileVO;
import com.SalGuMarket.www.domain.ProductDTO;
import com.SalGuMarket.www.domain.ProductVO;

@Mapper
public interface ProductMapper {

	List<FileVO> getCategoriesSliderImegeList10Imege();
	
	ProductVO getProductById(Long pno);

	int saveProduct(ProductVO pvo);

	Long getRecentPno();

}
