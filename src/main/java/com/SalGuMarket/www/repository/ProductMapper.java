package com.SalGuMarket.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.SalGuMarket.www.domain.FileVO;
import com.SalGuMarket.www.domain.ProductDTO;
import com.SalGuMarket.www.domain.ProductVO;

@Mapper
public interface ProductMapper {
	
	ProductVO getProductById(Long pno);

	int saveProduct(ProductVO pvo);

	Long getRecentPno();

	int modifyWalletAddress(@Param("staticBackdropInput") String staticBackdropInput,
			@Param("loginEmail") String loginEmail);

}
