package com.SalGuMarket.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.SalGuMarket.www.domain.PagingVO;
import com.SalGuMarket.www.security.AuthVO;
import com.SalGuMarket.www.security.MemberVO;

@Mapper
public interface MemberMapper {

	MemberVO selectEmail(String username);

	List<AuthVO> selectAuths(String username);

	int insert(MemberVO mvo);

	int insertAuthinit(String email);

	List<MemberVO> getList(PagingVO pgvo);

	int getTotalCount(PagingVO pgvo);

	int remove(String email);

	int delete(String email);

	MemberVO selcetNickName(String nickName);
	
}
