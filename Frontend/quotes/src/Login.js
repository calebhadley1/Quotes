import { useRef, useState, useEffect, useContext } from 'react';
import AuthContext from "./context/AuthProvider";
import { ErrorMessages } from './constants/Errors';

import axios from "./api/axios";
const LOGIN_URL = '/login';
const REGISTER_URL = '/register';

const Login = () => {
    const { setAuth } = useContext(AuthContext);
    const emailRef = useRef();
    const errRef = useRef();

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [roles, setRoles] = useState('');
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
            const token = response?.data;
            setAuth({ email, password, roles, token })
            setEmail('');
            setPassword('');
            setSuccess(true);
        } catch (err) {
            if (err.response) {
                if(err.response.status == 500)
                    setErrMsg(ErrorMessages.INVALID_LOGIN_CREDENTIALS);
                else
                    setErrMsg(ErrorMessages.SERVER_ERROR);
            }
            else {
                setErrMsg(ErrorMessages.SERVER_ERROR);
            }
        }
    }

    return (
        <section>
            <p ref={errRef} className={errMsg ? "errmsg" : "offscreen"} aria-live="assertive">{errMsg}</p>
            <h1>Sign In</h1>
            <form onSubmit={handleSubmit}>
                <label htmlFor="email">
                    Email:
                    <input type="text" id="email" ref={emailRef} onChange={(e) => setEmail(e.target.value)} value={email} required></input>
                </label>
                <label htmlFor="password">
                    Password:
                    <input type="password" id="password" onChange={(e) => setPassword(e.target.value)} value={password} required></input>
                </label>
                <button>
                    Sign In
                </button>
            </form>
            <p>
                Need an Account?<br/>
                <span className="line">
                    {/*put router link here*/}
                    <a href="#">Sign Up</a>
                </span>
            </p>
        </section>
    )
}

export default Login