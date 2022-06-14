import './App.css';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from './pages/Home';
import Layout from "./pages/Layout";
import Login from "./pages/Login";
import Register from './pages/Register.js';

function App() {
  return (
    <main className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Layout />}>
            <Route index element={<Login />} />
            <Route path="home" element={<Home />} />
            <Route path="login" element={<Login />} />
            <Route path="register" element={<Register />} />
            <Route path="*" element={<Login />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </main>
  );
}

export default App;
