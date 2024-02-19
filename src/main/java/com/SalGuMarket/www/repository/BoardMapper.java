package com.SalGuMarket.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.SalGuMarket.www.domain.BoardVO;
import com.SalGuMarket.www.domain.PagingVO;

@Mapper
public interface BoardMapper {

	int insert(BoardVO bvo);

	List<BoardVO> list(PagingVO pgvo);

	BoardVO selectOne(long bno);

	int edit(BoardVO bvo);

	int remove(long bno);

	int getTotalCount(PagingVO pgvo);

	long getBno();

}