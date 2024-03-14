package com.SalGuMarket.www.service;

import java.util.List;

import com.SalGuMarket.www.domain.HelpBoardDTO;
import com.SalGuMarket.www.domain.HelpBoardVO;
import com.SalGuMarket.www.domain.PagingVO;

public interface HelpBoardService {

	List<HelpBoardVO> boardList(PagingVO pgvo);

	int getTotalCount(PagingVO pgvo);

	HelpBoardDTO selectOne(long hbno);

	void helpBoardRegister(HelpBoardDTO helpBoardDTO);

	long getHBno();

}