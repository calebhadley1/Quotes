import { useRef, useState, useEffect, useContext } from 'react';
import { useNavigate } from 'react-router-dom'
import AuthContext from "../context/AuthProvider";
import { ErrorMessages } from '../constants/Errors';
import "../css/Form.css"

import axios from "./../api/axios";

const HOME_URL = '/home';
const LOGIN_URL = '/login';
const REGISTER_URL = '/register';

const Register = () => {

    const { setAuth } = useContext(AuthContext);
    const firstNameRef = useRef();
    const lastNameRef = useRef();
    const emailRef = useRef();
    const errRef = useRef();

    let navigate = useNavigate();

    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [passwordConfirm, setPasswordConfirm] = useState('');

    const [role, setRoles] = useState('');
    const [errMsg, setErrMsg] = useState('');
    const [success, setSuccess] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();

        console.log(firstName, lastName, email, password, passwordConfirm);
        if(password === passwordConfirm){
            try {
                const response = await axios.post(REGISTER_URL, 
                    JSON.stringify({firstName, lastName, email, password}),
                    {
                        headers: { 'Content-Type': 'application/json'},
                        withCredentials: true
                    }
                );
                console.log(response.data)
                const token = response?.data?.accessToken;
                setAuth({ email, password, role, token })
                setRoles(response?.data?.role)
                setFirstName('');
                setLastName('');
                setEmail('');
                setPassword('');
                setPasswordConfirm('');
                setSuccess(true);
                navigate(HOME_URL);
            } catch(err) {
                console.log("error")
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
    }

    const navigateToLogin = () => {
        navigate(LOGIN_URL);
    };

    return (
        <section>
            <div className='form-box'>
                <p ref={errRef} className={errMsg ? "errmsg" : "offscreen"} aria-live="assertive">{errMsg}</p>
                <h5>Sign up</h5>
                <br></br>
                <form onSubmit={handleSubmit}>
                    <label>
                        First Name:
                        <input type="text" id="firstName" ref={firstNameRef} onChange={(e) => setFirstName(e.target.value)} value={firstName} required></input>
                    </label>
                    <label>
                        Last Name:
                        <input type="text" id="lastName" ref={lastNameRef} onChange={(e) => setLastName(e.target.value)} value={lastName} required></input>
                    </label>
                    <label>
                        Email:
                        <input type="email" id="email" ref={emailRef} onChange={(e) => setEmail(e.target.value)} value={email} required></input>
                    </label>
                    <label>
                        Password:
                        <input type="password" id="password" onChange={(e) => setPassword(e.target.value)} value={password} required></input>
                    </label>
                    <label>
                        Password Confirm:
                        <input type="password" id="passwordConfirm" onChange={(e) => setPasswordConfirm(e.target.value)} value={passwordConfirm} required></input>
                    </label>
                    <button className='submit-button'>
                        Sign up
                    </button>
                </form>
                <p >
                Have an Account?<br/>
                <a href="/login" onClick={navigateToLogin}>Login</a>
            </p>
            </div>
        </section>
    )
}

export default Register