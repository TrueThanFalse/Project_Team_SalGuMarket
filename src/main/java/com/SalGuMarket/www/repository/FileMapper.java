package com.SalGuMarket.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.SalGuMarket.www.domain.FileVO;

@Mapper
public interface FileMapper {

	List<FileVO> selectListAllFile();

}
