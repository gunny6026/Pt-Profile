import './App.css';
import Header from './components/Header';
import { Route } from 'react-router-dom';
import Join from './pages/Join';
import LoginForm from './pages/LoginForm';

function App() {
  return (
    <div>
      <Header/>
      <h1>메인 페이지</h1>
      <Route path="/join" exact={true} component={Join} />
      <Route path="/loginForm" exact={true} component={LoginForm} />
    </div>
  );
}

export default App;
