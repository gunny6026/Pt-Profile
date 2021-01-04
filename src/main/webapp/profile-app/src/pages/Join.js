import React, { useState } from 'react';

const Join = (props) => {

	const [user, setUSer] = useState({
			id: '',
			name:'',
			password:'',
			email:'',
			address:'',
			auth_pt:''

	});
	
	const inputHandle = (e) =>{
		setUSer({...user,[e.target.name] :e.target.value})
		console.log(user);
	}

	const submitJoin = (e) => {
		e.preventDefault();
		fetch("http://localhost:8000/join",{
			method:"POST",
			headers:{
				"Content-Type": "application/json; charset=utf-8"
			},
			body:JSON.stringify(user)

		}).then(res =>{
			return res.text();
		}).then(res => {
			if(res ==="ok"){
				props.history.push("/loginForm");
			}else{
				alert("회원가입 실패");
			}
		});

	}

	const changeValue = (e) => {
		setUSer({
			...user,
			[e.target.name] :e.target.value
		})
	}
	return (
		<div>
			<h1>회원가입 화면</h1>
			<form>
			<p>아이디 : </p> <input type="text" name="id" placeholder="아이디" onChange={changeValue} />
			<p>이름 : </p> <input type="text" name="name" placeholder="이름" onChange={changeValue} />
			<p>비밀번호  : </p> <input type="password" name="password" placeholder="pw" onChange={changeValue} />
			<p>email : </p> <input type="text" name="email" placeholder="email" onChange={changeValue} />
			<p>address : </p> <input type="text" name="address" placeholder="address" onChange={changeValue} />
			<br/>
			유저 : <input type="radio" name="auth_pt" value="false" onChange={inputHandle} />
			트레이너 : <input type="radio" name="auth_ph" value="trye" onChange={inputHandle} />
			<br/>
			<button type="submit" onClick={submitJoin}>회원가입</button>


			</form>

		</div>
	);
};

export default Join;