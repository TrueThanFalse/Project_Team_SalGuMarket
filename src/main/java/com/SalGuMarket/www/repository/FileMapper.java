package com.SalGuMarket.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.SalGuMarket.www.domain.FileVO;
import com.SalGuMarket.www.domain.PagingVO;

@Mapper
public interface FileMapper {

	List<FileVO> selectListAllFile();

	int insertFile(FileVO fvo);

	List<FileVO> getFileList(long bno);

	int saveProductFile(FileVO fvo);

	List<FileVO> getCategoriesSliderImageList10Image();

	List<FileVO> get8MainImage(PagingVO pgvo);

}
