package com.SalGuMarket.www.service;

import java.util.List;

import com.SalGuMarket.www.domain.BoardDTO;
import com.SalGuMarket.www.domain.BoardVO;
import com.SalGuMarket.www.domain.PagingVO;

public interface BoardService {

	void register(BoardDTO boardDTO);

	List<BoardVO> list(PagingVO pgvo);

	BoardDTO selectOne(long bno);

	void modify(BoardDTO boardDTO);

	int remove(long bno);

	int getTotalCount(PagingVO pgvo);
	
}
