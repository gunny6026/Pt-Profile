package com.cos.jwt.config.jwt;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cos.jwt.domain.user.User;
import com.cos.jwt.domain.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JwtAuthenticationFilter implements Filter{

	private UserRepository userRepository;
	
	public JwtAuthenticationFilter(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("JwtAuthenticationFilter login 작동");
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		PrintWriter out = resp.getWriter();
		
		String method = req.getMethod();
		System.out.println(method);
		if(!method.equals("POST")) {
			out.print("required post method");
			out.flush();
		}else {
			ObjectMapper om = new ObjectMapper();
			try {
				System.out.println("sss");
				User user = om.readValue(req.getInputStream(), User.class);

				User personEntity = 
						userRepository.findByIdAndPassword(user.getId(), user.getPassword());
				
				if(personEntity == null) {
					System.out.println("fail");
					out.print("fail");
					out.flush();
				}else {
					System.out.println("인증되었습니다.");
					
					String jwtToken = 
							JWT.create()
							.withSubject("토큰제목")
							.withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60))
							.withClaim("id", personEntity.getId())
							.sign(Algorithm.HMAC512(JwtProps.secret));
					
					resp.addHeader(JwtProps.header, JwtProps.auth+jwtToken);
					out.print("ok");
					out.flush();
				}
			} catch (Exception e) {
				System.out.println("오류 : "+e.getMessage());
			}
		}
	}
}