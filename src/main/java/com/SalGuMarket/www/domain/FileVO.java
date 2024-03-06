package com.SalGuMarket.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FileVO {
	private String uuid; //사진id
	private String saveDir; //저장날짜
	private String fileName; //파일이름
	private int fileType; //파일타입
	private int mainImage;
	private long bno; //자유게시판
	private long pno; //상품
	private long notBno; //공지사항
	private long qBno; //1:1문의
	private long fileSize; //파일 사이즈
	private String regAt; //등록날짜
	private String Email;

}


//DB
/*
create table file(
uuid varchar(256) not null,
save_dir varchar(256) not null,
file_name varchar(256) not null,
file_type int(1) default 0,
bno bigint,
pno bigint,
not_bno bigint,
q_bno bigint,
file_size bigint not null,
reg_at datetime default now(),
primary key(uuid));
 */
