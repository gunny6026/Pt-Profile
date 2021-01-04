import React, { useState } from 'react';
import {useDispatch} from "react-redux";

const LoginForm = (props) => {
	
	const dispatch = useDispatch();
	const [user, setUser] = useState({
			id: "",
			password:""

	});

	const submitLogin = (e) =>{
		e.preventDefault();
		fetch("http://localhost:8000/login", {
			method :"POST",
			headers : {
				"Content-Type":"application/json; charset=utf-8"
			},
			body: JSON.stringify(user)
		}).then(res => {
			for(let header of res.headers.entries()){
				if(header[0] === "authorization"){
					localStorage.setItem("Authorization", header[1]);
				}
			}
			return res.text();
		}).then(
			res=>{
			if(res ==="ok"){
				dispatch(LoginForm);
				props.history.push("/")
			}else{
				alert("아이디와 비밀번호를 다시 입력하세요");
			}
			});

	}

	const changeValue = (e) => {
		setUser({
			...user,
			[e.target.name]: e.target.value
		});
	}

	return (
		<div>
			<form>
				<p>아이디 :</p> <input type="text" name="id" placeholder="id" onChange={changeValue} />
				<p>비밀번호 :</p> <input type="password" name="password" placeholder="password" onChange={changeValue} />

				<button type="submit" onClick={submitLogin}></button>
			</form>

		</div>
	);
};

export default LoginForm;