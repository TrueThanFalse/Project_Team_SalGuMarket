package com.SalGuMarket.www.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.SalGuMarket.www.domain.FileVO;
import com.SalGuMarket.www.domain.ProductDTO;
import com.SalGuMarket.www.domain.ProductVO;
import com.SalGuMarket.www.repository.FileMapper;
import com.SalGuMarket.www.repository.ProductMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

	private final ProductMapper productMapper;
	private final FileMapper fileMapper;

	@Override
	public ProductVO getProductById(Long pno) {
		return productMapper.getProductById(pno);
	}

	@Override
	public List<FileVO> getCategoriesSliderImageList10Image() {
		return productMapper.getCategoriesSliderImageList10Image();
	}

	@Override
	@Transactional
	public int saveProduct(ProductDTO pdto) {
		int isOK = productMapper.saveProduct(pdto.getPvo());
		
		if(isOK > 0) {
			Long pno = productMapper.getRecentPno();
			// 위 productMapper.saveProduct(pDTO.getPvo()); 구문으로 생성된 Pno이다.
			
			for(FileVO fvo : pdto.getFlist()) {
				fvo.setPno(pno);
				isOK *= fileMapper.saveProductFile(fvo);
			}
		}
		
		return isOK;
	}

	@Override
	public int modifyWalletAddress(String staticBackdropInput, String loginEmail) {
		return productMapper.modifyWalletAddress(staticBackdropInput, loginEmail);
	}
}
