package com.SalGuMarket.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.SalGuMarket.www.domain.BoardVO;
import com.SalGuMarket.www.domain.PagingVO;
import com.SalGuMarket.www.security.AuthVO;
import com.SalGuMarket.www.security.MemberVO;

@Mapper
public interface MemberMapper {

	MemberVO selectEmail(String email);

	List<AuthVO> selectAuths(String email);

	int insert(MemberVO mvo);

	int insertAuthinit(String email);

	List<MemberVO> getList(PagingVO pgvo);

	int getTotalCount(PagingVO pgvo);

	int remove(String email);

	int delete(String email);
	
	int yesProfile(String email);

	void noProfile(String email);

	int getIsProfile(String email);
	
	MemberVO selcetNickName(String nickName);

	MemberVO check(MemberVO mvo);

	int checkpw(MemberVO mvo);

	int updatePWD(MemberVO mvo);

	int selectEmailTotalCount(String email);

	List<BoardVO> getBoardList(@Param("email")String email, @Param("pgvo")PagingVO pgvo);

	
}
