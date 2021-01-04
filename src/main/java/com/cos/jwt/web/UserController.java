package com.cos.jwt.web;

import java.util.List;
import java.util.function.Supplier;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.jwt.domain.user.User;
import com.cos.jwt.domain.user.UserRepository;
import com.cos.jwt.service.UserService;

@RestController
public class UserController {

	@Autowired // 의존성 주입 DI
	private UserRepository userRepository;
	
	@Autowired
	private  HttpSession session;
	
	@Autowired
	private UserService userService;

	
	
	//회원가입
	@PostMapping("/join")
	public ResponseEntity<?> join(@RequestBody User object) {
		
		System.out.println(object);
	//	object.setAuth_pt(true);
		userService.join(object);
		//userRepository.save(object);
		
		return new ResponseEntity<String>("ok",HttpStatus.CREATED);
	}
	
	//로그 아웃
	@GetMapping("/logout")
	public ResponseEntity<?> logout(){
		session.invalidate();
		
		return new ResponseEntity<String>("ok", HttpStatus.OK);
	}
	
	//DB에서 아이디 값 가져오기 에러 패턴 익히기
	@GetMapping("/login/{user_no}")
	public User select(@PathVariable int user_no) {

		User user = userRepository.findById(user_no).orElseThrow(new Supplier<IllegalArgumentException>() {

			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 사용자 없음");
			}
		});

		return user;
	}
	
	//로그인하기
	//@GetMapping("loginex/{user_no}")
	//public String login(@PathVariable int user_no) {
		
	//	User login = userRepository.findById(user_no).orElseThrow(
	//			() ->new IllegalArgumentException("DB에 해당 번호가 없음")
	//			);
		
	//String id =	login.getId();
	//String password =	login.getPassword();
	
	//userService.login(login);
	 //userRepository.findByIdAndPassword(id, password);
		
	//return 	"로그인 성공";
		
	//}
	
	
	//빌더 패던 회원가입 연습
	@PostMapping("/home")
	public String home() {

		User user = User.builder().address("강남").email("rjslek@naver.com").auth_pt(true)
				.password("ㅇㅇㅇㅇㅇㅇㅇㄴㄴ").name("거니찡").id("qkrrjsgml").build();

		userRepository.save(user);

		return "home: " + user;
	}

	// User에 있는 값 다 들고오기
	@GetMapping("/list")
	public List<User> userList(){
		
		 
		
		return userRepository.findAll();
		
	}
	
	@Transactional
	@PutMapping("/user/{user_no}")
	public String userUpdate(@PathVariable int user_no , @RequestBody User user) {
		
		User DBuser = userRepository.findById(user_no).orElseThrow(() -> {
			return new IllegalArgumentException("수정 실패");
		}
				);
		DBuser.setId(user.getId());
		DBuser.setName(user.getName());
		DBuser.setPassword(user.getPassword());
		DBuser.setEmail(user.getEmail());
		
		//userRepository.save(DBuser);
		
		return "수정완료"+DBuser;
		
	}
	
	@DeleteMapping("/user/{user_no}")
	public String userDelete(@PathVariable int user_no) {
	
		
		try {
		userRepository.deleteById(user_no);
		
		}catch(EmptyResultDataAccessException e){
			
			return "해당 아이디가 없습니다";
			
		}
		return "삭제완료 삭제된 no :"+user_no;
		
	}
}
