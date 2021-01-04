package com.cos.jwt.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.cos.jwt.domain.board.Board;
import com.cos.jwt.domain.board.BoardRepository;
import com.cos.jwt.domain.user.User;

@Service
public class BoardService {
	
	@Autowired
	BoardRepository boardRepository;
	
	
	//게시판 글쓰기
	@Transactional
	public void write(Board board, User principal) {
		
		board.setUser(principal);
		
		boardRepository.save(board);
		
			
		
	}
	
	
	//게시판 글 목록
	@Transactional
	public Page<Board> boardList(Pageable pageable){
		
		
		return boardRepository.findAll(pageable);
	}
	
	//게시판 글 상세
	@Transactional
	public Board detail( int id) {
		
		return boardRepository.findById(id).orElseThrow(
				() -> new IllegalArgumentException(id+"는 존재하지 않습니다.")
				);
	}
	
	@Transactional
	public int update(Board board, int board_no, User principal) {
		
		Board boardEntity = boardRepository.findById(board_no).orElseThrow(
				()-> new IllegalArgumentException("해당 게시글이 없습니다.") );
		
		
		if(boardEntity.getUser().getUser_no() == principal.getUser_no()) {
			
			boardEntity.setTitle(board.getTitle());
			boardEntity.setContent(board.getContent());
			return 1;
		}else {
			
			return 0;
		}
				
	}
	
	@Transactional
	public int delete(int board_no, User principal) {
		
		Board boardEntity = boardRepository.findById(board_no).
				orElseThrow(()-> new IllegalArgumentException("해당 아이디가 없습니다") );
		
		if(boardEntity.getUser().getUser_no() == principal.getUser_no()) {
			
			return 1;
		}else {
			return 0;
		}
	}

}
