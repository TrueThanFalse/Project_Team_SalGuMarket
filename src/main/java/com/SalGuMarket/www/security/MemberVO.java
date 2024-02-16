package com.SalGuMarket.www.security;

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
public class MemberVO {
   
   private String email, walletAddress, pwd, nickName, regAt, lastLogin, phoneNum, stop;
   private int report;
   
   public MemberVO(String email, String walletAddress) {
	   this.email = email;
	   this.walletAddress = walletAddress;
   }
}