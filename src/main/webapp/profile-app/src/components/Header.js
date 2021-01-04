import React from 'react';
import { Link } from 'react-router-dom';

const Header = () => {
	return (
		<div>
			<ul class="header">
			<li>

				<Link to="/Join">회원가입</Link>
			</li>
			<li>
				<Link to="/LoginForm">로그인</Link>
			</li>
			<li></li>


			</ul>
		</div>
	);
};

export default Header;