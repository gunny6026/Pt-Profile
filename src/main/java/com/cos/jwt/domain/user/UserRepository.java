package com.cos.jwt.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	//로그인을 위한 함수
	//JPA 네이밍 쿼리
	//select * from user where id =?1 and password =?2;
	User findByIdAndPassword(String id, String password);
	
	/*
	 * @Query(value = "select * from user where id =?1 and password =?2",
	 * nativeQuery = true) User login(String id, String password);
	 */

}
