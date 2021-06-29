import React, {Component, useEffect, useState} from 'react';
import './App.css';
import './assets/bootstrap.min.css'
import API from "./api/api";
import {BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import  {Homepage}  from './homepage/Homepage';
import {Dashboard} from './dashboard/Dashboard';
import {Actions} from './actions/Actions';
import { Signup } from './signup/Signup';
function App() {
    const [count,setCount]=useState(0);
    console.log("bye")
   useEffect(()=>{
       console.log("hey")
        API.get('api/test').then(res=>console.log(res.data )).catch(err=> console.log(err))
   },[])
   console.log("dye")
    return (
     
        <Router> 
            <div>Navbar</div>
            <Switch>
                <Route path="/" exact component={Homepage}/>
                <Route path="/signup"  component={Signup}/>
                <Route path="/dashboard"  component={Dashboard}/>
                <Route path="/actions" component={Actions}/>
            </Switch>
           
        </Router>
    )
}

export default App
