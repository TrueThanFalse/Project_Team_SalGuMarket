package com.SalGuMarket.www.security;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class MemberVO {
	
	private String email, walletAddress, pwd, nickName, regAt, lastLogin, phoneNum, stop;
	private int report;
	private List<AuthVO> authList;
	
	public MemberVO(String email, String walletAddress) {
		this.email = email;
		this.walletAddress = walletAddress;
	}

}