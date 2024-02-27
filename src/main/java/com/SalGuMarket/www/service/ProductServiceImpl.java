package com.SalGuMarket.www.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.SalGuMarket.www.domain.FileVO;
import com.SalGuMarket.www.domain.ProductVO;
import com.SalGuMarket.www.repository.ProductMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

	private final ProductMapper productMapper;

	@Override
	public ProductVO getProductById(Long pno) {
		return productMapper.getProductById(pno);
	}

	@Override
	public List<FileVO> getCategoriesSliderImegeList10Imege() {
		return productMapper.getCategoriesSliderImegeList10Imege();
	}

	@Override
	public int saveProduct(ProductVO pvo) {
		return productMapper.saveProduct(pvo);
	}
}
