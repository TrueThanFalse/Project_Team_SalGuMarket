package com.SalGuMarket.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.SalGuMarket.www.domain.HelpBoardVO;
import com.SalGuMarket.www.domain.PagingVO;

@Mapper
public interface HelpBoardMapper {

	List<HelpBoardVO> list(PagingVO pgvo);

	int getTotalCount(PagingVO pgvo);

	HelpBoardVO selectOne(long hbno);

	long getHBno();

	int insert(HelpBoardVO hbvo);

}
