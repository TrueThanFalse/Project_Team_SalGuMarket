package com.SalGuMarket.www.handler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.SalGuMarket.www.domain.FileVO;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Slf4j
@Component
public class FileHandler {
	private final String DIR = "C:\\SalGuMarketUploadFile";
	
	public List<FileVO> uploadFile(MultipartFile[] files){
		List<FileVO> flist = new ArrayList<>();
		LocalDate date = LocalDate.now();
		String today = date.toString();
		
		today = today.replace("-", File.separator);
		File folders = new File(DIR, today);
		
		//실제 폴더 생성
		if(!folders.exists()) {
			folders.mkdirs();
		}
		
		//FileVO설정
		for(MultipartFile file : files) {
			FileVO fvo = new FileVO();
			fvo.setSaveDir(today);
			fvo.setFileSize(file.getSize());
			
			String originalFileName = file.getOriginalFilename();
			String FileName = originalFileName.substring(originalFileName.lastIndexOf(File.separator)+1);
			fvo.setFileName(FileName);
			
			UUID uuid = UUID.randomUUID();
			fvo.setUuid(uuid.toString());
			
			//디스크 저장 파일 생성
			String fullFileName = uuid.toString()+"_"+FileName;
			File storeFile = new File(folders, fullFileName);
			
			try {
				file.transferTo(storeFile);
				// 로컬디스크에 원본 파일을 실제로 생성
				
				if(isImageFile(storeFile)) {
					fvo.setFileType(1); // file의 type은 이미지 파일이면 1 아니면 0
					File thumbnail = new File(folders, uuid.toString()+"_th_"+FileName);
					Thumbnails.of(storeFile).size(80,80).toFile(thumbnail); // 섬네일 이미지 생성
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.info("파일 저장 오류");
			}
			flist.add(fvo);
	
		}
		return flist;
	}
	
	public FileVO uploadProfile(String nick, MultipartFile profile_image){
		String profile = "profile";
		
		File folders = new File(DIR, profile);
		
		//실제 폴더 생성
		if(!folders.exists()) {
			folders.mkdirs();
		}
		
		//FileVO설정
		//for(MultipartFile file : profile_image) {
		FileVO fvo = new FileVO();
		fvo.setSaveDir(profile);
		fvo.setFileSize(profile_image.getSize());
		fvo.setFileName(profile);
		
		//디스크 저장 파일 생성
		String fullFileName = nick+"_"+profile;
		File storeFile = new File(folders, fullFileName);
		
		try {
			//원본파일
			profile_image.transferTo(storeFile);
			//file=type 이미지 파일이면 1 아니면 0
			if(isImageFile(storeFile)) {
				fvo.setFileType(1);
				File thumbnail = new File(folders, nick+"_th_"+profile);
				//이미지만 썸네일 가능 (썸네일 사이즈는 다시 정해야함)
				Thumbnails.of(storeFile).size(80,80).toFile(thumbnail);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("파일 저장 오류");
		}
	
		//}
		return fvo;
	}
	
	public List<FileVO> uploadMainIamgeFile(MultipartFile[] files){
		List<FileVO> flist = new ArrayList<>();
		LocalDate date = LocalDate.now();
		String today = date.toString();
		
		today = today.replace("-", File.separator);
		File folders = new File(DIR, today);
		
		//실제 폴더 생성
		if(!folders.exists()) {
			folders.mkdirs();
		}
		
		//FileVO설정
		for(MultipartFile file : files) {
			FileVO fvo = new FileVO();
			fvo.setSaveDir(today);
			fvo.setFileSize(file.getSize());
			
			String originalFileName = file.getOriginalFilename();
			String FileName = originalFileName.substring(originalFileName.lastIndexOf(File.separator)+1);
			fvo.setFileName(FileName);
			
			UUID uuid = UUID.randomUUID();
			fvo.setUuid(uuid.toString());
			
			//디스크 저장 파일 생성
			String fullFileName = uuid.toString()+"_"+FileName;
			File storeFile = new File(folders, fullFileName);
			
			try {
				file.transferTo(storeFile);
				// 로컬디스크에 원본 파일을 실제로 생성
				
				if(isImageFile(storeFile)) {
					fvo.setFileType(1); // file의 type은 이미지 파일이면 1 아니면 0
					File thumbnail = new File(folders, uuid.toString()+"_main_"+FileName);
					Thumbnails.of(storeFile).size(262,270).toFile(thumbnail);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.info("파일 저장 오류");
			}
			flist.add(fvo);
	
		}
		return flist;
	}
	
	public List<FileVO> uploadMinorImageFile(MultipartFile[] files){
		List<FileVO> flist = new ArrayList<>();
		LocalDate date = LocalDate.now();
		String today = date.toString();
		
		today = today.replace("-", File.separator);
		File folders = new File(DIR, today);
		
		//실제 폴더 생성
		if(!folders.exists()) {
			folders.mkdirs();
		}
		
		//FileVO설정
		for(MultipartFile file : files) {
			FileVO fvo = new FileVO();
			fvo.setSaveDir(today);
			fvo.setFileSize(file.getSize());
			
			String originalFileName = file.getOriginalFilename();
			String FileName = originalFileName.substring(originalFileName.lastIndexOf(File.separator)+1);
			fvo.setFileName(FileName);
			
			UUID uuid = UUID.randomUUID();
			fvo.setUuid(uuid.toString());
			
			//디스크 저장 파일 생성
			String fullFileName = uuid.toString()+"_"+FileName;
			File storeFile = new File(folders, fullFileName);
			
			try {
				file.transferTo(storeFile);
				// 로컬디스크에 원본 파일을 실제로 생성
				
				if(isImageFile(storeFile)) {
					fvo.setFileType(1); // file의 type은 이미지 파일이면 1 아니면 0
					File thumbnail = new File(folders, uuid.toString()+"_minor_"+FileName);
					Thumbnails.of(storeFile).size(262,270).toFile(thumbnail);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.info("파일 저장 오류");
			}
			flist.add(fvo);
	
		}
		return flist;
	}

	private boolean isImageFile(File file) throws IOException {
		String mimeType = new Tika().detect(file);
		return mimeType.startsWith("image")? true : false;
	}
}
