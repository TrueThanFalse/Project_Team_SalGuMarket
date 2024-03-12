package com.SalGuMarket.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.SalGuMarket.www.domain.FileVO;

@Mapper
public interface FileMapper {

	List<FileVO> selectListAllFile(long bno);

	int insertFile(FileVO fvo);

	List<FileVO> getFileList(long bno);

	int saveProductFile(FileVO fvo);

	void deleteFile(String email);

	String getFileName(String email);

	FileVO getFile(String email);

	int insertProfile(FileVO fvo);
}
