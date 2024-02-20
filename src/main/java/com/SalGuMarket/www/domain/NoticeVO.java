package com.SalGuMarket.www.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NoticeVO {
	
	private int noBno,readCount;
	private String title, content, regAt, modAt, nickName;

}
