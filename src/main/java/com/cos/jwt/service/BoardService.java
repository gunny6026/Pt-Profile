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
import com.cos.jwt.domain.board.BoardDTO;
import com.cos.jwt.domain.board.BoardRepository;
import com.cos.jwt.domain.user.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {
	

	private final BoardRepository boardRepository;
	
	
	//게시판 글쓰기
	@Transactional
	public void boardWrite(Board board, User user) {
		
		board.setUser(user);
		
		boardRepository.save(board);
		
			
		
	}
	
	
	//게시판 글 목록
	@Transactional
	public Page<Board> boardList(Pageable pageable){
		
		
		return boardRepository.findAll(pageable);
	}
	
	//게시판 글 상세
	@Transactional
	public Board boardDetail( int no) {
		
		return boardRepository.findById(no).orElseThrow(
				() -> new IllegalArgumentException(no+"는 존재하지 않습니다.")
				);
	}
	
	@Transactional
	public int boardModify(BoardDTO dto, int no, User user) {
		
		Board board = boardRepository.findById(no).orElseThrow(
				()-> new IllegalArgumentException("해당 게시글이 없습니다.") );
		
		
		if(board.getUser().getId().equals(user.getId())) {
			
			board.setTitle(dto.getTitle());
			board.setContent(dto.getContent());
			return 1;
		}else {
			
			return 0;
		}
				
	}
	
	@Transactional
	public int boardRemove(int no, User user) {
		
		Board board = boardRepository.findById(no).
				orElseThrow(()-> new IllegalArgumentException("해당 아이디가 없습니다") );
		
		if(board.getUser().getUserNo()==user.getUserNo()) {
			boardRepository.deleteById(no);
			return 1;
		}else {
			return 0;
		}
	}

}
