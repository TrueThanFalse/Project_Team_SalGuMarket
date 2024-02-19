package com.SalGuMarket.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class PagingVO {
	private int pageNo, qty;
	private String type, keyword;
	
	public PagingVO() {
		this.pageNo=1;
		this.qty=10;
	}
	
}
