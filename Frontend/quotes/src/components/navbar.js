import React from 'react';
import { Link } from 'react-router-dom'

const navbar= () =>{
    return (
    <div>
      <li>
        <Link to="/">Sign Out</Link>
      </li>
      <li>
        <Link to="/home">Home</Link>
      </li>
    </div>
    );
  }
  export default navbar;