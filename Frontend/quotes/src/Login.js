import { useRef, useState, useEffect, useContext } from 'react';
import AuthContext from "./context/AuthProvider";

import axios from "./api/axios";
const LOGIN_URL = '/login/'; //change this to whatever is in spring

const Login = () => {
    const { setAuth } = useContext(AuthContext);
    const userRef = useRef();
    const errRef = useRef();

    const [user, setUser] = useState('');
    const [password, setPassword] = useState('');
    const [roles, setRoles] = useState('');
    const [errMsg, setErrMsg] = useState('');
    const [success, setSuccess] = useState('');


    useEffect(() => {
        userRef.current.focus();
    }, [])

    useEffect(() => {
        setErrMsg('');
    }, [user, password])

    const handleSubmit = async (e) => {
        e.preventDefault();
        console.log(user,password);

        try {
            const response = await axios.post(LOGIN_URL, 
                JSON.stringify({user, password}),
                {
                    headers: { 'Content-Type': 'application/json'},
                    withCredentials: true
                }
            );
            console.log(JSON.stringify(response?.data));
            const token = response?.data?.token;
            setAuth({ user, password, roles, token })
            setUser('');
            setPassword('');
            setSuccess(true);
        } catch (err) {
            console.log(err);
            setErrMsg(err);
        }
    }

    return (
        <section>
            <p ref={errRef} className={errMsg ? "errmsg" : "offscreen"} aria-live="assertive">{errMsg}</p>
            <h1>Sign In</h1>
            <form onSubmit={handleSubmit}>
                <label htmlFor="email">
                    Email:
                    <input type="text" id="email" ref={userRef} onChange={(e) => setUser(e.target.value)} value={user} required></input>
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