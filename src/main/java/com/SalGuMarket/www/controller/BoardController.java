package com.SalGuMarket.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SalGuMarket.www.handler.FileHandler;
import com.SalGuMarket.www.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board/*")
public class BoardController {

	private final BoardService boardService;
	
	private final FileHandler fileHandler;
	
	@GetMapping("/boardList")
	public void boardList() {}
	
	@GetMapping("/boardDetail")
	public void boardDetail() {}
	
	@GetMapping("/boardRegister")
	public void boardRegister() {}
	
	@GetMapping("/boardModify")
	public void boardModify() {}
	
	/*
	 * @GetMapping("/register") public void register() {}
	 * 
	 * @PostMapping("/register") public String register(BoardVO
	 * bvo, @RequestParam(name="files", required = false) MultipartFile[] files) {
	 * List<FileVO> flist = null; if(files[0].getSize()>0|| files!= null) { //파일 핸들러
	 * 작업 flist = fh.uploadFile(files); } bsv.register(new BoardDTO(bvo, flist));
	 * return "index"; }
	 * 
	 * @GetMapping("/list") public void list(Model m, PagingVO pgvo) { List<BoardVO>
	 * list = bsv.list(pgvo); //totalCount int totalCount = bsv.getTotalCount(pgvo);
	 * //PagingHandler PagingHandler ph = new PagingHandler(pgvo, totalCount);
	 * m.addAttribute("list", list); //PagingHandler 객체 보내기 m.addAttribute("ph",
	 * ph); }
	 * 
	 * @GetMapping({"/detail","/modify"}) public void
	 * detail(@RequestParam("bno")long bno,Model m) { BoardDTO bdto =
	 * bsv.selectOne(bno); m.addAttribute("bdto", bdto); }
	 * 
	 * @PostMapping("/modify") public String modify(BoardVO
	 * bvo, @RequestParam(name="files", required = false)MultipartFile[] files) {
	 * List<FileVO> flist = null; if(files[0].getSize()>0||files != null) { flist =
	 * fh.uploadFile(files); } bsv.modify(new BoardDTO(bvo,flist)); return
	 * "redirect:/board/detail?bno="+bvo.getBno(); }
	 * 
	 * @GetMapping("/remove") public String remove(@RequestParam("bno") long bno) {
	 * int isOK = bsv.remove(bno); return "redirect:/board/list"; }
	 */
}

