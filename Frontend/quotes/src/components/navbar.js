import React from 'react';
import { Link } from 'react-router-dom'

const navbar= () =>{
    return (
    <div>
      <li>
        <Link to="/">Sign Out</Link>
      </li>
      <li>
        <Link to="/cats">Home</Link>
      </li>
    </div>
    );
  }
  export default navbar;