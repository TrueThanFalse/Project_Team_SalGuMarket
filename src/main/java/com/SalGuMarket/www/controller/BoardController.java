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
	public void boardList(Model m,PagingVO pgvo) {
		List<BoardVO> list=boardService.boardList(pgvo);
		int totalCount=boardService.getTotalCount(pgvo);
		PagingHandler ph = new PagingHandler(pgvo, totalCount);
		m.addAttribute("list",list);
		m.addAttribute("ph",ph);
	}
	
	
	@GetMapping("/boardRegister")
	public void boardRegister() {}
	
	@PostMapping("/boardRegister")
	public String boardRegister(BoardVO bvo,@RequestParam(name="files", required=false)MultipartFile[] files) {
		List<FileVO> flist = null;
		if(files[0].getSize()>0||files!=null) {
			flist=fileHandler.uploadFile(files);
		}
		boardService.boardRegister(new BoardDTO(bvo,flist));
		return "index";
	}
	
	@GetMapping({"/boardDetail","/boardModify"})
	public void boardDetail(@RequestParam("bno") long bno, Model m) {
		BoardDTO bdto=boardService.selectOne(bno);
		m.addAttribute("bdto",bdto);
	}
	
	@PostMapping("/boardModify")
	public String boardModify(BoardVO bvo, @RequestParam(name="files", required=false)MultipartFile[] files) {
		List<FileVO> flist=null;
		if(files[0].getSize()>0||files!=null) {
			flist=fileHandler.uploadFile(files);
		}
		boardService.modify(new BoardDTO(bvo,flist));
		return "redirect:/board.detail?bno="+bvo.getBno();
	}
	
	public String remove(@RequestParam("bno") long bno) {
		int isOk=boardService.remove(bno);
		return "redirect:/board/boardList";
	}
}

