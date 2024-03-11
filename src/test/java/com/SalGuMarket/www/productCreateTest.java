package com.SalGuMarket.www;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.SalGuMarket.www.domain.ProductVO;
import com.SalGuMarket.www.repository.ProductMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = productCreateTest.class)
public class productCreateTest {
	
	@Autowired
	private ProductMapper productMapper;
	
	@Test
	void productCreate() {
		for(int i=0; i<23; i++) {
			ProductVO pvo = new ProductVO();
			pvo.setTitle("TestTitle" + i);
			pvo.setCategory("beauty");
			pvo.setContent("TestContent" + i);
			
			productMapper.saveProduct(pvo);
		}
		for(int i=0; i<23; i++) {
			ProductVO pvo = new ProductVO();
			pvo.setTitle("TestTitle" + i);
			pvo.setCategory("clothes");
			pvo.setContent("TestContent" + i);
			
			productMapper.saveProduct(pvo);
		}
		for(int i=0; i<23; i++) {
			ProductVO pvo = new ProductVO();
			pvo.setTitle("TestTitle" + i);
			pvo.setCategory("elec");
			pvo.setContent("TestContent" + i);
			
			productMapper.saveProduct(pvo);
		}
		for(int i=0; i<23; i++) {
			ProductVO pvo = new ProductVO();
			pvo.setTitle("TestTitle" + i);
			pvo.setCategory("ticket");
			pvo.setContent("TestContent" + i);
			
			productMapper.saveProduct(pvo);
		}
		for(int i=0; i<23; i++) {
			ProductVO pvo = new ProductVO();
			pvo.setTitle("TestTitle" + i);
			pvo.setCategory("animal");
			pvo.setContent("TestContent" + i);
			
			productMapper.saveProduct(pvo);
		}
		for(int i=0; i<23; i++) {
			ProductVO pvo = new ProductVO();
			pvo.setTitle("TestTitle" + i);
			pvo.setCategory("free");
			pvo.setContent("TestContent" + i);
			pvo.setSell("n");
			
			productMapper.saveProduct(pvo);
		}
	}
}
