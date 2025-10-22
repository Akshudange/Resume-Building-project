// import React from 'react';
// import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
// import Login from './components/Login';
// import Register from './components/Register';
// import ResumePage from './components/ResumePage';
// import AddSkill from './components/AddSkill';
// import Navbar from './components/Navbar';
// import './styles/style.css';
import Login from "./component/Login.tsx";
import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Register from "./component/Register.tsx";
import ResumePage from "./component/ResumePage.tsx";
import AddSkill from "./component/AddSkill.tsx";
import Navbar from "./component/Navbar.tsx";
import './Styles/Style.css'; 



// function App() {
//   return (
//     <Router>
//       <Navbar />
//       <Routes>
//         <Route path="/" element={<h1>Welcome to Resume System</h1>} />
//         <Route path="/login" element={<Login />} />
//         <Route path="/register" element={<Register />} />
//         <Route path="/resume" element={<ResumePage />} />
//         <Route path="/add-skill" element={<AddSkill />} />
//       </Routes>
//     </Router>
//   );
// }

function App(){
  return(
    <div>
      <ResumePage></ResumePage>
    </div>
  );
}

export default App;
