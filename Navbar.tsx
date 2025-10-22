import React from 'react';
import { Link } from 'react-router-dom';

function Navbar() {
  return (
    <nav>
      <Link to="/">Home</Link> | 
      <Link to="/login">Login</Link> | 
      <Link to="/register">Register</Link> | 
      <Link to="/resume">Resume</Link> | 
      <Link to="/add-skill">Add Skill</Link>
    </nav>
  );
}

export default Navbar;
