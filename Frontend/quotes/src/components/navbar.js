import React from 'react';
import { Link } from 'react-router-dom'
import '../css/Navbar.css'

const navbar= () =>{
    return (
    <div className='navbar'>
      <ul>
      <li>
        <Link to="/">Sign Out</Link>
      </li>
      <li  className="active">
        <Link to="/home">Home</Link>
      </li>
      </ul>
    </div>
    );
  }
  export default navbar;