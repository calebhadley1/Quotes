import { useRef, useState, useEffect, useContext } from 'react';
import { useNavigate } from 'react-router-dom'
import AuthContext from "../context/AuthProvider";
import axios from "./../api/axios";
import { ErrorMessages } from '../constants/Errors';

const CONFIRM_ACCOUNT_URL = '/register/confirm'
const HOME_URL = '/home'

const ConfirmAccount = () => {

    const errRef = useRef();
    const tokenRef = useRef();

    const [errMsg, setErrMsg] = useState('');
    const [token, setToken] = useState('');

    const contextValue = useContext(AuthContext)
    const providedToken = contextValue.auth.token;
    let navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await axios.get(CONFIRM_ACCOUNT_URL,
                {
                    params: {token: token},
                    withCredentials: true
                }
            );
            console.log(response.data)
            setToken('');
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

    console.log("hello")
    console.log(contextValue.auth.token)
    return (
        <section>
            <div className='form-box'>
                <p ref={errRef} className={errMsg ? "errmsg" : "offscreen"} aria-live="assertive">{errMsg}</p>
                <h5>Confirm Account</h5>
                <p>Please confirm your account by submiting the token in the field below:</p>
                <p>{providedToken}</p>
                <br></br>
                <form onSubmit={handleSubmit}>
                    <label>
                        Token:
                        <input type="text" id="token" ref={tokenRef} onChange={(e) => setToken(e.target.value)} value={token} required></input>
                    </label>
                    <button className='submit-button'>
                        Submit
                    </button>
                </form>
            </div>
        </section>
    )
}

export default ConfirmAccount;