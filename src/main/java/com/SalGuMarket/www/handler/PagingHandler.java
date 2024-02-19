package com.SalGuMarket.www.handler;

import com.SalGuMarket.www.domain.PagingVO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PagingHandler {
	
	private int startPage, endPage, totalCount;
	private boolean prev, next;
	private PagingVO pgvo;
	

}
