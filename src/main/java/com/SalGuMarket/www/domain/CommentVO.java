package com.SalGuMarket.www.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CommentVO {
	
	private int cno, bno;
	private String nickName,content,regAt,modAt,email;

}
