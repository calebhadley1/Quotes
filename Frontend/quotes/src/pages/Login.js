import { useRef, useState, useEffect, useContext } from 'react';
import { useNavigate } from 'react-router-dom'
import AuthContext from "../context/AuthProvider";
import { ErrorMessages } from '../constants/Errors';

import axios from "./../api/axios";
const HOME_URL = '/home';
const LOGIN_URL = '/login';
const REGISTER_URL = '/register';

const Login = () => {
    const { setAuth } = useContext(AuthContext);
    const emailRef = useRef();
    const errRef = useRef();
    let navigate = useNavigate();

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [role, setRoles] = useState('');
    const [errMsg, setErrMsg] = useState('');
    const [success, setSuccess] = useState('');


    useEffect(() => {
        emailRef.current.focus();
    }, [])

    useEffect(() => {
        setErrMsg('');
    }, [email, password])

    const handleSubmit = async (e) => {
        e.preventDefault();
        console.log(email,password);

        try {
            const response = await axios.post(LOGIN_URL, 
                JSON.stringify({email, password}),
                {
                    headers: { 'Content-Type': 'application/json'},
                    withCredentials: true
                }
            );
            const token = response?.data?.accessToken;
            setAuth({ email, password, role, token })
            setRoles(response?.data?.role)
            setEmail('');
            setPassword('');
            setSuccess(true);
            navigate(HOME_URL);
        } catch (err) {
            if (err.response) {
                if(err.response.status === 500)
                    setErrMsg(ErrorMessages.INVALID_LOGIN_CREDENTIALS);
                else
                    setErrMsg(ErrorMessages.SERVER_ERROR);
            }
            else {
                setErrMsg(ErrorMessages.SERVER_ERROR);
            }
        }
    }

    const navigateToRegister = () => {
        navigate(REGISTER_URL);
    };

    return (
        <section>
            <div className='form-box'>
                <p ref={errRef} className={errMsg ? "errmsg" : "offscreen"} aria-live="assertive">{errMsg}</p>
                <h5>Sign In</h5>
                <form onSubmit={handleSubmit}>
                    <label htmlFor="email">
                        Email:
                        <input type="text" id="email" ref={emailRef} onChange={(e) => setEmail(e.target.value)} value={email} required></input>
                    </label>
                    <label htmlFor="password">
                        Password:
                        <input type="password" id="password" onChange={(e) => setPassword(e.target.value)} value={password} required></input>
                    </label>
                    <button className='submit-button'>
                        Sign In
                    </button>
                </form>
                <p>
                    Need an Account?<br/>
                    <a href="/register" onClick={navigateToRegister}>Sign Up</a>
                </p>
            </div>
        </section>
    )
}

export default Login