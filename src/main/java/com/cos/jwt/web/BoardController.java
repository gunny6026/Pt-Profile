package com.cos.jwt.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.jwt.domain.board.Board;
import com.cos.jwt.domain.board.BoardRepository;
import com.cos.jwt.domain.user.User;
import com.cos.jwt.domain.user.UserRepository;
import com.cos.jwt.service.BoardService;

@RestController
public class BoardController {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired // 의존성 주입 DI
	private UserRepository userRepository;
	
	private HttpSession session;
	
	private BoardService boardSevice;
	
	
	//JWT 토큰만 있으면 접근 가능 , 글 쓰기
	@PostMapping("/post")
	public ResponseEntity<?> write(@RequestBody Board board) {
		
		User principal = (User) session.getAttribute("principal");
		//boardRepository.save(board);
		boardSevice.write(board, principal);
		
		return new ResponseEntity<String>("ok",HttpStatus.CREATED) ;
		
	}
	
	//JWT 토큰만 있으면 접근 가능 , 글 상세
	@GetMapping("/post/{board_no}")
	public ResponseEntity<?> detail(@PathVariable int board_no){
		
		return new ResponseEntity<Board>(boardSevice.detail(board_no),HttpStatus.OK);
	}
	
	// JWT 토큰으로  동일인 체크 후 접근 가능 , 글 수정
	@PutMapping("/post/{board_no}")
	public ResponseEntity<?> update(@RequestBody Board board, @PathVariable int board_no) {
		
		User principal = (User) session.getAttribute("principal");
		
		int result = boardSevice.update(board,board_no ,principal);
		
		if(result ==1) {
			return new ResponseEntity<String>("ok", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("fail", HttpStatus.FORBIDDEN);
		}
	}
	
	
	//JWT 토큰으로 동일인 체크 후 가능 , 글 삭제
	@DeleteMapping("/post/{board_no}")
	public ResponseEntity<?> delete(@PathVariable int board_no){
		
		User principal = (User) session.getAttribute("principal");
		int result = boardSevice.delete(board_no, principal);
		
		
		if(result ==1) {
			return new ResponseEntity<String>("ok",HttpStatus.OK);
			
		}else {
			
			return new ResponseEntity<String>("fail", HttpStatus.FORBIDDEN);
		}
		
		
	}
	
	
	//게시판 글목록
	@GetMapping("/board")
	public ResponseEntity<?> boardList(@PageableDefault(size=5, 
	sort="boardNo", direction = Sort.Direction.DESC) Pageable pageable)  {
		
		
		 return new ResponseEntity<Page<Board>>(boardSevice.boardList(pageable),HttpStatus.OK);
	}
	
	
	
	

}
