package com.SalGuMarket.www.service;

import java.util.List;

import com.SalGuMarket.www.domain.PagingVO;
import com.SalGuMarket.www.security.MemberVO;

public interface MemberService {

	int insert(MemberVO mvo);

	int getTotalCount(PagingVO pgvo);

	List<MemberVO> getList(PagingVO pgvo);

	int remove(String email);

	int delete(String email);

	MemberVO detail(String email);

	int setProfile(MemberVO mvo);
	
}
