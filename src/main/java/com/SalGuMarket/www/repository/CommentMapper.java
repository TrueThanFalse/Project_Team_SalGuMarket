package com.SalGuMarket.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.SalGuMarket.www.domain.CommentVO;
import com.SalGuMarket.www.domain.PagingVO;

@Mapper
public interface CommentMapper {

	int insert(CommentVO cvo);

	int selectOneBnoTotalCount(long bno);

	List<CommentVO> getList(@Param("bno") long bno, @Param("pgvo") PagingVO pgvo);

	int edit(CommentVO cvo);

}
